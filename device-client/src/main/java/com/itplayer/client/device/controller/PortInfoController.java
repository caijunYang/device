package com.itplayer.client.device.controller;

import com.itplayer.core.base.web.BaseController;
import com.itplayer.core.base.web.ResponseData;
import com.itplayer.core.device.entity.Device;
import com.itplayer.core.device.entity.dto.PortInfo;
import com.itplayer.core.device.service.DeviceService;
import com.itplayer.core.device.service.PortInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by caijun.yang on 2018/5/26
 */
@RestController
@RequestMapping("/dev/portInfo")
public class PortInfoController extends BaseController {
    @Autowired
    private PortInfoService portInfoService;


    @PostMapping("/reportPortInfo")
    public ResponseData reportPortInfo(@RequestBody PortInfo portInfo) {
        try {
            portInfoService.reportPortInfo(portInfo);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return faild(e.getMessage());
        }
    }

}
