package com.itplayer.core.base.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by caijun.yang on 2017/7/18.
 */
public class DateUtils {

    private DateUtils() {
    }

    public static final String YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static final String FULL_DATE = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_DATE = "HH:mm:ss";
    public static final String YEAR_MONTH = "yyyy-MM";
    /**
     * 比第一个时间小
     */
    public static final int DATE_BEFORE_MINDATE = 1;
    /**
     * 小于等于开始时间
     */
    public static final int DATE_BEFOREANDEQ_MINDATE = 2;
    /**
     * 比最大的时间大
     */
    public static final int DATE_AFTER_MAXDATE = 3;
    /**
     * 大于等于最大时间
     */
    public static final int DATE_AFTERANDEQ_MAXDATE = 4;

    /**
     * 介于两者之间
     */
    public static final int DATE_IN_MINANDMAX = 5;
    /**
     * 大于等于最小时间，小于等于最大时间
     */
    public static final int DATE_INANDEQ_MINANDMAX = 6;

    /**
     * String到日期
     */
    public static Date strToDate(String str, String dateType) throws Exception {
        DateFormat format = new SimpleDateFormat(dateType);
        Date date = format.parse(str);
        return date;
    }

    /**
     * 日期到String
     */
    public static String dateToStr(Date date, String dateType) {
        String str = "";
        DateFormat format = new SimpleDateFormat(dateType);
        str = format.format(date);
        return str;
    }

    /**
     * 日期前后加减
     *
     * @param date
     * @param num
     * @return
     */
    public static Date calculateDate(Date date, int num) {
        long time = date.getTime() + num * 24 * 60 * 60 * 1000;
        Date d = new Date(time);
        return d;
    }

    /**
     * 比较两个日期相差的天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int compareDate(Date startDate, Date endDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(startDate);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(endDate);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
        return day2 - day1;
    }

    /**
     * 判断两个日期是否是同一天
     *
     * @param firstDate
     * @param nextDate
     * @return
     */

    public static boolean isSameDay(Date firstDate, Date nextDate) {
        int deff = compareDate(firstDate, nextDate);
        if (0 == deff) {
            return true;
        }
        return false;
    }

    /**
     * 比较指定时间在摸个区间时间的情况
     *
     * @param minDate 区间的小时间
     * @param maxDate 区间的大事件
     * @param date    需要检测的时间
     * @return
     */
    public static boolean compareDate(Date minDate, Date maxDate, Date date, int searchStatus) {
        if (null == date) {
            return false;
        }
        boolean flag = false;
        switch (searchStatus) {
            case DATE_BEFORE_MINDATE:
                flag = date.before(minDate);
                break;
            case DATE_BEFOREANDEQ_MINDATE:
                flag = date.before(minDate) && date.equals(minDate);
                break;
            case DATE_AFTER_MAXDATE:
                flag = date.after(maxDate);
                break;
            case DATE_AFTERANDEQ_MAXDATE:
                flag = date.after(maxDate) && date.equals(maxDate);
                break;
            case DATE_IN_MINANDMAX:
                flag = date.after(minDate) && date.before(maxDate);
                break;
            case DATE_INANDEQ_MINANDMAX:
                flag = date.after(minDate) && date.equals(minDate) && date.before(maxDate) && date.equals(maxDate);
                break;
        }
        return flag;
    }


    /**
     * 比较两个时间相差的分钟数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int compareDateMinute(Date startDate, Date endDate) {
        Long l = (endDate.getTime() - startDate.getTime()) / 1000;
        int i = l.intValue();
        return i;
    }

    /**
     * 获取日期的小时数
     *
     * @param date 日期
     * @throws Exception
     */
    public static int getHoursBayDate(Date date) throws Exception {
        int hour = 0;
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        hour = ca.get(Calendar.HOUR_OF_DAY);// 一天中的小时数
        return hour;
    }

    /**
     * 获取字符串日期的小时数
     *
     * @param dateStr     日期的字符串
     * @param dateFormart 格式化参数
     * @return
     * @throws Exception
     */
    public static int getHoursBayDate(String dateStr, String dateFormart) throws Exception {
        int hour = 0;
        Date date = strToDate(dateStr, dateFormart);
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        hour = ca.get(Calendar.HOUR_OF_DAY);
        return hour;
    }

    /**
     * 获取日期的分钟数
     *
     * @param date 日期
     * @throws Exception
     */
    public static int getMinuteByDate(Date date) throws Exception {
        int minute = 0;
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        minute = ca.get(Calendar.MINUTE);
        return minute;
    }

    /**
     * 获取字符串日期的分钟数
     *
     * @param dateStr     日期的字符串
     * @param dateFormart 格式化参数
     * @return
     * @throws Exception
     */
    public static int getMinuteByDate(String dateStr, String dateFormart) throws Exception {
        int minute = 0;
        Date date = strToDate(dateStr, dateFormart);
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        minute = ca.get(Calendar.MINUTE);
        return minute;
    }

    /**
     * @param date    需要变更的日期
     * @param days    变更天数
     * @param hasTime 是否将日期归到0点（true 归到0点）
     * @return
     */
    public static Date dateAddDays(Date date, int days, boolean hasTime) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.DAY_OF_MONTH, ca.get(Calendar.DAY_OF_MONTH) + days);
        if (hasTime) {
            ca.set(Calendar.HOUR_OF_DAY, 0);
            ca.set(Calendar.MINUTE, 0);
            ca.set(Calendar.SECOND, 0);
            ca.set(Calendar.MILLISECOND, 0);
        }
        Date newDate = ca.getTime();
        return newDate;
    }


    /**
     * 获取指定日期所在的年月
     */
    public static String getMonthInYear(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.get(Calendar.YEAR);
        ca.get(Calendar.MONTH);
        return null;
    }
}
