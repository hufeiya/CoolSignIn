package com.hufeiya.utils;

import java.io.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import org.apache.tomcat.util.codec.binary.Base64;

public class AESUtils {
	static String IV = "AAAAAAAAAAAAAAAA";
	// If my users are more than 9000W.It  breaks.Hope it be true
	static final int PADDING = 10000000; 
	static String encryptionKey = "0123456789abcdef";
	static String STRINGPADDING = "\0\0\0\0\0\0\0\0";
	static final int INVALID_VALUE = -1;

	private static byte[] encrypt(String plainText, String encryptionKey)
			throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"),
				"AES");
		cipher.init(Cipher.ENCRYPT_MODE, key,
				new IvParameterSpec(IV.getBytes("UTF-8")));
		return cipher.doFinal(plainText.getBytes("UTF-8"));
	}

	private static String decrypt(byte[] cipherText, String encryptionKey)
			throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"),
				"AES");
		cipher.init(Cipher.DECRYPT_MODE, key,
				new IvParameterSpec(IV.getBytes("UTF-8")));
		return new String(cipher.doFinal(cipherText), "UTF-8");
	}

	public static String encrypt(int target) {
		target += PADDING;
		String targetString = Integer.toString(target);
		targetString += STRINGPADDING;
		try {
			return new String(Base64.encodeBase64(encrypt(targetString,
					encryptionKey)));

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int decrypt(String encryptedString) {
		byte[] decodedBytes;
		decodedBytes = Base64.decodeBase64(encryptedString);
		try {
			String rawString = decrypt(decodedBytes, encryptionKey);
			rawString = rawString.substring(0, 8);
			return Integer.parseInt(rawString) - PADDING;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return INVALID_VALUE;
		} catch (Exception e) {
			e.printStackTrace();
			return INVALID_VALUE;
		}
	}

}