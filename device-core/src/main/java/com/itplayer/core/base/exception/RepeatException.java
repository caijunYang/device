package com.itplayer.core.base.exception;

/**
 * Created by Nathans on 2017/11/26.
 */
public class RepeatException extends RuntimeException {

    private Integer code = 400;
    private String msg;

    public RepeatException() {
        super();
    }

    public RepeatException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepeatException(String message) {
        super(message);
    }

    public RepeatException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public RepeatException(Throwable cause) {
        super(cause);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
