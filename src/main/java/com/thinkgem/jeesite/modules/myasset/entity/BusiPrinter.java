/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.entity;

import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 打印机Entity
 * @author gucl
 * @version 2018-06-13
 */
public class BusiPrinter extends DataEntity<BusiPrinter> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 归属用户
	private Office office;		// 归属部门
	private Area area;		// 归属区域
	private String printerIp;		// 打印机IP
	private Integer printerPort;		// 打印机端口
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
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@Length(min=0, max=100, message="打印机IP长度必须介于 0 和 100 之间")
	public String getPrinterIp() {
		return printerIp;
	}

	public void setPrinterIp(String printerIp) {
		this.printerIp = printerIp;
	}
	
	public Integer getPrinterPort() {
		return printerPort;
	}

	public void setPrinterPort(Integer printerPort) {
		this.printerPort = printerPort;
	}
	
	@Length(min=0, max=100, message="打印机名称长度必须介于 0 和 100 之间")
	public String getPrinterName() {
		return printerName;
	}

	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}
	
}