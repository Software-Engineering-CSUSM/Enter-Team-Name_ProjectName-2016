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
}
