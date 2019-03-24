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
import com.myxapp.sdk.util.BeanUtil;
import com.myxapp.sdk.util.CollectionUtil;
import com.myxapp.sdk.util.DateUtil;
import com.myxapp.sdk.util.StringUtil;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.myasset.constant.SeqConstant;
import com.thinkgem.jeesite.modules.myasset.dao.BusiAssetLibinBillDao;
import com.thinkgem.jeesite.modules.myasset.dao.BusiAssetMainDao;
import com.thinkgem.jeesite.modules.myasset.dao.BusiStockLibDao;
import com.thinkgem.jeesite.modules.myasset.entity.BusiAssetLibinBill;
import com.thinkgem.jeesite.modules.myasset.entity.BusiAssetMain;
import com.thinkgem.jeesite.modules.myasset.entity.BusiStockLib;

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
	@Autowired
	private BusiStockLibDao stockLibDao;
	
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
	public void save(BusiAssetLibinBill libinBill) {
		//super.save(busiAssetLibinBill);
		if (StringUtil.isBlank(libinBill.getId())){
			libinBill.preInsert();
			String libinBillNo=DateUtil.getDateString(new Date(), "yyyyMMdd")+SeqUtil.getNewId(SeqConstant.LIBIN_BILL_NO, 5);
			libinBill.setLibinBillNo(libinBillNo);
			dao.insert(libinBill);
			//插入到资产主表
			int libnum=Integer.parseInt(libinBill.getLibinNum());
			
			//插入到库存表
			BusiStockLib stocklibParam=new BusiStockLib();
			stocklibParam.setCompanyId(libinBill.getCompany().getId());
			stocklibParam.setOffice(libinBill.getOffice());
			stocklibParam.setCategory(libinBill.getCategory());
			stocklibParam.setAssetnameId(libinBill.getAssetnameId());
			stocklibParam.setPlaceId(libinBill.getPlaceId());
			stocklibParam.setMeasureUnitId(libinBill.getMeasureUnitId());
			List<BusiStockLib> stocklist=stockLibDao.findList(stocklibParam);
			String stocklibId="";
			if(CollectionUtil.isEmpty(stocklist)) {
				//新增
				BusiStockLib stockAdd=new BusiStockLib();
				BeanUtil.copyProperties(stockAdd, stocklibParam);
				stockAdd.setStockNum(libnum);
				stockAdd.setInitialFlag("0");
				stockAdd.setDelFlag("0");
				stockAdd.setIsNewRecord(false);
				stockAdd.preInsert();
				stockLibDao.insert(stockAdd);
				stocklibId=stockAdd.getId();
			}
			else {
				//修改
				BusiStockLib stockMod=stocklist.get(0);
				int oldNum=stockMod.getStockNum();
				int newNum=oldNum+libnum;
				stockMod.setStockNum(newNum);
				stockMod.preUpdate();
				stockLibDao.update(stockMod);
				
				stocklibId=stockMod.getId();
				
			}
			String libinBeginGlobalId="";
			String libinEndGlobalId="";
			//更新资产主表
			for(int i=0;i<libnum;i++) {
				
				BusiAssetMain am=new BusiAssetMain();
				BeanUtil.copyProperties(am, libinBill);
				am.setCompanyId(libinBill.getCompany().getId());
				am.setStockLibId(stocklibId);
				String assetRfidTagid=SeqUtil.getNewId16Hex(SeqConstant.ASSET_RFID_TAGID, 24);
				
				am.setAssetRfidTagid(assetRfidTagid);
				am.setStatus("1");
				am.setIsNewRecord(false);
				am.preInsert();
				//String assetGlobalId=SeqUtil.getNewId(SeqConstant.ASSET_GLOBAL_ID, 8);
				String assetGlobalId=am.getId();
				am.setAssetGlobalId(assetGlobalId);
				am.setTagIssueFlag("0");//标签未发行
				assetMainDao.insert(am);
				if(i==0) {
					libinBeginGlobalId=assetGlobalId;
				}
				if(i==libnum-1) {
					libinEndGlobalId=assetGlobalId;
				}
			}
			//更新
			libinBill.setLibinBeginGlobalId(libinBeginGlobalId);
			libinBill.setLibinEndGlobalId(libinEndGlobalId);
			dao.update(libinBill);
			
		}else{
			libinBill.preUpdate();
			dao.update(libinBill);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(BusiAssetLibinBill busiAssetLibinBill) {
		super.delete(busiAssetLibinBill);
	}
	
}