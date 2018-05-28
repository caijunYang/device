package com.itplayer.core.device.service;

import com.itplayer.core.base.service.BaseService;
import com.itplayer.core.device.entity.EngineRoom;

public interface EngineRoomService extends BaseService<EngineRoom,Long> {
    EngineRoom findByCode(String roomCode1);
}
