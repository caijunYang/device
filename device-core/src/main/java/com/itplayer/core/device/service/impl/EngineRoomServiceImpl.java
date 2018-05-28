package com.itplayer.core.device.service.impl;

import com.itplayer.core.base.service.BaseServiceImpl;
import com.itplayer.core.device.dao.EngineRoomDao;
import com.itplayer.core.device.entity.EngineRoom;
import com.itplayer.core.device.service.EngineRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EngineRoomServiceImpl extends BaseServiceImpl<EngineRoom, Long> implements EngineRoomService {
    EngineRoomDao engineRoomDao;

    @Autowired
    public void setEngineRoomDao(EngineRoomDao engineRoomDao) {
        this.engineRoomDao = engineRoomDao;
        super.setDao(engineRoomDao);
    }

    @Override
    public EngineRoom findByCode(String roomCode) {
        return engineRoomDao.findByCode(roomCode);
    }
}
