package edu.CSUSM.testTaker.Backend;

import java.io.Serializable;
import java.util.UUID;
import java.util.HashMap;
import java.util.ArrayList;
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

	protected HashMap<String,ArrayList<Integer>> testAnswerLists;
	protected String associatedCourse;
	
	public double scoreTaken(){
		Course courseRef = LibraryController.retrieveCourse(associatedCourse);
		double numerator = 0;
		double denominator = 0;
		for(String testID : testAnswerLists.keySet()){
			Test tref = LibraryController.retrieveTest(testID);
			ArrayList <Integer>alist = testAnswerLists.get(testID);
			double ptot = (double)courseRef.getTestValue(testID);
			double prcvd = tref.totalPointsScored(alist);
			denominator += ptot;
			numerator += prcvd;
		}
		if(denominator > 0.0)
			return numerator / denominator;
		return 0.0;
	}
}
