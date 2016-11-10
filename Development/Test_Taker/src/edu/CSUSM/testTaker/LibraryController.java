package edu.CSUSM.testTaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Collection;
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
	 * Gives a Set container of every question in the entire library
	 * @return a Set of Questions
	 */
	public static Collection<Question> giveAllQuestions(){
		return questionMap.values();
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

	

	// End of testing purposes

	/** Accesors */
	/** Get the number of Questions currently in the library
	 * @author Justin Goulet
	 * @return amount of total questions in Library
	 */
	public static int getTotalQuestionCount() {
		return LibraryController.questionMap.size();
	}

	/** Get the number of Courses currently in the library
	 * @author Justin Goulet
	 * @return amount of total courses in Library
	 */
	public static int getTotalCourseCount() {
		return LibraryController.courseMap.size();
	}

	/** Get the number of Tests currently in the library
	 * @author Justin Goulet
	 * @return amount of total tests in Library
	 */
	public static int getTotalTestCount() {
		return LibraryController.testMap.size();
	}

	/**
	 * Is a particular ID string the ID of a Question
	 * @param checkID An ID String of a Question, or other Registerable object
	 * @return true if the ID is a Question in this library
	 */
	public static boolean isAQuestion(String checkID){
		return questionMap.containsKey(checkID);
	}

	/**
	 * Is a particular ID string the ID of a Test
	 * @param checkID An ID String of a Test, or other Registerable object
	 * @return true if the ID is a Test in this library
	 */
	public static boolean isATest(String checkID){
		return testMap.containsKey(checkID);		
	}

	/**
	 * Is a particular ID string the ID of a Course
	 * @param checkID An ID String of a Course, or other Registerable object
	 * @return true if the ID is a Course in this library
	 */
	public static boolean isACourse(String checkID){
		return courseMap.containsKey(checkID);		
	}

	/**
	 * Gives a String of class of Registerable object an ID refers to in this library.
	 * @param checkID The unique ID String of a Registerable object.
	 * @return A String representing the class of the object ID given if it is in the Library. "Question", "Test", "Course" or "Unregistered"
	 */
	public static String classNameOf(String checkID){
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

	// Get a list of all of the course idens
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

	/// Get a list of all questions in a particular course. If null, get all questions
	public static Question[] getAllQuestionsInCourse(String courseID){
		Course coursefound = retrieveCourse(courseID);
		if(coursefound != null){
			return coursefound.getQuestions().toArray(new Question[coursefound.numQuestions()]);
		}
		else
			return (Question[])questionMap.values().toArray();
	}

	/// Get a list of all question IDs in a particular course. If null, get all questions
	public static String[] getAllQuestionsInCourseID(String courseID){
		Course coursefound = retrieveCourse(courseID);
		if(coursefound != null){
			return coursefound.getQuestionIDs().toArray(new String[coursefound.numQuestions()]);
		}
		else
			return (String[])questionMap.keySet().toArray();	
	}

	/// Get a list of all Tests in a particular course. If null, get all questions
	public static Test[] getAllTestsInCourse(String courseID){
		Course coursefound = retrieveCourse(courseID);
		if(coursefound != null){
			return coursefound.getTests().toArray(new Test[coursefound.numTests()]);
		}
		else
			return (Test[])testMap.values().toArray();
	}

	///Get a list of all question IDs in a particular course. If null, get all questions
	public static String[] getAllTestsInCourseID(String courseID){
		Course coursefound = retrieveCourse(courseID);
		if(coursefound != null){
			return coursefound.getTestIDs().toArray(new String[coursefound.numTests()]);
		}
		else
			return (String[])testMap.keySet().toArray();
	}


}
