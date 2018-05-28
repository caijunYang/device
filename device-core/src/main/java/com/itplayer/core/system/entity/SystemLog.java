package com.itplayer.core.system.entity;

import com.itplayer.core.base.entity.MetaEntity;

import java.util.Date;

public class SystemLog extends MetaEntity {

    /*
     * 登录人姓名
     */
    private String name;
    /*
     *登录ip
     */
    private String loginip;
    /**
     * 日志编号
     */
    private String permissionCode;
    private String content; //日志内容
    private String accessUri; //访问路径
    private String accessParams; //访问参数
    private boolean status;


    public SystemLog() {
    }

    public SystemLog(String name, String loginip, String permissionCode, String accessUri, String accessParams, String content) {
        this.name = name;
        this.loginip = loginip;
        this.permissionCode = permissionCode;
        this.accessUri = accessUri;
        this.accessParams = accessParams;
        this.content = content;
        super.setCreateDate(new Date());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginip() {
        return loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAccessUri() {
        return accessUri;
    }

    public void setAccessUri(String accessUri) {
        this.accessUri = accessUri;
    }

    public String getAccessParams() {
        return accessParams;
    }

    public void setAccessParams(String accessParams) {
        this.accessParams = accessParams;
    }
    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
