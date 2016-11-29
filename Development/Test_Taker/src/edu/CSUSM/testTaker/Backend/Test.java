package edu.CSUSM.testTaker.Backend;

import edu.CSUSM.testTaker.*;
import edu.CSUSM.testTaker.Analytics.AnaSetup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.io.IOException;
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
		try{AnaSetup.logEvent("Initialized a Test instance");}
		catch(IOException e){e.printStackTrace();}
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
	public Test(String testName, List<Question> listOfQuestionsForTest){
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

		try{AnaSetup.logEvent("Made a new example Test");}
		catch(IOException e){e.printStackTrace();}
		return rval;
	}

	/** Accessors */

	/**
	 * Give identifier strings of all questions in Test.
	 * @return An array with the IDs of all Questions in this Test.
	 */
	public String[] getQuestionIDs(){
		try{
		if(questionIDs.size() > 0)
			return questionIDs.toArray(new String[questionIDs.size()]);
		else
			throw new Exception();
		}catch (Exception e){
			//JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + e.toString());
			return new String[] {"No Questions Found"};
		}
	}
	
	/**
	 * Give identifier strings of all questions in Test.
	 * @return An ArrayList with the ID Strings of all Questions in this Test.
	 */
	public ArrayList <String> getQuestionIDList(){
		try{
		if(questionIDs != null)
			return new ArrayList<String>(questionIDs);
		else
			throw new Exception();
		}catch(Exception e){
			//JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + e.toString());
			return new ArrayList<String>();
		}
	}


	/**
	 *  Get an array of questions in the test.
	 * @return The questions in the test, in order, at this moment in time.
	 */
	public Question[] getQuestions(){
		try{
		if(questionList != null)
			return questionList.toArray(new Question[questionList.size()]);
		else
			throw new Exception();
		}catch(Exception e){
			//JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + e.toString());
			return new Question[]{new Question("No Questions Found", new String[]{"No answers to an in-existing question"}, 0)};
		}
	}
	
	/**
	 * Get a list of all Questions in the test
	 * @return The questions in the test, in order, at this moment in time in an ArrayList.
	 */
	public ArrayList<Question> getQuestionList(){
		try{
		if(questionList != null)
			return new ArrayList<Question>(questionList);
		else{
			throw new Exception();
		}
		}catch(Exception e){
			//JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + e.toString());
			questionList = new ArrayList<Question>();
			return questionList;
		}
	}

	/**
	 * Get a question from the test.
	 * @param qn The index of the question to retrieve (0-indexed)
	 * @return Reference to the question number asked for.
	 */
	public Question getQuestion(int qn){
		if(qn < 0) return null;
		try{
		return (questionList.get(qn) != null) ? questionList.get(qn) : null;
		}catch(Exception e){
			//e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Get the ID of a particular Question
	 * @param index the index of the Question to retrieve the ID of
	 * @return a unique ID String for retrieving the Question from the Library
	 */
	public String getQuestionID(int index){
		if(index < 0) return null;
		return (questionIDs.get(index) != null) ? questionIDs.get(index) : null;
	}
	
	
	/**
	 * Get the Name of a particular question
	 * @param index the index of the Question to retrieve the name of
	 * @return the name String of the Question
	 */
	public String getQuestionName(int index){
		if(index < 0) return null;
		return (LibraryController.getItemName(questionIDs.get(index)) != null) ? LibraryController.getItemName(questionIDs.get(index)) : null;
	}
	
	/**
	 * Get a list of the "names" of all the Questions
	 * @return an ArrayList of name Strings
	 */
	public ArrayList <String> getQuestionNames(){
		try{
			return LibraryController.getNamesForIDs(questionIDs);
		}catch(Exception e){
			return new ArrayList<String>();
		}
		
	}

	/**
	 * Get the number of questions presently in this test.
	 * @return The length of the container of questions.
	 */
	public int numQuestions(){
		if(this.questionIDs == null) return 0;
		return this.questionIDs.size();
	}

	/**
	 * Get the points value of a particular test question
	 * @param qn The index of the question to retrieve (0-indexed)
	 * @return The value of the question within this test in points.
	 */
	public int getQuestionPoints(int qn){
		if(qn < 0) return 0;
		return questionPoints.get(qn);
	}
	
	/**
	 * Get a list of the points values for all Questions in the Test in order
	 * @return an ArrayList of Integers
	 */
	public ArrayList<Integer> getQuestionPoints(){
		try{
		return new ArrayList<Integer>(questionPoints);
		}catch(Exception e){
			return new ArrayList<Integer>();
		}
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

	/**
	 * Determines of there is an amount of questions in this test
	 * @returns Boolean of the result (questions.count >0)
	 */
	public boolean hasQuestions(){
		return (questionList.size()>0);
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
		try{AnaSetup.logEvent("Initialized the question reference list of a Test");}
		catch(IOException e){e.printStackTrace();}
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
			try{AnaSetup.logEvent("Set the points of a Question in a Test");}
			catch(IOException e){e.printStackTrace();}
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
		//System.out.println("Question: " + getQuestionList().toString());
		questionList.add(QuestionToAdd);
		questionIDs.add(QuestionToAdd.getID());
		questionPoints.add(questionvalue);
		try{AnaSetup.logEvent("Added a Question to a Test");}
		catch(IOException e){e.printStackTrace();}
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
		try{AnaSetup.logEvent("Added a Question to a Test");}
		catch(IOException e){e.printStackTrace();}
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
		try{AnaSetup.logEvent("Removed a Question from a Test");}
		catch(IOException e){e.printStackTrace();}
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
		try{AnaSetup.logEvent("Added a Question to a Test");}
		catch(IOException e){e.printStackTrace();}
		this.flush();
	}

	/**
	 * Set the title/name of this Test	
	 * @param newTestName A String of the new name for this Test
	 */
	public void setName(String newTestName){
		this._testName = newTestName;
		try{AnaSetup.logEvent("Renamed a Test");}
		catch(IOException e){e.printStackTrace();}
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
		try{AnaSetup.logEvent("Set all the Questions in a Test");}
		catch(IOException e){e.printStackTrace();}
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
		try{AnaSetup.logEvent("Scored a Test");}
		catch(IOException e){e.printStackTrace();}
		return weightAverage;
	}
	
	/**
	 * Get the percentage scored on this Test given an AbstractList of Integers for answers
	 * @param answerSet Any List of Integers with answer numbers for the test Questions
	 * @return An average of the scores for all Questions weighted by their points values.
	 */
	public double scoreAnswers(List <Integer> answerSet){
		return scoreAnswers(answerSet.stream().mapToInt(i->i).toArray());
	}

	/**
	 * Sets the entire list of questions for the Test.
	 * @param newQuestionList Any List of Questions to set the Test to.
	 */
	public void setQuestionList(List<Question> newQuestionList){
		questionList.clear();
		questionIDs.clear();
		questionPoints.clear();

		//Iterate through the list and add to the question map. we are going to add the test ID to each of the questions.
		for(Question tempQuestion : newQuestionList){			
			questionList.add(tempQuestion);
			questionIDs.add(tempQuestion.getID());
			questionPoints.add(0);
		}
		try{AnaSetup.logEvent("Set all the Questions in a Test");}
		catch(IOException e){e.printStackTrace();}
		this.flush();
	}



	/*
	 *  (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @decription overrides the default 'toString()' to showcase a basic exam with all questions included.
	 */
	@Override
	public String toString(){
		
		if(this.getTestName().length() == 0){
			return "No test Yet";
		}
			

		//Create the initial question
		String thisTestString = "Test: " + this._testName + "\n";
		
		thisTestString = thisTestString + ((Integer)numQuestions()).toString() + " Questions:\n";
		
		if(null == questionList || questionList.isEmpty())
			initQuestions();

		for(String qstring : getQuestionNames()){
			thisTestString = thisTestString + qstring + "\n";
		}
		//Return the result
		return thisTestString;
	}

}
