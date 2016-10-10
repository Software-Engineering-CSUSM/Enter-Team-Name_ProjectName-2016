package edu.CSUSM.testTaker.Backend;

import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.spi.RegisterableService;
import javax.imageio.spi.ServiceRegistry;

import edu.CSUSM.testTaker.LibraryController;

public class Course implements edu.CSUSM.testTaker.Backend.Registerable{
	String myID;
	{
		myID = java.util.UUID.randomUUID().toString();
	}
	
	/**
	 * Get the unique identifier string of this Course
	 * @return A string unique to this particular Course
	 * @override
	 */
	public String getID(){
		return myID;
	}
	
	//public static int COURSE_COUNT; 					//Keeps a sum of all existing courses. Will count every time the class is init.
	//I think this is a feature of LibraryController
	
	double _courseGrade, _testsCompleted;
	ArrayList<String> questionIDs;
	ArrayList<String> testIDs;
	ArrayList<Integer> testPoints;
	
	String courseName;


	/**
	 * Construct a blank Course.
	 * @author Justin Goulet
	 * @description Default Constructor
	 */
	public Course(){
		_courseGrade = 0.0;
		_testsCompleted = 0.0;		
		
		courseName = "";
		questionIDs = new ArrayList<String>();
		testIDs = new ArrayList<String>();
		testPoints = new ArrayList<Integer>();
	}
	
	/*
	 * Accessors:
	 */
	
	/**
	 * Get the name/title of the Course
	 * @return Course's name as String;
	 */
	public String getName(){
		return courseName;
	}
	
	/**
	 * Get the number of tests in this Course
	 * @return The number of tests as an int
	 */
	public int numTests(){
		return testIDs.size();
	}
	
	/**
	 * Get the ID of a particular test in the Course.
	 * @param index the number of the test (0 base) to retrieve
	 * @return The string ID of that Test.
	 */
	public String getTestID(int index){
		if(index >=0 && index < testIDs.size()){
			return testIDs.get(index);
		}
		return null;		
	}
	
	/**
	 * Get a particular test in the course
	 * @param index the number of the test (0 base) to retrieve
	 * @return A reference to that Test.
	 */
	public Test getTest(int index){
		if(index >=0 && index < testIDs.size()){
			return LibraryController.retrieveTest(testIDs.get(index));
		}
		return null;
	}
	
	/**
	 * Get the ID of a particular question in this Course
	 * @param index The number of the Question (0 base) to retrieve
	 * @return The ID string of that Question
	 */
	public String getQuestionID(int index){
		if(index >=0 && index < questionIDs.size()){
			return questionIDs.get(index);
		}
		return null;
	}


	/**
	 * Get a particular question in this Course
	 * @param index The number of the Question (0 base) to retrieve
	 * @return A reference to that Question
	 */
	public Question getQuestion(int index){
		if(index >=0 && index < questionIDs.size()){
			return LibraryController.retrieveQuestion(questionIDs.get(index));
		}
		return null;
	}
	
	/**
	 * Get the points value of a particular Test
	 * @param index The number of the Test (0 base) to get the value of
	 * @return An int of the number of points ascribed to this Test
	 */
	public int getTestValue(int index){
		if(index >=0 && index < testPoints.size()){
			return testPoints.get(index);
		}
		return 0;
	}

	/**
	 * Get the current grade in this course
	 * @author Justin Goulet
	 * @return The grade of the Course so far.
	 * @deprecated
	 */
	public double getCourseGrade() {
		return this._courseGrade;
	}

	/**
	 * Get the number of tests completed in the course so far.
	 * @author Justin Goulet
	 * @return The number of tests completed
	 * @deprecated
	 */
	public double getTestsCompleted() {
		return this._testsCompleted;
	}

		
	/**
	 * @author Justin Goulet
	 * @description Compiles all of the questions and tests for this particular course
	 * Note that all of the events are happening in the libraryController class
	 * @deprecated
	 */
	private static void compileCourse(){
		
	}
	
	/*
	 * Mutators:
	 */
	
	
	/*
	 * @author Justin Goulet
	 * @param courseID the courseID to set
	 */
	/*
	public void setCourseID(String courseID) {
		this._courseID = courseID;
	}
	*/

	/**
	 * Set the current grade for this course
	 * @author Justin Goulet
	 * @param courseGrade The courseGrade to set
	 * @deprecated
	 */
	public void setCourseGrade(double courseGrade) {
		this._courseGrade = courseGrade;
	}

	/**
	 * Set the number of tests completed in this course.
	 * @author Justin Goulet
	 * @param testsCompleted The number of tests that have been taken so far.
	 * @deprecated
	 */
	public void setTestsCompleted(double testsCompleted) {
		this._testsCompleted = testsCompleted;
	}

	/*
	 * @author Justin Goulet
	 * @return the courseID
	 */
	/*
	public String getCourseID() {
		return this._courseID;
	}*/

	
}
