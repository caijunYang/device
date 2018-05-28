package com.itplayer.core.device.query;

import com.itplayer.core.base.query.BaseQueryModel;
import com.itplayer.core.device.entity.BbuDeviceInfo;
import com.itplayer.core.device.entity.Device;

/**
 * Created by caijun.yang on 2018/4/17
 */
public class BbuDeviceInfoQueryModel extends BaseQueryModel<BbuDeviceInfo> {
    private Long device_id;

    public Long getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Long device_id) {
        this.device_id = device_id;
    }
}
