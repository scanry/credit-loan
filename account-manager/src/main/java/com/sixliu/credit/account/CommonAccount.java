package com.sixliu.credit.account;

import java.math.BigDecimal;


/**
 * @author:MG01867
 * @date:2018年6月21日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
public class CommonAccount{

	private final static BigDecimal ZERO_BIGDECIMAL = new BigDecimal(0);

	private final static CommonAccount[] EMPTY_PARENT_ACCOUNTS = new CommonAccount[0];
	private String id;

	private String name;
	
	/** 是否循环额度 **/
	private boolean loopQuota;

	/** 授信额度 **/
	private BigDecimal creditLine;

	/** 剩余额度 **/
	private BigDecimal surplusQuota;

	/** 使用额度 **/
	private BigDecimal usedQuota;

	private CommonAccount[] parentAccounts=EMPTY_PARENT_ACCOUNTS;

	public CommonAccount(String name,double creditLine, boolean loopQuota) {
		this.name = name;
		this.creditLine = new BigDecimal(creditLine);
		this.surplusQuota = new BigDecimal(creditLine);
		this.usedQuota = new BigDecimal(0);
		this.loopQuota = loopQuota;
	}

	public CommonAccount(String name,double creditLine, boolean loopQuota, CommonAccount[] parentAccounts) {
		this(name,creditLine, loopQuota);
		this.parentAccounts = null == parentAccounts ? EMPTY_PARENT_ACCOUNTS : parentAccounts;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getCreditLine() {
		return creditLine.doubleValue();
	}

	public double getSurplusQuota() {
		return surplusQuota.doubleValue();
	}

	public double getUsedQuotaQuota() {
		return usedQuota.doubleValue();
	}

	public void takeEffect() {

	}

	public void frozen() {
		// TODO Auto-generated method stub

	}

	public void recoveryQuota(double recoveryQuota) {
		BigDecimal recoveryQuotaBd = new BigDecimal(recoveryQuota);
		if (recoveryQuotaBd.compareTo(ZERO_BIGDECIMAL) <= 0) {
			throw new RuntimeException("恢复的额度必须大于等于0");
		}
		if (recoveryQuotaBd.compareTo(usedQuota) == 1) {
			throw new RuntimeException("恢复的额度必须小于等于使用过的额度");
		}
		for (CommonAccount parentAccount : parentAccounts) {
			parentAccount.recoveryQuota(recoveryQuota);
		}
		if (loopQuota) {
			surplusQuota = surplusQuota.add(new BigDecimal(recoveryQuota));
		}
		usedQuota = usedQuota.subtract(new BigDecimal(recoveryQuota));
	}

	public void occupyQuota(double occupyQuota) {
		BigDecimal occupyQuotaBd = new BigDecimal(occupyQuota);
		if (occupyQuotaBd.compareTo(ZERO_BIGDECIMAL) <= 0) {
			throw new RuntimeException("占用的额度必须大于等于0");
		}
		if (occupyQuotaBd.compareTo(surplusQuota) == 1) {
			throw new RuntimeException("占用的额度必须小于等于剩余额度");
		}
		for (CommonAccount parentAccount : parentAccounts) {
			parentAccount.occupyQuota(occupyQuota);
		}
		surplusQuota = surplusQuota.subtract(occupyQuotaBd);
		usedQuota = usedQuota.add(occupyQuotaBd);
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("{");
		sb.append("id=").append(id).append(",");
		sb.append("name=").append(name).append(",");
		sb.append("loopQuota=").append(loopQuota).append(",");
		sb.append("creditLine=").append(creditLine).append(",");
		sb.append("surplusQuota=").append(surplusQuota).append(",");
		sb.append("usedQuota=").append(usedQuota).append(",");
		sb.append("}");
		return sb.toString();
	}

}
