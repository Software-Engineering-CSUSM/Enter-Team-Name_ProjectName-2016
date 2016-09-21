package edu.csusm.TestTaker;

import java.io.Serializable;

public class Test implements Serializable{
	static final long serialVersionUID = 1L;
	String myID;
	java.util.ArrayList<Question> testQuestions;
	java.util.ArrayList<Integer> questionPointsValues;
}
