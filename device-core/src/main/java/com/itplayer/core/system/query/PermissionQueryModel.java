package com.itplayer.core.system.query;

import com.itplayer.core.base.enums.PermissionType;
import com.itplayer.core.base.query.BaseQueryModel;
import com.itplayer.core.system.entity.Manager;
import com.itplayer.core.system.entity.Permission;

public class PermissionQueryModel extends BaseQueryModel<Permission> {
    private String permissionName;

    private Manager manager;

    private Integer lev;

    private PermissionType permissionType;

    private Permission parent;


    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }


    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Integer getLev() {
        return lev;
    }

    public void setLev(Integer lev) {
        this.lev = lev;
    }

    public Permission getParent() {
        return parent;
    }

    public void setParent(Permission parent) {
        this.parent = parent;
    }
}
