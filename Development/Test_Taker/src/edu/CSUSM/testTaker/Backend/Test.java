package edu.CSUSM.testTaker.Backend;

import edu.CSUSM.testTaker.*;

import java.util.ArrayList;
import java.util.AbstractList;
import java.util.UUID;

import java.io.Serializable;

public class Test /*extends TaskObject*/ implements Serializable, Registerable{
	static final long serialVersionUID = 1L;

	transient ArrayList<Question> questionList;
	ArrayList<String> questionIDs;
	ArrayList<Integer> questionPoints;
	String _testName;
	String myID;

	public String getID(){
		return myID;
	}
	
	public String getTypeName(){
		return "Test";
	}
	
	public void turnIntoDuplicate(){
		myID = UUID.randomUUID().toString();
	}

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

	/** Initialize an untitled blank Test
	 */
	public Test(){
		//mostly handled by initializer block
	}

	/**
	 * Initialize blank test with only a Title
	 * @param testName The name of the test we just created
	 * @author Justin Goulet
	 */
	public Test(String testName){
		this._testName = testName;
		//this.currentName = testName;
	}

	/**
	 * Initialize from title and List of Questions
	 * @param testName Name of test
	 * @param listOfQuestionsForTest list of questions to add to the test
	 * @author Justin Goulet
	 */
	public Test(String testName, AbstractList<Question> listOfQuestionsForTest){
		this._testName = testName;
		//this.currentID = getID();
		//this.currentName = testName;
		setQuestionList(listOfQuestionsForTest);
	}

	/**
	 * Initialize a new test from a Title and a list of question ID strings.
	 * @param testName The title of the new test
	 * @param qids The question ids to import into the test
	 */
	public Test(String testName, String ... qids){
		this._testName=testName;
		for(String qid : qids){
			questionIDs.add(qid);
		}
		initQuestions();
	}

	/** Make an example Test
	 * @return A reference to a Test, a Monty Python reference
	 */
	public static Test makeExample(){
		Test rval = new Test();
		rval.setName("The sorceror ... Tim!");
		rval.addQuestion(Question.makeExample(),1);

		Question q2 = new Question("What! is your favorite color?");
		q2.addAnswer("green");
		q2.addAnswer("blue");
		q2.addAnswer("aaaaaah");
		q2.setCorrectIndex(2);
		rval.addQuestion(q2,1);

		Question q3 = new Question("What! is the average landspeed of an unladended swallow?");
		q3.addAnswer("African or European?");
		q3.addAnswer("I don't know that!");
		q3.addAnswer("aaaaahhhhh");
		q3.setCorrectIndex(0);
		rval.addQuestion(q3, 20);

		return rval;
	}

	/** Accessors */

	/**
	 * Give identifier strings of all questions in Test.
	 * @return An array with the IDs of all Questions in this Test.
	 */
	public String[] getQuestionIDs(){
		return questionIDs.toArray(new String[questionIDs.size()]);
	}
	
	/**
	 * Give identifier strings of all questions in Test.
	 * @return An ArrayList with the ID Strings of all Questions in this Test.
	 */
	public ArrayList <String> getQuestionIDList(){
		return new ArrayList<String>(questionIDs);
	}


	/**
	 *  Get an array of questions in the test.
	 * @return The questions in the test, in order, at this moment in time.
	 */
	public Question[] getQuestions(){
		return questionList.toArray(new Question[questionList.size()]);
	}
	
