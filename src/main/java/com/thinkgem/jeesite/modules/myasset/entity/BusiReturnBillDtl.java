/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 资产归还Entity
 * @author gucl
 * @version 2019-02-10
 */
public class BusiReturnBillDtl extends DataEntity<BusiReturnBillDtl> {
	
	private static final long serialVersionUID = 1L;
	private String borrowBillId;		// 借用单id
	private BusiReturnBill returnBillId;		// 归还单id 父类
	private Office company;		// 归属公司
	private Office office;		// 归属部门
	private String assetGlobalId;		// 资产id
	private String osPlatformId;		// 操作平台
	
	public BusiReturnBillDtl() {
		super();
	}

	public BusiReturnBillDtl(String id){
		super(id);
	}

	public BusiReturnBillDtl(BusiReturnBill returnBillId){
		this.returnBillId = returnBillId;
	}

	@Length(min=0, max=64, message="借用单id长度必须介于 0 和 64 之间")
	public String getBorrowBillId() {
		return borrowBillId;
	}

	public void setBorrowBillId(String borrowBillId) {
		this.borrowBillId = borrowBillId;
	}
	
	@Length(min=0, max=64, message="归还单id长度必须介于 0 和 64 之间")
	public BusiReturnBill getReturnBillId() {
		return returnBillId;
	}

	public void setReturnBillId(BusiReturnBill returnBillId) {
		this.returnBillId = returnBillId;
	}
	
	public Office getCompany() {
		return company;
	}

	public void setCompany(Office company) {
		this.company = company;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=64, message="资产id长度必须介于 0 和 64 之间")
	public String getAssetGlobalId() {
		return assetGlobalId;
	}

	public void setAssetGlobalId(String assetGlobalId) {
		this.assetGlobalId = assetGlobalId;
	}
	
	@Length(min=0, max=32, message="操作平台长度必须介于 0 和 32 之间")
	public String getOsPlatformId() {
		return osPlatformId;
	}

	public void setOsPlatformId(String osPlatformId) {
		this.osPlatformId = osPlatformId;
	}
	
}