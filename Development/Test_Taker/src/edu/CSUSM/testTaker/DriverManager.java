/**
 * 
 */
package edu.CSUSM.testTaker;


/**
 * @author Justin
 *
 */
public class DriverManager {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Create a sample question to ensure the class works
		LibraryController mainLib = new LibraryController();
		
		/** Print the final library to screen (After init) */
		System.out.println(mainLib.toString());
		
		//Re-init to show that the lib is not recreated
		LibraryController secondLib = new LibraryController();
		System.out.println("New Lib:\n" + secondLib.toString());
	}
	
	
}
