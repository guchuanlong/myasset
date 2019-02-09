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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.myasset.entity.BusiBorrowBill;
import com.thinkgem.jeesite.modules.myasset.dao.BusiBorrowBillDao;
import com.thinkgem.jeesite.modules.myasset.entity.BusiBorrowBillDtl;
import com.thinkgem.jeesite.modules.myasset.dao.BusiBorrowBillDtlDao;

/**
 * 资产领用Service
 * @author gucl
 * @version 2019-02-10
 */
@Service
@Transactional(readOnly = true)
public class BusiBorrowBillService extends CrudService<BusiBorrowBillDao, BusiBorrowBill> {

	@Autowired
	private BusiBorrowBillDtlDao busiBorrowBillDtlDao;
	
	public BusiBorrowBill get(String id) {
		BusiBorrowBill busiBorrowBill = super.get(id);
		busiBorrowBill.setBusiBorrowBillDtlList(busiBorrowBillDtlDao.findList(new BusiBorrowBillDtl(busiBorrowBill)));
		return busiBorrowBill;
	}
	
	public List<BusiBorrowBill> findList(BusiBorrowBill busiBorrowBill) {
		return super.findList(busiBorrowBill);
	}
	
	public Page<BusiBorrowBill> findPage(Page<BusiBorrowBill> page, BusiBorrowBill busiBorrowBill) {
		return super.findPage(page, busiBorrowBill);
	}
	
	@Transactional(readOnly = false)
	public void save(BusiBorrowBill busiBorrowBill) {
		super.save(busiBorrowBill);
		for (BusiBorrowBillDtl busiBorrowBillDtl : busiBorrowBill.getBusiBorrowBillDtlList()){
			if (busiBorrowBillDtl.getId() == null){
				continue;
			}
			if (BusiBorrowBillDtl.DEL_FLAG_NORMAL.equals(busiBorrowBillDtl.getDelFlag())){
				if (StringUtils.isBlank(busiBorrowBillDtl.getId())){
					busiBorrowBillDtl.setBorrowBillId(busiBorrowBill);
					busiBorrowBillDtl.preInsert();
					busiBorrowBillDtlDao.insert(busiBorrowBillDtl);
				}else{
					busiBorrowBillDtl.preUpdate();
					busiBorrowBillDtlDao.update(busiBorrowBillDtl);
				}
			}else{
				busiBorrowBillDtlDao.delete(busiBorrowBillDtl);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(BusiBorrowBill busiBorrowBill) {
		super.delete(busiBorrowBill);
		busiBorrowBillDtlDao.delete(new BusiBorrowBillDtl(busiBorrowBill));
	}
	
}