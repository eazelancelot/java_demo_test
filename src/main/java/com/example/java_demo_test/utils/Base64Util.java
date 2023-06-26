package com.example.java_demo_test.utils;

import java.util.Base64;

public class Base64Util {
	/**
	 * base 64 �[�K(�L���)
	 */
	public static String base64EncodeWithoutPadding(byte[] bytes) {
		return Base64.getEncoder().withoutPadding().encodeToString(bytes);
	}

	/**
	 * base 64 �[�K(�����)
	 */
	public static String base64Encode(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}
	
	/**
	 * base 64�ѱK
	 * @param base64EncodedStr
	 * @return
	 */
	public static byte[] base64Decode(String base64EncodedStr) {
		return Base64.getDecoder().decode(base64EncodedStr);
	}
	
	/**
	 * base 64 URL  �[�K
	 */
	public static String base64URLEncode(byte[] bytes) {
		return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
	}

	/**
	 * base 64 URL �ѱK
	 * @param base64EncodedStr
	 * @return
	 */
	public static byte[] base64URLDecode(String base64EncodedStr) {
		return Base64.getUrlDecoder().decode(base64EncodedStr);
	}

}
