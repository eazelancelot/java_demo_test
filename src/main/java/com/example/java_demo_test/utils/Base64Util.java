package com.example.java_demo_test.utils;

import java.util.Base64;

public class Base64Util {
	/**
	 * base 64 加密(無後綴)
	 */
	public static String base64EncodeWithoutPadding(byte[] bytes) {
		return Base64.getEncoder().withoutPadding().encodeToString(bytes);
	}

	/**
	 * base 64 加密(有後綴)
	 */
	public static String base64Encode(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}
	
	/**
	 * base 64解密
	 * @param base64EncodedStr
	 * @return
	 */
	public static byte[] base64Decode(String base64EncodedStr) {
		return Base64.getDecoder().decode(base64EncodedStr);
	}
	
	/**
	 * base 64 URL  加密
	 */
	public static String base64URLEncode(byte[] bytes) {
		return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
	}

	/**
	 * base 64 URL 解密
	 * @param base64EncodedStr
	 * @return
	 */
	public static byte[] base64URLDecode(String base64EncodedStr) {
		return Base64.getUrlDecoder().decode(base64EncodedStr);
	}

}
