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
		
		//Add shutdown hook to store the library at exit
		java.lang.Runtime.getRuntime().addShutdownHook(ShutdownManager.getInstance());
	}

}
