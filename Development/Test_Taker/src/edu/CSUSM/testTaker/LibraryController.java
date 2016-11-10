package edu.CSUSM.testTaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.Iterator;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.CSUSM.testTaker.Backend.*;
import edu.CSUSM.testTaker.UI.Pages.StudyGame.allMatched;

/**
 * 
 * @author Justin
 * @Description This class controls the library. This class loads in the library
 *              once and is used by many GUI classes throughout. For successful
 *              implementation, this class should not be created more than once.
 *              Many of the variables are static, so if it is created more than
 *              once, the variables will not need to be re-initialized.
 *
 */
public class LibraryController{
	
	/** Config values for database here:
	 * 
	 */
	protected static String databaseName = "Test-Taker";
	protected static String DBUserName = "";
	protected static String DBPassword = "";//* < should probably be derived from a cyphertext if ever used
	
	

	/**
	 * For Implementation, we are going to use Hashmaps. This is how: • The
	 * string used in the map (as the 'key') will be the identifier of the test
	 * • The 'value' of the map will be the actual object.
	 * 
	 * The reason we are using maps is because the time complexity is W(1) and
	 * the list will not have to loop through the data every time it is
	 * required.
	 */
	private static HashMap<String, Test> testMap;			//String is the testID
	private static HashMap<String, Question> questionMap;	//String is the questionID
	private static HashMap<String, Course> courseMap;
	private static HashMap<String, CourseProgress> progressMap;
	static {
		testMap = new HashMap<String, Test>();
		questionMap = new HashMap<String, Question>();
		courseMap = new HashMap<String, Course>();
		progressMap = new HashMap<String, CourseProgress>();
	}
	
	

