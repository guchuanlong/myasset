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
import com.thinkgem.jeesite.modules.myasset.entity.PandianResultHis;
import com.thinkgem.jeesite.modules.myasset.service.PandianResultHisService;

/**
 * 盘点结果历史Controller
 * @author gucl
 * @version 2019-05-27
 */
@Controller
@RequestMapping(value = "${adminPath}/myasset/pandianResultHis")
public class PandianResultHisController extends BaseController {

	@Autowired
	private PandianResultHisService pandianResultHisService;
	
	@ModelAttribute
	public PandianResultHis get(@RequestParam(required=false) String id) {
		PandianResultHis entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pandianResultHisService.get(id);
		}
		if (entity == null){
			entity = new PandianResultHis();
		}
		return entity;
	}
	
	@RequiresPermissions("myasset:pandianResultHis:view")
	@RequestMapping(value = {"list", ""})
	public String list(PandianResultHis pandianResultHis, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PandianResultHis> page = pandianResultHisService.findPage(new Page<PandianResultHis>(request, response), pandianResultHis); 
		model.addAttribute("page", page);
		return "modules/myasset/pandianResultHisList";
	}

	@RequiresPermissions("myasset:pandianResultHis:view")
	@RequestMapping(value = "form")
	public String form(PandianResultHis pandianResultHis, Model model) {
		model.addAttribute("pandianResultHis", pandianResultHis);
		return "modules/myasset/pandianResultHisForm";
	}

	@RequiresPermissions("myasset:pandianResultHis:edit")
	@RequestMapping(value = "save")
	public String save(PandianResultHis pandianResultHis, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pandianResultHis)){
			return form(pandianResultHis, model);
		}
		pandianResultHisService.save(pandianResultHis);
		addMessage(redirectAttributes, "保存盘点结果历史成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/pandianResultHis/?repage";
	}
	
	@RequiresPermissions("myasset:pandianResultHis:edit")
	@RequestMapping(value = "delete")
	public String delete(PandianResultHis pandianResultHis, RedirectAttributes redirectAttributes) {
		pandianResultHisService.delete(pandianResultHis);
		addMessage(redirectAttributes, "删除盘点结果历史成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/pandianResultHis/?repage";
	}

}