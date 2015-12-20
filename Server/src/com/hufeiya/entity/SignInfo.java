package com.hufeiya.entity;

/**
 * SignInfo entity. @author MyEclipse Persistence Tools
 */

public class SignInfo implements java.io.Serializable {

	// Fields

	private Integer signId;
	private Student student;
	private Course course;
	private String signDetail;
	private Integer signTimes;
	private String lastSignPhoto;

	// Constructors

	/** default constructor */
	public SignInfo() {
	}

	/** minimal constructor */
	public SignInfo(Student student, Course course) {
		this.student = student;
		this.course = course;
	}

	/** full constructor */
	public SignInfo(Student student, Course course, String signDetail,
			Integer signTimes, String lastSignPhoto) {
		this.student = student;
		this.course = course;
		this.signDetail = signDetail;
		this.signTimes = signTimes;
		this.lastSignPhoto = lastSignPhoto;
	}
	/** constructor for update*/
	public SignInfo( String signDetail,
			Integer signTimes, String lastSignPhoto) {
		this.signDetail = signDetail;
		this.signTimes = signTimes;
		this.lastSignPhoto = lastSignPhoto;
	}

	// Property accessors

	public Integer getSignId() {
		return this.signId;
	}

	public void setSignId(Integer signId) {
		this.signId = signId;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getSignDetail() {
		return this.signDetail;
	}

	public void setSignDetail(String signDetail) {
		this.signDetail = signDetail;
	}

	public Integer getSignTimes() {
		return this.signTimes;
	}

	public void setSignTimes(Integer signTimes) {
		this.signTimes = signTimes;
	}

	public String getLastSignPhoto() {
		return this.lastSignPhoto;
	}

	public void setLastSignPhoto(String lastSignPhoto) {
		this.lastSignPhoto = lastSignPhoto;
	}

}