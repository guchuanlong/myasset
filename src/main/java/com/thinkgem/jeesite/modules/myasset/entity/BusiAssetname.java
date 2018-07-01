/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.myasset.entity.BusiCategory;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 资产名称Entity
 * @author gucl
 * @version 2018-07-01
 */
public class BusiAssetname extends DataEntity<BusiAssetname> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 资产名称
	private BusiCategory category;		// 资产分类
	private String sort;		// 排序
	
	public BusiAssetname() {
		super();
	}

	public BusiAssetname(String id){
		super(id);
	}

	@Length(min=1, max=100, message="资产名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public BusiCategory getCategory() {
		return category;
	}

	public void setCategory(BusiCategory category) {
		this.category = category;
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}