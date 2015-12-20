package com.hufeiya.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.hufeiya.DAO.SignInfoDAO;
import com.hufeiya.entity.SignInfo;
import com.hufeiya.jsonObject.JsonSignInfo;
import com.hufeiya.utils.CookieUtils;

public class SignInServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		String signInJsonString = req.getParameter("signInfo");
		int uidFromCookies = CookieUtils.getUidFromCookies(req.getCookies());
		if (uidFromCookies == CookieUtils.INVALID_VALUE) {
			out.print("banned");//非法登录
			out.close();
			return;
		}else if(signInJsonString == null && signInJsonString.length() == 0){
			out.print("null");//没有收到信息
			out.close();
			return;
		}
		try {  
	        Gson gson = new Gson();
	        JsonSignInfo jsonSignInfo = gson.fromJson(signInJsonString, JsonSignInfo.class);
	        SignInfoDAO signInfoDAO = new SignInfoDAO();
	        boolean isSucceed =  signInfoDAO.updateSingle(jsonSignInfo.getSignId(), jsonSignInfo.getSignDetail(), jsonSignInfo.getSignTimes(), jsonSignInfo.getLastSignPhoto());
	        if (isSucceed){
	        	out.print("true");
				out.close();
				return;
	        }
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
		out.print("false");
		out.close();
	}
}
