package wuye.manager.utils;

import java.text.SimpleDateFormat;
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
