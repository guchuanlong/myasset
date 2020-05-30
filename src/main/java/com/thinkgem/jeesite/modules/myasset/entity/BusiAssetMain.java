/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.myasset.entity.BusiCategory;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 资产主表Entity
 * @author gucl
 * @version 2020-05-30
 */
public class BusiAssetMain extends DataEntity<BusiAssetMain> {
	
	private static final long serialVersionUID = 1L;
	private String stockLibId;		// 库存标识
	private String libinBillNo;		// 入库单编号
	private String status;		// 资产状态
	private String measureUnitId;		// 计量单位
	private String assetGlobalId;		// 资产编码
	private String assetRfidTagid;		// 资产RFID标识
	private String assetBarCode;		// 资产条形码
	private String assetQrCode;		// 资产二维码
	private String companyId;		// 归属公司
	private Office company;		// 归属公司
	private Office office;		// 归属部门
	private String placeId;		// 存放地点
	private BusiPlace place;		// 存放地点
	private BusiCategory category;		// 资产分类
	private String assetnameId;		// 资产名称
	private BusiAssetname assetname;		// 资产名称
	private String produceFactory;		// 生产厂家
	private String modelFormat;		// 资产型号
	private String deviceNo;		// 设备编号
	private String assetConfig;		// 资产配置
	private Date produceDate;		// 出厂日期
	private String unitPrice;		// 单价
	private String depreciationWayId;		// 折旧方式
	private String depreciationPeriod;		// 折旧期限
	private String tagIssueFlag;		// 标签发行标识（0：未发行（打印）， 1：已发行（打印））
	private String erpAssetCode;		// ERP资产号
	private String goodsType;		// 物品品类编码
	private String respPerson;		// 责任人
	private String tagClass;		// 标签类别
	private Date activeTime;		// 启用日期
	private Date scrapTime;		// 报废日期
	private String purchasePrice;		// 采购价格(元)
	private String scrapYearLimit;		// 报废年限
	private String netSalvage;		// 净残值(元)
	
	public BusiAssetMain() {
		super();
	}

	public BusiAssetMain(String id){
		super(id);
	}

	
	
	
	public Office getCompany() {
		return company;
	}

	public void setCompany(Office company) {
		this.company = company;
	}

	public BusiPlace getPlace() {
		return place;
	}

	public void setPlace(BusiPlace place) {
		this.place = place;
	}

	public BusiAssetname getAssetname() {
		return assetname;
	}

	public void setAssetname(BusiAssetname assetname) {
		this.assetname = assetname;
	}

	@Length(min=0, max=64, message="库存标识长度必须介于 0 和 64 之间")
	public String getStockLibId() {
		return stockLibId;
	}

	public void setStockLibId(String stockLibId) {
		this.stockLibId = stockLibId;
	}
	
	@Length(min=0, max=64, message="入库单编号长度必须介于 0 和 64 之间")
	public String getLibinBillNo() {
		return libinBillNo;
	}

	public void setLibinBillNo(String libinBillNo) {
		this.libinBillNo = libinBillNo;
	}
	
