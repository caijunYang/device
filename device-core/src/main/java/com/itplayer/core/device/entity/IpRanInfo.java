package com.itplayer.core.device.entity;

import com.itplayer.core.base.entity.MetaEntity;
import com.itplayer.core.device.entity.dto.PortInfo;

/**
 * Created by caijun.yang on 2018/4/16
 * 4G传输设备
 */

public class IpRanInfo extends DeviceInfo {
    private Device device;

    /**
     * 跳纤架位置
     */
    private String fiberFrameAddr;

    /**
     * 跳纤架子端口
     */
    private String fiberFramePort;
    /**
     * 对端设备
     */
    private String targetDevice;
    /**
     * 对端设备型号
     */
    private String targetDeviceModel;
    /**
     * 对端设备架框
     */
    private String targetFiberFrame;

    /**
     * 物理端口
     */
    private String physicalPort;

    /**
     * 业务名称
     */
    private String serviceName;


    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getFiberFrameAddr() {
        return fiberFrameAddr;
    }

    public void setFiberFrameAddr(String fiberFrameAddr) {
        this.fiberFrameAddr = fiberFrameAddr;
    }

    public String getFiberFramePort() {
        return fiberFramePort;
    }

    public void setFiberFramePort(String fiberFramePort) {
        this.fiberFramePort = fiberFramePort;
    }

    public String getTargetDevice() {
        return targetDevice;
    }

    public void setTargetDevice(String targetDevice) {
        this.targetDevice = targetDevice;
    }

    public String getTargetDeviceModel() {
        return targetDeviceModel;
    }

    public void setTargetDeviceModel(String targetDeviceModel) {
        this.targetDeviceModel = targetDeviceModel;
    }

    public String getTargetFiberFrame() {
        return targetFiberFrame;
    }

    public void setTargetFiberFrame(String targetFiberFrame) {
        this.targetFiberFrame = targetFiberFrame;
    }

    public String getPhysicalPort() {
        return physicalPort;
    }

    public void setPhysicalPort(String physicalPort) {
        this.physicalPort = physicalPort;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public IpRanInfo() {
    }

    public IpRanInfo(PortInfo portInfo) {
        this.device=portInfo.getDevice();
        this.fiberFrameAddr = portInfo.getFiberFrameAddr();
        this.fiberFramePort = portInfo.getFiberFramePort();
        this.targetDevice = portInfo.getTargetDevice();
        this.targetDeviceModel = portInfo.getTargetDeviceModel();
        this.targetFiberFrame = portInfo.getTargetFiberFrame();
        this.physicalPort = portInfo.getPhysicalPort();
        this.serviceName = portInfo.getServiceName();
        super.setId(portInfo.getId());
    }
}
