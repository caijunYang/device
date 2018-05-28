package com.itplayer.core.device.query;

import com.itplayer.core.base.query.BaseQueryModel;
import com.itplayer.core.device.entity.Device;
import com.itplayer.core.device.entity.EngineRoom;

public class DeviceQueryModel extends BaseQueryModel<Device> {

    private Long area_id;

    private Long engineRoom_id;

    private String deviceCode;

    private String deviceName;

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Long getArea_id() {
        return area_id;
    }

    public void setArea_id(Long area_id) {
        this.area_id = area_id;
    }

    public Long getEngineRoom_id() {
        return engineRoom_id;
    }

    public void setEngineRoom_id(Long engineRoom_id) {
        this.engineRoom_id = engineRoom_id;
    }
}
