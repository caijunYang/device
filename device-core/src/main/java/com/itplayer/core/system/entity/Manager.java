package com.itplayer.core.system.entity;

import com.itplayer.core.base.entity.MetaEntity;
import com.itplayer.core.base.enums.Sex;
import com.itplayer.core.base.validate.Validate;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by caijun.yang on 2018/4/11
 */

public class Manager extends MetaEntity {

    private static final long serialVersionUID = 3321286107344169638L;
    /**
     * 姓名
     */
    @Validate(description = "姓名", nullable = false)
    private String realName;
    /**
     * 登录名
     */
    @Validate(description = "登录名", nullable = false)
    private String username;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 邮件地址
     */
    private String email;
    /**
     * 电话号码
     */
    private String mobile;
    /**
     * 地址
     */
    private String addr;
    /**
     * 性别
     */
    private Sex sex;
    /**
     * 区域
     */
    private Area area;
    /**
     * 用户拥有角色
     */
    private List<Role> roles;
    /**
     * 用户拥有角色编号
     */
    private Long[] roleIds;
    /**
     * 最后登录时间
     */
    private Date lastLogin;
    /**
     * 启用状态
     */
    public Boolean enabled = true;


    private String salt;

    /**
     * 不入库，菜单列表
     */
    private List<Permission> menus;
    /**
     * 不入库，权限列表
     */
    private List<Permission> permissions;

    private String oldPwd;


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public List<Permission> getMenus() {
        return menus;
    }

    public void setMenus(List<Permission> menus) {
        this.menus = menus;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

}
