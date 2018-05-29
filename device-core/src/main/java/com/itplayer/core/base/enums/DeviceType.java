package com.itplayer.core.base.enums;

public enum DeviceType {
    ORDINARY("Ordinary 普通传输设备") ,
    OLT("OLT 上网传输设备"),
    BBU("BBU 室内基带处理单元"),
    IPRAN("IPRAN 4G传输设备");
    private String name;



    DeviceType(String name) {
        this.name = name;
    }
}
