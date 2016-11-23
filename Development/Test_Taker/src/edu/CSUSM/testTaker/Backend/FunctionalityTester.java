package edu.CSUSM.testTaker.Backend;

import java.io.IOException;

import edu.CSUSM.testTaker.LibraryController;
import edu.CSUSM.testTaker.Analytics.*;

public class FunctionalityTester {
	public static void main(String [] unused){
		try{AnaSetup.clearData();}
		catch(IOException e){e.printStackTrace();}		
		
		if(LibraryController.checkForDB()){
			System.out.println("database found with expected filename");
		}
		else{
			System.out.println("database NOT found with expected filename, creating");
			LibraryController.initDB();
		}
		
		Course testcourse = Course.makeExample();
		Test testtest = Test.makeExample();
		Question testquestion = Question.makeExample();
		Question testq2 = new Question("this has been a test of the emergency debug system");
		
		System.out.println(testcourse);
		System.out.println(testtest);
		System.out.println(testquestion);
		System.out.println(testq2);

		
		System.out.println("Attempting to add above to database.");
		
		LibraryController.putItem(testcourse);
		LibraryController.putItem(testtest);
		LibraryController.putItem(testquestion);
		LibraryController.putItem(testq2);

		System.out.println("Testing for presence.");
		System.out.println("course:"+LibraryController.containsID(testcourse.getID()));
		System.out.println("test:"+LibraryController.containsID(testtest.getID()));
		System.out.println("question:"+LibraryController.containsID(testquestion.getID()));
		System.out.println("question2:"+LibraryController.containsID(testq2.getID()));
		
		
		System.out.println("Attempting to retrieve objects from database.");
		Course rcourse = LibraryController.retrieveCourse(testcourse.getID());
		Test rtest = LibraryController.retrieveTest(testtest.getID());
		Question rquestion = LibraryController.retrieveQuestion(testquestion.getID());
		Question rq2 = LibraryController.retrieveQuestion(testq2.getID());
		rtest.addQuestion(rq2);

		System.out.println(rcourse);
		System.out.println(rtest);
		System.out.println(rquestion);
		System.out.println(rq2);
		
		
		try{AnaViewer.processLogs();}
		catch(IOException e){e.printStackTrace();}				
	}
}
