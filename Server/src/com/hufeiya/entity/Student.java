package com.hufeiya.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	// Fields

	private Integer sid;
	private StudentSheet studentSheet;
	private String clientId;
	private String studentName;
	private String studentNo;
	private String className;
	private Set signInfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** full constructor */
	public Student(StudentSheet studentSheet, String clientId,
			String studentName, String studentNo, String className,
			Set signInfos) {
		this.studentSheet = studentSheet;
		this.clientId = clientId;
		this.studentName = studentName;
		this.studentNo = studentNo;
		this.className = className;
		this.signInfos = signInfos;
	}

	// Property accessors

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public StudentSheet getStudentSheet() {
		return this.studentSheet;
	}

	public void setStudentSheet(StudentSheet studentSheet) {
		this.studentSheet = studentSheet;
	}

	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentNo() {
		return this.studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Set getSignInfos() {
		return this.signInfos;
	}

	public void setSignInfos(Set signInfos) {
		this.signInfos = signInfos;
	}

}