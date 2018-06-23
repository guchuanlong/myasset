/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.entity;

import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 打印机Entity
 * @author gucl
 * @version 2018-06-23
 */
public class BusiPrinter extends DataEntity<BusiPrinter> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 归属用户
	private Office office;		// 归属部门
	private String printerIp;		// 打印机IP
	private String printerPort;		// 打印机端口
	private String printerCode;		// 打印机编号
	private String printerName;		// 打印机名称
	
	public BusiPrinter() {
		super();
	}

	public BusiPrinter(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=100, message="打印机IP长度必须介于 0 和 100 之间")
	public String getPrinterIp() {
		return printerIp;
	}

	public void setPrinterIp(String printerIp) {
		this.printerIp = printerIp;
	}
	
	public String getPrinterPort() {
		return printerPort;
	}

	public void setPrinterPort(String printerPort) {
		this.printerPort = printerPort;
	}
	
	@Length(min=0, max=64, message="打印机编号长度必须介于 0 和 64 之间")
	public String getPrinterCode() {
		return printerCode;
	}

	public void setPrinterCode(String printerCode) {
		this.printerCode = printerCode;
	}
	
	@Length(min=0, max=100, message="打印机名称长度必须介于 0 和 100 之间")
	public String getPrinterName() {
		return printerName;
	}

	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}
	
}