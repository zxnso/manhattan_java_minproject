/*
 Navicat Premium Data Transfer

 Source Server         : local_test
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : 127.0.0.1:3306
 Source Schema         : min_project

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 07/09/2020 23:16:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hot_word_human
-- ----------------------------
DROP TABLE IF EXISTS `hot_word_human`;
CREATE TABLE `hot_word_human`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `hot_word` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '热词',
  `sequence` tinyint(2) NOT NULL COMMENT '顺序',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '人工热词表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for hot_word_record
-- ----------------------------
DROP TABLE IF EXISTS `hot_word_record`;
CREATE TABLE `hot_word_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `hot_word` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '热词',
  `search_num` tinyint(8) NOT NULL DEFAULT 0 COMMENT '次数',
  `time_quantum` bigint(10) NOT NULL COMMENT '时间段',
  `create_time` datetime NOT NULL COMMENT '插入时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '热词搜索记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of hot_word_record
-- ----------------------------
INSERT INTO `hot_word_record` VALUES (1, '问问', 1, 2020090619, '2020-09-06 20:26:19');
INSERT INTO `hot_word_record` VALUES (2, '我问', 2, 2020090618, '2020-09-06 20:26:52');
INSERT INTO `hot_word_record` VALUES (3, '嗯嗯', 3, 2020090620, '2020-09-06 20:27:14');
INSERT INTO `hot_word_record` VALUES (4, '我问', 2, 2020090619, '2020-09-06 20:26:52');
INSERT INTO `hot_word_record` VALUES (5, '我问', 5, 2020090620, '2020-09-06 20:26:52');

-- ----------------------------
-- Table structure for hot_word_rule
-- ----------------------------
DROP TABLE IF EXISTS `hot_word_rule`;
CREATE TABLE `hot_word_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `refresh_cycle` tinyint(2) DEFAULT NULL COMMENT '更新周期(小时)',
  `hot_num` tinyint(2) DEFAULT NULL COMMENT '热词数量',
  `create_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_name` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) DEFAULT NULL COMMENT '更新人ID',
  `update_name` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='热词规则表';
-- ----------------------------
-- Table structure for hot_word_show
-- ----------------------------
DROP TABLE IF EXISTS `hot_word_show`;
CREATE TABLE `hot_word_show`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `hot_word` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '热词',
  `search_num` int(8) NULL DEFAULT NULL COMMENT '次数',
  `time_quantum` bigint(10) NOT NULL COMMENT '时间段',
  `sequence` tinyint(2) NOT NULL COMMENT '顺序',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '热词表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
