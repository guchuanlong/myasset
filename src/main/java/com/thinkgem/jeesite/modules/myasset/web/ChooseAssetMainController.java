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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myxapp.sdk.base.BusinessConstants;
import com.myxapp.sdk.base.vo.BaseResponse;
import com.myxapp.sdk.util.StringUtil;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.myasset.entity.BusiAssetMain;
import com.thinkgem.jeesite.modules.myasset.service.BusiAssetMainService;

/**
 * 资产主表Controller
 * @author gucl
 * @version 2019-02-09
 */
@Controller
@RequestMapping(value = "${adminPath}/myasset/chooseBusiAssetMain")
public class ChooseAssetMainController extends BaseController {

	@Autowired
	private BusiAssetMainService busiAssetMainService;
	
	@RequestMapping(value = {"list"})
	public String list(BusiAssetMain busiAssetMain, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/myasset/chooseCanBorrowAssetList";
	}
	
	@RequestMapping(value = {"chooseAssetList"})
	@ResponseBody
	public Page<BusiAssetMain> chooseAssetList(@RequestBody BusiAssetMain busiAssetMain, HttpServletRequest request, HttpServletResponse response, Model model) {
		String pageNoStr = request.getParameter("pageNo");
        String pageSizeStr = request.getParameter("pageSize");
		int pageNo=1;
		int pageSize=10;
		if(!StringUtil.isBlank(pageNoStr)&&StringUtils.isNumeric(pageNoStr)) {
			pageNo=Integer.parseInt(pageNoStr);
		}
		if(!StringUtil.isBlank(pageSizeStr)&&StringUtils.isNumeric(pageSizeStr)) {
			pageSize=Integer.parseInt(pageSizeStr);
		}
		Page<BusiAssetMain> page = busiAssetMainService.findPage(new Page<BusiAssetMain>(pageNo, pageSize), busiAssetMain);
		
		return page;
	}


}