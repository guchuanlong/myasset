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
import com.thinkgem.jeesite.modules.myasset.dao.BusiAssetMainDao;
import com.thinkgem.jeesite.modules.myasset.dao.BusiBorrowBillDtlDao;
import com.thinkgem.jeesite.modules.myasset.entity.BusiBorrowBillDtl;

/**
 * 资产领用明细Service
 * @author gucl
 * @version 2019-02-10
 */
@Service
@Transactional(readOnly = true)
public class BusiBorrowBillDtlService extends CrudService<BusiBorrowBillDtlDao, BusiBorrowBillDtl> {

	@Autowired
	private BusiBorrowBillDtlDao busiBorrowBillDtlDao;
	@Autowired
	private BusiAssetMainDao busiAssetMainDao;
	
	public BusiBorrowBillDtl get(String id) {
		BusiBorrowBillDtl busiBorrowBill = super.get(id);
		return busiBorrowBill;
	}
	
	public List<BusiBorrowBillDtl> findList(BusiBorrowBillDtl busiBorrowBill) {
		return super.findList(busiBorrowBill);
	}
	
	public Page<BusiBorrowBillDtl> findPage(Page<BusiBorrowBillDtl> page, BusiBorrowBillDtl busiBorrowBill) {
		return super.findPage(page, busiBorrowBill);
	}
	
	
}