	/**
	 * Get an open database connection
	 * @return An open connection to the database.
	 * @throws SQLException
	 */
	protected static Connection connect()
	throws SQLException{
		return java.sql.DriverManager.getConnection("jdbc:h2:~/"+databaseName,DBUserName,DBPassword);
	}
	
	
	/**
	 * Reset the database
	 */
	public static void initDB(){
		try(Connection dbcon = connect()){
			Statement sqlLine = dbcon.createStatement();
			sqlLine.execute("drop table if exists REGISTRY;");
			sqlLine.execute("create table REGISTRY(ID binary(36) primary key,typename varchar(31), name varchar(255), data other);");
			dbcon.close();
		}
		catch(SQLException e){
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Is the given ID in the registry
	 * @param id Unique ID String of the object in question.
	 * @return True if found, false otherwise.
	 */
	public static boolean containsID(String id){
		try(Connection dbcon = connect()){
			PreparedStatement myquery = dbcon.prepareStatement("select name from REGISTRY where id = ?;");
			myquery.setBytes(1, id.getBytes());
			ResultSet myresults = myquery.executeQuery();
			boolean rval = myresults.next();
			dbcon.close();
			return rval;
		}
		catch(SQLException ex){
			ex.printStackTrace();
			System.exit(1);
			return false;
		}
	}
	
	/**
	 * Inserts or updates an item in the registry
	 * @param reg A reference to an object implementing the Registerable interface
	 */
	public static void putItem(Registerable reg){
		if(containsID(reg.getID())){
			try(Connection dbcon = connect()){
				PreparedStatement myupdate = dbcon.prepareStatement("update REGISTRY set name = ?, typename = ?, data = ? where ID = ?;");
				myupdate.setString(1, reg.getName());
				myupdate.setString(2, reg.getTypeName());
				myupdate.setObject(3, reg);
				myupdate.setBytes(4, reg.getID().getBytes());
				myupdate.executeUpdate();
			}
			catch(SQLException e){
				e.printStackTrace();
				System.exit(1);
			}
		}
		else{
			try(Connection dbcon = connect()){
				PreparedStatement myupdate = dbcon.prepareStatement("insert into REGISTRY values(?,?,?,?);");
				myupdate.setBytes(1, reg.getID().getBytes());
				myupdate.setString(2, reg.getTypeName());
				myupdate.setString(3, reg.getName());
				myupdate.setObject(4, reg);
				myupdate.executeUpdate();
			}
			catch(SQLException e){
				e.printStackTrace();
				System.exit(1);
			}
			
		}
	}
	
	/**
	 * Get a live reference to an object from the registry.
	 * @param id Unique ID String of the object in question.
	 * @return A reference to an object that implements the Registerable interface
	 */
	public static Registerable getItem(String ID){
		Registerable rval = null;
		try(Connection dbcon = connect()){
			PreparedStatement myquery = dbcon.prepareStatement("select DATA from REGISTRY where ID = ?;");
			myquery.setBytes(1, ID.getBytes());
			ResultSet results = myquery.executeQuery();
			if(results.next()){
				rval = (Registerable) results.getObject(1);
				dbcon.close();
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			System.exit(1);
		}
		return rval;
	}
	
	/**
	 * Get the name of an object in the registry without instantiating it.
	 * @param ID Unique ID string of the object in question.
	 * @return The name or title of the requested object as a String.
	 */
	public static String getItemName(String ID){
		String rval = null;
		try(Connection dbcon = connect()){
			PreparedStatement myquery = dbcon.prepareStatement("select NAME from REGISTRY where ID = ?;");
			myquery.setBytes(1, ID.getBytes());
			ResultSet results = myquery.executeQuery();
			if(results.next()){
				rval = results.getString(1);
				dbcon.close();
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			System.exit(1);
		}
		return rval;
	}
	
	/**
	 * Get a String describing the type of an object in the registry without instantiating it.
	 * @param ID Unique ID string of the object in question.
	 * @return The name of the type of the requested object as a String.
	 */
	public static String getItemType(String ID){
		String rval = null;
		try(Connection dbcon = connect()){
			PreparedStatement myquery = dbcon.prepareStatement("select TYPENAME from REGISTRY where ID = ?;");
			myquery.setBytes(1, ID.getBytes());
			ResultSet results = myquery.executeQuery();
			if(results.next()){
				rval = results.getString(1);
				dbcon.close();
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			System.exit(1);
		}
		return rval;
	}
	
	/**
	 * Remove an object from the registry
	 * @param ID Unique ID string of the object in question.
	 */
	public static void deleteItem(String ID){
		try(Connection dbcon = connect()){
			PreparedStatement myquery = dbcon.prepareStatement("delete from REGISTRY where ID = ?;");
			myquery.setBytes(1, ID.getBytes());
			myquery.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
			System.exit(1);
		}		
	}
	
	/**
	 * Does the database file already exist on this system
	 * @return true if a file with expected name and path exists.
	 */
	public static boolean checkForDB(){
		File testfile = new File(System.getProperty("user.home") + File.separator + databaseName + ".mv.db");
		return testfile.exists();
	}
	

	/**
	 * Gives a Set view of the ID strings of all Courses in the library.
	 * @return a reference to a Set containing all Course ID strings.
	 */
	public static Set<String> giveCourseSet(){
		return courseMap.keySet();
	}

	/**
	 * Gives a List of the current Course IDs
	 * @return An ArrayList of the ID strings of the current Courses in the Library.
	 */
	public static ArrayList<CourseInfo> giveCourseList(){

		//Create a local Arraylist for the classes
		//Note that a custom class will have to be implemented for storage
		ArrayList<CourseInfo> rlist = new ArrayList<CourseInfo>();
		for(Course aCourse : courseMap.values()){
			rlist.add(new CourseInfo(aCourse, aCourse.getID()));
		}

		return rlist;
	}

	public static class CourseInfo{
		public Course thisCourse;
		public String thisID;
		public CourseInfo(Course courseAct, String id){
			this.thisCourse = courseAct; this.thisID = id;
		}
	}


	/**
	 * Gives an iterator of all Courses in the library.
	 * @return A String Iterator over all Courses in the library.
	 */
	public static Iterator<String> courseIDIterator(){
		return courseMap.keySet().iterator();
	}

	/**
	 * Gives an iterator of all Tests in the library.
	 * @return A String Iterator over all Tests in the library.
	 */
	public static Iterator<String> testIDIterator(){
		return testMap.keySet().iterator();
	}

	/**
	 * Gives an iterator of all Questions in the library.
	 * @return A String Iterator over all Questions in the library.
	 */
	public static Iterator<String> questionIDIterator(){
		return questionMap.keySet().iterator();
	}

	public static Iterator<String> progressIDIterator(){
		return progressMap.keySet().iterator();
	}
	
	/** Gets an array of references to live copies of all Courses in the library
	 * @return An ArrayList of all available courses
	 */
	public static ArrayList<Course> getAllCourses(){
		//Create an array
		ArrayList<Course> tempHolder = new ArrayList<Course>();

		//Add all classes to the array
		tempHolder.addAll(courseMap.values());

		//Return the list
		return tempHolder;
	}


	//End of testing purposes

	/**
	 * The below static variables are for the current class, not all information
	 * as done above
	 */
	/**
	 * @deprecated
	 */
	public static HashMap<String, Test> _currentTestsInCourse = new HashMap<String, Test>(); // String
	// is
	// the
	// testID
																								// is
																								// the
																								// testID
	/** @deprecated
	 * 
	 */
	public static HashMap<String, Question> _currentQuestionsInCourse = new HashMap<String, Question>(); // String
	// is
	// the
	// questionID
																											// is
																											// the
	/**
	 * @deprecated																										// questionID
	 */
	public static String _currentClassID;

	// For testing purposes
	/**
	 * @deprecated
	 */
	public static ArrayList<Question> questionsInExam;

	// End of testing purposes

	/**
	 * @deprecated
	 */
	public static boolean hasInitialized = false;

	/**
	 * @author Justin Goulet
	 * @description Default constructor for class. Inits variables, if needed
	 *              and allows access to all library objects
	 */
	/**
	 * @deprecated
	 */
	public LibraryController() {

		/**
		 * If the library controller has been initialized already, skip the
		 * re-init of variables
		 */
		if (!LibraryController.hasInitialized) {

			LibraryController.hasInitialized = true;

			/** Init the hash maps */
			loadData();
		} else {
			System.out.println("Did not re-init library. Current lib in use.");
		}

		/** When data is initialized, do something else.. if needed */
		// ----------------------------------------------//
		// ----------------------------------------------//
		// ----------------------------------------------//
		// ----------------------------------------------//
		// ----------------------------------------------//
	}

	/**
	 * @author Justin Goulet
	 * @description Loads the data from where-ever it is stored
	 */
	/**
	 * @deprecated Does not initialie any test data anymore
	 */
	private static void loadData() {
		/** For now, just create empty maps, then add test data to them */
		testMap = new HashMap<String, Test>();
		questionMap = new HashMap<String, Question>();
		courseMap = new HashMap<String, Course>();

		addTestData();
	}

	/**
	 * @author Justin Goulet
	 * @description Adds sample data for testing purposes
	 * @deprecated
	 */
	private static void addTestData() {
		testQuestion();
		sampleCourse();
		sampleTest();
	}

	/**
	 * @author Justin Goulet
	 * @param ID
	 *            - Identifier of currentCourse
	 * @description Compiles the information/data that is relative to the
	 *              current course
	 * @deprecated
	 */
	public static void gatherCourseMaterialsForID(String ID) {

		/**
		 * First, locate all of the tests within the library and add all of the
		 * questions to the currentCourseLib
		 */
		LibraryController._currentClassID = ID; // Set the currentClassID value

		/**
		 * The way we are currently loading the course, DNE, we have to now sort
		 * the questions for this particular course. This should be done as we
		 * read them in
		 */
		LibraryController._currentQuestionsInCourse.clear(); // Remove all
		// questions
		// from the
		// currentClass
		// Question map

		for (Question currentQuestion : LibraryController.questionMap.values()) {
			/** We need to gather the courseID from the question */
			// If the testID of the question equals the currentTestID, we can
			// add it to the map
			if (ID.equalsIgnoreCase(currentQuestion.getCourseID()))
				LibraryController._currentQuestionsInCourse.put(currentQuestion.getID(), currentQuestion);
		}

	}

	/**
	 * @author Justin Goulet
	 * @description Creates a test question to ensure the question class works
	 *              as expected
	 * @deprecated
	 */
	private static void testQuestion() {

		// Initialize the question using the first constructor
		Question aSampleQuestion = new Question("What is 5 * 5?");

		// Add some possible answers
		aSampleQuestion.addAnswer("15");
		aSampleQuestion.addAnswer("25");
		aSampleQuestion.addAnswer("5");
		aSampleQuestion.addAnswer("10");

		// Tell the program where the correct answer lies within the array
		aSampleQuestion.setCorrectIndex(1);

		// Display the question in its entirety
		// System.out.println(aSampleQuestion.toString());

		// Add another question using the other constructor
		Question anotherQuestion = new Question("What is 5-5?", new String[] { "3", "4", "1", "7", "10", "0" }, 5);
		// System.out.println(anotherQuestion.toString());

		// Add the sample questions to the hashmap
		questionMap.put("1", aSampleQuestion);
		questionMap.put("2", anotherQuestion);
	}

	/**
	 * @author Justin Goulet
	 * @description Creates a sample course to ensre the course class works as
	 *              expected
	 * @NOTE Not yet completed. Awaiting Test Class
	 * @deprecated
	 */
	private static void sampleCourse() {

	}

	/**
	 * @author Justin Goulet
	 * @description Creates a sample test using the existing questions in teh
	 *              map. Note that all questions will currently be added.
	 * @deprecated
	 */
	private static void sampleTest() {
		Test newTest = new Test("Sample Exam");

		// Create arraylist from hashmap
		questionsInExam = new ArrayList<Question>(questionMap.values());

		// Add the arraylist to the exam
		newTest.setQuestionList(questionsInExam);

		int lengthOfTest = newTest.toString().length();
		String test = (lengthOfTest > 0) ? newTest.toString() : "No test Avail";

		// Print the exam
		System.out.print("Exam: " + test);
	}

	/**
	 * @author Justin Goulet
	 * @return Customized String that displays all of the relative data within
	 *         the class
	 */
	@Override
	public String toString() {

		// Create the string for questions
		String questionString = "Questions in Library: \n--------------------";

		// Add the question hashmap to the question string
		for (Question questionInLib : questionMap.values()) {
			questionString += "\n" + questionInLib + "\n";
		}

		// For now, just return the question string
		return questionString;
	}

	/** Accesors */
	/** Get the number of Questions currently in the library
	 * @author Justin Goulet
	 * @return amount of total questions in Library
	 */
	public int getTotalQuestionCount() {
		return LibraryController.questionMap.size();
	}

	/** Get the number of Courses currently in the library
	 * @author Justin Goulet
	 * @return amount of total courses in Library
	 */
	public int getTotalCourseCount() {
		return LibraryController.courseMap.size();
	}

	/** Get the number of Tests currently in the library
	 * @author Justin Goulet
	 * @return amount of total tests in Library
	 */
	public int getTotalTestCount() {
		return LibraryController.testMap.size();
	}

	/**
	 * Is a particular ID string the ID of a Question
	 * @param checkID An ID String of a Question, or other Registerable object
	 * @return true if the ID is a Question in this library
	 */
	public boolean isAQuestion(String checkID){
		return questionMap.containsKey(checkID);
	}

	/**
	 * Is a particular ID string the ID of a Test
	 * @param checkID An ID String of a Test, or other Registerable object
	 * @return true if the ID is a Test in this library
	 */
	public boolean isATest(String checkID){
		return testMap.containsKey(checkID);		
	}

	/**
	 * Is a particular ID string the ID of a Course
	 * @param checkID An ID String of a Course, or other Registerable object
	 * @return true if the ID is a Course in this library
	 */
	public boolean isACourse(String checkID){
		return courseMap.containsKey(checkID);		
	}

	/**
	 * Gives a String of class of Registerable object an ID refers to in this library.
	 * @param checkID The unique ID String of a Registerable object.
	 * @return A String representing the class of the object ID given if it is in the Library. "Question", "Test", "Course" or "Unregistered"
	 */
	public String classNameOf(String checkID){
		if (isAQuestion(checkID))
			return "Question";
		if (isATest(checkID))
			return "Test";
		if (isACourse(checkID))
			return "Course";
		return "Unregistered";
	}

	/**
	 * Backup the library to a file.
	 * @param filename A String of the filename to backup to.
	 * @return True for success
	 */
	public static boolean backupLibrary(String filename){
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename))){
			os.writeObject(questionMap);
			os.writeObject(testMap);
			os.writeObject(courseMap);
			os.writeObject(progressMap);
		}
		catch (java.io.IOException ex){
			return false;
		}
		return true;
	}

	/**
	 * Restore the Library from a file
	 * @param filename A String of the filename to restore from.
	 * @return True for success
	 */
	public static boolean restoreLibrary(String filename){
		try(ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename))){
			questionMap = (HashMap <String,Question>)is.readObject();
			testMap = (HashMap <String,Test>)is.readObject();
			courseMap = (HashMap <String,Course>)is.readObject();
			progressMap = (HashMap <String, CourseProgress>)is.readObject();
		}
		catch (java.lang.ClassNotFoundException ex){
			return false;
		}
		catch (java.io.IOException ex){
			return false;
		}
		return true;
	}

