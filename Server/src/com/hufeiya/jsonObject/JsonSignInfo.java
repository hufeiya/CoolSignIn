package com.hufeiya.jsonObject;

import com.hufeiya.entity.SignInfo;


/**
 * SignInfo entity. @author MyEclipse Persistence Tools
 */

public class JsonSignInfo {

	// Fields

	private Integer signId;
	private String studentName;
	private String studengNo;
	private String signDetail;
	private Integer signTimes;
	private String lastSignPhoto;

	// Constructors

	/** default constructor */
	public JsonSignInfo() {
	}


	/** full constructor */
	public JsonSignInfo(String studentName,String studentNo, String signDetail,
			Integer signTimes, String lastSignPhoto) {
		this.setStudentName(studentName);
		this.setStudengNo(studentNo);
		this.signDetail = signDetail;
		this.signTimes = signTimes;
		this.lastSignPhoto = lastSignPhoto;
	}
	
	public JsonSignInfo(SignInfo s) {
		this.setStudentName(s.getStudent().getStudentName());
		this.setStudengNo(s.getStudent().getStudentNo());
		this.signDetail = s.getSignDetail();
		this.signTimes = s.getSignTimes();
		this.lastSignPhoto = s.getLastSignPhoto();
	}
	// Property accessors

	public Integer getSignId() {
		return this.signId;
	}

	public void setSignId(Integer signId) {
		this.signId = signId;
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


	public String getStudentName() {
		return studentName;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public String getStudengNo() {
		return studengNo;
	}


	public void setStudengNo(String studengNo) {
		this.studengNo = studengNo;
	}

}