package com.myxapp.sdk.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具类 Date: 2017年2月10日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author
 */
public final class ExceptionUtil {

	private ExceptionUtil() {

	}

	/**
	 * 获取堆栈信息
	 * 
	 * @param t
	 * @return
	 * @author
	 */
	public static String getTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(stringWriter);
		t.printStackTrace(printWriter);
		return stringWriter.getBuffer().toString();
	}

}
