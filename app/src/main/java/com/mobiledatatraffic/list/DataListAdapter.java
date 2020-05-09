package com.mobiledatatraffic.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.mobiledatatraffic.R;

import java.util.List;

import static com.mobiledatatraffic.list.DataListViewType.*;

public class DataListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<DataListViewModel> viewModels;

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

            case LIST_SEPARATOR:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.view_data_list_separator, parent, false);
                return new DataListStaticViewHolder(view);

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
                break;
            case LIST_HEADER:
            case LIST_SEPARATOR:
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

}
