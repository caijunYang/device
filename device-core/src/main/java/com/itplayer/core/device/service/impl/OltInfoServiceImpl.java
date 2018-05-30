package com.itplayer.core.device.service.impl;

import com.itplayer.core.base.exception.SystemException;
import com.itplayer.core.base.service.BaseServiceImpl;
import com.itplayer.core.base.utils.StrUtils;
import com.itplayer.core.device.dao.OltInfoDao;
import com.itplayer.core.device.entity.BbuDeviceInfo;
import com.itplayer.core.device.entity.Device;
import com.itplayer.core.device.entity.OltInfo;
import com.itplayer.core.device.service.OltInfoService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by caijun.yang on 2018/4/17
 */
@Service
public class OltInfoServiceImpl extends BaseServiceImpl<OltInfo, Long> implements OltInfoService {

    OltInfoDao oltInfoDao;

    @Autowired
    public void setOltInfoDao(OltInfoDao oltInfoDao) {
        this.oltInfoDao = oltInfoDao;
        super.setDao(oltInfoDao);
    }
    public void validate(OltInfo oltInfo) {
        if (StrUtils.isNull(oltInfo.getPort())) {
            throw new SystemException("请填写端口");
        }
        if (StrUtils.isNull(oltInfo.getFiberFramePort())) {
            throw new SystemException("请填写跳纤架位置");
        }
        if (StrUtils.isNull(oltInfo.getTargetDevice())) {
            throw new SystemException("请填写出局ODF架");

        }
        if (StrUtils.isNull(oltInfo.getPhysicalPort())) {
            throw new SystemException("请填写ODF架框槽端子");

        }

    }

    @Override
    public int create(OltInfo oltInfo) {
        validate(oltInfo);
        List<OltInfo> oltInfos = findByEntity(oltInfo);
        if(!oltInfos.isEmpty()){
            throw new SystemException("已存在同端口位置的信息");
        }
        return super.create(oltInfo);
    }

    @Override
    public int update(OltInfo oltInfo) {
        validate(oltInfo);
        List<OltInfo> oltInfos = findByEntity(oltInfo);
        if(!oltInfos.isEmpty()&&oltInfos.get(0).getId().equals(oltInfo.getId())){
            throw new SystemException("已存在同端口位置的信息");
        }
        return super.update(oltInfo);
    }

    @Override
    public HSSFWorkbook export(Device device) {
        //创建工作簿
        HSSFWorkbook workBook = new HSSFWorkbook();
        //创建工作表  工作表的名字叫helloWorld
        HSSFSheet sheet = workBook.createSheet("普通通用设备");
        List<OltInfo> oltInfos = oltInfoDao.findByDevice(device);
        //创建行,第3行
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("本端设备名称");
        cell = row.createCell(1);
        cell.setCellValue("本端设备端口");
        cell = row.createCell(2);
        cell.setCellValue("本端设备型号");
        cell = row.createCell(3);
        cell.setCellValue("本端所在机架");

        cell = row.createCell(4);
        cell.setCellValue("槽位/槽口/端口");
        cell = row.createCell(5);
        cell.setCellValue("业务标签");
        cell = row.createCell(6);
        cell.setCellValue("对应跳纤架（ODF）");
        cell = row.createCell(7);
        cell.setCellValue("跳纤架框槽端子（ODFB面）");
        cell = row.createCell(8);
        cell.setCellValue("出局ODF架/对端设备");
        cell = row.createCell(9);
        cell.setCellValue("物理端口 ");
        cell = row.createCell(10);
        cell.setCellValue("光缆名称");
        cell = row.createCell(11);
        cell.setCellValue("纤芯占用");
        cell = row.createCell(12);
        cell.setCellValue("标签/人工");
        int index = 1;
        if (null == oltInfos || oltInfos.size() < 1) {
            return workBook;
        }
        for (int i = 0; i < oltInfos.size(); i++) {
            row = sheet.createRow(index++);
            OltInfo oltInfo = oltInfos.get(i);
            if (i == 0) {
                cell = row.createCell(0);
                cell.setCellValue(device.getDeviceName());
                cell = row.createCell(1);
                cell.setCellValue(device.getDeviceCode());
                cell = row.createCell(2);
                cell.setCellValue(device.getDeviceModel());
                cell = row.createCell(3);
                cell.setCellValue(device.getDeviceFrame());
            }
            cell = row.createCell(4);
            cell.setCellValue(oltInfo.getPort());
            cell = row.createCell(5);
            cell.setCellValue(oltInfo.getServiceName());
            cell = row.createCell(6);
            cell.setCellValue(oltInfo.getFiberFrameAddr());
            cell = row.createCell(7);
            cell.setCellValue(oltInfo.getFiberFramePort());
            cell = row.createCell(8);
            cell.setCellValue(oltInfo.getTargetDevice());
            cell = row.createCell(9);
            cell.setCellValue(oltInfo.getPhysicalPort());
            cell = row.createCell(10);
            cell.setCellValue(oltInfo.getOpticalName());
            cell = row.createCell(11);
            cell.setCellValue(oltInfo.getOpticalCore());
            cell = row.createCell(12);
            cell.setCellValue(oltInfo.getLable());
        }
        return workBook;
    }

    @Override
    public List<OltInfo>  findByDevice(Device device) {
        return oltInfoDao.findByDevice(device);
    }
}
