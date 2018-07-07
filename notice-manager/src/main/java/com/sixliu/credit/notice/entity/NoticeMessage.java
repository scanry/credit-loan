package com.sixliu.credit.notice.entity;

import java.util.List;

import com.sixliu.credit.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author:MG01867
 * @date:2018年7月5日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NoticeMessage extends BaseEntity {
	/**
	 * 消息模版key
	 */
	private String templateKey;

	/**
	 * 消息模版变量集合
	 */
	private List<String> variable;
}
