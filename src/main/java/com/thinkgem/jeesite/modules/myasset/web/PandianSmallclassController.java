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
import com.thinkgem.jeesite.modules.myasset.entity.PandianSmallclass;
import com.thinkgem.jeesite.modules.myasset.service.PandianSmallclassService;

/**
 * 盘点小类Controller
 * @author gucl
 * @version 2019-05-11
 */
@Controller
@RequestMapping(value = "${adminPath}/myasset/pandianSmallclass")
public class PandianSmallclassController extends BaseController {

	@Autowired
	private PandianSmallclassService pandianSmallclassService;
	
	@ModelAttribute
	public PandianSmallclass get(@RequestParam(required=false) String id) {
		PandianSmallclass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pandianSmallclassService.get(id);
		}
		if (entity == null){
			entity = new PandianSmallclass();
		}
		return entity;
	}
	
	@RequiresPermissions("myasset:pandianSmallclass:view")
	@RequestMapping(value = {"list", ""})
	public String list(PandianSmallclass pandianSmallclass, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PandianSmallclass> page = pandianSmallclassService.findPage(new Page<PandianSmallclass>(request, response), pandianSmallclass); 
		model.addAttribute("page", page);
		return "modules/myasset/pandianSmallclassList";
	}

	@RequiresPermissions("myasset:pandianSmallclass:view")
	@RequestMapping(value = "form")
	public String form(PandianSmallclass pandianSmallclass, Model model) {
		model.addAttribute("pandianSmallclass", pandianSmallclass);
		return "modules/myasset/pandianSmallclassForm";
	}

	@RequiresPermissions("myasset:pandianSmallclass:edit")
	@RequestMapping(value = "save")
	public String save(PandianSmallclass pandianSmallclass, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pandianSmallclass)){
			return form(pandianSmallclass, model);
		}
		pandianSmallclassService.save(pandianSmallclass);
		addMessage(redirectAttributes, "保存盘点小类成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/pandianSmallclass/?repage";
	}
	
	@RequiresPermissions("myasset:pandianSmallclass:edit")
	@RequestMapping(value = "delete")
	public String delete(PandianSmallclass pandianSmallclass, RedirectAttributes redirectAttributes) {
		pandianSmallclassService.delete(pandianSmallclass);
		addMessage(redirectAttributes, "删除盘点小类成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/pandianSmallclass/?repage";
	}

}