package com.cxzgwing.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Print time-consuming(eg: 1h23m20s50ms). <br/>
 * -step1: addStartTime() <br/>
 * -step2: addEndTime() <br/>
 * -step3: print() <br/>
 * Steps 1 to 3 are a set of operations that can be looped. Calling addStartTime() and addEndTime()
 * will print the current time in the format of "yyyy-MM-dd HH:mm:ss.S". The method addStartTime()
 * and addEndTime() must match each other, otherwise output NULL.
 */
public class TimeConsume {
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    private final List<Long> startTime;
    private final List<Long> endTime;
    private int point;

    public TimeConsume() {
        this.startTime = new ArrayList<>();
        this.endTime = new ArrayList<>();
        this.point = 0;
    }

    /***
     * Print current time(simple date format is "yyyy-MM-dd HH:mm:ss.S") and add current time to
     * startTime.
     */
    public void addStartTime() {
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("[start time] " + sdf.format(currentTimeMillis));
        this.startTime.add(currentTimeMillis);
    }

    /***
     * Print current time(simple date format is "yyyy-MM-dd HH:mm:ss.S") and add current time to
     * endTime.
     */
    public void addEndTime() {
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("[end time] " + sdf.format(currentTimeMillis));
        this.endTime.add(currentTimeMillis);
    }

    /***
     * Print time-consuming(eg: 1h23m20s50ms). If the start time and end time do not match each
     * other, output empty. This method can be reused.
     */
    public void print() {
        try {
            System.out.println("[time-consuming] "
                    + convert(this.endTime.get(point) - this.startTime.get(point)));
        } catch (Exception e) {
            System.out.println("[time-consuming] NULL");
        }
        this.point++;
    }

    /***
     * timestamp convert to time-consuming
     * 
     * @param timestamp long timestamp
     * @return time-consuming string, eg: 1d, 12h32m45s123ms, 1h23m20s50ms, 5min2s, 10s, 520ms
     */
    public String convert(long timestamp) {
        if (timestamp <= 0) {
            return "0s";
        }

        long oneSecond = 1000;
        long oneMinute = 60 * oneSecond;
        long oneHour = 60 * oneMinute;
        long oneDay = 24 * oneHour;

        int d = 0;
        int h = 0;
        int m = 0;
        int s = 0;

        String result = "";
        if (timestamp >= oneDay) {
            d = (int) (timestamp / oneDay);
            result += d + "d";
        }
        timestamp -= d * oneDay;

        if (timestamp >= oneHour) {
            h = (int) (timestamp / oneHour);
            result += h + "h";
        }
        timestamp -= h * oneHour;

        if (timestamp >= oneMinute) {
            m = (int) (timestamp / oneMinute);
            result += m + "m";
        }
        timestamp -= m * oneMinute;

        if (timestamp >= oneSecond) {
            s = (int) (timestamp / oneSecond);
            result += s + "s";
        }
        timestamp -= s * oneSecond;

        if (timestamp > 0) {
            result += timestamp + "ms";
        }

        return result;
    }
}
