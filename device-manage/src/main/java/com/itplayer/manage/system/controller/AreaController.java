package com.itplayer.manage.system.controller;

import com.itplayer.core.base.page.PageResult;
import com.itplayer.core.base.web.BaseController;
import com.itplayer.core.base.web.ResponseData;
import com.itplayer.core.system.entity.Area;
import com.itplayer.core.system.log.PermissionValidate;
import com.itplayer.core.system.query.AreaQueryModel;
import com.itplayer.core.system.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sys/area")
public class AreaController extends BaseController {

    @Autowired
    private AreaService areaService;


    @PostMapping("/update")
    @ResponseBody
    @PermissionValidate(code = "Area:update")
    public ResponseData update(@RequestBody Area area) {
        if(area.getId()!=null){

        areaService.update(area);
        }else{
            areaService.create(area);
        }
        return success();
    }

    @GetMapping("/listPage")
    public String listPage() {
        return "/system/area_list";
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    @PermissionValidate(code = "Area:del")
    public ResponseData delete(@PathVariable("id") Long id) {
        areaService.deleteByPrimaryKey(id);
        return success();
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Area> lists() {
        List<Area> areas = areaService.findAll();
        return areas;
    }

    @PostMapping("/list")
    @ResponseBody
    public PageResult<Area> list(AreaQueryModel queryModel) {
        PageResult<Area> pageResult = areaService.queryPage(queryModel);
        return pageResult;
    }
}
