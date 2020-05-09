package com.mobiledatatraffic;

public class DataTrafficPresenter implements DataTrafficContract.Presenter {
    private DataTrafficContract.View view;

    @Override
    public void setView(DataTrafficContract.View view) {
        this.view = view;
    }

    @Override
    public void fetchData() {

    }
}
