package com.myxapp.sdk.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

/**
 * 图片字节工具类 Date: 2017年2月10日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author
 */
public class ImageByteUtil {
	/**
	 * 图片到byte数组
	 * 
	 * @param path
	 * @return
	 * @author
	 */
	public static byte[] image2byte(String path) {
		byte[] data = null;
		FileImageInputStream input = null;
		ByteArrayOutputStream output = null;
		try {
			input = new FileImageInputStream(new File(path));
			output = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int numBytesRead = 0;
			while ((numBytesRead = input.read(buf)) != -1) {
				output.write(buf, 0, numBytesRead);
			}
			data = output.toByteArray();
		} catch (FileNotFoundException ex1) {
			ex1.printStackTrace();
		} catch (IOException ex1) {
			ex1.printStackTrace();
		} finally {
			if (null != input) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != output) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}

	/**
	 * byte数组到图片
	 * 
	 * @param data
	 * @param path
	 * @author
	 */
	public static void byte2image(byte[] data, String path) {
		if (data.length < 3 || path.equals(""))
			return;
		try {
			FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
			imageOutput.write(data, 0, data.length);
			imageOutput.close();
			System.out.println("Make Picture success,Please find image in " + path);
		} catch (Exception ex) {
			System.out.println("Exception: " + ex);
			ex.printStackTrace();
		}
	}

	/**
	 * byte数组到16进制字符串
	 * 
	 * @param data
	 * @return
	 * @author
	 */
	public static String byte2string(byte[] data) {
		if (data == null || data.length <= 1)
			return "0x";
		if (data.length > 200000)
			return "0x";
		StringBuffer sb = new StringBuffer();
		int buf[] = new int[data.length];
		// byte数组转化成十进制
		for (int k = 0; k < data.length; k++) {
			buf[k] = data[k] < 0 ? (data[k] + 256) : (data[k]);
		}
		// 十进制转化成十六进制
		for (int k = 0; k < buf.length; k++) {
			if (buf[k] < 16)
				sb.append("0" + Integer.toHexString(buf[k]));
			else
				sb.append(Integer.toHexString(buf[k]));
		}
		return "0x" + sb.toString().toUpperCase();
	}
}
