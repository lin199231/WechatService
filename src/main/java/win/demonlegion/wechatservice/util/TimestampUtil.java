package win.demonlegion.wechatservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by john on 2017/6/19.
 * 获取格式化时间的工具类
 */
public class TimestampUtil implements Serializable {
    private static final long serialVersionUID = 5836254631785464360L;
    private static final Logger logger = LoggerFactory.getLogger(TimestampUtil.class);

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat simpleDateYearFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat simpleDateHouseFormat = new SimpleDateFormat("HH:mm");

    public static Date getDatetime() {
        return getCalendar().getTime();
    }

    public static Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"), Locale.SIMPLIFIED_CHINESE);
        // 设置周一为一星期的第一天, 默认为周日
        calendar.setFirstDayOfWeek(Calendar.MONDAY);

        return calendar;
    }

    // 返回可以增加秒数的Date
    public static Date getDatetime( int second ) {
        Calendar calendar = getCalendar();
        calendar.add(Calendar.SECOND, second);

        return calendar.getTime();
    }

    // 返回指定日期增加秒数的Date
    public static Date getDatetime( Date date, int second ) {
        Calendar calendar = getCalendar();
        if (date != null) calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);

        return calendar.getTime();
    }

    // 返回指定日期增加指定域数值的Date
    public static Date getDatetime( Date date, int field, int value ) {
        Calendar calendar = getCalendar();
        if (date != null) calendar.setTime(date);
        calendar.add(field, value);

        return calendar.getTime();
    }

    public static String getDateYearFormat( Date date ) {
        return simpleDateYearFormat.format(date);
    }

    public static String getDateHouseFormat( Date date ) {
        return simpleDateHouseFormat.format(date);
    }

    // 返回指定日期的Date
    public static Date getDatetime( int year, int month, int day, int hour, int minute, int second ) {
        Calendar calendar = getCalendar();
        if (year > 0) calendar.set(Calendar.YEAR, year);
        month -= 1; // 日历的月份从0开始计算
        if (month > 0) calendar.set(Calendar.MONTH, month);
        if (day > 0) calendar.set(Calendar.DAY_OF_MONTH, day);
        if (hour >= 0) calendar.set(Calendar.HOUR_OF_DAY, hour);
        if (minute >= 0) calendar.set(Calendar.MINUTE, minute);
        if (second >= 0) calendar.set(Calendar.SECOND, second);

        return calendar.getTime();
    }

    public static String getDateDay( int day ) {
        Calendar calendar = getCalendar();
        calendar.add(Calendar.DAY_OF_WEEK, day);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getDateMouth( int mouth ) {
        Calendar calendar = getCalendar();
        calendar.add(Calendar.MONTH, mouth);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getDateYear( int year ) {
        Calendar calendar = getCalendar();
        calendar.add(Calendar.YEAR, year);
        return simpleDateFormat.format(calendar.getTime());
    }

    // 返回今天零点的Date
    public static Date getZeroDatetime() {
        Calendar calendar = getCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    // 返回传入date所在的周一日期
    public static Date getMondayDatetime( Date date ) {
        return getMondayDatetime(date, 0);
    }

    // 返回传入date所在的周一日期
    public static Date getMondayDatetime( Date date, int offset ) {
        Calendar calendar = getCalendar();
        if (date != null) calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.WEEK_OF_YEAR, calendar.get(Calendar.WEEK_OF_YEAR) + offset);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return calendar.getTime();
    }

    public static int getDifferenceSecond( Date date, Date anotherDate ) {
        return (int) (Math.abs(date.getTime() - anotherDate.getTime()) / 1000);
    }

    public static int getDifferenceSecond( Calendar calendar, Calendar anotherCalendar ) {
        return getDifferenceSecond(calendar.getTime(), anotherCalendar.getTime());
    }

    public static String getDatetimeString() {
        return simpleDateFormat.format(getDatetime());
    }

    public static String getDatetimeString( String format ) {
        return new SimpleDateFormat(format).format(getDatetime());
    }

    public static String formatDatetime( Date date, String format ) {
        return new SimpleDateFormat(format).format(date);
    }

    public static String formatDatetime( Date date ) {
        return simpleDateFormat.format(date);
    }

    public static String formatDatetime( Calendar calendar, String format ) {
        return formatDatetime(calendar.getTime(), format);
    }

    public static String formatDatetime( Calendar calendar ) {
        return formatDatetime(calendar.getTime());
    }

    public static String formatDatetime() {
        return formatDatetime(getDatetime());
    }

    public static Date parseDatetime( String datetime, String format ) {
        try {
            return new SimpleDateFormat(format).parse(datetime);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static Date parseDatetime( String datetime ) {
        try {
            return simpleDateFormat.parse(datetime);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static Date parseDateYearTime( String datetime ) {
        try {
            return simpleDateYearFormat.parse(datetime);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public static Set<String> dateToWeek( Date date ) {
        Set<String> stringSet = new TreeSet<>();
        Date date1 = getMondayDatetime(date);
        Calendar calendar = getCalendar();
        if (date1 != null) calendar.setTime(date1);
        for (int i = 0; i < 7; i++) {
            stringSet.add(simpleDateYearFormat.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_WEEK, 1);
        }
        return stringSet;
    }

    public static Date getTypeDate( Date date, int type, int num ) {
        Calendar calendar = getCalendar();
        if (date != null) calendar.setTime(date);
        if (type == 3) calendar.add(Calendar.DAY_OF_WEEK, num);
        if (type == 2) calendar.add(Calendar.MONTH, num);
        if (type == 1) calendar.add(Calendar.YEAR, num);
        return calendar.getTime();
    }
    public static boolean isValidDate(String str) {
        boolean convertSuccess=true;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess=false;
        }
        return convertSuccess;
    }

}
