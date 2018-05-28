package com.itplayer.core.device.entity;

import com.itplayer.core.base.entity.MetaEntity;
import com.itplayer.core.system.entity.Area;

/**
 * 机房
 */
public class EngineRoom extends MetaEntity {


    private String roomCode;
    private String deviceRoomName;

    private String roomDesc;

    private Area area;

    public String getDeviceRoomName() {
        return deviceRoomName;
    }

    public void setDeviceRoomName(String deviceRoomName) {
        this.deviceRoomName = deviceRoomName;
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }


    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }
}

