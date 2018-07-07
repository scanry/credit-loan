package com.sixliu.credit.notice.entity;

import com.sixliu.credit.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
*@author:MG01867
*@date:2018年7月5日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
@Data
@EqualsAndHashCode(callSuper = false)
public class NoticeMessageTemplate extends BaseEntity{

	/**消息模版 **/
	private String template;
	
	/**发送渠道:1.短息, 2.邮件, 3.微信**/
	private String channel;
	
	/**发送方式:1.即时, 2.延迟, 3.规定时间内**/
	private String trigger;
	
	/**延迟时间**/
	private long delayTime;
	
	private int control;
}
