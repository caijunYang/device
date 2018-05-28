package com.itplayer.core.system.service.impl;

import com.itplayer.core.base.page.PageResult;
import com.itplayer.core.base.query.BaseQueryModel;
import com.itplayer.core.base.service.BaseServiceImpl;
import com.itplayer.core.system.entity.Role;
import com.itplayer.core.system.dao.RoleDao;
import com.itplayer.core.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

    RoleDao roleDao;

    @Autowired
    public void setRoleRepository(RoleDao roleDao) {
        this.roleDao = roleDao;
        super.setDao(roleDao);
    }

    @Override
    public List<Long>  findRoleIdsByManager(Long id) {
        return roleDao.findRoleIdsByManager(id);
    }

    @Override
    public List<Role> findRolesByManager(Long id) {
        return null;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(Role role) {
        int i = super.create(role);
        addPermission(role);
        return i;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Role role) {
        removePermission(role);
        addPermission(role);
        return super.update(role);
    }

    @Override
    public PageResult<Role> queryPage(BaseQueryModel qm) {
        return super.queryPage(qm);
    }

    @Override
    public void addPermission(Role role) {
        roleDao.addPermission(role);
    }

    @Override
    public void removePermission(Role role) {
        roleDao.removePermission(role);
    }
}
