/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.myasset.entity.PandianBigclass;
import com.thinkgem.jeesite.modules.myasset.dao.PandianBigclassDao;

/**
 * 盘点大类Service
 * @author gucl
 * @version 2019-05-11
 */
@Service
@Transactional(readOnly = true)
public class PandianBigclassService extends CrudService<PandianBigclassDao, PandianBigclass> {

	public PandianBigclass get(String id) {
		return super.get(id);
	}
	
	public List<PandianBigclass> findList(PandianBigclass pandianBigclass) {
		return super.findList(pandianBigclass);
	}
	
	public Page<PandianBigclass> findPage(Page<PandianBigclass> page, PandianBigclass pandianBigclass) {
		return super.findPage(page, pandianBigclass);
	}
	
	@Transactional(readOnly = false)
	public void save(PandianBigclass pandianBigclass) {
		super.save(pandianBigclass);
	}
	
	@Transactional(readOnly = false)
	public void delete(PandianBigclass pandianBigclass) {
		super.delete(pandianBigclass);
	}
	
}