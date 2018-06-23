/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.myasset.entity.BusiCategory;
import com.thinkgem.jeesite.modules.myasset.service.BusiCategoryService;

/**
 * 资产分类Controller
 * @author gucl
 * @version 2018-06-23
 */
@Controller
@RequestMapping(value = "${adminPath}/myasset/busiCategory")
public class BusiCategoryController extends BaseController {

	@Autowired
	private BusiCategoryService busiCategoryService;
	
	@ModelAttribute
	public BusiCategory get(@RequestParam(required=false) String id) {
		BusiCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = busiCategoryService.get(id);
		}
		if (entity == null){
			entity = new BusiCategory();
		}
		return entity;
	}
	
	@RequiresPermissions("myasset:busiCategory:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusiCategory busiCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<BusiCategory> list = busiCategoryService.findList(busiCategory); 
		model.addAttribute("list", list);
		return "modules/myasset/busiCategoryList";
	}

	@RequiresPermissions("myasset:busiCategory:view")
	@RequestMapping(value = "form")
	public String form(BusiCategory busiCategory, Model model) {
		if (busiCategory.getParent()!=null && StringUtils.isNotBlank(busiCategory.getParent().getId())){
			busiCategory.setParent(busiCategoryService.get(busiCategory.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(busiCategory.getId())){
				BusiCategory busiCategoryChild = new BusiCategory();
				busiCategoryChild.setParent(new BusiCategory(busiCategory.getParent().getId()));
				List<BusiCategory> list = busiCategoryService.findList(busiCategory); 
				if (list.size() > 0){
					busiCategory.setSort(list.get(list.size()-1).getSort());
					if (busiCategory.getSort() != null){
						busiCategory.setSort(busiCategory.getSort() + 30);
					}
				}
			}
		}
		if (busiCategory.getSort() == null){
			busiCategory.setSort(30);
		}
		model.addAttribute("busiCategory", busiCategory);
		return "modules/myasset/busiCategoryForm";
	}

	@RequiresPermissions("myasset:busiCategory:edit")
	@RequestMapping(value = "save")
	public String save(BusiCategory busiCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, busiCategory)){
			return form(busiCategory, model);
		}
		if (busiCategory.getSort() == null){
			busiCategory.setSort(30);
		}
		busiCategoryService.save(busiCategory);
		addMessage(redirectAttributes, "保存资产分类成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/busiCategory/?repage";
	}
	
	@RequiresPermissions("myasset:busiCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(BusiCategory busiCategory, RedirectAttributes redirectAttributes) {
		busiCategoryService.delete(busiCategory);
		addMessage(redirectAttributes, "删除资产分类成功");
		return "redirect:"+Global.getAdminPath()+"/myasset/busiCategory/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<BusiCategory> list = busiCategoryService.findList(new BusiCategory());
		for (int i=0; i<list.size(); i++){
			BusiCategory e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}