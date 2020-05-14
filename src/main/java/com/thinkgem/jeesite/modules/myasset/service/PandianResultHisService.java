/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.myasset.entity.PandianResultHis;
import com.thinkgem.jeesite.modules.myasset.dao.PandianResultHisDao;

/**
 * 盘点结果历史Service
 * @author gucl
 * @version 2019-05-27
 */
@Service
@Transactional(readOnly = true)
public class PandianResultHisService extends CrudService<PandianResultHisDao, PandianResultHis> {

	public PandianResultHis get(String id) {
		return super.get(id);
	}
	
	public List<PandianResultHis> findList(PandianResultHis pandianResultHis) {
		pandianResultHis.getSqlMap().put("dsf", dataScopeFilter(pandianResultHis.getCurrentUser(), "o6", "a"));
		return super.findList(pandianResultHis);
	}
	
	public Page<PandianResultHis> findPage(Page<PandianResultHis> page, PandianResultHis pandianResultHis) {
		pandianResultHis.getSqlMap().put("dsf", dataScopeFilter(pandianResultHis.getCurrentUser(), "o6", "a"));
		return super.findPage(page, pandianResultHis);
	}
	
	@Transactional(readOnly = false)
	public void save(PandianResultHis pandianResultHis) {
		super.save(pandianResultHis);
	}
	
	@Transactional(readOnly = false)
	public void delete(PandianResultHis pandianResultHis) {
		super.delete(pandianResultHis);
	}
	
}