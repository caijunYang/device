package com.itplayer.core.base.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

/**
 * Create by caijun.yang on 2017/7/18.
 */
public class JsonUtils {

    public static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtils() {
    }

    /**
     * 将对象转换为字符转
     *
     * @param object
     * @return
     */
    public static String obj2Str(Object object) {
        String str = "";
        try {
            str = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 将字符串转换为对象
     *
     * @param str
     * @param cls
     * @return
     */
    public static <T> T str2Obj(String str, Class<T> cls) {
        T object = null;
        try {
            object = objectMapper.readValue(str, cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }


}
