package com.itplayer.manage.system.controller;

import com.itplayer.core.base.page.PageResult;
import com.itplayer.core.base.web.BaseController;
import com.itplayer.core.base.web.ResponseData;
import com.itplayer.core.system.entity.SystemLog;
import com.itplayer.core.system.log.PermissionValidate;
import com.itplayer.core.system.query.SystemLogQueryModel;
import com.itplayer.core.system.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sys/systemLog")
public class SystemLogController extends BaseController {

    @Autowired
    private SystemLogService systemLogService;


    @PostMapping("/update")
    @ResponseBody
    @PermissionValidate(code = "33")
    public ResponseData update(@RequestBody SystemLog systemLog) {
        Long id = systemLog.getId();
        if (null == id) {
            systemLogService.create(systemLog);
        } else {
            systemLogService.update(systemLog);
        }
        return success();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public ResponseData delete(@PathVariable("id") Long id) {
        systemLogService.deleteByPrimaryKey(id);
        return success();
    }

    @GetMapping("/listPage")
    public String listPage() {
        return "/system/systemLog_list";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<SystemLog> lists() {
        List<SystemLog> managers = systemLogService.findAll();
        return managers;
    }

    @PostMapping("/list")
    @ResponseBody
    public PageResult<SystemLog> list(SystemLogQueryModel queryModel) {
        PageResult<SystemLog> pageResult = systemLogService.queryPage(queryModel);
        return pageResult;
    }
}
