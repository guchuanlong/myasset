/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.myasset.entity.PandianBaseHis;
import com.thinkgem.jeesite.modules.myasset.dao.PandianBaseHisDao;

/**
 * 盘点基础历史Service
 * @author gucl
 * @version 2019-05-27
 */
@Service
@Transactional(readOnly = true)
public class PandianBaseHisService extends CrudService<PandianBaseHisDao, PandianBaseHis> {

	public PandianBaseHis get(String id) {
		return super.get(id);
	}
	
	public List<PandianBaseHis> findList(PandianBaseHis pandianBaseHis) {
		pandianBaseHis.getSqlMap().put("dsf", dataScopeFilter(pandianBaseHis.getCurrentUser(), "o6", "a"));
		return super.findList(pandianBaseHis);
	}
	
	public Page<PandianBaseHis> findPage(Page<PandianBaseHis> page, PandianBaseHis pandianBaseHis) {
		pandianBaseHis.getSqlMap().put("dsf", dataScopeFilter(pandianBaseHis.getCurrentUser(), "o6", "a"));
		return super.findPage(page, pandianBaseHis);
	}
	
	@Transactional(readOnly = false)
	public void save(PandianBaseHis pandianBaseHis) {
		super.save(pandianBaseHis);
	}
	
	@Transactional(readOnly = false)
	public void delete(PandianBaseHis pandianBaseHis) {
		super.delete(pandianBaseHis);
	}
	
}