package com.mobiledatatraffic.helper;

import com.mobiledatatraffic.model.MobileDataResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static java.util.Collections.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuarterCombinerTest {

    @InjectMocks
    private QuarterCombiner subject;

    @Mock
    private MobileDataResponse mobileDataResponse;

    @Test
    public void getQuarterMap_givenMobileDataResponseNull_returnsEmptyMap() {
        Map result = subject.getQuarterMap(null, EMPTY_LIST);
        assertTrue(result.isEmpty());
    }

    @Test
    public void getQuarterMap_givenListToExcludeNull_returnsEmptyMap() {
        Map result = subject.getQuarterMap(EMPTY_LIST, null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void getQuarterMap_givenMobileDataResponseEmpty_returnsEmptyMap() {
        Map quarterMap = subject.getQuarterMap(EMPTY_LIST, Collections.singletonList("year"));
        assertTrue(quarterMap.isEmpty());
    }

    @Test
    public void getQuarterMap_givenListToExcludeEmpty_returnsNonEmptyMap() {
        when(mobileDataResponse.getQuarter()).thenReturn("year");
        Map quarterMap = subject.getQuarterMap(Collections.singletonList(mobileDataResponse), EMPTY_LIST);
        assertFalse(quarterMap.isEmpty());
    }

    @Test
    public void getQuarterMap_givenMobileDataResponseWithInvalidYear_returnsEmptyMap() {
        when(mobileDataResponse.getQuarter()).thenReturn("yea");
        Map quarterMap = subject.getQuarterMap(Collections.singletonList(mobileDataResponse), EMPTY_LIST);
        assertTrue(quarterMap.isEmpty());
    }

    @Test
    public void getQuarterMap_givenMobileDataResponseWithValidYear_givenListToExcludeWithSameYear_returnsEmptyMap() {
        when(mobileDataResponse.getQuarter()).thenReturn("year");
        Map quarterMap = subject.getQuarterMap(Collections.singletonList(mobileDataResponse), Collections.singletonList("year"));
        assertTrue(quarterMap.isEmpty());
    }

    @Test
    public void getQuarterMap_givenMobileDataResponseWithValidYear_givenListToExcludeWithDifferentYear_returnsNonEmptyMap() {
        when(mobileDataResponse.getQuarter()).thenReturn("year");
        Map quarterMap = subject.getQuarterMap(Collections.singletonList(mobileDataResponse), Collections.singletonList("year1"));
        assertFalse(quarterMap.isEmpty());
    }

    @Test
    public void getQuarterMAp_givenMobileDataResponseWithNullVolume_givenListToExcludeWithDifferentYear_returnsMapWithNullVolume() {
        when(mobileDataResponse.getQuarter()).thenReturn("year");
        when(mobileDataResponse.getVolume()).thenReturn(null);
        Map<String, ArrayList<String>> quarterMap = subject.getQuarterMap(Collections.singletonList(mobileDataResponse), Collections.singletonList("year1"));
        assertEquals(1, quarterMap.size());
        assertNull(quarterMap.get("year").get(0));
    }

    @Test
    public void getQuarterMAp_givenTwoMobileDataResponsesWithSameYearAndDifferentVolume_givenListToExcludeWithDifferentYear_returnsMapWithVolumeList() {
        when(mobileDataResponse.getQuarter()).thenReturn("year");
        when(mobileDataResponse.getVolume()).thenReturn("volume1");

        MobileDataResponse mobileDataResponse1 = mock(MobileDataResponse.class);
        when(mobileDataResponse1.getQuarter()).thenReturn("year");
        when(mobileDataResponse1.getVolume()).thenReturn("volume2");
        Map<String, ArrayList<String>> quarterMap = subject.getQuarterMap(Arrays.asList(mobileDataResponse,mobileDataResponse1), Collections.singletonList("year1"));
        assertEquals(1, quarterMap.size());
        assertEquals(2,quarterMap.get("year").size());
        assertEquals("volume1",quarterMap.get("year").get(0));
        assertEquals("volume2",quarterMap.get("year").get(1));
    }

    @Test
    public void getQuarterMAp_givenTwoMobileDataResponsesWithDifferentYearAndDifferentVolume_givenListToExcludeWithDifferentYear_returnsMapWithVolumeList() {
        when(mobileDataResponse.getQuarter()).thenReturn("yea1");
        when(mobileDataResponse.getVolume()).thenReturn("volume1");

        MobileDataResponse mobileDataResponse1 = mock(MobileDataResponse.class);
        when(mobileDataResponse1.getQuarter()).thenReturn("yea2");
        when(mobileDataResponse1.getVolume()).thenReturn("volume2");
        Map<String, ArrayList<String>> quarterMap = subject.getQuarterMap(Arrays.asList(mobileDataResponse,mobileDataResponse1), Collections.singletonList("year"));
        assertEquals(2, quarterMap.size());
        assertEquals(1,quarterMap.get("yea1").size());
        assertEquals(1,quarterMap.get("yea2").size());
        assertEquals("volume1",quarterMap.get("yea1").get(0));
        assertEquals("volume2",quarterMap.get("yea2").get(0));
    }
}