package com.itplayer.core.system.entity;

import com.itplayer.core.base.entity.MetaEntity;
import com.itplayer.core.base.enums.PermissionType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class Permission extends MetaEntity {

    /**
     * 资源名称
     */
    private String permissionName;

    @Autowired
    private String permissionCode;
    /**
     * 资源类型
     */
    private PermissionType permissionType;
    /**
     * 资源访问地址
     */
    private String url;
    /**
     * 路由地址
     */
    private String routerUrl;
    /**
     * 图片路径
     */
    private String icon;
    /**
     * 排序
     */
    private int sort;
    /**
     * 上级权限资源
     */
    private Permission parent;
    /**
     * 层级级数
     */
    private int lev;
    /**
     * 层级结构
     */
    private String permissionPath;

    /**
     * 子集权限资源
     */
    private List<Permission> children;


    private boolean selected;

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRouterUrl() {
        return routerUrl;
    }

    public void setRouterUrl(String routerUrl) {
        this.routerUrl = routerUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Permission getParent() {
        return parent;
    }

    public void setParent(Permission parent) {
        this.parent = parent;
    }


    public String getPermissionPath() {
        return permissionPath;
    }

    public void setPermissionPath(String permissionPath) {
        this.permissionPath = permissionPath;
    }


    public List<Permission> getChildren() {
        return children;
    }

    public void setChildren(List<Permission> children) {
        this.children = children;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public int getLev() {
        return lev;
    }

    public void setLev(int lev) {
        this.lev = lev;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getText() {
        return permissionName;
    }

    public Attributes getAttributes() {
       return new Attributes(url);
    }
}

class Attributes {
    private String url;

    public Attributes(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

