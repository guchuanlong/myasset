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
import com.thinkgem.jeesite.modules.myasset.entity.BusiPrinter;
import com.thinkgem.jeesite.modules.myasset.service.BusiPrinterService;

/**
 * 打印机Controller
 * @author gucl
 * @version 2018-06-13
 */
@Controller
@RequestMapping(value = "${adminPath}/myasset/busiPrinter")
public class BusiPrinterController extends BaseController {

	@Autowired
	private BusiPrinterService busiPrinterService;
	
	@ModelAttribute
	public BusiPrinter get(@RequestParam(required=false) String id) {
		BusiPrinter entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = busiPrinterService.get(id);
		}
		if (entity == null){
			entity = new BusiPrinter();
		}
		return entity;
	}
	
	@RequiresPermissions("myasset:busiPrinter:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusiPrinter busiPrinter, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusiPrinter> page = busiPrinterService.findPage(new Page<BusiPrinter>(request, response), busiPrinter); 
		model.addAttribute("page", page);
		return "modules/myasset/busiPrinterList";
	}

	@RequiresPermissions("myasset:busiPrinter:view")
	@RequestMapping(value = "form")
	public String form(BusiPrinter busiPrinter, Model model) {
		model.addAttribute("busiPrinter", busiPrinter);
		return "modules/myasset/busiPrinterForm";
	}

	@RequiresPermissions("myasset:busiPrinter:edit")
	@RequestMapping(value = "save")
	public String save(BusiPrinter busiPrinter, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, busiPrinter)){
			return form(busiPrinter, model);
		}
		busiPrinterService.save(busiPrinter);
		addMessage(redirectAttributes, "保存打印机成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/busiPrinter/?repage";
	}
	
	@RequiresPermissions("myasset:busiPrinter:edit")
	@RequestMapping(value = "delete")
	public String delete(BusiPrinter busiPrinter, RedirectAttributes redirectAttributes) {
		busiPrinterService.delete(busiPrinter);
		addMessage(redirectAttributes, "删除打印机成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/busiPrinter/?repage";
	}

}