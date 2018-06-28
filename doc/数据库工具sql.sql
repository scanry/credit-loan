--
SELECT * FROM data_access.product_type_config;

--
SELECT * FROM data_access.product_config;

--
SELECT * FROM data_access.product_config_log;

--
SELECT * FROM data_access.product_config_copy;

CREATE TABLE data_access.`product_attribute_config` (
  `id` varchar(36) NOT NULL COMMENT '数据id:业务无关性',
  `ownerEntityId` varchar(10) NOT NULL COMMENT '产品id',
  `takeEffect` varchar(20) NOT NULL COMMENT '激活',
  `attributeName` varchar(36) NOT NULL COMMENT '属性名称',
  `attributeValue` int(4) NOT NULL COMMENT '属性值',
  `remarks` varchar(100) DEFAULT NULL COMMENT '数据备注',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '数据版本',
  `owner_id` varchar(36) NOT NULL COMMENT '数据所有者id',
  `update_user_id` varchar(36) NOT NULL COMMENT '数据更新用户id',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据更新日期id',
  `create_user_id` varchar(36) NOT NULL COMMENT '数据创建用户id',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_name_UNIQUE` (`code`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品类型表';


