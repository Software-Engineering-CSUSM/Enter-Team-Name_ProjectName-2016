package edu.CSUSM.testTaker.Backend;

import java.util.ArrayList;

public class Question {
	
	public String _question;
	public ArrayList<String> _answers = new ArrayList<String>();
	public int _correctIndex;

	/**
	 * @param mainQuestion
	 */
	public Question(String mainQuestion){
		setQuestion(mainQuestion);
	}
	
	/**
	 * @param mainQuestion
	 * @param answers
	 * @param correctAnsIndex
	 */
	public Question(String mainQuestion, String[] answers, int correctAnsIndex){
		
		/* Set the main Question */
		setQuestion(mainQuestion);
		
		/* Add all possible answers to the list */
		for(int i = 0; i < answers.length; i++){
			addAnswer(answers[i]);
		}
		
		/* Set the correct answer index */
		setCorrectIndex(correctAnsIndex);
	}
	
	/* Mutators */
	/**
	 * @param newQuestion overwrites the existing question, if any
	 */
	public void setQuestion(String newQuestion){
		this._question = newQuestion;
		}
	
	/**
	 * @param newAnswer Provides a new answer for the provided index
	 * @param index The current location of the answer that is being modified
	 */
	public void setAnswer(String newAnswer, int index){
		this._answers.set(index, newAnswer); 
		}
	
	/**
	 * @param additionalAnswer Adds a new answer the array
	 */
	public void addAnswer(String additionalAnswer){
		this._answers.add(additionalAnswer);
	}
	
	/**
	 * @param index of which references index of answers array
	 */
	public void setCorrectIndex(int index){
		this._correctIndex = index;
		}
	
	/* Accessors */
	/**
	 * @return The current question
	 */
	public String getQuestion(){
		return this._question;
	}
	
	/**
	 * @return a list of the correct answers
	 */
	public String[] getAnswers(){
		return this._answers.toArray(new String[this._answers.size()]);
	}
	
	/**
	 * @return the location to the correct answer within the answers array
	 */
	public int getCorrectIndex(){
		return this._correctIndex;
	}
	
	
	public String toString(){
		
		//Create the initial question
		String thisQuestionString = "Question: " + this._question;
				
		//Now, add each of the possible answers in the provided question
		for(int iterator = 0; iterator < this.getAnswers().length; iterator++){
			thisQuestionString += "\n\t\t " + (iterator+1) + ") " + this._answers.get(iterator);
			
			//If the answer is correct, add an asterisk to the end
			thisQuestionString += (iterator == this._correctIndex) ? " *correct*" : "";
		}
		
		//Return the result
		return thisQuestionString;
	}
}