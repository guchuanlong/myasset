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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myxapp.sdk.util.DateUtil;
import com.myxapp.sdk.util.StringUtil;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.myasset.constant.MyassetConstant;
import com.thinkgem.jeesite.modules.myasset.constant.MyassetConstant.AssetBigClass;
import com.thinkgem.jeesite.modules.myasset.entity.PandianResult;
import com.thinkgem.jeesite.modules.myasset.service.PandianResultService;

/**
 * 盘点结果Controller
 * @author gucl
 * @version 2019-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/myasset/pandianResult")
public class PandianResultController extends BaseController {

	@Autowired
	private PandianResultService pandianResultService;
	
	@ModelAttribute
	public PandianResult get(@RequestParam(required=false) String id) {
		PandianResult entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pandianResultService.get(id);
		}
		if (entity == null){
			entity = new PandianResult();
		}
		return entity;
	}
	
	@RequiresPermissions("myasset:pandianResult:view")
	@RequestMapping(value = {"list", ""})
	public String list(PandianResult pandianResult, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PandianResult> page = pandianResultService.findPage(new Page<PandianResult>(request, response), pandianResult); 
		model.addAttribute("page", page);
		return "modules/myasset/pandianResultList";
	}

	@RequiresPermissions("myasset:pandianResult:view")
	@RequestMapping(value = "form")
	public String form(PandianResult pandianResult, Model model) {
		model.addAttribute("pandianResult", pandianResult);
		return "modules/myasset/pandianResultForm";
	}

	@RequiresPermissions("myasset:pandianResult:edit")
	@RequestMapping(value = "save")
	public String save(PandianResult pandianResult, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pandianResult)){
			return form(pandianResult, model);
		}
		if(null!=pandianResult.getCompany()) {
			pandianResult.setCompanyName(pandianResult.getCompany().getName());
		}
		if(null!=pandianResult.getOffice()) {
			pandianResult.setPowerSupplyUnit(pandianResult.getOffice().getName());
		}
		pandianResult.setAssetRfidEpc(pandianResult.getAssetBarcode());
		
		pandianResult.setBaseDate(DateUtil.getSysDate());
		pandianResult.setResultDate(DateUtil.getSysDate());
		String barcode=pandianResult.getAssetBarcode();
		String bigClassCode="",bigClassName="";
		if(!StringUtil.isBlank(barcode)&&barcode.length()>=9) {
			bigClassCode=barcode.substring(5, 7);
			AssetBigClass bigClass=MyassetConstant.AssetBigClass.getAssetBigClass(bigClassCode);
			if(null!=bigClass) {
				bigClassName=bigClass.getDesc();
			}
		}
		pandianResult.setBigClassCode(bigClassCode);
		pandianResult.setBigClassName(bigClassName);
		pandianResult.setAssetRfidEpc(pandianResult.getAssetBarcode());
		
		pandianResultService.save(pandianResult);
		addMessage(redirectAttributes, "保存盘点结果成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/pandianResult/?repage";
	}
	
	@RequiresPermissions("myasset:pandianResult:edit")
	@RequestMapping(value = "delete")
	public String delete(PandianResult pandianResult, RedirectAttributes redirectAttributes) {
		pandianResultService.delete(pandianResult);
		addMessage(redirectAttributes, "删除盘点结果成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/pandianResult/?repage";
	}
	
	@RequiresPermissions("sys:user:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PandianResult user, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "盘点结果"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<PandianResult> page = pandianResultService.findPage(new Page<PandianResult>(request, response, -1), user);
    		new ExportExcel("盘点结果", PandianResult.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出盘点结果失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/myasset/pandianResult/?repage";
    }

}