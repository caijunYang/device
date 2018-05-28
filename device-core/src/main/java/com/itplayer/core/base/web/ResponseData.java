package com.itplayer.core.base.web;

import java.io.Serializable;

/**
 * Created by caijun.yang on 2016/5/24.
 */
public class ResponseData implements Serializable {
    private static final long serialVersionUID = -7735376359249727752L;

    public ResponseData() {
        this.code = 1;
        this.msg = "";
    }

    public ResponseData(int code) {
        this.code = code;
    }

    public ResponseData(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 失败
     */
    public static final int ERROR = 0;
    /**
     * 成功
     */
    public static final int SUCCESS = 1;

    private int code = 1;
    private String msg;
    private Object data;

    public ResponseData putData(Object data) {
        setData(data);
        return this;
    }

    public static int getERROR() {
        return ERROR;
    }

    public static int getSUCCESS() {
        return SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseData{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
    }

}
