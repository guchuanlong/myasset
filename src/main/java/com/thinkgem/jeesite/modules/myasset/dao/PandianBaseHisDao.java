/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.myasset.entity.PandianBaseHis;

/**
 * 盘点基础历史DAO接口
 * @author gucl
 * @version 2019-05-27
 */
@MyBatisDao
public interface PandianBaseHisDao extends CrudDao<PandianBaseHis> {
	
}