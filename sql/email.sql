

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for discuss_post
-- ----------------------------
DROP TABLE IF EXISTS `discuss_post`;
CREATE TABLE `discuss_post`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `type` int NULL DEFAULT NULL COMMENT '0-普通; ',
  `status` int NULL DEFAULT NULL COMMENT '0未读 1已读',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `comment_count` int NULL DEFAULT NULL,
  `score` double NULL DEFAULT NULL,
  `to_user_id` int NULL DEFAULT NULL COMMENT '接收人user_id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of discuss_post
-- ----------------------------
INSERT INTO `discuss_post` VALUES (1, 102, 'ceshi yixia a ', 'MEc6z7b7i2zq12hUjdlZS8RZRHGeim62twIxWTWB6kHyLydU78JS87h5+4SIsPG+4v9gIB1WBY/RNpd1VNaj8Pr0IsT3XWhk+Z7kHI4mfvMZMUs+LBAlmv3BdNFGe38pHPUOMM6j9Vxhksc3Bsqjq83W0AO0EfgWtO2BcmxrOf0=', 0, 0, '2022-03-01 16:10:02', 0, 0, NULL);
INSERT INTO `discuss_post` VALUES (2, 102, '你好啊wedding', 'MEc6z7b7i2zq12hUjdlZS8RZRHGeim62twIxWTWB6kHyLydU78JS87h5+4SIsPG+4v9gIB1WBY/RNpd1VNaj8Pr0IsT3XWhk+Z7kHI4mfvMZMUs+LBAlmv3BdNFGe38pHPUOMM6j9Vxhksc3Bsqjq83W0AO0EfgWtO2BcmxrOf0=', 0, 0, '2022-03-01 16:11:32', 0, 0, NULL);
INSERT INTO `discuss_post` VALUES (3, 102, '123', 'MEc6z7b7i2zq12hUjdlZS8RZRHGeim62twIxWTWB6kHyLydU78JS87h5+4SIsPG+4v9gIB1WBY/RNpd1VNaj8Pr0IsT3XWhk+Z7kHI4mfvMZMUs+LBAlmv3BdNFGe38pHPUOMM6j9Vxhksc3Bsqjq83W0AO0EfgWtO2BcmxrOf0=', 0, 0, '2022-03-01 16:21:38', 0, 0, NULL);
INSERT INTO `discuss_post` VALUES (4, 102, '来看看好吃的土豆吧', 'MEc6z7b7i2zq12hUjdlZS8RZRHGeim62twIxWTWB6kHyLydU78JS87h5+4SIsPG+4v9gIB1WBY/RNpd1VNaj8Pr0IsT3XWhk+Z7kHI4mfvMZMUs+LBAlmv3BdNFGe38pHPUOMM6j9Vxhksc3Bsqjq83W0AO0EfgWtO2BcmxrOf0=', 0, 0, '2022-03-01 16:22:08', 1, 0, NULL);
INSERT INTO `discuss_post` VALUES (5, 102, '123321', 'MEc6z7b7i2zq12hUjdlZS8RZRHGeim62twIxWTWB6kHyLydU78JS87h5+4SIsPG+4v9gIB1WBY/RNpd1VNaj8Pr0IsT3XWhk+Z7kHI4mfvMZMUs+LBAlmv3BdNFGe38pHPUOMM6j9Vxhksc3Bsqjq83W0AO0EfgWtO2BcmxrOf0=', 0, 1, '2022-03-05 16:17:21', NULL, NULL, 103);
INSERT INTO `discuss_post` VALUES (7, 104, '你好啊abc123,很高兴认识你啊', 'MEc6z7b7i2zq12hUjdlZS8RZRHGeim62twIxWTWB6kHyLydU78JS87h5+4SIsPG+4v9gIB1WBY/RNpd1VNaj8Pr0IsT3XWhk+Z7kHI4mfvMZMUs+LBAlmv3BdNFGe38pHPUOMM6j9Vxhksc3Bsqjq83W0AO0EfgWtO2BcmxrOf0=', 0, 1, '2022-03-05 18:55:38', NULL, NULL, 102);
INSERT INTO `discuss_post` VALUES (8, 105, '你好啊张三 很高兴认识你', 'MEc6z7b7i2zq12hUjdlZS8RZRHGeim62twIxWTWB6kHyLydU78JS87h5+4SIsPG+4v9gIB1WBY/RNpd1VNaj8Pr0IsT3XWhk+Z7kHI4mfvMZMUs+LBAlmv3BdNFGe38pHPUOMM6j9Vxhksc3Bsqjq83W0AO0EfgWtO2BcmxrOf0=', 0, 1, '2022-03-05 19:50:17', NULL, NULL, 106);
INSERT INTO `discuss_post` VALUES (9, 106, '你好啊你白 收到你的邮件我很开心', 'MEc6z7b7i2zq12hUjdlZS8RZRHGeim62twIxWTWB6kHyLydU78JS87h5+4SIsPG+4v9gIB1WBY/RNpd1VNaj8Pr0IsT3XWhk+Z7kHI4mfvMZMUs+LBAlmv3BdNFGe38pHPUOMM6j9Vxhksc3Bsqjq83W0AO0EfgWtO2BcmxrOf0=', 0, 1, '2022-03-05 19:52:56', NULL, NULL, 105);
INSERT INTO `discuss_post` VALUES (10, 106, '谢谢你啊', 'MEc6z7b7i2zq12hUjdlZS8RZRHGeim62twIxWTWB6kHyLydU78JS87h5+4SIsPG+4v9gIB1WBY/RNpd1VNaj8Pr0IsT3XWhk+Z7kHI4mfvMZMUs+LBAlmv3BdNFGe38pHPUOMM6j9Vxhksc3Bsqjq83W0AO0EfgWtO2BcmxrOf0=', 0, 1, '2022-03-05 19:53:26', NULL, NULL, 105);
INSERT INTO `discuss_post` VALUES (11, 105, '测试主题-123', 'MEc6z7b7i2zq12hUjdlZS8RZRHGeim62twIxWTWB6kHyLydU78JS87h5+4SIsPG+4v9gIB1WBY/RNpd1VNaj8Pr0IsT3XWhk+Z7kHI4mfvMZMUs+LBAlmv3BdNFGe38pHPUOMM6j9Vxhksc3Bsqjq83W0AO0EfgWtO2BcmxrOf0=', 0, 0, '2022-03-16 18:14:22', NULL, NULL, 106);

