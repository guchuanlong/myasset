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
import com.thinkgem.jeesite.modules.myasset.entity.PandianBaseHis;
import com.thinkgem.jeesite.modules.myasset.service.PandianBaseHisService;

/**
 * 盘点基础历史Controller
 * @author gucl
 * @version 2019-05-27
 */
@Controller
@RequestMapping(value = "${adminPath}/myasset/pandianBaseHis")
public class PandianBaseHisController extends BaseController {

	@Autowired
	private PandianBaseHisService pandianBaseHisService;
	
	@ModelAttribute
	public PandianBaseHis get(@RequestParam(required=false) String id) {
		PandianBaseHis entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pandianBaseHisService.get(id);
		}
		if (entity == null){
			entity = new PandianBaseHis();
		}
		return entity;
	}
	
	@RequiresPermissions("myasset:pandianBaseHis:view")
	@RequestMapping(value = {"list", ""})
	public String list(PandianBaseHis pandianBaseHis, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PandianBaseHis> page = pandianBaseHisService.findPage(new Page<PandianBaseHis>(request, response), pandianBaseHis); 
		model.addAttribute("page", page);
		return "modules/myasset/pandianBaseHisList";
	}

	@RequiresPermissions("myasset:pandianBaseHis:view")
	@RequestMapping(value = "form")
	public String form(PandianBaseHis pandianBaseHis, Model model) {
		model.addAttribute("pandianBaseHis", pandianBaseHis);
		return "modules/myasset/pandianBaseHisForm";
	}

	@RequiresPermissions("myasset:pandianBaseHis:edit")
	@RequestMapping(value = "save")
	public String save(PandianBaseHis pandianBaseHis, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pandianBaseHis)){
			return form(pandianBaseHis, model);
		}
		pandianBaseHisService.save(pandianBaseHis);
		addMessage(redirectAttributes, "保存盘点基础历史成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/pandianBaseHis/?repage";
	}
	
	@RequiresPermissions("myasset:pandianBaseHis:edit")
	@RequestMapping(value = "delete")
	public String delete(PandianBaseHis pandianBaseHis, RedirectAttributes redirectAttributes) {
		pandianBaseHisService.delete(pandianBaseHis);
		addMessage(redirectAttributes, "删除盘点基础历史成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/pandianBaseHis/?repage";
	}

}