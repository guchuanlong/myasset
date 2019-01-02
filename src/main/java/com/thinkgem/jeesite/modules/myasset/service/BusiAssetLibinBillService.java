/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.myasset.entity.BusiAssetLibinBill;
import com.thinkgem.jeesite.modules.myasset.dao.BusiAssetLibinBillDao;

/**
 * 资产入库Service
 * @author gucl
 * @version 2019-01-03
 */
@Service
@Transactional(readOnly = true)
public class BusiAssetLibinBillService extends CrudService<BusiAssetLibinBillDao, BusiAssetLibinBill> {

	public BusiAssetLibinBill get(String id) {
		return super.get(id);
	}
	
	public List<BusiAssetLibinBill> findList(BusiAssetLibinBill busiAssetLibinBill) {
		return super.findList(busiAssetLibinBill);
	}
	
	public Page<BusiAssetLibinBill> findPage(Page<BusiAssetLibinBill> page, BusiAssetLibinBill busiAssetLibinBill) {
		return super.findPage(page, busiAssetLibinBill);
	}
	
	@Transactional(readOnly = false)
	public void save(BusiAssetLibinBill busiAssetLibinBill) {
		super.save(busiAssetLibinBill);
	}
	
	@Transactional(readOnly = false)
	public void delete(BusiAssetLibinBill busiAssetLibinBill) {
		super.delete(busiAssetLibinBill);
	}
	
}