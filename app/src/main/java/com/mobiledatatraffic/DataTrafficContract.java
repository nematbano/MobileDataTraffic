package com.mobiledatatraffic;

import com.mobiledatatraffic.list.DataListViewModel;

import java.util.List;

public class DataTrafficContract {
    public interface View{
        void loadData(List<DataListViewModel> mobileDataList);

        void showError();
    }
    public interface Presenter{
        void setView(View view);
        void fetchData();
    }
}
