package com.mobiledatatraffic.helper;

import com.mobiledatatraffic.model.MobileData;
import com.mobiledatatraffic.model.MobileDataResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MobileDataResponseMapper {
    private QuarterCombiner quarterCombiner;
    private DecreaseInVolumeVerifier decreaseInVolumeVerifier;

    public MobileDataResponseMapper(QuarterCombiner quarterCombiner, DecreaseInVolumeVerifier decreaseInVolumeVerifier) {
        this.quarterCombiner = quarterCombiner;
        this.decreaseInVolumeVerifier = decreaseInVolumeVerifier;
    }

    public List<MobileData> mapResponse(List<MobileDataResponse> mobileDataResponses) {
        List<MobileData> list = new ArrayList<>();
        Map<String, String> map = quarterCombiner.getDataMap(mobileDataResponses);
        Map<String, ArrayList<String>> quarterMap = quarterCombiner.getQuarterMap(mobileDataResponses);
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            MobileData mobileData = new MobileData();
            String year = stringStringEntry.getKey();
            String value = stringStringEntry.getValue();
            mobileData.setYear(year);
            mobileData.setVolume(value);
            mobileData.setQuarterList(quarterMap.get(year));
            mobileData.setDecreaseInVolume(decreaseInVolumeVerifier.verify(quarterMap, year));
            list.add(mobileData);
        }
        return list;
    }
}
