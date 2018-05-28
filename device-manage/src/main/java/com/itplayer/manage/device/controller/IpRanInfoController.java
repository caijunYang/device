package com.itplayer.manage.device.controller;

import com.itplayer.core.base.page.PageResult;
import com.itplayer.core.base.web.BaseController;
import com.itplayer.core.base.web.ResponseData;
import com.itplayer.core.device.entity.IpRanInfo;
import com.itplayer.core.device.query.IpRanInfoQueryModel;
import com.itplayer.core.device.service.IpRanInfoService;
import com.itplayer.core.system.log.PermissionValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dev/ipRanInfo")
public class IpRanInfoController extends BaseController {

    @Autowired
    private IpRanInfoService ipRanInfoService;


    @PostMapping("/update")
    @ResponseBody
    @PermissionValidate(code = "IpRan:update")
    public ResponseData update(@RequestBody IpRanInfo ipRanInfo) {
        ipRanInfoService.update(ipRanInfo);
        return success();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    @PermissionValidate(code = "IpRan:del")
    public ResponseData delete(@PathVariable("id") Long id) {
        ipRanInfoService.deleteByPrimaryKey(id);
        return success();
    }

    @GetMapping("/list")
    @ResponseBody
    public List<IpRanInfo> lists() {
        List<IpRanInfo> ipRanInfos = ipRanInfoService.findAll();
        return ipRanInfos;
    }

    @PostMapping("/list")
    @ResponseBody
    public PageResult<IpRanInfo> list( IpRanInfoQueryModel queryModel) {
        PageResult<IpRanInfo> pageResult = ipRanInfoService.queryPage(queryModel);
        return pageResult;
    }
}
