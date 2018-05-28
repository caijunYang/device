package com.itplayer.manage.device.controller;

import com.itplayer.core.base.page.PageResult;
import com.itplayer.core.base.web.BaseController;
import com.itplayer.core.base.web.ResponseData;
import com.itplayer.core.device.entity.EngineRoom;
import com.itplayer.core.device.query.EngineRoomQueryModel;
import com.itplayer.core.device.service.EngineRoomService;
import com.itplayer.core.system.log.PermissionValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dev/engineRoom")
public class EngineRoomController extends BaseController {

    @Autowired
    private EngineRoomService engineRoomService;


    @PostMapping("/update")
    @ResponseBody
    @PermissionValidate(code = "DeviceRoom:update")
    public ResponseData update(@RequestBody EngineRoom engineRoom) {
        if (engineRoom.getId() == null) {
            engineRoomService.create(engineRoom);
        } else {
            engineRoomService.update(engineRoom);
        }
        return success();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    @PermissionValidate(code = "DeviceRoom:del")
    public ResponseData delete(@PathVariable("id") Long id) {
        engineRoomService.deleteByPrimaryKey(id);
        return success();
    }

    @GetMapping("/listPage")
    public String listPage() {
        return "/device/engineRoom_list";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<EngineRoom> lists() {
        List<EngineRoom> engineRooms = engineRoomService.findAll();
        return engineRooms;
    }

    @PostMapping("/list")
    @ResponseBody
    public PageResult<EngineRoom> list(EngineRoomQueryModel queryModel) {
        PageResult<EngineRoom> pageResult = engineRoomService.queryPage(queryModel);
        return pageResult;
    }
}
