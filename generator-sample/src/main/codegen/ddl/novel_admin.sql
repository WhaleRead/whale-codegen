# noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `display_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `avatar` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `age` int NOT NULL DEFAULT 0,
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT ', 0:,1:',
  `type` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'admin' COMMENT '账号类型,admin:管理,channel:渠道,author:作者',
  `remark` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `username` (`username`) USING BTREE,
  KEY `type` (`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT '用户表';

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `value` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `remark` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `perm` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `value` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `group_id` bigint(20) NOT NULL COMMENT 'ID',
  `remark` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `user_role_mid` (
  `user_id` bigint(20) NOT NULL COMMENT 'ID',
  `role_id` bigint(20) NOT NULL COMMENT 'ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `role_perm_mid` (
  `role_id` bigint(20) NOT NULL COMMENT 'ID',
  `perm_id` bigint(20) NOT NULL COMMENT 'ID',
  PRIMARY KEY (`role_id`,`perm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;