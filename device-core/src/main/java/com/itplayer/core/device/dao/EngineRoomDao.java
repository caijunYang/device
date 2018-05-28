package com.itplayer.core.device.dao;

import com.itplayer.core.base.dao.BaseDao;
import com.itplayer.core.device.entity.EngineRoom;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineRoomDao extends BaseDao<EngineRoom, Long> {
    EngineRoom findByCode(String roomCode);
}
