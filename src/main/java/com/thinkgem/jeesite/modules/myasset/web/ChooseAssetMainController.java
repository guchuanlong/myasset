/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myxapp.sdk.util.StringUtil;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.myasset.entity.BusiAssetMain;
import com.thinkgem.jeesite.modules.myasset.entity.BusiAssetname;
import com.thinkgem.jeesite.modules.myasset.entity.BusiBorrowBill;
import com.thinkgem.jeesite.modules.myasset.entity.BusiBorrowBillDtl;
import com.thinkgem.jeesite.modules.myasset.service.BusiAssetMainService;
import com.thinkgem.jeesite.modules.myasset.service.BusiBorrowBillDtlService;

/**
 * 资产主表Controller
 * @author gucl
 * @version 2019-02-09
 */
@Controller
@RequestMapping(value = "${adminPath}/myasset/chooseBusiAssetMain")
public class ChooseAssetMainController extends BaseController {

	@Autowired
	private BusiAssetMainService busiAssetMainService;
	@Autowired
	private BusiBorrowBillDtlService busiBorrowBillDtlService;
	
	@RequestMapping(value = {"/list"})
	public String list(BusiAssetMain busiAssetMain, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/myasset/chooseCanBorrowAssetList";
	}
	
	/**
	 * 查询可以领用的资产
	* @title chooseAssetList
	* @author guchuanlong<br>
	* @date 2019年3月24日<br>
	* @description:  <br>
	* @param request
	* @param response
	* @return
	 */
	@RequestMapping(value = {"/chooseAssetList"})
	@ResponseBody
	public Page<BusiAssetMain> chooseAssetList(HttpServletRequest request, HttpServletResponse response) {
		String pageNoStr = request.getParameter("pageNo");
        String pageSizeStr = request.getParameter("pageSize");
		int pageNo=1;
		int pageSize=10;
		if(!StringUtil.isBlank(pageNoStr)&&StringUtils.isNumeric(pageNoStr)) {
			pageNo=Integer.parseInt(pageNoStr);
		}
		if(!StringUtil.isBlank(pageSizeStr)&&StringUtils.isNumeric(pageSizeStr)) {
			pageSize=Integer.parseInt(pageSizeStr);
		}
		
		String status=request.getParameter("paramStatus");
		String assetNameStr=request.getParameter("paramAssetName");
		String assetGlobalId=request.getParameter("paramAssetGlobalId");
		
		
		
		BusiAssetMain param=new BusiAssetMain();
		if(!StringUtil.isBlank(assetGlobalId)) {
			param.setAssetGlobalId(assetGlobalId);
		}
		if(!StringUtil.isBlank(assetNameStr)) {
			BusiAssetname assetname=new BusiAssetname();
			assetname.setName(assetNameStr);
			param.setAssetname(assetname);
		}
		if(!StringUtil.isBlank(status)) {
			param.setStatus(status);
		}
		
		Page<BusiAssetMain> page = busiAssetMainService.findPage(new Page<BusiAssetMain>(pageNo, pageSize), param);
		
		return page;
	}
	/**
	 * 查询已领用待归还的资产
	 * @title queryBorrowAssetList
	 * @author guchuanlong<br>
	 * @date 2019年3月24日<br>
	 * @description:  <br>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = {"/queryBorrowAssetList"})
	@ResponseBody
	public Page<BusiBorrowBillDtl> queryBorrowAssetList(HttpServletRequest request, HttpServletResponse response) {
		String pageNoStr = request.getParameter("pageNo");
		String pageSizeStr = request.getParameter("pageSize");
		int pageNo=1;
		int pageSize=10;
		if(!StringUtil.isBlank(pageNoStr)&&StringUtils.isNumeric(pageNoStr)) {
			pageNo=Integer.parseInt(pageNoStr);
		}
		if(!StringUtil.isBlank(pageSizeStr)&&StringUtils.isNumeric(pageSizeStr)) {
			pageSize=Integer.parseInt(pageSizeStr);
		}
		
		String borrowBillNo=request.getParameter("paramBorrowBillNo");
		String assetNameStr=request.getParameter("paramAssetName");
		String assetGlobalId=request.getParameter("paramAssetGlobalId");
		
		
		
		BusiBorrowBillDtl param=new BusiBorrowBillDtl();
		if(!StringUtil.isBlank(assetGlobalId)) {
			param.setAssetGlobalId(assetGlobalId);
		}
		if(!StringUtil.isBlank(assetNameStr)) {
			BusiAssetname assetname=new BusiAssetname();
			assetname.setName(assetNameStr);
			param.setAssetname(assetname);
		}
		if(!StringUtil.isBlank(borrowBillNo)) {
			BusiBorrowBill borrowBillId=new BusiBorrowBill();
			borrowBillId.setBorrowBillNo(borrowBillNo);
			param.setBorrowBillId(borrowBillId);
		}
		
		Page<BusiBorrowBillDtl> page = busiBorrowBillDtlService.findPage(new Page<BusiBorrowBillDtl>(pageNo, pageSize), param);
		
		return page;
	}


}