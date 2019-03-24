/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myxapp.sdk.sequence.util.SeqUtil;
import com.myxapp.sdk.util.DateUtil;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.myasset.constant.MyassetConstant;
import com.thinkgem.jeesite.modules.myasset.constant.SeqConstant;
import com.thinkgem.jeesite.modules.myasset.dao.BusiAssetMainDao;
import com.thinkgem.jeesite.modules.myasset.dao.BusiBorrowBillDao;
import com.thinkgem.jeesite.modules.myasset.dao.BusiBorrowBillDtlDao;
import com.thinkgem.jeesite.modules.myasset.entity.BusiAssetMain;
import com.thinkgem.jeesite.modules.myasset.entity.BusiBorrowBill;
import com.thinkgem.jeesite.modules.myasset.entity.BusiBorrowBillDtl;

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
	@Autowired
	private BusiAssetMainDao busiAssetMainDao;
	
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
		String borrowBillNo=DateUtil.getDateString(new Date(), "yyyyMMdd")+SeqUtil.getNewId(SeqConstant.BORROW_BILL_NO, 6);
		//领用单号
		busiBorrowBill.setBorrowBillNo(borrowBillNo);
		//操作平台
		busiBorrowBill.setOsPlatformId(MyassetConstant.OsPlatFormId.PC);
		//领用数量
		busiBorrowBill.setBorrowNum(String.valueOf(busiBorrowBill.getBusiBorrowBillDtlList().size()));
		//领用单状态
		busiBorrowBill.setStatus(MyassetConstant.BorrowBillStatus.ALL_NOT_RETURN);
		
		super.save(busiBorrowBill);
		//删除原有的关联关系
		BusiBorrowBillDtl deleteByBill=new BusiBorrowBillDtl();
		deleteByBill.setBorrowBillId(busiBorrowBill);
		busiBorrowBillDtlDao.delete(deleteByBill);
		
		for (BusiBorrowBillDtl busiBorrowBillDtl : busiBorrowBill.getBusiBorrowBillDtlList()){
			busiBorrowBillDtl.setCompany(busiBorrowBill.getCompany());
			busiBorrowBillDtl.setOffice(busiBorrowBill.getOffice());
			busiBorrowBillDtl.setOsPlatformId(busiBorrowBill.getOsPlatformId());
			busiBorrowBillDtl.setAssetIsReturn(MyassetConstant.AssetIsReturn.NOT_RETURN);
			busiBorrowBillDtl.setBorrowBillId(busiBorrowBill);
			busiBorrowBillDtl.preInsert();
			busiBorrowBillDtlDao.insert(busiBorrowBillDtl);
			
			//资产状态改为使用中
			BusiAssetMain am=new BusiAssetMain();
			am.setId(busiBorrowBillDtl.getAssetGlobalId());
			am.setStatus(MyassetConstant.AssetStatus.USING);
			am.preUpdate();
			busiAssetMainDao.updateStatusById(am);
			
			
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(BusiBorrowBill busiBorrowBill) {
		super.delete(busiBorrowBill);
		busiBorrowBillDtlDao.delete(new BusiBorrowBillDtl(busiBorrowBill));
	}
	
}