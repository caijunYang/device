package com.itplayer.core.device.service.impl;

import com.itplayer.core.base.exception.SystemException;
import com.itplayer.core.base.service.BaseServiceImpl;
import com.itplayer.core.base.utils.StrUtils;
import com.itplayer.core.device.dao.BbuDeviceInfoDao;
import com.itplayer.core.device.entity.BbuDeviceInfo;
import com.itplayer.core.device.entity.Device;
import com.itplayer.core.device.entity.OrdinaryInfo;
import com.itplayer.core.device.service.BbuDeviceInfoService;
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
public class BbuDeviceInfoServiceImpl extends BaseServiceImpl<BbuDeviceInfo, Long> implements BbuDeviceInfoService {

    BbuDeviceInfoDao bbuDeviceInfoDao;

    @Autowired
    public void setBbuDeviceInfoDao(BbuDeviceInfoDao bbuDeviceInfoDao) {
        this.bbuDeviceInfoDao = bbuDeviceInfoDao;
        super.setDao(bbuDeviceInfoDao);
    }

    @Override
    public HSSFWorkbook export(Device device) {
        //创建工作簿
        HSSFWorkbook workBook = new HSSFWorkbook();
        //创建工作表  工作表的名字叫helloWorld
        HSSFSheet sheet = workBook.createSheet("普通通用设备");
        List<BbuDeviceInfo> ordinaryInfos = bbuDeviceInfoDao.findByDevice(device);
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
        cell.setCellValue("序列号");
        cell = row.createCell(5);
        cell.setCellValue("端口");
        cell = row.createCell(6);
        cell.setCellValue("跳纤架位置");
        cell = row.createCell(7);
        cell.setCellValue("跳纤架子端口");
        cell = row.createCell(8);
        cell.setCellValue("对端设备");
        cell = row.createCell(9);
        cell.setCellValue("对端设备型号");
        cell = row.createCell(10);
        cell.setCellValue("对端设备架框");
        cell = row.createCell(11);
        cell.setCellValue("对端设备物理端口");
        cell = row.createCell(12);
        cell.setCellValue("业务名称");
        int index = 1;
        if (null == ordinaryInfos || ordinaryInfos.size() < 1) {
            return workBook;
        }
        for (int i = 0; i < ordinaryInfos.size(); i++) {
            row = sheet.createRow(index++);
            BbuDeviceInfo bbuDeviceInfo = ordinaryInfos.get(i);
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
            cell.setCellValue(bbuDeviceInfo.getSerialNo());
            cell = row.createCell(5);
            cell.setCellValue(bbuDeviceInfo.getPort());
            cell = row.createCell(6);
            cell.setCellValue(bbuDeviceInfo.getFiberFrameAddr());
            cell = row.createCell(7);
            cell.setCellValue(bbuDeviceInfo.getFiberFramePort());
            cell = row.createCell(8);
            cell.setCellValue(bbuDeviceInfo.getTargetDevice());
            cell = row.createCell(9);
            cell.setCellValue(bbuDeviceInfo.getTargetDeviceModel());
            cell = row.createCell(10);
            cell.setCellValue(bbuDeviceInfo.getTargetFiberFrame());
            cell = row.createCell(11);
            cell.setCellValue(bbuDeviceInfo.getPhysicalPort());
            cell = row.createCell(12);
            cell.setCellValue(bbuDeviceInfo.getServiceName());
        }
        return workBook;
    }

    @Override
    public List<BbuDeviceInfo> findByDevice(Device device) {
        return bbuDeviceInfoDao.findByDevice(device);
    }

    @Override
    public int create(BbuDeviceInfo bbuDeviceInfo) {
        validate(bbuDeviceInfo);
        List<BbuDeviceInfo> bbuDeviceInfos = findByEntity(bbuDeviceInfo);
        if (!bbuDeviceInfos.isEmpty()) {
            throw new SystemException("已存在同端口位置的信息");
        }
        return super.create(bbuDeviceInfo);
    }

    @Override
    public int update(BbuDeviceInfo bbuDeviceInfo) {
        validate(bbuDeviceInfo);
        List<BbuDeviceInfo> bbuDeviceInfos = findByEntity(bbuDeviceInfo);
        if (!bbuDeviceInfos.isEmpty() && !bbuDeviceInfos.get(0).getId().equals(bbuDeviceInfo.getId())) {
            throw new SystemException("已存在同端口位置的信息");
        }
        return super.update(bbuDeviceInfo);
    }

    private void validate(BbuDeviceInfo bbuDeviceInfo) {
        if (StrUtils.isNull(bbuDeviceInfo.getPort())) {
            throw new SystemException("请填写端口");
        }
        if (StrUtils.isNull(bbuDeviceInfo.getFiberFramePort())) {
            throw new SystemException("请填写跳纤架位置");
        }
        if (StrUtils.isNull(bbuDeviceInfo.getSerialNo())) {
            throw new SystemException("请填写编号");
        }
    }
}
