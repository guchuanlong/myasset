/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.myasset.entity.BusiAssetMain;

/**
 * 资产主表DAO接口
 * @author gucl
 * @version 2020-05-30
 */
@MyBatisDao
public interface BusiAssetMainDao extends CrudDao<BusiAssetMain> {
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public int updateStatusById(BusiAssetMain entity);
	
}