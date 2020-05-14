/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myxapp.sdk.base.BusinessConstants;
import com.myxapp.sdk.base.vo.BaseResponse;
import com.myxapp.sdk.util.BeanUtil;
import com.myxapp.sdk.util.CollectionUtil;
import com.myxapp.sdk.util.StringUtil;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.myasset.appvo.AppLoginRequest;
import com.thinkgem.jeesite.modules.myasset.appvo.AppPandianBase;
import com.thinkgem.jeesite.modules.myasset.appvo.AppPandianResultSaveRequest;
import com.thinkgem.jeesite.modules.myasset.appvo.AppUser;
import com.thinkgem.jeesite.modules.myasset.appvo.DownloadRequest;
import com.thinkgem.jeesite.modules.myasset.entity.PandianBase;
import com.thinkgem.jeesite.modules.myasset.entity.PandianBigclass;
import com.thinkgem.jeesite.modules.myasset.service.PandianBaseService;
import com.thinkgem.jeesite.modules.myasset.service.PandianBigclassService;
import com.thinkgem.jeesite.modules.myasset.service.PandianResultService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

/**
 * pda数据接口
 * @author gucl
 * @version 2019-02-09
 */
@Controller
@RequestMapping(value = "${adminPath}/myasset/apipda")
public class ApiPdaController extends BaseController {

	@Autowired
	private PandianBaseService pandianBaseService;
	
	@Autowired
	private PandianBigclassService pandianBigclassService;
	
	@Autowired
	private PandianResultService pandianResultService;
	
	
	
	private SystemService systemService;
	
	
	/**
	 * 1.app登录校验服务
	 * @title loginCheck
	 * @author guchuanlong<br>
	 * @date 2019年6月10日<br>
	 * @description:  <br>
	 * @param loginRequest
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = {"/loadUserByLoginName"})
	@ResponseBody
	public BaseResponse<AppUser> loadUserByLoginName(@RequestBody AppLoginRequest loginRequest,  HttpServletRequest request, HttpServletResponse response){
		BaseResponse<AppUser> resp=new BaseResponse<>(true,BusinessConstants.BUSI_SUCCESS_CODE,BusinessConstants.BUSI_SUCCESS_MESSAGE);
		
		try {
			String username=loginRequest.getLoginName();
			if(StringUtil.isBlank(username)) {
				resp.setResultCode("222222");
				resp.setResultMessage("账号不能为空");
				return resp;
			}
			
			User user = getSystemService().getUserByLoginName(username);
			if (user != null) {
				if (Global.NO.equals(user.getLoginFlag())){
					resp.setResultCode("444444");
					resp.setResultMessage("该帐号禁止登录");
					return resp;
				}
				
				AppUser appUser=new AppUser();
				BeanUtil.copyProperties(appUser, user);
				//登录成功
				resp.setResult(appUser);
				return resp;
				
			} else {
				resp.setResultCode("111111");
				resp.setResultMessage("账号不存在");
				return resp;
			}
		} catch (Exception e) {
			resp.setResultCode("555555");
			resp.setResultMessage("发生异常，具体错误信息："+e.getMessage());
			return resp;
		}
	}
	/**
	 * 1.app登录校验服务
	* @title loginCheck
	* @author guchuanlong<br>
	* @date 2019年6月10日<br>
	* @description:  <br>
	* @param loginRequest
	* @param request
	* @param response
	* @return
	 */
	@RequestMapping(value = {"/loginCheck"})
	@ResponseBody
	public BaseResponse<AppUser> loginCheck(@RequestBody AppLoginRequest loginRequest,  HttpServletRequest request, HttpServletResponse response){
		BaseResponse<AppUser> resp=new BaseResponse<>(true,BusinessConstants.BUSI_SUCCESS_CODE,BusinessConstants.BUSI_SUCCESS_MESSAGE);
		
		 try {
			String username=loginRequest.getLoginName();
			 String pwdEncry=loginRequest.getLoginPwd();
			 if(StringUtil.isBlank(username)) {
				resp.setResultCode("222222");
				resp.setResultMessage("账号不能为空");
				return resp;
			}
			 if(StringUtil.isBlank(pwdEncry)) {
				resp.setResultCode("333333");
				resp.setResultMessage("密码不能为空");
				return resp;
			}
			 
			User user = getSystemService().getUserByLoginName(username);
			if (user != null) {
				if (Global.NO.equals(user.getLoginFlag())){
					resp.setResultCode("444444");
					resp.setResultMessage("该帐号禁止登录");
					return resp;
				}
				if(pwdEncry.equalsIgnoreCase(user.getPassword())) {
					AppUser appUser=new AppUser();
					BeanUtil.copyProperties(appUser, user);
					//登录成功
					resp.setResult(appUser);
					return resp;
					
				}
			} else {
				resp.setResultCode("111111");
				resp.setResultMessage("账号不存在");
				return resp;
			}
		} catch (Exception e) {
			resp.setResultCode("555555");
			resp.setResultMessage("发生异常，具体错误信息："+e.getMessage());
			return resp;
		}
		return resp;
	}
	
