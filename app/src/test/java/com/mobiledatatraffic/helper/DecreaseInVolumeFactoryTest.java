package com.mobiledatatraffic.helper;

import com.mobiledatatraffic.model.DecreaseInVolume;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DecreaseInVolumeFactoryTest {

    @InjectMocks
    private DecreaseInVolumeFactory subject;

    @Test
    public void get_givenNullQuarterMap_returnsNull() {
        DecreaseInVolume result= subject.get(null,"year");
        assertNull(result);
    }

    @Test
    public void get_givenNullYear_returnsNull() {
        DecreaseInVolume result= subject.get(Collections.EMPTY_MAP,null);
        assertNull(result);
    }

    @Test
    public void get_givenMapDoesNotContainYearAsKey_returnsNull() {
        DecreaseInVolume result= subject.get(Collections.singletonMap("key", new ArrayList<String>()),"year");
        assertNull(result);
    }

    @Test
    public void get_givenMapContainsEmptyQuarterList_returnsNull() {
        DecreaseInVolume result= subject.get(Collections.singletonMap("year", new ArrayList<String>()),"year");
        assertNull(result);
    }

    @Test
    public void get_givenMapContainsQuarterListWithOneItem_returnsNull() {
        DecreaseInVolume result= subject.get(Collections.singletonMap("year", new ArrayList<String>(Collections.singletonList("Q1"))),"year");
        assertNull(result);
    }

    @Test
    public void get_givenMapContainsQuarterListWithInvalidItems_returnsNull() {
        DecreaseInVolume result= subject.get(Collections.singletonMap("year", new ArrayList<String>(Arrays.asList("Q1","Q2"))),"year");
        assertNull(result);
    }

    @Test
    public void get_givenMapContainsQuarterListWithValidItemsWithIncreasingValues_returnsNull() {
        DecreaseInVolume result= subject.get(Collections.singletonMap("year", new ArrayList<String>(Arrays.asList("0.1","0.2"))),"year");
        assertNull(result);
    }

    @Test
    public void get_givenMapContainsQuarterListWithValidItemsWithDecreasingValues_returnsNull() {
        DecreaseInVolume result= subject.get(Collections.singletonMap("year", new ArrayList<String>(Arrays.asList("0.2","0.1"))),"year");
        assertEquals("0.2",result.getFrom());
        assertEquals("0.1",result.getTo());
        assertEquals(2,result.getQuarter());
    }
}