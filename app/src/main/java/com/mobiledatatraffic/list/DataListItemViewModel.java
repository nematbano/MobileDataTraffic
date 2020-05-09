package com.mobiledatatraffic.list;

import android.os.Parcel;
import android.os.Parcelable;

import com.mobiledatatraffic.model.MobileData;

import static com.mobiledatatraffic.list.DataListViewType.LIST_ITEM;

class DataListItemViewModel extends DataListViewModel implements Parcelable {

    private final String year;
    private final String volume;
    private final boolean isDecreaseInVolume;

    public DataListItemViewModel(MobileData mobileData) {
        this.year = mobileData.getYear();
        this.volume = mobileData.getVolume();
        this.isDecreaseInVolume = mobileData.isDecreaseInVolume();
    }

    protected DataListItemViewModel(Parcel in) {
        year = in.readString();
        volume = in.readString();
        isDecreaseInVolume = in.readInt()==1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(year);
        dest.writeString(volume);
        dest.writeInt(isDecreaseInVolume ? 1 : 0);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataListItemViewModel> CREATOR = new Creator<DataListItemViewModel>() {
        @Override
        public DataListItemViewModel createFromParcel(Parcel in) {
            return new DataListItemViewModel(in);
        }

        @Override
        public DataListItemViewModel[] newArray(int size) {
            return new DataListItemViewModel[size];
        }
    };

    @Override
    public int getType() {
        return LIST_ITEM;
    }

    public String getYear() {
        return year;
    }
    public String getVolume() {
        return volume;
    }
    public boolean getDecreaseInVolume(){
        return isDecreaseInVolume;
    }

}
