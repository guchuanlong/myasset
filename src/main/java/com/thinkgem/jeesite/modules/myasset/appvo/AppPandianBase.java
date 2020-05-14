/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.appvo;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 盘点基础表Entity
 * @author gucl
 * @version 2019-05-16
 */
public class AppPandianBase implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 实体编号（唯一标识）
	 */
	protected String id;
	
	/**
	 * 当前用户
	 */
	protected User currentUser;
	
	/**
	 * 当前实体分页对象
	 */
	protected Page<AppPandianBase> page;
	
	/**
	 * 自定义SQL（SQL标识，SQL内容）
	 */
	protected Map<String, String> sqlMap;
	
	/**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
	 */
	protected boolean isNewRecord = false;
	private String cityCode;		// 地市编码（预留）
	private String cityName;		// 地市名称
	private String companyName;		// 公司名称
	private Office company;		// 公司
	private String powerSupplyUnit;		// 供电单位名称
	private String connectMethod;		// 接线方式
	private Office office;		// 供电单位
	private String bigClassCode;		// 资产大类编码
	private String bigClassName;		// 资产大类名称
	private String smallClassCode;		// 资产小类编码
	private String smallClassName;		// 资产小类名称
	private String assetBarcode;		// 资产条形码
	private String assetRfidEpc;		// 资产rfid epc
	private String pandianStatusId;		// 盘点状态
	private String pandianStatusName;		// 盘点状态名称
	private String batchUuid;		// 批次
	private Date baseDate;		// 基础数据导入时间
	private Date resultDate;		// 盘点结果时间
	protected String remarks;	// 备注
	protected User createBy;	// 创建者
	protected Date createDate;	// 创建日期
	protected User updateBy;	// 更新者
	protected Date updateDate;	// 更新日期
	protected String delFlag; 	// 删除标记（0：正常；1：删除；2：审核）
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	public Page<AppPandianBase> getPage() {
		return page;
	}
	public void setPage(Page<AppPandianBase> page) {
		this.page = page;
	}
	public Map<String, String> getSqlMap() {
		return sqlMap;
	}
	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}
	public boolean isNewRecord() {
		return isNewRecord;
	}
	public void setNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Office getCompany() {
		return company;
	}
	public void setCompany(Office company) {
		this.company = company;
	}
	public String getPowerSupplyUnit() {
		return powerSupplyUnit;
	}
	public void setPowerSupplyUnit(String powerSupplyUnit) {
		this.powerSupplyUnit = powerSupplyUnit;
	}
	public String getConnectMethod() {
		return connectMethod;
	}
	public void setConnectMethod(String connectMethod) {
		this.connectMethod = connectMethod;
	}
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public String getBigClassCode() {
		return bigClassCode;
	}
	public void setBigClassCode(String bigClassCode) {
		this.bigClassCode = bigClassCode;
	}
	public String getBigClassName() {
		return bigClassName;
	}
	public void setBigClassName(String bigClassName) {
		this.bigClassName = bigClassName;
	}
	public String getSmallClassCode() {
		return smallClassCode;
	}
	public void setSmallClassCode(String smallClassCode) {
		this.smallClassCode = smallClassCode;
	}
	public String getSmallClassName() {
		return smallClassName;
	}
	public void setSmallClassName(String smallClassName) {
		this.smallClassName = smallClassName;
	}
	public String getAssetBarcode() {
		return assetBarcode;
	}
	public void setAssetBarcode(String assetBarcode) {
		this.assetBarcode = assetBarcode;
	}
	public String getAssetRfidEpc() {
		return assetRfidEpc;
	}
	public void setAssetRfidEpc(String assetRfidEpc) {
		this.assetRfidEpc = assetRfidEpc;
	}
	public String getPandianStatusId() {
		return pandianStatusId;
	}
	public void setPandianStatusId(String pandianStatusId) {
		this.pandianStatusId = pandianStatusId;
	}
	public String getPandianStatusName() {
		return pandianStatusName;
	}
	public void setPandianStatusName(String pandianStatusName) {
		this.pandianStatusName = pandianStatusName;
	}
	public String getBatchUuid() {
		return batchUuid;
	}
	public void setBatchUuid(String batchUuid) {
		this.batchUuid = batchUuid;
	}
	public Date getBaseDate() {
		return baseDate;
	}
	public void setBaseDate(Date baseDate) {
		this.baseDate = baseDate;
	}
	public Date getResultDate() {
		return resultDate;
	}
	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public User getCreateBy() {
		return createBy;
	}
	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public User getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	
	
}