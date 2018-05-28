package com.itplayer.core.device.service.impl;

import com.itplayer.core.base.enums.DeviceType;
import com.itplayer.core.base.exception.SystemException;
import com.itplayer.core.device.entity.*;
import com.itplayer.core.device.entity.dto.PortInfo;
import com.itplayer.core.device.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by caijun.yang on 2018/5/26
 */
@Service
public class PortInfoServiceImpl implements PortInfoService {

    @Autowired
    private BbuDeviceInfoService bbuDeviceInfoService;
    @Autowired
    private IpRanInfoService ipRanInfoService;
    @Autowired
    private OrdinaryInfoService ordinaryInfoService;
    @Autowired
    private OltInfoService oltInfoService;
    @Autowired
    private DeviceService deviceService;

    @Override
    public void reportPortInfo(PortInfo portInfo) {
        Device device1 = portInfo.getDevice();
        if (null == device1) {
            throw new SystemException("端口设备未选则");
        }
        Device device = deviceService.getByPrimaryKey(device1.getId());
        if (null == device) {
            throw new SystemException("未识别的设备信息");
        }
        DeviceType deviceType = device.getDeviceType();
        switch (deviceType) {
            case ORDINARY:
                OrdinaryInfo ordinaryInfo = new OrdinaryInfo(portInfo);
                if (ordinaryInfo.getId() != null) {
                    ordinaryInfoService.update(ordinaryInfo);
                } else {
                    ordinaryInfoService.create(ordinaryInfo);
                }
                break;
            case OLT:
                OltInfo oltInfo = new OltInfo(portInfo);
                if (oltInfo.getId() != null) {
                    oltInfoService.update(oltInfo);
                } else {
                    oltInfoService.create(oltInfo);
                }
                break;
            case IPRAN:
                IpRanInfo ipRanInfo = new IpRanInfo(portInfo);
                if (ipRanInfo.getId() != null) {
                    ipRanInfoService.update(ipRanInfo);
                } else {
                    ipRanInfoService.create(ipRanInfo);
                }
                break;
            case BBU:
                BbuDeviceInfo bbuDeviceInfo = new BbuDeviceInfo(portInfo);
                if (bbuDeviceInfo.getId() != null) {
                    bbuDeviceInfoService.update(bbuDeviceInfo);
                } else {
                    bbuDeviceInfoService.create(bbuDeviceInfo);
                }
                break;
        }
    }
}