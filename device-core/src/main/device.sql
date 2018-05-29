/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : device

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2018-05-28 06:30:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dev_bbudeviceinfo
-- ----------------------------
DROP TABLE IF EXISTS `dev_bbudeviceinfo`;
CREATE TABLE `dev_bbudeviceinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `device_id` bigint(20) DEFAULT NULL,
  `port` varchar(50) DEFAULT NULL,
  `serialNo` int(11) DEFAULT NULL,
  `fiberFrameAddr` varchar(255) DEFAULT NULL,
  `fiberFramePort` varchar(255) DEFAULT NULL,
  `targetDevice` varchar(255) DEFAULT NULL,
  `targetDeviceModel` varchar(255) DEFAULT NULL,
  `targetFiberFrame` varchar(255) DEFAULT NULL,
  `physicalPort` varchar(255) DEFAULT NULL,
  `serviceName` varchar(255) DEFAULT NULL,
  `context` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dev_bbudeviceinfo
-- ----------------------------
INSERT INTO `dev_bbudeviceinfo` VALUES ('2', null, null, '3','2', '1', '324234', null, null, null, null, null, null, null);
INSERT INTO `dev_bbudeviceinfo` VALUES ('3', null, null, '3','2', '1', '324234', null, null, null, null, null, null, null);
INSERT INTO `dev_bbudeviceinfo` VALUES ('4', null, null, '3','2', '1', '324234', null, null, null, null, null, null, null);
INSERT INTO `dev_bbudeviceinfo` VALUES ('5', null, null, '3','2', '1', '324234', null, null, null, null, null, null, null);
INSERT INTO `dev_bbudeviceinfo` VALUES ('6', null, null, '3','2', '1', '324234', null, null, null, null, null, null, null);
INSERT INTO `dev_bbudeviceinfo` VALUES ('7', null, null, '3','2', '1', '324234', null, null, null, null, null, null, null);
INSERT INTO `dev_bbudeviceinfo` VALUES ('8', null, null, '3','2', '1', '324234', null, null, null, null, null, null, null);
INSERT INTO `dev_bbudeviceinfo` VALUES ('10', null, null, '3','2', '1', '324234', null, null, null, null, null, null, null);
INSERT INTO `dev_bbudeviceinfo` VALUES ('11', null, null, '3','2', '1', '324234', null, null, null, null, null, null, null);
INSERT INTO `dev_bbudeviceinfo` VALUES ('12', null, null, '3','2', '1', '324234', null, null, null, null, null, null, null);
INSERT INTO `dev_bbudeviceinfo` VALUES ('13', null, null, '3','2', '1', '324234', null, null, null, null, null, null, null);
INSERT INTO `dev_bbudeviceinfo` VALUES ('14', null, null, '3','2', '1', '324234', null, null, null, null, null, null, null);
INSERT INTO `dev_bbudeviceinfo` VALUES ('15', null, null, '3','2', '1', '324234', null, null, null, null, null, null, null);
INSERT INTO `dev_bbudeviceinfo` VALUES ('16', null, null, '3','2', '1', '324234', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for dev_device
-- ----------------------------
DROP TABLE IF EXISTS `dev_device`;
CREATE TABLE `dev_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `deviceCode` varchar(255) DEFAULT NULL,
  `area_id` bigint(20) DEFAULT NULL,
  `engineRoom_id` bigint(20) DEFAULT NULL,
  `deviceName` varchar(255) DEFAULT NULL,
  `deviceModel` varchar(255) DEFAULT NULL,
  `deviceFrame` varchar(255) DEFAULT NULL,
  `snCode` varchar(255) DEFAULT NULL,
  `deviceType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dev_device
-- ----------------------------
INSERT INTO `dev_device` VALUES ('2', '2018-05-01 18:30:36', '2018-05-01 18:30:36', 'OLT1', null, '1', 'OLT1', '124213', '4213423', '42134132', 'OLT');
INSERT INTO `dev_device` VALUES ('3', '2018-05-01 18:38:11', '2018-05-01 18:38:11', 'BBU1', '4', '3', 'BBU1', '234', '21341324', '23413', 'BBU');
INSERT INTO `dev_device` VALUES ('4', '2018-05-01 18:47:05', '2018-05-01 18:47:05', 'ORDINARY1', '2', '1', 'ORDINARY1', '41234', '2341234', '312412', 'ORDINARY');
INSERT INTO `dev_device` VALUES ('5', '2018-05-01 18:47:16', '2018-05-01 18:47:16', 'IPRAN1', '4', '3', 'IPRAN1', '124234', '1234', '2314123414', 'IPRAN');
INSERT INTO `dev_device` VALUES ('6', '2018-05-06 14:26:57', '2018-05-06 14:26:57', '1', '1', null, '234234', '2342', '24234', '4234', 'OLT');
INSERT INTO `dev_device` VALUES ('7', '2018-05-06 14:27:18', '2018-05-27 18:09:03', '1', '1', '8', '是不是这个', '234223', '24234999', '4234', 'OLT');
INSERT INTO `dev_device` VALUES ('40', '2018-05-06 14:50:40', '2018-05-06 14:50:40', '2', '1', '8', '水电费', '2342', '24234', '4234', 'OLT');
INSERT INTO `dev_device` VALUES ('41', '2018-05-06 14:50:40', '2018-05-27 18:06:37', '3', '1', '8', '水电费3', '2342', '24234678', '4234', 'OLT');
INSERT INTO `dev_device` VALUES ('42', '2018-05-06 14:50:40', '2018-05-06 14:50:40', '4', '1', '8', '都是非法', '2342', '24234', '4234', 'OLT');
INSERT INTO `dev_device` VALUES ('43', '2018-05-06 14:50:40', '2018-05-06 14:50:40', '5', '1', '8', '阿萨德发的撒 ', '2342', '24234', '4234', 'OLT');

-- ----------------------------
-- Table structure for dev_engineroom
-- ----------------------------
DROP TABLE IF EXISTS `dev_engineroom`;
CREATE TABLE `dev_engineroom` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `roomCode` varchar(255) DEFAULT NULL,
  `deviceRoomName` varchar(255) DEFAULT NULL,
  `roomDesc` varchar(255) DEFAULT NULL,
  `area_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dev_engineroom
