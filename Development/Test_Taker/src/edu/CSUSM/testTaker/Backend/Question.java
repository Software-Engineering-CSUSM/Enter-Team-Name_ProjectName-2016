package edu.CSUSM.testTaker.Backend;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import edu.CSUSM.testTaker.LibraryController;
import edu.CSUSM.testTaker.Analytics.AnaSetup;

public class Question /*extends TaskObject*/ implements Serializable, Registerable{
	public static final long serialVersionUID = 1L;
	
	String myID;
	
	public String getID(){
		return myID;							//Within the Question HashMap, the ID will be in the key position where the question is the value
	}
	
	public String getName(){
		return getQuestion();
	}
	
	public String getTypeName(){
		return "Question";
	}
	
	public void turnIntoDuplicate(){
		myID = UUID.randomUUID().toString();
	}

	
	String _question;
	ArrayList<String> _answers;			//To easily manage questions added and removed
	int _correctIndex = -1;				//The default correct index is 0 because it is not yet assigned;
	
	
	/** New question with no details
	 */
	public Question(){
		myID = UUID.randomUUID().toString();
		_answers = new ArrayList<String>();
		_question = "";
		try{AnaSetup.logEvent("Constructed a new Question");}
		catch(IOException e){e.printStackTrace();}
	}

	/**New Question starting with question text
	 * @author Justin Goulet
	 * @param mainQuestion The main text of the Question : the question being asked.
	 */
	public Question(String mainQuestion) {

		myID = UUID.randomUUID().toString();

		_answers = new ArrayList<String>();
		setQuestion(mainQuestion);
		
		/*Increment the total count of questions*/
		
		myID = UUID.randomUUID().toString();
		this.flush();
		try{AnaSetup.logEvent("Constructed a new Question");}
		catch(IOException e){e.printStackTrace();}
	}
	
	/** Complete question definition, with question, answers, and correct answer index
	 * @author Justin Goulet
	 * @param mainQuestion
	 *            The main question to be read
	 * @param answers
	 *            A list of answers that will be included in the question
	 * @param correctAnsIndex
	 *            The index of the correct answer within the 'answers' array
	 */
	public Question(String mainQuestion, String[] answers, int correctAnsIndex) {

		myID = UUID.randomUUID().toString();

		/* Set the main Question */
		setQuestion(mainQuestion);

		/* Add all possible answers to the list */
		this._answers = new ArrayList<String>(Arrays.asList(answers));

		/* Set the correct answer index */
		setCorrectIndex(correctAnsIndex);
		
		myID = UUID.randomUUID().toString();
		try{AnaSetup.logEvent("Constructed a new Question");}
		catch(IOException e){e.printStackTrace();}
		this.flush();
	}
	
	/** Make a sample question.
	 * @return A reference to a Question, a Monty Python reference 
	 */
	public static Question makeExample(){
		Question rval = new Question();
		
		rval.setQuestion("What! is your Quest?");
		rval.addAnswer("What?");
		rval.addAnswer("To find the Holy Grail");
		rval.addAnswer("Aaaaaah");
		rval.setCorrectIndex(1);
		try{AnaSetup.logEvent("Made a new example Question");}
		catch(IOException e){e.printStackTrace();}
		return rval;
	}
	

	/* Mutators */
	/**
	 * Set the question asked by this question.
	 * @author Justin Goulet
	 * @param newQuestion
	 *            overwrites the existing question, if any
	 */
	public void setQuestion(String newQuestion) {
		this._question = newQuestion;
		this.flush();
		try{AnaSetup.logEvent("Reset a Question's main question");}
		catch(IOException e){e.printStackTrace();}
	}

	/**
	 * Set/reset an individual answer to this question.
	 * @author Justin Goulet
	 * @param newAnswer
	 *            Provides a new answer for the provided index
	 * @param index
	 *            The current location of the answer that is being modified
	 */
	public void setAnswer(String newAnswer, int index){
		if(index <= _answers.size()){
			this._answers.set(index, newAnswer); 
			this.flush();
			try{AnaSetup.logEvent("Changed an answer to a Question");}
			catch(IOException e){e.printStackTrace();}
			}
		}
	
	/**
	 * Remove a particular answer from the set of answers to this question
	 * @param index The number of the answer to remove.
	 */
	public void deleteAnswer(int index){
		this._answers.remove(index);
		if(index < this._correctIndex){
			--_correctIndex;
		}
		try{AnaSetup.logEvent("Removed an answer to a Question");}
		catch(IOException e){e.printStackTrace();}
	}
	
	/**
	 * Add a new answer to the set of answers for this question.
	 * @author Justin Goulet
	 * @param additionalAnswer A new answer to add to the array
	 */
	public void addAnswer(String additionalAnswer) {
		this._answers.add(additionalAnswer);
		this.flush();
		try{AnaSetup.logEvent("Added an answer to a Question");}
		catch(IOException e){e.printStackTrace();}
	}

	/**
	 * Set the correct answer to this question
	 * @author Justin Goulet
	 * @param index Index of correct answer to this Question.
	 */
	public void setCorrectIndex(int index){
		if(index < _answers.size()){
			this._correctIndex = index;
			this.flush();
			try{AnaSetup.logEvent("Set the correct answer to a Question");}
			catch(IOException e){e.printStackTrace();}
			}
		}

	/* Accessors */
	/**
	 * Get the text of this Question
	 * @author Justin Goulet
	 * @return The current question being asked.
	 */
	public String getQuestion() {
		return this._question;
	}
	
	/**
	 * Get the current number of answers to this Question.
	 * @return An int number of answers.
	 */
	public int numAnswers(){
		return _answers.size();
	}
	

	/**
	 * Get a list of answers to this Question.
	 * @author Justin Goulet
	 * @return A list of the current answer choices to this Question.
	 */
	public String[] getAnswers(){
		return (String[])this._answers.toArray(new String[_answers.size()]);
	}

	/**
	 * Get the answer string for a given index
	 * 
	 * @param dex
	 *            The index number of the answer to get.
	 * @return The String of that answer.
	 */
	public String getAnswer(int dex) {
		return _answers.get(dex);
	}

	/**
	 * Get the index of the correct answer to this question.
	 * @author Justin Goulet
	 * @return the location to the correct answer within the answers array
	 */
	public int getCorrectIndex() {
		return this._correctIndex;
	}
	
	
	/** Grades an answer to this question
	 * @author John Orcino
	 * @param location of user's chose of answer
	 * @description returns a 1.0 if answer given by user matches the correct answer for this Question
	 */
	public double scoreAnswer(int index){
		try{AnaSetup.logEvent("Scored a Question");}
		catch(IOException e){e.printStackTrace();}
		if(index == _correctIndex)
			return 1.0;
		else 
		return 0.0;
	}
	
	
	/** (non-Javadoc)
	/**
	 * Return a string representation of this Question.
	 * @see java.lang.Object#toString()
	 * @author Justin Goulet
	 * @return A human readable string of the contents of this question.
	 */
	@Override
	public String toString() {

		// Create the initial question
		String thisQuestionString = "Question: " + this._question;
		thisQuestionString += "\nQuestion ID: " + getID();

		
		// Now, add each of the possible answers in the provided question
		for (int iterator = 0; iterator < this.getAnswers().length; iterator++) {
			thisQuestionString += "\n\t\t " + (iterator + 1) + ") " + this.getAnswers()[iterator];

			// If the answer is correct, add an asterisk to the end
			thisQuestionString += (iterator == this._correctIndex) ? " *correct*" : "";
		}
		
		// Return the result
		return thisQuestionString;
	}

}
