/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 资产转移Entity
 * @author gucl
 * @version 2020-05-31
 */
public class BusiTransferBillDtl extends DataEntity<BusiTransferBillDtl> {
	
	private static final long serialVersionUID = 1L;
	private BusiTransferBill transferBillId;		// 转移单id 父类
	private Office company;		// 申请人公司
	private Office office;		// 申请人部门
	private String assetGlobalId;		// 资产id
	private String assetIsTransfer;		// 资产是否转移
	private String osPlatformId;		// 操作平台
	private String origRespPerson;		// 原负责人
	private String origPlaceId;		// 原存放地点
	private String recvRespPerson;		// 接收人
	private String recvPlaceId;		// 接收地点
	
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
	
	public BusiTransferBillDtl() {
		super();
	}

	public BusiTransferBillDtl(String id){
		super(id);
	}

	public BusiTransferBillDtl(BusiTransferBill transferBillId){
		this.transferBillId = transferBillId;
	}

	@Length(min=0, max=64, message="转移单id长度必须介于 0 和 64 之间")
	public BusiTransferBill getTransferBillId() {
		return transferBillId;
	}

	public void setTransferBillId(BusiTransferBill transferBillId) {
		this.transferBillId = transferBillId;
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
	
	@Length(min=0, max=64, message="资产是否转移长度必须介于 0 和 64 之间")
	public String getAssetIsTransfer() {
		return assetIsTransfer;
	}

	public void setAssetIsTransfer(String assetIsTransfer) {
		this.assetIsTransfer = assetIsTransfer;
	}
	
	@Length(min=0, max=32, message="操作平台长度必须介于 0 和 32 之间")
	public String getOsPlatformId() {
		return osPlatformId;
	}

	public void setOsPlatformId(String osPlatformId) {
		this.osPlatformId = osPlatformId;
	}
	
	@Length(min=0, max=64, message="原负责人长度必须介于 0 和 64 之间")
	public String getOrigRespPerson() {
		return origRespPerson;
	}

	public void setOrigRespPerson(String origRespPerson) {
		this.origRespPerson = origRespPerson;
	}
	
	@Length(min=0, max=64, message="原存放地点长度必须介于 0 和 64 之间")
	public String getOrigPlaceId() {
		return origPlaceId;
	}

	public void setOrigPlaceId(String origPlaceId) {
		this.origPlaceId = origPlaceId;
	}
	
	@Length(min=0, max=64, message="接收人长度必须介于 0 和 64 之间")
	public String getRecvRespPerson() {
		return recvRespPerson;
	}

	public void setRecvRespPerson(String recvRespPerson) {
		this.recvRespPerson = recvRespPerson;
	}
	
	@Length(min=0, max=64, message="接收地点长度必须介于 0 和 64 之间")
	public String getRecvPlaceId() {
		return recvPlaceId;
	}

	public void setRecvPlaceId(String recvPlaceId) {
		this.recvPlaceId = recvPlaceId;
	}
	
}