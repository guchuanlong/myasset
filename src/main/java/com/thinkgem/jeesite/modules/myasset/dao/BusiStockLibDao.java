/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.myasset.entity.BusiStockLib;

/**
 * 资产库存DAO接口
 * @author gucl
 * @version 2018-07-15
 */
@MyBatisDao
public interface BusiStockLibDao extends CrudDao<BusiStockLib> {
	
}