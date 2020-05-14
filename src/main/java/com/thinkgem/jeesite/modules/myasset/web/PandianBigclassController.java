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
import com.thinkgem.jeesite.modules.myasset.entity.PandianBigclass;
import com.thinkgem.jeesite.modules.myasset.service.PandianBigclassService;

/**
 * 盘点大类Controller
 * @author gucl
 * @version 2019-05-11
 */
@Controller
@RequestMapping(value = "${adminPath}/myasset/pandianBigclass")
public class PandianBigclassController extends BaseController {

	@Autowired
	private PandianBigclassService pandianBigclassService;
	
	@ModelAttribute
	public PandianBigclass get(@RequestParam(required=false) String id) {
		PandianBigclass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pandianBigclassService.get(id);
		}
		if (entity == null){
			entity = new PandianBigclass();
		}
		return entity;
	}
	
	@RequiresPermissions("myasset:pandianBigclass:view")
	@RequestMapping(value = {"list", ""})
	public String list(PandianBigclass pandianBigclass, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PandianBigclass> page = pandianBigclassService.findPage(new Page<PandianBigclass>(request, response), pandianBigclass); 
		model.addAttribute("page", page);
		return "modules/myasset/pandianBigclassList";
	}

	@RequiresPermissions("myasset:pandianBigclass:view")
	@RequestMapping(value = "form")
	public String form(PandianBigclass pandianBigclass, Model model) {
		model.addAttribute("pandianBigclass", pandianBigclass);
		return "modules/myasset/pandianBigclassForm";
	}

	@RequiresPermissions("myasset:pandianBigclass:edit")
	@RequestMapping(value = "save")
	public String save(PandianBigclass pandianBigclass, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pandianBigclass)){
			return form(pandianBigclass, model);
		}
		pandianBigclassService.save(pandianBigclass);
		addMessage(redirectAttributes, "保存盘点大类成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/pandianBigclass/?repage";
	}
	
	@RequiresPermissions("myasset:pandianBigclass:edit")
	@RequestMapping(value = "delete")
	public String delete(PandianBigclass pandianBigclass, RedirectAttributes redirectAttributes) {
		pandianBigclassService.delete(pandianBigclass);
		addMessage(redirectAttributes, "删除盘点大类成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/pandianBigclass/?repage";
	}

}