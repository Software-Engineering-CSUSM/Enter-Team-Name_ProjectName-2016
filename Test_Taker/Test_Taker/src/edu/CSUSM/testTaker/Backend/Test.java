package edu.CSUSM.testTaker.Backend;

import java.util.ArrayList;
import java.io.Serializable;

public class Test implements Serializable{
	static final long serialVersionUID = 1L;
	String myID;

	ArrayList<Question> questionList;
	ArrayList<Integer> questionPoints;
}
