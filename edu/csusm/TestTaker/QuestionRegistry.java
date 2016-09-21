package edu.csusm.TestTaker;

import java.io.Serializable;

public class QuestionRegistry implements Serializable{
	static final long serialVersionUID = 1L;
	static java.util.HashMap<String, Question> questionStore;
}
