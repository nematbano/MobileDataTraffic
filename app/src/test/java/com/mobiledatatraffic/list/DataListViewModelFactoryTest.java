package com.mobiledatatraffic.list;

import com.mobiledatatraffic.model.MobileData;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DataListViewModelFactoryTest {

    @InjectMocks
    private DataListViewModelFactory subject;
    @Mock
    private MobileData mobileData;

    @Test
    public void generateViewModels_givenDataListNull_returnsEmptyList() {
        List<DataListViewModel> result = subject.generateViewModels(null);
        assertEquals(0,result.size());
    }

    @Test
    public void generateViewModels_givenDataListNonNull_returnsList() {
        List<DataListViewModel> result = subject.generateViewModels(Collections.singletonList(mobileData));
        assertEquals(2,result.size());
        assertThat(result.get(0), instanceOf(DataListHeaderViewModel.class));
        assertThat(result.get(1),instanceOf(DataListItemViewModel.class));
    }
}