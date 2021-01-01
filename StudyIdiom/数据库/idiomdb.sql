/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50506
Source Host           : localhost:3306
Source Database       : idiomdb

Target Server Type    : MYSQL
Target Server Version : 50506
File Encoding         : 65001

Date: 2021-01-01 16:21:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tbl_gameconn`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gameconn`;
CREATE TABLE `tbl_gameconn` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nowset` int(11) DEFAULT '1',
  `userId` int(11) DEFAULT '-1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_gameconn
-- ----------------------------
INSERT INTO `tbl_gameconn` VALUES ('1', '2', '3');
INSERT INTO `tbl_gameconn` VALUES ('2', '1', '4');

-- ----------------------------
-- Table structure for `tbl_gameguess`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_gameguess`;
CREATE TABLE `tbl_gameguess` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nowset` int(11) DEFAULT '1',
  `userId` int(11) DEFAULT '-1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_gameguess
-- ----------------------------
INSERT INTO `tbl_gameguess` VALUES ('1', '4', '3');
INSERT INTO `tbl_gameguess` VALUES ('3', '2', '4');

-- ----------------------------
-- Table structure for `tbl_user`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES ('1', '张三', '123');
INSERT INTO `tbl_user` VALUES ('2', '李四', '123');
INSERT INTO `tbl_user` VALUES ('3', 'zs', '123');
INSERT INTO `tbl_user` VALUES ('4', 'lisi', '123');
