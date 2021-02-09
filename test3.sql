/*
Navicat MySQL Data Transfer

Source Server         : Demo
Source Server Version : 50729
Source Host           : localhost:3306
Source Database       : test3

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2021-02-09 14:50:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class_info
-- ----------------------------
DROP TABLE IF EXISTS `class_info`;
CREATE TABLE `class_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `remove` bit(1) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `class_name` varchar(255) NOT NULL,
  `teacher_info_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbo22jgnaghpmsf8noxraf71aw` (`teacher_info_id`),
  CONSTRAINT `FKbo22jgnaghpmsf8noxraf71aw` FOREIGN KEY (`teacher_info_id`) REFERENCES `teach_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class_info
-- ----------------------------
INSERT INTO `class_info` VALUES ('1', '2020-05-27 15:45:57', '\0', '2020-09-10 14:22:14', 'cbfc032e8b004523b2cf7068221a322d', '1班', '1');
INSERT INTO `class_info` VALUES ('2', '2020-08-31 18:50:46', '\0', '2020-08-31 18:50:46', '5a1a00104ed64836a6ca5b63abed64c5', '2班', '1');

-- ----------------------------
-- Table structure for dom_ask_for_leave
-- ----------------------------
DROP TABLE IF EXISTS `dom_ask_for_leave`;
CREATE TABLE `dom_ask_for_leave` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `remove` bit(1) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `begin_day` int(11) NOT NULL,
  `begin_month` int(11) NOT NULL,
  `begin_year` int(11) NOT NULL,
  `end_day` int(11) NOT NULL,
  `end_month` int(11) NOT NULL,
  `end_year` int(11) NOT NULL,
  `is_handle` int(11) DEFAULT NULL COMMENT '0：未处理\r\n1：同意\r\n2：拒绝',
  `leave_reason_text` varchar(255) DEFAULT NULL,
  `reply_text` varchar(255) DEFAULT NULL,
  `student_info_id` int(11) DEFAULT NULL,
  `teacher_info_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlw91q1gddedfek49rtitigd3t` (`student_info_id`),
  KEY `FKrr9afkwdptqsinvjfhhavrftb` (`teacher_info_id`),
  CONSTRAINT `FKlw91q1gddedfek49rtitigd3t` FOREIGN KEY (`student_info_id`) REFERENCES `stu_info` (`id`),
  CONSTRAINT `FKrr9afkwdptqsinvjfhhavrftb` FOREIGN KEY (`teacher_info_id`) REFERENCES `teach_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dom_ask_for_leave
-- ----------------------------
INSERT INTO `dom_ask_for_leave` VALUES ('1', '2020-05-27 15:48:05', '\0', '2020-05-27 15:48:05', '3d2116fc5a50488ca70ddd43a405a651', '7', '5', '2020', '8', '5', '2020', '2', 'go home.', 'fff', '1', '1');
INSERT INTO `dom_ask_for_leave` VALUES ('5', '2020-08-28 17:32:22', '\0', '2020-09-26 20:04:14', 'bf22ab501c694d97a26314a6a3eafb90', '28', '8', '2020', '29', '8', '2020', '1', '回家', '12', '1', '1');
INSERT INTO `dom_ask_for_leave` VALUES ('6', '2020-09-24 21:03:23', '\0', '2020-09-26 20:04:17', '24c189557cc74ed3a6ea859653d618ab', '24', '9', '2020', '24', '9', '2020', '2', '123', '123', '1', '1');

-- ----------------------------
-- Table structure for dom_permission
-- ----------------------------
DROP TABLE IF EXISTS `dom_permission`;
CREATE TABLE `dom_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `remove` bit(1) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dom_permission
-- ----------------------------
INSERT INTO `dom_permission` VALUES ('1', '2020-05-27 15:45:52', '\0', '2020-05-27 15:45:52', '75135e28e24b4dc6bf2b11003a526043', '数据管理员', '数据管理员', '1');

-- ----------------------------
-- Table structure for dom_role
-- ----------------------------
DROP TABLE IF EXISTS `dom_role`;
CREATE TABLE `dom_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `remove` bit(1) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `description` varchar(150) DEFAULT NULL COMMENT '备注',
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dom_role
-- ----------------------------
INSERT INTO `dom_role` VALUES ('1', '2020-05-27 15:45:34', '\0', '2020-05-27 15:45:34', '646547fbac3d404eb0b9d151bea42e58', '管理员,系统负责人，最高权限', '管理员');
INSERT INTO `dom_role` VALUES ('2', '2020-05-27 15:45:34', '\0', '2020-05-27 15:45:34', '38f4c4280c1c41f2ab2808c0e6548809', '学生，只允许调用学生权限下的接口', '学生');
INSERT INTO `dom_role` VALUES ('3', '2020-05-27 15:45:34', '\0', '2020-05-27 15:45:34', 'a9583c8426184f70a0c49d393e1d29ae', '教师，只允许调用教师权限下的接口', '教师');

-- ----------------------------
-- Table structure for dom_role_action
-- ----------------------------
DROP TABLE IF EXISTS `dom_role_action`;
CREATE TABLE `dom_role_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `remove` bit(1) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `action_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdvqh94xrjxngssuflar6a6s0s` (`action_id`),
  KEY `FK1a57oirjeg2tci9qjc9wf7v0r` (`role_id`),
  CONSTRAINT `FK1a57oirjeg2tci9qjc9wf7v0r` FOREIGN KEY (`role_id`) REFERENCES `dom_role` (`id`),
  CONSTRAINT `FKdvqh94xrjxngssuflar6a6s0s` FOREIGN KEY (`action_id`) REFERENCES `dom_sys_role_action` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dom_role_action
-- ----------------------------
INSERT INTO `dom_role_action` VALUES ('1', '2020-05-27 15:45:46', '\0', '2020-05-27 15:45:46', '0e174e5b21cd42928372ad43ff52c484', '1', '1');
INSERT INTO `dom_role_action` VALUES ('2', '2020-05-27 15:45:46', '\0', '2020-05-27 15:45:46', '0be43f01b2f74e00bef0896640dbcbb4', '2', '1');
INSERT INTO `dom_role_action` VALUES ('3', '2020-05-27 15:45:46', '\0', '2020-05-27 15:45:46', '14c83892dfbb4043863b0425dd7d2330', '3', '1');
INSERT INTO `dom_role_action` VALUES ('4', '2020-05-27 15:45:46', '\0', '2020-05-27 15:45:46', '17febd1364ab4f6a8aa92a6c7a3b1885', '2', '2');
INSERT INTO `dom_role_action` VALUES ('5', '2020-05-27 15:45:46', '\0', '2020-05-27 15:45:46', '4e802eb2d67d48d8a8e6e17b3d3b03d4', '3', '3');

-- ----------------------------
-- Table structure for dom_sign_log
-- ----------------------------
DROP TABLE IF EXISTS `dom_sign_log`;
CREATE TABLE `dom_sign_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `remove` bit(1) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `clock_in_day` int(11) NOT NULL,
  `clock_in_month` int(11) NOT NULL,
  `clock_in_year` int(11) NOT NULL,
  `student_info_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKre96hlupcj88ljswgl5xt1aii` (`student_info_id`),
  CONSTRAINT `FKre96hlupcj88ljswgl5xt1aii` FOREIGN KEY (`student_info_id`) REFERENCES `stu_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dom_sign_log
-- ----------------------------
INSERT INTO `dom_sign_log` VALUES ('1', '2020-05-27 15:47:58', '\0', '2020-05-27 15:47:58', '246bd320c6a8412d85a38a6c7e8fb0e2', '17', '4', '2020', '1');
INSERT INTO `dom_sign_log` VALUES ('2', '2020-05-27 15:47:58', '\0', '2020-05-27 15:47:58', '6dd9a6bab2804e8ba3431819a2d56143', '18', '4', '2020', '1');
INSERT INTO `dom_sign_log` VALUES ('3', '2020-08-24 14:50:57', '\0', '2020-08-24 14:50:57', 'f3e421ce02c54320889e95d0af1f39c6', '24', '8', '2020', '1');
INSERT INTO `dom_sign_log` VALUES ('4', '2020-08-27 18:51:38', '\0', '2020-08-27 18:51:38', 'ea8af973e0104af282c902cc1b4c101d', '27', '8', '2020', '1');
INSERT INTO `dom_sign_log` VALUES ('5', '2020-09-28 10:53:24', '\0', '2020-09-28 10:53:24', '1aa41f64c20c419e8189457cb8ddfd04', '28', '9', '2020', '1');
INSERT INTO `dom_sign_log` VALUES ('6', '2020-10-07 21:55:36', '\0', '2020-10-07 21:55:36', 'abba1f0eaf154f17870c33f161dce009', '7', '10', '2020', '1');
INSERT INTO `dom_sign_log` VALUES ('7', '2020-10-29 13:36:43', '\0', '2020-10-29 13:36:43', 'e9e97f03393e48569556ee7c887283a2', '29', '10', '2020', '1');

-- ----------------------------
-- Table structure for dom_sys_role_action
-- ----------------------------
DROP TABLE IF EXISTS `dom_sys_role_action`;
CREATE TABLE `dom_sys_role_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `remove` bit(1) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `action_group` varchar(255) DEFAULT NULL,
  `action_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dom_sys_role_action
-- ----------------------------
INSERT INTO `dom_sys_role_action` VALUES ('1', '2020-05-27 15:45:40', '\0', '2020-05-27 15:45:40', 'ae3ec0ef8e69416aad6c487d7cf8e494', 'API_ACCOUNT', 'API_ALLOW_ADMIN');
INSERT INTO `dom_sys_role_action` VALUES ('2', '2020-05-27 15:45:40', '\0', '2020-05-27 15:45:40', 'f50c6a6ad54e47828eebfb1431fd07b7', 'API_ACCOUNT', 'API_ALLOW_STU');
INSERT INTO `dom_sys_role_action` VALUES ('3', '2020-05-27 15:45:40', '\0', '2020-05-27 15:45:40', '281a96ba407c4296a4723b4fe9ca680d', 'API_ACCOUNT', 'API_ALLOW_TEACH');

-- ----------------------------
-- Table structure for dom_user
-- ----------------------------
DROP TABLE IF EXISTS `dom_user`;
CREATE TABLE `dom_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `remove` bit(1) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `class_info_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `student_info_id` int(11) DEFAULT NULL,
  `teacher_info_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7c1fo5wpdmf1s9m88bj3w3hwh` (`class_info_id`),
  KEY `FKm3nbcuivp73uvwduogk18dr13` (`permission_id`),
  KEY `FKawp8spm6apb4nk9gs32nm0931` (`role_id`),
  KEY `FKp1v0kr9j3n0bsg28kp63eae1u` (`student_info_id`),
  KEY `FK4d8pnv15ch12utmujlt5unyal` (`teacher_info_id`),
  CONSTRAINT `FK4d8pnv15ch12utmujlt5unyal` FOREIGN KEY (`teacher_info_id`) REFERENCES `teach_info` (`id`),
  CONSTRAINT `FK7c1fo5wpdmf1s9m88bj3w3hwh` FOREIGN KEY (`class_info_id`) REFERENCES `class_info` (`id`),
  CONSTRAINT `FKawp8spm6apb4nk9gs32nm0931` FOREIGN KEY (`role_id`) REFERENCES `dom_role` (`id`),
  CONSTRAINT `FKm3nbcuivp73uvwduogk18dr13` FOREIGN KEY (`permission_id`) REFERENCES `dom_permission` (`id`),
  CONSTRAINT `FKp1v0kr9j3n0bsg28kp63eae1u` FOREIGN KEY (`student_info_id`) REFERENCES `stu_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dom_user
-- ----------------------------
INSERT INTO `dom_user` VALUES ('1', '2020-05-27 15:46:03', '\0', '2020-05-27 15:46:03', 'e106efeb3cb54ec3850ef1f164fba2ac', '123', '123', 'aaa', '1', null, '2', '1', null);
INSERT INTO `dom_user` VALUES ('2', '2020-05-27 15:46:03', '\0', '2020-05-27 15:46:03', '5d48981c24c3415783c91e4e66305be5', '1234', '1234', 'aaaa', '1', null, '3', null, '1');
INSERT INTO `dom_user` VALUES ('3', '2020-05-27 15:46:03', '\0', '2020-05-27 15:46:03', '5211174da6cb455393e0bbac37189683', '12345', '12345', 'aaaaa', null, null, '1', null, null);
INSERT INTO `dom_user` VALUES ('7', '2020-09-10 14:30:32', '\0', '2020-09-10 14:30:32', 'f798a7a009f54aab9e1e3b54b042d33a', '123', '123', 'aab', '2', null, '2', '3', null);
INSERT INTO `dom_user` VALUES ('8', '2020-09-10 20:05:57', '\0', '2020-09-10 20:06:05', 'dfgdgdfg454534534erwetwet', '123', '123', 'aac', '2', null, '2', '4', null);
INSERT INTO `dom_user` VALUES ('9', '2020-12-22 12:16:20', '\0', '2020-12-22 12:16:20', '4bb710f6be21483da329c3cbaaf836fa', '123', '12312312312', '123', '1', null, '2', '5', null);

-- ----------------------------
-- Table structure for stu_info
-- ----------------------------
DROP TABLE IF EXISTS `stu_info`;
CREATE TABLE `stu_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `remove` bit(1) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `bed_number` int(11) NOT NULL,
  `building` varchar(255) NOT NULL,
  `dom_number` int(11) NOT NULL,
  `gender` int(11) NOT NULL,
  `leave_times` int(11) NOT NULL,
  `student_code` varchar(255) NOT NULL,
  `class_info_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpm1dgwm92xmd07l8t0wakbaec` (`class_info_id`),
  CONSTRAINT `FKpm1dgwm92xmd07l8t0wakbaec` FOREIGN KEY (`class_info_id`) REFERENCES `class_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_info
-- ----------------------------
INSERT INTO `stu_info` VALUES ('1', '2020-05-27 15:46:03', '\0', '2020-05-27 15:46:03', '58091cdf820f4332bc354a211a3da983', '1', '1', '1', '1', '1', '2', '1');
INSERT INTO `stu_info` VALUES ('3', '2020-09-10 14:30:32', '\0', '2020-09-10 14:30:32', 'f47d0b9caa194a618b27debce08c40b5', '1', '1', '1', '1', '1', '3', '2');
INSERT INTO `stu_info` VALUES ('4', '2020-09-10 20:05:10', '\0', '2020-09-10 20:05:16', 'sdfsdfsdfs324234234ssdfsdf', '1', '1', '1', '1', '1', '4', '2');
INSERT INTO `stu_info` VALUES ('5', '2020-12-22 12:16:19', '\0', '2020-12-22 12:16:19', '5c2d358cb30840a2a1d6881ecd1d7bb3', '0', '123', '123', '1', '0', '123', '1');

-- ----------------------------
-- Table structure for teach_info
-- ----------------------------
DROP TABLE IF EXISTS `teach_info`;
CREATE TABLE `teach_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `remove` bit(1) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `board_content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teach_info
-- ----------------------------
INSERT INTO `teach_info` VALUES ('1', '2020-05-27 15:46:03', '\0', '2020-05-27 15:46:03', 'ac63de35015047ecb07d9c9899dd33a6', 'weqw');
