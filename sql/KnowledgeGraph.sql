/*
 Navicat Premium Data Transfer

 Source Server         : aliUbuntu
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 8.140.23.240:3306
 Source Schema         : KnowledgeGraph

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 13/06/2021 16:14:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for graph
-- ----------------------------
DROP TABLE IF EXISTS `graph`;
CREATE TABLE `graph`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `objectName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for searchs
-- ----------------------------
DROP TABLE IF EXISTS `searchs`;
CREATE TABLE `searchs`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `recording` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sum` int(11) NULL DEFAULT NULL,
  `graphId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phonenumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `usertype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (13, '147258@qq.com', '$2a$10$uzelwc4hHDIsaDOKx4M1nu9pFGTszGWB76YpMi9IeE5rqaLj.KI4e', NULL, NULL, NULL);
INSERT INTO `user` VALUES (14, '111@qq.com', '111111', 'coin', '13592862039', NULL);
INSERT INTO `user` VALUES (15, 'zss@qq.com', '$2a$10$Jvcf5.AtJkug0njbYroe9uhCuNovjHftAS6OSlz/WFCsz7H4gqW.G', NULL, NULL, NULL);
INSERT INTO `user` VALUES (16, '789456@163.com', '$2a$10$re2I/9mTNIBcfBnP4aLqW.VGy0eWU8vRqjrISZTivd4VENtSLwuGe', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
