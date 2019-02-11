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
import com.thinkgem.jeesite.modules.myasset.entity.BusiReturnBill;
import com.thinkgem.jeesite.modules.myasset.constant.MyassetConstant;
import com.thinkgem.jeesite.modules.myasset.constant.SeqConstant;
import com.thinkgem.jeesite.modules.myasset.dao.BusiReturnBillDao;
import com.thinkgem.jeesite.modules.myasset.entity.BusiReturnBillDtl;
import com.thinkgem.jeesite.modules.myasset.dao.BusiReturnBillDtlDao;

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
		for (BusiReturnBillDtl busiReturnBillDtl : busiReturnBill.getBusiReturnBillDtlList()){
			if (busiReturnBillDtl.getId() == null){
				continue;
			}
			busiReturnBillDtl.setCompany(busiReturnBill.getCompany());
			busiReturnBillDtl.setOffice(busiReturnBill.getOffice());
			busiReturnBillDtl.setOsPlatformId(busiReturnBill.getOsPlatformId());
			
			if (BusiReturnBillDtl.DEL_FLAG_NORMAL.equals(busiReturnBillDtl.getDelFlag())){
				if (StringUtils.isBlank(busiReturnBillDtl.getId())){
					busiReturnBillDtl.setReturnBillId(busiReturnBill);
					busiReturnBillDtl.preInsert();
					busiReturnBillDtlDao.insert(busiReturnBillDtl);
				}else{
					busiReturnBillDtl.preUpdate();
					busiReturnBillDtlDao.update(busiReturnBillDtl);
				}
			}else{
				busiReturnBillDtlDao.delete(busiReturnBillDtl);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(BusiReturnBill busiReturnBill) {
		super.delete(busiReturnBill);
		busiReturnBillDtlDao.delete(new BusiReturnBillDtl(busiReturnBill));
	}
	
}