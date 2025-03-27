/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MariaDB
 Source Server Version : 101109 (10.11.9-MariaDB-ubu2204)
 Source Host           : 127.0.0.1:3306
 Source Schema         : by-email

 Target Server Type    : MariaDB
 Target Server Version : 101109 (10.11.9-MariaDB-ubu2204)
 File Encoding         : 65001

 Date: 25/03/2025 07:57:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for discuss_post
-- ----------------------------
DROP TABLE IF EXISTS `discuss_post`;
CREATE TABLE `discuss_post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` text DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0-普通; ',
  `status` int(11) DEFAULT NULL COMMENT '0未读 1已读',
  `create_time` timestamp NULL DEFAULT NULL,
  `comment_count` int(11) DEFAULT NULL,
  `score` double DEFAULT NULL,
  `to_user_id` int(11) DEFAULT NULL COMMENT '接收人user_id',
  `mail_type` varchar(10) DEFAULT NULL COMMENT '邮件类型',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of discuss_post
-- ----------------------------
BEGIN;
INSERT INTO `discuss_post` (`id`, `user_id`, `title`, `content`, `type`, `status`, `create_time`, `comment_count`, `score`, `to_user_id`, `mail_type`) VALUES (1, 102, 'ceshi yixia a ', 'MEc6z7b7i2zq12hUjdlZS8RZRHGeim62twIxWTWB6kHyLydU78JS87h5+4SIsPG+4v9gIB1WBY/RNpd1VNaj8Pr0IsT3XWhk+Z7kHI4mfvMZMUs+LBAlmv3BdNFGe38pHPUOMM6j9Vxhksc3Bsqjq83W0AO0EfgWtO2BcmxrOf0=', 0, 0, '2022-03-01 16:10:02', 0, 0, NULL, NULL);
INSERT INTO `discuss_post` (`id`, `user_id`, `title`, `content`, `type`, `status`, `create_time`, `comment_count`, `score`, `to_user_id`, `mail_type`) VALUES (2, 102, '你好啊wedding', 'MEc6z7b7i2zq12hUjdlZS8RZRHGeim62twIxWTWB6kHyLydU78JS87h5+4SIsPG+4v9gIB1WBY/RNpd1VNaj8Pr0IsT3XWhk+Z7kHI4mfvMZMUs+LBAlmv3BdNFGe38pHPUOMM6j9Vxhksc3Bsqjq83W0AO0EfgWtO2BcmxrOf0=', 0, 0, '2022-03-01 16:11:32', 0, 0, NULL, NULL);
INSERT INTO `discuss_post` (`id`, `user_id`, `title`, `content`, `type`, `status`, `create_time`, `comment_count`, `score`, `to_user_id`, `mail_type`) VALUES (3, 102, '123', 'MEc6z7b7i2zq12hUjdlZS8RZRHGeim62twIxWTWB6kHyLydU78JS87h5+4SIsPG+4v9gIB1WBY/RNpd1VNaj8Pr0IsT3XWhk+Z7kHI4mfvMZMUs+LBAlmv3BdNFGe38pHPUOMM6j9Vxhksc3Bsqjq83W0AO0EfgWtO2BcmxrOf0=', 0, 0, '2022-03-01 16:21:38', 0, 0, NULL, NULL);
INSERT INTO `discuss_post` (`id`, `user_id`, `title`, `content`, `type`, `status`, `create_time`, `comment_count`, `score`, `to_user_id`, `mail_type`) VALUES (4, 102, '来看看好吃的土豆吧', 'MEc6z7b7i2zq12hUjdlZS8RZRHGeim62twIxWTWB6kHyLydU78JS87h5+4SIsPG+4v9gIB1WBY/RNpd1VNaj8Pr0IsT3XWhk+Z7kHI4mfvMZMUs+LBAlmv3BdNFGe38pHPUOMM6j9Vxhksc3Bsqjq83W0AO0EfgWtO2BcmxrOf0=', 0, 0, '2022-03-01 16:22:08', 1, 0, NULL, NULL);
INSERT INTO `discuss_post` (`id`, `user_id`, `title`, `content`, `type`, `status`, `create_time`, `comment_count`, `score`, `to_user_id`, `mail_type`) VALUES (5, 102, '123321', 'MEc6z7b7i2zq12hUjdlZS8RZRHGeim62twIxWTWB6kHyLydU78JS87h5+4SIsPG+4v9gIB1WBY/RNpd1VNaj8Pr0IsT3XWhk+Z7kHI4mfvMZMUs+LBAlmv3BdNFGe38pHPUOMM6j9Vxhksc3Bsqjq83W0AO0EfgWtO2BcmxrOf0=', 0, 1, '2022-03-05 16:17:21', NULL, NULL, 103, NULL);
INSERT INTO `discuss_post` (`id`, `user_id`, `title`, `content`, `type`, `status`, `create_time`, `comment_count`, `score`, `to_user_id`, `mail_type`) VALUES (7, 104, '你好啊abc123,很高兴认识你啊', 'MEc6z7b7i2zq12hUjdlZS8RZRHGeim62twIxWTWB6kHyLydU78JS87h5+4SIsPG+4v9gIB1WBY/RNpd1VNaj8Pr0IsT3XWhk+Z7kHI4mfvMZMUs+LBAlmv3BdNFGe38pHPUOMM6j9Vxhksc3Bsqjq83W0AO0EfgWtO2BcmxrOf0=', 0, 1, '2022-03-05 18:55:38', NULL, NULL, 102, NULL);
INSERT INTO `discuss_post` (`id`, `user_id`, `title`, `content`, `type`, `status`, `create_time`, `comment_count`, `score`, `to_user_id`, `mail_type`) VALUES (8, 105, '你好啊张三 很高兴认识你', 'MEc6z7b7i2zq12hUjdlZS8RZRHGeim62twIxWTWB6kHyLydU78JS87h5+4SIsPG+4v9gIB1WBY/RNpd1VNaj8Pr0IsT3XWhk+Z7kHI4mfvMZMUs+LBAlmv3BdNFGe38pHPUOMM6j9Vxhksc3Bsqjq83W0AO0EfgWtO2BcmxrOf0=', 0, 1, '2022-03-05 19:50:17', NULL, NULL, 106, NULL);
INSERT INTO `discuss_post` (`id`, `user_id`, `title`, `content`, `type`, `status`, `create_time`, `comment_count`, `score`, `to_user_id`, `mail_type`) VALUES (9, 106, '你好啊你白 收到你的邮件我很开心', 'MEc6z7b7i2zq12hUjdlZS8RZRHGeim62twIxWTWB6kHyLydU78JS87h5+4SIsPG+4v9gIB1WBY/RNpd1VNaj8Pr0IsT3XWhk+Z7kHI4mfvMZMUs+LBAlmv3BdNFGe38pHPUOMM6j9Vxhksc3Bsqjq83W0AO0EfgWtO2BcmxrOf0=', 0, 1, '2022-03-05 19:52:56', NULL, NULL, 105, NULL);
INSERT INTO `discuss_post` (`id`, `user_id`, `title`, `content`, `type`, `status`, `create_time`, `comment_count`, `score`, `to_user_id`, `mail_type`) VALUES (10, 106, '谢谢你啊', 'MEc6z7b7i2zq12hUjdlZS8RZRHGeim62twIxWTWB6kHyLydU78JS87h5+4SIsPG+4v9gIB1WBY/RNpd1VNaj8Pr0IsT3XWhk+Z7kHI4mfvMZMUs+LBAlmv3BdNFGe38pHPUOMM6j9Vxhksc3Bsqjq83W0AO0EfgWtO2BcmxrOf0=', 0, 1, '2022-03-05 19:53:26', NULL, NULL, 105, NULL);
INSERT INTO `discuss_post` (`id`, `user_id`, `title`, `content`, `type`, `status`, `create_time`, `comment_count`, `score`, `to_user_id`, `mail_type`) VALUES (11, 105, '测试主题-123', 'MEc6z7b7i2zq12hUjdlZS8RZRHGeim62twIxWTWB6kHyLydU78JS87h5+4SIsPG+4v9gIB1WBY/RNpd1VNaj8Pr0IsT3XWhk+Z7kHI4mfvMZMUs+LBAlmv3BdNFGe38pHPUOMM6j9Vxhksc3Bsqjq83W0AO0EfgWtO2BcmxrOf0=', 0, 0, '2022-03-16 18:14:22', NULL, NULL, 106, NULL);
INSERT INTO `discuss_post` (`id`, `user_id`, `title`, `content`, `type`, `status`, `create_time`, `comment_count`, `score`, `to_user_id`, `mail_type`) VALUES (12, 103, '22111', 'MN/BDMqQaG0vMC9opOQYvQX//HLq3ojViKBdONu/B8Z0AUSNPvLeQGG+NF480+jlsvNamZxS17O6bYoI+6MdFnG4iYkKatTvp0rAJwIBldufdChO0AhK/Y/rcv4d6eH7ezvoTzsZZ4QYk3tnP7NJOnFv6IgNjL71Z1xCOFhj4cE=', 0, 0, '2025-03-19 16:47:49', NULL, NULL, 107, '2');
INSERT INTO `discuss_post` (`id`, `user_id`, `title`, `content`, `type`, `status`, `create_time`, `comment_count`, `score`, `to_user_id`, `mail_type`) VALUES (13, 103, '111', 'MN/BDMqQaG0vMC9opOQYvQX//HLq3ojViKBdONu/B8Z0AUSNPvLeQGG+NF480+jlsvNamZxS17O6bYoI+6MdFnG4iYkKatTvp0rAJwIBldufdChO0AhK/Y/rcv4d6eH7ezvoTzsZZ4QYk3tnP7NJOnFv6IgNjL71Z1xCOFhj4cE=', 0, 0, '2025-03-19 16:48:47', NULL, NULL, 107, '2');
INSERT INTO `discuss_post` (`id`, `user_id`, `title`, `content`, `type`, `status`, `create_time`, `comment_count`, `score`, `to_user_id`, `mail_type`) VALUES (14, 103, '111', 'MN/BDMqQaG0vMC9opOQYvQX//HLq3ojViKBdONu/B8Z0AUSNPvLeQGG+NF480+jlsvNamZxS17O6bYoI+6MdFnG4iYkKatTvp0rAJwIBldufdChO0AhK/Y/rcv4d6eH7ezvoTzsZZ4QYk3tnP7NJOnFv6IgNjL71Z1xCOFhj4cE=', 0, 0, '2025-03-19 16:49:01', NULL, NULL, 107, '2');
INSERT INTO `discuss_post` (`id`, `user_id`, `title`, `content`, `type`, `status`, `create_time`, `comment_count`, `score`, `to_user_id`, `mail_type`) VALUES (15, 103, '111', 'F0PzDXoySeY9ZozLXUVlgzr4kuK7ZONi5pJBmmyXljEYbgtPvVa7yXOBctN01bqzQrLZiq0TeRwSK9Y+tnHbOItMAieIMej77ybp/G7bUO9rMXq3SV9UxCcwRYX9EFWG9YCHXK0TZZesQ2/Oyy879SiwJxKuaHzEn1+JVvhOqKg=', 0, 0, '2025-03-19 20:09:15', NULL, NULL, 108, '2');
COMMIT;

