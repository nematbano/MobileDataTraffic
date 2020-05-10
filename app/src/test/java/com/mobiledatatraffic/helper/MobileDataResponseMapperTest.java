package com.mobiledatatraffic.helper;

import com.mobiledatatraffic.model.DecreaseInVolume;
import com.mobiledatatraffic.model.MobileData;
import com.mobiledatatraffic.model.MobileDataResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Collections.EMPTY_LIST;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MobileDataResponseMapperTest {

    @InjectMocks
    MobileDataResponseMapper subject;

    @Mock
    private MobileDataResponse mobileDataResponse;
    @Mock
    private DecreaseInVolume decreaseInVolume;

    @Mock
    private QuarterCombiner quarterCombiner;
    @Mock
    private VolumeCalculator volumeCalculator;
    @Mock
    private DecreaseInVolumeFactory decreaseInVolumeFactory;

    @Test
    public void mapResponse_givenQuarterMapNull_returnsEmptyList() {
        when(quarterCombiner.getQuarterMap(Collections.singletonList(mobileDataResponse), EMPTY_LIST)).thenReturn(null);
        List result = subject.mapResponse(Collections.singletonList(mobileDataResponse), EMPTY_LIST);
        assertEquals(0,result.size());
    }

    @Test
    public void mapResponse_givenQuarterMap_returnsList() {
        ArrayList<String> list = new ArrayList<>(Collections.singletonList("Q1"));
        Map<String, ArrayList<String>> quarterMap = Collections.singletonMap("year", list);
        when(quarterCombiner.getQuarterMap(Collections.singletonList(mobileDataResponse), EMPTY_LIST)).thenReturn(quarterMap);
        when(volumeCalculator.getTotalVolume(list)).thenReturn("volume");

        when(decreaseInVolume.getFrom()).thenReturn("from");
        when(decreaseInVolume.getTo()).thenReturn("to");
        when(decreaseInVolume.getQuarter()).thenReturn(1);
        when(decreaseInVolumeFactory.get(quarterMap,"year")).thenReturn(decreaseInVolume);

        List result = subject.mapResponse(Collections.singletonList(mobileDataResponse), EMPTY_LIST);
        assertEquals("year",((MobileData)result.get(0)).getYear());
        assertEquals("volume",((MobileData)result.get(0)).getVolume());
        assertEquals("Q1",((MobileData)result.get(0)).getQuarterList().get(0));
        assertEquals("from",((MobileData)result.get(0)).getDecreaseInVolume().getFrom());
        assertEquals("to",((MobileData)result.get(0)).getDecreaseInVolume().getTo());
        assertEquals(1,((MobileData)result.get(0)).getDecreaseInVolume().getQuarter());
    }
}