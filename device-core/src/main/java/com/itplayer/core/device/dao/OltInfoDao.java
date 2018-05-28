package com.itplayer.core.device.dao;

import com.itplayer.core.base.dao.BaseDao;
import com.itplayer.core.device.entity.BbuDeviceInfo;
import com.itplayer.core.device.entity.Device;
import com.itplayer.core.device.entity.OltInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by caijun.yang on 2018/4/17
 */
@Repository
public interface OltInfoDao extends BaseDao<OltInfo, Long> {
    List<OltInfo> findByDevice(Device device);
}
