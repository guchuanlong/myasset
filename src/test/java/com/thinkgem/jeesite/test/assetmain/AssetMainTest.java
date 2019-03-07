package com.thinkgem.jeesite.test.assetmain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.myxapp.sdk.base.vo.PageResult;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.myasset.entity.BusiAssetMain;
import com.thinkgem.jeesite.modules.myasset.service.BusiAssetMainService;
import com.thinkgem.jeesite.test.BaseTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-context.xml" })
public class AssetMainTest {
	public static final Logger log = LoggerFactory.getLogger(BaseTest.class);
	
	@Autowired
	private BusiAssetMainService busiAssetMainService;
	
	
	@Test
	public void testseq(){
		int pageNo=1;
		int pageSize=10;
		BusiAssetMain busiAssetMain=new BusiAssetMain();
		busiAssetMain.setStatus("1");
		Page<BusiAssetMain> page = busiAssetMainService.findPage(new Page<BusiAssetMain>(pageNo, pageSize), busiAssetMain);
		log.info("page="+JSON.toJSONString(page));
		/*
		 * if(page!=null&&page.getCount()>0) { PageResult<BusiAssetMain> pageRes=new
		 * PageResult<BusiAssetMain>(); pageRes.setCount(page.getCount());
		 * pageRes.setPageSize(page.getPageSize());
		 * pageRes.setPageNum(page.getPageNo()); pageRes.setResult(page.getList());
		 * 
		 * 
		 * log.info("pageRes="+JSON.toJSONString(pageRes)); }
		 */
		
	}
	
}
