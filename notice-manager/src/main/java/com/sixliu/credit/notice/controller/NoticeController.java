package com.sixliu.credit.notice.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sixliu.credit.common.dto.Response;
import com.sixliu.credit.common.dto.ResponseUtils;
import com.sixliu.credit.notice.dto.NoticeMessageDTO;
import com.sixliu.credit.notice.service.NoticeService;

/**
 * @author:MG01867
 * @date:2018年7月5日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

	private NoticeService noticeService;
	
	@RequestMapping(value = "/send", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Response<Void> send(NoticeMessageDTO message) {
		noticeService.send(message);
		return ResponseUtils.succeed();
	}

}
