package edu.csusm.TestTaker;

import java.io.Serializable;

public class Question implements Serializable{
	String myID;
	java.util.ArrayList<String> answers;
	int correctAnswer;
}
