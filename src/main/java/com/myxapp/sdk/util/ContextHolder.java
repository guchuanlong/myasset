package com.myxapp.sdk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ApplicationContextUtil <br>
 * Description: 获取应用程序中的spring context<br>
 * Date: 2016年6月28日 <br>
 * Copyright (c) 2016 AI <br>
 * 
 * @author gucl
 */
public class ContextHolder {
	private static final Logger logger = LoggerFactory.getLogger(ContextHolder.class);
	// 约定 spring context的路径
	private static final String PATH = "classpath:context/core-context.xml";

	private static AbstractApplicationContext appContext;

	/**
	 * 用于外部接口传入spring context
	 * 
	 * @param ctx
	 * @author gucl
	 */
	public synchronized static void setApplicationContext(AbstractApplicationContext ctx) {
		appContext = ctx;
	}

	/**
	 * 初始化上下文
	 * 
	 * @author
	 */
	private synchronized static void initApplicationContext() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { PATH });
		context.start();
		appContext = context;
	}

	/**
	 * 获取服务
	 * 
	 * @param beanId
	 * @param clazz
	 * @return
	 * @author
	 */
	public static <T> T getService(String beanId, Class<T> clazz) {
		if (appContext == null) {
			initApplicationContext();
		}
		return (T) appContext.getBean(beanId, clazz);
	}

	/**
	 * 获取服务
	 * 
	 * @param clazz
	 * @return
	 * @author
	 */
	public static <T> T getService(Class<T> clazz) {
		if (appContext == null) {
			initApplicationContext();
		}
		return (T) appContext.getBean(clazz);
	}

	/**
	 * 获取服务
	 * 
	 * @param beanId
	 * @return
	 * @author
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getService(String beanId) {
		if (appContext == null) {
			initApplicationContext();
		}
		return (T) appContext.getBean(beanId);
	}

	/**
	 * 关闭AppContext
	 * 
	 * @author
	 */
	public static void closeAppContext() {
		if (null != appContext) {
			appContext.close();
		}
	}

	public static String getProperty(String key) {
		String value = null;
		try {
			value = appContext.getBeanFactory().resolveEmbeddedValue("${" + key + "}");
		} catch (Exception e) {
			logger.error("There are no containers configer in dubbo.properties!");
		}
		return value;
	}
}
