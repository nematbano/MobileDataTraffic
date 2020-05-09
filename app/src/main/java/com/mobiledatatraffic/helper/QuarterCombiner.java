package com.mobiledatatraffic.helper;

import com.mobiledatatraffic.model.MobileDataResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QuarterCombiner {
    Map<String, ArrayList<String>> quarterMap = new LinkedHashMap<>();
    Map<String, String> dataMap = new LinkedHashMap<>();

    public Map<String, String> getDataMap(List<MobileDataResponse> mobileDataResponses) {

        List<String> listToExclude = Arrays.asList("2004", "2005", "2006", "2007", "2019");
        for (MobileDataResponse mobileDataResponse : mobileDataResponses) {
            String year = mobileDataResponse.getQuarter().substring(0, 4);
            if (listToExclude.contains(year)) {
                continue;
            }
            if (dataMap.get(year) == null) {
                dataMap.put(year, mobileDataResponse.getVolume());
            } else {
                String existingVolume = dataMap.get(year);
                String currentVolume = mobileDataResponse.getVolume();
                double totalVolume = Double.parseDouble(existingVolume) + Double.parseDouble(currentVolume);
                dataMap.put(year, String.valueOf(totalVolume));

            }
        }
        System.out.println(dataMap);
        return dataMap;
    }

    public Map<String, ArrayList<String>> getQuarterMap(List<MobileDataResponse> mobileDataResponses) {
        List<String> listToExclude = Arrays.asList("2004", "2005", "2006", "2007", "2019");
        for (MobileDataResponse mobileDataResponse : mobileDataResponses) {
            String year = mobileDataResponse.getQuarter().substring(0, 4);
            if (listToExclude.contains(year)) {
                continue;
            }
            if (quarterMap.get(year) == null) {
                ArrayList<String> quarters = new ArrayList<>();
                quarters.add(mobileDataResponse.getVolume());
                quarterMap.put(year, quarters);
            } else {
                ArrayList<String> existingVolume = quarterMap.get(year);
                String currentVolume = mobileDataResponse.getVolume();
                existingVolume.add(currentVolume);
                quarterMap.put(year, existingVolume);
            }
        }
        System.out.println(quarterMap);
        return quarterMap;
    }
}

