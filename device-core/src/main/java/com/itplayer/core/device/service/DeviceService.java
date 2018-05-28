package com.itplayer.core.device.service;

import com.itplayer.core.base.service.BaseService;
import com.itplayer.core.device.entity.Device;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public interface DeviceService extends BaseService<Device, Long> {
    void improt(MultipartFile file, String deviceName);

    HSSFWorkbook exportCard(Long id);

    void reportDevice(Device device);
}
