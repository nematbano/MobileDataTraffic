package com.mobiledatatraffic.helper;

import java.util.List;

public class VolumeCalculator {
    public String getTotalVolume(List<String> list) {
        double sum = 0;
        if (list != null) {
            try {
                for (int i = 0; i < list.size(); i++) {
                    sum += Double.parseDouble(list.get(i));
                }
            } catch (NumberFormatException exception) { }
        }
        return String.valueOf(sum);
    }
}
