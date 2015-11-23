package com.hufeiya.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer uid;
	private String username;
	private String pass;
	private String userNo;
	private String phone;
	private Boolean userType;
	private Set studentSheets = new HashSet(0);
	private Set courses = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String username, String pass, String userNo, String phone,
			Boolean userType, Set studentSheets, Set courses) {
		this.username = username;
		this.pass = pass;
		this.userNo = userNo;
		this.phone = phone;
		this.userType = userType;
		this.studentSheets = studentSheets;
		this.courses = courses;
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

	public Set getStudentSheets() {
		return this.studentSheets;
	}

	public void setStudentSheets(Set studentSheets) {
		this.studentSheets = studentSheets;
	}

	public Set getCourses() {
		return this.courses;
	}

	public void setCourses(Set courses) {
		this.courses = courses;
	}

}