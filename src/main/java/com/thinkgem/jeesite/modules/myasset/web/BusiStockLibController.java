/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.web;

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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.myasset.entity.BusiStockLib;
import com.thinkgem.jeesite.modules.myasset.service.BusiStockLibService;

/**
 * 资产库存Controller
 * @author gucl
 * @version 2018-07-15
 */
@Controller
@RequestMapping(value = "${adminPath}/myasset/busiStockLib")
public class BusiStockLibController extends BaseController {

	@Autowired
	private BusiStockLibService busiStockLibService;
	
	@ModelAttribute
	public BusiStockLib get(@RequestParam(required=false) String id) {
		BusiStockLib entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = busiStockLibService.get(id);
		}
		if (entity == null){
			entity = new BusiStockLib();
		}
		return entity;
	}
	
	@RequiresPermissions("myasset:busiStockLib:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusiStockLib busiStockLib, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusiStockLib> page = busiStockLibService.findPage(new Page<BusiStockLib>(request, response), busiStockLib); 
		model.addAttribute("page", page);
		return "modules/myasset/busiStockLibList";
	}

	@RequiresPermissions("myasset:busiStockLib:view")
	@RequestMapping(value = "form")
	public String form(BusiStockLib busiStockLib, Model model) {
		model.addAttribute("busiStockLib", busiStockLib);
		return "modules/myasset/busiStockLibForm";
	}

	@RequiresPermissions("myasset:busiStockLib:edit")
	@RequestMapping(value = "save")
	public String save(BusiStockLib busiStockLib, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, busiStockLib)){
			return form(busiStockLib, model);
		}
		busiStockLibService.save(busiStockLib);
		addMessage(redirectAttributes, "保存资产库存成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/busiStockLib/?repage";
	}
	
	@RequiresPermissions("myasset:busiStockLib:edit")
	@RequestMapping(value = "delete")
	public String delete(BusiStockLib busiStockLib, RedirectAttributes redirectAttributes) {
		busiStockLibService.delete(busiStockLib);
		addMessage(redirectAttributes, "删除资产库存成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/busiStockLib/?repage";
	}

}