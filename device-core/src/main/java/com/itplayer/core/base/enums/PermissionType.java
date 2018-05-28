package com.itplayer.core.base.enums;

/**
 * Created by caijun.yang on 2018/4/23
 */
public enum PermissionType {

    /**
     * 1.菜单
     */
    MENU(1, "菜单"),
    /**
     * 2.功能
     */
    ACTION(2, "功能");

    private int code;

    private String text;

    PermissionType(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
