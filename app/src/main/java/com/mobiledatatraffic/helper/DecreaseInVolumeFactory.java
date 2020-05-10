package com.mobiledatatraffic.helper;

import com.mobiledatatraffic.model.DecreaseInVolume;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DecreaseInVolumeFactory {

    public DecreaseInVolume get(Map<String, ArrayList<String>> quarterMap, String year) {
        if (quarterMap != null) {
            Iterator<Map.Entry<String, ArrayList<String>>> itr = quarterMap.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry<String, ArrayList<String>> entry = itr.next();
                String key = entry.getKey();
                if (key.equals(year)) {
                    List<String> list = entry.getValue();
                    for (int i = 0; i < list.size() - 1; i++) {
                        try {
                            if (Double.parseDouble(list.get(i)) > Double.parseDouble(list.get(i + 1))) {
                                DecreaseInVolume decreaseInVolume = new DecreaseInVolume();
                                decreaseInVolume.setFrom(list.get(i));
                                decreaseInVolume.setTo(list.get(i + 1));
                                decreaseInVolume.setQuarter(i + 2);
                                return decreaseInVolume;
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }
        }
        return null;
    }
}
