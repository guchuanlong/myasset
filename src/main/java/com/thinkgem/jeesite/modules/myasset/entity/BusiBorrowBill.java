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
 * 资产领用Entity
 * @author gucl
 * @version 2019-02-10
 */
public class BusiBorrowBill extends DataEntity<BusiBorrowBill> {
	
	private static final long serialVersionUID = 1L;
	private String borrowBillNo;		// 领用单编号
	private Office company;		// 领用人归属公司
	private Office office;		// 领用人归属部门
	private String borrowPerson;		// 领用人
	private String borrowNum;		// 领用数量
	private Date expReturnDate;		// 预期归还时间
	private String status;		// 领用单状态
	private String osPlatformId;		// 操作平台（1：pc端，2：手持机）
	private List<BusiBorrowBillDtl> busiBorrowBillDtlList = Lists.newArrayList();		// 子表列表
	
	public BusiBorrowBill() {
		super();
	}

	public BusiBorrowBill(String id){
		super(id);
	}

	@Length(min=0, max=64, message="领用单编号长度必须介于 0 和 64 之间")
	public String getBorrowBillNo() {
		return borrowBillNo;
	}

	public void setBorrowBillNo(String borrowBillNo) {
		this.borrowBillNo = borrowBillNo;
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
	
	@Length(min=0, max=64, message="领用人长度必须介于 0 和 64 之间")
	public String getBorrowPerson() {
		return borrowPerson;
	}

	public void setBorrowPerson(String borrowPerson) {
		this.borrowPerson = borrowPerson;
	}
	
	public String getBorrowNum() {
		return borrowNum;
	}

	public void setBorrowNum(String borrowNum) {
		this.borrowNum = borrowNum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpReturnDate() {
		return expReturnDate;
	}

	public void setExpReturnDate(Date expReturnDate) {
		this.expReturnDate = expReturnDate;
	}
	
	@Length(min=0, max=32, message="领用单状态长度必须介于 0 和 32 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=32, message="操作平台（1：pc端，2：手持机）长度必须介于 0 和 32 之间")
	public String getOsPlatformId() {
		return osPlatformId;
	}

	public void setOsPlatformId(String osPlatformId) {
		this.osPlatformId = osPlatformId;
	}
	
	public List<BusiBorrowBillDtl> getBusiBorrowBillDtlList() {
		return busiBorrowBillDtlList;
	}

	public void setBusiBorrowBillDtlList(List<BusiBorrowBillDtl> busiBorrowBillDtlList) {
		this.busiBorrowBillDtlList = busiBorrowBillDtlList;
	}
}