package com.hufeiya.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hufeiya.utils.CookieUtils;
import com.qiniu.util.Auth;

public class UptokenServlet extends HttpServlet {

	private static Auth auth = Auth.create("8VTgVpzoGcxeuMR0df4te3qH9JE8xDy0XqZCqTLR", "b11rZxCn7f4JPMW2kQUMPS7A5q2F4UVo13smFbfv");

	// 覆盖上传
	private String getUpToken(String signId){
	    return auth.uploadToken("coolsignin",signId);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		String signId = req.getParameter("signid");
		System.out.println(signId);
		PrintWriter out = res.getWriter();
		int uidFromCookies = CookieUtils.getUidFromCookies(req.getCookies());
		if (uidFromCookies != CookieUtils.INVALID_VALUE) {
			out.print(getUpToken(signId));
			out.close();
			return;
		} else {
			out.print("false");
			out.close();
			return;
		}
	}
}
