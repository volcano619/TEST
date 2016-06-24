package com.emildesign.jsonparsingapp;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;

import android.util.Log;

public class JSONParsingClass 
{
	public static final String TAG = JSONParsingClass.class.getSimpleName();
	
	//static final String for parsed elements
	//Class elements
	public static final String STUDENTS_LIST_ELEMENT = "Class_Studens";
	
	//Student elements
	public static final String STUDENT_ID = "Student_Id";
	public static final String STUDENT_NAME = "Student_Name";
	public static final String STUDENT_AGE = "Student_Age";
	public static final String STUDENT_GRADE_LIST = "Student_Grades";
	public static final String CLASS = "Class";
	
	//Grade elements
	public static final String COURSE_NAME = "Course_Name";
	public static final String COURSE_GRADE = "Course_Grade";

	//Native method for parsing json file
	public static List<Student> NativeJsonParseMethodForStudents(String jsonString) {
		try{
			//Creating list object for storing the parsed students
			List<Student> studentsList = new ArrayList<Student>();
			
			//Creating JSON object from string
			JSONObject json = new JSONObject(jsonString);
			
			//Getting "Class" element from JSON file
			JSONObject classElement = json.getJSONObject(CLASS);

			//Making sure that students list array exists
			if (!classElement.isNull(STUDENTS_LIST_ELEMENT))
			{
				//Getting students array
				JSONArray array = classElement.getJSONArray(STUDENTS_LIST_ELEMENT);
				
				//Creating empty json object for students loop
				JSONObject currentStudent;
				
				//looping all the students and adding them parsed one by one to the list
				for (int i = 0 ; i < array.length() ; i ++){
					currentStudent = array.getJSONObject(i);
					
					//Method to parse students and add them to the list
					//addCurrentStudentToStudentsList(currentStudent, studentsList);
					
					addCurrentStudentToStudentsListUsingGson(currentStudent, studentsList);
				}
				//Returning parsed students list
				return studentsList;
			}
			else
			{
				Log.d(TAG, "Students List is Null");
				return null;
			}
		} catch (Exception e) {
		   	Log.e("EXC", "Error", e);
		   	return null;
		}
	}
	
	//Method to parse students and added them to the list
	private static void addCurrentStudentToStudentsList(JSONObject currentStudent, List<Student> studentsList) 
	{
		//Initializing Student Object
		Student student = new Student();
		
		//Creating empty list for student's grades.
		List<Grade> studentGrades = new ArrayList<Grade>();
		try {
			//Parsing student's details.
			student.setStudentId(currentStudent.getString(STUDENT_ID));
			student.setStudentName(currentStudent.getString(STUDENT_NAME));
			student.setStudentAge(currentStudent.getInt(STUDENT_AGE));	
			
			//Making sure that student's grades list array exists
			if (!currentStudent.isNull(STUDENT_GRADE_LIST))
			{
				//Getting grades array
				JSONArray array = currentStudent.getJSONArray(STUDENT_GRADE_LIST);
				
				//Creating empty json object for grades loop
				JSONObject currentGrade;
				
				//looping all the student's grades and adding them parsed one by one to the list
				for (int i = 0 ; i < array.length() ; i ++){
					currentGrade = array.getJSONObject(i);
					
					//Method to parse grades and add them to the list
					addCurrentGradeToGradesList(currentGrade, studentGrades);
				}
			}
			//Setting student's grades
			student.setStudentGrades(studentGrades);
			
			//Adding student to the list
			studentsList.add(student);
			Log.d(TAG, "Added student: "+ student.toString()); 
		} catch (JSONException e) {
			Log.e(TAG, "Exception thrown on adding student to students list: "+ e.toString());
			e.printStackTrace();
		}	
	}
	
	//Method to parse grades and add them to the list
	private static void addCurrentGradeToGradesList(JSONObject currentGrade, List<Grade> studentGrades) {
		Grade grade = new Grade();
		Log.d(TAG, "currentGrade object: " + currentGrade.toString());
		try 
		{
			//Parsing grade's details
			grade.setCourse(currentGrade.getString(COURSE_NAME));
			grade.setCourseGrade(currentGrade.getInt(COURSE_GRADE));
			
			//Adding grade to the list
			studentGrades.add(grade);
			
		} catch (JSONException e) {
		Log.e(TAG, "Exception thrown on adding grade to grades list: "+ e.toString());
		e.printStackTrace();
		}
	}
	
	private static void addCurrentStudentToStudentsListUsingGson(JSONObject currentStudent, List<Student> studentsList) 
	{
		Gson gson = new Gson();
		Student student = gson.fromJson(currentStudent.toString(), Student.class);
		studentsList.add(student);
	}
}