-- ----------------------------
INSERT INTO `dev_engineroom` VALUES ('1', '2018-05-01 14:45:42', '2018-05-01 14:45:42', '32432', '机房一 ', '234', '2');
INSERT INTO `dev_engineroom` VALUES ('3', '2018-05-01 14:59:44', '2018-05-01 14:59:44', '9808', '机房二', '333', '4');
INSERT INTO `dev_engineroom` VALUES ('8', '2018-05-06 14:26:56', '2018-05-06 14:26:56', '2342', '2342', null, '1');

-- ----------------------------
-- Table structure for dev_ipraninfo
-- ----------------------------
DROP TABLE IF EXISTS `dev_ipraninfo`;
CREATE TABLE `dev_ipraninfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `device_id` bigint(20) DEFAULT NULL,
  `port` varchar(255) DEFAULT NULL,
  `fiberFrameAddr` varchar(255) DEFAULT NULL,
  `fiberFramePort` varchar(255) DEFAULT NULL,
  `targetDevice` varchar(255) DEFAULT NULL,
  `targetDeviceModel` varchar(255) DEFAULT NULL,
  `targetFiberFrame` varchar(255) DEFAULT NULL,
  `physicalPort` varchar(255) DEFAULT NULL,
  `serviceName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dev_ipraninfo
-- ----------------------------

-- ----------------------------
-- Table structure for dev_oltinfo
-- ----------------------------
DROP TABLE IF EXISTS `dev_oltinfo`;
CREATE TABLE `dev_oltinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `device_id` bigint(20) DEFAULT NULL,
  `port` varchar(255) DEFAULT NULL,
  `serviceName` varchar(255) DEFAULT NULL,
  `fiberFrameAddr` varchar(255) DEFAULT NULL,
  `fiberFramePort` varchar(255) DEFAULT NULL,
  `targetDevice` varchar(255) DEFAULT NULL,
  `physicalPort` varchar(255) DEFAULT NULL,
  `opticalName` varchar(255) DEFAULT NULL,
  `opticalCore` varchar(255) DEFAULT NULL,
  `lable` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dev_oltinfo
-- ----------------------------

-- ----------------------------
-- Table structure for dev_ordinaryinfo
-- ----------------------------
DROP TABLE IF EXISTS `dev_ordinaryinfo`;
CREATE TABLE `dev_ordinaryinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `device_id` bigint(20) DEFAULT NULL,
  `port` varchar(50) DEFAULT NULL,
  `fiberFrameAddr` varchar(255) DEFAULT NULL,
  `fiberFramePort` varchar(255) DEFAULT NULL,
  `targetDevice` varchar(255) DEFAULT NULL,
  `targetDeviceModel` varchar(255) DEFAULT NULL,
  `targetFiberFrame` varchar(255) DEFAULT NULL,
  `physicalPort` varchar(255) DEFAULT NULL,
  `serviceName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dev_ordinaryinfo
-- ----------------------------
INSERT INTO `dev_ordinaryinfo` VALUES ('2', null, null, '4', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `dev_ordinaryinfo` VALUES ('3', null, null, '4', '2', '2', '2', '2', '2', '2', '2', '2');

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for sys_area
-- ----------------------------
DROP TABLE IF EXISTS `sys_area`;
CREATE TABLE `sys_area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `areaCode` varchar(255) DEFAULT NULL,
  `areaName` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_area
