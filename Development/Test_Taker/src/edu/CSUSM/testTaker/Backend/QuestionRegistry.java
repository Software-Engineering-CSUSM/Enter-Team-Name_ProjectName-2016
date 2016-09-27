package edu.CSUSM.testTaker.Backend;

import java.io.Serializable;
import java.util.HashMap;

public class QuestionRegistry implements Serializable{
	static final long serialVersionUID = 1L;
	static HashMap<String, Question> questionStore;

	public static Question retrieve(String queryString){
		return questionStore.get(queryString);
	}
	
	public static boolean store(Question updated){
		questionStore.put(updated.getID(), updated);
		return true;
	}
	/*	Author: John Orcino
	 * 	@param needs the original question and the string that the user entered
	 * 	FUNCTION: change the requested question into what the user entered
	 * public void editQuestion(Question oldQuestion, String newQuestion){
	 *  
	 * }	
	 */
}
