/**
 * 
 */
package edu.CSUSM.testTaker.Backend;

/**
 * @author Justin
 *
 */
/**
 * 
 * @description: All small classes should be branched from this. This will be used to capture all important data - all in one place.
 * This class's role is to make it so that, no matter the object, it will return what needs to be returned
 *
 */
public class TaskObject {
	
	public String currentID, currentName;
	
	public static void getObjects(){}
	
	public String getID(){
		return this.currentID;
	}
	
	public String getName(){
		return this.currentName;
	}

}
