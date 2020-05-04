/*
 Navicat Premium Data Transfer

 Source Server         : local-mysql
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : p-two

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 03/05/2020 11:51:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for wx_authorization_info
-- ----------------------------
DROP TABLE IF EXISTS `wx_authorization_info`;
CREATE TABLE `wx_authorization_info`  (
  `authorizer_appid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `authorizer_access_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `expires_in` int(0) NULL DEFAULT NULL,
  `authorizer_refresh_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `func_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `systime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`authorizer_appid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_authorization_info
-- ----------------------------
INSERT INTO `wx_authorization_info` VALUES ('wx7d04f2e04d2c7dad', '32_Y0EN7oSQpBb_yW1cAHMtslAHSfZrWaqB8iPxdTPWvloRINy3outTm6YYYXL5hoSEerOVfQv13d5PKuk6_cpXhczt1rHh5p-LzRo-w-UaDdZ_XspjWJnmmCVysAU3sIi_BkLwf5miCty24LleKKJcAMDNNM', 7200, 'refreshtoken@@@PN-YPnN-YLpre6rDRmKvnHCtja9_8cRXkBAXjNv7kzY', NULL, '2020-05-03 11:47:11');

-- ----------------------------
-- Table structure for wx_authorizer_info
-- ----------------------------
DROP TABLE IF EXISTS `wx_authorizer_info`;
CREATE TABLE `wx_authorizer_info`  (
  `authorizer_appid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `head_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `service_type_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `verify_type_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `principal_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `alias` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `business_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `qrcode_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `systime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`authorizer_appid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_authorizer_info
-- ----------------------------
INSERT INTO `wx_authorizer_info` VALUES ('wx7d04f2e04d2c7dad', '', 'http://wx.qlogo.cn/mmopen/HBpoicFupW10d3X3dMkF7sLQMVicI1jm8jEzlZRXwzEM499PtW9jt9IE3GajZjV7Mp4FdeRRbd85LrQXnh6bzk6YboWqEfD97x/0', NULL, NULL, 'gh_294870642cba', '个人', '', NULL, 'http://mmbiz.qpic.cn/mmbiz_jpg/LSmlLG92FugdJMFJM0ibyBJQabDwZS6uvs0LqHmbEaDtWfT5hBUdkpaCwnbzvJaoDTibPY0NTC4hBYJfnfQoeNfg/0', NULL);

-- ----------------------------
-- Table structure for wx_component_access_token
-- ----------------------------
DROP TABLE IF EXISTS `wx_component_access_token`;
CREATE TABLE `wx_component_access_token`  (
  `component_access_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `expires_in` int(0) NULL DEFAULT NULL,
  `systime` datetime(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_component_access_token
-- ----------------------------
INSERT INTO `wx_component_access_token` VALUES ('32_xtJNb3XgU0U3vH5NHhsVg5bR2Jy_FXMOXcdd_bNyomAP2HcOfXMP2bJRXr-Jo_7PmK4GnOyQFI0G2AD0o1aJkDRpjImLiVnDQXKbgZjTLaVLA2LRpzbhxfxTd5Sq3C8RNv0uAZ70UUulehKzVWUdAAAABZ', 7200, '2020-05-02 01:42:40');
INSERT INTO `wx_component_access_token` VALUES ('32_26FVt30TqOBL1QfVawzIL7dB1aRQHAxL5gf6CxX2swBVlmVeGOf4I_b3UndD1Uxki45oE_87Yjq0RUKQ4acCx8LIVdub2BsdlvfG86L3LKpKcw1Q3hp0DbnIRd8jRnnr1tgC19L9YDcAz_EiGDKdADAAXE', 7200, '2020-05-03 11:11:26');

-- ----------------------------
-- Table structure for wx_component_verify_ticket
-- ----------------------------
DROP TABLE IF EXISTS `wx_component_verify_ticket`;
CREATE TABLE `wx_component_verify_ticket`  (
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  `infoType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `componentVerifyTicket` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `systime` datetime(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_component_verify_ticket
-- ----------------------------
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-02 02:56:48', 'component_verify_ticket', 'ticket@@@TgAJB92yRsvqFUXdtNH9KAu4Ia3AsDTehLxpH3rvdVBCKEfVoNA4pAPFfufX-iDmOZVVRblh88pwhRmTudDycQ', '2020-05-02 02:56:51');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-03 10:17:13', 'component_verify_ticket', 'ticket@@@14rzOyf-LSdPtLdH7bA0tNVgMXQb1h0qIeNHdD9NIOY7dIKKWk_61aSl-L6IdZqtkBOHzpbhSZ0cD5CyJ4xQUQ', '2020-05-03 10:17:17');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-03 10:17:13', 'component_verify_ticket', 'ticket@@@14rzOyf-LSdPtLdH7bA0tNVgMXQb1h0qIeNHdD9NIOY7dIKKWk_61aSl-L6IdZqtkBOHzpbhSZ0cD5CyJ4xQUQ', '2020-05-03 10:17:20');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-03 10:17:13', 'component_verify_ticket', 'ticket@@@14rzOyf-LSdPtLdH7bA0tNVgMXQb1h0qIeNHdD9NIOY7dIKKWk_61aSl-L6IdZqtkBOHzpbhSZ0cD5CyJ4xQUQ', '2020-05-03 10:17:20');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-03 10:27:31', 'component_verify_ticket', 'ticket@@@14rzOyf-LSdPtLdH7bA0tNVgMXQb1h0qIeNHdD9NIOY7dIKKWk_61aSl-L6IdZqtkBOHzpbhSZ0cD5CyJ4xQUQ', '2020-05-03 10:27:35');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-03 10:37:42', 'component_verify_ticket', 'ticket@@@14rzOyf-LSdPtLdH7bA0tNVgMXQb1h0qIeNHdD9NIOY7dIKKWk_61aSl-L6IdZqtkBOHzpbhSZ0cD5CyJ4xQUQ', '2020-05-03 10:37:44');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-03 10:37:42', 'component_verify_ticket', 'ticket@@@14rzOyf-LSdPtLdH7bA0tNVgMXQb1h0qIeNHdD9NIOY7dIKKWk_61aSl-L6IdZqtkBOHzpbhSZ0cD5CyJ4xQUQ', '2020-05-03 10:37:46');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-03 10:47:19', 'component_verify_ticket', 'ticket@@@14rzOyf-LSdPtLdH7bA0tNVgMXQb1h0qIeNHdD9NIOY7dIKKWk_61aSl-L6IdZqtkBOHzpbhSZ0cD5CyJ4xQUQ', '2020-05-03 10:47:22');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-03 10:47:19', 'component_verify_ticket', 'ticket@@@14rzOyf-LSdPtLdH7bA0tNVgMXQb1h0qIeNHdD9NIOY7dIKKWk_61aSl-L6IdZqtkBOHzpbhSZ0cD5CyJ4xQUQ', '2020-05-03 10:47:23');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-03 10:57:01', 'component_verify_ticket', 'ticket@@@14rzOyf-LSdPtLdH7bA0tNVgMXQb1h0qIeNHdD9NIOY7dIKKWk_61aSl-L6IdZqtkBOHzpbhSZ0cD5CyJ4xQUQ', '2020-05-03 10:57:03');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-03 10:57:02', 'component_verify_ticket', 'ticket@@@14rzOyf-LSdPtLdH7bA0tNVgMXQb1h0qIeNHdD9NIOY7dIKKWk_61aSl-L6IdZqtkBOHzpbhSZ0cD5CyJ4xQUQ', '2020-05-03 10:57:03');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-03 11:12:08', 'component_verify_ticket', 'ticket@@@QK8Jsyw2aVJi-WHoJ2Uo94qJH02Fn_UKqb_kQSX0vfC5--YLzNcQKj00ZsJ6kB6k8aDsTpVShgDwER4WSeypEA', '2020-05-03 11:12:10');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-03 11:22:10', 'component_verify_ticket', 'ticket@@@QK8Jsyw2aVJi-WHoJ2Uo94qJH02Fn_UKqb_kQSX0vfC5--YLzNcQKj00ZsJ6kB6k8aDsTpVShgDwER4WSeypEA', '2020-05-03 11:22:13');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-03 11:23:06', 'component_verify_ticket', 'ticket@@@QK8Jsyw2aVJi-WHoJ2Uo94qJH02Fn_UKqb_kQSX0vfC5--YLzNcQKj00ZsJ6kB6k8aDsTpVShgDwER4WSeypEA', '2020-05-03 11:23:07');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-03 11:33:00', 'component_verify_ticket', 'ticket@@@QK8Jsyw2aVJi-WHoJ2Uo94qJH02Fn_UKqb_kQSX0vfC5--YLzNcQKj00ZsJ6kB6k8aDsTpVShgDwER4WSeypEA', '2020-05-03 11:33:03');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-03 11:33:00', 'component_verify_ticket', 'ticket@@@QK8Jsyw2aVJi-WHoJ2Uo94qJH02Fn_UKqb_kQSX0vfC5--YLzNcQKj00ZsJ6kB6k8aDsTpVShgDwER4WSeypEA', '2020-05-03 11:33:03');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-03 11:33:00', 'component_verify_ticket', 'ticket@@@QK8Jsyw2aVJi-WHoJ2Uo94qJH02Fn_UKqb_kQSX0vfC5--YLzNcQKj00ZsJ6kB6k8aDsTpVShgDwER4WSeypEA', '2020-05-03 11:33:05');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-03 11:33:07', 'component_verify_ticket', 'ticket@@@QK8Jsyw2aVJi-WHoJ2Uo94qJH02Fn_UKqb_kQSX0vfC5--YLzNcQKj00ZsJ6kB6k8aDsTpVShgDwER4WSeypEA', '2020-05-03 11:33:07');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-03 11:42:47', 'component_verify_ticket', 'ticket@@@QK8Jsyw2aVJi-WHoJ2Uo94qJH02Fn_UKqb_kQSX0vfC5--YLzNcQKj00ZsJ6kB6k8aDsTpVShgDwER4WSeypEA', '2020-05-03 11:42:49');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-03 11:42:47', 'component_verify_ticket', 'ticket@@@QK8Jsyw2aVJi-WHoJ2Uo94qJH02Fn_UKqb_kQSX0vfC5--YLzNcQKj00ZsJ6kB6k8aDsTpVShgDwER4WSeypEA', '2020-05-03 11:42:50');

SET FOREIGN_KEY_CHECKS = 1;
