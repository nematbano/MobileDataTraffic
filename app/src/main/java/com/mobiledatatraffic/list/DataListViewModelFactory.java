package com.mobiledatatraffic.list;

import com.mobiledatatraffic.model.MobileData;

import java.util.ArrayList;
import java.util.List;

public class DataListViewModelFactory {
    public List<DataListViewModel> generateViewModels(List<MobileData> dataList) {

        if (dataList == null || dataList.isEmpty()) {
            return new ArrayList<>();
        }
        List<DataListViewModel> viewModels = new ArrayList<>();
        viewModels.add(new DataListHeaderViewModel());
        for (int i = 0; i < dataList.size(); i++) {
            viewModels.add(new DataListItemViewModel(dataList.get(i)));
        }

        return viewModels;
    }
}
