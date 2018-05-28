package com.itplayer.core.system.service.impl;

import com.itplayer.core.base.exception.SystemException;
import com.itplayer.core.base.service.BaseServiceImpl;
import com.itplayer.core.system.dao.AreaDao;
import com.itplayer.core.system.entity.Area;
import com.itplayer.core.system.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by caijun.yang on 2018/4/11
 */
@Service
public class AreaServiceImpl extends BaseServiceImpl<Area, Long> implements AreaService {

    AreaDao areaDao;

    @Autowired
    public void setAreaDao(AreaDao areaDao) {
        this.areaDao = areaDao;
        super.setDao(areaDao);
    }

    @Override
    public Area findByAreaCode(String areaCode) {
        return areaDao.findByAreaCode(areaCode);
    }

    @Override
    public int update(Area area) {
        Area dbIn = getByPrimaryKey(area.getId());
        if(!dbIn.getAreaCode().equals(area.getAreaCode())){
            throw new SystemException("登录名不可修改!");
        }
        return super.update(area);
    }

    @Override
    public int create(Area area) {
        Area byAreaCode = areaDao.findByAreaCode(area.getAreaCode());
        if(null!=byAreaCode){
            throw new SystemException("区域编号已存在!");
        }
        return super.create(area);
    }
}
