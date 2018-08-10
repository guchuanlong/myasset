package com.myxapp.sdk.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

import org.apache.commons.codec.binary.Base64;

public class ZlibUtil {

	/**
	 * 用zlib压缩
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static byte[] compress(String input) throws Exception {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Deflater compresser = new Deflater(Deflater.BEST_COMPRESSION, true);
		DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(stream, compresser);
		deflaterOutputStream.write(input.getBytes("UTF-8"));
		deflaterOutputStream.close();
		return stream.toByteArray();
	}

	/**
	 * 解压缩
	 * 
	 * @param barr
	 *            需要解压缩的字节数组
	 * @param charater
	 *            对应压缩时的编码方式
	 * @return
	 * @throws Exception
	 */
	public static byte[] deCompress(byte[] input) throws Exception {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Inflater decompresser = new Inflater(true);
		InflaterOutputStream inflaterOutputStream = new InflaterOutputStream(stream, decompresser);
		inflaterOutputStream.write(input);
		inflaterOutputStream.close();
		return stream.toByteArray();

	}

	public static void main(String[] args) throws Exception {

		String str = "abcde|qqqqqqqqqqqqqqq|wwwwwwwwwwwwwwwwwwww";
		System.out.println(str.length());
		byte[] def = ZlibUtil.compress(str);
		String strBase = Base64.encodeBase64String(def);
		System.out.println(strBase.length());
		System.out.println("str base64 string " + strBase);
		byte[] decStr = Base64.decodeBase64(strBase);
		byte[] decode_str = ZlibUtil.deCompress(decStr);
		String decStrOgr = new String(decode_str, "UTF-8");
		System.out.println("decStrOgr " + decStrOgr);
	}
}