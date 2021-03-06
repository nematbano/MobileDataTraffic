package com.mobiledatatraffic.model;

import java.util.List;

public class MobileData {
    String volume;
    String year;
    List<String> quarterList;

    DecreaseInVolume decreaseInVolume;

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

    public DecreaseInVolume getDecreaseInVolume() {
        return decreaseInVolume;
    }

    public void setDecreaseInVolume(DecreaseInVolume decreaseInVolume) {
        this.decreaseInVolume = decreaseInVolume;
    }
}
