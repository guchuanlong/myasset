/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 盘点大类Entity
 * @author gucl
 * @version 2019-05-11
 */
public class PandianBigclass extends DataEntity<PandianBigclass> {
	
	private static final long serialVersionUID = 1L;
	private String bigclassCode;		// 大类编码
	private String bigclassName;		// 大类名称
	
	public PandianBigclass() {
		super();
	}

	public PandianBigclass(String id){
		super(id);
	}

	@Length(min=1, max=10, message="大类编码长度必须介于 1 和 10 之间")
	public String getBigclassCode() {
		return bigclassCode;
	}

	public void setBigclassCode(String bigclassCode) {
		this.bigclassCode = bigclassCode;
	}
	
	@Length(min=0, max=50, message="大类名称长度必须介于 0 和 50 之间")
	public String getBigclassName() {
		return bigclassName;
	}

	public void setBigclassName(String bigclassName) {
		this.bigclassName = bigclassName;
	}
	
}