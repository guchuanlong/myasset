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
 * 资产转移Entity
 * @author gucl
 * @version 2020-05-31
 */
public class BusiTransferBill extends DataEntity<BusiTransferBill> {
	
	private static final long serialVersionUID = 1L;
	private String transferBillNo;		// 转移单编号
	private Office company;		// 申请人公司
	private Office office;		// 申请人部门
	private String applyPerson;		// 申请人
	private String transferNum;		// 申请转移数量
	private Date applyDate;		// 申请转移时间
	private String status;		// 转移单状态
	private String osPlatformId;		// 操作平台
	private String operPerson;		// 经办人
	private String recvRespPerson;		// 接收人
	private String recvPlaceId;		// 接收地点
	private List<BusiTransferBillDtl> busiTransferBillDtlList = Lists.newArrayList();		// 子表列表
	
	public BusiTransferBill() {
		super();
	}

	public BusiTransferBill(String id){
		super(id);
	}

	@Length(min=0, max=64, message="转移单编号长度必须介于 0 和 64 之间")
	public String getTransferBillNo() {
		return transferBillNo;
	}

	public void setTransferBillNo(String transferBillNo) {
		this.transferBillNo = transferBillNo;
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
	
	@Length(min=0, max=64, message="申请人长度必须介于 0 和 64 之间")
	public String getApplyPerson() {
		return applyPerson;
	}

	public void setApplyPerson(String applyPerson) {
		this.applyPerson = applyPerson;
	}
	
	public String getTransferNum() {
		return transferNum;
	}

	public void setTransferNum(String transferNum) {
		this.transferNum = transferNum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	@Length(min=0, max=32, message="转移单状态长度必须介于 0 和 32 之间")
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
	
	public List<BusiTransferBillDtl> getBusiTransferBillDtlList() {
		return busiTransferBillDtlList;
	}

	public void setBusiTransferBillDtlList(List<BusiTransferBillDtl> busiTransferBillDtlList) {
		this.busiTransferBillDtlList = busiTransferBillDtlList;
	}
}