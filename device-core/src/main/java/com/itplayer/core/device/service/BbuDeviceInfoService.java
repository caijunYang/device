package com.itplayer.core.device.service;

import com.itplayer.core.base.service.BaseService;
import com.itplayer.core.device.entity.BbuDeviceInfo;
import com.itplayer.core.device.entity.Device;
import com.itplayer.core.device.entity.OrdinaryInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * Created by caijun.yang on 2018/4/17
 */
public interface BbuDeviceInfoService extends BaseService<BbuDeviceInfo, Long> {
    HSSFWorkbook export(Device device);

    List<BbuDeviceInfo> findByDevice(Device device);
}
