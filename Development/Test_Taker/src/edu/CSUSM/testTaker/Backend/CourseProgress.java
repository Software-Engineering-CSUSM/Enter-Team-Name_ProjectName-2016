package edu.CSUSM.testTaker.Backend;

import java.io.Serializable;
import java.util.UUID;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.AbstractList;
import edu.CSUSM.testTaker.LibraryController;

public class CourseProgress implements Registerable, Serializable {
	static final long serialVersionUID = 1l;

	private  UUID myID;
	{
		myID = java.util.UUID.randomUUID();
	}
	
	@Override
	public String getID() {
		if(myID != null)
			return myID.toString();
		return null;
	}
	
	public String getName(){
		return name;
	}

	String name;
	HashMap<String,ArrayList<Integer>> testAnswerLists;
	String associatedCourse;
	double totalGrade;
	double takenGrade;
	
	public CourseProgress(String courseID){
		testAnswerLists = new HashMap<String,ArrayList<Integer>>();
		associatedCourse = courseID;
		totalGrade = 0.0;
		takenGrade = 0.0;
		name = "Unknown Student's work on " + LibraryController.retrieveCourse(courseID).getName();
	}
	
	/*
	 * Accessors
	 */
	
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> answersForTest(String testID){
		return (ArrayList<Integer>)testAnswerLists.get(testID).clone();
	}
	
	public ArrayList<String> takenTests(){
		ArrayList <String> rList = new ArrayList <String> (testAnswerLists.size());
		
		for(String testID : testAnswerLists.keySet()){
			rList.add(testID);
		}
		
		return rList;
	}
	
	public boolean hasBeenTaken(String testID){
		return testAnswerLists.containsKey(testID);
	}
	
	public double storedCompleteGrade(){
		return totalGrade;
	}
	
	public double storedPartialGrade(){
		return takenGrade;
	}
	
	/*
	 * Mutators
	 */
	
	public void addTestAnswers(String testID, AbstractList <Integer> answers){
		ArrayList<Integer> newValue = new ArrayList<Integer>(answers.size());
		for(Integer i : answers){
			newValue.add(i);
		}
		testAnswerLists.put(testID, newValue);
		LibraryController.storeProgress(this);
	}
	
	public void removeAnswersTo(String testID){
		testAnswerLists.remove(testID);
		LibraryController.storeProgress(this);
	}
	
	/*
	 * Scoring Functions
	 */
	
	public double scoreTaken(){
		Course courseRef = LibraryController.retrieveCourse(associatedCourse);
		double numerator = 0;
		double denominator = 0;
		for(String testID : testAnswerLists.keySet()){
			Test tref = LibraryController.retrieveTest(testID);
			ArrayList <Integer>alist = testAnswerLists.get(testID);
			double ptot = (double)courseRef.getTestValue(testID);
			double prcvd = tref.scoreAnswers(alist) * ptot;
			denominator += ptot;
			numerator += prcvd;
		}
		if(denominator > 0.0)
			return numerator / denominator;
		return 0.0;
	}
	
	public double scoreAll(){
		Course courseRef = LibraryController.retrieveCourse(associatedCourse);
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
		return 0.0;
	}
	
	public void rescore(){
		takenGrade = scoreTaken();
		totalGrade = scoreAll();
		LibraryController.storeProgress(this);
	}
}
