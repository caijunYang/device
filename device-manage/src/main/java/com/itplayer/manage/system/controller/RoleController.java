package com.itplayer.manage.system.controller;

import com.itplayer.core.base.page.PageResult;
import com.itplayer.core.base.web.BaseController;
import com.itplayer.core.base.web.ResponseData;
import com.itplayer.core.system.entity.Role;
import com.itplayer.core.system.log.PermissionValidate;
import com.itplayer.core.system.query.RoleQueryModel;
import com.itplayer.core.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sys/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/update")
    @ResponseBody

    @PermissionValidate(code = "Role:update")
    public ResponseData update(@RequestBody Role role) {
        if (role.getId() != null) {

            roleService.update(role);
        } else {
            roleService.create(role);
        }
        return success();
    }

    @GetMapping("/delete/{id}")
    @PermissionValidate(code = "Role:del")
    public ResponseData delete(@PathVariable("id") Long id) {
        roleService.deleteByPrimaryKey(id);
        return success();
    }

    @GetMapping("/listPage")
    public String listPage() {
        return "/system/role_list";
    }

    @GetMapping("/list")
    @ResponseBody
    public ResponseData lists() {
        List<Role> areas = roleService.findAll();
        return success(areas);
    }

    @PostMapping("/list")
    @ResponseBody
    public PageResult<Role> list( RoleQueryModel queryModel) {
        PageResult<Role> pageResult = roleService.queryPage(queryModel);
        return pageResult;
    }
}
