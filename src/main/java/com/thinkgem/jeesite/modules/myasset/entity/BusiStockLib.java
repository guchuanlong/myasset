/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.myasset.entity.BusiCategory;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 资产库存Entity
 * @author gucl
 * @version 2018-07-15
 */
public class BusiStockLib extends DataEntity<BusiStockLib> {
	
	private static final long serialVersionUID = 1L;
	private String companyId;		// 归属公司
	private Office office;		// 归属部门
	private BusiCategory category;		// 资产分类
	private String assetnameId;		// 资产名称
	private String placeId;		// 存放地点
	private String modelFormat;		// 资产型号
	private String measureUnitId;		// 计量单位
	private String produceFactory;		// 生产厂家
	private String deviceNo;		// 设备编号
	private String initialFlag;		// 是否初始库存
	private String stockNum;		// stock_num
	
	public BusiStockLib() {
		super();
	}

	public BusiStockLib(String id){
		super(id);
	}

	@Length(min=0, max=64, message="归属公司长度必须介于 0 和 64 之间")
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	public BusiCategory getCategory() {
		return category;
	}

	public void setCategory(BusiCategory category) {
		this.category = category;
	}
	
	@Length(min=0, max=64, message="资产名称长度必须介于 0 和 64 之间")
	public String getAssetnameId() {
		return assetnameId;
	}

	public void setAssetnameId(String assetnameId) {
		this.assetnameId = assetnameId;
	}
	
	@Length(min=0, max=100, message="存放地点长度必须介于 0 和 100 之间")
	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	
	@Length(min=0, max=100, message="资产型号长度必须介于 0 和 100 之间")
	public String getModelFormat() {
		return modelFormat;
	}

	public void setModelFormat(String modelFormat) {
		this.modelFormat = modelFormat;
	}
	
	@Length(min=0, max=64, message="计量单位长度必须介于 0 和 64 之间")
	public String getMeasureUnitId() {
		return measureUnitId;
	}

	public void setMeasureUnitId(String measureUnitId) {
		this.measureUnitId = measureUnitId;
	}
	
	@Length(min=0, max=200, message="生产厂家长度必须介于 0 和 200 之间")
	public String getProduceFactory() {
		return produceFactory;
	}

	public void setProduceFactory(String produceFactory) {
		this.produceFactory = produceFactory;
	}
	
	@Length(min=0, max=200, message="设备编号长度必须介于 0 和 200 之间")
	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	
	@Length(min=0, max=32, message="是否初始库存长度必须介于 0 和 32 之间")
	public String getInitialFlag() {
		return initialFlag;
	}

	public void setInitialFlag(String initialFlag) {
		this.initialFlag = initialFlag;
	}
	
	public String getStockNum() {
		return stockNum;
	}

	public void setStockNum(String stockNum) {
		this.stockNum = stockNum;
	}
	
}