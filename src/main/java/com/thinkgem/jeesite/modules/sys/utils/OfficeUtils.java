/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.utils;

import org.apache.commons.lang3.StringUtils;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.myasset.dao.BusiPlaceDao;
import com.thinkgem.jeesite.modules.myasset.entity.BusiPlace;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 字典工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class OfficeUtils {
	
	private static OfficeDao dao = SpringContextHolder.getBean(OfficeDao.class);

	public static String getOfficename(String id){
		String defaultValue="";
		if (StringUtils.isNotBlank(id)){
			Office db=dao.get(id);
			if(db!=null) {
				defaultValue=db.getName();
			}
		}
		return defaultValue;
	}
	
	
}
