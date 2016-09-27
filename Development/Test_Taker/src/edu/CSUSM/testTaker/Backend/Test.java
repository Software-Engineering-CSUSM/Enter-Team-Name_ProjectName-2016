package edu.CSUSM.testTaker.Backend;

import java.util.ArrayList;
import java.io.Serializable;

public class Test implements Serializable{
	static final long serialVersionUID = 1L;
	String myID;
	
	public String getID(){
		return myID;
	}

	volatile ArrayList<Question> questionList;
	ArrayList<String> questionIDs;
	ArrayList<Integer> questionPoints;
	String title;
	
	/*Author: John Orcino
	 * PARAMETER: May need one
	 * FUNCTION: gets the Id for the question to have the array of questions and answers
	 */
	public void getQuestionIDs(){
		
	}
	
	/*	Author: John Orcino
	 * 	FUNCTION: adds up the total points of the test and returns it
	 */
	public int totalPointsScored(){
		int tot = 0;
		
		//iterator adding up the question points 
		
		return tot;
	}
	
	/*	Author: John Orcino
	 * 	PARAMETERS:
	 * 	FUNCTION: Display the question and answers, stores the user's answer
	 * 
	 */
	
	public void testDisplay(){
		//iterator that uses the array of answers and questions and displays them
	}
	
	@Override
    public String toString(){
        
        //Create the initial question
        String thisTestString = "Test: " + this.title;
                
        //Now, add each of the possible answers in the provided question
        for(int iterator = 0; iterator < questionList.size() ; iterator++){
            thisTestString += "\n\t\t " + (iterator+1) + ") " + this.questionList.get(iterator).toString();
        }
        
        //Return the result
        return thisTestString;
    }

}
