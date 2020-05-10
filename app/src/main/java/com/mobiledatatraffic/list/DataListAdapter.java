package com.mobiledatatraffic.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.mobiledatatraffic.MainActivity;
import com.mobiledatatraffic.R;

import java.util.List;

import static com.mobiledatatraffic.list.DataListViewType.*;

public class DataListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<DataListViewModel> viewModels;
    private DataListItemViewHolder.OnImageClickedCallback onImageClickedCallback;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case LIST_HEADER:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.view_data_list_header, parent, false);
                return new DataListStaticViewHolder(view);

            case LIST_ITEM:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.view_data_list_item, parent, false);
                return new DataListItemViewHolder(view);

            default:
                throw new IllegalStateException(
                        "Unknown type " + viewType + " for ListAdapter");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DataListViewModel viewModel = viewModels.get(position);
        switch (viewModel.getType()) {
            case LIST_ITEM:
                ((DataListItemViewHolder) holder).bindView((DataListItemViewModel) viewModel);
                ((DataListItemViewHolder) holder).setOnImageClickedCallback(onImageClickedCallback);
                break;
            case LIST_HEADER:
                break;
            default:
                throw new IllegalStateException(
                        "Unknown type " + viewModel.getType() + " for ListAdapter");
        }
    }

    @Override
    public int getItemViewType(int position) {
        return viewModels.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return viewModels.size();
    }

    public void setDataList(List<DataListViewModel> viewModels) {
        this.viewModels = viewModels;
    }

    public void setOnImageClickedCallback(DataListItemViewHolder.OnImageClickedCallback onImageClickedCallback){

        this.onImageClickedCallback = onImageClickedCallback;
    }

}
