package com.panietoar.callcenter;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    private Utils() {

    }

    static long newId = 0;

    public static long getNewId() {
        return newId++;
    }

    public static long getCallDuration() {
        return ThreadLocalRandom.current().nextInt(5000, 10001);
    }

}
