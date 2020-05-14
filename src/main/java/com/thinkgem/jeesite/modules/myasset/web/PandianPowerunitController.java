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
import com.thinkgem.jeesite.modules.myasset.entity.PandianPowerunit;
import com.thinkgem.jeesite.modules.myasset.service.PandianPowerunitService;

/**
 * 供电单位Controller
 * @author gucl
 * @version 2019-05-11
 */
@Controller
@RequestMapping(value = "${adminPath}/myasset/pandianPowerunit")
public class PandianPowerunitController extends BaseController {

	@Autowired
	private PandianPowerunitService pandianPowerunitService;
	
	@ModelAttribute
	public PandianPowerunit get(@RequestParam(required=false) String id) {
		PandianPowerunit entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pandianPowerunitService.get(id);
		}
		if (entity == null){
			entity = new PandianPowerunit();
		}
		return entity;
	}
	
	@RequiresPermissions("myasset:pandianPowerunit:view")
	@RequestMapping(value = {"list", ""})
	public String list(PandianPowerunit pandianPowerunit, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PandianPowerunit> page = pandianPowerunitService.findPage(new Page<PandianPowerunit>(request, response), pandianPowerunit); 
		model.addAttribute("page", page);
		return "modules/myasset/pandianPowerunitList";
	}

	@RequiresPermissions("myasset:pandianPowerunit:view")
	@RequestMapping(value = "form")
	public String form(PandianPowerunit pandianPowerunit, Model model) {
		model.addAttribute("pandianPowerunit", pandianPowerunit);
		return "modules/myasset/pandianPowerunitForm";
	}

	@RequiresPermissions("myasset:pandianPowerunit:edit")
	@RequestMapping(value = "save")
	public String save(PandianPowerunit pandianPowerunit, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pandianPowerunit)){
			return form(pandianPowerunit, model);
		}
		pandianPowerunitService.save(pandianPowerunit);
		addMessage(redirectAttributes, "保存供电单位成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/pandianPowerunit/?repage";
	}
	
	@RequiresPermissions("myasset:pandianPowerunit:edit")
	@RequestMapping(value = "delete")
	public String delete(PandianPowerunit pandianPowerunit, RedirectAttributes redirectAttributes) {
		pandianPowerunitService.delete(pandianPowerunit);
		addMessage(redirectAttributes, "删除供电单位成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/pandianPowerunit/?repage";
	}

}