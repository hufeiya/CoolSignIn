package com.hufeiya.jsonObject;
import java.util.HashSet;
import java.util.Set;

import com.hufeiya.entity.Course;
import com.hufeiya.entity.SignInfo;
public class JsonCourse {

		// Fields

		private Integer cid;
		private String courseName;
		private String startDates;
		private Integer numberOfWeeks;
		private Set<JsonSignInfo> signInfos = new HashSet<JsonSignInfo>();

		// Constructors

		/** default constructor */
		public JsonCourse() {
		}

		/** full constructor */
		public JsonCourse(String courseName, String startDates,
				Integer numberOfWeeks, Set signInfos) {
			this.courseName = courseName;
			this.startDates = startDates;
			this.numberOfWeeks = numberOfWeeks;
			this.signInfos = signInfos;
		}
		
		public JsonCourse(Course course){
			this.courseName = course.getCourseName();
			this.startDates = course.getStartDates();
			this.numberOfWeeks = course.getNumberOfWeeks();
			Set<SignInfo> temp = course.getSignInfos();
			for(SignInfo signInfo:temp){
				JsonSignInfo jsonSignInfo = new JsonSignInfo(signInfo);
				this.signInfos.add(jsonSignInfo);
			}
			
		}
		
		/**used for student user*/
		public JsonCourse(Course course,JsonSignInfo jsonSignInfo){
			this.courseName = course.getCourseName();
			this.startDates = course.getStartDates();
			this.numberOfWeeks = course.getNumberOfWeeks();
			this.getSignInfos().add(jsonSignInfo);
			
		}


		public Integer getCid() {
			return this.cid;
		}

		public void setCid(Integer cid) {
			this.cid = cid;
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