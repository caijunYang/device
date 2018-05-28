package com.itplayer.core.system.service;

import com.itplayer.core.base.service.BaseService;
import com.itplayer.core.system.entity.Role;

import java.util.List;

public interface RoleService extends BaseService<Role, Long> {
    List<Long> findRoleIdsByManager(Long id);

    List<Role> findRolesByManager(Long id);

    void addPermission(Role role);

    void removePermission(Role role);
}
