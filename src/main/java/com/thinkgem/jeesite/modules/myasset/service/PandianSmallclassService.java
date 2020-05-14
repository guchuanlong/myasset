/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.myasset.entity.PandianSmallclass;
import com.thinkgem.jeesite.modules.myasset.dao.PandianSmallclassDao;

/**
 * 盘点小类Service
 * @author gucl
 * @version 2019-05-11
 */
@Service
@Transactional(readOnly = true)
public class PandianSmallclassService extends CrudService<PandianSmallclassDao, PandianSmallclass> {

	public PandianSmallclass get(String id) {
		return super.get(id);
	}
	
	public List<PandianSmallclass> findList(PandianSmallclass pandianSmallclass) {
		return super.findList(pandianSmallclass);
	}
	
	public Page<PandianSmallclass> findPage(Page<PandianSmallclass> page, PandianSmallclass pandianSmallclass) {
		return super.findPage(page, pandianSmallclass);
	}
	
	@Transactional(readOnly = false)
	public void save(PandianSmallclass pandianSmallclass) {
		super.save(pandianSmallclass);
	}
	
	@Transactional(readOnly = false)
	public void delete(PandianSmallclass pandianSmallclass) {
		super.delete(pandianSmallclass);
	}
	
}