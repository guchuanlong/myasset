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
import com.thinkgem.jeesite.modules.myasset.entity.BusiAssetMain;
import com.thinkgem.jeesite.modules.myasset.entity.BusiMaintainBillDtl;
import com.thinkgem.jeesite.modules.myasset.entity.BusiMaintainBill;
import com.thinkgem.jeesite.modules.myasset.constant.MyassetConstant;
import com.thinkgem.jeesite.modules.myasset.constant.SeqConstant;
import com.thinkgem.jeesite.modules.myasset.dao.BusiAssetMainDao;
import com.thinkgem.jeesite.modules.myasset.dao.BusiMaintainBillDao;
import com.thinkgem.jeesite.modules.myasset.entity.BusiMaintainBillDtl;
import com.thinkgem.jeesite.modules.myasset.dao.BusiMaintainBillDtlDao;

/**
 * 资产维护Service
 * @author gucl
 * @version 2020-05-31
 */
@Service
@Transactional(readOnly = true)
public class BusiMaintainBillService extends CrudService<BusiMaintainBillDao, BusiMaintainBill> {

	@Autowired
	private BusiMaintainBillDtlDao busiMaintainBillDtlDao;
	
	@Autowired
	private BusiAssetMainDao busiAssetMainDao;
	
	public BusiMaintainBill get(String id) {
		BusiMaintainBill busiMaintainBill = super.get(id);
		busiMaintainBill.setBusiMaintainBillDtlList(busiMaintainBillDtlDao.findList(new BusiMaintainBillDtl(busiMaintainBill)));
		return busiMaintainBill;
	}
	
	public List<BusiMaintainBill> findList(BusiMaintainBill busiMaintainBill) {
		return super.findList(busiMaintainBill);
	}
	
	public Page<BusiMaintainBill> findPage(Page<BusiMaintainBill> page, BusiMaintainBill busiMaintainBill) {
		return super.findPage(page, busiMaintainBill);
	}
	
	@Transactional(readOnly = false)
	public void save(BusiMaintainBill busiMaintainBill) {
		String maintainBillNo=DateUtil.getDateString(new Date(), "yyyyMMdd")+SeqUtil.getNewId(SeqConstant.MAINTAIN_BILL_NO, 6);
		busiMaintainBill.setMaintainBillNo(maintainBillNo);
		//操作平台
		busiMaintainBill.setOsPlatformId(MyassetConstant.OsPlatFormId.PC);
		//数量
		busiMaintainBill.setMaintainNum(String.valueOf(busiMaintainBill.getBusiMaintainBillDtlList().size()));
		//状态
		busiMaintainBill.setStatus(MyassetConstant.MaintainBillStatus.MAINTAIN);
		
		super.save(busiMaintainBill);
		
		//删除原有的关联关系
		BusiMaintainBillDtl deleteByBill=new BusiMaintainBillDtl();
		deleteByBill.setMaintainBillId(busiMaintainBill);
		busiMaintainBillDtlDao.delete(deleteByBill);
				
		for (BusiMaintainBillDtl busiMaintainBillDtl : busiMaintainBill.getBusiMaintainBillDtlList()){
			busiMaintainBillDtl.setCompany(busiMaintainBill.getCompany());
			busiMaintainBillDtl.setOffice(busiMaintainBill.getOffice());
			busiMaintainBillDtl.setOsPlatformId(busiMaintainBill.getOsPlatformId());
			//单个资产借用状态
			busiMaintainBillDtl.setAssetIsMaintain(MyassetConstant.AssetIsReturn.NOT_RETURN);
			busiMaintainBillDtl.setMaintainBillId(busiMaintainBill);
			busiMaintainBillDtl.preInsert();
			busiMaintainBillDtlDao.insert(busiMaintainBillDtl);
			
			//资产状态改为使用中
			BusiAssetMain am=new BusiAssetMain();
			am.setId(busiMaintainBillDtl.getAssetGlobalId());
			am.setStatus(MyassetConstant.AssetStatus.MAINTAIN);
			am.preUpdate();
			busiAssetMainDao.updateStatusById(am);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(BusiMaintainBill busiMaintainBill) {
		super.delete(busiMaintainBill);
		busiMaintainBillDtlDao.delete(new BusiMaintainBillDtl(busiMaintainBill));
	}
	
}