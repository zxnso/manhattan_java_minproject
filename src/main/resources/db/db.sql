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

 Date: 08/09/2020 23:51:52
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
  `sequence` tinyint(4) NOT NULL COMMENT '顺序',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '人工热词表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of hot_word_human
-- ----------------------------
INSERT INTO `hot_word_human` VALUES (1, 'aa', 3, NULL, NULL, NULL);
INSERT INTO `hot_word_human` VALUES (2, 'aaa', 5, NULL, NULL, '2020-09-08 20:38:38');
INSERT INTO `hot_word_human` VALUES (3, 'wwww', 2, NULL, NULL, '2020-09-08 20:43:30');
INSERT INTO `hot_word_human` VALUES (4, 'aaaaaaa', 8, NULL, NULL, '2020-09-08 20:48:58');
INSERT INTO `hot_word_human` VALUES (5, 'eeeee', 1, NULL, NULL, '2020-09-08 21:15:05');
INSERT INTO `hot_word_human` VALUES (6, '222', 9, NULL, NULL, '2020-09-08 21:18:36');
INSERT INTO `hot_word_human` VALUES (11, '777', 1, NULL, NULL, '2020-09-08 23:26:15');

-- ----------------------------
-- Table structure for hot_word_record
-- ----------------------------
DROP TABLE IF EXISTS `hot_word_record`;
CREATE TABLE `hot_word_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `hot_word` varchar(100) NOT NULL COMMENT '热词',
  `search_num` tinyint(8) NOT NULL DEFAULT '0' COMMENT '次数',
  `time_quantum` bigint(10) NOT NULL COMMENT '时间段',
  `create_time` datetime NOT NULL COMMENT '插入时间',
  PRIMARY KEY (`id`),
  KEY `hot_word` (`hot_word`),
  KEY `search_num` (`search_num`),
  KEY `time_quantum` (`time_quantum`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='热词搜索记录表';

-- ----------------------------
-- Records of hot_word_record
-- ----------------------------
INSERT INTO `hot_word_record` VALUES (1, '问问', 1, 2020090619, '2020-09-06 20:26:19');
INSERT INTO `hot_word_record` VALUES (2, '我问', 2, 2020090618, '2020-09-06 20:26:52');
INSERT INTO `hot_word_record` VALUES (3, '嗯嗯', 3, 2020090620, '2020-09-06 20:27:14');
INSERT INTO `hot_word_record` VALUES (4, '我问', 2, 2020090619, '2020-09-06 20:26:52');
INSERT INTO `hot_word_record` VALUES (5, '我问', 5, 2020090620, '2020-09-06 20:26:52');
INSERT INTO `hot_word_record` VALUES (6, '啦啦啦', 1, 2020090821, '2020-09-08 21:39:54');
INSERT INTO `hot_word_record` VALUES (7, '222', 1, 2020090821, '2020-09-08 21:53:40');

-- ----------------------------
-- Table structure for hot_word_rule
-- ----------------------------
DROP TABLE IF EXISTS `hot_word_rule`;
CREATE TABLE `hot_word_rule`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `refresh_cycle` tinyint(2) NULL DEFAULT NULL COMMENT '更新周期(小时)',
  `hot_num` tinyint(2) NULL DEFAULT NULL COMMENT '热词数量',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '热词规则表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of hot_word_rule
-- ----------------------------
INSERT INTO `hot_word_rule` VALUES (1, 1, 12, NULL, NULL, NULL, NULL, NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '热词表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of hot_word_show
-- ----------------------------
INSERT INTO `hot_word_show` VALUES (1, 'eeeee', NULL, 2020090801, 1, '2020-09-08 23:25:42');
INSERT INTO `hot_word_show` VALUES (2, 'wwww', NULL, 2020090801, 2, '2020-09-08 23:25:42');
INSERT INTO `hot_word_show` VALUES (3, 'aa', NULL, 2020090801, 3, '2020-09-08 23:25:42');
INSERT INTO `hot_word_show` VALUES (4, '啦啦啦', 1, 2020090801, 4, '2020-09-08 23:25:42');
INSERT INTO `hot_word_show` VALUES (5, 'aaa', NULL, 2020090801, 5, '2020-09-08 23:25:42');
INSERT INTO `hot_word_show` VALUES (6, '222', NULL, 2020090801, 6, '2020-09-08 23:25:42');
INSERT INTO `hot_word_show` VALUES (7, 'aaaaaaa', NULL, 2020090801, 7, '2020-09-08 23:25:42');
INSERT INTO `hot_word_show` VALUES (8, 'eeeee', NULL, 2020090801, 1, '2020-09-08 23:26:26');
INSERT INTO `hot_word_show` VALUES (9, '777', NULL, 2020090801, 2, '2020-09-08 23:26:26');
INSERT INTO `hot_word_show` VALUES (10, 'wwww', NULL, 2020090801, 3, '2020-09-08 23:26:26');
INSERT INTO `hot_word_show` VALUES (11, 'aa', NULL, 2020090801, 4, '2020-09-08 23:26:26');
INSERT INTO `hot_word_show` VALUES (12, 'aaa', NULL, 2020090801, 5, '2020-09-08 23:26:26');
INSERT INTO `hot_word_show` VALUES (13, '啦啦啦', 1, 2020090801, 6, '2020-09-08 23:26:26');
INSERT INTO `hot_word_show` VALUES (14, 'aaaaaaa', NULL, 2020090801, 7, '2020-09-08 23:26:26');
INSERT INTO `hot_word_show` VALUES (15, '222', NULL, 2020090801, 8, '2020-09-08 23:26:26');
INSERT INTO `hot_word_show` VALUES (16, 'eeeee', NULL, 2020090801, 1, '2020-09-08 23:46:16');
INSERT INTO `hot_word_show` VALUES (17, '777', NULL, 2020090801, 2, '2020-09-08 23:46:16');
INSERT INTO `hot_word_show` VALUES (18, 'wwww', NULL, 2020090801, 3, '2020-09-08 23:46:16');
INSERT INTO `hot_word_show` VALUES (19, 'aa', NULL, 2020090801, 4, '2020-09-08 23:46:16');
INSERT INTO `hot_word_show` VALUES (20, 'aaa', NULL, 2020090801, 5, '2020-09-08 23:46:16');
INSERT INTO `hot_word_show` VALUES (21, '啦啦啦', 1, 2020090801, 6, '2020-09-08 23:46:16');
INSERT INTO `hot_word_show` VALUES (22, 'aaaaaaa', NULL, 2020090801, 7, '2020-09-08 23:46:16');
INSERT INTO `hot_word_show` VALUES (23, '222', NULL, 2020090801, 8, '2020-09-08 23:46:16');
INSERT INTO `hot_word_show` VALUES (24, 'eeeee', NULL, 2020090801, 1, '2020-09-08 23:46:23');
INSERT INTO `hot_word_show` VALUES (25, '777', NULL, 2020090801, 2, '2020-09-08 23:46:23');
INSERT INTO `hot_word_show` VALUES (26, 'wwww', NULL, 2020090801, 3, '2020-09-08 23:46:23');
INSERT INTO `hot_word_show` VALUES (27, 'aa', NULL, 2020090801, 4, '2020-09-08 23:46:23');
INSERT INTO `hot_word_show` VALUES (28, 'aaa', NULL, 2020090801, 5, '2020-09-08 23:46:23');
INSERT INTO `hot_word_show` VALUES (29, '啦啦啦', 1, 2020090801, 6, '2020-09-08 23:46:23');
INSERT INTO `hot_word_show` VALUES (30, 'aaaaaaa', NULL, 2020090801, 7, '2020-09-08 23:46:23');
INSERT INTO `hot_word_show` VALUES (31, '222', NULL, 2020090801, 8, '2020-09-08 23:46:23');

SET FOREIGN_KEY_CHECKS = 1;
