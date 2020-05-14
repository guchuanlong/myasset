package com.myxapp.sdk.util;

import java.security.MessageDigest;

public class Sha256Encoder {
	private Sha256Encoder() {
	}

	public static String sha256(String base) {
		if (StringUtil.isBlank(base))
			return null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static boolean equals(String encoded, String origin) {
		if (StringUtil.isBlank(encoded) || StringUtil.isBlank(origin))
			return false;
		return sha256(origin).equals(encoded);
	}

	public static void main(String[] args) {
		System.out.println(sha256("this is a test"));
	}

}
