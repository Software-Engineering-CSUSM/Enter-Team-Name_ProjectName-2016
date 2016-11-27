package edu.CSUSM.testTaker.Backend;

import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import edu.CSUSM.testTaker.LibraryController;
import edu.CSUSM.testTaker.Analytics.AnaSetup;

public class CourseProgress implements Registerable, Serializable {
	static final long serialVersionUID = 1l;

	private  UUID myID;
	{
		myID = java.util.UUID.randomUUID();
	}
	
	public String getID() {
		if(myID != null)
			return myID.toString();
		return null;
	}
	
	public String getName(){
		return name;
	}

	public String getTypeName(){
		return "CourseProgress";
	}
	
	public void turnIntoDuplicate(){
		myID = java.util.UUID.randomUUID();
	}

	
	String name;
	HashMap<String,ArrayList<Integer>> testAnswerLists;
	String associatedCourse;
	double totalGrade;
	double takenGrade;
	
	/**
	 * Construct a new CourseProgress for the given course
	 * @param courseID The ID string of the course being taken
	 */
	public CourseProgress(String courseID){
		testAnswerLists = new HashMap<String,ArrayList<Integer>>();
		associatedCourse = courseID;
		totalGrade = 0.0;
		takenGrade = 0.0;
		name = "User's work on " + LibraryController.getItemName(associatedCourse);
		try{AnaSetup.logEvent("Constructed a new CourseProgress");}
		catch(IOException e){e.printStackTrace();}
	}
	
	/*
	 * Accessors
	 */
	
	@SuppressWarnings("unchecked")
	/**
	 * Get the answers for a test that were stored
	 * @param testID The ID String of a Test
	 * @return an ArrayList of answer numbers for the questions of that test.
	 */
	public ArrayList<Integer> answersForTest(String testID){
		return (ArrayList<Integer>)testAnswerLists.get(testID).clone();
	}
	
	/**
	 * Get a list of all tests that have answers recorded.
	 * @return an ArrayList of ID Strings of Tests
	 */
	public ArrayList<String> takenTests(){
		ArrayList <String> rList = new ArrayList <String> (testAnswerLists.size());
		
		for(String testID : testAnswerLists.keySet()){
			rList.add(testID);
		}
		
		return rList;
	}
	
	/**
	 * Have answers to a given Test been recorded.
	 * @param testID ID String of the Test
	 * @return true if answers are stored here, false if not
	 */
	public boolean hasBeenTaken(String testID){
		return testAnswerLists.containsKey(testID);
	}
	
	/**
	 * Get the last grade calculated for all tests in the course
	 * @return A cached weighted average of Test scores.
	 */
	public double storedCompleteGrade(){
		return totalGrade;
	}
	
	/**
	 * Get the last grade calculated for only the Tests taken.
	 * @return A cached weighted average of Test scores.
	 */
	public double storedPartialGrade(){
		return takenGrade;
	}
	
	/*
	 * Mutators
	 */
	
	/**
	 * Store a list of answers to a test.
	 * @param testID The ID String of the Test taken.
	 * @param answers A List of answers to the Questions of that test.
	 */
	public void addTestAnswers(String testID, List <Integer> answers){
		ArrayList<Integer> newValue = new ArrayList<Integer>(answers.size());
		for(Integer i : answers){
			newValue.add(i);
		}
		testAnswerLists.put(testID, newValue);
		this.flush();
		try{AnaSetup.logEvent("Added a set of Test Answers to a CourseProgress");}
		catch(IOException e){e.printStackTrace();}

	}
	
	/**
	 * Remove a record of test answers for a particular test.
	 * @param testID The unique ID string of the test to forget taking.
	 */
	public void removeAnswersTo(String testID){
		testAnswerLists.remove(testID);
		this.flush();
	}
	
	/*
	 * Scoring Functions
	 */
	
	/**
	 * Give a score for only the completed tests of the course.
	 * @return A weighted average percentage of all test scores
	 */
	public double scoreTaken(){
		Course courseRef = (Course)LibraryController.getItem(associatedCourse);
		double numerator = 0;
		double denominator = 0;
		for(String testID : testAnswerLists.keySet()){
			Test tref = (Test)LibraryController.getItem(testID);
			ArrayList <Integer>alist = testAnswerLists.get(testID);
			double ptot = (double)courseRef.getTestValue(testID);
			double prcvd = tref.scoreAnswers(alist) * ptot;
			denominator += ptot;
			numerator += prcvd;
		}
		if(denominator > 0.0)
			return numerator / denominator;
		try{AnaSetup.logEvent("Scored all tests taken in a CourseProgress");}
		catch(IOException e){e.printStackTrace();}
		return 0.0;
	}
	
	/**
	 * Give a score for all work on a course including untaken tests as zero.
	 * @return A weighted average percentage of all test scores
	 */
	public double scoreAll(){
		Course courseRef = (Course)LibraryController.getItem(associatedCourse);
		double numerator = 0;
		double denominator = 0;
		for(int i = 0; i<courseRef.numTests(); i++){
			String testID = courseRef.getTestID(i);
			Test tref = courseRef.getTest(i);
			ArrayList <Integer>alist = testAnswerLists.get(testID);
			double ptot = (double)courseRef.getTestValue(i);
			double prcvd;
			if(alist != null)
				prcvd = tref.scoreAnswers(alist) * ptot;
			else
				prcvd = 0.0;
			denominator += ptot;
			numerator += prcvd;
		}
		if(denominator > 0.0)
			return numerator / denominator;
		try{AnaSetup.logEvent("Scored all tests taken and untaken in a CourseProgress");}
		catch(IOException e){e.printStackTrace();}
		return 0.0;
	}
	
	/**
	 * Calculate and store the current course grade, both complete and taken-only.
	 */
	public void rescore(){
		takenGrade = scoreTaken();
		totalGrade = scoreAll();
		this.flush();
	}
}
