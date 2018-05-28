package com.itplayer.manage.system.controller;

import com.itplayer.core.base.page.PageResult;
import com.itplayer.core.base.web.BaseController;
import com.itplayer.core.base.web.ResponseData;
import com.itplayer.core.system.entity.Manager;
import com.itplayer.core.system.entity.Role;
import com.itplayer.core.system.log.PermissionValidate;
import com.itplayer.core.system.query.ManagerQueryModel;
import com.itplayer.core.system.service.ManagerService;
import com.itplayer.manage.system.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sys/manager")
public class ManagerController extends BaseController {

    @Autowired
    private ManagerService managerService;


    @PostMapping("/update")
    @ResponseBody
    @PermissionValidate(code = "Manager:update")
//    @PermissionValidate(logType = SystemLogType.BUSSINESS, module = ModuleType.SYSTEM_MANAGER, operationType = OperationType.DELETE, description = "用户新增/编辑")
    public ResponseData update(@RequestBody Manager manager) {
        Long id = manager.getId();
        if (null == id) {
            managerService.create(manager);
        } else {
            managerService.update(manager);
        }
        return success();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    @PermissionValidate(code = "Manager:del")
    public ResponseData delete(@PathVariable("id") Long id) {
        managerService.deleteByPrimaryKey(id);
        return success();
    }

    @GetMapping("/listPage")
    public String listPage() {
        return "/system/manager_list";
    }
    @GetMapping("/info")
    @ResponseBody
    public ResponseData managerInfo() {
        Manager loginManager = SessionUtils.getLoginManager();
        return success(loginManager);
    }

    @PostMapping("/changeInfo")
    @ResponseBody
    @PermissionValidate(code = "Manager:info")
    public ResponseData changeInfo(@RequestBody Manager manager){
        managerService.changeInfo(manager);
        return success();
    }


    @GetMapping("/list")
    @ResponseBody
    public List<Manager> lists() {
        List<Manager> managers = managerService.findAll();
        return managers;
    }

    @PostMapping("/list")
    @ResponseBody
    public PageResult<Manager> list(ManagerQueryModel queryModel) {
        PageResult<Manager> pageResult = managerService.queryPage(queryModel);
        return pageResult;
    }

    @GetMapping("/role/{id}")
    @ResponseBody
    public List<Role> managerRole(@PathVariable("id") Long id) {
        List<Role> roles = managerService.managerRole(id);
        return roles;
    }

    @PostMapping("/role")
    @ResponseBody
    @PermissionValidate(code = "Manager:setRole")
    public ResponseData setRole(@RequestBody Manager manager) {
        managerService.setRole(manager);
        return success();
    }
}
