package edu.CSUSM.testTaker.Backend;

import edu.CSUSM.testTaker.LibraryController;

public class FunctionalityTester {
	public static void main(String [] unused){
		LibraryController.initDB();
		
		Course testcourse = Course.makeExample();
		Test testtest = Test.makeExample();
		Question testquestion = Question.makeExample();
		
		System.out.println(testcourse);
		System.out.println(testtest);
		System.out.println(testquestion);

		System.out.println("Testing for presence, too early.");
		System.out.println("course:"+LibraryController.inTable(testcourse.getID(), "COURSES"));
		System.out.println("test:"+LibraryController.inTable(testtest.getID(), "TESTS"));
		System.out.println("question:"+LibraryController.inTable(testquestion.getID(), "QUESTIONS"));		

		
		System.out.println("Attempting to add above to database.");
		
		LibraryController.addOrUpdate(testcourse, "COURSES");
		LibraryController.addOrUpdate(testtest, "TESTS");
		LibraryController.addOrUpdate(testquestion, "QUESTIONS");

		System.out.println("Testing for presence.");
		System.out.println("course:"+LibraryController.inTable(testcourse.getID(), "COURSES"));
		System.out.println("test:"+LibraryController.inTable(testtest.getID(), "TESTS"));
		System.out.println("question:"+LibraryController.inTable(testquestion.getID(), "QUESTIONS"));		
	}
}
