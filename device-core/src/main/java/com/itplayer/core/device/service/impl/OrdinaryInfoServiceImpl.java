package com.itplayer.core.device.service.impl;

import com.itplayer.core.base.exception.SystemException;
import com.itplayer.core.base.service.BaseServiceImpl;
import com.itplayer.core.base.utils.StrUtils;
import com.itplayer.core.device.dao.OrdinaryInfoDao;
import com.itplayer.core.device.entity.Device;
import com.itplayer.core.device.entity.OrdinaryInfo;
import com.itplayer.core.device.service.OrdinaryInfoService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdinaryInfoServiceImpl extends BaseServiceImpl<OrdinaryInfo, Long> implements OrdinaryInfoService {

    OrdinaryInfoDao ordinaryInfoDao;

    @Autowired
    public void setOrdinaryInfoDao(OrdinaryInfoDao ordinaryInfoDao) {
        this.ordinaryInfoDao = ordinaryInfoDao;
        super.setDao(ordinaryInfoDao);
    }

    public void validate(OrdinaryInfo ordinaryInfo) {
        if (StrUtils.isNull(ordinaryInfo.getPort())) {
            throw new SystemException("请填写端口");
        }
        if (StrUtils.isNull(ordinaryInfo.getFiberFramePort())) {
            throw new SystemException("请填写跳纤架位置");
        }
    }

    @Override
    public int create(OrdinaryInfo ordinaryInfo) {
        validate(ordinaryInfo);
        List<OrdinaryInfo> ordinaryInfos = findByEntity(ordinaryInfo);
        if (!ordinaryInfos.isEmpty()) {
            throw new SystemException("端口和跳纤架位置重复！");
        }
        return super.create(ordinaryInfo);
    }

    @Override
    public int update(OrdinaryInfo ordinaryInfo) {
        validate(ordinaryInfo);
        List<OrdinaryInfo> ordinaryInfos = findByEntity(ordinaryInfo);
        if (!ordinaryInfos.isEmpty()) {
            OrdinaryInfo oldOrdinaryInfo = ordinaryInfos.get(0);
            if (!oldOrdinaryInfo.getId().equals(ordinaryInfo.getId())) {
                throw new SystemException("端口和跳纤架位置重复！");
            }
        }
        return super.update(ordinaryInfo);
    }

    @Override
    public HSSFWorkbook export(Device device) {
        //创建工作簿
        HSSFWorkbook workBook = new HSSFWorkbook();
        //创建工作表  工作表的名字叫helloWorld
        HSSFSheet sheet = workBook.createSheet("普通通用设备");
        List<OrdinaryInfo> ordinaryInfos = ordinaryInfoDao.findByDevice(device);
        //创建行,第3行
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("本端设备名称");
        cell = row.createCell(1);
        cell.setCellValue("本端设备编号");
        cell = row.createCell(2);
        cell.setCellValue("本端设备型号");
        cell = row.createCell(3);
        cell.setCellValue("本端所在机架");
        cell = row.createCell(4);
        cell.setCellValue("端口");
        cell = row.createCell(5);
        cell.setCellValue("跳纤架位置");
        cell = row.createCell(6);
        cell.setCellValue("跳纤架子端口");
        cell = row.createCell(7);
        cell.setCellValue("对端设备");
        cell = row.createCell(8);
        cell.setCellValue("对端设备型号");
        cell = row.createCell(9);
        cell.setCellValue("对端设备架框");
        cell = row.createCell(10);
        cell.setCellValue("对端设备物理端口");
        cell = row.createCell(11);
        cell.setCellValue("业务名称");
        int index = 1;
        if (null == ordinaryInfos || ordinaryInfos.size() < 1) {
            return workBook;
        }
        for (int i = 0; i < ordinaryInfos.size(); i++) {
            row = sheet.createRow(index++);
            OrdinaryInfo ordinaryInfo = ordinaryInfos.get(i);
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
            cell.setCellValue(ordinaryInfo.getPort());
            cell = row.createCell(5);
            cell.setCellValue(ordinaryInfo.getFiberFrameAddr());
            cell = row.createCell(6);
            cell.setCellValue(ordinaryInfo.getFiberFramePort());
            cell = row.createCell(7);
            cell.setCellValue(ordinaryInfo.getTargetDevice());
            cell = row.createCell(8);
            cell.setCellValue(ordinaryInfo.getTargetDeviceModel());
            cell = row.createCell(9);
            cell.setCellValue(ordinaryInfo.getTargetFiberFrame());
            cell = row.createCell(10);
            cell.setCellValue(ordinaryInfo.getPhysicalPort());
            cell = row.createCell(11);
            cell.setCellValue(ordinaryInfo.getServiceName());
        }
        return workBook;
    }

    @Override
    public List<OrdinaryInfo> findByDevice(Device device) {
        return ordinaryInfoDao.findByDevice(device);
    }
}
