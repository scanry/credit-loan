package com.sixliu.credit.common.dto;

import java.util.List;

import lombok.Data;

/**
*@author:MG01867
*@date:2018年6月22日
*@E-mail:359852326@qq.com
*@version:
*@describe 分页查询请求
*/
@Data
public abstract class PageQueryRequest<D> {

	/**当前分页索引**/
	private Integer currentIndex;
	
	/**分页数量**/
	private Integer pageSize;
	
	/**总计数量**/
	private Integer totalSize;
	
	/**分页查询的数据集合**/
	private List<D> datas;
}
