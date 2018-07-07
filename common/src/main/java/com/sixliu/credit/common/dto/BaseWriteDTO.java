package com.sixliu.credit.common.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author:MG01867
 * @date:2018年7月5日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
@Data
public abstract class BaseWriteDTO {

	@NotBlank(message = "the request's id must not be blank")
	private String id;
}
