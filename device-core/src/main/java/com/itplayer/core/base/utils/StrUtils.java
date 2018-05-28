package com.itplayer.core.base.utils;

/**
 * Created by caijun.yang on 2018/4/11
 */
public class StrUtils {
    /**
     * 判断字符串为空
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotNull(String str) {
        return !isNull(str);
    }
}
