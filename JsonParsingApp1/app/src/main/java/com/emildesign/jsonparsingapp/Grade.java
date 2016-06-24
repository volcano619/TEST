package com.emildesign.jsonparsingapp;

import com.google.gson.annotations.SerializedName;

public class Grade 
{
	@SerializedName(JSONParsingClass.COURSE_NAME)
	private String course;
	
	@SerializedName(JSONParsingClass.COURSE_GRADE)
	private int courseGrade;
	
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public int getCourseGrade() {
		return courseGrade;
	}
	public void setCourseGrade(int courseGrade) {
		this.courseGrade = courseGrade;
	}
	@Override
	public String toString() {
		return "Grade [course=" + course + ", courseGrade=" + courseGrade + "]";
	}	
}
