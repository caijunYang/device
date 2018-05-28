package com.itplayer.core.system.query;

import com.itplayer.core.base.query.BaseQueryModel;
import com.itplayer.core.system.entity.Area;
import com.itplayer.core.system.entity.Manager;

public class ManagerQueryModel extends BaseQueryModel<Manager> {
    private String realName;

    private Long area_id;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getArea_id() {
        return area_id;
    }

    public void setArea_id(Long area_id) {
        this.area_id = area_id;
    }
}
