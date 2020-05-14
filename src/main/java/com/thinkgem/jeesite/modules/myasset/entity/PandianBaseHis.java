/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 盘点基础历史Entity
 * @author gucl
 * @version 2019-05-27
 */
public class PandianBaseHis extends DataEntity<PandianBaseHis> {
	
	private static final long serialVersionUID = 1L;
	private String cityCode;		// 地市编码（预留）
	private String cityName;		// 地市名称
	private Office company;		// 公司
	private String companyName;		// 公司名称
	private Office office;		// 供电单位
	private String powerSupplyUnit;		// 供电单位名称
	private String connectMethod;		// 接线方式
	private String bigClassCode;		// 资产大类编码
	private String bigClassName;		// 资产大类名称
	private String smallClassCode;		// 资产小类编码
	private String smallClassName;		// 资产小类名称
	private String assetBarcode;		// 资产条形码
	private String assetRfidEpc;		// 资产rfid epc
	private String pandianStatusId;		// 盘点状态编码  0-未盘  1-盘亏 2-盘平 3-盘盈
	private String pandianStatusName;		// 盘点状态名称0-未盘  1-盘亏 2-盘平 3-盘盈
	private String batchUuid;		// 盘点任务号
	private Date baseDate;		// 基础数据导入时间
	private Date resultDate;		// 盘点结果时间
	private Date historyTime;		// 进入历史表时间
	private String historyRemark;		// 进入历史表备注
	private String historyLoginName;		// 进入历史操作人登录名
	private String historyUserId;		// 进入历史操作人id
	
	public PandianBaseHis() {
		super();
	}

	public PandianBaseHis(String id){
		super(id);
	}

	@Length(min=0, max=50, message="地市编码（预留）长度必须介于 0 和 50 之间")
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	@Length(min=0, max=50, message="地市名称长度必须介于 0 和 50 之间")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public Office getCompany() {
		return company;
	}

	public void setCompany(Office company) {
		this.company = company;
	}
	
	@Length(min=0, max=50, message="公司名称长度必须介于 0 和 50 之间")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=50, message="供电单位名称长度必须介于 0 和 50 之间")
	public String getPowerSupplyUnit() {
		return powerSupplyUnit;
	}

	public void setPowerSupplyUnit(String powerSupplyUnit) {
		this.powerSupplyUnit = powerSupplyUnit;
	}
	
	@Length(min=0, max=50, message="接线方式长度必须介于 0 和 50 之间")
	public String getConnectMethod() {
		return connectMethod;
	}

	public void setConnectMethod(String connectMethod) {
		this.connectMethod = connectMethod;
	}
	
	@Length(min=0, max=50, message="资产大类编码长度必须介于 0 和 50 之间")
	public String getBigClassCode() {
		return bigClassCode;
	}

	public void setBigClassCode(String bigClassCode) {
		this.bigClassCode = bigClassCode;
	}
	
	@Length(min=0, max=50, message="资产大类名称长度必须介于 0 和 50 之间")
	public String getBigClassName() {
		return bigClassName;
	}

	public void setBigClassName(String bigClassName) {
		this.bigClassName = bigClassName;
	}
	
	@Length(min=0, max=50, message="资产小类编码长度必须介于 0 和 50 之间")
	public String getSmallClassCode() {
		return smallClassCode;
	}

	public void setSmallClassCode(String smallClassCode) {
		this.smallClassCode = smallClassCode;
	}
	
	@Length(min=0, max=100, message="资产小类名称长度必须介于 0 和 100 之间")
	public String getSmallClassName() {
		return smallClassName;
	}

	public void setSmallClassName(String smallClassName) {
		this.smallClassName = smallClassName;
	}
	
	@Length(min=0, max=50, message="资产条形码长度必须介于 0 和 50 之间")
	public String getAssetBarcode() {
		return assetBarcode;
	}

	public void setAssetBarcode(String assetBarcode) {
		this.assetBarcode = assetBarcode;
	}
	
	@Length(min=0, max=50, message="资产rfid epc长度必须介于 0 和 50 之间")
	public String getAssetRfidEpc() {
		return assetRfidEpc;
	}

	public void setAssetRfidEpc(String assetRfidEpc) {
		this.assetRfidEpc = assetRfidEpc;
	}
	
	@Length(min=0, max=11, message="盘点状态编码  0-未盘  1-盘亏 2-盘平 3-盘盈长度必须介于 0 和 11 之间")
	public String getPandianStatusId() {
		return pandianStatusId;
	}

	public void setPandianStatusId(String pandianStatusId) {
		this.pandianStatusId = pandianStatusId;
	}
	
	@Length(min=0, max=50, message="盘点状态名称0-未盘  1-盘亏 2-盘平 3-盘盈长度必须介于 0 和 50 之间")
	public String getPandianStatusName() {
		return pandianStatusName;
	}

	public void setPandianStatusName(String pandianStatusName) {
		this.pandianStatusName = pandianStatusName;
	}
	
	@Length(min=0, max=50, message="盘点任务号长度必须介于 0 和 50 之间")
	public String getBatchUuid() {
		return batchUuid;
	}

	public void setBatchUuid(String batchUuid) {
		this.batchUuid = batchUuid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBaseDate() {
		return baseDate;
	}

	public void setBaseDate(Date baseDate) {
		this.baseDate = baseDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getResultDate() {
		return resultDate;
	}

	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getHistoryTime() {
		return historyTime;
	}

	public void setHistoryTime(Date historyTime) {
		this.historyTime = historyTime;
	}
	
	@Length(min=0, max=255, message="进入历史表备注长度必须介于 0 和 255 之间")
	public String getHistoryRemark() {
		return historyRemark;
	}

	public void setHistoryRemark(String historyRemark) {
		this.historyRemark = historyRemark;
	}
	
	@Length(min=0, max=64, message="进入历史操作人登录名长度必须介于 0 和 64 之间")
	public String getHistoryLoginName() {
		return historyLoginName;
	}

	public void setHistoryLoginName(String historyLoginName) {
		this.historyLoginName = historyLoginName;
	}
	
	@Length(min=0, max=64, message="进入历史操作人id长度必须介于 0 和 64 之间")
	public String getHistoryUserId() {
		return historyUserId;
	}

	public void setHistoryUserId(String historyUserId) {
		this.historyUserId = historyUserId;
	}
	
}