	/**
	 * Get a reference to a Question in the array of Questions.
	 * 
	 * @param queryID
	 *            Unique ID string to look for
	 * @return Reference to the requested Question or null for failure.
	 * @author Steven Clark
	 */
	public static Question retrieveQuestion(String queryID) {
		return LibraryController.questionMap.get(queryID);
	}

	/**
	 * Get a reference to a Test in the array of Tests.
	 * 
	 * @param queryID
	 *            Unique ID string to look for
	 * @return Reference to the requested test or null for failure.
	 * @author Steven Clark
	 */
	public static Test retrieveTest(String queryID){
		Test rvalue = testMap.get(queryID);
		rvalue.initQuestions();
		return rvalue;
	}
	/**
	 * Get a reference to a Test in the set of Tests, without necessarily valid Question references.
	 * @param queryID Unique ID string to look for
	 * @return Reference to the requested test or null for failure.
	 * @author Steven Clark
	 */
	public static Test previewTest(String queryID){
		return testMap.get(queryID);
	}

	/**
	 * Get a reference to a Course in the array of Courses.
	 * 
	 * @param queryID
	 *            Unique ID string to look for
	 * @return Reference to the requested Course or null for failure.
	 * @author Steven Clark
	 */
	public static Course retrieveCourse(String queryID) {
		return LibraryController.courseMap.get(queryID);
	}
	
