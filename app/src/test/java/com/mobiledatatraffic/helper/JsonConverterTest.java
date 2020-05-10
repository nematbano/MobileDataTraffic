package com.mobiledatatraffic.helper;

import com.mobiledatatraffic.model.MobileDataResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JsonConverterTest {

    @InjectMocks
    JsonConverter subject;
    @Mock
    JSONObject jsonObject;
    @Mock
    JSONObject internalJsonObject;
    @Mock
    JSONArray jArr;
    @Mock
    JSONObject optedJsonObject;

    @Test
    public void convert_givenJsonObjectNull_returnsNull() {
        List<MobileDataResponse> result = subject.convert(null);
        assertNull(result);
    }

    @Test
    public void convert_givenJsonObjectDoesNotContainResult_returnsNull() throws Exception {
        List<MobileDataResponse> result = subject.convert(jsonObject);
        when(jsonObject.getJSONObject("result")).thenReturn(null);
        assertNull(result);
    }

    @Test
    public void convert_givenJsonObjectDoesNotContainRecords_returnsNull() throws Exception {
        when(jsonObject.getJSONObject("result")).thenReturn(internalJsonObject);
        when(internalJsonObject.getJSONArray("records")).thenReturn(null);
        List<MobileDataResponse> result = subject.convert(jsonObject);

        assertNull(result);
    }

    @Test
    public void convert_givenJsonArrayDoesNotContainVolumeOfMobileData_returnsEmptyList() throws Exception {
       when(jsonObject.getJSONObject("result")).thenReturn(internalJsonObject);
        when(internalJsonObject.getJSONArray("records")).thenReturn(jArr);
        when(jArr.length()).thenReturn(1);
        when(jArr.optJSONObject(0)).thenReturn(optedJsonObject);
        when(optedJsonObject.get("volume_of_mobile_data")).thenReturn(null);

        List<MobileDataResponse> result = subject.convert(jsonObject);
        assertEquals(0,result.size());
    }

    @Test
    public void convert_givenJsonArrayDoesNotContainQuarter_returnsEmptyList() throws Exception {
        when(jsonObject.getJSONObject("result")).thenReturn(internalJsonObject);
        when(internalJsonObject.getJSONArray("records")).thenReturn(jArr);
        when(jArr.length()).thenReturn(1);
        when(jArr.optJSONObject(0)).thenReturn(optedJsonObject);
        when(optedJsonObject.get("volume_of_mobile_data")).thenReturn("Volume");
        when(optedJsonObject.get("quarter")).thenReturn(null);

        List<MobileDataResponse> result = subject.convert(jsonObject);
        assertEquals(0,result.size());
    }

    @Test
    public void convert_givenJsonArray_returnsMobileDataResponseList() throws Exception {
        when(jsonObject.getJSONObject("result")).thenReturn(internalJsonObject);
        when(internalJsonObject.getJSONArray("records")).thenReturn(jArr);
        when(jArr.length()).thenReturn(1);
        when(jArr.optJSONObject(0)).thenReturn(optedJsonObject);
        when(optedJsonObject.get("volume_of_mobile_data")).thenReturn("Volume");
        when(optedJsonObject.get("quarter")).thenReturn("Quarter");

        List<MobileDataResponse> result = subject.convert(jsonObject);
        assertEquals("Volume",result.get(0).getVolume());
        assertEquals("Quarter",result.get(0).getQuarter());
    }
}