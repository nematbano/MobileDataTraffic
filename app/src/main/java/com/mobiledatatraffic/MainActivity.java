package com.mobiledatatraffic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mobiledatatraffic.helper.DecreaseInVolumeVerifier;
import com.mobiledatatraffic.helper.JsonConverter;
import com.mobiledatatraffic.helper.MobileDataResponseMapper;
import com.mobiledatatraffic.helper.NetworkConnectionHelper;
import com.mobiledatatraffic.helper.QuarterCombiner;
import com.mobiledatatraffic.list.DataListAdapter;
import com.mobiledatatraffic.list.DataListViewModel;
import com.mobiledatatraffic.list.DataListViewModelFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DataTrafficContract.View {
    RecyclerView dataListRecyclerView;
    DataTrafficContract.Presenter dataTrafficPresenter;
    NetworkConnectionHelper networkConnectionHelper;
    DataListAdapter dataListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataListRecyclerView = findViewById(R.id.data_list);
        dataListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataListAdapter = new DataListAdapter();

        JsonConverter jsonConverter = new JsonConverter();
        QuarterCombiner quarterCombiner = new QuarterCombiner();
        DecreaseInVolumeVerifier decreaseInVolumeVerifier = new DecreaseInVolumeVerifier();
        MobileDataResponseMapper mobileDataResponseMapper = new MobileDataResponseMapper(quarterCombiner, decreaseInVolumeVerifier);
        DataListViewModelFactory dataListViewModelFactory = new DataListViewModelFactory();
        dataTrafficPresenter = new DataTrafficPresenter(networkConnectionHelper,jsonConverter,mobileDataResponseMapper,dataListViewModelFactory);
    }

    @Override
    protected void onResume() {
        super.onResume();

        dataTrafficPresenter.setView(this);
        dataTrafficPresenter.fetchData();
    }

    @Override
    public void loadData(List<DataListViewModel> mobileDataList) {
        dataListAdapter.setDataList(mobileDataList);
        dataListRecyclerView.setAdapter(dataListAdapter);
        dataListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {

    }
}
