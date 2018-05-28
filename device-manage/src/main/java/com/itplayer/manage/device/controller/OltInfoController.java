package com.itplayer.manage.device.controller;

import com.itplayer.core.base.page.PageResult;
import com.itplayer.core.base.web.BaseController;
import com.itplayer.core.base.web.ResponseData;
import com.itplayer.core.device.entity.OltInfo;
import com.itplayer.core.device.query.OltInfoQueryModel;
import com.itplayer.core.device.service.OltInfoService;
import com.itplayer.core.system.log.PermissionValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dev/oltInfo")
public class OltInfoController extends BaseController {

    @Autowired
    private OltInfoService oltInfoService;



    @PostMapping("/update")
    @ResponseBody
    @PermissionValidate(code = "Olt:update")
    public ResponseData update(@RequestBody OltInfo oltInfo) {
        oltInfoService.update(oltInfo);
        return success();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    @PermissionValidate(code = "Olt:del")
    public ResponseData delete(@PathVariable("id") Long id) {
        oltInfoService.deleteByPrimaryKey(id);
        return success();
    }

    @GetMapping("/list")
    @ResponseBody
    public List<OltInfo> lists() {
        List<OltInfo> oltInfos = oltInfoService.findAll();
        return oltInfos;
    }

    @PostMapping("/list")
    @ResponseBody
    public PageResult<OltInfo> list( OltInfoQueryModel queryModel) {
        PageResult<OltInfo> pageResult = oltInfoService.queryPage(queryModel);
        return pageResult;
    }
}
