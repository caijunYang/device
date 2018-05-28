package com.itplayer.manage.device.controller;

import com.itplayer.core.base.exception.SystemException;
import com.itplayer.core.base.page.PageResult;
import com.itplayer.core.base.web.BaseController;
import com.itplayer.core.base.web.ResponseData;
import com.itplayer.core.device.entity.Device;
import com.itplayer.core.device.query.DeviceQueryModel;
import com.itplayer.core.device.service.DeviceService;
import com.itplayer.core.system.log.PermissionValidate;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/dev/device")
public class DeviceController extends BaseController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/listPage")
    public String listPage() {
        return "/device/device_list";
    }

    @PostMapping("/update")
    @ResponseBody
    @PermissionValidate(code = "Device:update")
    public ResponseData update(@RequestBody Device device) {
        if (device.getId() == null) {
            deviceService.create(device);
        } else {
            deviceService.update(device);
        }
        return success();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    @PermissionValidate(code = "Device:del")
    public ResponseData delete(@PathVariable("id") Long id) {
        deviceService.deleteByPrimaryKey(id);
        return success();
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Device> lists() {
        List<Device> devices = deviceService.findAll();
        return devices;
    }

    @PostMapping("/list")
    @ResponseBody
    public PageResult<Device> list(DeviceQueryModel queryModel) {
        PageResult<Device> pageResult = deviceService.queryPage(queryModel);
        return pageResult;
    }

    @PostMapping("/importData")
    @ResponseBody
    @PermissionValidate(code = "Device:import")
    public ResponseData importData(@RequestParam("file") MultipartFile file, String productSerNum) {
        String name = file.getOriginalFilename();
        if (!name.endsWith(".xlsx") && !name.endsWith(".xls")) {
            throw new SystemException("文件格式不正确");
        }
        try {
            deviceService.improt(file, productSerNum);
        } catch (Exception e) {
            return faild(e.getMessage());
        }
        return success();
    }

    @GetMapping("/downloadTemplate")
    @PermissionValidate(code = "Device:export")
    public ResponseEntity<byte[]> downloadTemplate(HttpServletRequest request) throws IOException {
        String contextPath = request.getServletContext().getRealPath("/files");
        File file = new File(contextPath + "/DeviceImportTemplate.xlsx");
        HttpHeaders headers = new HttpHeaders();
        String fileName = new String("DeviceImportTemplate.xlsx".getBytes("UTF-8"), "iso-8859-1");
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "export/{id}", method = RequestMethod.GET)
    public void exportCards(@PathVariable("id") Long id, HttpServletResponse response)
            throws Exception {

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + "Device.xls");
        ServletOutputStream outputStream = response.getOutputStream();
        try {

            HSSFWorkbook workbook = deviceService.exportCard(id);
            workbook.write(outputStream);
        } catch (Exception e) {
            throw new SystemException("导出失败！");
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }
}
