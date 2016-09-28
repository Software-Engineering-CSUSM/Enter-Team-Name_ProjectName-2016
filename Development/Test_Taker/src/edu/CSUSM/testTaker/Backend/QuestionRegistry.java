package edu.CSUSM.testTaker.Backend;

import java.io.Serializable;
import java.util.HashMap;

public class QuestionRegistry implements Serializable{
	static final long serialVersionUID = 1L;
	static HashMap<String, Question> questionStore;
	/**
	 * For now just set an empty hashmap each startup;
	 */
	static {
		questionStore = new HashMap<String, Question>();
	}

	/**
	 * Provide a reference to the Question in the registry with the given ID string.
	 * @param queryString a globally unique ID string associated with a Question
	 * @return A reference to a Question with that ID retrieved from the "database" or null for failure.
	 */
	public static Question retrieve(String queryString){
		return questionStore.get(queryString); 
	}
	
	/**
	 * 
	 * @param updated A Question object reference to store/update in the "database"
	 * @return true if successful, false for failure
	 */
	public static boolean store(Question updated){
		questionStore.put(updated.getID(), updated);
		return true;
	}
	
}
