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
 * 资产归还Entity
 * @author gucl
 * @version 2019-02-11
 */
public class BusiReturnBill extends DataEntity<BusiReturnBill> {
	
	private static final long serialVersionUID = 1L;
	private String borrowBillId;		// 借用单id
	private String returnBillNo;		// 归还单编号
	private Office company;		// 归属公司
	private Office office;		// 归属部门
	private String returnPerson;		// 归还人
	private String returnNum;		// 归还数量
	private Date returnDate;		// 归还时间
	private String operPerson;		// 经办人
	private String osPlatformId;		// 操作平台
	private List<BusiReturnBillDtl> busiReturnBillDtlList = Lists.newArrayList();		// 子表列表
	
	public BusiReturnBill() {
		super();
	}

	public BusiReturnBill(String id){
		super(id);
	}

	@Length(min=0, max=64, message="借用单id长度必须介于 0 和 64 之间")
	public String getBorrowBillId() {
		return borrowBillId;
	}

	public void setBorrowBillId(String borrowBillId) {
		this.borrowBillId = borrowBillId;
	}
	
	@Length(min=0, max=64, message="归还单编号长度必须介于 0 和 64 之间")
	public String getReturnBillNo() {
		return returnBillNo;
	}

	public void setReturnBillNo(String returnBillNo) {
		this.returnBillNo = returnBillNo;
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
	
	@Length(min=0, max=64, message="归还人长度必须介于 0 和 64 之间")
	public String getReturnPerson() {
		return returnPerson;
	}

	public void setReturnPerson(String returnPerson) {
		this.returnPerson = returnPerson;
	}
	
	public String getReturnNum() {
		return returnNum;
	}

	public void setReturnNum(String returnNum) {
		this.returnNum = returnNum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	@Length(min=0, max=32, message="经办人长度必须介于 0 和 32 之间")
	public String getOperPerson() {
		return operPerson;
	}

	public void setOperPerson(String operPerson) {
		this.operPerson = operPerson;
	}
	
	@Length(min=0, max=32, message="操作平台长度必须介于 0 和 32 之间")
	public String getOsPlatformId() {
		return osPlatformId;
	}

	public void setOsPlatformId(String osPlatformId) {
		this.osPlatformId = osPlatformId;
	}
	
	public List<BusiReturnBillDtl> getBusiReturnBillDtlList() {
		return busiReturnBillDtlList;
	}

	public void setBusiReturnBillDtlList(List<BusiReturnBillDtl> busiReturnBillDtlList) {
		this.busiReturnBillDtlList = busiReturnBillDtlList;
	}
}