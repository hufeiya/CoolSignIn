package com.hufeiya.entity;

/**
 * SignInfoId entity. @author MyEclipse Persistence Tools
 */

public class SignInfoId implements java.io.Serializable {

	// Fields

	private Course course;
	private Student student;

	// Constructors

	/** default constructor */
	public SignInfoId() {
	}

	/** full constructor */
	public SignInfoId(Course course, Student student) {
		this.course = course;
		this.student = student;
	}

	// Property accessors

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SignInfoId))
			return false;
		SignInfoId castOther = (SignInfoId) other;

		return ((this.getCourse() == castOther.getCourse()) || (this
				.getCourse() != null && castOther.getCourse() != null && this
				.getCourse().equals(castOther.getCourse())))
				&& ((this.getStudent() == castOther.getStudent()) || (this
						.getStudent() != null && castOther.getStudent() != null && this
						.getStudent().equals(castOther.getStudent())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCourse() == null ? 0 : this.getCourse().hashCode());
		result = 37 * result
				+ (getStudent() == null ? 0 : this.getStudent().hashCode());
		return result;
	}

}