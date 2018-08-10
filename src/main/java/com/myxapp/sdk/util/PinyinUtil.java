package com.myxapp.sdk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

/**
 * 拼音工具类 Date: 2017年2月10日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author
 */
public final class PinyinUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(PinyinUtil.class);

	private PinyinUtil() {
	}

	static HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();

	static {
		outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
	}

	public static String getPinyin(String cnWords) throws Exception {
		return PinyinHelper.toHanYuPinyinString(cnWords, outputFormat, "", true);
	}

	public static String getPinyin(String cnWords, String seperator) throws Exception {
		LOGGER.info(cnWords + ":" + seperator);
		return PinyinHelper.toHanYuPinyinString(cnWords, outputFormat, seperator, true);
	}

	public static String[] getFirstPinyin(String cnWords) {
		if (StringUtil.isBlank(cnWords))
			return null;
		return PinyinHelper.toHanyuPinyinStringArray(cnWords.charAt(0));
	}

	public static void main(String[] args) throws Exception {
		System.out.println("你好:" + getPinyin("你好"));
		System.out.println("张学诚:" + getPinyin("张学诚"));
		System.out.println("庞宏涛:" + getPinyin("庞宏涛"));
		System.out.println("余贤雷:" + getPinyin("余贤雷"));
		System.out.println("袁定苗:" + getPinyin("袁定苗"));
		System.out.println("重庆:" + getPinyin("重庆"));
		System.out.println("重庆:" + getPinyin("重庆", ","));
		//
		 System.out.println("你好:" + getFirstPinyin("你a好"));
		// System.out.println("张学诚:" + getFirstPinyin("张1学诚"));
		// System.out.println("庞宏涛:" + getFirstPinyin("庞宏涛"));
		// System.out.println("余贤雷:" + getFirstPinyin("余贤雷"));
		// System.out.println("袁定苗:" + getFirstPinyin("袁定苗"));
	}
}