-- ----------------------------
-- Table structure for drafts
-- ----------------------------
DROP TABLE IF EXISTS `drafts`;
CREATE TABLE `drafts`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `type` int NULL DEFAULT NULL COMMENT '0-普通; ',
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `to_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收人user信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55667789 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '草稿箱表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of drafts
-- ----------------------------
INSERT INTO `drafts` VALUES (55667789, 105, '12343', 'Sd+Ba+OF4eHy/g00BWlq/Oqg9UBagU76k9n2W0Dc/u56jwJ+HFcV+XXWBqxMUCFi2W4dcJIvnA6oAWmmjzTHeK7TnA9ZIWKOBFqWZLp9veveO3sqbvrF4T96TWO5QuZVtYZ3UzKwppV53nWxSLhGzLXZ/ZN2tbbuvUNQPCMypCc=', 0, '2022-03-16 17:05:20', '321');

-- ----------------------------
-- Table structure for post_file
-- ----------------------------
DROP TABLE IF EXISTS `post_file`;
CREATE TABLE `post_file`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '附件名称',
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '附件url',
  `file_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '附件类型',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `post_id` int NULL DEFAULT NULL COMMENT '邮件ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post_file
-- ----------------------------
INSERT INTO `post_file` VALUES (1, 'voiceProMenu.sql', 'http://localhost:8080/editor-md-upload/41f6e364806d4ee7859f10b32f6691f9.sql', NULL, '2022-03-05 17:20:14', 6);
INSERT INTO `post_file` VALUES (2, 'QQ截图20220226154908.png', 'http://localhost:8080/editor-md-upload/41c2723ecf044378bd13abdbe9fcb796.png', NULL, '2022-03-05 18:55:38', 7);
INSERT INTO `post_file` VALUES (3, 'e79c1ea2fd222a8ed5136f76d8ce3343.mp4', 'http://localhost:8080/editor-md-upload/7e4990ccf0ad485ba4c9c841a220a9cc.mp4', NULL, '2022-03-05 19:50:17', 8);
INSERT INTO `post_file` VALUES (4, '5aced33b7a76eac1ac3d89eae2fae5d4.mp4', 'http://localhost:8080/editor-md-upload/71fe9daa3d0a4748825571ade427b276.mp4', NULL, '2022-03-05 19:50:18', 8);
INSERT INTO `post_file` VALUES (5, '21-热身舞音乐hiphop1.mp3', 'http://localhost:8080/editor-md-upload/2c121e6516b343b1bbf72739a0b29ac5.mp3', NULL, '2022-03-05 19:50:18', 8);
INSERT INTO `post_file` VALUES (6, 'e79c1ea2fd222a8ed5136f76d8ce3343.mp4', 'http://localhost:8080/editor-md-upload/a0feb87a3d874bffaeb0e3db1dd2a4dc.mp4', NULL, '2022-03-05 19:52:56', 9);
INSERT INTO `post_file` VALUES (12, 'QQ截图20220226155010.png', 'http://localhost:8080/editor-md-upload/f93129da24fb415ba806cfd1752cbd42.png', NULL, '2022-03-16 18:15:19', 55667789);
INSERT INTO `post_file` VALUES (13, 'QQ截图20220226154952.png', 'http://localhost:8080/editor-md-upload/756ab09de331441eae5154bc83ef2a99.png', NULL, '2022-03-16 18:15:19', 55667789);
INSERT INTO `post_file` VALUES (14, 'taohua-5.png', 'http://localhost:8080/editor-md-upload/c2e14706baa240f8b538116cab91e478.png', NULL, '2022-03-16 18:15:19', 55667789);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `salt` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int NULL DEFAULT NULL COMMENT '0-普通用户; 1-超级管理员; 2-版主;',
  `status` int NULL DEFAULT NULL COMMENT '0-未激活; 1-已激活;',
  `activation_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `header_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_username`(`username`(20)) USING BTREE,
  INDEX `index_email`(`email`(20)) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (102, 'abc123', 'd3bd4221c6c2b6367d653392a1406991', '24420', '461764761@qq.com', 0, 1, '1b24238b540c41cb81501208a6185dba', 'http://localhost:8080/editor-md-upload/596ff577af474802866549468c783e3c.png', '2022-03-01 16:06:42');
INSERT INTO `user` VALUES (103, 'admin', '43eef0667dfea2dc9b5339c25bcfc146', 'bd6a0', '46182673287@qq.com', 0, 1, 'b4ede5ceb74a404d8eb8af76a7d2739f', 'http://images.nowcoder.com/head/969t.png', '2022-03-02 15:50:32');
INSERT INTO `user` VALUES (104, '张明明', '26b23d1396c1dd39e0ce828d5ba5acae', 'ddfdd', '333@qq.com', 0, 1, '035b41e037d8489a9e46cd7b5788b04a', 'http://images.nowcoder.com/head/883t.png', '2022-03-05 18:52:08');
INSERT INTO `user` VALUES (105, '李白', 'b5f4a7eb82c973640c00d983a411c3ef', '7b9a3', '111@qq.com', 0, 1, '02bfbba8891e45a6a0a7e0333bf22f17', 'http://localhost:8080/editor-md-upload/dcdac12e03fa4fb68334eb4029702dff.png', '2022-03-05 19:39:57');
INSERT INTO `user` VALUES (106, '张三', 'e4308b596e8ad60e2e3f8f4dd9809151', '1ce8a', '222@qq.com', 0, 1, 'd1184713c07845f48985d4bc4f552878', 'http://images.nowcoder.com/head/561t.png', '2022-03-05 19:40:45');

SET FOREIGN_KEY_CHECKS = 1;
