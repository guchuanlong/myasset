/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 资产报废Entity
 * @author gucl
 * @version 2020-05-31
 */
public class BusiScrapBillDtl extends DataEntity<BusiScrapBillDtl> {
	
	private static final long serialVersionUID = 1L;
	private BusiScrapBill scrapBillId;		// 报废单id 父类
	private Office company;		// 申请人公司
	private Office office;		// 申请人部门
	private String assetGlobalId;		// 资产id
	private String assetIsScrap;		// 资产是否报废
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
	
	public BusiScrapBillDtl() {
		super();
	}

	public BusiScrapBillDtl(String id){
		super(id);
	}

	public BusiScrapBillDtl(BusiScrapBill scrapBillId){
		this.scrapBillId = scrapBillId;
	}

	@Length(min=0, max=64, message="报废单id长度必须介于 0 和 64 之间")
	public BusiScrapBill getScrapBillId() {
		return scrapBillId;
	}

	public void setScrapBillId(BusiScrapBill scrapBillId) {
		this.scrapBillId = scrapBillId;
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
	
	@Length(min=0, max=64, message="资产是否报废长度必须介于 0 和 64 之间")
	public String getAssetIsScrap() {
		return assetIsScrap;
	}

	public void setAssetIsScrap(String assetIsScrap) {
		this.assetIsScrap = assetIsScrap;
	}
	
	@Length(min=0, max=32, message="操作平台长度必须介于 0 和 32 之间")
	public String getOsPlatformId() {
		return osPlatformId;
	}

	public void setOsPlatformId(String osPlatformId) {
		this.osPlatformId = osPlatformId;
	}
	
}