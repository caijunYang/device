package com.itplayer.core.device.service;

import com.itplayer.core.base.service.BaseService;
import com.itplayer.core.device.entity.Device;
import com.itplayer.core.device.entity.OltInfo;
import com.itplayer.core.device.entity.OrdinaryInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * Created by caijun.yang on 2018/4/17
 */
public interface OltInfoService extends BaseService<OltInfo, Long> {
    HSSFWorkbook export(Device device);

    List<OltInfo>  findByDevice(Device device);
}
