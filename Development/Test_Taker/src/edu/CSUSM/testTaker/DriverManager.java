/**
 * 
 */
package edu.CSUSM.testTaker;

import edu.CSUSM.testTaker.Backend.Course;
import edu.CSUSM.testTaker.Backend.Question;
import edu.CSUSM.testTaker.Backend.Test;
import edu.CSUSM.testTaker.UI.GUIController;
import java.io.File;

/**
 * @author Justin
 *
 */
public class DriverManager {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/** Testing **/
		LibraryController.initDB();
		Course.makeExample();
		/** End Testing **/
		
		GUIController gui = new GUIController();
		gui.setVisible(true);
		
		
		/*
		//Restore Library if it exists
		File testfile = new File("Library.bin");
		if(testfile.exists()){
			LibraryController.restoreLibrary("Library.bin");
		}else{
			//Load content into library only once
			Question sample = 		Question.makeExample();
			Test sampleTest = 		Test.makeExample();
			Course sampleCourse = 	Course.makeExample();
			
			System.out.println(sample);
			System.out.println(sampleTest);
			System.out.println(sampleCourse);
			
		}*/
		
		//Add shutdown hook to store the library at exit
		java.lang.Runtime.getRuntime().addShutdownHook(ShutdownManager.getInstance());
	}

}
