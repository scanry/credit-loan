package com.sixliu.credit.notice.service;

import com.sixliu.credit.notice.dto.NoticeMessageDTO;

/**
*@author:MG01867
*@date:2018年7月5日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
public interface NoticeService {

	public void send(NoticeMessageDTO message);
}
