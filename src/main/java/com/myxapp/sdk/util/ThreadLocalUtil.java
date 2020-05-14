package com.myxapp.sdk.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 本地线程工具类 可能有内存泄露风险,需要使用完及时进行清理 Date: 2017年2月10日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author
 */
public final class ThreadLocalUtil {
	private static final ThreadLocal<Map<String, String>> threadLocals = new ThreadLocal<Map<String, String>>();

	private ThreadLocalUtil() {

	}

	/**
	 * 设置
	 * 
	 * @param key
	 * @param value
	 * @author
	 */
	public static void set(String key, String value) {
		Map<String, String> values = threadLocals.get();
		if (values == null) {
			values = new HashMap<String, String>();
			threadLocals.set(values);
		}
		values.put(key, value);
	}

	/**
	 * 获取
	 * 
	 * @param key
	 * @return
	 * @author
	 */
	public static String get(String key) {
		Map<String, String> values = threadLocals.get();
		if (values == null) {
			return null;
		}
		return values.get(key);
	}

	public static void clean() {
		Map<String, String> values = threadLocals.get();
		if (values != null) {
			values.clear();
			values = null;
		}
		threadLocals.remove();
	}

	public static void removeKey(String key) {
		Map<String, String> values = threadLocals.get();
		if (values != null) {
			values.remove(key);
		}
	}

}
