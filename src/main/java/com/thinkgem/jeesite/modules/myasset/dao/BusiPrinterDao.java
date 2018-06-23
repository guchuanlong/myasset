/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.myasset.entity.BusiPrinter;

/**
 * 打印机DAO接口
 * @author gucl
 * @version 2018-06-23
 */
@MyBatisDao
public interface BusiPrinterDao extends CrudDao<BusiPrinter> {
	
}