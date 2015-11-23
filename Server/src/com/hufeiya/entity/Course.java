package com.hufeiya.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Course entity. @author MyEclipse Persistence Tools
 */

public class Course implements java.io.Serializable {

	// Fields

	private Integer cid;
	private User user;
	private String courseName;
	private String startDates;
	private Integer numberOfWeeks;
	private Set signInfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Course() {
	}

	/** full constructor */
	public Course(User user, String courseName, String startDates,
			Integer numberOfWeeks, Set signInfos) {
		this.user = user;
		this.courseName = courseName;
		this.startDates = startDates;
		this.numberOfWeeks = numberOfWeeks;
		this.signInfos = signInfos;
	}

	// Property accessors

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getStartDates() {
		return this.startDates;
	}

	public void setStartDates(String startDates) {
		this.startDates = startDates;
	}

	public Integer getNumberOfWeeks() {
		return this.numberOfWeeks;
	}

	public void setNumberOfWeeks(Integer numberOfWeeks) {
		this.numberOfWeeks = numberOfWeeks;
	}

	public Set getSignInfos() {
		return this.signInfos;
	}

	public void setSignInfos(Set signInfos) {
		this.signInfos = signInfos;
	}

}