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
import com.thinkgem.jeesite.modules.myasset.entity.BusiBorrowBill;
import com.thinkgem.jeesite.modules.myasset.entity.BusiBorrowBillDtl;
import com.thinkgem.jeesite.modules.myasset.service.BusiBorrowBillService;

/**
 * 资产领用Controller
 * @author gucl
 * @version 2019-02-10
 */
@Controller
@RequestMapping(value = "${adminPath}/myasset/busiBorrowBill")
public class BusiBorrowBillController extends BaseController {

	@Autowired
	private BusiBorrowBillService busiBorrowBillService;
	
	@ModelAttribute
	public BusiBorrowBill get(@RequestParam(required=false) String id) {
		BusiBorrowBill entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = busiBorrowBillService.get(id);
		}
		if (entity == null){
			entity = new BusiBorrowBill();
		}
		return entity;
	}
	
	@RequiresPermissions("myasset:busiBorrowBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusiBorrowBill busiBorrowBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusiBorrowBill> page = busiBorrowBillService.findPage(new Page<BusiBorrowBill>(request, response), busiBorrowBill); 
		model.addAttribute("page", page);
		return "modules/myasset/busiBorrowBillList";
	}

	@RequiresPermissions("myasset:busiBorrowBill:view")
	@RequestMapping(value = "form")
	public String form(BusiBorrowBill busiBorrowBill, Model model) {
		model.addAttribute("busiBorrowBill", busiBorrowBill);
		return "modules/myasset/busiBorrowBillForm";
	}

	@RequiresPermissions("myasset:busiBorrowBill:edit")
	@RequestMapping(value = "save")
	public String save(BusiBorrowBill busiBorrowBill, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, busiBorrowBill)){
			return form(busiBorrowBill, model);
		}
		String paramHasChooseAsset=request.getParameter("paramHasChooseAsset");
		if(!StringUtil.isBlank(paramHasChooseAsset)) {
			List<BusiBorrowBillDtl> list = JSONArray.parseArray(paramHasChooseAsset, BusiBorrowBillDtl.class);
			busiBorrowBill.setBusiBorrowBillDtlList(list);
		}
		busiBorrowBillService.save(busiBorrowBill);
		addMessage(redirectAttributes, "保存资产领用成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/busiBorrowBill/?repage";
	}
	
	@RequiresPermissions("myasset:busiBorrowBill:edit")
	@RequestMapping(value = "delete")
	public String delete(BusiBorrowBill busiBorrowBill, RedirectAttributes redirectAttributes) {
		busiBorrowBillService.delete(busiBorrowBill);
		addMessage(redirectAttributes, "删除资产领用成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/busiBorrowBill/?repage";
	}

}