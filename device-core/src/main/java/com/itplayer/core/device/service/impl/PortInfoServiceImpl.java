package com.itplayer.core.device.service.impl;

import com.itplayer.core.base.enums.DeviceType;
import com.itplayer.core.base.exception.SystemException;
import com.itplayer.core.base.utils.StrUtils;
import com.itplayer.core.device.entity.*;
import com.itplayer.core.device.entity.dto.PortInfo;
import com.itplayer.core.device.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                ReportOrdinaryInfo(ordinaryInfo);
                break;
            case OLT:
                OltInfo oltInfo = new OltInfo(portInfo);
                ReportOltInfo(oltInfo);
                break;
            case IPRAN:
                IpRanInfo ipRanInfo = new IpRanInfo(portInfo);
                ReportIpRanInfo(ipRanInfo);
                break;
            case BBU:
                BbuDeviceInfo bbuDeviceInfo = new BbuDeviceInfo(portInfo);
                ReportBbuDeviceInfo(bbuDeviceInfo);
                break;
        }
    }


    public void ReportOrdinaryInfo(OrdinaryInfo ordinaryInfo) {
        if (StrUtils.isNull(ordinaryInfo.getPort())) {
            throw new SystemException("请填写端口");
        }
        if (StrUtils.isNull(ordinaryInfo.getFiberFramePort())) {
            throw new SystemException("请填写跳纤架位置");
        }
        List<OrdinaryInfo> ordinaryInfos = ordinaryInfoService.findByEntity(ordinaryInfo);


        if (ordinaryInfo.getId() != null) {
            ordinaryInfoService.update(ordinaryInfo);
        } else {
            ordinaryInfoService.create(ordinaryInfo);
        }
    }

    public void ReportOltInfo(OltInfo oltInfo) {
        if (StrUtils.isNull(oltInfo.getPort())) {
            throw new SystemException("请填写端口");
        }
        if (StrUtils.isNull(oltInfo.getFiberFramePort())) {
            throw new SystemException("请填写跳纤架位置");
        }
        if (StrUtils.isNull(oltInfo.getTargetDevice())) {
            throw new SystemException("请填写出局ODF架");

        }
        if (StrUtils.isNull(oltInfo.getPhysicalPort())) {
            throw new SystemException("请填写ODF架框槽端子");

        }
        if (oltInfo.getId() != null) {
            oltInfoService.update(oltInfo);
        } else {
            oltInfoService.create(oltInfo);
        }
    }

    public void ReportIpRanInfo(IpRanInfo ipRanInfo) {
        if (StrUtils.isNull(ipRanInfo.getPort())) {
            throw new SystemException("请填写端口");
        }
        if (StrUtils.isNull(ipRanInfo.getFiberFramePort())) {
            throw new SystemException("请填写跳纤架位置");
        }
        if (ipRanInfo.getId() != null) {
            ipRanInfoService.update(ipRanInfo);
        } else {
            ipRanInfoService.create(ipRanInfo);
        }
    }

    public void ReportBbuDeviceInfo(BbuDeviceInfo bbuDeviceInfo) {
        if (StrUtils.isNull(bbuDeviceInfo.getPort())) {
            throw new SystemException("请填写端口");
        }
        if (StrUtils.isNull(bbuDeviceInfo.getFiberFramePort())) {
            throw new SystemException("请填写跳纤架位置");
        }
        if (StrUtils.isNull(bbuDeviceInfo.getSerialNo())) {
            throw new SystemException("请填写编号");
        }
        if (bbuDeviceInfo.getId() != null) {
            bbuDeviceInfoService.update(bbuDeviceInfo);
        } else {
            bbuDeviceInfoService.create(bbuDeviceInfo);
        }
    }
}