-- ----------------------------
INSERT INTO `sys_area` VALUES ('1', '2018-04-18 16:52:29', '2018-04-20 16:52:29', '1', '泸州1', null);
INSERT INTO `sys_area` VALUES ('2', '2018-04-20 16:53:07', '2018-04-20 16:53:07', '2', '泸州2', null);
INSERT INTO `sys_area` VALUES ('3', '2018-04-20 16:53:19', '2018-04-20 16:53:19', '3', '泸州3', null);
INSERT INTO `sys_area` VALUES ('4', '2018-04-20 16:53:44', '2018-04-20 16:53:44', '4', '泸州4', null);
INSERT INTO `sys_area` VALUES ('5', '2018-04-25 16:54:34', '2018-04-20 16:54:34', '5', '泸州', null);
INSERT INTO `sys_area` VALUES ('8', '2018-05-01 14:05:17', '2018-05-01 14:41:14', '324234', '21431234324', null);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `loginip` varchar(255) DEFAULT NULL,
  `permissionCode` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `accessUri` varchar(255) DEFAULT NULL,
  `accessParams` varchar(255) DEFAULT NULL,
  `status` bit(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', '2018-05-19 11:51:41', '2018-05-19 11:51:41', 'admin', '0:0:0:0:0:0:0:1', 'MangeDelete', null, '/sys/manager/delete/24', '[24]', null);
INSERT INTO `sys_log` VALUES ('2', '2018-05-19 12:27:46', '2018-05-19 12:27:46', 'admin', '0:0:0:0:0:0:0:1', 'Manager:del', '管理员删除', '/sys/manager/delete/23', '[23]', null);

-- ----------------------------
-- Table structure for sys_manager
-- ----------------------------
DROP TABLE IF EXISTS `sys_manager`;
CREATE TABLE `sys_manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `realName` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `area_id` bigint(20) DEFAULT NULL,
  `lastLogin` datetime DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_manager
-- ----------------------------
INSERT INTO `sys_manager` VALUES ('1', null, '2018-05-27 13:31:52', 'admin', 'D15F5A58B7998C491BC92789D2179214', '超级管理员', '123213213', null, null, '836909', 'WOMAN', null, null, '');
INSERT INTO `sys_manager` VALUES ('2', null, null, '234324', '234', '234', '23432·', null, null, null, 'WOMAN', '2', null, null);
INSERT INTO `sys_manager` VALUES ('3', null, null, '12312312', '234324', '3424', '234214', null, null, null, 'MAN', '3', null, null);

-- ----------------------------
-- Table structure for sys_manager_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_manager_role`;
CREATE TABLE `sys_manager_role` (
  `manager_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_manager_role
-- ----------------------------
INSERT INTO `sys_manager_role` VALUES ('1', '1');
INSERT INTO `sys_manager_role` VALUES ('25', '1');
INSERT INTO `sys_manager_role` VALUES ('25', '5');
INSERT INTO `sys_manager_role` VALUES ('26', '1');
INSERT INTO `sys_manager_role` VALUES ('26', '5');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `permissionName` varchar(255) DEFAULT NULL,
  `permissionCode` varchar(255) DEFAULT NULL,
  `permissionType` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `routerUrl` varchar(255) DEFAULT NULL,
  `icon` varchar(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `permissionPath` varchar(255) DEFAULT NULL,
  `actionType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '2018-04-29 20:08:43', '2018-04-29 20:08:44', '系统管理', null, 'MENU', null, null, null, '1', null, '1', '/1', null);
INSERT INTO `sys_permission` VALUES ('2', '2018-04-29 20:09:59', '2018-04-29 20:10:01', '管理员管理', 'Manager:list', 'MENU', '/sys/manager/listPage', null, null, '2', '1', '2', '/1/2', null);
INSERT INTO `sys_permission` VALUES ('3', '2018-04-29 20:09:59', '2018-04-29 20:10:01', '角色管理', 'Role:list', 'MENU', '/sys/role/listPage', null, null, '3', '1', '2', '/1/3', null);
INSERT INTO `sys_permission` VALUES ('4', '2018-05-01 07:53:32', '2018-05-01 07:53:34', '区域管理', 'Area:list', 'MENU', '/sys/area/listPage', null, null, '4', '1', '2', '/1/4', null);
INSERT INTO `sys_permission` VALUES ('5', '2018-05-19 08:52:56', '2018-05-19 08:52:58', '日志管理', 'ManageInfo:view', 'MENU', '/sys/systemLog/listPage', null, null, '5', '1', '2', '/1/5', null);
INSERT INTO `sys_permission` VALUES ('6', '2018-04-29 20:09:59', '2018-04-29 20:09:59', '新增编辑管理员', 'Manager:update', 'ACTION', null, null, null, '6', '2', '3', '/1/2/6', null);
INSERT INTO `sys_permission` VALUES ('7', '2018-05-01 14:08:26', '2018-05-01 14:08:26', '管理员修改信息', 'Manager:info', 'ACTION', null, null, null, '7', '3', '3', '/1/2/7', null);
INSERT INTO `sys_permission` VALUES ('8', '2018-04-29 20:09:59', '2018-04-29 20:09:59', '管理员删除', 'Manager:del', 'ACTION', null, null, null, '8', '2', '3', '/1/2/8', null);
INSERT INTO `sys_permission` VALUES ('9', '2018-04-29 20:09:59', '2018-04-29 20:09:59', '管理员分角色', 'Manager:setRole', 'ACTION', null, null, null, '9', '2', '3', '/1/2/9', null);
INSERT INTO `sys_permission` VALUES ('10', '2018-04-29 20:09:59', '2018-04-29 20:09:59', '新增/编辑角色', 'Role:update', 'ACTION', null, null, null, '10', '3', '3', '/1/3/10', null);
INSERT INTO `sys_permission` VALUES ('12', '2018-04-29 20:09:59', '2018-04-29 20:09:59', '角色删除', 'Role:del', 'ACTION', null, null, null, '12', '3', '3', '/1/3/12', null);
INSERT INTO `sys_permission` VALUES ('13', '2018-04-29 20:09:59', '2018-04-29 20:09:59', '角色赋权', 'Role:setPermission', 'ACTION', null, null, null, '13', '3', '3', '/1/3/13', null);
INSERT INTO `sys_permission` VALUES ('14', '2018-05-01 14:07:30', '2018-05-01 14:07:34', '设备管理', null, 'MENU', null, null, null, '14', null, '1', '/14', null);
INSERT INTO `sys_permission` VALUES ('15', '2018-05-01 14:08:23', '2018-05-01 14:08:28', '机房管理', 'DeviceRoom:list', 'MENU', '/dev/engineRoom/listPage', null, null, '15', '14', '2', '/14/15', null);
INSERT INTO `sys_permission` VALUES ('16', '2018-05-01 14:08:26', '2018-05-01 14:08:30', '设备管理', 'Device:list', 'MENU', '/dev/device/listPage', null, null, '16', '14', '2', '/14/16', null);
INSERT INTO `sys_permission` VALUES ('17', '2018-05-01 14:08:26', '2018-05-01 14:08:26', '新增/编辑机房', 'DeviceRoom:update', 'ACTION', null, null, null, '17', '15', '3', '/14/15/17', null);
INSERT INTO `sys_permission` VALUES ('19', '2018-05-01 14:08:26', '2018-05-01 14:08:26', '机房删除', 'DeviceRoom:del', 'ACTION', null, null, null, '19', '15', '3', '/14/15/19', null);
INSERT INTO `sys_permission` VALUES ('20', '2018-05-01 14:08:26', '2018-05-01 14:08:26', '新增/编辑设备', 'Device:update', 'ACTION', null, null, null, '20', '16', '3', '/14/16/20', null);
INSERT INTO `sys_permission` VALUES ('22', '2018-05-01 14:08:26', '2018-05-01 14:08:26', '设备删除', 'Device:del', 'ACTION', null, null, null, '22', '16', '3', '/14/16/22', null);
INSERT INTO `sys_permission` VALUES ('23', '2018-05-01 14:08:26', '2018-05-01 14:08:26', '设备导入', 'Device:import', 'ACTION', null, null, null, '23', '16', '3', '/14/16/23', null);
INSERT INTO `sys_permission` VALUES ('24', '2018-05-01 14:08:26', '2018-05-01 14:08:26', '设备导出', 'Device:export', 'ACTION', null, null, null, '24', '16', '3', '/14/16/24', null);
INSERT INTO `sys_permission` VALUES ('25', '2018-05-01 14:08:26', '2018-05-01 14:08:26', '设备明细删除', 'DeviceDetail:del', 'ACTION', null, null, null, '25', '16', '3', '/14/16/25', null);
INSERT INTO `sys_permission` VALUES ('26', '2018-05-01 14:08:26', '2018-05-01 14:08:26', '设备明细修改', 'DeviceDetail:update', 'ACTION', null, null, null, '26', '16', '3', '/14/16/26', null);
INSERT INTO `sys_permission` VALUES ('27', '2018-05-01 14:08:26', '2018-05-01 14:08:26', '新增/编辑区域', 'Area:update', 'ACTION', null, null, null, '27', '4', '3', '/1/4/27', null);
INSERT INTO `sys_permission` VALUES ('28', '2018-05-01 14:08:26', '2018-05-01 14:08:26', '区域删除', 'Area:del', 'ACTION', null, null, null, '28', '4', '3', '/1/4/28', null);
INSERT INTO `sys_permission` VALUES ('29', '2018-05-01 14:08:26', '2018-05-01 14:08:26', '无线设备删除', 'Bbu:del', 'ACTION', null, null, null, '29', '25', '4', '/14/16/25/29', null);
INSERT INTO `sys_permission` VALUES ('30', '2018-05-01 14:08:26', '2018-05-01 14:08:26', '无线设备编辑', 'Bbu:update', 'ACTION', null, null, null, '30', '26', '4', '/14/16/26/30', null);
INSERT INTO `sys_permission` VALUES ('31', '2018-05-01 14:08:26', '2018-05-01 14:08:26', '4G传输设备删除', 'IpRan:del', 'ACTION', null, null, null, '31', '25', '4', '/14/16/25/31', null);
INSERT INTO `sys_permission` VALUES ('32', '2018-05-01 14:08:26', '2018-05-01 14:08:26', '4G传输设备编辑', 'IpRan:update', 'ACTION', null, null, null, '32', '26', '4', '/14/16/26/32', null);
INSERT INTO `sys_permission` VALUES ('33', '2018-05-01 14:08:26', '2018-05-01 14:08:26', '上网传输设备删除', 'Olt:del', 'ACTION', null, null, null, '33', '25', '4', '/14/16/25/33', null);
INSERT INTO `sys_permission` VALUES ('34', '2018-05-01 14:08:26', '2018-05-01 14:08:26', '上网传输设备编辑', 'Olt:update', 'ACTION', null, null, null, '34', '26', '4', '/14/16/26/34', null);
INSERT INTO `sys_permission` VALUES ('35', '2018-05-01 14:08:26', '2018-05-01 14:08:26', '通用设备删除', 'Ordinary:del', 'ACTION', null, null, null, '35', '25', '4', '/14/16/25/35', null);
INSERT INTO `sys_permission` VALUES ('36', '2018-05-01 14:08:26', '2018-05-01 14:08:26', '通用设备编辑', 'Ordinary:update', 'ACTION', null, null, null, '36', '26', '4', '/14/16/26/36', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `roleCode` varchar(255) DEFAULT NULL,
  `roleName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '2018-05-13 08:18:13', '2018-05-19 11:13:19', 'superAdmin', '  超级管理员');
INSERT INTO `sys_role` VALUES ('5', '2018-05-13 08:26:52', '2018-05-13 08:26:52', 'dd', '4234');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('5', '5');
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('1', '3');
INSERT INTO `sys_role_permission` VALUES ('1', '4');
INSERT INTO `sys_role_permission` VALUES ('1', '5');
INSERT INTO `sys_role_permission` VALUES ('1', '16');
INSERT INTO `sys_role_permission` VALUES ('1', '14');
INSERT INTO `sys_role_permission` VALUES ('1', '15');
