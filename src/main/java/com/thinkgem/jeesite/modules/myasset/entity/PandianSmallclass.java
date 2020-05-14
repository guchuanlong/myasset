/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 盘点小类Entity
 * @author gucl
 * @version 2019-05-11
 */
public class PandianSmallclass extends DataEntity<PandianSmallclass> {
	
	private static final long serialVersionUID = 1L;
	private String smallclassCode;		// 小类code
	private String smallclassName;		// 小类名称
	private String bigclassCode;		// 归属的大类code
	private String bigclassName;		// 归属的大类名称
	
	public PandianSmallclass() {
		super();
	}

	public PandianSmallclass(String id){
		super(id);
	}

	@Length(min=1, max=10, message="小类code长度必须介于 1 和 10 之间")
	public String getSmallclassCode() {
		return smallclassCode;
	}

	public void setSmallclassCode(String smallclassCode) {
		this.smallclassCode = smallclassCode;
	}
	
	@Length(min=0, max=50, message="小类名称长度必须介于 0 和 50 之间")
	public String getSmallclassName() {
		return smallclassName;
	}

	public void setSmallclassName(String smallclassName) {
		this.smallclassName = smallclassName;
	}
	
	@Length(min=0, max=10, message="归属的大类code长度必须介于 0 和 10 之间")
	public String getBigclassCode() {
		return bigclassCode;
	}

	public void setBigclassCode(String bigclassCode) {
		this.bigclassCode = bigclassCode;
	}
	
	@Length(min=0, max=100, message="归属的大类名称长度必须介于 0 和 100 之间")
	public String getBigclassName() {
		return bigclassName;
	}

	public void setBigclassName(String bigclassName) {
		this.bigclassName = bigclassName;
	}
	
}