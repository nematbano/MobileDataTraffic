package com.mobiledatatraffic.helper;

import com.mobiledatatraffic.DataTrafficContract;
import com.mobiledatatraffic.list.DataListViewModelFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class NetworkConnectionHelperFactoryTest {
    @Mock
    private DataTrafficContract.View view;
    @Mock
    private JsonConverter jsonConverter;
    @Mock
    private MobileDataResponseMapper mobileDataResponseMapper;
    @Mock
    private DataListViewModelFactory dataListViewModelFactory;

    NetworkConnectionHelperFactory subject;

    @Test
    public void get() {
        subject = new NetworkConnectionHelperFactory(view, jsonConverter,
                mobileDataResponseMapper,
                dataListViewModelFactory, Collections.singletonList("year"));
        NetworkConnectionHelper result = subject.get();
        assertEquals(view, result.view);
        assertEquals(jsonConverter, result.jsonConverter);
        assertEquals(mobileDataResponseMapper, result.mobileDataResponseMapper);
        assertEquals(dataListViewModelFactory, result.dataListViewModelFactory);
        assertEquals("year", result.listToExclude.get(0));
    }
}