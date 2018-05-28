package com.itplayer.core.device.service;

import com.itplayer.core.base.service.BaseService;
import com.itplayer.core.device.entity.Device;
import com.itplayer.core.device.entity.OrdinaryInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public interface OrdinaryInfoService extends BaseService<OrdinaryInfo, Long> {
    HSSFWorkbook export(Device device);

    List<OrdinaryInfo>  findByDevice(Device device);
}
