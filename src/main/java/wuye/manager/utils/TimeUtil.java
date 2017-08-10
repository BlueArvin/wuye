package wuye.manager.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    private static long ONEDAY = 24*3600*1000;

    public static int getDayAdd(int day,int n) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        try{
            Date date = format.parse(String.valueOf(day));
            long a = date.getTime() + (ONEDAY*n);
            return Integer.valueOf(format.format(new Date(a)));
        }catch(Exception ex) {
            return day;
        }
    }

    public static int getMonthDec(int day) {

        int index = day % 100;
        int year = day/100;

        if(index > 1) {
            return day - 1;
        } else {
            return (year-1)*100 + 12;
        }
    }

    public static int getSeasonDec(int day) {
        int index = day % 100;
        int year = day/100;

        if(index > 1) {
            return day - 1;
        } else {
            return (year-1)*100 + 4;
        }
    }



    public static String getDayAddStr(String day,int n) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        try{
            Date date = format.parse(day);
            long a = date.getTime() + (ONEDAY*n);
            return format.format(new Date(a));
        }catch(Exception ex) {
            return day;
        }
    }
}
