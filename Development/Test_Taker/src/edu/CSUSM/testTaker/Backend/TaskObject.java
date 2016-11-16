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
 * @deprecated Similar functionality can already be provided through the Registerable interface without breaking the serialization version.
 * if adding data members to all the classes hasn't broken our serialized data it's only by dumb luck.
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
