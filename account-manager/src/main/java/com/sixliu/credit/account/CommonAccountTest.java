package com.sixliu.credit.account;
/**
*@author:MG01867
*@date:2018年6月21日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
public class CommonAccountTest {

	public static void main(String[] args) {
		double customerBaseQuota=10000d;
		CommonAccount customerBaseAccount=new CommonAccount("客户基础账户",customerBaseQuota,true);
		System.out.println(customerBaseAccount);
		System.out.println("----------------------------------------------");
		
		double projectBaseQuota=150000d;
		CommonAccount projectAccount=new CommonAccount("产品管控账户",projectBaseQuota,true);
		System.out.println(projectAccount);
		System.out.println("----------------------------------------------");
		
		double customerProductQuota=5000d;
		CommonAccount customerProductAccount=new CommonAccount("客户贷款产品账户",customerProductQuota,true,new CommonAccount[] {customerBaseAccount,projectAccount});
		System.out.println(customerProductAccount);
		System.out.println("----------------------------------------------");
		
		customerProductAccount.occupyQuota(5000);
		System.out.println("占用5000额度");
		System.out.println(customerBaseAccount);
		System.out.println(projectAccount);
		System.out.println(customerProductAccount);
		System.out.println("----------------------------------------------");
		
		
	}

}
