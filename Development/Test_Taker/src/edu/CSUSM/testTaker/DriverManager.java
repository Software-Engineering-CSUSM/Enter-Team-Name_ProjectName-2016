/**
 * 
 */
package edu.CSUSM.testTaker;

/* Import the used classes within the package */
import edu.CSUSM.testTaker.Backend.Question;

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
		testQuestion();
		
	}
	
	/**
	 * 	Creates a test question to ensure the question class works as expected
	 */
	public static void testQuestion(){
		
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
		System.out.println(aSampleQuestion.toString());
		
		//Add another question using the other constructor
		Question anotherQuestion = new Question("What is 5-5?", new String[]{"3", "4", "1", "7", "10", "0"}, 5);
		System.out.println(anotherQuestion.toString());
	}

}