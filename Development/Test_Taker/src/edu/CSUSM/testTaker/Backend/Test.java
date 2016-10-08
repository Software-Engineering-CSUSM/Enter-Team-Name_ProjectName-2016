package edu.CSUSM.testTaker.Backend;

import edu.CSUSM.testTaker.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import java.io.Serializable;

public class Test implements Serializable, Registerable{
	static final long serialVersionUID = 1L;

	transient ArrayList<Question> questionList;
	ArrayList<String> questionIDs;
	ArrayList<Integer> questionPoints;
	public String _testName, _courseID;

	String myID;
	
	
	/** @brief Get the ID of the test for database storage and retrieval
	 * @return unique ID string of this Test
	 * @author Steven Clark
	 */
	public String getID(){
		return myID;
	}
	
	//For testing purposes
	//public HashMap<String, Question> _listOfQuestionsInExam;		//Format: (String testID, Question questionWithIDBuiltIn)

	/**
	 * @description does the work of setting up the class regardless of what vars are passed
	 */
	{
		_testName = null;
		myID = UUID.randomUUID().toString();
		//_listOfQuestionsInExam = new HashMap<String,Question>();
		questionList = new ArrayList<Question>();
		questionIDs = new ArrayList<String>();
		questionPoints = new ArrayList<Integer>();
	}

	
	/**
	 * @param testName The name of the test we just created
	 * @author Justin Goulet
	 */
	public Test(String testName){
		this._testName = testName;
	}
	
	/**
	 * @param testName Name of test
	 * @param listOfQuestionsForTest list of questions to add to the test
	 * @author Justin Goulet
	 */
	public Test(String testName, ArrayList<Question> listOfQuestionsForTest){
		this._testName = testName;
		setQuestionList(listOfQuestionsForTest);
	}
	
	/**
	 * @param testName Title of the test
	 * @param listOfQuestionsForTest An ArrayList of Questions to add to the test
	 * @param courseID ID of course that the test is associated with.
	 * @author Justin Goulet
	 */
	public Test(String testName, ArrayList<Question> listOfQuestionsForTest, String courseID){
		this._testName = testName;
		setQuestionList(listOfQuestionsForTest);
		this._courseID = courseID;
	}
	
	
	/** Accessors */
	
	/**
	 * @brief Get an array of questions in the test.
	 * @return The questions in the test, in order, at this moment in time.
	 */
	public Question[] getQuestions(){
		return questionList.toArray(null);
	}
	
	/**
	 * Get a question from the test.
	 * @param qn The index of the question to retrieve (0-indexed)
	 * @return Reference to the question number asked for.
	 */
	public Question getQuestion(int qn){
		return questionList.get(qn);
	}

	/**
	 * Get the number of questions presently in this test.
	 * @return The length of the container of questions.
	 */
	public int numQuestions(){
		return this.questionIDs.size();
	}
	
	/**
	 * Get the points value of a particular test question
	 * @param qn The index of the question to retrieve (0-indexed)
	 * @return The value of the question within this test in points.
	 */
	public int getQuestionPoints(int qn){
		return questionPoints.get(qn);
	}
	
	/**
	 * Get title of the test.
	 * @return The name of this test.
	 */
	public String getTestName(){
		return this._testName;
	}
	
	/**
	 * Get the associated course.
	 * @return The identifier string of the course this test is filed u
	 */
	public String getCourseID(){
		return this._courseID;
	}
	
	/** Mutators */
	
	/**
	 * Allows client to set the points value of a particular question of the test.
	 * @param qn The index (0-based) of the question to set the value of.
	 * @param qp The number of points to value the question at.
	 * @return true for success
	 */
	public boolean setQuestionPoints(int qn, int qp){
		if(questionPoints.size() < qn && qn >= 0){
			questionPoints.set(qn, qp);
			LibraryController.storeTest(this);
			return true;
		}
		LibraryController.storeTest(this);
		return false;
	}
	
	/**
	 * @brief Add a new question to the test.
	 * @param QuestionToAdd A Question to add to the question list for this test.
	 * @param questionvalue An integer number of points to value the question at.
	 */
	public void addQuestion(Question QuestionToAdd, int questionvalue){
		questionList.add(QuestionToAdd);
		questionIDs.add(QuestionToAdd.getID());
		questionPoints.add(questionvalue);
		//QuestionToAdd.setTestID(getID());
		LibraryController.storeTest(this);		
	}

	/**
	 * @brief Add a new question to the test.
	 * @param QuestionToAdd A Question to add to the question list for this test.
	 */
	public void addQuestion(Question QuestionToAdd){
		questionList.add(QuestionToAdd);
		questionIDs.add(QuestionToAdd.getID());
		questionPoints.add(0);
		QuestionToAdd.setTestID(getID());
		LibraryController.storeTest(this);		
	}
	
	
	
	public void setTestName(String newTestName){
		this._testName = newTestName;
		LibraryController.storeTest(this);
	}
	
	/*
	public void setTestID(String newTestID){
		this._testID = newTestID;
	}
	*/
	
	public void setCourseID(String newCourseID){
		this._courseID = newCourseID;
		LibraryController.storeTest(this);		
	}
	
	
	/**
	 * Utility function sets/resets the list of questions
	 * @param newQuestionList an ArrayList of Question refs to insert
	 */
	public void setQuestionList(ArrayList<Question> newQuestionList){
		_listOfQuestionsInExam.clear();
		
		//Iterate through the list and add to the question map. we are going to add the test ID to each of the questions.
		for(Question tempQuestion : newQuestionList){
			
			//Print out the question
			System.out.println(tempQuestion.toString());
			
			//Print out the TEstID
			System.out.println("Question ID: " + tempQuestion);
			
			tempQuestion.setTestID(getID());
			this.questionList.add(tempQuestion);
			this.questionIDs.add(tempQuestion.getID());
		}
		LibraryController.storeTest(this);
	}
	
	/** @author John Orcino
	 * PARAMETER: May need one
	 * FUNCTION: gets the Id for the question to have the array of questions and answers
	 */
	public void getQuestionIDs(){
		
	}
	
	/** @author John Orcino
	 * 	FUNCTION: adds up the total points of the test and returns it
	 */
	public int totalPointsScored(){
		int tot = 0;
		
		//iterator adding up the question points 
		
		return tot;
	}
	
	/*	@Author: John Orcino
	 * 	@PARAMETERS:
	 * 	@FUNCTION: Display the question and answers, stores the user's answer
	 * 
	 */
	
	public void testDisplay(){
		//iterator that uses the array of answers and questions and displays them
	}
	
	
	
	/**
	 *  (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @decription overrides the default 'toString()' to showcase a basic exam with all questions included.
	 */
	@Override
    public String toString(){
        
        //Create the initial question
        String thisTestString = "Test: " + this._testName;
        
        System.out.println("Question Size: " + _listOfQuestionsInExam);
                
        if(_listOfQuestionsInExam != null && _listOfQuestionsInExam.size() > 0){
        	//Now, add each of the possible answers in the provided question
            for(Question tempQuestion : _listOfQuestionsInExam.values()){
            	thisTestString += "\n\t" + tempQuestion;
            }
        }else{
        	return "No questions yet in test: " + thisTestString + "\n";
        }
        
        //Return the result
        return thisTestString;
    }

}