	/**
	 * Get a list of all Questions in the test
	 * @return The questions in the test, in order, at this moment in time in an ArrayList.
	 */
	public ArrayList<Question> getQuestionList(){
		return new ArrayList<Question>(questionList);
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
	 * Get the ID of a particular Question
	 * @param index the index of the Question to retrieve the ID of
	 * @return a unique ID String for retrieving the Question from the Library
	 */
	public String getQuestionID(int index){
		return questionIDs.get(index);
	}
	
	
	/**
	 * Get the Name of a particular question
	 * @param index the index of the Question to retrieve the name of
	 * @return the name String of the Question
	 */
	public String getQuestionName(int index){
		return LibraryController.getItemName(questionIDs.get(index));
	}
	
	/**
	 * Get a list of the "names" of all the Questions
	 * @return an ArrayList of name Strings
	 */
	public ArrayList <String> getQuestionNames(){
		return LibraryController.getNamesForIDs(questionIDs);
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
	 * Get a list of the points values for all Questions in the Test in order
	 * @return an ArrayList of Integers
	 */
	public ArrayList<Integer> getQuestionPoints(){
		return new ArrayList<Integer>(questionPoints);
	}

	/**
	 * Get title of the test.
	 * @return The name of this test.
	 */
	public String getName(){
		return this._testName;
	}
	
	/**
	 * Get title of the test.
	 * @return The name of this test.
	 */
	public String getTestName(){
		return getName();
	}

	

	/** Mutators */

	/**
	 * Utility function to initialize reference array and optionally points from just Question IDs.
	 */
	public void initQuestions(){
		questionList = (ArrayList)LibraryController.getItemsForIDs(questionIDs);
		if(questionPoints == null || questionPoints.size() != questionIDs.size()){
			questionPoints = new ArrayList<Integer>(questionIDs.size());
			for(String tempID : questionIDs){
				questionPoints.add(0);
			}
			this.flush();
		}
	}

	/**
	 * Allows client to set the points value of a particular question of the test.
	 * @param qn The index (0-based) of the question to set the value of.
	 * @param qp The number of points to value the question at.
	 * @return true for success
	 */
	public boolean setQuestionPoints(int qn, int qp){
		if(questionPoints.size() < qn && qn >= 0){
			questionPoints.set(qn, qp);
			this.flush();
			return true;
		}
		return false;
	}

	/**
	 *  Add a new question to the test.
	 * @param QuestionToAdd A Question to add to the question list for this test.
	 * @param questionvalue An integer number of points to value the question at.
	 */
	public void addQuestion(Question QuestionToAdd, int questionvalue){
		questionList.add(QuestionToAdd);
		questionIDs.add(QuestionToAdd.getID());
		questionPoints.add(questionvalue);
		this.flush();
	}

	/**
	 *  Add a new question to the test.
	 * @param QuestionToAdd A Question to add to the question list for this test.
	 */
	public void addQuestion(Question QuestionToAdd){
		questionList.add(QuestionToAdd);
		questionIDs.add(QuestionToAdd.getID());
		questionPoints.add(0);
		this.flush();
	}


	/**
	 * Remove a particular question number from the test.
	 * @param qn The index of the question to remove.
	 */
	public void removeQuestion(int qn){
		questionList.remove(qn);
		questionIDs.remove(qn);
		questionPoints.remove(qn);
		this.flush();
	}

	/**
	 * Insert a Question into the test at a particular index.
	 * @param insertit A reference to the Question to insert
	 * @param points The number of points to assign to the Question
	 * @param qi The index at which to insert the Question.
	 * @note This operation along with {@link #removeQuestion(int) removeQuestion()} should allow re-ordering of Test questions.
	 */
	public void insertQuestion(Question insertit,int points, int qi){
		questionList.add(qi, insertit);
		questionIDs.add(qi,insertit.getID());
		questionPoints.add(qi,points);
		this.flush();
	}

	/**
	 * Set the title/name of this Test	
	 * @param newTestName A String of the new name for this Test
	 */
	public void setName(String newTestName){
		this._testName = newTestName;
		this.flush();
	}


	/**
	 * Utility function sets/resets the list of questions
	 * @param newQuestionList an array of Question refs to insert
	 */
	public void setQuestionList(Question[] newQuestionList){
		questionList.clear();
		questionIDs.clear();
		questionPoints.clear();

		//Iterate through the list and add to the question map. we are going to add the test ID to each of the questions.
		for(Question tempQuestion : newQuestionList){			
			questionList.add(tempQuestion);
			questionIDs.add(tempQuestion.getID());
			questionPoints.add(0);
		}
		this.flush();
	}


	/**
	 * Gets the percentage scored on a test given an array of student answers.
	 * @author John Orcino
	 * @param ansAry An array of ints for Test Question answer numbers.
	 * @return An average of the scores for all Questions weighted by their points values.
	 */
	public double scoreAnswers(int [] ansAry){
		double weightAverage = 0.0;		//total points from the formula	
		double sumOfPoints = 0.0;		//total points in the test
		double pointValue = 0.0;		//total points of the user's correct answers
		double tempPoints = 0.0;   		//get the point that is set in the questionPoint array index
		double tempValue = 0.0;			//get the point that is set in the answerPoint array index

		/**
		 * iterator
		 * @description goes through the array list and adds up the sum of points
		 * and the point values 
		 */
		for(int x = 0; x < questionPoints.size(); x++)
		{
			tempPoints = this.questionPoints.get(x);
			tempValue = questionList.get(x).scoreAnswer(ansAry[x]);

			sumOfPoints = sumOfPoints + tempPoints;
			pointValue = pointValue + (tempValue*tempPoints);	
		}
		if(sumOfPoints != 0)
			weightAverage = pointValue/sumOfPoints;
		return weightAverage;
	}
	
	/**
	 * Get the percentage scored on this Test given an AbstractList of Integers for answers
	 * @param answerSet Any List of Integers with answer numbers for the test Questions
	 * @return An average of the scores for all Questions weighted by their points values.
	 */
	public double scoreAnswers(AbstractList <Integer> answerSet){
		return scoreAnswers(answerSet.stream().mapToInt(i->i).toArray());
	}

	/**
	 * Sets the entire list of questions for the Test.
	 * @param newQuestionList Any List of Questions to set the Test to.
	 */
	public void setQuestionList(AbstractList<Question> newQuestionList){
		questionList.clear();
		questionIDs.clear();
		questionPoints.clear();

		//Iterate through the list and add to the question map. we are going to add the test ID to each of the questions.
		for(Question tempQuestion : newQuestionList){			
			questionList.add(tempQuestion);
			questionIDs.add(tempQuestion.getID());
			questionPoints.add(0);
		}
		this.flush();
	}



	/*
	 *  (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @decription overrides the default 'toString()' to showcase a basic exam with all questions included.
	 */
	@Override
	public String toString(){

		//Create the initial question
		String thisTestString = "Test: " + this._testName + "\n";
		
		thisTestString = thisTestString + ((Integer)numQuestions()).toString() + " Questions:\n";
		
		if(null == questionList || questionList.isEmpty())
			initQuestions();

		for(Question tempQuestion : questionList){
			thisTestString = thisTestString + tempQuestion.getQuestion() + "\n";
		}
		//Return the result
		return thisTestString;
	}

}
