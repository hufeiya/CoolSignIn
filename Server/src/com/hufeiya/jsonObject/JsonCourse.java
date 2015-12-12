package com.hufeiya.jsonObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.hufeiya.entity.Course;
import com.hufeiya.entity.SignInfo;
public class JsonCourse {

		// Fields

		private Integer cid;
		private Integer uid;
		private String teacherName;
		private String courseName;
		private String startDates;
		private Integer numberOfWeeks;
		private Map<Integer, JsonSignInfo> signInfos = new HashMap<>();

		// Constructors

		/** default constructor */
		public JsonCourse() {
		}

		/** full constructor */
		public JsonCourse(String courseName, String startDates,
				Integer numberOfWeeks, Map signInfos) {
			this.courseName = courseName;
			this.startDates = startDates;
			this.numberOfWeeks = numberOfWeeks;
			this.signInfos = signInfos;
		}
		
		public JsonCourse(Course course){
			this.cid = course.getCid();
			this.uid = course.getUser().getUid();
			this.teacherName = course.getUser().getUsername();
			this.courseName = course.getCourseName();
			this.startDates = course.getStartDates();
			this.numberOfWeeks = course.getNumberOfWeeks();
			Set<SignInfo> temp = course.getSignInfos();
			for(SignInfo signInfo:temp){
				JsonSignInfo jsonSignInfo = new JsonSignInfo(signInfo);
				this.signInfos.put(jsonSignInfo.getSignId(), jsonSignInfo);
			}
			
		}
		
		/**used for student user*/
		public JsonCourse(Course course,JsonSignInfo jsonSignInfo){
			this.cid = course.getCid();
			this.teacherName = course.getUser().getUsername();
			this.courseName = course.getCourseName();
			this.startDates = course.getStartDates();
			this.numberOfWeeks = course.getNumberOfWeeks();
			this.getSignInfos().put(jsonSignInfo.getSignId(), jsonSignInfo);
			
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

		public Map<Integer, JsonSignInfo> getSignInfos() {
			return this.signInfos;
		}

		public void setSignInfos(Map<Integer, JsonSignInfo> signInfos) {
			this.signInfos = signInfos;
		}

		public Integer getUid() {
			return uid;
		}

		public void setUid(Integer uid) {
			this.uid = uid;
		}

		public String getTeacherName() {
			return teacherName;
		}

		public void setTeacherName(String teacherName) {
			this.teacherName = teacherName;
		}

	}