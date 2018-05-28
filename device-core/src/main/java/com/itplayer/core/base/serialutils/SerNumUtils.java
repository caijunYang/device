package com.itplayer.core.base.serialutils;

import com.itplayer.core.base.utils.DateUtils;

import java.util.Date;
import java.util.Random;

/**
 * Created by caijun.yang on 2017/7/28.
 */
public class SerNumUtils {
    public static int sortNum = 0;

    public synchronized static int getSortNum() {
        sortNum = sortNum % 100;
        synchronized (SerNumUtils.class) {
            return sortNum++;
        }
    }

    /**
     * 获取随机编号
     *
     * @param t
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> String getSerNum(T t) throws Exception {
        Date date = new Date();
        String dateToStr = DateUtils.dateToStr(date, "yyyyMMddHHmmssSSS");
        Class<?> aClass = t.getClass();
        SerialNumberHearder annotation = aClass.getAnnotation(SerialNumberHearder.class);
        if (null == annotation) {
            return dateToStr + getSortNum();
        }
        String prefix = annotation.prefix();
        String suffix = annotation.suffix();
        return prefix + dateToStr + getSortNum() + suffix;
    }

    /**
     * 获取前缀
     */
    public static <T> String getPrefix(T t) throws Exception {
        Class<?> aClass = t.getClass();
        SerialNumberHearder annotation = aClass.getAnnotation(SerialNumberHearder.class);
        String prefix = annotation.prefix();
        return prefix;
    }

    /**
     * 获取后缀缀
     */
    public static <T> String getSuffix(T t) throws Exception {
        Class<?> aClass = t.getClass();
        SerialNumberHearder annotation = aClass.getAnnotation(SerialNumberHearder.class);
        String suffix = annotation.suffix();
        return suffix;
    }

    public static String getSortNumByDate(Date date) {
    	String dateTime = DateUtils.dateToStr(date, "ssSSS");
    	 int max=99;
         int min=0;
         Random random = new Random();

         int s = random.nextInt(max)%(max-min+1) + min;
        return dateTime+s;
    }

    public static void main(String[] args) {
       for (int i=0;i<110;i++){

        System.out.println(getSortNum());
       }
    }
}
