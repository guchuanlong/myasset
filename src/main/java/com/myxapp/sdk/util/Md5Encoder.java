package com.myxapp.sdk.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MD5编码 Date: 2017年2月10日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author
 */
public final class Md5Encoder {
	private static final Logger LOG = LoggerFactory.getLogger(Md5Encoder.class);

	private Md5Encoder() {
	}

	private static MessageDigest md = null;
	static {
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Can not found MD5 al.");
		}
	}
	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

	private static char[] encodeHex(byte[] data) {

		int l = data.length;

		char[] out = new char[l << 1];

		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS[0x0F & data[i]];
		}

		return out;
	}

	/**
	 * 对URL中的参数增加校验字段，只对参数校验
	 * 
	 * @param seed
	 *            这个和要加密的参数组合在一起，为了防止随意猜测，也作为一个参数参与排序，这样就随机够强了
	 * @param url
	 * @return
	 */
	public static String encodeUrl(String seed, String url) {
		if (StringUtil.isBlank(seed))
			throw new IllegalArgumentException();
		if (StringUtil.isBlank(url))
			return url;
		int index = url.indexOf("?");
		// 没参数
		if (index <= 0)
			return url;
		String parts = url.substring(index + 1);
		String[] splits = parts.split("&");
		if (null == splits || splits.length <= 0)
			return url;
		return url + "&sig=" + getSign(seed, splits);
	}

	public static boolean isUrlTempered(String seed, String url) {
		if (StringUtil.isBlank(seed))
			throw new IllegalArgumentException();
		if (StringUtil.isBlank(url))
			throw new IllegalArgumentException();
		int index = url.indexOf("?");
		// 没参数
		if (index <= 0)
			return false;
		String parts = url.substring(index + 1);
		// 先把sig拿出来
		index = parts.indexOf("&sig=");
		if (index <= 0)
			return false;
		String oriSig = null;
		int next = parts.indexOf("&", index + 1);
		if (next >= 0) {
			oriSig = parts.substring(index+5, next);
			parts = parts.substring(0, index) + parts.substring(next);

		} else {
			oriSig = parts.substring(index+5);
			parts = parts.substring(0, index);

		}
		String[] splits = parts.split("&");
		if (null == splits || splits.length <= 0)
			return false;
		return isParamTempered(seed, splits, oriSig);
	}

	/**
	 * 基于关键信息进行MD5 HASH，防止篡改
	 * 
	 * @param seed
	 *            种子key，建议使用sessionid
	 * @param keys
	 *            关键信息，如订单id，人员ID等，不能改变的值
	 * @return
	 */
	public static String getSign(String seed, String[] keys) {
		if (StringUtil.isBlank(seed))
			return null;
		if (null == keys || keys.length <= 0)
			return null;
		String[] params = new String[keys.length + 1];
		System.arraycopy(keys, 0, params, 0, keys.length);
		params[keys.length] = "sgkey=" + seed;
		Arrays.sort(params);
		String dest = Arrays.toString(params);
		// 开始加密
		byte[] digest = md.digest(dest.getBytes());
		return new String(encodeHex(digest));
	}

	/**
	 * 判断关键信息是否被篡改
	 * 
	 * @param seed
	 * @param keys
	 * @param sig
	 * @return
	 */
	public static boolean isParamTempered(String seed, String[] keys, String sig) {
		if (null == sig)
			return true;
		return !sig.equals(getSign(seed, keys));
	}

	/**
	 * 密码md5加密
	 * 
	 * @param rawPass
	 * @return
	 * @author
	 */
	public static String encodePassword(String rawPass) {

		byte[] digest = md.digest(rawPass.getBytes());

		return new String(encodeHex(digest));
	}
	
	/**
	 * 密码md5加密
	 * 
	 * @param rawPass  原始密码
	 * @param salt  加密盐值
	 * @return
	 * @author
	 */
	public static String encodePassword(String rawPass, String salt) {
		String pass=rawPass+salt;
		byte[] digest = md.digest(pass.getBytes());
		
		return new String(encodeHex(digest));
	}

	/**
	 * 校验密码
	 * 
	 * @param encPass
	 * @param rawPass
	 * @return
	 * @author
	 */
	public static boolean isPasswordValid(String encPass, String rawPass) {
		String pass1 = "" + encPass;
		String pass2 = encodePassword(rawPass);
		return pass1.equals(pass2);
	}

	public static void main(String[] args) {
		/*String url = "/chan/mgmt/view?chanId=123456&type=self";
		String sigUrl = encodeUrl("123456", url);
		System.out.println(sigUrl);
		System.out.println(isUrlTempered("123456", "http://127.0.0.1:8080/mgmt/sec/channel/create?chanId=123456789&chanName=test&ddd=1&sig=78B95FB2426CD5224521C04063E81101"));
		String[] keys={"orderid=123456","userId=1977733"};
		System.out.println(getSign("this is a test", keys));
		System.out.println(isParamTempered("this is a test", keys,"A36298EC875EBC966717888CE228BC7B"));*/
		//BCE2C74486C4EA20F1697D030BD91B1C
		System.out.println(encodePassword("123456", "FB870F5CC56CBC5CA97D1340024B8FCC"));
		
	}
}

