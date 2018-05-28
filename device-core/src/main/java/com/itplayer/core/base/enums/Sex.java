package com.itplayer.core.base.enums;

public enum Sex {
    /**
     * 1.男
     */
    MAN(1, "男"),

    /**
     * 2.女
     */
    WOMAN(2, "女");

    private int code;

    private String text;

    Sex(int code, String text) {
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
