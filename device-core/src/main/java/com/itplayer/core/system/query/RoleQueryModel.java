package com.itplayer.core.system.query;

import com.itplayer.core.base.query.BaseQueryModel;
import com.itplayer.core.system.entity.Role;

public class RoleQueryModel extends BaseQueryModel<Role> {
    private String roleName;



    public String getRealName() {
        return roleName;
    }

    public void setRealName(String realName) {
        this.roleName = realName;
    }
}
