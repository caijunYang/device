package com.itplayer.core.system.dao;

import com.itplayer.core.base.dao.BaseDao;
import com.itplayer.core.system.entity.SystemLog;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemLogDao extends BaseDao<SystemLog, Long> {
}
