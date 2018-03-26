CREATE TABLE `banner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '名称',
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '图标URL',
  `link_type` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '链接类型',
  `link` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '链接URL',
  `scale` double NOT NULL DEFAULT '0' COMMENT '图片高/宽比例',
  `label` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '标签文本',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '启用状态（1启动，0禁用）',
  `remark` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Banner';