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
import com.myxapp.sdk.util.StringUtil;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.myasset.entity.BusiBorrowBillDtl;
import com.thinkgem.jeesite.modules.myasset.entity.BusiMaintainBill;
import com.thinkgem.jeesite.modules.myasset.entity.BusiMaintainBillDtl;
import com.thinkgem.jeesite.modules.myasset.service.BusiMaintainBillService;

/**
 * 资产维护Controller
 * @author gucl
 * @version 2020-05-31
 */
@Controller
@RequestMapping(value = "${adminPath}/myasset/busiMaintainBill")
public class BusiMaintainBillController extends BaseController {

	@Autowired
	private BusiMaintainBillService busiMaintainBillService;
	
	@ModelAttribute
	public BusiMaintainBill get(@RequestParam(required=false) String id) {
		BusiMaintainBill entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = busiMaintainBillService.get(id);
		}
		if (entity == null){
			entity = new BusiMaintainBill();
		}
		return entity;
	}
	
	@RequiresPermissions("myasset:busiMaintainBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusiMaintainBill busiMaintainBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusiMaintainBill> page = busiMaintainBillService.findPage(new Page<BusiMaintainBill>(request, response), busiMaintainBill); 
		model.addAttribute("page", page);
		return "modules/myasset/busiMaintainBillList";
	}

	@RequiresPermissions("myasset:busiMaintainBill:view")
	@RequestMapping(value = "form")
	public String form(BusiMaintainBill busiMaintainBill, Model model) {
		model.addAttribute("busiMaintainBill", busiMaintainBill);
		return "modules/myasset/busiMaintainBillForm";
	}

	@RequiresPermissions("myasset:busiMaintainBill:edit")
	@RequestMapping(value = "save")
	public String save(BusiMaintainBill busiMaintainBill, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, busiMaintainBill)){
			return form(busiMaintainBill, model);
		}
		String paramHasChooseAsset=request.getParameter("paramHasChooseAsset");
		if(!StringUtil.isBlank(paramHasChooseAsset)) {
			List<BusiMaintainBillDtl> list = JSONArray.parseArray(paramHasChooseAsset, BusiMaintainBillDtl.class);
			busiMaintainBill.setBusiMaintainBillDtlList(list);
		}
		busiMaintainBillService.save(busiMaintainBill);
		addMessage(redirectAttributes, "保存资产维护成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/busiMaintainBill/?repage";
	}
	
	@RequiresPermissions("myasset:busiMaintainBill:edit")
	@RequestMapping(value = "delete")
	public String delete(BusiMaintainBill busiMaintainBill, RedirectAttributes redirectAttributes) {
		busiMaintainBillService.delete(busiMaintainBill);
		addMessage(redirectAttributes, "删除资产维护成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/busiMaintainBill/?repage";
	}

}