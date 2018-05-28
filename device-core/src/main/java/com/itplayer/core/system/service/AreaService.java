package com.itplayer.core.system.service;

import com.itplayer.core.base.service.BaseService;
import com.itplayer.core.system.entity.Area;
import com.itplayer.core.system.query.AreaQueryModel;

/**
 * Created by caijun.yang on 2018/4/11
 */
public interface AreaService extends BaseService<Area, Long> {

    Area findByAreaCode(String areaCode);
}
