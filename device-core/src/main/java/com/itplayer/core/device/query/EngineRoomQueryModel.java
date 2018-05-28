package com.itplayer.core.device.query;

import com.itplayer.core.base.query.BaseQueryModel;
import com.itplayer.core.device.entity.EngineRoom;
import com.itplayer.core.system.entity.Area;

public class EngineRoomQueryModel extends BaseQueryModel<EngineRoom> {


    private Long area_id;
    private String roomName;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getArea_id() {
        return area_id;
    }

    public void setArea_id(Long area_id) {
        this.area_id = area_id;
    }
}
