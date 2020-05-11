package com.mobiledatatraffic;

import android.os.AsyncTask;

import com.mobiledatatraffic.helper.NetworkConnectionHelper;
import com.mobiledatatraffic.helper.NetworkConnectionHelperFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DataTrafficPresenterTest {
    @InjectMocks
    DataTrafficPresenter subject;
    @Mock
    DataTrafficContract.View view;
    @Mock
    NetworkConnectionHelperFactory factory;
    @Mock
    NetworkConnectionHelper networkConnectionHelper;
    @Mock
    NetworkConnectionHelper networkConnectionHelper1;

    @Test
    public void fetchData_givenNetworkConnectionNull_executesTask() {
        when(factory.get()).thenReturn(networkConnectionHelper);
        subject.fetchData(null);
        verify(networkConnectionHelper).execute();
    }

    @Test
    public void fetchData_givenNetworkConnectionNonNull_givenStatusRunning_executesTask() {
        when(factory.get()).thenReturn(networkConnectionHelper1);
        when(networkConnectionHelper.getStatus()).thenReturn(AsyncTask.Status.RUNNING);
        subject.fetchData(networkConnectionHelper);
        verify(networkConnectionHelper).cancel(true);
        verify(networkConnectionHelper1).execute();
    }

    @Test
    public void fetchData_givenNetworkConnectionNonNull_givenStatusNotRunning_doesNotExecuteTask() {
        when(networkConnectionHelper.getStatus()).thenReturn(AsyncTask.Status.FINISHED);
        subject.fetchData(networkConnectionHelper);
        verify(networkConnectionHelper, never()).cancel(true);
    }
}