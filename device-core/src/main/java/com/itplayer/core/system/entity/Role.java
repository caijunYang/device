package com.itplayer.core.system.entity;

import com.itplayer.core.base.entity.MetaEntity;

import java.util.List;


public class Role extends MetaEntity {
    private String roleCode;
    private String roleName;

    private boolean checked = false;
    private List<Manager> managers;// 一个角色对应多个用户

    private List<Permission> permissions;


    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getText() {
        return roleName;
    }

}