-- ----------------------------
-- Table structure for drafts
-- ----------------------------
DROP TABLE IF EXISTS `drafts`;
CREATE TABLE `drafts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` text DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0-普通; ',
  `create_time` timestamp NULL DEFAULT NULL,
  `to_user` varchar(100) DEFAULT NULL COMMENT '接收人user信息',
  `mail_type` varchar(10) DEFAULT NULL COMMENT '邮件类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=55667791 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci ROW_FORMAT=DYNAMIC COMMENT='草稿箱表';

-- ----------------------------
-- Records of drafts
-- ----------------------------
BEGIN;
INSERT INTO `drafts` (`id`, `user_id`, `title`, `content`, `type`, `create_time`, `to_user`, `mail_type`) VALUES (55667789, 105, '12343', 'Sd+Ba+OF4eHy/g00BWlq/Oqg9UBagU76k9n2W0Dc/u56jwJ+HFcV+XXWBqxMUCFi2W4dcJIvnA6oAWmmjzTHeK7TnA9ZIWKOBFqWZLp9veveO3sqbvrF4T96TWO5QuZVtYZ3UzKwppV53nWxSLhGzLXZ/ZN2tbbuvUNQPCMypCc=', 0, '2022-03-16 17:05:20', '321', NULL);
INSERT INTO `drafts` (`id`, `user_id`, `title`, `content`, `type`, `create_time`, `to_user`, `mail_type`) VALUES (55667790, 103, '111', 'RwPK1inEw6XtfClOylc2MjKiOsvF/8Q53iL6aIAn2lw2ZcoCTV9WzLKZT8YTliydam1XGzzEiQ5I8ET2eMGEoQ3FdjYHAjJXPiT1Vdfy24s4PSi6Xxvr+DfCdVp93w1ulcpkqH832fYNo/G9ObJWPtHfIrdjgpXsrTQkvvIfOdE=', 0, '2025-03-19 14:37:32', '111@qq.com', '2');
COMMIT;

