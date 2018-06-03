package com.itplayer.client.device.controller;

import com.itplayer.core.base.enums.DeviceType;
import com.itplayer.core.base.exception.SystemException;
import com.itplayer.core.base.page.PageResult;
import com.itplayer.core.base.web.BaseController;
import com.itplayer.core.base.web.ResponseData;
import com.itplayer.core.device.entity.*;
import com.itplayer.core.device.query.DeviceQueryModel;
import com.itplayer.core.device.query.EngineRoomQueryModel;
import com.itplayer.core.device.service.*;
import com.itplayer.core.system.entity.Area;
import com.itplayer.core.system.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dev/device")
public class DeviceController extends BaseController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private EngineRoomService engineRoomService;

    @Autowired
    private BbuDeviceInfoService bbuDeviceInfoService;

    @Autowired
    private OrdinaryInfoService ordinaryInfoService;

    @Autowired
    private OltInfoService oltInfoService;
    @Autowired
    private IpRanInfoService ipRanInfoService;

    @Autowired
    private AreaService areaService;

    @PostMapping("/reportDevice")
    public ResponseData reportDevice(@RequestBody Device device) {
        try {
            deviceService.reportDevice(device);
            return success();
        } catch (Exception e) {
            return success(e.getMessage());
        }
    }


    @GetMapping("/queryRoom/{areaId}")
    public ResponseData queryRoomByArea(@PathVariable("areaId") Long areaId) {
        EngineRoomQueryModel queryModel = new EngineRoomQueryModel();
        queryModel.setArea_id(areaId);
        queryModel.setPageSize(5000);
        PageResult<EngineRoom> engineRoomPageResult = engineRoomService.queryPage(queryModel);
        return success(engineRoomPageResult.getRows());
    }

    @GetMapping("/queryDevice/{roomId}")
    public ResponseData queryDeviceByRoom(@PathVariable("roomId") Long roomId) {
        DeviceQueryModel queryModel = new DeviceQueryModel();
        queryModel.setPageSize(5000);
        queryModel.setEngineRoom_id(roomId);
        PageResult<Device> devicePageResult = deviceService.queryPage(queryModel);
        return success(devicePageResult.getRows());
    }

    @GetMapping("/queryDeviceInfo/{deviceId}")
    public ResponseData queryDeviceInfo(@PathVariable("deviceId") Long deviceId) {
        Device device = deviceService.getByPrimaryKey(deviceId);
        if (null == device) {
            throw new SystemException("设备不存在");
        }
        DeviceType deviceType = device.getDeviceType();
        if (deviceType == null) {
            return faild("设备信息异常,未识别的设备类型");
        }

        switch (deviceType) {
            case ORDINARY:
                List<OrdinaryInfo> ordinaryInfos = ordinaryInfoService.findByDevice(device);
                device.setDeviceInfos(ordinaryInfos);
                break;
            case IPRAN:
                List<IpRanInfo> ipRanInfos = ipRanInfoService.findByDevice(device);
                device.setDeviceInfos(ipRanInfos);
                break;
            case OLT:
                List<OltInfo> oltInfos = oltInfoService.findByDevice(device);
                device.setDeviceInfos(oltInfos);
                break;
            case BBU:
                List<BbuDeviceInfo> bbuDeviceInfos = bbuDeviceInfoService.findByDevice(device);
                device.setDeviceInfos(bbuDeviceInfos);
                break;
        }
        return success(device);
    }

    @GetMapping("/areas")
    @ResponseBody
    public ResponseData areas() {
        List<Area> areas = areaService.findAll();
        return success(areas);
    }
}
