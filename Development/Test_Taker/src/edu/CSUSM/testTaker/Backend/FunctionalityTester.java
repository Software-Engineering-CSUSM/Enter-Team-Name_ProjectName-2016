package edu.CSUSM.testTaker.Backend;

import edu.CSUSM.testTaker.LibraryController;

public class FunctionalityTester {
	public static void main(String [] unused){
		Course testcourse = Course.makeExample();
		Test testtest = Test.makeExample();
		Question testquestion = Question.makeExample();
		
		System.out.println(testcourse);
		System.out.println(testtest);
		System.out.println(testquestion);
	}
}
