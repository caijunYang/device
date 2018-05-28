package com.itplayer.core.device.service.impl;

import com.itplayer.core.base.enums.DeviceType;
import com.itplayer.core.base.exception.SystemException;
import com.itplayer.core.base.page.PageResult;
import com.itplayer.core.base.service.BaseServiceImpl;
import com.itplayer.core.device.dao.DeviceDao;
import com.itplayer.core.device.entity.Device;
import com.itplayer.core.device.entity.EngineRoom;
import com.itplayer.core.device.entity.OltInfo;
import com.itplayer.core.device.query.DeviceQueryModel;
import com.itplayer.core.device.service.*;
import com.itplayer.core.device.service.dto.ImportDataDto;
import com.itplayer.core.system.entity.Area;
import com.itplayer.core.system.service.AreaService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class DeviceServiceImpl extends BaseServiceImpl<Device, Long> implements DeviceService {

    private DeviceDao deviceDao;

    @Autowired
    private EngineRoomService engineRoomService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private OrdinaryInfoService ordinaryInfoService;

    @Autowired
    private OltInfoService oltInfoService;

    @Autowired
    private BbuDeviceInfoService bbuDeviceInfoService;

    @Autowired
    private IpRanInfoService ipRanInfoService;

    @Autowired
    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
        super.setDao(deviceDao);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void improt(MultipartFile file, String deviceName) {
        Date date = new Date();
        Workbook book;
        try {
            book = new HSSFWorkbook(file.getInputStream());
        } catch (Exception e) {
            try {
                book = new XSSFWorkbook(file.getInputStream());
            } catch (Exception e2) {
                throw new SystemException("文件解析失败，请检查文档格式，或下载文档模板录入");
            }
        }
        if (null == book) {
            throw new SystemException("文件解析失败，请检查文档格式，或下载文档模板录入");
        }
        Sheet sheetAt = book.getSheetAt(0);
        int lastRowNum = sheetAt.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            ImportDataDto importDataDto = new ImportDataDto();
            Row row = sheetAt.getRow(i);
            if (row == null) {
                continue;
            }
            Cell cell0 = row.getCell(0);
            if (cell0 != null) {
                cell0.setCellType(Cell.CELL_TYPE_STRING);
                String cardSer = row.getCell(0).getStringCellValue();
                importDataDto.setDeviceCode(cardSer);
            }
            Cell cell = row.getCell(1);
            if (cell != null) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String stringCellValue = cell.getStringCellValue();
                importDataDto.setAreaCode(stringCellValue);
            }
            Cell cel2 = row.getCell(2);
            if (cel2 != null) {
                cel2.setCellType(Cell.CELL_TYPE_STRING);
                String stringCellValue = cel2.getStringCellValue();
                importDataDto.setRoomCode(stringCellValue);
            }
            Cell cel3 = row.getCell(3);
            if (cel3 != null) {
                cel3.setCellType(Cell.CELL_TYPE_STRING);
                String stringCellValue = cel3.getStringCellValue();
                importDataDto.setDeviceName(stringCellValue);
            }
            Cell cel4 = row.getCell(4);
            if (cel4 != null) {
                cel4.setCellType(Cell.CELL_TYPE_STRING);
                String stringCellValue = cel4.getStringCellValue();
                importDataDto.setDeviceModel(stringCellValue);
            }
            Cell cel5 = row.getCell(5);
            if (cel5 != null) {
                cel5.setCellType(Cell.CELL_TYPE_STRING);
                String stringCellValue = cel5.getStringCellValue();
                importDataDto.setDeviceFrame(stringCellValue);
            }
            Cell cel6 = row.getCell(6);
            if (cel6 != null) {
                cel6.setCellType(Cell.CELL_TYPE_STRING);
                String stringCellValue = cel6.getStringCellValue();
                if (null != stringCellValue) {
                    DeviceType deviceType = DeviceType.valueOf(stringCellValue);
                    importDataDto.setDeviceType(deviceType);
                }
            }
            Cell cel7 = row.getCell(7);
            if (cel7 != null) {
                cel7.setCellType(Cell.CELL_TYPE_STRING);
                String stringCellValue = cel7.getStringCellValue();
                importDataDto.setSnCode(stringCellValue);
            }
            Device device = importRowDataToDevice(importDataDto);
            create(device);

        }
    }

    @Override
    public HSSFWorkbook exportCard(Long id) {
        Device device = getByPrimaryKey(id);
        DeviceType deviceType = device.getDeviceType();
        HSSFWorkbook export = null;
        switch (deviceType) {
            case ORDINARY:
                export = ordinaryInfoService.export(device);
                break;
            case OLT:
                export = oltInfoService.export(device);
                break;
            case BBU:
                export = bbuDeviceInfoService.export(device);
                break;
            case IPRAN:
                export = ipRanInfoService.export(device);
                break;
            default:
                break;
        }
        return export;
    }


    private Device importRowDataToDevice(ImportDataDto rowData) {
        Device device = new Device();
        device.setDeviceCode(rowData.getDeviceCode());
        device.setDeviceName(rowData.getDeviceName());
        device.setDeviceModel(rowData.getDeviceModel());
        device.setSnCode(rowData.getSnCode());
        device.setDeviceType(rowData.getDeviceType());
        device.setDeviceFrame(rowData.getDeviceFrame());

        String areaCode = rowData.getAreaCode();
        String roomCode1 = rowData.getRoomCode();
        EngineRoom engineRoom = engineRoomService.findByCode(roomCode1);
        if (null == engineRoom) {
            engineRoom = new EngineRoom();
            Area area = areaService.findByAreaCode(areaCode);
            if (null == area) {
                throw new SystemException("区域不存在");
            }
            engineRoom.setArea(area);
            engineRoom.setRoomCode(roomCode1);
            engineRoom.setDeviceRoomName(roomCode1);
            engineRoomService.create(engineRoom);
        }
        device.setEngineRoom(engineRoom);
        device.setArea(engineRoom.getArea());
        return device;
    }

    @Override
    public int create(Device device) {
        genArea(device);
        DeviceQueryModel queryModel = new DeviceQueryModel();
        queryModel.setArea_id(device.getArea().getId());
        queryModel.setEngineRoom_id(device.getEngineRoom().getId());
        queryModel.setDeviceCode(device.getDeviceCode());
        Long count = deviceDao.count(queryModel);
        if (count != null && count > 0) {
            throw new SystemException("区域[" + device.getArea().getAreaCode() + "],机房[" + device.getEngineRoom().getRoomCode() + "]下编号为[" + device.getDeviceCode() + "]的设备已存在");
        }
        return super.create(device);
    }

    @Override
    public int update(Device device) {
        Long id = device.getId();
        Device deviceDb = getByPrimaryKey(id);
        if(null==deviceDb){
            throw new SystemException("设备信息不存在!");
        }
        if (!deviceDb.getDeviceCode().equals(device.getDeviceCode())) {
            throw new SystemException("设备编号不能修改");
        }
        genArea(device);
        return super.update(device);
    }

    private void genArea(Device device) {
        EngineRoom engineRoom = device.getEngineRoom();
        if (engineRoom != null) {
            Area area = engineRoom.getArea();
            if (area == null) {
                engineRoom = engineRoomService.getByPrimaryKey(engineRoom.getId());
                area = engineRoom.getArea();
            }
            if (area != null) {
                device.setArea(area);
            }
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reportDevice(Device device) {
        Long id = device.getId();
//        DeviceType deviceType = device.getDeviceType();
        if (null != id) {
//            Device deviceInDb = getByPrimaryKey(id);
//            deviceType = deviceInDb.getDeviceType();
            update(device);
        }
    }


}
