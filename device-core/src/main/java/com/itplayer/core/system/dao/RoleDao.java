package com.itplayer.core.system.dao;

import com.itplayer.core.base.dao.BaseDao;
import com.itplayer.core.system.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends BaseDao<Role, Long> {
    List<Long> findRoleIdsByManager(Long id);

    void addPermission(Role role);

    void removePermission(Role role);
}
