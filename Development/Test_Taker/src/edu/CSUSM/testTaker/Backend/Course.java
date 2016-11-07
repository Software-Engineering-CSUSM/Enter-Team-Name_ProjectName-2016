package edu.CSUSM.testTaker.Backend;

import java.util.ArrayList;
import java.io.Serializable;

import edu.CSUSM.testTaker.LibraryController;

public class Course extends TaskObject implements Serializable, edu.CSUSM.testTaker.Backend.Registerable{
	static final long serialVersionUID = 1L;

	/**  Get the ID of the test for database storage and retrieval
	 * @return unique ID string of this Course
	 * @author Steven Clark
	 */
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
		this.currentID = getID();
		this.currentName = courseName;
	}
	
	/** Make an example Course
	 * @return A reference to an example Course, a Monty Python reference
	 */
	public static Course makeExample(){
		Course rval = new Course();
		
		rval.setName("The Quest for the Holy Grail");
		rval.addTest(Test.makeExample(), 100);
		
		return rval;
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
	 * Get the number of Questions in this Course
	 * @return The number of Questions as an int
	 */
	public int numQuestions(){
		return questionIDs.size();
	}
	
	/**
	 * Return a static view of all Questions currently in the Course
	 * @return An ArrayList of ID Strings of Questions
	 */
	@SuppressWarnings("unchecked")
	public ArrayList <String> allQuestions(){
		return (ArrayList <String>)questionIDs.clone();
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
	 * @return An int of the number of points ascribed to this Test, negative if not found.
	 */
	public int getTestValue(int index){
		if(index >=0 && index < testPoints.size()){
			return testPoints.get(index);
		}
		return -1;
	}
	
	/**
	 * Return the points value of a particular Test given it's ID.
	 * @param TID the unique ID string of the Test to find.
	 * @return An int number of points ascribed to the Test in this Course, negative if not found.
	 */
	public int getTestValue(String TID){
		int tindex = this.testIDs.indexOf(TID);
		return getTestValue(tindex);
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
	
	public String toString(){
		//String outString = "Course: ";
		String outString = "";
		
		outString = outString + courseName + "\n";
		outString = outString + ((Integer)testIDs.size()).toString() + " tests:\n";
		
		for(String testID : testIDs){
			Test testRef = LibraryController.previewTest(testID);
			outString = outString + testRef.getTestName() + "\n";
		}
		
		return outString;
	}
	
	/*
	 * Mutators:
	 */
	
	/**
	 * Set the name/title of the Course.
	 * @param newname A String of the new name for this Course.
	 */
	public void setName(String newname){
		this.courseName = newname;
		this.currentName = courseName;
		LibraryController.storeCourse(this);
	}

	/**
	 * Adds a Question to the list of Questions for this course, if it is not already in the list
	 * @param nqID An ID string for a Question to add to the Course.
	 */
	public void addQuestion(String nqID){
		if(! questionIDs.contains(nqID) )
			questionIDs.add(nqID);
	}

	/**
	 * Adds a Question to the list of Questions for this course, if it is not already in the list
	 * @param nq A reference to a Question to add to the Course.
	 */
	public void addQuestion(Question nq){
		addQuestion(nq.getID());
	}
	
	/**
	 * Imports all new questions from a given test into this course.
	 * @param nt A Test to add the Questions of to the Course.
	 */
	public void addTestsQuestions(Test nt){
		for(String tqid : nt.getQuestionIDs()){
			addQuestion(tqid);
		}
	}
	
	/**
	 * Add a test to the end of this Course
	 * @param newtest A reference to the Test to add
	 * @param newpoints An int number of points to assign to said new test
	 */
	public void addTest(Test newtest, int newpoints){
		this.testIDs.add(newtest.getID());
		this.testPoints.add(newpoints);
		for(int i = 0; i < newtest.numQuestions(); ++i){
			addQuestion(newtest.getQuestion(i));
		}
		LibraryController.storeCourse(this);
	}
	
	/**
	 * Add a test to the end of this Course
	 * @param newtest A reference to the Test to add
	 */
	public void addTest(Test newtest){
		addTest(newtest, 0);
		//LibraryController.storeCourse(this);//implied
	}

	/**
	 * Add a test to the end of this Course
	 * @param newtest The ID String for the Test to add
	 * @param newpoints An int number of points to assign to said new test
	 */
	public void addTest(String newtestid, int newpoints){
		Test newtest = LibraryController.retrieveTest(newtestid);
		if(newtestid != null && newtest != null){
			this.testIDs.add(newtestid);
			this.testPoints.add(newpoints);
			for(int i = 0; i < newtest.numQuestions(); ++i){
				addQuestion(newtest.getQuestion(i));
			}			
			LibraryController.storeCourse(this);
		}
	}
	
	/**
	 * Add a test to the end of this Course
	 * @param newtest The ID String for the Test to add
	 */
	public void addTest(String newtestid){
		addTest(newtestid, 0);
		//LibraryController.storeCourse(this);//implied
	}
	
	
	/**
	 * Remove A test or question from this course
	 * @param removethis The ID string of the Test or Question to remove.
	 */
	public void remove(String removethis){
		int tindex = testIDs.indexOf(removethis);
		if(tindex != -1){
			removeTestNum(tindex);
		}
		questionIDs.remove(removethis);
		LibraryController.storeCourse(this);
	}
	
	/**
	 * Remove a Test from this course
	 * @param removethis A reference to the test to be removed
	 */
	public void remove(Test removethis){
		testPoints.remove(testIDs.indexOf(removethis.getID()));
		remove(removethis.getID());
		LibraryController.storeCourse(this);
	}
	
	/**
	 * Remove a Question from this course
	 * @param removethis A reference to the Question to be removed.
	 * @note Does not currently remove the Question from Tests.
	 */
	public void remove(Question removethis){
		remove(removethis.getID());
		LibraryController.storeCourse(this);
	}
	
	/**
	 * Remove a numbered test from the Course
	 * @param testnum An int (0 based) number for the Test to remove
	 */
	public void removeTestNum(int testnum){
		testPoints.remove(testnum);
		testIDs.remove(testnum);
		LibraryController.storeCourse(this);
	}
	
	/**
	 * Remove a numbered Question from the Course
	 * @param qnum An int (0 based) number for the Question to remove
	 */
	public void removeQuestionNum(int qnum){
		questionIDs.remove(qnum);
		LibraryController.storeCourse(this);
	}
	
	/**
	 * Insert a Test at a particular location within the Course
	 * @param insertthis A reference to the Test to insert
	 * @param points An int number of points to assign to the Test
	 * @param tn The int index at which to place the Test
	 * @note This operation can be combined with a remove operation to change the order of a Test in the Course.
	 */
	public void insertTest(Test insertthis, int points, int tn){
		testIDs.add(tn, insertthis.getID());
		testPoints.add(tn,points);
		LibraryController.storeCourse(this);
	}
	
	/**
	 * Add a Question somewhere in the interior of the list of Questions
	 * @param insertthis An ID string for a Question to add to the Course
	 * @param qn An int index at which to insert the Question
	 * @note Will not store duplicates.
	 */
	public void insertQuestion(String insertthis, int qn){
		if(! questionIDs.contains(insertthis) ){
			questionIDs.add(qn, insertthis);
			LibraryController.storeCourse(this);
		}
	}
	
	/**
	 * Utility function store changes to this Course to the LibraryController
	 * @note Currently unnecessary
	 */
	public void flush(){
		LibraryController.storeCourse(this);
	}

	/**
	 * Set the current grade for this course
	 * @author Justin Goulet
	 * @param courseGrade The courseGrade to set
	 * @deprecated
	 */
	public void setCourseGrade(double courseGrade) {
		this._courseGrade = courseGrade;
		LibraryController.storeCourse(this);
	}

	/**
	 * Set the number of tests completed in this course.
	 * @author Justin Goulet
	 * @param testsCompleted The number of tests that have been taken so far.
	 * @deprecated
	 */
	public void setTestsCompleted(double testsCompleted) {
		this._testsCompleted = testsCompleted;
		LibraryController.storeCourse(this);
	}
}
