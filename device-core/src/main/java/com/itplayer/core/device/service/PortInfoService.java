package com.itplayer.core.device.service;

import com.itplayer.core.device.entity.Device;
import com.itplayer.core.device.entity.dto.PortInfo;

/**
 * Created by caijun.yang on 2018/5/26
 */
public interface PortInfoService {
    void reportPortInfo(PortInfo portInfo);
}
