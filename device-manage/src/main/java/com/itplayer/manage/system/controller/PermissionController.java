package com.itplayer.manage.system.controller;

import com.itplayer.core.base.enums.PermissionType;
import com.itplayer.core.base.page.PageResult;
import com.itplayer.core.base.web.BaseController;
import com.itplayer.core.base.web.ResponseData;
import com.itplayer.core.system.entity.Permission;
import com.itplayer.core.system.query.PermissionQueryModel;
import com.itplayer.core.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sys/permission")
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/add")
    @ResponseBody
    public ResponseData create(@RequestBody Permission permission) {
        permissionService.create(permission);
        return success();
    }

    @PostMapping("/udpate")
    @ResponseBody
    public ResponseData update(@RequestBody Permission permission) {
        permissionService.update(permission);
        return success();
    }
    @GetMapping("/listPage")
    public String listPage() {
        return "/system/permission_list";
    }
    @GetMapping("/delete/{id}")
    @ResponseBody
    public ResponseData delete(@PathVariable("id") Long id) {
        permissionService.deleteByPrimaryKey(id);
        return success();
    }

    @GetMapping("/list")
    @ResponseBody
    public ResponseData lists() {
        List<Permission> areas = permissionService.findAll();
        return success(areas);
    }

    @PostMapping("/list")
    @ResponseBody
    public PageResult<Permission> list(PermissionQueryModel queryModel) {
        PageResult<Permission> pageResult = permissionService.queryPage(queryModel);
        return pageResult;
    }
    @PostMapping("/allMenue")
    @ResponseBody
    public PageResult<Permission> allMenue(PermissionQueryModel queryModel) {
        queryModel.setPermissionType(PermissionType.MENU);
        PageResult<Permission> pageResult = permissionService.queryPage(queryModel);
        return pageResult;
    }
}
