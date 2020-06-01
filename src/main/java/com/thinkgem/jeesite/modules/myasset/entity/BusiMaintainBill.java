/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 资产维护Entity
 * @author gucl
 * @version 2020-05-31
 */
public class BusiMaintainBill extends DataEntity<BusiMaintainBill> {
	
	private static final long serialVersionUID = 1L;
	private String maintainBillNo;		// 维护单编号
	private Office company;		// 申请人公司
	private Office office;		// 申请人部门
	private String applyPerson;		// 维护申请人
	private String maintainNum;		// 维护数量
	private Date applyDate;		// 申请维修日期
	private String status;		// 维护单状态
	private String osPlatformId;		// 操作平台
	private String operPerson;		// 经办人
	private List<BusiMaintainBillDtl> busiMaintainBillDtlList = Lists.newArrayList();		// 子表列表
	
	public BusiMaintainBill() {
		super();
	}

	public BusiMaintainBill(String id){
		super(id);
	}

	@Length(min=0, max=64, message="维护单编号长度必须介于 0 和 64 之间")
	public String getMaintainBillNo() {
		return maintainBillNo;
	}

	public void setMaintainBillNo(String maintainBillNo) {
		this.maintainBillNo = maintainBillNo;
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
	
	@Length(min=0, max=64, message="维护申请人长度必须介于 0 和 64 之间")
	public String getApplyPerson() {
		return applyPerson;
	}

	public void setApplyPerson(String applyPerson) {
		this.applyPerson = applyPerson;
	}
	
	public String getMaintainNum() {
		return maintainNum;
	}

	public void setMaintainNum(String maintainNum) {
		this.maintainNum = maintainNum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	@Length(min=0, max=32, message="维护单状态长度必须介于 0 和 32 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=32, message="操作平台长度必须介于 0 和 32 之间")
	public String getOsPlatformId() {
		return osPlatformId;
	}

	public void setOsPlatformId(String osPlatformId) {
		this.osPlatformId = osPlatformId;
	}
	
	@Length(min=0, max=64, message="经办人长度必须介于 0 和 64 之间")
	public String getOperPerson() {
		return operPerson;
	}

	public void setOperPerson(String operPerson) {
		this.operPerson = operPerson;
	}
	
	public List<BusiMaintainBillDtl> getBusiMaintainBillDtlList() {
		return busiMaintainBillDtlList;
	}

	public void setBusiMaintainBillDtlList(List<BusiMaintainBillDtl> busiMaintainBillDtlList) {
		this.busiMaintainBillDtlList = busiMaintainBillDtlList;
	}
}