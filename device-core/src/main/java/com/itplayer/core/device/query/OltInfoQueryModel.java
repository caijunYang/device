package com.itplayer.core.device.query;

import com.itplayer.core.base.query.BaseQueryModel;
import com.itplayer.core.device.entity.Device;
import com.itplayer.core.device.entity.OltInfo;

/**
 * Created by caijun.yang on 2018/4/17
 */
public class OltInfoQueryModel extends BaseQueryModel<OltInfo> {

    private Long device_id;

    public Long getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Long device_id) {
        this.device_id = device_id;
    }
}
