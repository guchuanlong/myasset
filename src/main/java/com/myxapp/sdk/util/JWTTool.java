package com.myxapp.sdk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * json web token的工具类，主要用于身份验证
 * 
 * @author douxiaofeng
 *
 */
public class JWTTool {
	private static final Logger LOG = LoggerFactory.getLogger(Md5Encoder.class);
	private static final String DEST_SUB_NAME = "subject";
	private static final String DEST_SUB_VALUE = "jwsClient";
	private static final String PAY_LOAD = "{\"" + DEST_SUB_NAME + "\":\"" + DEST_SUB_VALUE + "\",\"iss\":\"cmc-asc\"}";

	private JWTTool() {

	}

	public static String genJWT(String key) {
		return Jwts.builder().setPayload(PAY_LOAD).signWith(SignatureAlgorithm.HS512, key).compact();
	}

	public static boolean isLegal(String key, String jws) {
		try {
			Jws<Claims> jwsIns = Jwts.parser().setSigningKey(key).parseClaimsJws(jws);
			return jwsIns.getBody().get(DEST_SUB_NAME).equals(DEST_SUB_VALUE);

		} catch (Exception e) {
			LOG.error("", e);
			return false;
		}
	}

	public static void main(String[] args) {
		if (null == args || args.length <= 0) {
			System.out.println("Usage: java cp com.ai.channel.common.util.JWTTool key");
		}
		String key = args[0];
		String token = genJWT(key);
		System.out.println("The generated token (pls. keep secrurity) is:" + token);
		System.out.println(isLegal(key, token));
	}
}
