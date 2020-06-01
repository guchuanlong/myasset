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
import com.thinkgem.jeesite.modules.myasset.dao.BusiScrapBillDao;
import com.thinkgem.jeesite.modules.myasset.dao.BusiScrapBillDtlDao;
import com.thinkgem.jeesite.modules.myasset.entity.BusiAssetMain;
import com.thinkgem.jeesite.modules.myasset.entity.BusiScrapBill;
import com.thinkgem.jeesite.modules.myasset.entity.BusiScrapBillDtl;

/**
 * 资产报废Service
 * @author gucl
 * @version 2020-05-31
 */
@Service
@Transactional(readOnly = true)
public class BusiScrapBillService extends CrudService<BusiScrapBillDao, BusiScrapBill> {

	@Autowired
	private BusiScrapBillDtlDao busiScrapBillDtlDao;
	
	@Autowired
	private BusiAssetMainDao busiAssetMainDao;
	
	public BusiScrapBill get(String id) {
		BusiScrapBill busiScrapBill = super.get(id);
		busiScrapBill.setBusiScrapBillDtlList(busiScrapBillDtlDao.findList(new BusiScrapBillDtl(busiScrapBill)));
		return busiScrapBill;
	}
	
	public List<BusiScrapBill> findList(BusiScrapBill busiScrapBill) {
		return super.findList(busiScrapBill);
	}
	
	public Page<BusiScrapBill> findPage(Page<BusiScrapBill> page, BusiScrapBill busiScrapBill) {
		return super.findPage(page, busiScrapBill);
	}
	
	@Transactional(readOnly = false)
	public void save(BusiScrapBill busiScrapBill) {
		String scrapBillNo=DateUtil.getDateString(new Date(), "yyyyMMdd")+SeqUtil.getNewId(SeqConstant.SCRAP_BILL_NO, 6);
		busiScrapBill.setScrapBillNo(scrapBillNo);
		//操作平台
		busiScrapBill.setOsPlatformId(MyassetConstant.OsPlatFormId.PC);
		//数量
		busiScrapBill.setScrapNum(String.valueOf(busiScrapBill.getBusiScrapBillDtlList().size()));
		//状态
		busiScrapBill.setStatus(MyassetConstant.ScrapBillStatus.SCRAP);
		
		super.save(busiScrapBill);
		
		//删除原有的关联关系
		BusiScrapBillDtl deleteByBill=new BusiScrapBillDtl();
		deleteByBill.setScrapBillId(busiScrapBill);
		busiScrapBillDtlDao.delete(deleteByBill);
				
		for (BusiScrapBillDtl busiScrapBillDtl : busiScrapBill.getBusiScrapBillDtlList()){
			busiScrapBillDtl.setCompany(busiScrapBill.getCompany());
			busiScrapBillDtl.setOffice(busiScrapBill.getOffice());
			busiScrapBillDtl.setOsPlatformId(busiScrapBill.getOsPlatformId());
			//单个资产借用状态
			busiScrapBillDtl.setAssetIsScrap(MyassetConstant.AssetIsReturn.NOT_RETURN);
			busiScrapBillDtl.setScrapBillId(busiScrapBill);
			busiScrapBillDtl.preInsert();
			busiScrapBillDtlDao.insert(busiScrapBillDtl);
			
			//资产状态改为使用中
			BusiAssetMain am=new BusiAssetMain();
			am.setId(busiScrapBillDtl.getAssetGlobalId());
			am.setStatus(MyassetConstant.AssetStatus.SCRAP);
			am.preUpdate();
			busiAssetMainDao.updateStatusById(am);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(BusiScrapBill busiScrapBill) {
		super.delete(busiScrapBill);
		busiScrapBillDtlDao.delete(new BusiScrapBillDtl(busiScrapBill));
	}
	
}