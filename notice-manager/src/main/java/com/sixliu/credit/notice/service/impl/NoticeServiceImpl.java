package com.sixliu.credit.notice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixliu.credit.notice.dao.NoticeMessageDao;
import com.sixliu.credit.notice.dao.NoticeMessageTemplateDao;
import com.sixliu.credit.notice.dto.NoticeMessageDTO;
import com.sixliu.credit.notice.service.NoticeService;

/**
*@author:MG01867
*@date:2018年7月5日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeMessageTemplateDao noticeMessageTemplateDao;
	
	@Autowired
	private NoticeMessageDao noticeMessageDao;
	
	@Override
	public void send(NoticeMessageDTO message) {
		
	}

}
