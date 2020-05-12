package com.mobiledatatraffic;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import okio.Buffer;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public final MockWebServerRule mMockServerRule = new MockWebServerRule();

    @Before
    public void setUp() throws Exception {
        activityRule.launchActivity(null);
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
    }

    @Test
    public void onLoad_givenSuccess_seesDataVolumeList() {
        mMockServerRule.server().setDispatcher(getDispatcher());
        onView(withRecyclerView(R.id.data_list).atPosition(0))
                .check(matches(hasDescendant(withText(R.string.header))));
    }

    @Test
    public void onLoad_givenServerError_showsErrorPopup() {
        mMockServerRule.server().enqueue(new MockResponse().setResponseCode(500));

        onView(withText(R.string.error_message)).check(matches(isDisplayed()));
    }

    private static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    private Dispatcher getDispatcher(){
        final Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                if (request.getPath().startsWith("https://data.gov.sg/")) {
                    try {
                        return new MockResponse().setResponseCode(200)
                                .setBody(new Buffer().readFrom(getClass().getClassLoader().getResourceAsStream("get.json")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                throw new IllegalStateException("no mock set up for " + request.getPath());
            }
        };
        return dispatcher;
    }
}