	/**
	 * 获取系统业务对象
	 */
	public SystemService getSystemService() {
		if (systemService == null){
			systemService = SpringContextHolder.getBean(SystemService.class);
		}
		return systemService;
	}
	
	/**
	 * 2.下载盘点基础数据
	* @title pageQueryPandianBase
	* @author guchuanlong<br>
	* @date 2019年3月24日<br>
	* @description:  <br>
	* @param request
	* @param response
	* @return
	 */
	@RequestMapping(value = {"/pageQueryPandianBase"},method=RequestMethod.POST)
	@ResponseBody
	public BaseResponse<Page<AppPandianBase>> pageQueryPandianBase(@RequestBody DownloadRequest downloadReq, HttpServletRequest request, HttpServletResponse response) {
		BaseResponse<Page<AppPandianBase>> resp=new BaseResponse<>(true,BusinessConstants.BUSI_SUCCESS_CODE,BusinessConstants.BUSI_SUCCESS_MESSAGE);
		try {
			if(StringUtil.isBlank(downloadReq.getLoginName())) {
				resp.setResultCode("222222");
				resp.setResultMessage("账号不能为空");
				return resp;
			}
			int pageNo=downloadReq.getPageNo();
			int pageSize=downloadReq.getPageSize();
			User user=getSystemService().getUserByLoginName(downloadReq.getLoginName());
			
			
			PandianBase param=new PandianBase();
			BeanUtil.copyProperties(param, downloadReq);
			param.setCurrentUser(user);
			
			Page<PandianBase> page = pandianBaseService.findPage(new Page<PandianBase>(pageNo, pageSize), param);
			Page<AppPandianBase> result=new Page<>();
			BeanUtil.copyProperties(result, page,"list");
			List<AppPandianBase> resList=new ArrayList<AppPandianBase>();
			if(!CollectionUtil.isEmpty(page.getList())) {
				for(PandianBase pb:page.getList()) {
					AppPandianBase appvo=new AppPandianBase();
					BeanUtil.copyProperties(appvo, pb);
					resList.add(appvo);
				}
				result.setList(resList);
			}
			
			resp.setResult(result);
			
		} catch (Exception e) {
			resp.setResultCode("555555");
			resp.setResultMessage("发生异常，具体错误信息："+e.getMessage());
			return resp;
		}
		
		return resp;
	}
	/**
	 * 查询盘点大类
	 * @title queryAllBigClass
	 * @author guchuanlong<br>
	 * @date 2019年5月12日<br>
	 * @description:  <br>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = {"/queryAllBigClass"})
	@ResponseBody
	public BaseResponse<List<PandianBigclass>> queryAllBigClass(HttpServletRequest request, HttpServletResponse response) {
		BaseResponse<List<PandianBigclass>> resp=new BaseResponse<>(true,BusinessConstants.BUSI_SUCCESS_CODE,BusinessConstants.BUSI_SUCCESS_MESSAGE);
		try {
			
			List<PandianBigclass> resList=pandianBigclassService.findList(new PandianBigclass());
			resp.setResult(resList);
			
		} catch (Exception e) {
			resp.setResultCode("555555");
			resp.setResultMessage("发生异常，具体错误信息："+e.getMessage());
			return resp;
		}
		
		return resp;
		
		
	}
	
	@RequestMapping(value = {"/uploadPandianResult"},method=RequestMethod.POST)
	@ResponseBody
	public BaseResponse<String> uploadPandianResult(@RequestBody AppPandianResultSaveRequest appResult, HttpServletRequest request, HttpServletResponse response) {
		BaseResponse<String> resp=new BaseResponse<>(true,BusinessConstants.BUSI_SUCCESS_CODE,BusinessConstants.BUSI_SUCCESS_MESSAGE);
		try {
			User user=getSystemService().getUserByLoginName(appResult.getLoginName());
			appResult.setAppUser(user);
			pandianResultService.uploadAppResult(appResult);
			
			resp.setResult("OK");
			
		} catch (Exception e) {
			resp.setResultCode("555555");
			resp.setResultMessage("发生异常，具体错误信息："+e.getMessage());
			return resp;
		}
		
		return resp;
		
	}//end function
	
	
	
	


}