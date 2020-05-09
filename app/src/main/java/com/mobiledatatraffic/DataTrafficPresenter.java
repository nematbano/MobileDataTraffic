package com.mobiledatatraffic;

import android.os.AsyncTask;

import com.mobiledatatraffic.helper.DecreaseInVolumeVerifier;
import com.mobiledatatraffic.helper.JsonConverter;
import com.mobiledatatraffic.helper.MobileDataResponseMapper;
import com.mobiledatatraffic.helper.NetworkConnectionHelper;
import com.mobiledatatraffic.helper.QuarterCombiner;
import com.mobiledatatraffic.list.DataListViewModelFactory;

public class DataTrafficPresenter implements DataTrafficContract.Presenter {
    private DataTrafficContract.View view;
    private NetworkConnectionHelper networkConnectionHelper;
    private JsonConverter jsonConverter;
    private MobileDataResponseMapper mobileDataResponseMapper;
    private DataListViewModelFactory dataListViewModelFactory;

    public DataTrafficPresenter(
                                NetworkConnectionHelper networkConnectionHelper,
                                JsonConverter jsonConverter,
                                MobileDataResponseMapper mobileDataResponseMapper,
                                DataListViewModelFactory dataListViewModelFactory) {
        this.networkConnectionHelper = networkConnectionHelper;
        this.jsonConverter = jsonConverter;
        this.mobileDataResponseMapper = mobileDataResponseMapper;
        this.dataListViewModelFactory = dataListViewModelFactory;
    }

    @Override
    public void setView(DataTrafficContract.View view) {
        this.view = view;
    }

    @Override
    public void fetchData() {
        try {
            if ((networkConnectionHelper != null)) {
                if (networkConnectionHelper.getStatus() != AsyncTask.Status.RUNNING) {
                    networkConnectionHelper.cancel(true);
                    networkConnectionHelper = new NetworkConnectionHelper(view,jsonConverter, mobileDataResponseMapper,dataListViewModelFactory);
                    networkConnectionHelper.execute();
                }
            }
            else {
                networkConnectionHelper = new NetworkConnectionHelper(view,jsonConverter, mobileDataResponseMapper,dataListViewModelFactory);
                networkConnectionHelper.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
