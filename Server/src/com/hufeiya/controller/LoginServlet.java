package com.hufeiya.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
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

public class LoginServlet extends HttpServlet {
	private  static final Boolean IS_STUDENT = true;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); 
        res.setContentType("text/html;charset=UTF-8"); 
        res.setCharacterEncoding("UTF-8"); 
        PrintWriter out = res.getWriter();
        String phone = req.getParameter("phone");
        String pass = req.getParameter("pass");
        if(phone == null || pass == null){
        	out.print("[\"false\"]");
        	out.close();
        	return;
        }
        User example = new User(phone,pass);
        UserDAO userDAO = new UserDAO();
        List resultUserList = userDAO.findByExample(example);
        if(resultUserList == null || resultUserList.size() == 0){//cannot find the User with target password
        	out.print("[\"false\"]");
        	out.close();
        	return;
        }
        User user = (User) resultUserList.get(0);
        JsonUser jsonUser = userToJsonUser(user);
        final Gson gson = new Gson();
        final String json = gson.toJson(jsonUser);
        out.print("[\"true\","  + json + "]");
        out.close();
	}
	public JsonUser userToJsonUser(User user){
		 JsonUser jsonUser =  new JsonUser(user);
		 if (user.getUserType() == IS_STUDENT){
			Student example = new Student();
			example.setStudentNo(user.getUserNo());
			List<Student> students = new StudentDAO().findByExample(example);
			if(students == null || students.size() == 0){		//This student haven't joined a course
				return jsonUser;
			}
			Student student = students.get(0);
			SignInfo exampleInfo = new SignInfo();
			exampleInfo.setStudent(student);
			List<SignInfo>signInfos = new SignInfoDAO().findByExample(exampleInfo);
			Set<JsonCourse>jsonCourses = new HashSet<JsonCourse>();
			for(SignInfo signInfo: signInfos){
				JsonSignInfo jsonSignInfo = new JsonSignInfo(signInfo);
				JsonCourse jsonCourse = new JsonCourse(signInfo.getCourse(), jsonSignInfo);
				jsonCourses.add(jsonCourse);
			}
			jsonUser.setJsonCourses(jsonCourses);
			return jsonUser;
	        }else{
	        	return jsonUser;
	        }
	}
}
