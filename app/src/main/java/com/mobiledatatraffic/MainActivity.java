package com.mobiledatatraffic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.mobiledatatraffic.helper.DecreaseInVolumeFactory;
import com.mobiledatatraffic.helper.JsonConverter;
import com.mobiledatatraffic.helper.MobileDataResponseMapper;
import com.mobiledatatraffic.helper.NetworkConnectionHelper;
import com.mobiledatatraffic.helper.NetworkConnectionHelperFactory;
import com.mobiledatatraffic.helper.QuarterCombiner;
import com.mobiledatatraffic.helper.VolumeCalculator;
import com.mobiledatatraffic.list.DataListAdapter;
import com.mobiledatatraffic.list.DataListItemViewHolder;
import com.mobiledatatraffic.list.DataListViewModel;
import com.mobiledatatraffic.list.DataListViewModelFactory;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataTrafficContract.View, DataListItemViewHolder.OnImageClickedCallback {
    RecyclerView dataListRecyclerView;
    DataTrafficContract.Presenter dataTrafficPresenter;
    NetworkConnectionHelper networkConnectionHelper;
    DataListAdapter dataListAdapter;
    AlertDialog alertDialog = null;
    String[] listToExclude = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataListRecyclerView = findViewById(R.id.data_list);
        dataListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataListAdapter = new DataListAdapter();

        listToExclude = getResources().getStringArray(R.array.list_to_exclude);

        JsonConverter jsonConverter = new JsonConverter();
        QuarterCombiner quarterCombiner = new QuarterCombiner();
        DecreaseInVolumeFactory decreaseInVolumeFactory = new DecreaseInVolumeFactory();
        VolumeCalculator volumeCalculator = new VolumeCalculator();
        MobileDataResponseMapper mobileDataResponseMapper = new MobileDataResponseMapper(quarterCombiner, decreaseInVolumeFactory, volumeCalculator);
        DataListViewModelFactory dataListViewModelFactory = new DataListViewModelFactory();
        NetworkConnectionHelperFactory networkConnectionHelperFactory = new NetworkConnectionHelperFactory(this, jsonConverter, mobileDataResponseMapper, dataListViewModelFactory, Arrays.asList(listToExclude));
        dataTrafficPresenter = new DataTrafficPresenter(networkConnectionHelperFactory);
    }

    @Override
    protected void onResume() {
        super.onResume();

        dataTrafficPresenter.fetchData(networkConnectionHelper);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (networkConnectionHelper != null) {
            networkConnectionHelper.cancel(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    @Override
    public void loadData(List<DataListViewModel> mobileDataList) {
        dataListAdapter.setDataList(mobileDataList);
        dataListAdapter.setOnImageClickedCallback(this);
        dataListRecyclerView.setAdapter(dataListAdapter);
        dataListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(getString(R.string.error_message));
        alertDialogBuilder.setPositiveButton("Okay",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        if (alertDialog != null && alertDialog.isShowing()) {
                            alertDialog.dismiss();
                        }
                    }
                });

        alertDialog = alertDialogBuilder.create();
        if (alertDialog.isShowing()) {
            alertDialog.dismiss();
        }

        alertDialog.show();

    }

    @Override
    public void onImageClicked(String from, String to, String quarter) {
        if(from!=null) {
            String q = String.format(getString(R.string.quarter), quarter);
            Toast.makeText(this, String.format(getString(R.string.message), from, to, q),
                    Toast.LENGTH_LONG).show();
        }

    }
}
