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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.myasset.entity.BusiBorrowBill;
import com.thinkgem.jeesite.modules.myasset.constant.MyassetConstant;
import com.thinkgem.jeesite.modules.myasset.constant.SeqConstant;
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
		for (BusiBorrowBillDtl busiBorrowBillDtl : busiBorrowBill.getBusiBorrowBillDtlList()){
			if (busiBorrowBillDtl.getId() == null){
				continue;
			}
			busiBorrowBillDtl.setCompany(busiBorrowBill.getCompany());
			busiBorrowBillDtl.setOffice(busiBorrowBill.getOffice());
			busiBorrowBillDtl.setOsPlatformId(busiBorrowBill.getOsPlatformId());
			busiBorrowBillDtl.setAssetIsReturn(MyassetConstant.AssetIsReturn.NOT_RETURN);
			
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