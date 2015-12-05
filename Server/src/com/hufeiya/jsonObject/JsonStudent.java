package com.hufeiya.jsonObject;

import java.util.HashSet;
import java.util.Set;

import com.hufeiya.entity.SignInfo;
import com.hufeiya.entity.Student;

public class JsonStudent {

	// Fields
	private Integer sid;
	private String clientId;
	private String studentName;
	private String studentNo;
	private String className;
	private Set<JsonSignInfo> jsonSignInfos = new HashSet<>();

	// Constructors

	/** default constructor */
	public JsonStudent() {
	}

	public JsonStudent(Student student) {
		this.sid = student.getSid();
		this.clientId = student.getClientId();
		this.studentName = student.getStudentName();
		this.studentNo = student.getStudentNo();
		this.className = student.getClassName();
		Set<SignInfo> signInfos = student.getSignInfos();
		for (SignInfo sinInfo : signInfos ){
			jsonSignInfos.add(new JsonSignInfo(sinInfo));
		}
	}

	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Set<JsonSignInfo> getJsonSignInfos() {
		return jsonSignInfos;
	}

	public void setJsonSignInfos(Set<JsonSignInfo> jsonSignInfos) {
		this.jsonSignInfos = jsonSignInfos;
	}

	
	

}