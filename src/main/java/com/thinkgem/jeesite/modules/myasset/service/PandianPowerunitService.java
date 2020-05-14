/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.myasset.entity.PandianPowerunit;
import com.thinkgem.jeesite.modules.myasset.dao.PandianPowerunitDao;

/**
 * 供电单位Service
 * @author gucl
 * @version 2019-05-11
 */
@Service
@Transactional(readOnly = true)
public class PandianPowerunitService extends CrudService<PandianPowerunitDao, PandianPowerunit> {

	public PandianPowerunit get(String id) {
		return super.get(id);
	}
	
	public List<PandianPowerunit> findList(PandianPowerunit pandianPowerunit) {
		return super.findList(pandianPowerunit);
	}
	
	public Page<PandianPowerunit> findPage(Page<PandianPowerunit> page, PandianPowerunit pandianPowerunit) {
		return super.findPage(page, pandianPowerunit);
	}
	
	@Transactional(readOnly = false)
	public void save(PandianPowerunit pandianPowerunit) {
		super.save(pandianPowerunit);
	}
	
	@Transactional(readOnly = false)
	public void delete(PandianPowerunit pandianPowerunit) {
		super.delete(pandianPowerunit);
	}
	
}