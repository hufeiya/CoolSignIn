package com.hufeiya.entity;

/**
 * SignInfo entity. @author MyEclipse Persistence Tools
 */

public class SignInfo implements java.io.Serializable {

	// Fields

	private SignInfoId id;
	private String signDetail;
	private Integer signTimes;
	private String lastSignPhoto;

	// Constructors

	/** default constructor */
	public SignInfo() {
	}

	/** minimal constructor */
	public SignInfo(SignInfoId id) {
		this.id = id;
	}

	/** full constructor */
	public SignInfo(SignInfoId id, String signDetail, Integer signTimes,
			String lastSignPhoto) {
		this.id = id;
		this.signDetail = signDetail;
		this.signTimes = signTimes;
		this.lastSignPhoto = lastSignPhoto;
	}

	// Property accessors

	public SignInfoId getId() {
		return this.id;
	}

	public void setId(SignInfoId id) {
		this.id = id;
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