package com.mobiledatatraffic.helper;

import com.mobiledatatraffic.model.MobileDataResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonConverter {
    public List<MobileDataResponse> convert(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                jsonObject = jsonObject.getJSONObject("result");
                if(jsonObject!= null) {
                    return convert(jsonObject.getJSONArray("records"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private List<MobileDataResponse> convert(JSONArray jArr) {
        List<MobileDataResponse> list = null;
        if (jArr != null) {
            list = new ArrayList<>();
            try {
                for (int i = 0, l = jArr.length(); i < l; i++) {
                    MobileDataResponse mobileData = new MobileDataResponse();
                    JSONObject jsonObject = jArr.optJSONObject(i);
                    Object volumeOfMobileData = jsonObject.get("volume_of_mobile_data");
                    Object quarter = jsonObject.get("quarter");
                    if((volumeOfMobileData!=null) && (quarter!=null)) {
                        mobileData.setVolume(volumeOfMobileData.toString());
                        mobileData.setQuarter(quarter.toString());
                        list.add(mobileData);
                    }
                }
            } catch (JSONException e) {
            }
        }
        return list;
    }
}
