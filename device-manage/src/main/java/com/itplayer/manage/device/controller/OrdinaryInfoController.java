package com.itplayer.manage.device.controller;

import com.itplayer.core.base.page.PageResult;
import com.itplayer.core.base.web.BaseController;
import com.itplayer.core.base.web.ResponseData;
import com.itplayer.core.device.entity.OrdinaryInfo;
import com.itplayer.core.device.query.OltInfoQueryModel;
import com.itplayer.core.device.service.OrdinaryInfoService;
import com.itplayer.core.system.log.PermissionValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dev/ordinaryInfo")
public class OrdinaryInfoController extends BaseController {

    @Autowired
    private OrdinaryInfoService ordinaryInfoService;


    @PostMapping("/update")
    @ResponseBody
    @PermissionValidate(code = "Ordinary:update")
    public ResponseData update(@RequestBody OrdinaryInfo ordinaryInfo) {
        ordinaryInfoService.update(ordinaryInfo);
        return success();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    @PermissionValidate(code = "Ordinary:del")
    public ResponseData delete(@PathVariable("id") Long id) {
        ordinaryInfoService.deleteByPrimaryKey(id);
        return success();
    }

    @GetMapping("/list")
    @ResponseBody
    public List<OrdinaryInfo>  lists() {
        List<OrdinaryInfo> ordinaryInfos = ordinaryInfoService.findAll();
        return ordinaryInfos;
    }
    @PostMapping("/list")
    @ResponseBody
    public PageResult<OrdinaryInfo> list( OltInfoQueryModel queryModel) {
        PageResult<OrdinaryInfo> pageResult = ordinaryInfoService.queryPage(queryModel);
        return pageResult;
    }
}
