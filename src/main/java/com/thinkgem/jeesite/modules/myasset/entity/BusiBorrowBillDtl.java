/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 资产领用Entity
 * @author gucl
 * @version 2019-02-10
 */
public class BusiBorrowBillDtl extends DataEntity<BusiBorrowBillDtl> {
	
	private static final long serialVersionUID = 1L;
	private BusiBorrowBill borrowBillId;		// 领用单id 父类
	private Office company;		// 领用人归属公司
	private Office office;		// 领用人归属部门
	private String assetGlobalId;		// 资产id
	private String assetIsReturn;		// 资产是否归还
	private String osPlatformId;		// 操作平台
	
	//================
	private BusiPlace place;		// 存放地点
	private BusiCategory category;		// 资产分类
	private BusiAssetname assetname;		// 资产名称
	
	
	public BusiPlace getPlace() {
		return place;
	}

	public void setPlace(BusiPlace place) {
		this.place = place;
	}

	public BusiCategory getCategory() {
		return category;
	}

	public void setCategory(BusiCategory category) {
		this.category = category;
	}

	public BusiAssetname getAssetname() {
		return assetname;
	}

	public void setAssetname(BusiAssetname assetname) {
		this.assetname = assetname;
	}

	public BusiBorrowBillDtl() {
		super();
	}

	public BusiBorrowBillDtl(String id){
		super(id);
	}

	public BusiBorrowBillDtl(BusiBorrowBill borrowBillId){
		this.borrowBillId = borrowBillId;
	}

	@Length(min=0, max=64, message="领用单id长度必须介于 0 和 64 之间")
	public BusiBorrowBill getBorrowBillId() {
		return borrowBillId;
	}

	public void setBorrowBillId(BusiBorrowBill borrowBillId) {
		this.borrowBillId = borrowBillId;
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
	
	@Length(min=0, max=64, message="资产是否归还长度必须介于 0 和 64 之间")
	public String getAssetIsReturn() {
		return assetIsReturn;
	}

	public void setAssetIsReturn(String assetIsReturn) {
		this.assetIsReturn = assetIsReturn;
	}
	
	@Length(min=0, max=32, message="操作平台长度必须介于 0 和 32 之间")
	public String getOsPlatformId() {
		return osPlatformId;
	}

	public void setOsPlatformId(String osPlatformId) {
		this.osPlatformId = osPlatformId;
	}
	
}