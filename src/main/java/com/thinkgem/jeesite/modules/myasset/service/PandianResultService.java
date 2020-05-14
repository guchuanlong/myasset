/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.myasset.appvo.AppPandianResultSaveRequest;
import com.thinkgem.jeesite.modules.myasset.dao.PandianResultDao;
import com.thinkgem.jeesite.modules.myasset.entity.PandianResult;

/**
 * 盘点结果Service
 * @author gucl
 * @version 2019-05-16
 */
@Service
@Transactional(readOnly = true)
public class PandianResultService extends CrudService<PandianResultDao, PandianResult> {

	public PandianResult get(String id) {
		return super.get(id);
	}
	
	public List<PandianResult> findList(PandianResult pandianResult) {
		pandianResult.getSqlMap().put("dsf", dataScopeFilter(pandianResult.getCurrentUser(), "o8", "a"));
		return super.findList(pandianResult);
	}
	
	public Page<PandianResult> findPage(Page<PandianResult> page, PandianResult pandianResult) {
		pandianResult.getSqlMap().put("dsf", dataScopeFilter(pandianResult.getCurrentUser(), "o8", "a"));
		return super.findPage(page, pandianResult);
	}
	
	@Transactional(readOnly = false)
	public void save(PandianResult pandianResult) {
		super.save(pandianResult);
	}
	
	@Transactional(readOnly = false)
	public void delete(PandianResult pandianResult) {
		super.delete(pandianResult);
	}
	@Transactional(readOnly = false)
	public void uploadAppResult( AppPandianResultSaveRequest appResult) {
		dao.deleteAppResultByPowerUnit(appResult.getAppUser());
		dao.insertAppResultByPowerUnit(appResult.getAppUser());
		dao.batchUpdateAppResult(appResult.getList());
	}
	
}