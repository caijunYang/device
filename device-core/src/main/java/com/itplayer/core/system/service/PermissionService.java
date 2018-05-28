package com.itplayer.core.system.service;

import com.itplayer.core.base.service.BaseService;
import com.itplayer.core.system.entity.Manager;
import com.itplayer.core.system.entity.Permission;
import com.itplayer.core.system.entity.Role;
import com.itplayer.core.system.query.PermissionQueryModel;

import java.util.List;

public interface PermissionService extends BaseService<Permission, Long> {
    /**
     * 根据manager Id查询权限列表
     *
     * @return
     * @
     */
    List<Permission> findPermissionListByManager(Manager manager) ;

    /**
     * 根据manager Id查询菜单列表
     *
     * @return
     * @
     */
    List<Permission> findMenuListByManager(Manager manager) ;

    /**
     * 根据角色查询角色拥有的权限资源
     *
     * @param role
     * @return
     * @
     */
    List<Permission> getPermissions(Role role) ;
    //  public List<Permission> findPermissionByRole(Role role);
}
