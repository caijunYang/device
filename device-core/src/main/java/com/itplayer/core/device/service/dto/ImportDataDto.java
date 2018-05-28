package com.itplayer.core.device.service.dto;

import com.itplayer.core.base.enums.DeviceType;

public class ImportDataDto {

    private String deviceCode;

    private String areaCode;

    private String roomCode;

    private String deviceName;

    private String deviceModel;

    private String deviceFrame;

    private DeviceType deviceType;

    private String snCode;



    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceFrame() {
        return deviceFrame;
    }

    public void setDeviceFrame(String deviceFrame) {
        this.deviceFrame = deviceFrame;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }
}
