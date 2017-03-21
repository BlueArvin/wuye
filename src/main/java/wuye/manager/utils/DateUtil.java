package wuye.manager.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * 
 * 日期信息处理
 */

public class DateUtil {
	public final static String defaultPattern = "yyyy-MM-dd";
	public final static String dateTimePattern = "yyyy-MM-dd HH:mm";
	private final static String dateMonthHourPattern = "M月d日HH:mm";
	public final static String dateMonthPattern = "yyyy年MM月dd日";
	public final static String dateTimeSecondPattern = "yyyy-MM-dd HH:mm:ss";
	public final static String dateTimeYNDPattern = "yyyy年MM月dd日HH点mm分";
	public final static String dateTimeYNDHMSPattern = "yyyyMMddHHmmss";
	public final static String dateYNDPattern = "yyyyMMdd";

	/**
	 * 根据传入字符串 返回yyyy年mm月dd日,月份日期为单位时显示为双位
	 */
	public static String getCurrentDateToCNWhole(String dateTime) {

		String year = dateTime.substring(0, 4);
		String month;
		String day;

		month = dateTime.substring(5, 7);
		day = dateTime.substring(8, 10);
		return year + "年" + month + "月" + day + "日";
	}
	
	 /**
     * Description: 将日期字符串转换成日期型
     *
     * @param dateStr
     * @return
     * @Version1.0 2012-11-5 上午08:50:21 
     */
    public static Date dateString2Date(String dateStr) {
        return dateString2Date(dateStr, defaultPattern);
    }
    
    /**
     * Description: 将日期字符串转换成指定格式日期
     *
     * @param dateStr
     * @param partner
     * @return
     * @Version1.0 2012-11-5 上午08:50:55 
     */
    public static Date dateString2Date(String dateStr, String partner) {
        try {
            DateFormat formatter = new SimpleDateFormat(partner);
            ParsePosition pos = new ParsePosition(0);
            return formatter.parse(dateStr, pos);
        } catch (NullPointerException e) {
            return null;
        }
    }
    
    /**
     * Description: 日期转化指定格式的字符串型日期
     *
     * @param p_utilDate java.util.Date
     * @param p_format   日期格式
     * @return 字符串格式日期
     * @Version1.0 2012-11-5 上午08:58:44 by 
     */
    public static String date2String(java.util.Date p_utilDate) {
    	return date2String(p_utilDate, defaultPattern);
    }
    
    
    /**
     * Description: 日期转化指定格式的字符串型日期
     *
     * @param p_utilDate java.util.Date
     * @param p_format   日期格式
     * @return 字符串格式日期
     * @Version1.0 2012-11-5 上午08:58:44 by 
     */
    public static String date2String(
            java.util.Date p_utilDate, String p_format) {
        String l_result = "";
        if (p_utilDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(p_format);
            l_result = sdf.format(p_utilDate);
        }
        return l_result;
    }
    
}
