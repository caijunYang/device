package com.itplayer.core.device.dao;

import com.itplayer.core.base.dao.BaseDao;
import com.itplayer.core.device.entity.Device;
import com.itplayer.core.device.entity.OrdinaryInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdinaryInfoDao extends BaseDao<OrdinaryInfo, Long> {
    List<OrdinaryInfo> findByDevice(Device device);
}
