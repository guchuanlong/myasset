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
import com.thinkgem.jeesite.modules.myasset.entity.BusiScrapBill;
import com.thinkgem.jeesite.modules.myasset.entity.BusiScrapBillDtl;
import com.thinkgem.jeesite.modules.myasset.service.BusiScrapBillService;

/**
 * 资产报废Controller
 * @author gucl
 * @version 2020-05-31
 */
@Controller
@RequestMapping(value = "${adminPath}/myasset/busiScrapBill")
public class BusiScrapBillController extends BaseController {

	@Autowired
	private BusiScrapBillService busiScrapBillService;
	
	@ModelAttribute
	public BusiScrapBill get(@RequestParam(required=false) String id) {
		BusiScrapBill entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = busiScrapBillService.get(id);
		}
		if (entity == null){
			entity = new BusiScrapBill();
		}
		return entity;
	}
	
	@RequiresPermissions("myasset:busiScrapBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusiScrapBill busiScrapBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusiScrapBill> page = busiScrapBillService.findPage(new Page<BusiScrapBill>(request, response), busiScrapBill); 
		model.addAttribute("page", page);
		return "modules/myasset/busiScrapBillList";
	}

	@RequiresPermissions("myasset:busiScrapBill:view")
	@RequestMapping(value = "form")
	public String form(BusiScrapBill busiScrapBill, Model model) {
		model.addAttribute("busiScrapBill", busiScrapBill);
		return "modules/myasset/busiScrapBillForm";
	}

	@RequiresPermissions("myasset:busiScrapBill:edit")
	@RequestMapping(value = "save")
	public String save(BusiScrapBill busiScrapBill, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, busiScrapBill)){
			return form(busiScrapBill, model);
		}
		String paramHasChooseAsset=request.getParameter("paramHasChooseAsset");
		if(!StringUtil.isBlank(paramHasChooseAsset)) {
			List<BusiScrapBillDtl> list = JSONArray.parseArray(paramHasChooseAsset, BusiScrapBillDtl.class);
			busiScrapBill.setBusiScrapBillDtlList(list);
		}
		busiScrapBillService.save(busiScrapBill);
		addMessage(redirectAttributes, "保存资产报废成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/busiScrapBill/?repage";
	}
	
	@RequiresPermissions("myasset:busiScrapBill:edit")
	@RequestMapping(value = "delete")
	public String delete(BusiScrapBill busiScrapBill, RedirectAttributes redirectAttributes) {
		busiScrapBillService.delete(busiScrapBill);
		addMessage(redirectAttributes, "删除资产报废成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/busiScrapBill/?repage";
	}

}