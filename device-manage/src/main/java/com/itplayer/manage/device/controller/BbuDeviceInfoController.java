package com.itplayer.manage.device.controller;

import com.itplayer.core.base.page.PageResult;
import com.itplayer.core.base.web.BaseController;
import com.itplayer.core.base.web.ResponseData;
import com.itplayer.core.device.entity.BbuDeviceInfo;
import com.itplayer.core.device.query.BbuDeviceInfoQueryModel;
import com.itplayer.core.device.service.BbuDeviceInfoService;
import com.itplayer.core.system.log.PermissionValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dev/bbuDeviceInfo")
public class BbuDeviceInfoController extends BaseController {

    @Autowired
    private BbuDeviceInfoService bbuDeviceInfoService;


    @PostMapping("/update")
    @ResponseBody
    @PermissionValidate(code = "Bbu:update")
    public ResponseData update(@RequestBody BbuDeviceInfo bbuDeviceInfo) {
        bbuDeviceInfoService.update(bbuDeviceInfo);
        return success();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    @PermissionValidate(code = "Bbu:del")
    public ResponseData delete(@PathVariable("id") Long id) {
        bbuDeviceInfoService.deleteByPrimaryKey(id);
        return success();
    }

    @GetMapping("/list")
    @ResponseBody
    public List<BbuDeviceInfo> lists() {
        List<BbuDeviceInfo> bbuDeviceInfos = bbuDeviceInfoService.findAll();
        return bbuDeviceInfos;
    }

    @PostMapping("/list")
    @ResponseBody
    public PageResult<BbuDeviceInfo> list( BbuDeviceInfoQueryModel queryModel) {
        PageResult<BbuDeviceInfo> pageResult = bbuDeviceInfoService.queryPage(queryModel);
        return pageResult;
    }
}
