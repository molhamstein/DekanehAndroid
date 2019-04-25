package com.socket.dekaneh.utils;

import android.content.Context;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import com.socket.dekaneh.R;

/**
 * Created by molhammahmoud on 6/12/17.
 */

public class AppDateUtils {

    enum DATE_FORMAT {DATE_TIME, DATE_ONLY, TIME_ONLY}

    // date formats
    private static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm";
    private static final String FORMAT_TIME_ONLY = "HH:mm";
    private static final String FORMAT_DATE_ONLY = "yyyy/MM/dd";
    private static final String FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private static final long oneDayMillies = 24 * 60 * 60 * 1000;
    private static final long oneHourMillies = 60 * 60 * 1000;
    private static final long oneMinuteMillies = 60 * 1000;

    /**
     * @return unix time representation of the current time
     */
    public static long getTimestampNow () {
        long res = 0;
        try {
            res = Calendar.getInstance().getTimeInMillis();
        } catch (Exception ignored) {}
        return res;
    }

    public static String getFormatedDateForDisplay (long timestamp, DATE_FORMAT format) {
        String res = null;
        try {
            String strFormat = FORMAT_DATE_TIME;
            switch (format) {
                case DATE_ONLY:
                    strFormat = FORMAT_DATE_ONLY;
                    break;
                case TIME_ONLY:
                    strFormat = FORMAT_TIME_ONLY;
                    break;
                case DATE_TIME:
                    strFormat = FORMAT_DATE_TIME;
                    break;
            }

            Date date = new Date(timestamp);
            SimpleDateFormat sdf = new SimpleDateFormat(strFormat, Locale.ENGLISH);
            res = sdf.format(date);
        } catch (Exception ignored) {

        }
        return res;
    }

    // TOD need to test if its working currectly with different timezones

    /**
     * @return string text representing the time lapsed from the current time
     */
    public static String getLapsedString(Context context, Date date) {
        String result = "";
        long now = Calendar.getInstance().getTimeInMillis();
        long timeLapsed = now - date.getTime();
        int days = (int) (timeLapsed / oneDayMillies);
        int hours = (int) (timeLapsed / oneHourMillies);//(int) (timeLapsed - (long)(days * oneDayMillies))  ;
        int minutes = (int) (timeLapsed / oneMinuteMillies);
        if (minutes == 0) {
            result = context.getString(R.string.DATE_fiew_seconds_ago);
        } else if (hours == 0) {
            result = context.getString(R.string.DATE_fiew_minutes_ago , minutes);
        } else if (days == 0) {
            result = context.getString(R.string.DATE_fiew_hours_ago , hours);
        } else if (days == 1) {
            result = context.getString(R.string.DATE_FORMATE_YESTERDAY);
        } else if (days <= 30) {
            result = context.getString(R.string.DATE_fiew_days_ago , days);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_ONLY);
            result = sdf.format(date);
        }
        return result;
    }

    /**
     * @return unix time representation of the UTC date
     */
    public static long parseUtcDate(String receivedDate){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_UTC , Locale.getDefault());
            return sdf.parse(receivedDate).getTime();
        }catch (Exception ignored){}
        return  0;
    }

    /**
     * @return ISO formated date string
     */
    public static String gettUtcTimeString(Date date){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_UTC , Locale.ENGLISH);
            return sdf.format(date);
        }catch (Exception ignored){}
        return  "";
    }

    public static long getCurrentTimezoneOffset() {
        TimeZone tz = TimeZone.getDefault();
        Date now = new Date();
        int offsetFromUtc = tz.getOffset(now.getTime());
        long offset = TimeUnit.HOURS.convert(offsetFromUtc, TimeUnit.MILLISECONDS);
        return offset;
    }

    public static String dateToString(Date date) {
        DateFormat format = new SimpleDateFormat(FORMAT_DATE_ONLY);
        return format.format(date);
    }
}
