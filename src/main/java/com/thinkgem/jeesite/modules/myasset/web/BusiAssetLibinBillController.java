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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.myasset.entity.BusiAssetLibinBill;
import com.thinkgem.jeesite.modules.myasset.entity.BusiAssetname;
import com.thinkgem.jeesite.modules.myasset.entity.BusiPlace;
import com.thinkgem.jeesite.modules.myasset.service.BusiAssetLibinBillService;
import com.thinkgem.jeesite.modules.myasset.service.BusiAssetnameService;
import com.thinkgem.jeesite.modules.myasset.service.BusiPlaceService;

/**
 * 资产入库Controller
 * @author gucl
 * @version 2020-05-30
 */
@Controller
@RequestMapping(value = "${adminPath}/myasset/busiAssetLibinBill")
public class BusiAssetLibinBillController extends BaseController {

	@Autowired
	private BusiAssetLibinBillService busiAssetLibinBillService;
	
	@Autowired
	private BusiAssetnameService busiAssetnameService;
	
	@Autowired
	private BusiPlaceService busiPlaceService;
	
	@ModelAttribute
	public BusiAssetLibinBill get(@RequestParam(required=false) String id) {
		BusiAssetLibinBill entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = busiAssetLibinBillService.get(id);
		}
		if (entity == null){
			entity = new BusiAssetLibinBill();
		}
		return entity;
	}
	
	@RequiresPermissions("myasset:busiAssetLibinBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusiAssetLibinBill busiAssetLibinBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusiAssetLibinBill> page = busiAssetLibinBillService.findPage(new Page<BusiAssetLibinBill>(request, response), busiAssetLibinBill); 
		model.addAttribute("page", page);
		return "modules/myasset/busiAssetLibinBillList";
	}

	@RequiresPermissions("myasset:busiAssetLibinBill:view")
	@RequestMapping(value = "form")
	public String form(BusiAssetLibinBill busiAssetLibinBill, Model model) {
		model.addAttribute("busiAssetLibinBill", busiAssetLibinBill);
		
		BusiAssetname busiAssetname=new BusiAssetname();
		List<BusiAssetname> assetnameList=busiAssetnameService.findList(busiAssetname);
		
		BusiPlace busiPlace=new BusiPlace();
		List<BusiPlace> placeList=busiPlaceService.findList(busiPlace);
		
		model.addAttribute("assetnameList", assetnameList);
		model.addAttribute("placeList", placeList);
		
		return "modules/myasset/busiAssetLibinBillForm";
	}

	@RequiresPermissions("myasset:busiAssetLibinBill:edit")
	@RequestMapping(value = "save")
	public String save(BusiAssetLibinBill busiAssetLibinBill, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, busiAssetLibinBill)){
			return form(busiAssetLibinBill, model);
		}
		busiAssetLibinBillService.save(busiAssetLibinBill);
		addMessage(redirectAttributes, "保存资产入库成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/busiAssetLibinBill/?repage";
	}
	
	@RequiresPermissions("myasset:busiAssetLibinBill:edit")
	@RequestMapping(value = "delete")
	public String delete(BusiAssetLibinBill busiAssetLibinBill, RedirectAttributes redirectAttributes) {
		busiAssetLibinBillService.delete(busiAssetLibinBill);
		addMessage(redirectAttributes, "删除资产入库成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/busiAssetLibinBill/?repage";
	}

}