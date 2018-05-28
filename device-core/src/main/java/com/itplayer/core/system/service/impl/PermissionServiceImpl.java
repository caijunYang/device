package com.itplayer.core.system.service.impl;

import com.itplayer.core.base.enums.PermissionType;
import com.itplayer.core.base.service.BaseServiceImpl;
import com.itplayer.core.base.utils.ObjectUtils;
import com.itplayer.core.system.entity.Manager;
import com.itplayer.core.system.entity.Permission;
import com.itplayer.core.system.dao.PermissionDao;
import com.itplayer.core.system.entity.Role;
import com.itplayer.core.system.query.PermissionQueryModel;
import com.itplayer.core.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, Long> implements PermissionService {

    PermissionDao permissionDao;

    @Autowired
    public void setPermissionDao(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
        super.setDao(permissionDao);
    }




    public void doSortPermisions(List<Permission> permissions, Permission parentPermission)  {
        Iterator<Permission> it = permissions.iterator();
        while (it.hasNext()) {
            Permission permission = it.next();
            if (parentPermission.getChildren() == null) {
                parentPermission.setChildren(new ArrayList<Permission>());
            }
            if (null != permission.getParent()) {
                if (parentPermission.getId().equals(permission.getParent().getId())) {
                    parentPermission.getChildren().add(permission);
                    doSortPermisions(permissions, permission);
                }
            }
        }
    }

    @Override
    public List<Permission> findPermissionListByManager(Manager manager)  {
        return permissionDao.findPermissionListByManager(manager);
    }

    @Override
    public List<Permission> findMenuListByManager(Manager manager)  {
        List<Permission> rootMenus = permissionDao.findRootMenus();
        List<Permission> permissions = permissionDao.findMenuListByManager(manager);
        Iterator<Permission> it = rootMenus.iterator();
        while (it.hasNext()) {
            Permission permission = it.next();
            doSortPermisions(permissions, permission);
            if (permission.getChildren() == null || permission.getChildren().size() < 1) {
                it.remove();
            }
        }
        return rootMenus;
    }

    // @Override
    // public List<Permission> findPermissionByRole(Role role){
    // return dao.findPermissionByRole(role);
    //
    // }
    @SuppressWarnings("unchecked")
    @Override
    public List<Permission> getPermissions(Role role)  {

        List<Permission> permissionByRole = permissionDao.findPermissionByRole(role);
         List<Permission> permissions = permissionDao.findAll();
//        List<Permission> permissions = (List<Permission>) ObjectUtils.cloneObject(Constant.PERMISSIONS);
        permissionsItem:
        for (Permission per : permissions) {
            Iterator<Permission> it = permissionByRole.iterator();
            while (it.hasNext()) {
                Permission next = it.next();
                if (next.getId().equals(per.getId())) {
                    per.setSelected(true);
                    continue permissionsItem;
                }
            }
        }
        List<Permission> newPermissions = new ArrayList<Permission>();
        addRootPermission(permissions, newPermissions);
        for (Permission permission : newPermissions) {
            doSortPermisions(permissions, permission);
        }
        return newPermissions;
    }

    /**
     * 找到根权限，既没有父权限的权限
     *
     * @param permissions
     * @param newPermissions
     */
    public void addRootPermission(List<Permission> permissions, List<Permission> newPermissions) {
        if (permissions != null && permissions.size() > 0) {
            Iterator<Permission> it = permissions.iterator();
            while (it.hasNext()) {
                Permission permission = it.next();
                if (null == permission.getParent()) {
                    newPermissions.add(permission);
                    it.remove();
                }
            }
        }
    }
}
