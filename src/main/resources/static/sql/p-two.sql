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

 Date: 14/05/2020 11:32:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `systime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (1, '活动No.1', '2020-05-07 13:37:55');

-- ----------------------------
-- Table structure for activity_user
-- ----------------------------
DROP TABLE IF EXISTS `activity_user`;
CREATE TABLE `activity_user`  (
  `appid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `activity_id` int(0) NOT NULL,
  `organizer_unionid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `member_unionid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `subscribe` int(0) NULL DEFAULT NULL,
  `systime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`activity_id`, `organizer_unionid`, `appid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_user
-- ----------------------------
INSERT INTO `activity_user` VALUES ('wx9a6fb162ec18f8e3', 1, 'zero', 'ojPllw5w_dZSM5fgKibW9arYFaSM', 1, '2020-05-09 22:11:38');

-- ----------------------------
-- Table structure for app_activity
-- ----------------------------
DROP TABLE IF EXISTS `app_activity`;
CREATE TABLE `app_activity`  (
  `appid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `activity_id` int(0) NULL DEFAULT NULL,
  `systime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`appid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of app_activity
-- ----------------------------
INSERT INTO `app_activity` VALUES ('wx9a6fb162ec18f8e3', 1, '2020-05-07 12:35:31');

-- ----------------------------
-- Table structure for app_user
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user`  (
  `appid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `unionid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `systime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`unionid`, `appid`, `openid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of app_user
-- ----------------------------
INSERT INTO `app_user` VALUES ('wx0969469f30ac0f3f', 'o9QXJ1UTi0Al6xMlKOY5qvIXPKwY', 'ojPllw5w_dZSM5fgKibW9arYFaSM', '2020-05-09 22:11:38');
INSERT INTO `app_user` VALUES ('wx9a6fb162ec18f8e3', 'oMlWBwqI2s4vll4XXcHw0XgUipug', 'ojPllw5w_dZSM5fgKibW9arYFaSM', '2020-05-09 22:12:06');
INSERT INTO `app_user` VALUES ('wx0969469f30ac0f3f', 'o9QXJ1U0OF7OKhO4i2WZM5BM2C84', 'ojPllwxaDc4A5hU0imNKuAhWx_bc', '2020-05-09 22:13:00');
INSERT INTO `app_user` VALUES ('wx9a6fb162ec18f8e3', 'oMlWBwgcaD_BrL7XEto6e1nYpfM8', 'ojPllwxaDc4A5hU0imNKuAhWx_bc', '2020-05-09 22:13:12');

-- ----------------------------
-- Table structure for wx_auth_event
-- ----------------------------
DROP TABLE IF EXISTS `wx_auth_event`;
CREATE TABLE `wx_auth_event`  (
  `appId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  `infoType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `authorizerAppid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `AuthorizationCode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `AuthorizationCodeExpiredTime` datetime(0) NULL DEFAULT NULL,
  `PreAuthCode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `systime` datetime(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_auth_event
-- ----------------------------
INSERT INTO `wx_auth_event` VALUES ('wxf5d8d7427ebabd90', '2020-05-09 21:41:09', 'unauthorized', 'wx9a6fb162ec18f8e3', NULL, NULL, NULL, '2020-05-09 21:41:12');
INSERT INTO `wx_auth_event` VALUES ('wxf5d8d7427ebabd90', '2020-05-09 21:42:12', 'unauthorized', 'wx0969469f30ac0f3f', NULL, NULL, NULL, '2020-05-09 21:42:14');
INSERT INTO `wx_auth_event` VALUES ('wxf5d8d7427ebabd90', '2020-05-09 21:44:23', 'authorized', 'wx0969469f30ac0f3f', 'queryauthcode@@@NaP_J8YmV06EH8bwLmu6ZSrXW32LcJBSysI7An3mdarXi6hNoeTZ4gq0BLLdVjVSZq1xjuPIXfFFcbQMxCNo9Q', '2020-05-09 22:44:23', 'preauthcode@@@4PBroHbrnYO4PG16kmOcJC4kCoxYTpdTMyDLNNu5haGnL9Wljn6nlf8irQumD5lS', '2020-05-09 21:44:26');
INSERT INTO `wx_auth_event` VALUES ('wxf5d8d7427ebabd90', '2020-05-09 21:44:53', 'authorized', 'wx9a6fb162ec18f8e3', 'queryauthcode@@@NaP_J8YmV06EH8bwLmu6ZdWr1qf-cLeHWOwzjT6ZZPXc5N8Ofzc_xvu08Bn3gD6tGGGP65IMRXJYvz-k2IG0nw', '2020-05-09 22:44:53', 'preauthcode@@@CB1RwjVZ8QQjRuOALKSIsRaqBar1XvkrvkkBMmOhkRHmIRxL-Y-vW0H-oqJX6291', '2020-05-09 21:44:55');

-- ----------------------------
-- Table structure for wx_auth_user_info
-- ----------------------------
DROP TABLE IF EXISTS `wx_auth_user_info`;
CREATE TABLE `wx_auth_user_info`  (
  `unionid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` int(0) NULL DEFAULT NULL,
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `headimgurl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `privilege` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `systime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`unionid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_auth_user_info
-- ----------------------------
INSERT INTO `wx_auth_user_info` VALUES ('ojPllw5w_dZSM5fgKibW9arYFaSM', 'o9QXJ1UTi0Al6xMlKOY5qvIXPKwY', '陈玉锋', 1, '黑龙江', '哈尔滨', '中国', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTK3sk1klAITIYXdhiaFJHQBjdia4fYszQOs6GIrv1HW65ZERpNQfKLeicnbFwt2m78tfEt77yuF7wmEw/132', NULL, '2020-05-09 22:11:38');
INSERT INTO `wx_auth_user_info` VALUES ('ojPllwxaDc4A5hU0imNKuAhWx_bc', 'o9QXJ1U0OF7OKhO4i2WZM5BM2C84', '十二盛夏', 2, '西藏', '昌都', '中国', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLG8YBANRYqjMK3ib0jqZ4ZwSgaqRJIARibhMoMoXARicnspkkeGmpd26udfoRgIjXc4FaDmBdKZykrw/132', NULL, '2020-05-09 22:13:00');

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
INSERT INTO `wx_authorization_info` VALUES ('wx0969469f30ac0f3f', '33_zsfaCXtZUGo4dpIVWqTOoPu9V0-z5WPvCiirjN8g-K1OxLx9qCKPbx3pxwSgYdOY7411Gp4RNb_zzlHEhrPl8ZlUkQ5WoZ5ApezvOVeCtrtZVJcLu5fKkr-nF5EgKLiMzKnXfW57PExJzdorTWYaAGDDGI', 7200, 'refreshtoken@@@H9jQ6Ni9eKhmeAFfJyd4f3lFEmBeYb2KcZy7yuw9iAE', NULL, '2020-05-09 21:44:26');
INSERT INTO `wx_authorization_info` VALUES ('wx9a6fb162ec18f8e3', '33_jl0i_uqZINL7ik9HQAKb5vFDGJsF4iLVHMNPnRHy1lv_ZEVSOnrZ2SRPb2MSt1zqzgFz5JsaIR4hQ6gsAHLraKV8MdiuYmvhu3bn50co0u6xf0lNbGQoH2YOxnfiwun0EZhSqiZnXugMu7w-FUOhALDYNH', 7200, 'refreshtoken@@@6YT4W4YyIc3k-DozgPmtnu2O0xOslaQF4zkxLDk8NaM', NULL, '2020-05-09 23:18:19');

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
INSERT INTO `wx_authorizer_info` VALUES ('wx0969469f30ac0f3f', '龙江镜界', 'http://wx.qlogo.cn/mmopen/Szib8ySqErWKWcAHmtWYeBcO7jrGIp69aYUU2GAY2ficAiaWQAabja9bnrQ4SPYDkm8uPtbEFT8xxbwiaBVeMaA4a8oYWn9FdsFT/0', NULL, NULL, 'gh_7cda915e2209', '哈尔滨谛越科技有限公司', 'lj-wenyi', NULL, 'http://mmbiz.qpic.cn/mmbiz_jpg/N8Tib7suknw26g2NpQrlq0RT2fBEibWo2lYGHj5L8bPx21zF0ujd5JI1A4zPiamag49SlfhIufIqquC6lvmcHeubg/0', NULL);
INSERT INTO `wx_authorizer_info` VALUES ('wx9a6fb162ec18f8e3', '娱闻瓜哥', 'http://wx.qlogo.cn/mmopen/HBpoicFupW10d3X3dMkF7sKqSykiarLbkzGics7NDCnKeYTMTKK9b8TcIH7HtfHxOtt04lHibaWT7EibWSbeqYzly7sBAtVYzkqmE/0', NULL, NULL, 'gh_5969d7ebc512', '哈尔滨谛越科技有限公司', 'yuwenguage', NULL, 'http://mmbiz.qpic.cn/mmbiz_jpg/tvUDUmSY7KmClVB5hAsDBE8yuquicOfsRAia6J8KRoRvyfJVTYzpWfibKCN7Xu3ibwBAYXpG4bblwnajibNLiceKRGug/0', NULL);

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
INSERT INTO `wx_component_access_token` VALUES ('33_xfvee53eyhSGLZO2-oaJw3cfR92Q6UNQeMTCV2JjM8eT0zONHbmMhJxZoHrveQ0sms1e2uokZYHp1I38M-R4ZyzEnMJsE-GbFrCCzpwRdeH_JgnKUEF--6bP9aDfwZ3YiYfI77YRB4Ujtk7qMGAjAFACWQ', 7200, '2020-05-09 21:29:06');
INSERT INTO `wx_component_access_token` VALUES ('33_G32QNVgLgoqKDZ7a9RbYthANFMNU1OQJPIqH75Ah3R6wKgWzReSQK-fiDTFZAg5C_P-UGGW-PlDPjRI5naOTaeoeRXpVz00Ud8BOfompuQiWnQj9jGr36eJd8aL507S0-wQNOKHhgiaDgp2qQWLjABAHNC', 7200, '2020-05-09 23:16:42');

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
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-09 23:01:39', 'component_verify_ticket', 'ticket@@@XSCqsiqMFEb7YqXxtzDHbStyCdNeLSTqwXlFgeiX_p6v4uY75pwowCJqT6TjbKzp3GQkj0yNAN5dLM67ajGj5Q', '2020-05-09 23:01:42');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-09 23:11:16', 'component_verify_ticket', 'ticket@@@XSCqsiqMFEb7YqXxtzDHbStyCdNeLSTqwXlFgeiX_p6v4uY75pwowCJqT6TjbKzp3GQkj0yNAN5dLM67ajGj5Q', '2020-05-09 23:11:19');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-09 23:11:16', 'component_verify_ticket', 'ticket@@@XSCqsiqMFEb7YqXxtzDHbStyCdNeLSTqwXlFgeiX_p6v4uY75pwowCJqT6TjbKzp3GQkj0yNAN5dLM67ajGj5Q', '2020-05-09 23:11:19');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-09 23:20:46', 'component_verify_ticket', 'ticket@@@XSCqsiqMFEb7YqXxtzDHbStyCdNeLSTqwXlFgeiX_p6v4uY75pwowCJqT6TjbKzp3GQkj0yNAN5dLM67ajGj5Q', '2020-05-09 23:20:49');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-09 23:20:46', 'component_verify_ticket', 'ticket@@@XSCqsiqMFEb7YqXxtzDHbStyCdNeLSTqwXlFgeiX_p6v4uY75pwowCJqT6TjbKzp3GQkj0yNAN5dLM67ajGj5Q', '2020-05-09 23:20:49');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-09 23:30:06', 'component_verify_ticket', 'ticket@@@XSCqsiqMFEb7YqXxtzDHbStyCdNeLSTqwXlFgeiX_p6v4uY75pwowCJqT6TjbKzp3GQkj0yNAN5dLM67ajGj5Q', '2020-05-09 23:30:09');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-09 23:30:06', 'component_verify_ticket', 'ticket@@@XSCqsiqMFEb7YqXxtzDHbStyCdNeLSTqwXlFgeiX_p6v4uY75pwowCJqT6TjbKzp3GQkj0yNAN5dLM67ajGj5Q', '2020-05-09 23:30:09');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-09 23:39:02', 'component_verify_ticket', 'ticket@@@XSCqsiqMFEb7YqXxtzDHbStyCdNeLSTqwXlFgeiX_p6v4uY75pwowCJqT6TjbKzp3GQkj0yNAN5dLM67ajGj5Q', '2020-05-09 23:39:04');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-09 23:39:02', 'component_verify_ticket', 'ticket@@@XSCqsiqMFEb7YqXxtzDHbStyCdNeLSTqwXlFgeiX_p6v4uY75pwowCJqT6TjbKzp3GQkj0yNAN5dLM67ajGj5Q', '2020-05-09 23:39:05');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-09 23:39:02', 'component_verify_ticket', 'ticket@@@XSCqsiqMFEb7YqXxtzDHbStyCdNeLSTqwXlFgeiX_p6v4uY75pwowCJqT6TjbKzp3GQkj0yNAN5dLM67ajGj5Q', '2020-05-09 23:39:05');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-09 23:48:39', 'component_verify_ticket', 'ticket@@@XSCqsiqMFEb7YqXxtzDHbStyCdNeLSTqwXlFgeiX_p6v4uY75pwowCJqT6TjbKzp3GQkj0yNAN5dLM67ajGj5Q', '2020-05-09 23:48:42');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-09 23:48:39', 'component_verify_ticket', 'ticket@@@XSCqsiqMFEb7YqXxtzDHbStyCdNeLSTqwXlFgeiX_p6v4uY75pwowCJqT6TjbKzp3GQkj0yNAN5dLM67ajGj5Q', '2020-05-09 23:48:42');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-14 11:28:11', 'component_verify_ticket', 'ticket@@@yAbenhz2LOdvM_GSUXhmtF4j9ipy7FZVxmjhjmWyGiQCZRWliTpkbX7iDIRpaLBRDf-9kJ0c6klWskHWefG2hA', '2020-05-14 11:28:17');
INSERT INTO `wx_component_verify_ticket` VALUES ('wxf5d8d7427ebabd90', '2020-05-14 11:28:11', 'component_verify_ticket', 'ticket@@@yAbenhz2LOdvM_GSUXhmtF4j9ipy7FZVxmjhjmWyGiQCZRWliTpkbX7iDIRpaLBRDf-9kJ0c6klWskHWefG2hA', '2020-05-14 11:28:18');

SET FOREIGN_KEY_CHECKS = 1;
