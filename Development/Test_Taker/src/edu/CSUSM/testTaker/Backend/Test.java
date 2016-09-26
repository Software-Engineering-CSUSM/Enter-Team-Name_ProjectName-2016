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
