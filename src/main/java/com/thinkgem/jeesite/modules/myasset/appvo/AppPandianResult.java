/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.appvo;

import java.io.Serializable;
import java.util.Date;

/**
 * 盘点结果Entity
 * @author gucl
 * @version 2019-05-16
 */
public class AppPandianResult implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected String id;
	private String assetBarcode;		// 资产条形码
	private String assetRfidEpc;		// 资产rfid epc
	private String pandianStatusId;		// 盘点状态
	private String pandianStatusName;		// 盘点状态
	private Date resultDate=new Date();		// 盘点时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	public Date getResultDate() {
		return resultDate;
	}
	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}
	
	
	
}