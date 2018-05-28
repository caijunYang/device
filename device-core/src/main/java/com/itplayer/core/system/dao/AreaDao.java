package com.itplayer.core.system.dao;

import com.itplayer.core.base.dao.BaseDao;
import com.itplayer.core.system.entity.Area;
import org.springframework.stereotype.Repository;

/**
 * Created by caijun.yang on 2018/4/11
 */
@Repository
public interface AreaDao extends BaseDao<Area, Long> {

    Area findByAreaCode(String areaCode);
}
