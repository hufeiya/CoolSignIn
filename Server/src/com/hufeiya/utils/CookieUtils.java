package com.hufeiya.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	private static final Boolean IS_STUDENT = true;
	private static final int INVALID_VALUE = -1;
	public static void addCookieToRespose(HttpServletResponse response, int id) {
		Cookie cookie = new Cookie("uid", AESUtils.encrypt(id));
		System.out.println(cookie.getValue());//debug the cookie 
		response.addCookie(cookie);
	}

	public static  int getUidFromCookies(Cookie[] cookies) {
		int decryptedCookieValue = INVALID_VALUE;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("uid")) {
					int tempDecrypted = AESUtils.decrypt(cookie.getValue());
					if (tempDecrypted != INVALID_VALUE) {
						decryptedCookieValue = tempDecrypted;
						break;
					}
				}
			}
		}
		return decryptedCookieValue;
	}
}
