package com.mobiledatatraffic.helper;

import com.mobiledatatraffic.model.MobileData;
import com.mobiledatatraffic.model.MobileDataResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MobileDataResponseMapper {
    private QuarterCombiner quarterCombiner;
    private DecreaseInVolumeVerifier decreaseInVolumeVerifier;
    private VolumeCalculator volumeCalculator;

    public MobileDataResponseMapper(QuarterCombiner quarterCombiner, DecreaseInVolumeVerifier decreaseInVolumeVerifier, VolumeCalculator volumeCalculator) {
        this.quarterCombiner = quarterCombiner;
        this.decreaseInVolumeVerifier = decreaseInVolumeVerifier;
        this.volumeCalculator = volumeCalculator;
    }

    public List<MobileData> mapResponse(List<MobileDataResponse> mobileDataResponses,List<String> listToExclude) {
        List<MobileData> list = new ArrayList<>();
        Map<String, ArrayList<String>> quarterMap = quarterCombiner.getQuarterMap(mobileDataResponses,listToExclude);
        for (Map.Entry<String,  ArrayList<String>> entry : quarterMap.entrySet()) {
            MobileData mobileData = new MobileData();
            String year = entry.getKey();
            List<String> value = entry.getValue();
            mobileData.setYear(year);
            mobileData.setVolume(volumeCalculator.getTotalVolume(value));
            mobileData.setQuarterList(quarterMap.get(year));
            mobileData.setDecreaseInVolume(decreaseInVolumeVerifier.verify(quarterMap, year));
            list.add(mobileData);
        }
        return list;
    }
}
