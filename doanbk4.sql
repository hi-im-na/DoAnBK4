/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3307
 Source Schema         : student_management

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 08/06/2020 09:53:12
*/
Create database personnn;
use personnn;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `encryted_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `full_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `email_update` (`email`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 'phuongnh@rikkeisoft.com', '$2a$10$fHd2qSm7QxjFnHcol2VRHOfy.HlLnavWSg3cd.cGMkxbbIGfYwU2.', 'Nguyen Ho Phuong');
INSERT INTO `personnn`.`account` (`id`, `email`, `encryted_password`, `full_name`) VALUES ('2', 'thuan@gmail.com', '$10$fHd2qSm7QxjFnHcol2VRHOfy.HlLnavWSg3cd.cGMkxbbIGfYwU2.', 'Bui Sy Thuan');

-- ----------------------------
-- Table structure for account_role
-- ----------------------------
DROP TABLE IF EXISTS `account_role`;
CREATE TABLE `account_role`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `account_id` int(11) UNSIGNED NULL DEFAULT NULL,
  `role_id` int(11) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  FOREIGN KEY (`account_id`) REFERENCES `account`(`id`) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`role_id`) REFERENCES `role`(`id`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account_role
-- ----------------------------
INSERT INTO `account_role` VALUES (1, 1, 1);
INSERT INTO `account_role` VALUES (2, 2, 2);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin', 'Administrator');
INSERT INTO `role` VALUES (2, 'user', 'Personal');


-- ----------------------------
-- Table structure for location
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location`	(
	`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `location_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of location
-- ----------------------------
INSERT INTO `personnn`.`location` (`location_name`) VALUES ('phòng a');
INSERT INTO `personnn`.`location` (`location_name`) VALUES ('phòng b');

-- ----------------------------
-- Table structure for plan
-- ----------------------------
DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan`	(
	`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `location_id` int(11) UNSIGNED NOT NULL,
    `begin_time` datetime NOT NULL,
    `end_time` datetime NOT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    FOREIGN KEY (`location_id`) REFERENCES `location`(`id`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of plan
-- ----------------------------
INSERT INTO `personnn`.`plan` (`location_id`, `begin_time`, `end_time`) VALUES ('1', '2020-06-26 10:10:00', '2020-06-26 10:15:00');
INSERT INTO `personnn`.`plan` (`location_id`, `begin_time`, `end_time`) VALUES ('2', '2020-06-26 10:10:00', '2020-06-26 10:10:00');

-- ----------------------------
-- Table structure for account_plan
-- ----------------------------
DROP TABLE IF EXISTS `account_plan`;
CREATE TABLE `account_plan`	(
	`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
	`account_id` int(11) UNSIGNED NULL DEFAULT NULL,
    `plan_id` int(11) UNSIGNED NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
    FOREIGN KEY (`plan_id`) REFERENCES `plan`(`id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account_plan
-- ----------------------------
INSERT INTO `personnn`.`account_plan` (`account_id`, `plan_id`) VALUES ('1', '1');
INSERT INTO `personnn`.`account_plan` (`account_id`, `plan_id`) VALUES ('2', '2');


SET FOREIGN_KEY_CHECKS = 1;