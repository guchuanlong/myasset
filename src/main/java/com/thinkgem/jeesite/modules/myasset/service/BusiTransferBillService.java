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
import com.thinkgem.jeesite.modules.myasset.dao.BusiTransferBillDao;
import com.thinkgem.jeesite.modules.myasset.dao.BusiTransferBillDtlDao;
import com.thinkgem.jeesite.modules.myasset.entity.BusiAssetMain;
import com.thinkgem.jeesite.modules.myasset.entity.BusiTransferBill;
import com.thinkgem.jeesite.modules.myasset.entity.BusiTransferBillDtl;

/**
 * 资产转移Service
 * @author gucl
 * @version 2020-05-31
 */
@Service
@Transactional(readOnly = true)
public class BusiTransferBillService extends CrudService<BusiTransferBillDao, BusiTransferBill> {

	@Autowired
	private BusiTransferBillDtlDao busiTransferBillDtlDao;
	
	@Autowired
	private BusiAssetMainDao busiAssetMainDao;
	
	public BusiTransferBill get(String id) {
		BusiTransferBill busiTransferBill = super.get(id);
		busiTransferBill.setBusiTransferBillDtlList(busiTransferBillDtlDao.findList(new BusiTransferBillDtl(busiTransferBill)));
		return busiTransferBill;
	}
	
	public List<BusiTransferBill> findList(BusiTransferBill busiTransferBill) {
		return super.findList(busiTransferBill);
	}
	
	public Page<BusiTransferBill> findPage(Page<BusiTransferBill> page, BusiTransferBill busiTransferBill) {
		return super.findPage(page, busiTransferBill);
	}
	
	@Transactional(readOnly = false)
	public void save(BusiTransferBill busiTransferBill) {
		String transferBillNo=DateUtil.getDateString(new Date(), "yyyyMMdd")+SeqUtil.getNewId(SeqConstant.TRANSFER_BILL_NO, 6);
		busiTransferBill.setTransferBillNo(transferBillNo);
		//操作平台
		busiTransferBill.setOsPlatformId(MyassetConstant.OsPlatFormId.PC);
		//数量
		busiTransferBill.setTransferNum(String.valueOf(busiTransferBill.getBusiTransferBillDtlList().size()));
		//状态
		busiTransferBill.setStatus(MyassetConstant.TransferBillStatus.TRANSFER);
		
		super.save(busiTransferBill);
		
		//删除原有的关联关系
		BusiTransferBillDtl deleteByBill=new BusiTransferBillDtl();
		deleteByBill.setTransferBillId(busiTransferBill);
		busiTransferBillDtlDao.delete(deleteByBill);
				
		for (BusiTransferBillDtl busiTransferBillDtl : busiTransferBill.getBusiTransferBillDtlList()){
			busiTransferBillDtl.setCompany(busiTransferBill.getCompany());
			busiTransferBillDtl.setOffice(busiTransferBill.getOffice());
			busiTransferBillDtl.setOsPlatformId(busiTransferBill.getOsPlatformId());
			//单个资产借用状态
			busiTransferBillDtl.setAssetIsTransfer(MyassetConstant.AssetIsReturn.NOT_RETURN);
			busiTransferBillDtl.setTransferBillId(busiTransferBill);
			busiTransferBillDtl.preInsert();
			busiTransferBillDtlDao.insert(busiTransferBillDtl);
			
			//资产状态改为使用中
			BusiAssetMain am=new BusiAssetMain();
			am.setId(busiTransferBillDtl.getAssetGlobalId());
			am.setRespPerson(busiTransferBill.getRecvRespPerson());
			am.setPlaceId(busiTransferBill.getRecvPlaceId());
			am.preUpdate();
			busiAssetMainDao.updateStatusById(am);
			
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(BusiTransferBill busiTransferBill) {
		super.delete(busiTransferBill);
		busiTransferBillDtlDao.delete(new BusiTransferBillDtl(busiTransferBill));
	}
	
}