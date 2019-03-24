/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.thinkgem.jeesite.modules.myasset.dao.BusiReturnBillDao;
import com.thinkgem.jeesite.modules.myasset.dao.BusiReturnBillDtlDao;
import com.thinkgem.jeesite.modules.myasset.entity.BusiAssetMain;
import com.thinkgem.jeesite.modules.myasset.entity.BusiBorrowBill;
import com.thinkgem.jeesite.modules.myasset.entity.BusiBorrowBillDtl;
import com.thinkgem.jeesite.modules.myasset.entity.BusiReturnBill;
import com.thinkgem.jeesite.modules.myasset.entity.BusiReturnBillDtl;

/**
 * 资产归还Service
 * @author gucl
 * @version 2019-02-11
 */
@Service
@Transactional(readOnly = true)
public class BusiReturnBillService extends CrudService<BusiReturnBillDao, BusiReturnBill> {

	@Autowired
	private BusiReturnBillDtlDao busiReturnBillDtlDao;
	
	@Autowired
	private BusiBorrowBillDao busiBorrowBillDao;
	@Autowired
	private BusiBorrowBillDtlDao busiBorrowBillDtlDao;
	@Autowired
	private BusiAssetMainDao busiAssetMainDao;
	
	public BusiReturnBill get(String id) {
		BusiReturnBill busiReturnBill = super.get(id);
		busiReturnBill.setBusiReturnBillDtlList(busiReturnBillDtlDao.findList(new BusiReturnBillDtl(busiReturnBill)));
		return busiReturnBill;
	}
	
	public List<BusiReturnBill> findList(BusiReturnBill busiReturnBill) {
		return super.findList(busiReturnBill);
	}
	
	public Page<BusiReturnBill> findPage(Page<BusiReturnBill> page, BusiReturnBill busiReturnBill) {
		return super.findPage(page, busiReturnBill);
	}
	
	@Transactional(readOnly = false)
	public void save(BusiReturnBill busiReturnBill) {
		String returnBillNo=DateUtil.getDateString(new Date(), "yyyyMMdd")+SeqUtil.getNewId(SeqConstant.RETURN_BILL_NO, 6);
		//归还单号
		busiReturnBill.setReturnBillNo(returnBillNo);
		//操作平台
		busiReturnBill.setOsPlatformId(MyassetConstant.OsPlatFormId.PC);
		//归还数量
		busiReturnBill.setReturnNum(String.valueOf(busiReturnBill.getBusiReturnBillDtlList().size()));
		
		super.save(busiReturnBill);
		//记录归还的领用单ID
		Map<String,BusiBorrowBill> borrowBillIdMap=new HashMap<>();
		for (BusiReturnBillDtl busiReturnBillDtl : busiReturnBill.getBusiReturnBillDtlList()){
			
			busiReturnBillDtl.setCompany(busiReturnBill.getCompany());
			busiReturnBillDtl.setOffice(busiReturnBill.getOffice());
			busiReturnBillDtl.setOsPlatformId(busiReturnBill.getOsPlatformId());
			busiReturnBillDtl.setReturnBillId(busiReturnBill);
			busiReturnBillDtl.preInsert();
			busiReturnBillDtlDao.insert(busiReturnBillDtl);
			
			
			//领用单明细asset_is_return修改
			BusiBorrowBillDtl borrowDtl=new BusiBorrowBillDtl();
			borrowDtl.setAssetIsReturn(MyassetConstant.AssetIsReturn.RETURN);
			borrowDtl.setId(busiReturnBillDtl.getBorrowBillDtlId());
			borrowDtl.preUpdate();
			busiBorrowBillDtlDao.updateAssetIsReturnByDtlId(borrowDtl);
			
			//领用单存入map
			BusiBorrowBill borrow=new BusiBorrowBill();
			borrow.setId(busiReturnBillDtl.getBorrowBillId());
			borrowBillIdMap.put(borrow.getId(), borrow);
			
			//资产主表状态修改
			BusiAssetMain am=new BusiAssetMain();
			am.setId(busiReturnBillDtl.getAssetGlobalId());
			am.setStatus(MyassetConstant.AssetStatus.STOCK);
			am.preUpdate();
			busiAssetMainDao.updateStatusById(am);
			
		}
		
		//判断领用单是否已全部归还
		for (Map.Entry<String,BusiBorrowBill> entry : borrowBillIdMap.entrySet()) { 
			  BusiBorrowBill borrow=entry.getValue();
			  BusiBorrowBillDtl borrowDtl=new BusiBorrowBillDtl();
			  borrowDtl.setBorrowBillId(borrow);
			  long cnt=busiBorrowBillDtlDao.countNoReturnByBorrowBillId(borrowDtl);
			  if(cnt>0) {
				  //部分归还
				  borrow.setStatus(MyassetConstant.BorrowBillStatus.PART_RETURN);
			  }
			  else {
				  //全部归还
				  borrow.setStatus(MyassetConstant.BorrowBillStatus.ALL_RETURNED);
			  }
			  borrow.preUpdate();
			  busiBorrowBillDao.updateStatusById(borrow);
			  
		}
		
		
	}
	
	@Transactional(readOnly = false)
	public void delete(BusiReturnBill busiReturnBill) {
		super.delete(busiReturnBill);
		busiReturnBillDtlDao.delete(new BusiReturnBillDtl(busiReturnBill));
	}
	
}