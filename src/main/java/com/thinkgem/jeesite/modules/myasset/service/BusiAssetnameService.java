/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.myasset.entity.BusiAssetname;
import com.thinkgem.jeesite.modules.myasset.dao.BusiAssetnameDao;

/**
 * 资产名称Service
 * @author gucl
 * @version 2018-07-01
 */
@Service
@Transactional(readOnly = true)
public class BusiAssetnameService extends CrudService<BusiAssetnameDao, BusiAssetname> {

	public BusiAssetname get(String id) {
		return super.get(id);
	}
	
	public List<BusiAssetname> findList(BusiAssetname busiAssetname) {
		return super.findList(busiAssetname);
	}
	
	public Page<BusiAssetname> findPage(Page<BusiAssetname> page, BusiAssetname busiAssetname) {
		return super.findPage(page, busiAssetname);
	}
	
	@Transactional(readOnly = false)
	public void save(BusiAssetname busiAssetname) {
		super.save(busiAssetname);
	}
	
	@Transactional(readOnly = false)
	public void delete(BusiAssetname busiAssetname) {
		super.delete(busiAssetname);
	}
	
}