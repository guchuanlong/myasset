/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.myxapp.sdk.util.DateUtil;
import com.myxapp.sdk.util.StringUtil;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.myasset.entity.BusiBorrowBillDtl;
import com.thinkgem.jeesite.modules.myasset.entity.BusiReturnBill;
import com.thinkgem.jeesite.modules.myasset.entity.BusiReturnBillDtl;
import com.thinkgem.jeesite.modules.myasset.service.BusiReturnBillService;

/**
 * 资产归还Controller
 * @author gucl
 * @version 2019-02-11
 */
@Controller
@RequestMapping(value = "${adminPath}/myasset/busiReturnBill")
public class BusiReturnBillController extends BaseController {

	@Autowired
	private BusiReturnBillService busiReturnBillService;
	
	@ModelAttribute
	public BusiReturnBill get(@RequestParam(required=false) String id) {
		BusiReturnBill entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = busiReturnBillService.get(id);
		}
		if (entity == null){
			entity = new BusiReturnBill();
		}
		return entity;
	}
	
	@RequiresPermissions("myasset:busiReturnBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusiReturnBill busiReturnBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusiReturnBill> page = busiReturnBillService.findPage(new Page<BusiReturnBill>(request, response), busiReturnBill); 
		model.addAttribute("page", page);
		return "modules/myasset/busiReturnBillList";
	}

	@RequiresPermissions("myasset:busiReturnBill:view")
	@RequestMapping(value = "form")
	public String form(BusiReturnBill busiReturnBill, Model model) {
		model.addAttribute("busiReturnBill", busiReturnBill);
		return "modules/myasset/busiReturnBillForm";
	}
	@RequiresPermissions("myasset:busiReturnBill:view")
	@RequestMapping(value = "detail")
	public String detail(BusiReturnBill busiReturnBill, Model model) {
		model.addAttribute("busiReturnBill", busiReturnBill);
		return "modules/myasset/busiReturnBillDetail";
	}

	@RequiresPermissions("myasset:busiReturnBill:edit")
	@RequestMapping(value = "save")
	public String save(BusiReturnBill busiReturnBill, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, busiReturnBill)){
			return form(busiReturnBill, model);
		}
		String paramHasChooseAsset=request.getParameter("paramHasChooseAsset");
		if(!StringUtil.isBlank(paramHasChooseAsset)) {
			List<BusiReturnBillDtl> list = JSONArray.parseArray(paramHasChooseAsset, BusiReturnBillDtl.class);
			busiReturnBill.setBusiReturnBillDtlList(list);
		}
		busiReturnBill.setReturnDate(DateUtil.getSysDate());
		busiReturnBillService.save(busiReturnBill);
		addMessage(redirectAttributes, "保存资产归还成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/busiReturnBill/?repage";
	}
	
	@RequiresPermissions("myasset:busiReturnBill:edit")
	@RequestMapping(value = "delete")
	public String delete(BusiReturnBill busiReturnBill, RedirectAttributes redirectAttributes) {
		busiReturnBillService.delete(busiReturnBill);
		addMessage(redirectAttributes, "删除资产归还成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/busiReturnBill/?repage";
	}

}