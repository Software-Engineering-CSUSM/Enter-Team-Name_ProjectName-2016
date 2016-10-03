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

	String myID;
	
	
	/** @brief Get the ID of the test for database storage and retrieval
	 * @return unique ID string of this Test
	 * @author Steven Clark
	 */
	public String getID(){
		return myID;
	}
	
	//For testing purposes
	public HashMap<String, Question> _listOfQuestionsInExam;		//Format: (String testID, Question questionWithIDBuiltIn)
	public String _testName, _courseID;

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
	 * @param testName Name of test
	 * @param listOfQuestionsForTest list of questions to add to the test
	 * @param courseID course to assign the test to
	 * @author Justin Goulet
	 */
	public Test(String testName, ArrayList<Question> listOfQuestionsForTest, String courseID){
		this._testName = testName;
		setQuestionList(listOfQuestionsForTest);
		this._courseID = courseID;
	}
	
	
	/** Accessors */
	/**
	 * @return current hashmap of questions (TestID, Question)
	 */
	public HashMap<String, Question> getQuestions(){
		return this._listOfQuestionsInExam;
	}
	
	/*
	public String getTestID(){
		return this._testID;
	}
	*/
	
	public String getTestName(){
		return this._testName;
	}
	
	public String getCourseID(){
		return this._courseID;
	}
	
	/** Mutators */
	/**
	 * @brief Add a new question to the test.
	 * @param QuestionToAdd A Question to add to the question list for this test.
	 * @param questionvalue An integer number of points to value the question at.
	 */
	public void addQuestion(Question QuestionToAdd, int questionvalue){
		questionList.add(QuestionToAdd);
		questionIDs.add(QuestionToAdd.getID());
		questionPoints.add(questionvalue);
		QuestionToAdd.setTestID(getID());
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
