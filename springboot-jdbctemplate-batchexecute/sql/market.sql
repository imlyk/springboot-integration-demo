
DROP DATABASE IF EXISTS `market_db`;
CREATE DATABASE `market_db` DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

USE `market_db`;

-- ----------------------------
-- Table structure for apk
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '应用分类表，分类id',
  `parent_id` bigint(20) NOT NULL COMMENT '分类父分类id，自己没有上级时，id是0',
  `depth` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类id路径',
  `category_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类名称',
  `icon` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '分类图标(图标存储路径)',
  `level` int(3) NOT NULL COMMENT '分类层级(1级、2级、3级)',
  `is_leaf` tinyint(1) NOT NULL COMMENT '是否叶子节点,0-否,1-是',
  `category_type` tinyint(2) NOT NULL COMMENT '分类类型:\r\n0通用\r\n1订制',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`category_id`) USING BTREE,
  UNIQUE INDEX `category_id`(`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;