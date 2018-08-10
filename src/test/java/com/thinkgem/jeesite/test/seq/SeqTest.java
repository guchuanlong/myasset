package com.thinkgem.jeesite.test.seq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myxapp.sdk.sequence.util.SeqUtil;
import com.thinkgem.jeesite.test.BaseTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/spring-context.xml" })
public class SeqTest {
	public static final Logger log = LoggerFactory.getLogger(BaseTest.class);
	@Test
	public void testseq(){
		log.info("seq:aaaa");
		log.info("seq="+SeqUtil.getNewId("test"));
	}
}
