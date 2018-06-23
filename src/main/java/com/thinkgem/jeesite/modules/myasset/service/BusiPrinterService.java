/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.myasset.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.myasset.entity.BusiPrinter;
import com.thinkgem.jeesite.modules.myasset.dao.BusiPrinterDao;

/**
 * 打印机Service
 * @author gucl
 * @version 2018-06-23
 */
@Service
@Transactional(readOnly = true)
public class BusiPrinterService extends CrudService<BusiPrinterDao, BusiPrinter> {

	public BusiPrinter get(String id) {
		return super.get(id);
	}
	
	public List<BusiPrinter> findList(BusiPrinter busiPrinter) {
		return super.findList(busiPrinter);
	}
	
	public Page<BusiPrinter> findPage(Page<BusiPrinter> page, BusiPrinter busiPrinter) {
		return super.findPage(page, busiPrinter);
	}
	
	@Transactional(readOnly = false)
	public void save(BusiPrinter busiPrinter) {
		super.save(busiPrinter);
	}
	
	@Transactional(readOnly = false)
	public void delete(BusiPrinter busiPrinter) {
		super.delete(busiPrinter);
	}
	
}