	/**
	 * Retrieve a live reference to a CourseProgress from the Library given it's ID String.
	 * @param queryID A unique ID string for a CourseProgress
	 * @return A live CourseProgress found in the library with that ID.
	 */
	public static CourseProgress retrieveProgress(String queryID){
		return LibraryController.progressMap.get(queryID);
	}

	/**
	 * Store or update the library representation of a given Question
	 * 
	 * @param updateThing
	 *            reference to a Question to store in the LibraryController
	 */
	public static void storeQuestion(Question updateThing) {
		// System.out.println("Question ID: " + updateThing.getID() +
		// "\nQuestion: " + updateThing);
		LibraryController.questionMap.put(updateThing.getID(), updateThing);
	}

	/**
	 * Store or update the library representation of a given Test
	 * 
	 * @param updateThing
	 *            reference to a Test to store in the LibraryController
	 */
	public static void storeTest(Test updateThing) {
		LibraryController.testMap.put(updateThing.getID(), updateThing);
	}

	/**
	 * Store or update the library representation of a given Course
	 * 
	 * @param updateThing
	 *            reference to a Course to store in the LibraryController
	 */
	public static void storeCourse(Course updateThing) {
		LibraryController.courseMap.put(updateThing.getID(), updateThing);
	}
	
	/**
	 * Store or update the library representation of a given CourseProgress
	 * 
	 * @param updateThing
	 *            reference to a CourseProgress to store in the LibraryController
	 */
	public static void storeProgress(CourseProgress updated){
		LibraryController.progressMap.put(updated.getID(), updated);
	}

