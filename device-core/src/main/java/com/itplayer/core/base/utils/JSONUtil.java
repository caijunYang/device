package com.itplayer.core.base.utils;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
public class JSONUtil {
    public static final ObjectMapper objectMapper = new ObjectMapper();
    public static final ObjectMapper INDENT_OUTPUT_OBJECT_MAPPER = new ObjectMapper();
    static {
        objectMapper.enable(MapperFeature.USE_GETTERS_AS_SETTERS);
        objectMapper.enable(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        INDENT_OUTPUT_OBJECT_MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        INDENT_OUTPUT_OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        INDENT_OUTPUT_OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        INDENT_OUTPUT_OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private JSONUtil() {
    }

    public static String toJson(Object bean) throws JsonProcessingException {
        if (bean == null){
            return null;
        }
        return objectMapper.writeValueAsString(bean);
    }

    public static <T> T toBean(String json, Class<T> item) {
        T t = null;
        try {
            t = objectMapper.readValue(json,item);
        }catch (Exception e){}
        return t;
    }

    public static <T> T toBean(String json, Class<T> item, String charset) {
        T t = null;
        try {
            byte[] bytes = json.getBytes("ISO-8859-1");
            t = objectMapper.readValue(new String(bytes,charset),item);
        }catch (Exception e){}
        return t;
    }

    public static void main(String[] args) throws Exception {
        String json = "{\"code\":200,\"msg\":\"SUCCESS\",\"data\":[{\"userId\":1,\"userName\":\"超级管理员\",\"account\":\"superAdmin1\",\"deptId\":null,\"deptCode\":null,\"deptName\":null,\"roleId\":1,\"roleName\":null},{\"userId\":2,\"userName\":\"安徽省级管理员\",\"account\":\"anhuisheng\",\"deptId\":null,\"deptCode\":null,\"deptName\":null,\"roleId\":3,\"roleName\":null},{\"userId\":3,\"userName\":\"宣城市级管理员\",\"account\":\"xuanchengshi\",\"deptId\":\"2\",\"deptCode\":\"551\",\"deptName\":\"宣城\",\"roleId\":4,\"roleName\":null}]}";
        Map<String,Object> resultMap = new LinkedHashMap<>();
        resultMap = JSONUtil.toBean(json,resultMap.getClass());
        List<Map<String,Object>> dataMapList = (List<Map<String,Object>>)resultMap.get("data");
        for (Map map : dataMapList) {
            for (Object obj : map.keySet()) {
                System.out.print(obj.toString()+":"+map.get(obj)+"; ");
            }
            System.out.println();
        }
    }
}