package com.itplayer.core.device.service.impl;

import com.itplayer.core.base.exception.SystemException;
import com.itplayer.core.base.service.BaseServiceImpl;
import com.itplayer.core.base.utils.StrUtils;
import com.itplayer.core.device.dao.IpRanInfoDao;
import com.itplayer.core.device.entity.BbuDeviceInfo;
import com.itplayer.core.device.entity.Device;
import com.itplayer.core.device.entity.IpRanInfo;
import com.itplayer.core.device.service.IpRanInfoService;
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
public class IpRanInfoServiceImpl extends BaseServiceImpl<IpRanInfo, Long> implements IpRanInfoService {

    IpRanInfoDao ipRanInfoDao;

    @Autowired
    public void setIpRanInfoDao(IpRanInfoDao ipRanInfoDao) {
        this.ipRanInfoDao = ipRanInfoDao;
        super.setDao(ipRanInfoDao);
    }

    public void validate(IpRanInfo ipRanInfo) {
        if (StrUtils.isNull(ipRanInfo.getPort())) {
            throw new SystemException("请填写端口");
        }
        if (StrUtils.isNull(ipRanInfo.getFiberFramePort())) {
            throw new SystemException("请填写跳纤架端口");
        }
    }

    @Override
    public int create(IpRanInfo ipRanInfo) {
        validate(ipRanInfo);
        List<IpRanInfo> ipRanInfos = findByEntity(ipRanInfo);
        if (!ipRanInfos.isEmpty()) {
            throw new SystemException("已存在同端口位置的信息");
        }
        return super.create(ipRanInfo);
    }

    @Override
    public int update(IpRanInfo ipRanInfo) {
        validate(ipRanInfo);
        List<IpRanInfo> ipRanInfos = findByEntity(ipRanInfo);
        if (!ipRanInfos.isEmpty() && !ipRanInfos.get(0).getId().equals(ipRanInfo.getId())) {
            throw new SystemException("已存在同端口位置的信息");
        }
        return super.update(ipRanInfo);
    }

    @Override
    public HSSFWorkbook export(Device device) {
        //创建工作簿
        HSSFWorkbook workBook = new HSSFWorkbook();
        //创建工作表  工作表的名字叫helloWorld
        HSSFSheet sheet = workBook.createSheet("普通通用设备");
        List<IpRanInfo> ipRanInfos = ipRanInfoDao.findByDevice(device);
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
        cell.setCellValue("SN码， ipran独有");
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
        if (null == ipRanInfos || ipRanInfos.size() < 1) {
            return workBook;
        }
        for (int i = 0; i < ipRanInfos.size(); i++) {
            row = sheet.createRow(index++);
            IpRanInfo ipRanInfo = ipRanInfos.get(i);
            if (i == 0) {
                cell = row.createCell(0);
                cell.setCellValue(device.getDeviceName());
                cell = row.createCell(1);
                cell.setCellValue(device.getDeviceCode());
                cell = row.createCell(2);
                cell.setCellValue(device.getDeviceModel());
                cell = row.createCell(3);
                cell.setCellValue(device.getDeviceFrame());
                cell = row.createCell(4);
                cell.setCellValue(device.getSnCode());
            }
            cell = row.createCell(5);
            cell.setCellValue(ipRanInfo.getPort());
            cell = row.createCell(6);
            cell.setCellValue(ipRanInfo.getFiberFrameAddr());
            cell = row.createCell(7);
            cell.setCellValue(ipRanInfo.getFiberFramePort());
            cell = row.createCell(8);
            cell.setCellValue(ipRanInfo.getTargetDevice());
            cell = row.createCell(9);
            cell.setCellValue(ipRanInfo.getTargetDeviceModel());
            cell = row.createCell(10);
            cell.setCellValue(ipRanInfo.getTargetFiberFrame());
            cell = row.createCell(11);
            cell.setCellValue(ipRanInfo.getPhysicalPort());
            cell = row.createCell(12);
            cell.setCellValue(ipRanInfo.getServiceName());
        }
        return workBook;
    }

    @Override
    public List<IpRanInfo> findByDevice(Device device) {
        return ipRanInfoDao.findByDevice(device);
    }
}
