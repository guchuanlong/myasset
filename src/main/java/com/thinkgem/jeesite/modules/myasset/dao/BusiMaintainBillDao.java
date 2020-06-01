/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.myasset.entity.BusiMaintainBill;

/**
 * 资产维护DAO接口
 * @author gucl
 * @version 2020-05-31
 */
@MyBatisDao
public interface BusiMaintainBillDao extends CrudDao<BusiMaintainBill> {
	
}