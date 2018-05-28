package com.itplayer.core.system.service.impl;

import com.itplayer.core.base.service.BaseServiceImpl;
import com.itplayer.core.system.dao.SystemLogDao;
import com.itplayer.core.system.entity.SystemLog;
import com.itplayer.core.system.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemLogServiceImpl extends BaseServiceImpl<SystemLog, Long> implements SystemLogService {

    private SystemLogDao systemLogDao;

    @Autowired
    public void setSystemLogDao(SystemLogDao systemLogDao) {
        this.systemLogDao = systemLogDao;
        super.setDao(systemLogDao);
    }
}