-- ----------------------------
-- Table structure for post_file
-- ----------------------------
DROP TABLE IF EXISTS `post_file`;
CREATE TABLE `post_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) DEFAULT NULL COMMENT '附件名称',
  `file_url` varchar(255) DEFAULT NULL COMMENT '附件url',
  `file_type` varchar(255) DEFAULT NULL COMMENT '附件类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `post_id` int(11) DEFAULT NULL COMMENT '邮件ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of post_file
-- ----------------------------
BEGIN;
INSERT INTO `post_file` (`id`, `file_name`, `file_url`, `file_type`, `create_time`, `post_id`) VALUES (1, 'voiceProMenu.sql', 'http://localhost:8080/editor-md-upload/41f6e364806d4ee7859f10b32f6691f9.sql', NULL, '2022-03-05 17:20:14', 6);
INSERT INTO `post_file` (`id`, `file_name`, `file_url`, `file_type`, `create_time`, `post_id`) VALUES (2, 'QQ截图20220226154908.png', 'http://localhost:8080/editor-md-upload/41c2723ecf044378bd13abdbe9fcb796.png', NULL, '2022-03-05 18:55:38', 7);
INSERT INTO `post_file` (`id`, `file_name`, `file_url`, `file_type`, `create_time`, `post_id`) VALUES (3, 'e79c1ea2fd222a8ed5136f76d8ce3343.mp4', 'http://localhost:8080/editor-md-upload/7e4990ccf0ad485ba4c9c841a220a9cc.mp4', NULL, '2022-03-05 19:50:17', 8);
INSERT INTO `post_file` (`id`, `file_name`, `file_url`, `file_type`, `create_time`, `post_id`) VALUES (4, '5aced33b7a76eac1ac3d89eae2fae5d4.mp4', 'http://localhost:8080/editor-md-upload/71fe9daa3d0a4748825571ade427b276.mp4', NULL, '2022-03-05 19:50:18', 8);
INSERT INTO `post_file` (`id`, `file_name`, `file_url`, `file_type`, `create_time`, `post_id`) VALUES (5, '21-热身舞音乐hiphop1.mp3', 'http://localhost:8080/editor-md-upload/2c121e6516b343b1bbf72739a0b29ac5.mp3', NULL, '2022-03-05 19:50:18', 8);
INSERT INTO `post_file` (`id`, `file_name`, `file_url`, `file_type`, `create_time`, `post_id`) VALUES (6, 'e79c1ea2fd222a8ed5136f76d8ce3343.mp4', 'http://localhost:8080/editor-md-upload/a0feb87a3d874bffaeb0e3db1dd2a4dc.mp4', NULL, '2022-03-05 19:52:56', 9);
INSERT INTO `post_file` (`id`, `file_name`, `file_url`, `file_type`, `create_time`, `post_id`) VALUES (12, 'QQ截图20220226155010.png', 'http://localhost:8080/editor-md-upload/f93129da24fb415ba806cfd1752cbd42.png', NULL, '2022-03-16 18:15:19', 55667789);
INSERT INTO `post_file` (`id`, `file_name`, `file_url`, `file_type`, `create_time`, `post_id`) VALUES (13, 'QQ截图20220226154952.png', 'http://localhost:8080/editor-md-upload/756ab09de331441eae5154bc83ef2a99.png', NULL, '2022-03-16 18:15:19', 55667789);
INSERT INTO `post_file` (`id`, `file_name`, `file_url`, `file_type`, `create_time`, `post_id`) VALUES (14, 'taohua-5.png', 'http://localhost:8080/editor-md-upload/c2e14706baa240f8b538116cab91e478.png', NULL, '2022-03-16 18:15:19', 55667789);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `salt` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0-普通用户; 1-超级管理员; 2-版主;',
  `status` int(11) DEFAULT NULL COMMENT '0-未激活; 1-已激活;',
  `activation_code` varchar(100) DEFAULT NULL,
  `header_url` varchar(200) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `user_dept` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_username` (`username`(20)) USING BTREE,
  KEY `index_email` (`email`(20)) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `username`, `password`, `salt`, `email`, `type`, `status`, `activation_code`, `header_url`, `create_time`, `user_dept`) VALUES (102, 'abc123', 'd3bd4221c6c2b6367d653392a1406991', '24420', '461764761@qq.com', 0, 1, '1b24238b540c41cb81501208a6185dba', 'http://localhost:8080/editor-md-upload/596ff577af474802866549468c783e3c.png', '2022-03-01 16:06:42', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `salt`, `email`, `type`, `status`, `activation_code`, `header_url`, `create_time`, `user_dept`) VALUES (103, 'admin', '584091668a888dd2f33cd8857416ecfb', '36a75', '46182673287@qq.com', 0, 1, 'b4ede5ceb74a404d8eb8af76a7d2739f', 'http://images.nowcoder.com/head/969t.png', '2022-03-02 15:50:32', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `salt`, `email`, `type`, `status`, `activation_code`, `header_url`, `create_time`, `user_dept`) VALUES (104, '张明明', '26b23d1396c1dd39e0ce828d5ba5acae', 'ddfdd', '333@qq.com', 0, 1, '035b41e037d8489a9e46cd7b5788b04a', 'http://images.nowcoder.com/head/883t.png', '2022-03-05 18:52:08', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `salt`, `email`, `type`, `status`, `activation_code`, `header_url`, `create_time`, `user_dept`) VALUES (105, '李白', 'b5f4a7eb82c973640c00d983a411c3ef', '7b9a3', '111@qq.com', 0, 1, '02bfbba8891e45a6a0a7e0333bf22f17', 'http://localhost:8080/editor-md-upload/dcdac12e03fa4fb68334eb4029702dff.png', '2022-03-05 19:39:57', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `salt`, `email`, `type`, `status`, `activation_code`, `header_url`, `create_time`, `user_dept`) VALUES (106, '张三', 'e4308b596e8ad60e2e3f8f4dd9809151', '1ce8a', '222@qq.com', 0, 1, 'd1184713c07845f48985d4bc4f552878', 'http://images.nowcoder.com/head/561t.png', '2022-03-05 19:40:45', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `salt`, `email`, `type`, `status`, `activation_code`, `header_url`, `create_time`, `user_dept`) VALUES (107, '1', '584091668a888dd2f33cd8857416ecfb', '36a75', '1', 0, 1, '44a511b9415646fc9bdf64f34813b27e', 'http://images.nowcoder.com/head/882t.png', '2025-03-19 11:32:31', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `salt`, `email`, `type`, `status`, `activation_code`, `header_url`, `create_time`, `user_dept`) VALUES (108, '2', 'c85fdd49af1b82b16f6b73d3f9edd3f2', '44986', '2@1.com', 0, 1, 'fee3bed5b3ff4f8f84fcf04493063952', 'http://images.nowcoder.com/head/493t.png', '2025-03-19 19:54:54', '测试部门');
INSERT INTO `user` (`id`, `username`, `password`, `salt`, `email`, `type`, `status`, `activation_code`, `header_url`, `create_time`, `user_dept`) VALUES (109, '3', '62945a1235a01760bdaa703ea2bd64cb', '6829a', '3@1.com', 1, 1, '79d4232389ee4b0f88d1839b00516803', 'http://images.nowcoder.com/head/941t.png', '2025-03-19 20:00:16', '22');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
