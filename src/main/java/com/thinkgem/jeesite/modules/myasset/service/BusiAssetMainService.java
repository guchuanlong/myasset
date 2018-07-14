/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.myasset.entity.BusiAssetMain;
import com.thinkgem.jeesite.modules.myasset.dao.BusiAssetMainDao;

/**
 * 资产主表Service
 * @author gucl
 * @version 2018-07-15
 */
@Service
@Transactional(readOnly = true)
public class BusiAssetMainService extends CrudService<BusiAssetMainDao, BusiAssetMain> {

	public BusiAssetMain get(String id) {
		return super.get(id);
	}
	
	public List<BusiAssetMain> findList(BusiAssetMain busiAssetMain) {
		return super.findList(busiAssetMain);
	}
	
	public Page<BusiAssetMain> findPage(Page<BusiAssetMain> page, BusiAssetMain busiAssetMain) {
		return super.findPage(page, busiAssetMain);
	}
	
	@Transactional(readOnly = false)
	public void save(BusiAssetMain busiAssetMain) {
		super.save(busiAssetMain);
	}
	
	@Transactional(readOnly = false)
	public void delete(BusiAssetMain busiAssetMain) {
		super.delete(busiAssetMain);
	}
	
}