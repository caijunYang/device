package com.itplayer.core.device.dao;

import com.itplayer.core.base.dao.BaseDao;
import com.itplayer.core.device.entity.Device;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceDao extends BaseDao<Device, Long> {
    Device findByDeviceCode(String deviceCode);
}