	@Length(min=0, max=32, message="资产状态长度必须介于 0 和 32 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=64, message="计量单位长度必须介于 0 和 64 之间")
	public String getMeasureUnitId() {
		return measureUnitId;
	}

	public void setMeasureUnitId(String measureUnitId) {
		this.measureUnitId = measureUnitId;
	}
	
	@Length(min=0, max=64, message="资产编码长度必须介于 0 和 64 之间")
	public String getAssetGlobalId() {
		return assetGlobalId;
	}

	public void setAssetGlobalId(String assetGlobalId) {
		this.assetGlobalId = assetGlobalId;
	}
	
	@Length(min=0, max=64, message="资产RFID标识长度必须介于 0 和 64 之间")
	public String getAssetRfidTagid() {
		return assetRfidTagid;
	}

	public void setAssetRfidTagid(String assetRfidTagid) {
		this.assetRfidTagid = assetRfidTagid;
	}
	
	@Length(min=0, max=64, message="资产条形码长度必须介于 0 和 64 之间")
	public String getAssetBarCode() {
		return assetBarCode;
	}

	public void setAssetBarCode(String assetBarCode) {
		this.assetBarCode = assetBarCode;
	}
	
	@Length(min=0, max=400, message="资产二维码长度必须介于 0 和 400 之间")
	public String getAssetQrCode() {
		return assetQrCode;
	}

	public void setAssetQrCode(String assetQrCode) {
		this.assetQrCode = assetQrCode;
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
	
	@Length(min=0, max=64, message="存放地点长度必须介于 0 和 64 之间")
	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
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
	
	@Length(min=0, max=200, message="生产厂家长度必须介于 0 和 200 之间")
	public String getProduceFactory() {
		return produceFactory;
	}

	public void setProduceFactory(String produceFactory) {
		this.produceFactory = produceFactory;
	}
	
	@Length(min=0, max=200, message="资产型号长度必须介于 0 和 200 之间")
	public String getModelFormat() {
		return modelFormat;
	}

	public void setModelFormat(String modelFormat) {
		this.modelFormat = modelFormat;
	}
	
	@Length(min=0, max=100, message="设备编号长度必须介于 0 和 100 之间")
	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	
	@Length(min=0, max=400, message="资产配置长度必须介于 0 和 400 之间")
	public String getAssetConfig() {
		return assetConfig;
	}

	public void setAssetConfig(String assetConfig) {
		this.assetConfig = assetConfig;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}
	
	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	@Length(min=0, max=64, message="折旧方式长度必须介于 0 和 64 之间")
	public String getDepreciationWayId() {
		return depreciationWayId;
	}

	public void setDepreciationWayId(String depreciationWayId) {
		this.depreciationWayId = depreciationWayId;
	}
	
	@Length(min=0, max=64, message="折旧期限长度必须介于 0 和 64 之间")
	public String getDepreciationPeriod() {
		return depreciationPeriod;
	}

	public void setDepreciationPeriod(String depreciationPeriod) {
		this.depreciationPeriod = depreciationPeriod;
	}
	
	@Length(min=0, max=64, message="标签发行标识（0：未发行（打印）， 1：已发行（打印））长度必须介于 0 和 64 之间")
	public String getTagIssueFlag() {
		return tagIssueFlag;
	}

	public void setTagIssueFlag(String tagIssueFlag) {
		this.tagIssueFlag = tagIssueFlag;
	}
	
	@Length(min=0, max=100, message="ERP资产号长度必须介于 0 和 100 之间")
	public String getErpAssetCode() {
		return erpAssetCode;
	}

	public void setErpAssetCode(String erpAssetCode) {
		this.erpAssetCode = erpAssetCode;
	}
	
	@Length(min=0, max=64, message="物品品类编码长度必须介于 0 和 64 之间")
	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	
	@Length(min=0, max=64, message="责任人长度必须介于 0 和 64 之间")
	public String getRespPerson() {
		return respPerson;
	}

	public void setRespPerson(String respPerson) {
		this.respPerson = respPerson;
	}
	
	@Length(min=0, max=32, message="标签类别长度必须介于 0 和 32 之间")
	public String getTagClass() {
		return tagClass;
	}

	public void setTagClass(String tagClass) {
		this.tagClass = tagClass;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getScrapTime() {
		return scrapTime;
	}

	public void setScrapTime(Date scrapTime) {
		this.scrapTime = scrapTime;
	}
	
	public String getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
	public String getScrapYearLimit() {
		return scrapYearLimit;
	}

	public void setScrapYearLimit(String scrapYearLimit) {
		this.scrapYearLimit = scrapYearLimit;
	}
	
	public String getNetSalvage() {
		return netSalvage;
	}

	public void setNetSalvage(String netSalvage) {
		this.netSalvage = netSalvage;
	}
	
}