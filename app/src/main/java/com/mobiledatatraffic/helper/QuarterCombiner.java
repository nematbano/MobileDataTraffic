package com.mobiledatatraffic.helper;

import com.mobiledatatraffic.model.MobileDataResponse;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QuarterCombiner {
    public Map<String, ArrayList<String>> getQuarterMap(List<MobileDataResponse> mobileDataResponses, List<String> listToExclude) {
        Map<String, ArrayList<String>> quarterMap = new LinkedHashMap<>();
        if (mobileDataResponses != null && listToExclude != null) {
            for (MobileDataResponse mobileDataResponse : mobileDataResponses) {
                String quarter = mobileDataResponse.getQuarter();
                if (quarter.length() > 3) {
                    String year = quarter.substring(0, 4);
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
            }
        }
        return quarterMap;
    }
}

