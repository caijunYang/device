package com.itplayer.core.base.exception;

/**
 * Created by caijun.yang on 2018/3/15.
 */
public class SystemException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 318372007591443391L;
    private Integer code = 0;
    private String message = "";

    public SystemException() {
        super();
    }

    public SystemException(Integer code) {
        this.code = code;
    }

    public SystemException(String message) {
        this.message = message;
    }

    public SystemException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
