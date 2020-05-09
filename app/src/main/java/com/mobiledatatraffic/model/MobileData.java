package com.mobiledatatraffic.model;

import java.util.List;

public class MobileData {
    String volume;
    String year;
    List<String> quarterList;
    boolean isDecreaseInVolume;

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<String> getQuarterList() {
        return quarterList;
    }

    public void setQuarterList(List<String> quarterList) {
        this.quarterList = quarterList;
    }

    public boolean isDecreaseInVolume() {
        return isDecreaseInVolume;
    }

    public void setDecreaseInVolume(boolean decreaseInVolume) {
        isDecreaseInVolume = decreaseInVolume;
    }
}
