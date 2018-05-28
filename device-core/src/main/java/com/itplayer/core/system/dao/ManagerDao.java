package com.itplayer.core.system.dao;

import com.itplayer.core.base.dao.BaseDao;
import com.itplayer.core.system.entity.Manager;
import org.springframework.stereotype.Repository;


@Repository
public interface ManagerDao extends BaseDao<Manager, Long> {
    Manager findByUsername(String username);

    void updateLastLogin(Manager loginManager);

    void removeRole(Manager manager);

    void addRole(Manager manager);
}
