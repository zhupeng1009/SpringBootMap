/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : information

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2020-05-25 20:13:04
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `time` int(11) DEFAULT NULL COMMENT '响应时间',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=914 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_macro`
-- ----------------------------
DROP TABLE IF EXISTS `sys_macro`;
CREATE TABLE `sys_macro` (
  `macro_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_id` bigint(255) DEFAULT NULL COMMENT '父级id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `value` varchar(2000) DEFAULT NULL COMMENT '值',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，0：隐藏   1：显示',
  `type` tinyint(20) DEFAULT NULL COMMENT '类型,0:目录，1:参数配置',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`macro_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='通用字典表';

-- ----------------------------
-- Records of sys_macro
-- ----------------------------
INSERT INTO sys_macro VALUES ('1', '0', '系统参数', null, '1', '0', '0', null, '2017-08-15 14:51:27', null);
INSERT INTO sys_macro VALUES ('2', '1', '用户状态', 'userStatus', '1', '0', '0', null, '2017-08-15 14:51:30', null);
INSERT INTO sys_macro VALUES ('3', '2', '正常', '1', '0', '1', '0', '用户可登录', '2017-08-15 14:52:48', '2017-08-15 20:23:29');
INSERT INTO sys_macro VALUES ('4', '2', '禁用', '0', '1', '1', '0', '禁止用户登录', '2017-08-15 14:52:51', '2017-08-15 20:44:42');
INSERT INTO sys_macro VALUES ('5', '0', '客户关系', ' clientManage', '1', '0', '1', null, '2017-08-15 19:58:24', null);
INSERT INTO sys_macro VALUES ('6', '5', '产品信息', 'clientProduct', '1', '0', '0', null, '2017-08-15 19:59:06', null);

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO sys_menu VALUES ('1', '0', '基础管理', '', '', '0', 'fa fa-bars', '2', '2017-08-09 22:49:47', null);
INSERT INTO sys_menu VALUES ('2', '3', '系统菜单', '/sys/menu', 'sys:menu:menu', '1', 'fa fa-th-list', '3', '2017-08-09 22:55:15', null);
INSERT INTO sys_menu VALUES ('3', '0', '系统管理', '', '', '0', 'fa fa-desktop', '3', '2017-08-09 23:06:55', '2017-08-14 14:13:43');
INSERT INTO sys_menu VALUES ('6', '3', '用户管理', '/sys/user', 'sys:user:user', '1', 'fa fa-user', '1', '2017-08-10 14:12:11', null);
INSERT INTO sys_menu VALUES ('7', '3', '角色管理', '/sys/role', 'sys:role:role', '1', 'fa fa-paw', '2', '2017-08-10 14:13:19', null);
INSERT INTO sys_menu VALUES ('12', '6', '新增', '', 'sys:user:add', '2', '', '1', '2017-08-14 10:51:35', null);
INSERT INTO sys_menu VALUES ('13', '6', '编辑', '', 'sys:user:edit', '2', '', '2', '2017-08-14 10:52:06', null);
INSERT INTO sys_menu VALUES ('14', '6', '删除', null, 'sys:user:remove', '2', null, '3', '2017-08-14 10:52:24', null);
INSERT INTO sys_menu VALUES ('15', '7', '新增', '', 'sys:role:add', '2', '', '1', '2017-08-14 10:56:37', null);
INSERT INTO sys_menu VALUES ('20', '2', '新增', '', 'sys:menu:add', '2', '', '1', '2017-08-14 10:59:32', null);
INSERT INTO sys_menu VALUES ('21', '2', '编辑', '', 'sys:menu:edit', '2', '', '2', '2017-08-14 10:59:56', null);
INSERT INTO sys_menu VALUES ('22', '2', '删除', '', 'sys:menu:remove', '2', '', '3', '2017-08-14 11:00:26', null);
INSERT INTO sys_menu VALUES ('24', '6', '批量删除', '', 'sys:user:batchRemove', '2', '', '0', '2017-08-14 17:27:18', null);
INSERT INTO sys_menu VALUES ('25', '6', '停用', null, 'sys:user:disable', '2', null, '0', '2017-08-14 17:27:43', null);
INSERT INTO sys_menu VALUES ('26', '6', '重置密码', '', 'sys:user:resetPwd', '2', '', '0', '2017-08-14 17:28:34', null);
INSERT INTO sys_menu VALUES ('27', '1', '系统日志', '/log', 'common:log', '1', 'fa fa-warning', '1', '2017-08-14 22:11:53', null);
INSERT INTO sys_menu VALUES ('28', '27', '刷新', null, 'sys:log:list', '2', null, '0', '2017-08-14 22:30:22', null);
INSERT INTO sys_menu VALUES ('29', '27', '删除', null, 'sys:log:remove', '2', null, '0', '2017-08-14 22:30:43', null);
INSERT INTO sys_menu VALUES ('30', '27', '清空', null, 'sys:log:clear', '2', null, '2', '2017-08-14 22:31:02', null);
INSERT INTO sys_menu VALUES ('48', '1', '代码生成', '/generator', 'common:generator', '1', 'fa fa-code', '2', null, null);
INSERT INTO sys_menu VALUES ('55', '7', '编辑', '', 'sys:role:edit', '2', '', '2', null, null);
INSERT INTO sys_menu VALUES ('56', '7', '删除', '', 'sys:role:remove', '2', null, '3', null, null);
INSERT INTO sys_menu VALUES ('57', '1', '运行监控', '/druid/index.html', '', '1', 'fa fa-caret-square-o-right', '3', null, null);
INSERT INTO sys_menu VALUES ('61', '2', '批量删除', '', 'sys:menu:batchRemove', '2', null, '4', null, null);
INSERT INTO sys_menu VALUES ('62', '7', '批量删除', '', 'sys:role:batchRemove', '2', null, '4', null, null);
INSERT INTO sys_menu VALUES ('63', '0', '情报分析', '', '', '0', 'fa fa-anchor', '1', null, null);
INSERT INTO sys_menu VALUES ('64', '63', '情报显示', '/position', 'business:position', '1', '', '1', null, null);
INSERT INTO sys_menu VALUES ('65', '63', '设备概况', '/device', 'business:device', '1', null, '2', null, null);

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_sign` varchar(100) DEFAULT NULL COMMENT '角色标识',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO sys_role VALUES ('1', '超级用户角色', 'admin', '拥有最高权限', '2', '2017-08-12 00:43:52', '2017-08-12 19:14:59');
INSERT INTO sys_role VALUES ('56', '普通用户', null, '只能使用部分功能和部分数据', null, null, null);

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1561 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO sys_role_menu VALUES ('367', '44', '1');
INSERT INTO sys_role_menu VALUES ('368', '44', '32');
INSERT INTO sys_role_menu VALUES ('369', '44', '33');
INSERT INTO sys_role_menu VALUES ('370', '44', '34');
INSERT INTO sys_role_menu VALUES ('371', '44', '35');
INSERT INTO sys_role_menu VALUES ('372', '44', '28');
INSERT INTO sys_role_menu VALUES ('373', '44', '29');
INSERT INTO sys_role_menu VALUES ('374', '44', '30');
INSERT INTO sys_role_menu VALUES ('375', '44', '38');
INSERT INTO sys_role_menu VALUES ('376', '44', '4');
INSERT INTO sys_role_menu VALUES ('377', '44', '27');
INSERT INTO sys_role_menu VALUES ('378', '45', '38');
INSERT INTO sys_role_menu VALUES ('379', '46', '3');
INSERT INTO sys_role_menu VALUES ('380', '46', '20');
INSERT INTO sys_role_menu VALUES ('381', '46', '21');
INSERT INTO sys_role_menu VALUES ('382', '46', '22');
INSERT INTO sys_role_menu VALUES ('383', '46', '23');
INSERT INTO sys_role_menu VALUES ('384', '46', '11');
INSERT INTO sys_role_menu VALUES ('385', '46', '12');
INSERT INTO sys_role_menu VALUES ('386', '46', '13');
INSERT INTO sys_role_menu VALUES ('387', '46', '14');
INSERT INTO sys_role_menu VALUES ('388', '46', '24');
INSERT INTO sys_role_menu VALUES ('389', '46', '25');
INSERT INTO sys_role_menu VALUES ('390', '46', '26');
INSERT INTO sys_role_menu VALUES ('391', '46', '15');
INSERT INTO sys_role_menu VALUES ('392', '46', '2');
INSERT INTO sys_role_menu VALUES ('393', '46', '6');
INSERT INTO sys_role_menu VALUES ('394', '46', '7');
INSERT INTO sys_role_menu VALUES ('581', '48', '38');
INSERT INTO sys_role_menu VALUES ('582', '48', '32');
INSERT INTO sys_role_menu VALUES ('583', '48', '33');
INSERT INTO sys_role_menu VALUES ('584', '48', '34');
INSERT INTO sys_role_menu VALUES ('585', '48', '35');
INSERT INTO sys_role_menu VALUES ('586', '48', '4');
INSERT INTO sys_role_menu VALUES ('587', '48', '28');
INSERT INTO sys_role_menu VALUES ('588', '48', '29');
INSERT INTO sys_role_menu VALUES ('589', '48', '30');
INSERT INTO sys_role_menu VALUES ('590', '48', '27');
INSERT INTO sys_role_menu VALUES ('591', '48', '1');
INSERT INTO sys_role_menu VALUES ('598', '50', '38');
INSERT INTO sys_role_menu VALUES ('632', '38', '42');
INSERT INTO sys_role_menu VALUES ('737', '51', '38');
INSERT INTO sys_role_menu VALUES ('738', '51', '39');
INSERT INTO sys_role_menu VALUES ('739', '51', '40');
INSERT INTO sys_role_menu VALUES ('740', '51', '41');
INSERT INTO sys_role_menu VALUES ('741', '51', '4');
INSERT INTO sys_role_menu VALUES ('742', '51', '32');
INSERT INTO sys_role_menu VALUES ('743', '51', '33');
INSERT INTO sys_role_menu VALUES ('744', '51', '34');
INSERT INTO sys_role_menu VALUES ('745', '51', '35');
INSERT INTO sys_role_menu VALUES ('746', '51', '27');
INSERT INTO sys_role_menu VALUES ('747', '51', '28');
INSERT INTO sys_role_menu VALUES ('748', '51', '29');
INSERT INTO sys_role_menu VALUES ('749', '51', '30');
INSERT INTO sys_role_menu VALUES ('750', '51', '1');
INSERT INTO sys_role_menu VALUES ('1038', '49', '-1');
INSERT INTO sys_role_menu VALUES ('1040', '52', '49');
INSERT INTO sys_role_menu VALUES ('1064', '54', '53');
INSERT INTO sys_role_menu VALUES ('1095', '55', '2');
INSERT INTO sys_role_menu VALUES ('1096', '55', '6');
INSERT INTO sys_role_menu VALUES ('1097', '55', '7');
INSERT INTO sys_role_menu VALUES ('1098', '55', '3');
INSERT INTO sys_role_menu VALUES ('1100', '55', '49');
INSERT INTO sys_role_menu VALUES ('1101', '55', '1');
INSERT INTO sys_role_menu VALUES ('1375', '59', '1');
INSERT INTO sys_role_menu VALUES ('1376', '59', '3');
INSERT INTO sys_role_menu VALUES ('1513', '56', '63');
INSERT INTO sys_role_menu VALUES ('1514', '56', '64');
INSERT INTO sys_role_menu VALUES ('1515', '56', '65');
INSERT INTO sys_role_menu VALUES ('1540', '1', '20');
INSERT INTO sys_role_menu VALUES ('1541', '1', '21');
INSERT INTO sys_role_menu VALUES ('1542', '1', '22');
INSERT INTO sys_role_menu VALUES ('1543', '1', '61');
INSERT INTO sys_role_menu VALUES ('1544', '1', '2');
INSERT INTO sys_role_menu VALUES ('1545', '1', '12');
INSERT INTO sys_role_menu VALUES ('1546', '1', '13');
INSERT INTO sys_role_menu VALUES ('1547', '1', '14');
INSERT INTO sys_role_menu VALUES ('1548', '1', '24');
INSERT INTO sys_role_menu VALUES ('1549', '1', '25');
INSERT INTO sys_role_menu VALUES ('1550', '1', '26');
INSERT INTO sys_role_menu VALUES ('1551', '1', '6');
INSERT INTO sys_role_menu VALUES ('1552', '1', '15');
INSERT INTO sys_role_menu VALUES ('1553', '1', '55');
INSERT INTO sys_role_menu VALUES ('1554', '1', '56');
INSERT INTO sys_role_menu VALUES ('1555', '1', '62');
INSERT INTO sys_role_menu VALUES ('1556', '1', '7');
INSERT INTO sys_role_menu VALUES ('1557', '1', '3');
INSERT INTO sys_role_menu VALUES ('1558', '1', '64');
INSERT INTO sys_role_menu VALUES ('1559', '1', '65');
INSERT INTO sys_role_menu VALUES ('1560', '1', '63');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(255) DEFAULT NULL COMMENT '状态 0:禁用，1:正常',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO sys_user VALUES ('1', 'admin', '33808479d49ca8a3cdc93d4f976d1e3d', 'admin@example.com', '123456', '1', '1', '2017-08-15 21:40:39', '2017-08-15 21:41:00', '超级管理员');
INSERT INTO sys_user VALUES ('2', 'test', '6cf3bb3deba2aadbd41ec9a22511084e', 'test@bootdo.com', null, '1', '1', '2017-08-14 13:43:05', '2017-08-14 21:15:36', '临时用户');
INSERT INTO sys_user VALUES ('3', 'qingbao', '31a27ec1ab50301b91eef0e07045a319', '250817@qq.com', null, '1', null, null, null, 'qingbao');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO sys_user_role VALUES ('67', '2', '38');
INSERT INTO sys_user_role VALUES ('68', '2', '48');
INSERT INTO sys_user_role VALUES ('73', '30', '48');
INSERT INTO sys_user_role VALUES ('74', '30', '49');
INSERT INTO sys_user_role VALUES ('75', '30', '50');
INSERT INTO sys_user_role VALUES ('76', '31', '48');
INSERT INTO sys_user_role VALUES ('77', '31', '49');
INSERT INTO sys_user_role VALUES ('78', '31', '52');
INSERT INTO sys_user_role VALUES ('79', '32', '48');
INSERT INTO sys_user_role VALUES ('80', '32', '49');
INSERT INTO sys_user_role VALUES ('81', '32', '50');
INSERT INTO sys_user_role VALUES ('82', '32', '51');
INSERT INTO sys_user_role VALUES ('83', '32', '52');
INSERT INTO sys_user_role VALUES ('84', '33', '38');
INSERT INTO sys_user_role VALUES ('85', '33', '49');
INSERT INTO sys_user_role VALUES ('86', '33', '52');
INSERT INTO sys_user_role VALUES ('87', '34', '50');
INSERT INTO sys_user_role VALUES ('88', '34', '51');
INSERT INTO sys_user_role VALUES ('89', '34', '52');
INSERT INTO sys_user_role VALUES ('110', '1', '1');
INSERT INTO sys_user_role VALUES ('112', '3', '56');

-- ----------------------------
-- Table structure for `t_airplane`
-- ----------------------------
DROP TABLE IF EXISTS `t_airplane`;
CREATE TABLE `t_airplane` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(20) DEFAULT NULL,
  `pnumber` varchar(32) DEFAULT NULL,
  `speed` decimal(10,7) DEFAULT NULL,
  `longitude` decimal(10,7) DEFAULT NULL,
  `latitude` decimal(10,7) DEFAULT NULL,
  `img` varchar(100) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_airplane
-- ----------------------------
INSERT INTO t_airplane VALUES ('1', 'airplane', '12324234235', '822.0000000', '120.4041000', '39.9150000', 'map/img/airplane.jpg', '2019-12-17 13:50:45');
INSERT INTO t_airplane VALUES ('2', 'airplane', '12324234235', '843.0000000', '120.4043000', '39.9152000', 'map/img/airplane.jpg', '2019-12-17 13:51:50');
INSERT INTO t_airplane VALUES ('3', 'airplane', '12324234235', '811.0000000', '120.4044000', '39.9150000', 'map/img/airplane.jpg', '2019-12-17 13:52:33');
INSERT INTO t_airplane VALUES ('4', 'airplane', '12324234235', '943.0000000', '120.4042000', '39.9563000', 'map/img/airplane.jpg', '2020-05-25 18:59:59');
INSERT INTO t_airplane VALUES ('5', 'airplane', '12324234235', '888.0000000', '120.4043000', '39.9565000', 'map/img/airplane.jpg', '2020-05-25 19:03:11');
INSERT INTO t_airplane VALUES ('6', 'airplane', '12324234235', '866.0000000', '120.4044000', '39.9564000', 'map/img/airplane.jpg', '2020-05-25 19:07:14');

-- ----------------------------
-- Table structure for `t_boat`
-- ----------------------------
DROP TABLE IF EXISTS `t_boat`;
CREATE TABLE `t_boat` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(20) DEFAULT NULL,
  `pnumber` varchar(32) DEFAULT NULL,
  `speed` decimal(10,7) DEFAULT NULL,
  `longitude` decimal(10,7) DEFAULT NULL,
  `latitude` decimal(10,7) DEFAULT NULL,
  `img` varchar(100) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_boat
-- ----------------------------
INSERT INTO t_boat VALUES ('1', 'boat', '12324234234', '54.0000000', '113.4060000', '18.9160000', 'map/img/boat.jpg', '2019-12-17 13:50:45');
INSERT INTO t_boat VALUES ('2', 'boat', '12324234234', '55.0000000', '113.4050000', '18.4030000', 'map/img/boat.jpg', '2019-12-17 13:51:50');
INSERT INTO t_boat VALUES ('3', 'boat', '12324234234', '50.0000000', '113.4040000', '18.9150000', 'map/img/boat.jpg', '2019-12-17 13:52:33');

-- ----------------------------
-- Table structure for `t_car`
-- ----------------------------
DROP TABLE IF EXISTS `t_car`;
CREATE TABLE `t_car` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(20) DEFAULT NULL,
  `pnumber` varchar(32) DEFAULT NULL,
  `speed` decimal(10,7) DEFAULT NULL,
  `longitude` decimal(10,7) DEFAULT NULL,
  `latitude` decimal(10,7) DEFAULT NULL,
  `img` varchar(100) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_car
-- ----------------------------
INSERT INTO t_car VALUES ('1', 'car', '12324234236', '110.0000000', '116.4040000', '29.9160000', 'map/img/car.jpg', '2019-12-17 13:50:45');
INSERT INTO t_car VALUES ('2', 'car', '12324234236', '80.0000000', '116.4040000', '29.9150000', 'map/img/car.jpg', '2019-12-17 13:51:50');
INSERT INTO t_car VALUES ('3', 'car', '12324234236', '50.0000000', '116.4040000', '28.9170000', 'map/img/car.jpg', '2019-12-17 13:52:33');

-- ----------------------------
-- Table structure for `t_device`
-- ----------------------------
DROP TABLE IF EXISTS `t_device`;
CREATE TABLE `t_device` (
  `id` bigint(20) NOT NULL,
  `node_name` varchar(50) DEFAULT NULL,
  `ip_address` varchar(100) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `img` varchar(500) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_device
-- ----------------------------
INSERT INTO t_device VALUES ('1', '服务器1', 'localhost', 'down', '', '2019-12-17 09:53:18');
INSERT INTO t_device VALUES ('2', '服务器2', '127.0.0.1', 'down', null, '2019-12-17 13:53:11');
INSERT INTO t_device VALUES ('3', '服务器3', '192.168.0.1', 'down', null, '2019-12-17 13:53:33');

-- ----------------------------
-- Table structure for `t_position`
-- ----------------------------
DROP TABLE IF EXISTS `t_position`;
CREATE TABLE `t_position` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(20) DEFAULT NULL,
  `pnumber` varchar(32) DEFAULT NULL,
  `speed` decimal(10,7) DEFAULT NULL,
  `longitude` decimal(10,7) DEFAULT NULL,
  `latitude` decimal(10,7) DEFAULT NULL,
  `img` varchar(100) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_position
-- ----------------------------
INSERT INTO t_position VALUES ('1', 'airplane', '12324234234', '800.0000000', '116.4040000', '39.9150000', 'map/img/airplane.jpg', '2019-12-17 13:50:45');
INSERT INTO t_position VALUES ('2', 'car', '12324234235', '80.0000000', '120.4040000', '29.4040000', 'map/img/car.jpg', '2019-12-17 13:51:50');
INSERT INTO t_position VALUES ('3', 'boat', '12324234236', '50.0000000', '113.4040000', '18.9150000', 'map/img/boat.jpg', '2019-12-17 13:52:33');
