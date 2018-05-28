package com.itplayer.core.system.service;

import com.itplayer.core.base.service.BaseService;
import com.itplayer.core.system.entity.Manager;
import com.itplayer.core.system.entity.Role;

import java.util.List;

public interface ManagerService  extends BaseService<Manager,Long> {
    Manager findByUsername(String username);


    void refreshPermission(Manager manager);

    List<Role> managerRole(Long id);

    void setRole(Manager manager);

    void changeInfo(Manager manager);
}
