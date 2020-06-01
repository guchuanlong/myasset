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
import com.thinkgem.jeesite.modules.myasset.entity.BusiTransferBill;
import com.thinkgem.jeesite.modules.myasset.entity.BusiTransferBillDtl;
import com.thinkgem.jeesite.modules.myasset.service.BusiTransferBillService;

/**
 * 资产转移Controller
 * @author gucl
 * @version 2020-05-31
 */
@Controller
@RequestMapping(value = "${adminPath}/myasset/busiTransferBill")
public class BusiTransferBillController extends BaseController {

	@Autowired
	private BusiTransferBillService busiTransferBillService;
	
	@ModelAttribute
	public BusiTransferBill get(@RequestParam(required=false) String id) {
		BusiTransferBill entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = busiTransferBillService.get(id);
		}
		if (entity == null){
			entity = new BusiTransferBill();
		}
		return entity;
	}
	
	@RequiresPermissions("myasset:busiTransferBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusiTransferBill busiTransferBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusiTransferBill> page = busiTransferBillService.findPage(new Page<BusiTransferBill>(request, response), busiTransferBill); 
		model.addAttribute("page", page);
		return "modules/myasset/busiTransferBillList";
	}

	@RequiresPermissions("myasset:busiTransferBill:view")
	@RequestMapping(value = "form")
	public String form(BusiTransferBill busiTransferBill, Model model) {
		model.addAttribute("busiTransferBill", busiTransferBill);
		return "modules/myasset/busiTransferBillForm";
	}

	@RequiresPermissions("myasset:busiTransferBill:edit")
	@RequestMapping(value = "save")
	public String save(BusiTransferBill busiTransferBill, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, busiTransferBill)){
			return form(busiTransferBill, model);
		}
		String paramHasChooseAsset=request.getParameter("paramHasChooseAsset");
		if(!StringUtil.isBlank(paramHasChooseAsset)) {
			List<BusiTransferBillDtl> list = JSONArray.parseArray(paramHasChooseAsset, BusiTransferBillDtl.class);
			busiTransferBill.setBusiTransferBillDtlList(list);
		}
		busiTransferBillService.save(busiTransferBill);
		addMessage(redirectAttributes, "保存资产转移成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/busiTransferBill/?repage";
	}
	
	@RequiresPermissions("myasset:busiTransferBill:edit")
	@RequestMapping(value = "delete")
	public String delete(BusiTransferBill busiTransferBill, RedirectAttributes redirectAttributes) {
		busiTransferBillService.delete(busiTransferBill);
		addMessage(redirectAttributes, "删除资产转移成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/busiTransferBill/?repage";
	}

}