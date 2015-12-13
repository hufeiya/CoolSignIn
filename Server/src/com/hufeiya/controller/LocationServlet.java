package com.hufeiya.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hufeiya.DAO.UserDAO;
import com.hufeiya.entity.User;
import com.hufeiya.utils.CookieUtils;

public class LocationServlet extends HttpServlet {
	private static final Boolean IS_STUDENT = true;
	private static final int INVALID_VALUE = -1;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		int cid = Integer.parseInt(req.getParameter("cid"));
		System.out.println(cid);
		PrintWriter out = res.getWriter();
		double latitude;
		double longitude;
		try {
			 latitude = Double.parseDouble(req.getParameter("latitude"));
			 longitude = Double.parseDouble(req.getParameter("longitude"));
			
		} catch (NumberFormatException e) {
			out.print("false");
			out.close();
			return;
		}

		int uidFromCookies = CookieUtils.getUidFromCookies(req.getCookies());
		if (uidFromCookies != INVALID_VALUE) {
				//TODO if teacher,save the location,else jduge the distance.
			out.print("true");
			out.close();
			return;
		} else {
			out.print("false");
			out.close();
			return;
		}
	}
}
