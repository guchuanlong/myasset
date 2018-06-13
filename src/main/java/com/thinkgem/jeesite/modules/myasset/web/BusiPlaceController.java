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
import com.thinkgem.jeesite.modules.myasset.entity.BusiPlace;
import com.thinkgem.jeesite.modules.myasset.service.BusiPlaceService;

/**
 * 资产存放地点Controller
 * @author gucl
 * @version 2018-06-13
 */
@Controller
@RequestMapping(value = "${adminPath}/myasset/busiPlace")
public class BusiPlaceController extends BaseController {

	@Autowired
	private BusiPlaceService busiPlaceService;
	
	@ModelAttribute
	public BusiPlace get(@RequestParam(required=false) String id) {
		BusiPlace entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = busiPlaceService.get(id);
		}
		if (entity == null){
			entity = new BusiPlace();
		}
		return entity;
	}
	
	@RequiresPermissions("myasset:busiPlace:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusiPlace busiPlace, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusiPlace> page = busiPlaceService.findPage(new Page<BusiPlace>(request, response), busiPlace); 
		model.addAttribute("page", page);
		return "modules/myasset/busiPlaceList";
	}

	@RequiresPermissions("myasset:busiPlace:view")
	@RequestMapping(value = "form")
	public String form(BusiPlace busiPlace, Model model) {
		model.addAttribute("busiPlace", busiPlace);
		return "modules/myasset/busiPlaceForm";
	}

	@RequiresPermissions("myasset:busiPlace:edit")
	@RequestMapping(value = "save")
	public String save(BusiPlace busiPlace, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, busiPlace)){
			return form(busiPlace, model);
		}
		busiPlaceService.save(busiPlace);
		addMessage(redirectAttributes, "保存资产存放地点成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/busiPlace/?repage";
	}
	
	@RequiresPermissions("myasset:busiPlace:edit")
	@RequestMapping(value = "delete")
	public String delete(BusiPlace busiPlace, RedirectAttributes redirectAttributes) {
		busiPlaceService.delete(busiPlace);
		addMessage(redirectAttributes, "删除资产存放地点成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/busiPlace/?repage";
	}

}