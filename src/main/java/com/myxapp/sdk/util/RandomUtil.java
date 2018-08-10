package com.myxapp.sdk.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 生成指定位数的随机数
 *
 * Date: 2016年3月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author gucl
 */
public class RandomUtil {

	/**
	 * 返回任意长度的随机数
	 * 
	 * @param length
	 * @return
	 */
	public static String randomNum(int length) {
		StringBuilder sb = new StringBuilder();
		sb.append(ThreadLocalRandom.current().nextLong(1, 10));
		for (int i = 0; i < length - 1; i++) {
			sb.append(ThreadLocalRandom.current().nextLong(0, 10));
		}
		return sb.toString();
	}

	/**
	 * 随机字符串
	 * 
	 * @param length
	 * @return
	 * @author
	 */
	public static final String randomString(int length) {
		String alphabet = new String("23456789ABCDEFGHJKMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz"); // 9
		int n = alphabet.length(); // 10

		String result = new String();

		for (int i = 0; i < length; i++) // 12
			result = result + alphabet.charAt(ThreadLocalRandom.current().nextInt(n)); // 13

		return result;
	}

	public static void main(String[] args){
		System.out.println(randomNum(15));
		System.out.println(randomString(15));
	}
}
