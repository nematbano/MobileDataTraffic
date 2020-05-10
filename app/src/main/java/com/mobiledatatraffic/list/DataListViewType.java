package com.mobiledatatraffic.list;

import androidx.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({
        DataListViewType.LIST_HEADER,
        DataListViewType.LIST_ITEM
})

public @interface DataListViewType {
    int LIST_HEADER=1;
    int LIST_ITEM = 2;
}
