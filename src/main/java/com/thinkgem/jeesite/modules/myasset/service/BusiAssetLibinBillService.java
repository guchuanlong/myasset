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
import com.thinkgem.jeesite.modules.myasset.dao.BusiAssetLibinBillDao;
import com.thinkgem.jeesite.modules.myasset.dao.BusiAssetMainDao;
import com.thinkgem.jeesite.modules.myasset.entity.BusiAssetLibinBill;
import com.thinkgem.jeesite.modules.myasset.entity.BusiAssetMain;

/**
 * 资产入库Service
 * @author gucl
 * @version 2019-01-03
 */
@Service
@Transactional(readOnly = true)
public class BusiAssetLibinBillService extends CrudService<BusiAssetLibinBillDao, BusiAssetLibinBill> {

	@Autowired
	private BusiAssetMainDao assetMainDao;
	
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
		//super.save(busiAssetLibinBill);
		if (busiAssetLibinBill.getIsNewRecord()){
			busiAssetLibinBill.preInsert();
			dao.insert(busiAssetLibinBill);
			//插入到资产主表
			int libnum=Integer.parseInt(busiAssetLibinBill.getLibinNum());
			for(int i=0;i<libnum;i++) {
				BusiAssetMain am=new BusiAssetMain();
				//TODO 设置资产属性
				am.preInsert();
				assetMainDao.insert(am);
			}
			
			//TODO 插入到库存表
			
			
		}else{
			busiAssetLibinBill.preUpdate();
			dao.update(busiAssetLibinBill);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(BusiAssetLibinBill busiAssetLibinBill) {
		super.delete(busiAssetLibinBill);
	}
	
}