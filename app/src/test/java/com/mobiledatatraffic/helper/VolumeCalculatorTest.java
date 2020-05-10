package com.mobiledatatraffic.helper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class VolumeCalculatorTest {

    @InjectMocks
    private VolumeCalculator subject;

    @Test
    public void getTotalVolume_givenInputListNull_returnsZero() {
        String result = subject.getTotalVolume(null);
        Assert.assertEquals("0.0", result);
    }

    @Test
    public void getTotalVolume_givenInputListEmpty_returnsZero() {
        String result = subject.getTotalVolume(Collections.<String>emptyList());
        Assert.assertEquals("0.0", result);
    }

    @Test
    public void getTotalVolume_givenInputListSizeOne_returnsSameValue() {
        String result = subject.getTotalVolume(Collections.singletonList("0.1"));
        Assert.assertEquals("0.1", result);
    }

    @Test
    public void getTotalVolume_givenInputListItemNotDouble_returnsZero() {
        String result = subject.getTotalVolume(Collections.singletonList("item"));
        Assert.assertEquals("0.0", result);
    }
}