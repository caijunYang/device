package com.itplayer.core.device.entity.dto;

import com.itplayer.core.device.entity.Device;

/**
 * Created by caijun.yang on 2018/5/26
 */
public class PortInfo {
    private Long id;

    private Device device;


    private String serialNo;

    private String fiberFrameAddr;

    private String fiberFramePort;

    private String targetDevice;

    private String targetDeviceModel;

    private String targetFiberFrame;

    private String physicalPort;

    private String serviceName;

    private String context;

    private String port;

    private String opticalName;

    private String opticalCore;

    private String lable;


    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getOpticalName() {
        return opticalName;
    }

    public void setOpticalName(String opticalName) {
        this.opticalName = opticalName;
    }

    public String getOpticalCore() {
        return opticalCore;
    }

    public void setOpticalCore(String opticalCore) {
        this.opticalCore = opticalCore;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
