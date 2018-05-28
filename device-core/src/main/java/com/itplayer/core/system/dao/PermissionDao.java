package com.itplayer.core.system.dao;

import com.itplayer.core.base.dao.BaseDao;
import com.itplayer.core.system.entity.Manager;
import com.itplayer.core.system.entity.Permission;
import com.itplayer.core.system.entity.Role;
import com.itplayer.core.system.query.PermissionQueryModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao extends BaseDao<Permission, Long> {

    List<Permission> findPermissionByRole(Role role);

    List<Permission> findPermissionListByManager(Manager manager);

    List<Permission> findRootMenus();

    List<Permission> findMenuListByManager(Manager manager);
}
