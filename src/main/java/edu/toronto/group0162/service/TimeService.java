package edu.toronto.group0162.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static java.lang.Math.abs;

/**
 * Created by GGG on 2018/8/4.
 */
public class TimeService {



    public long getHourDifference(String time1, String time2) {
        // Set new simple date format that input time should stick to.
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Set the time zone of our format as "EDT".
        //format.setTimeZone(TimeZone.getTimeZone("Eastern Daylight Time"));
        // Parse time1 and time2 to simple date format.
        Date d1 = null;
        try {
            d1 = format.parse(time1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d2 = null;
        try {
            d2 = format.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Return the time difference rounded down closest hour.
        return abs((d2.getTime() - d1.getTime()) / (1000 * 60 * 60));
    }

    public double getHourDifferenceWithoutAbs(String time1, String time2) {
        // Set new simple date format that input time should stick to.
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Set the time zone of our format as "EDT".
        //format.setTimeZone(TimeZone.getTimeZone("Eastern Daylight Time"));
        // Parse time1 and time2 to simple date format.
        Date d1 = null;
        try {
            d1 = format.parse(time1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d2 = null;
        try {
            d2 = format.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Return the time difference rounded down closest hour.
        return (d2.getTime() - d1.getTime() / (1000 * 60 * 60));
    }


}
