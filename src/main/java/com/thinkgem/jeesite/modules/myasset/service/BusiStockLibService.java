/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.myasset.entity.BusiStockLib;
import com.thinkgem.jeesite.modules.myasset.dao.BusiStockLibDao;

/**
 * 资产库存Service
 * @author gucl
 * @version 2018-07-15
 */
@Service
@Transactional(readOnly = true)
public class BusiStockLibService extends CrudService<BusiStockLibDao, BusiStockLib> {

	public BusiStockLib get(String id) {
		return super.get(id);
	}
	
	public List<BusiStockLib> findList(BusiStockLib busiStockLib) {
		return super.findList(busiStockLib);
	}
	
	public Page<BusiStockLib> findPage(Page<BusiStockLib> page, BusiStockLib busiStockLib) {
		return super.findPage(page, busiStockLib);
	}
	
	@Transactional(readOnly = false)
	public void save(BusiStockLib busiStockLib) {
		super.save(busiStockLib);
	}
	
	@Transactional(readOnly = false)
	public void delete(BusiStockLib busiStockLib) {
		super.delete(busiStockLib);
	}
	
}