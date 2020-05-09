package com.mobiledatatraffic.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DecreaseInVolumeVerifier {

    public boolean verify(Map<String, ArrayList<String>> quarterMap, String year) {
        Iterator<Map.Entry<String, ArrayList<String>>> itr = quarterMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, ArrayList<String>> entry = itr.next();
            String key = entry.getKey();
            if (key.equals(year)) {
                List<String> list = entry.getValue();
                for (int i = 0; i < 3; i++) {
                    if (Double.parseDouble(list.get(i)) > Double.parseDouble(list.get(i + 1))) {
                        return true;
                    }
                }
            }

        }
        return false;
    }
}
