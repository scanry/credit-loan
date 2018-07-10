package com.sixliu.credit.account.dto;

import com.sixliu.credit.common.dto.BaseWriteDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author:MG01867
 * @date:2018年7月10日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DepositResultDTO extends BaseWriteDTO {
	
	private String transferId;
}
