package com.hufeiya.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hufeiya.DAO.SignInfoDAO;
import com.hufeiya.DAO.StudentDAO;
import com.hufeiya.DAO.UserDAO;
import com.hufeiya.entity.SignInfo;
import com.hufeiya.entity.Student;
import com.hufeiya.entity.User;
import com.hufeiya.jsonObject.JsonCourse;
import com.hufeiya.jsonObject.JsonSignInfo;
import com.hufeiya.jsonObject.JsonUser;
import com.hufeiya.utils.AESUtils;

import org.apache.tomcat.util.codec.binary.Base64;

public class LoginServlet extends HttpServlet {
	private static final Boolean IS_STUDENT = true;
	private static final int INVALID_VALUE = -1;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		UserDAO userDAO = new UserDAO();
		User user = null;
		int uidFromCookies = getUidFromCookies(req.getCookies());
		if (uidFromCookies != INVALID_VALUE) {
			user = userDAO.findById(uidFromCookies);
			System.out.println("用户id:" + user.getUid());
		} else {
			String phone = req.getParameter("phone");
			String pass = req.getParameter("pass");
			if (phone == null || pass == null) {
				out.print("[\"false\"]");
				out.close();
				return;
			}
			User example = new User(phone, pass);
			List resultUserList = userDAO.findByExample(example);
			// cannot find the User with target password
			if (resultUserList == null || resultUserList.size() == 0) {
				out.print("[\"false\"]");
				out.close();
				return;
			}
			user = (User) resultUserList.get(0);
			addCookieToRespose(res, user.getUid());
		}
		JsonUser jsonUser = userToJsonUser(user);
		final Gson gson = new Gson();
		final String json = gson.toJson(jsonUser);
		out.print("[\"true\"," + json + "]");
		out.close();
		
	}

	public JsonUser userToJsonUser(User user) {
		JsonUser jsonUser = new JsonUser(user);
		if (user.getUserType() == IS_STUDENT) {
			Student example = new Student();
			example.setStudentNo(user.getUserNo());
			List<Student> students = new StudentDAO().findByExample(example);
			// This student haven't joined a course
			if (students == null || students.size() == 0) {
				jsonUser.setJsonCoursesMap(null);
				return jsonUser;
			}
			Map<Integer,JsonCourse> jsonCourses = new HashMap<Integer, JsonCourse>();
			//Student student = students.get(0);
			for(Student student : students){
				List<SignInfo> signInfos = new SignInfoDAO().findByProperty(
					"student", student);
				for (SignInfo signInfo : signInfos) {
					JsonSignInfo jsonSignInfo = new JsonSignInfo(signInfo);
					JsonCourse jsonCourse = new JsonCourse(signInfo.getCourse(),
							jsonSignInfo);
					jsonCourses.put(jsonCourse.getCid(), jsonCourse);
				}
			}
			jsonUser.setJsonCoursesMap(jsonCourses);
			return jsonUser;
		} else {
			return jsonUser;
		}
	}

	private void addCookieToRespose(HttpServletResponse response, int id) {
		Cookie cookie = new Cookie("uid", AESUtils.encrypt(id));
		System.out.println(cookie.getValue());//debug the cookie 
		response.addCookie(cookie);
	}

	private int getUidFromCookies(Cookie[] cookies) {
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
