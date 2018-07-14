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
import com.thinkgem.jeesite.modules.myasset.entity.BusiAssetMain;
import com.thinkgem.jeesite.modules.myasset.service.BusiAssetMainService;

/**
 * 资产主表Controller
 * @author gucl
 * @version 2018-07-15
 */
@Controller
@RequestMapping(value = "${adminPath}/myasset/busiAssetMain")
public class BusiAssetMainController extends BaseController {

	@Autowired
	private BusiAssetMainService busiAssetMainService;
	
	@ModelAttribute
	public BusiAssetMain get(@RequestParam(required=false) String id) {
		BusiAssetMain entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = busiAssetMainService.get(id);
		}
		if (entity == null){
			entity = new BusiAssetMain();
		}
		return entity;
	}
	
	@RequiresPermissions("myasset:busiAssetMain:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusiAssetMain busiAssetMain, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusiAssetMain> page = busiAssetMainService.findPage(new Page<BusiAssetMain>(request, response), busiAssetMain); 
		model.addAttribute("page", page);
		return "modules/myasset/busiAssetMainList";
	}

	@RequiresPermissions("myasset:busiAssetMain:view")
	@RequestMapping(value = "form")
	public String form(BusiAssetMain busiAssetMain, Model model) {
		model.addAttribute("busiAssetMain", busiAssetMain);
		return "modules/myasset/busiAssetMainForm";
	}

	@RequiresPermissions("myasset:busiAssetMain:edit")
	@RequestMapping(value = "save")
	public String save(BusiAssetMain busiAssetMain, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, busiAssetMain)){
			return form(busiAssetMain, model);
		}
		busiAssetMainService.save(busiAssetMain);
		addMessage(redirectAttributes, "保存资产主表成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/busiAssetMain/?repage";
	}
	
	@RequiresPermissions("myasset:busiAssetMain:edit")
	@RequestMapping(value = "delete")
	public String delete(BusiAssetMain busiAssetMain, RedirectAttributes redirectAttributes) {
		busiAssetMainService.delete(busiAssetMain);
		addMessage(redirectAttributes, "删除资产主表成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/busiAssetMain/?repage";
	}

}