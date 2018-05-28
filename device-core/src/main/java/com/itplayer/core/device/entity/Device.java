package com.itplayer.core.device.entity;

import com.itplayer.core.base.entity.MetaEntity;
import com.itplayer.core.base.enums.DeviceType;
import com.itplayer.core.base.utils.ObjectUtils;
import com.itplayer.core.device.entity.dto.PortInfo;
import com.itplayer.core.system.entity.Area;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 设备
 */
public class Device extends MetaEntity {

    private String deviceCode;

    private Area area;
    /**
     * 机房id
     */
    private EngineRoom engineRoom;
    /**
     * 本端设备名称
     */
    private String deviceName;
    /**
     * 本端设备型号
     */
    private String deviceModel;
    /**
     * 本端所在机架
     */
    private String deviceFrame;

    /**
     * SN码， ipran独有
     */
    private String snCode;

    private DeviceType deviceType;

    private List<PortInfo> portInfo;

    private Object deviceInfos;

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

    public EngineRoom getEngineRoom() {
        return engineRoom;
    }

    public void setEngineRoom(EngineRoom engineRoom) {
        this.engineRoom = engineRoom;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public List<PortInfo> getPortInfo() {
        return portInfo;
    }

    public void setPortInfo(List<PortInfo> portInfo) {
        this.portInfo = portInfo;
    }

    public Object getDeviceInfos() {
        return deviceInfos;
    }

    public void setDeviceInfos(Object deviceInfos) {
        this.deviceInfos = deviceInfos;
    }
}
