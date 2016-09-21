package edu.csusm.TestTaker;

import java.io.Serializable;

public class Question implements Serializable{
	static final long serialVersionUID = 1L;
	String myID;
	java.util.ArrayList<String> answers;
	int correctAnswer;
}
