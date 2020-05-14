package com.thinkgem.jeesite.modules.myasset.appvo;

import java.io.Serializable;
import java.util.Date;

import com.thinkgem.jeesite.modules.sys.entity.Office;

public class DownloadRequest implements Serializable {
	private String loginName;
	private int pageNo=1;
	private int pageSize=10;
	private String cityName;		// 地市名称
	private String companyName;		// 公司名称
	private Office company;		// 公司
	private String powerSupplyUnit;		// 供电单位名称
	private String connectMethod;		// 接线方式
	private Office office;		// 供电单位
	private String bigClassCode;		// 资产大类编码
	private String bigClassName;		// 资产大类名称
	private String assetBarcode;		// 资产条形码
	private String assetRfidEpc;		// 资产rfid epc
	private String pandianStatusId;		// 盘点状态
	private String pandianStatusName;		// 盘点状态名称
	private String batchUuid;		// 批次
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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
	
	

}
