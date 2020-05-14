/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.myasset.dao.PandianBaseDao;
import com.thinkgem.jeesite.modules.myasset.dao.PandianResultDao;
import com.thinkgem.jeesite.modules.myasset.entity.PandianBase;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 盘点基础表Service
 * @author gucl
 * @version 2019-05-16
 */
@Service
@Transactional(readOnly = true)
public class PandianBaseService extends CrudService<PandianBaseDao, PandianBase> {
	@Autowired
	protected PandianResultDao resultDao;
	public PandianBase get(String id) {
		return super.get(id);
	}
	
	public List<PandianBase> findList(PandianBase pandianBase) {
		pandianBase.getSqlMap().put("dsf", dataScopeFilter(pandianBase.getCurrentUser(), "o8", "a"));
		return super.findList(pandianBase);
	}
	
	public Page<PandianBase> findPage(Page<PandianBase> page, PandianBase pandianBase) {
		pandianBase.getSqlMap().put("dsf", dataScopeFilter(pandianBase.getCurrentUser(), "o8", "a"));
		return super.findPage(page, pandianBase);
	}
	
	@Transactional(readOnly = false)
	public void save(PandianBase pandianBase) {
		super.save(pandianBase);
	}
	
	@Transactional(readOnly = false)
	public void delete(PandianBase pandianBase) {
		super.delete(pandianBase);
	}
	
	/**
	 * 根据供电单位插入历史数据
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false)
	public int move2hisByPowerUnit(User user) {
		PandianBaseDao pdao=dao;
		int baseCnt=pdao.insert2hisByPowerUnit(user);
		baseCnt=pdao.deleteByPowerUnit(user);
		int resultCnt=0;
		try {
			resultCnt=resultDao.insert2hisByPowerUnit(user);
			resultCnt=resultDao.deleteByPowerUnit(user);
			
		}
		catch(Exception e) {
			logger.error("操作盘点结果表出错：",e.getMessage(),e);
		}
		logger.info("move2hisByPowerUnit baseCnt:{},resultCnt:{}",baseCnt,resultCnt);
		return baseCnt;
	}
	
	
	
}