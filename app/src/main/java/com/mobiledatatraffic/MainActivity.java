package com.mobiledatatraffic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements DataTrafficContract.View {
    RecyclerView dataListRecyclerView;
    DataTrafficContract.Presenter dataTrafficPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataListRecyclerView = findViewById(R.id.data_list);
        dataListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataTrafficPresenter = new DataTrafficPresenter();
        dataTrafficPresenter.setView(this);
    }

    @Override
    public void loadData() {

    }
}
