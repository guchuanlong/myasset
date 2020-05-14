/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.myasset.appvo.AppPandianResult;
import com.thinkgem.jeesite.modules.myasset.entity.PandianResult;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 盘点结果DAO接口
 * @author gucl
 * @version 2019-05-16
 */
@MyBatisDao
public interface PandianResultDao extends CrudDao<PandianResult> {
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
	
	
	
	/**
	 * app上传-根据供电单位统计盘点结果表中的条数
	 * @param entity
	 * @return
	 */
	public int countAppResultByPowerUnit(User user);
	/**
	 * app上传-根据供电单位统计删除盘点结果
	 * @param entity
	 * @return
	 */
	public int deleteAppResultByPowerUnit(User user);
	/**
	 * app上传-根据供电单位插入基础数据
	 * @param entity
	 * @return
	 */
	public int insertAppResultByPowerUnit(User user);
	
	/**
	 * 删除对应供电单位的数据
	 * @param entity
	 * @return
	 */
	public int batchUpdateAppResult(List<AppPandianResult> data);
}