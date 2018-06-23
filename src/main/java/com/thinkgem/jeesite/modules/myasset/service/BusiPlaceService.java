/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.myasset.entity.BusiPlace;
import com.thinkgem.jeesite.modules.myasset.dao.BusiPlaceDao;

/**
 * 资产存放地点Service
 * @author gucl
 * @version 2018-06-23
 */
@Service
@Transactional(readOnly = true)
public class BusiPlaceService extends CrudService<BusiPlaceDao, BusiPlace> {

	public BusiPlace get(String id) {
		return super.get(id);
	}
	
	public List<BusiPlace> findList(BusiPlace busiPlace) {
		return super.findList(busiPlace);
	}
	
	public Page<BusiPlace> findPage(Page<BusiPlace> page, BusiPlace busiPlace) {
		return super.findPage(page, busiPlace);
	}
	
	@Transactional(readOnly = false)
	public void save(BusiPlace busiPlace) {
		super.save(busiPlace);
	}
	
	@Transactional(readOnly = false)
	public void delete(BusiPlace busiPlace) {
		super.delete(busiPlace);
	}
	
}