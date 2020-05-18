/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.myxapp.sdk.base.BusinessException;
import com.myxapp.sdk.base.SystemException;
import com.myxapp.sdk.util.DateUtil;
import com.myxapp.sdk.util.StringUtil;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.myasset.constant.MyassetConstant;
import com.thinkgem.jeesite.modules.myasset.constant.MyassetConstant.AssetBigClass;
import com.thinkgem.jeesite.modules.myasset.entity.PandianBase;
import com.thinkgem.jeesite.modules.myasset.service.PandianBaseService;
import com.thinkgem.jeesite.modules.myasset.util.PandianSeqUtil;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 盘点基础表Controller
 * @author gucl
 * @version 2019-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/myasset/pandianBase")
public class PandianBaseController extends BaseController {

	@Autowired
	private PandianBaseService pandianBaseService;
	
	@ModelAttribute
	public PandianBase get(@RequestParam(required=false) String id) {
		PandianBase entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pandianBaseService.get(id);
		}
		if (entity == null){
			entity = new PandianBase();
		}
		return entity;
	}
	
	@RequiresPermissions("myasset:pandianBase:view")
	@RequestMapping(value = {"list", ""})
	public String list(PandianBase pandianBase, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PandianBase> page = pandianBaseService.findPage(new Page<PandianBase>(request, response), pandianBase); 
		model.addAttribute("page", page);
		return "modules/myasset/pandianBaseList";
	}
	@ResponseBody
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "checkLoginName")
	public String checkAssetBarcode(String oldLoginName, String loginName) {
//		if (loginName !=null && loginName.equals(oldLoginName)) {
//			return "true";
//		} else if (loginName !=null && systemService.getUserByLoginName(loginName) == null) {
//			return "true";
//		}
//		return "false";
		return "true";
	}
	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	//@RequiresPermissions("myasset:pandianBase:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/myasset/pandianBase/pandianBaseList?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<PandianBase> list = ei.getDataList(PandianBase.class);
			//1.1将原有基础表的数据挪到历史记录表
			User user=UserUtils.getUser();
			pandianBaseService.move2hisByPowerUnit(user);
			//2.导入数据到基础表
			String batchUuid=PandianSeqUtil.getPandianBaseBatchUuid();
			for (PandianBase pandianBase : list){
				try{
					if ("true".equals(checkAssetBarcode("", pandianBase.getAssetBarcode()))){
						
						
						
						String barcode=pandianBase.getAssetBarcode();
						String bigClassCode="",bigClassName="";
						if(!StringUtil.isBlank(barcode)&&barcode.length()>=9) {
							bigClassCode=barcode.substring(5, 7);
							AssetBigClass bigClass=MyassetConstant.AssetBigClass.getAssetBigClass(bigClassCode);
							if(null!=bigClass) {
								bigClassName=bigClass.getDesc();
							}
						}
						pandianBase.setBigClassCode(bigClassCode);
						pandianBase.setBigClassName(bigClassName);
						pandianBase.setAssetRfidEpc(pandianBase.getAssetBarcode());
						pandianBase.setBatchUuid(batchUuid);
						pandianBase.setBaseDate(DateUtil.getSysDate());
						
						if(null!=pandianBase.getCompany()) {
							pandianBase.setCompanyName(pandianBase.getCompany().getName());
							pandianBase.setCityName(pandianBase.getCompany().getArea().getName());
						}
						else {
							throw new BusinessException("999999","找不到公司名称");
						}
						if(null!=pandianBase.getOffice()) {
							pandianBase.setPowerSupplyUnit(pandianBase.getOffice().getName());
						}
						else {
							throw new BusinessException("999999","找不到供电单位名称");
						}
						
//						//校验公司id和供电单位id是否匹配
//						if(!pandianBase.getCompany().getId().equalsIgnoreCase(user.getCompany().getId())) {
//							throw new BusinessException("999999","公司名称和当前用户归属的公司不匹配");
//						}
//						//校验公司id和供电单位id是否匹配
//						if(!pandianBase.getOffice().getId().equalsIgnoreCase(user.getOffice().getId())) {
//							throw new BusinessException("999999","供电单位名称和当前用户归属的供电单位不匹配");
//						}
							
							
						BeanValidators.validateWithException(validator, pandianBase);
						pandianBaseService.save(pandianBase);
						successNum++;
					}else{
						failureMsg.append("<br/>资产条码 "+pandianBase.getAssetBarcode()+" 已存在; ");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>资产条码 "+pandianBase.getAssetBarcode()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}
				catch (BusinessException ex) {
					failureMsg.append("<br/>资产条码 "+pandianBase.getAssetBarcode()+" 导入失败："+ex.getMessage());
				}
				catch (SystemException ex) {
					failureMsg.append("<br/>资产条码 "+pandianBase.getAssetBarcode()+" 导入失败："+ex.getMessage());
				}catch (Exception ex) {
					failureMsg.append("<br/>资产条码 "+pandianBase.getAssetBarcode()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条资产，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条资产"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入资产失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/myasset/pandianBase?repage";
    }
	
	/**
	 * 下载导入盘点基础数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	//@RequiresPermissions("myasset:pandianBase:edit")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "盘点基础数据导入模板.xlsx";
    		List<PandianBase> list = Lists.newArrayList(); 
    		//list.add(UserUtils.getUser());
    		PandianBase demo=new PandianBase();
    		demo.setCityName("抚州");
    		demo.setCompanyName("山东省总公司");
    		demo.setPowerSupplyUnit("济南市分公司");
    		demo.setConnectMethod("三相三线");
    		demo.setAssetBarcode("20300130002444425");
    		Office company=new Office();
    		Office powerUnit=new Office();
    		company.setId("22");
    		company.setName("抚州");
    		powerUnit.setId("0100");
    		powerUnit.setName("抚州供电分公司供电区域");
    		demo.setCompany(company);
    		demo.setOffice(powerUnit);
    		list.add(demo);
    		new ExportExcel("盘点基础数据", PandianBase.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/myasset/pandianBase?repage";
    }

	@RequiresPermissions("myasset:pandianBase:view")
	@RequestMapping(value = "form")
	public String form(PandianBase pandianBase, Model model) {
		model.addAttribute("pandianBase", pandianBase);
		return "modules/myasset/pandianBaseForm";
	}

	@RequiresPermissions("myasset:pandianBase:edit")
	@RequestMapping(value = "save")
	public String save(PandianBase pandianBase, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pandianBase)){
			return form(pandianBase, model);
		}
		if(null!=pandianBase.getCompany()) {
			pandianBase.setCompanyName(pandianBase.getCompany().getName());
		}
		if(null!=pandianBase.getOffice()) {
			pandianBase.setPowerSupplyUnit(pandianBase.getOffice().getName());
		}
		pandianBase.setAssetRfidEpc(pandianBase.getAssetBarcode());
		
		String batchUuid=PandianSeqUtil.getPandianBaseBatchUuid();
		pandianBase.setBatchUuid(batchUuid);
		pandianBase.setBaseDate(DateUtil.getSysDate());
		String barcode=pandianBase.getAssetBarcode();
		String bigClassCode="",bigClassName="";
		if(!StringUtil.isBlank(barcode)&&barcode.length()>=9) {
			bigClassCode=barcode.substring(5, 7);
			AssetBigClass bigClass=MyassetConstant.AssetBigClass.getAssetBigClass(bigClassCode);
			if(null!=bigClass) {
				bigClassName=bigClass.getDesc();
			}
		}
		pandianBase.setBigClassCode(bigClassCode);
		pandianBase.setBigClassName(bigClassName);
		
		pandianBaseService.save(pandianBase);
		addMessage(redirectAttributes, "保存盘点基础表成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/pandianBase/?repage";
	}
	
	@RequiresPermissions("myasset:pandianBase:edit")
	@RequestMapping(value = "delete")
	public String delete(PandianBase pandianBase, RedirectAttributes redirectAttributes) {
		pandianBaseService.delete(pandianBase);
		addMessage(redirectAttributes, "删除盘点基础表成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/pandianBase/?repage";
	}

}