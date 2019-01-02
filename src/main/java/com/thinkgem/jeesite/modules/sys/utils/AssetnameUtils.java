/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.utils;

import org.apache.commons.lang3.StringUtils;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.myasset.dao.BusiAssetnameDao;
import com.thinkgem.jeesite.modules.myasset.entity.BusiAssetname;

/**
 * 字典工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class AssetnameUtils {
	
	private static BusiAssetnameDao dao = SpringContextHolder.getBean(BusiAssetnameDao.class);

	public static String getAssetname(String id){
		String defaultValue="";
		if (StringUtils.isNotBlank(id)){
			BusiAssetname db=dao.get(id);
			if(db!=null) {
				defaultValue=db.getName();
			}
		}
		return defaultValue;
	}
	
	
}
