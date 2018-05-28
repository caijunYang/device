package com.itplayer.core.system.entity;

import com.itplayer.core.base.entity.MetaEntity;

/**
 * Created by caijun.yang on 2018/4/11
 */

public class Area extends MetaEntity {


    private String areaCode;

    private String areaName;

    private Area parent;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Area getParent() {
        return parent;
    }

    public void setParent(Area parent) {
        this.parent = parent;
    }

    public String getText(){
        return areaName;
    }
}