	/**
	 * Permanently remove a Question from the Library
	 * @param delthing An ID string of a Question to remove.
	 */
	public static void deleteQuestion(String delthing){
		questionMap.remove(delthing);
	}

	/**
	 * Permanently remove a Test from the Library
	 * @param delthing An ID string of a Test to remove.
	 */
	public static void deleteTest(String delthing){
		testMap.remove(delthing);
	}

	/**
	 * Permanently remove a Course from the Library
	 * @param delthing An ID string of a Course to remove.
	 */
	public static void deleteCourse(String delthing){
		courseMap.remove(delthing);
	}
	
	/**
	 * Permanently remove a CourseProgress from the Library
	 * @param delthing An ID string of a CourseProgress to remove.
	 */
	public static void deleteProgress(String delID){
		progressMap.remove(delID);
	}

	//Get a list of all of the course names
	public static String[] getAllCoursesAvailable(){
		ArrayList<String> thisList = new ArrayList<String>();

		//Add all of the course to it
		for(Course temp : LibraryController.courseMap.values()){
			System.out.println("Course Values: " + temp.getName());
			thisList.add(temp.getName());
			//System.out.println(temp.getName());
		}

		//Return the list of names
		return thisList.toArray(new String[thisList.size()]);
	}

	//Get a list of all of the course idens
	public static String[] getAllCoursesAvailableIDs(){
		ArrayList<String> thisList = new ArrayList<String>();

		//Add all of the course to it
		for(Course temp : LibraryController.courseMap.values()){
			System.out.println("Course Values (ID): " + temp.getID());
			thisList.add(temp.getID());
		}

		//Return the list of names
		return thisList.toArray(new String[thisList.size()]);
	}

