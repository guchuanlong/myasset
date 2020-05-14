/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.appvo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 盘点结果Entity
 * @author gucl
 * @version 2019-05-16
 */
public class AppPandianResultSaveRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String loginName;
	private User appUser;
	
	private List<AppPandianResult> list=new ArrayList<>();

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public List<AppPandianResult> getList() {
		return list;
	}

	public void setList(List<AppPandianResult> list) {
		this.list = list;
	}

	public User getAppUser() {
		return appUser;
	}

	public void setAppUser(User appUser) {
		this.appUser = appUser;
	}
	
	
	
	
	
}