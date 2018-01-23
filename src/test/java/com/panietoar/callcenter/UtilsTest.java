package com.panietoar.callcenter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UtilsTest {

    @Test
    public void shouldGetIncrementalIds() {
        long expectedResult = 3;

        Utils.getNewId(); //0
        Utils.getNewId(); //1
        Utils.getNewId(); //2

        long actualResult = Utils.getNewId(); //3

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldReturnDurationInRange() {
        long minDuration = 5000;
        long maxDuration = 10000;

        long actualDuration = Utils.getCallDuration();

        assertTrue(minDuration <= actualDuration && actualDuration <= maxDuration);

    }

}
