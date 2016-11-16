package edu.CSUSM.testTaker.Backend;

import java.util.ArrayList;
import java.util.UUID;
import java.io.Serializable;

import edu.CSUSM.testTaker.LibraryController;

public class Course /*extends TaskObject*/ implements Serializable, edu.CSUSM.testTaker.Backend.Registerable{
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
	 * {@inheritDoc}
	 */
	public String getID(){
		return myID;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getTypeName(){
		return "Course";
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void turnIntoDuplicate(){
		myID = UUID.randomUUID().toString();
	}

	
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
		courseName = "New Course";
		questionIDs = new ArrayList<String>();
		testIDs = new ArrayList<String>();
		testPoints = new ArrayList<Integer>();
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
	public ArrayList <String> getQuestionIDs(){
		ArrayList <String>rval = new ArrayList <String>(questionIDs.size());
		rval.addAll(questionIDs);
		return rval;
	}
	
	/**
	 * Give a particular Question's name from the course.
	 * @param index an int index for the question
	 * @return a name String for that Question
	 */
	public String getQuestionName(int index){
		return LibraryController.getItemName(questionIDs.get(index));
	}
	
	/**
	 * Give a list of names of Questions in the Course.
	 * @return An ArrayList of name Strings
	 */
	public ArrayList <String> getQuestionNames(){
		return LibraryController.getNamesForIDs(questionIDs);
	}
	
	
	/**
	 * Return a static view of all Questions currently in the Course
	 * @return An ArrayList of Questions
	 */
	public ArrayList <Question> getQuestions(){
		ArrayList <Question> rval = new ArrayList <Question>(questionIDs.size());
		for(String qid : questionIDs){
			rval.add((Question)LibraryController.getItem(qid));
		}
		return rval;
	}

	
	/**
	 * Return a static view of all Tests currently in the Course
	 * @return An ArrayList of ID Strings of Tests
	 */
	public ArrayList <String> getTestIDs(){
		ArrayList <String>rval = new ArrayList <String>(testIDs.size());
		rval.addAll(testIDs);
		return rval;
	}
	
	/**
	 * Get a list of names of Tests in this Course
	 * @return an ArrayList of name Strings of Tests
	 */
	public ArrayList <String> getTestNames(){
		return LibraryController.getNamesForIDs(testIDs);
	}
	
	/**
	 * Get the name of a Test number
	 * @param index an index number of a Test
	 * @return a name String of the test
	 */
	public String getTestName(int index){
		return LibraryController.getItemName(testIDs.get(index));
	}
	
	/**
	 * Return a static view of all Tests currently in the Course
	 * @return An ArrayList of Tests
	 */
	public ArrayList <Test> getTests(){
		ArrayList <Test> rval = new ArrayList <Test>(testIDs.size());
		for(String tid : testIDs){
			rval.add((Test)LibraryController.getItem(tid));
		}
		return rval;
	}
	
	/**
	 * Give a listing of the current points values of all Tests in this Course
	 * @return an ArrayList of Integers
	 */
	public ArrayList <Integer> getTestValues(){
		ArrayList <Integer>rval = new ArrayList <Integer>(testPoints.size());
		rval.addAll(testPoints);
		return rval;
		
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
			return (Test)LibraryController.getItem(testIDs.get(index));
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
			return (Question)LibraryController.getItem(questionIDs.get(index));
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

	public String toString(){
		//String outString = "Course: ";
		String outString = "";
		
		outString = outString + courseName + "\n";
		outString = outString + ((Integer)testIDs.size()).toString() + " tests:\n";
		
		for(String testID : testIDs){
			Test testRef = LibraryController.previewTest(testID);
			outString = outString + testRef.getName() + "\n";
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
		this.flush();
	}

	/**
	 * Adds a Question to the list of Questions for this course, if it is not already in the list
	 * @param nqID An ID string for a Question to add to the Course.
	 */
	public void addQuestion(String nqID){
		if(! questionIDs.contains(nqID) )
			questionIDs.add(nqID);
		this.flush();
	}

	/**
	 * Adds a Question to the list of Questions for this course, if it is not already in the list
	 * @param nq A reference to a Question to add to the Course.
	 */
	public void addQuestion(Question nq){
		addQuestion(nq.getID());
		this.flush();
	}
	
	/**
	 * Imports all new questions from a given test into this course.
	 * @param nt A Test to add the Questions of to the Course.
	 */
	public void addTestsQuestions(Test nt){
		for(String tqid : nt.getQuestionIDs()){
			addQuestion(tqid);
		}
		this.flush();
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
		this.flush();
	}
	
	/**
	 * Add a test to the end of this Course
	 * @param newtest A reference to the Test to add
	 */
	public void addTest(Test newtest){
		addTest(newtest, 0);
		this.flush();
	}

	/**
	 * Add a test to the end of this Course
	 * @param newtest The ID String for the Test to add
	 * @param newpoints An int number of points to assign to said new test
	 */
	public void addTest(String newtestid, int newpoints){
		Test newtest = (Test)LibraryController.getItem(newtestid);
		if(newtestid != null && newtest != null){
			this.testIDs.add(newtestid);
			this.testPoints.add(newpoints);
			for(int i = 0; i < newtest.numQuestions(); ++i){
				addQuestion(newtest.getQuestion(i));
			}			
			this.flush();
		}
	}
	
	/**
	 * Add a test to the end of this Course
	 * @param newtest The ID String for the Test to add
	 */
	public void addTest(String newtestid){
		addTest(newtestid, 0);
		this.flush();
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
		this.flush();
	}
	
	/**
	 * Remove a Test from this course
	 * @param removethis A reference to the test to be removed
	 */
	public void remove(Test removethis){
		testPoints.remove(testIDs.indexOf(removethis.getID()));
		remove(removethis.getID());
		this.flush();
	}
	
	/**
	 * Remove a Question from this course
	 * @param removethis A reference to the Question to be removed.
	 * @note Does not currently remove the Question from Tests.
	 */
	public void remove(Question removethis){
		remove(removethis.getID());
		this.flush();
	}
	
	/**
	 * Remove a numbered test from the Course
	 * @param testnum An int (0 based) number for the Test to remove
	 */
	public void removeTestNum(int testnum){
		testPoints.remove(testnum);
		testIDs.remove(testnum);
		this.flush();
	}
	
	/**
	 * Remove a numbered Question from the Course
	 * @param qnum An int (0 based) number for the Question to remove
	 */
	public void removeQuestionNum(int qnum){
		questionIDs.remove(qnum);
		this.flush();
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
		this.flush();
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
			this.flush();
		}
	}
	
}
