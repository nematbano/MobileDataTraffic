package com.mobiledatatraffic;

public class DataTrafficContract {
    public interface View{
        void loadData();
    }
    public interface Presenter{
        void setView(View view);
        void fetchData();
    }
}
