package com.itplayer.manage.system.controller;

import com.itplayer.core.base.exception.SystemException;
import com.itplayer.core.base.utils.AesUtil;
import com.itplayer.core.base.web.BaseController;
import com.itplayer.core.base.web.ResponseData;
import com.itplayer.core.system.entity.Manager;
import com.itplayer.core.system.entity.Permission;
import com.itplayer.core.system.log.PermissionValidate;
import com.itplayer.core.system.service.ManagerService;
import com.itplayer.core.system.service.PermissionService;
import com.itplayer.core.system.service.RoleService;
import com.itplayer.core.system.service.SystemLogService;
import com.itplayer.manage.system.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description：SystemController<br>
 * @author：xxq<br>
 * @date：2018年3月27日<br>
 */
@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {

    @Autowired
    private SystemLogService systemLogService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("login")
    @ResponseBody
    public ResponseData login(@RequestBody Manager manager) {
        Manager managerDb = managerService.findByUsername(manager.getUsername());
        if (null == managerDb) {
            throw new SystemException("账号不存在");
        }
        String password = manager.getPassword();
        String solt = managerDb.getSalt();
        if (!managerDb.getPassword().equals(AesUtil.getMD5String(solt + password))) {
            throw new SystemException("密码不正确");
        }
        managerDb.setPassword(null);
        managerService.refreshPermission(managerDb);
        SessionUtils.putLoginManager(managerDb);
        return success();
    }

    public static void main(String[] args) {
        System.out.println(AesUtil.getMD5String("abcd" + "123456"));
    }

    @GetMapping(value = "main")
    public String main() {
        return "main";
    }

    @GetMapping(value = "/menu")
    @ResponseBody
    public List<Permission> menu() {
        Manager loginManager = SessionUtils.getLoginManager();
        List<Permission> menus = loginManager.getMenus();
        return menus;
    }

    @GetMapping("/loginOut")
    public String loginOut() {
        SessionUtils.clearLoginManager();
        return "main";
    }
}
