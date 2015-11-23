package com.hufeiya.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * StudentSheet entity. @author MyEclipse Persistence Tools
 */

public class StudentSheet implements java.io.Serializable {

	// Fields

	private Integer sheetId;
	private User user;
	private String sheetName;
	private Set students = new HashSet(0);

	// Constructors

	/** default constructor */
	public StudentSheet() {
	}

	/** minimal constructor */
	public StudentSheet(User user, String sheetName) {
		this.user = user;
		this.sheetName = sheetName;
	}

	/** full constructor */
	public StudentSheet(User user, String sheetName, Set students) {
		this.user = user;
		this.sheetName = sheetName;
		this.students = students;
	}

	// Property accessors

	public Integer getSheetId() {
		return this.sheetId;
	}

	public void setSheetId(Integer sheetId) {
		this.sheetId = sheetId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSheetName() {
		return this.sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public Set getStudents() {
		return this.students;
	}

	public void setStudents(Set students) {
		this.students = students;
	}

}