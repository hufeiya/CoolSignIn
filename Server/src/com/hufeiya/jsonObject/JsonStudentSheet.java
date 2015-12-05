package com.hufeiya.jsonObject;

import java.util.HashSet;
import java.util.Set;

import com.hufeiya.entity.Student;
import com.hufeiya.entity.StudentSheet;
public class JsonStudentSheet {
	// Fields

	private Integer sheetId;
	private String sheetName;
	private Set<JsonStudent> jsonStudents = new HashSet<>();

	// Constructors

	/** default constructor */
	public JsonStudentSheet() {
	}

	public JsonStudentSheet(StudentSheet studentSheet) {
		this.sheetId = studentSheet.getSheetId();
		this.sheetName = studentSheet.getSheetName();
		Set<Student> suStudents = studentSheet.getStudents();
		for (Student student : suStudents) {
			this.jsonStudents.add(new JsonStudent(student));
		}
	}

	public Integer getSheetId() {
		return sheetId;
	}

	public void setSheetId(Integer sheetId) {
		this.sheetId = sheetId;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public Set<JsonStudent> getJsonStudents() {
		return jsonStudents;
	}

	public void setJsonStudents(Set<JsonStudent> jsonStudents) {
		this.jsonStudents = jsonStudents;
	}

}