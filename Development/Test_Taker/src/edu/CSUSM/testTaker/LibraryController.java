package edu.CSUSM.testTaker;


import edu.CSUSM.testTaker.Backend.*;
import java.util.HashMap;

/**
 * 
 * @author Justin
 * @Description This class controls the library. This class loads in the library once and is used by many GUI classes throughout.
 * 		For successful implementation, this class should not be created more than once. Many of the variables are static, so if it is created more
 * 			than once, the variables will not need to be re-initialized.
 *
 */
public class LibraryController{

	/** 
	 * For Implementation, we are going to use Hashmaps. This is how:
	 * 		• The string used in the map (as the 'key') will be the identifier of the test
	 * 		• The 'value' of the map will be the actual object.
	 * 
	 * The reason we are using maps is because the time complexity is W(1) and the list will not have to loop
	 * 	through the data every time it is required.
	 */
	public static HashMap<String, Test> testArray;
	public static HashMap<String, Question> questionArray;
	public static HashMap<String, Course> classArray;


	public static boolean hasInitialized = false;

	/**
	 * Default constructor for class. Inits variables, if needed and allows access to all library objects
	 */
	public LibraryController(){

		/** If the library controller has been initialized already, skip the re-init of variables */
		if(!LibraryController.hasInitialized){
			
			LibraryController.hasInitialized = true;

			/**Init the hash maps*/
			loadData();	
		}else{
			System.out.println("Did not re-init library. Current lib in use.");
		}
		
		/**When data is initialized, do something else.. if needed*/
		//----------------------------------------------//
		//----------------------------------------------//
		//----------------------------------------------//
		//----------------------------------------------//
		//----------------------------------------------//
	}

	/**
	 * Loads the data from where-ever it is stored
	 */
	private static void loadData(){
		/**For now, just create empty maps, then add test data to them */
		testArray = new HashMap<String, Test>();
		questionArray = new HashMap<String, Question>();
		classArray = new HashMap<String, Course>();
		
		addTestData();
	}
	
	/**
	 * Adds sample data for testing purposes
	 */
	private static void addTestData(){
		testQuestion();
	}

	/**
	 * 	Creates a test question to ensure the question class works as expected
	 */
	private static void testQuestion(){
		
		//Initialize the question using the first constructor
		Question aSampleQuestion = new Question("What is 5 * 5?");
		
		//Add some possible answers
		aSampleQuestion.addAnswer("15");
		aSampleQuestion.addAnswer("25");
		aSampleQuestion.addAnswer("5");
		aSampleQuestion.addAnswer("10");
		
		//Tell the program where the correct answer lies within the array
		aSampleQuestion.setCorrectIndex(1);
		
		//Display the question in its entirety
		//System.out.println(aSampleQuestion.toString());
		
		//Add another question using the other constructor
		Question anotherQuestion = new Question("What is 5-5?", new String[]{"3", "4", "1", "7", "10", "0"}, 5);
		//System.out.println(anotherQuestion.toString());
		
		//Add the sample questions to the hashmap
		questionArray.put("1", aSampleQuestion);
		questionArray.put("2", anotherQuestion);
	}
	
	/**
	 * @return Customized String that displays all of the relative data within the class
	 */
	public String toString(){
		
		//Create the string for questions
		String questionString = "Questions in Library: \n--------------------";
		
		//Add the question hashmap to the question string
		for(Question questionInLib : questionArray.values()){
			questionString += "\n" + questionInLib + "\n";
		}
		
		//For now, just return the question string
		return questionString;
	}


}
