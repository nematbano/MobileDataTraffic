package com.mobiledatatraffic.helper;

import com.mobiledatatraffic.DataTrafficContract;
import com.mobiledatatraffic.list.DataListViewModelFactory;

import java.util.List;

public class NetworkConnectionHelperFactory {
    private DataTrafficContract.View view;
    private JsonConverter jsonConverter;
    private MobileDataResponseMapper mobileDataResponseMapper;
    private DataListViewModelFactory dataListViewModelFactory;
    private List<String> listToExclude;

    public NetworkConnectionHelperFactory(DataTrafficContract.View view, JsonConverter jsonConverter,
                                          MobileDataResponseMapper mobileDataResponseMapper,
                                          DataListViewModelFactory dataListViewModelFactory,
                                          List<String> listToExclude) {
        this.view = view;
        this.jsonConverter = jsonConverter;
        this.mobileDataResponseMapper = mobileDataResponseMapper;
        this.dataListViewModelFactory = dataListViewModelFactory;
        this.listToExclude = listToExclude;
    }

    public NetworkConnectionHelper get(){
        return new NetworkConnectionHelper(view,jsonConverter, mobileDataResponseMapper,dataListViewModelFactory,listToExclude);
    }
}
