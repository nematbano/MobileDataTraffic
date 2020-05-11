package com.mobiledatatraffic;

import android.os.AsyncTask;

import com.mobiledatatraffic.helper.NetworkConnectionHelper;
import com.mobiledatatraffic.helper.NetworkConnectionHelperFactory;

public class DataTrafficPresenter implements DataTrafficContract.Presenter {
    private NetworkConnectionHelperFactory networkConnectionHelperFactory;

    public DataTrafficPresenter(NetworkConnectionHelperFactory networkConnectionHelperFactory) {
        this.networkConnectionHelperFactory = networkConnectionHelperFactory;
    }

    @Override
    public void fetchData(NetworkConnectionHelper networkConnectionHelper) {
        try {
            if ((networkConnectionHelper != null)) {
                if (networkConnectionHelper.getStatus() == AsyncTask.Status.RUNNING) {
                    networkConnectionHelper.cancel(true);
                    networkConnectionHelper = networkConnectionHelperFactory.get();
                    networkConnectionHelper.execute();
                }
            }
            else {
                networkConnectionHelper = networkConnectionHelperFactory.get();
                networkConnectionHelper.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