	//Get a list of all questions in a particular course. If null, get all questions
	public static String[] getAllQuestionsInCourse(String courseID){

		//Create the arraylist
		ArrayList<String> tempList = new ArrayList<String>();

		for(Question temp : questionMap.values()){
			System.out.println("Question Values: " + temp.getQuestion());
			if(courseID.length() > 0){
				//If not null, only do the course ID specified
				if(temp.getCourseID().equals(courseID)){
					tempList.add(temp.getQuestion());
				}
			}else{
				tempList.add(temp.getQuestion());
			}
		}
		return tempList.toArray(new String[tempList.size()]);

	}

	//Get a list of all question IDs in a particular course. If null, get all questions
	public static String[] getAllQuestionsInCourseID(String courseID){

		//Create the arraylist
		ArrayList<String> tempList = new ArrayList<String>();

		for(Question temp : questionMap.values()){
			System.out.println("Question Values (ID): " + temp.getID());
			if(courseID.length() > 0){
				//If not null, only do the course ID specified
				if(temp.getCourseID().equals(courseID)){
					tempList.add(temp.getID());
				}
			}else{
				tempList.add(temp.getID());
			}
		}
		return tempList.toArray(new String[tempList.size()]);

	}

	//Get a list of all Tests in a particular course. If null, get all questions
	public static String[] getAllTestsInCourse(String courseID){

		//Create the arraylist
		ArrayList<String> tempList = new ArrayList<String>();

		for(Test temp : testMap.values()){
			//System.out.println("Test Values: " + temp.getTestName());
			if(courseID.length() > 0){
				//If not null, only do the course ID specified
				if(temp.getCourseID().equals(courseID)){
					tempList.add(temp.getName());
				}
			}else{
				tempList.add(temp.getName());
			}
		}
		return tempList.toArray(new String[tempList.size()]);

	}

	//Get a list of all question IDs in a particular course. If null, get all questions
	public static String[] getAllTestsInCourseID(String courseID){

		//Create the arraylist
		ArrayList<String> tempList = new ArrayList<String>();

		for(Test temp : testMap.values()){

			System.out.println("Test Values (ID): " + temp.getName());
			if(courseID.length() > 0){
				//If not null, only do the course ID specified
				if(temp.getCourseID().equals(courseID)){
					tempList.add(temp.getID());
				}
			}else{
				tempList.add(temp.getID());
			}
		}
		return tempList.toArray(new String[tempList.size()]);

	}


}
