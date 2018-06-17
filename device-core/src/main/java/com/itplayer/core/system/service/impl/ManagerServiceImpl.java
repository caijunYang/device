package com.itplayer.core.system.service.impl;

import com.itplayer.core.base.exception.SystemException;
import com.itplayer.core.base.service.BaseServiceImpl;
import com.itplayer.core.base.utils.AesUtil;
import com.itplayer.core.base.utils.StrUtils;
import com.itplayer.core.system.dao.ManagerDao;
import com.itplayer.core.system.entity.Manager;
import com.itplayer.core.system.entity.Permission;
import com.itplayer.core.system.entity.Role;
import com.itplayer.core.system.service.ManagerService;
import com.itplayer.core.system.service.PermissionService;
import com.itplayer.core.system.service.RoleService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ManagerServiceImpl extends BaseServiceImpl<Manager, Long> implements ManagerService {

    @Autowired
    @Qualifier(value = "managerDao")
    ManagerDao managerDao;

    @Autowired
    PermissionService permissionService;

    @Autowired
    RoleService roleService;

    @Autowired
    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
        super.setDao(managerDao);
    }

    @Override
    public Manager findByUsername(String username) {
        return managerDao.findByUsername(username);
    }

    @Override
    public void refreshPermission(Manager manager) {
        List<Permission> menuListByManager = permissionService.findMenuListByManager(manager);
        manager.setMenus(menuListByManager);

    }

    @Override
    public List<Role> managerRole(Long id) {
        List<Role> roles = roleService.findAll();
        if (roles == null && roles.size() < 1) {
            throw new SystemException("系统未初始化角色，请联系管理员");
        }
        List<Long> roleIdsByManager = roleService.findRoleIdsByManager(id);
        if (null != roleIdsByManager && roleIdsByManager.size() > 0) {
            for (Role role : roles) {
                if (roleIdsByManager.contains(role.getId())) {
                    role.setChecked(true);
                }
            }
        }
        return roles;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setRole(Manager manager) {
        managerDao.removeRole(manager);
        managerDao.addRole(manager);
    }

    @Override
    public void changeInfo(Manager manager) {
        String password = manager.getPassword();
        if (StrUtils.isNotNull(password)) {
            Manager man = getByPrimaryKey(manager.getId());
            if (null == man) {
                man = findByUsername(manager.getUsername());
            }
            manager.setId(man.getId());
            if (null == man) {
                throw new SystemException("账号不存在");
            }
            String salt = man.getSalt();
            String oldPwd = manager.getOldPwd();
//            System.out.println(oldPwd);
            String md5String = AesUtil.getMD5String(salt +oldPwd);
            if (!md5String.equals(man.getPassword())) {
                throw new SystemException("原密码不正确");
            }
            String random = RandomStringUtils.randomNumeric(6);
            manager.setSalt(random);
            String newSafePwd = AesUtil.getMD5String(random + password);
            manager.setPassword(newSafePwd);
        }
        update(manager);
    }

    @Override
    public int create(Manager manager) {
        Manager managerDb = findByUsername(manager.getUsername());
        if (null != managerDb) {
            throw new SystemException("用登录名称户名已存在!");
        }
        String random = RandomStringUtils.randomNumeric(6);
        manager.setSalt(random);
        String md5String = AesUtil.getMD5String(random + "123456");
        manager.setPassword(md5String);
        return super.create(manager);
    }

    @Override
    public int update(Manager manager) {
        Manager dbIn = getByPrimaryKey(manager.getId());
        if (!dbIn.getUsername().equals(manager.getUsername())) {
            throw new SystemException("登录名不可修改!");
        }
        return super.update(manager);
    }
}
