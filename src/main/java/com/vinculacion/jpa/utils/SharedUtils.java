package com.vinculacion.jpa.utils;

import java.util.Date;
import java.util.Random;

/**
 * Created by Mauricio on 05/06/2017.
 */
public class SharedUtils {


    public static int randomNegativeId() {
        Random rand = new Random();
        return -1 * (rand.nextInt(1000));
    }
/*
    public static long timeMark() {
        return new Date().getTime();
    }

    public static String totalTime(long lStartTime, long lEndTime) {
        long duration = lEndTime - lStartTime;
        String totalTime = String.format("Milliseconds: %d", duration);
        return totalTime;
    }
*/
}
