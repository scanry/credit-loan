package com.sixliu.credit.notice.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.sixliu.credit.common.dto.BaseWriteDTO;

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
public class NoticeMessageDTO extends BaseWriteDTO{
	
	/**
	 * 消息模版key
	 */
	@NotBlank(message="The NoticeMessage's templateKey must be not blank")
	private String templateKey;
	
	/**
	 * 消息模版变量集合
	 */
	private List<String> variable;
}
