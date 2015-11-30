package com.hufeiya.SignIn.jsonObject;

import java.util.HashSet;
import java.util.Set;


public class JsonUser {

	// Fields

	private Integer uid;
	private String username;
	private String pass;
	private String userNo;
	private String phone;
	private Boolean userType;
	private Set<JsonCourse> jsonCourses = new HashSet<JsonCourse>();
	// Constructors

	/** default constructor */
	public JsonUser() {
	}

	/** full constructor */
	public JsonUser(String username, String pass, String userNo, String phone,
			Boolean userType) {
		this.username = username;
		this.pass = pass;
		this.userNo = userNo;
		this.phone = phone;
		this.userType = userType;
	}
	


	// Property accessors

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Set<JsonCourse> getJsonCourses() {
		return jsonCourses;
	}

	public void setJsonCourses(Set<JsonCourse> jsonCourses) {
		this.jsonCourses = jsonCourses;
	}

	public String getUserNo() {
		return this.userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getUserType() {
		return this.userType;
	}

	public void setUserType(Boolean userType) {
		this.userType = userType;
	}

}