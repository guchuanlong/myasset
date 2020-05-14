/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.myasset.entity.PandianBase;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 盘点基础表DAO接口
 * @author gucl
 * @version 2019-05-16
 */
@MyBatisDao
public interface PandianBaseDao extends CrudDao<PandianBase> {
	/**
	 * 根据供电单位插入历史数据
	 * @param entity
	 * @return
	 */
	public int insert2hisByPowerUnit(User user);
	
	/**
	 * 删除对应供电单位的数据
	 * @param entity
	 * @return
	 */
	public int deleteByPowerUnit(User user);
}