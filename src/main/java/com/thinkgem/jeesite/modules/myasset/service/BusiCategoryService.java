/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.myasset.entity.BusiCategory;
import com.thinkgem.jeesite.modules.myasset.dao.BusiCategoryDao;

/**
 * 资产分类Service
 * @author gucl
 * @version 2018-06-23
 */
@Service
@Transactional(readOnly = true)
public class BusiCategoryService extends TreeService<BusiCategoryDao, BusiCategory> {

	public BusiCategory get(String id) {
		return super.get(id);
	}
	
	public List<BusiCategory> findList(BusiCategory busiCategory) {
		if (StringUtils.isNotBlank(busiCategory.getParentIds())){
			busiCategory.setParentIds(","+busiCategory.getParentIds()+",");
		}
		return super.findList(busiCategory);
	}
	
	@Transactional(readOnly = false)
	public void save(BusiCategory busiCategory) {
		super.save(busiCategory);
	}
	
	@Transactional(readOnly = false)
	public void delete(BusiCategory busiCategory) {
		super.delete(busiCategory);
	}
	
}