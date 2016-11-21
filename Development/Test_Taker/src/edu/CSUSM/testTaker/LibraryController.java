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
	
	//Save current data for access elsewhere
	public static Course CURRENT_COURSE;
	public static Test CURRENT_TEST;
	public static Question CURRENT_QUESTION;

	static {
		if(!checkForDB()){
			initDB();
		}
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
	 * Is the given ID in the database
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
	 * Inserts or updates an item in the database
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
	 * Get a live reference to an object from the database.
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
	 * Get the name of an object in the database without instantiating it.
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
	 * Get a String describing the type of an object in the database without instantiating it.
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
	 * Remove an object from the database
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
	 * Give all IDs of Courses in the database
	 * @return an ArrayList of ID Strings for all Courses in the database.
	 */
	public static ArrayList<String> getCourseItemIDs(){
		ArrayList<String> rval = null;
		try(Connection dbcon = connect()){
			ResultSet myresults = dbcon.createStatement().executeQuery("select ID from REGISTRY where TYPENAME = 'Course';");
			rval = new ArrayList<String>();
			while(myresults.next()){
				rval.add(new String(myresults.getBytes(1)));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rval;
	}
	
	/**
	 * Give all IDs of CourseProgresses in the database
	 * @return an ArrayList of ID Strings for all CourseProgresses in the database.
	 */
	public static ArrayList<String> getProgressItemIDs(){
		ArrayList<String> rval = null;
		try(Connection dbcon = connect()){
			ResultSet myresults = dbcon.createStatement().executeQuery("select ID from REGISTRY where TYPENAME = 'CourseProgress';");
			rval = new ArrayList<String>();
			while(myresults.next()){
				rval.add(new String(myresults.getBytes(1)));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rval;
	}
	/**
	 * Give all IDs of Tests in the database
	 * @return an ArrayList of ID Strings for all Tests in the database.
	 * @deprecated
	 */
	public static ArrayList<String> getTestItemIDs(){
		ArrayList<String> rval = null;
		try(Connection dbcon = connect()){
			ResultSet myresults = dbcon.createStatement().executeQuery("select ID from REGISTRY where TYPENAME = 'Test';");
			rval = new ArrayList<String>();
			while(myresults.next()){
				rval.add(new String(myresults.getBytes(1)));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rval;
	}

	/**
	 * Give all IDs of Tests in the database
	 * @return an ArrayList of ID Strings for all Tests in the database.
	 * @deprecated
	 */
	public static ArrayList<String> getQuestionItemIDs(){
		ArrayList<String> rval = null;
		try(Connection dbcon = connect()){
			ResultSet myresults = dbcon.createStatement().executeQuery("select ID from REGISTRY where TYPENAME = 'Question';");
			rval = new ArrayList<String>();
			while(myresults.next()){
				rval.add(new String(myresults.getBytes(1)));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rval;
	}

	
	/**
	 * Give all Names of Courses in the database
	 * @return an ArrayList of name Strings for all Courses in the database.
	 */
	public static ArrayList<String> getCourseItemNames(){
		ArrayList<String> rval = null;
		try(Connection dbcon = connect()){
			ResultSet myresults = dbcon.createStatement().executeQuery("select NAME from REGISTRY where TYPENAME = 'Course';");
			rval = new ArrayList<String>();
			while(myresults.next()){
				rval.add(myresults.getString(1));
			}			
		}catch(SQLException e){
			e.printStackTrace();
		}		
		return rval;
	}
	
	/**
	 * Give all Names of CourseProgresses in the database
	 * @return an ArrayList of name Strings for all CourseProgresses in the database.
	 */
	public static ArrayList<String> getProgressItemNames(){
		ArrayList<String> rval = null;
		try(Connection dbcon = connect()){
			ResultSet myresults = dbcon.createStatement().executeQuery("select NAME from REGISTRY where TYPENAME = 'CourseProgress';");
			rval = new ArrayList<String>();
			while(myresults.next()){
				rval.add(myresults.getString(1));
			}			
		}catch(SQLException e){
			e.printStackTrace();
		}		
		return rval;
	}
	
	/**
	 * Give all Names of Tests in the database
	 * @return an ArrayList of name Strings for all Tests in the database.
	 * @deprecated
	 */
	public static ArrayList<String> getTestItemNames(){
		ArrayList<String> rval = null;
		try(Connection dbcon = connect()){
			ResultSet myresults = dbcon.createStatement().executeQuery("select NAME from REGISTRY where TYPENAME = 'Test';");
			rval = new ArrayList<String>();
			while(myresults.next()){
				rval.add(myresults.getString(1));
			}			
		}catch(SQLException e){
			e.printStackTrace();
		}		
		return rval;
	}

	/**
	 * Give all Names of Tests in the database
	 * @return an ArrayList of name Strings for all Tests in the database.
	 * @deprecated
	 */
	public static ArrayList<String> getQuestionItemNames(){
		ArrayList<String> rval = null;
		try(Connection dbcon = connect()){
			ResultSet myresults = dbcon.createStatement().executeQuery("select NAME from REGISTRY where TYPENAME = 'Question';");
			rval = new ArrayList<String>();
			while(myresults.next()){
				rval.add(myresults.getString(1));
			}			
		}catch(SQLException e){
			e.printStackTrace();
		}		
		return rval;
	}

	
	/**
	 * Get a list of Registerable items from a Collection for their IDs
	 * @param terms A Collection of unique ID Strings
	 * @return An ArrayList of Registerable objects pulled from the database.
	 */
	public static ArrayList<Registerable> getItemsForIDs(Collection <String> terms){
		ArrayList <Registerable> rval = null;
		if(terms != null && !terms.isEmpty()){
			rval = new ArrayList<Registerable>(terms.size());
			for(String termID : terms){
				rval.add(getItem(termID));
			}
		}
		return rval;
	}
	
	/**
	 * Get a list of names from a collection of their IDs
	 * @param terms a Collection of ID strings
	 * @return an ArrayList of Name strings pulled from the database.
	 */
	public static ArrayList<String> getNamesForIDs(Collection <String> terms){
		ArrayList <String> rval = null;
		if(terms != null && !terms.isEmpty()){
			rval = new ArrayList<String>(terms.size());
			for(String termid : terms){
				rval.add(getItemName(termid));
			}
		}
		return rval;
	}
	

	/**
	 * Gives an ArrayList view of the ID strings of all Courses in the library.
	 * @return a reference to a Set containing all Course ID strings.
	 */
	public static ArrayList<String> giveCourseSet(){
		return getCourseItemIDs();
	}
	
	/**
	 * Gives an ArrayList container of every question in the entire library
	 * @return a Set of Questions
	 */
	public static ArrayList<Question> giveAllQuestions(){
		ArrayList <Question> rval = null;
		try(Connection dbcon = connect()){
			Statement dbquery = dbcon.createStatement();
			ResultSet results = dbquery.executeQuery("select DATA from REGISTRTY where TYPENAME = 'Question';");
			ArrayList <Question> newlist = new ArrayList<Question>();
			while(results.next()){
				newlist.add((Question)results.getObject(1));
			}
			rval = newlist;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rval;
	}

	/**
	 * Gives a List of the current Course IDs
	 * @return An ArrayList of the ID strings of the current Courses in the Library.
	 */
	public static ArrayList<CourseInfo> giveCourseList(){
		ArrayList<CourseInfo> rlist = new ArrayList<CourseInfo>();
		ArrayList<Course> everydamncourse = getAllCourses();
		for(Course aCourse : everydamncourse){
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
		return getCourseItemIDs().iterator();
	}

	/**
	 * Gives an iterator of all Tests in the library.
	 * @return A String Iterator over all Tests in the library.
	 * @deprecated access Tests and Questions through courses that use them so they have some context
	 */
	public static Iterator<String> testIDIterator(){
		return getTestItemIDs().iterator();
	}

	/**
	 * Gives an iterator of all Questions in the library.
	 * @return A String Iterator over all Questions in the library.
	 * @deprecated access Tests and Questions through courses that use them so they have some context
	 */
	public static Iterator<String> questionIDIterator(){
		return getQuestionItemIDs().iterator();
	}

	/**
	 * Gives an iterator over all CourseProgresses in the database
	 * @return A String Collection Iterator over the IDS of all CourseProgress items
	 */
	public static Iterator<String> progressIDIterator(){
		return getProgressItemIDs().iterator();
	}
	
	/** Gets an array of references to live copies of all Courses in the library
	 * @return An ArrayList of all available courses
	 * Use this sparingly.
	 */
	public static ArrayList<Course> getAllCourses(){
		ArrayList<Course> rval = null;
		try(Connection dbcon = connect()){
			Statement dbquery = dbcon.createStatement();
			ResultSet results = dbquery.executeQuery("select DATA from REGISTRTY where TYPENAME = 'Course';");
			ArrayList <Course> newlist = new ArrayList<Course>();
			while(results.next()){
				newlist.add((Course)results.getObject(1));
			}
			rval = newlist;
		}catch(SQLException e){
			e.printStackTrace();
		}		
		
		return rval;
	}

	/** Accesors */
	/** Get the number of Questions currently in the library
	 * @author Justin Goulet
	 * @return amount of total questions in Library
	 */
	public static int getTotalQuestionCount() {
		try(ResultSet results = connect().createStatement().executeQuery("select ID from REGISTRY where TYPENAME = 'Question';")){
			results.afterLast();
			return results.getRow();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}

	/** Get the number of Courses currently in the library
	 * @author Justin Goulet
	 * @return amount of total courses in Library
	 */
	public static int getTotalCourseCount() {
		try(ResultSet results = connect().createStatement().executeQuery("select ID from REGISTRY where TYPENAME = 'Course';")){
			results.afterLast();
			return results.getRow();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}

	/** Get the number of Tests currently in the library
	 * @author Justin Goulet
	 * @return amount of total tests in Library
	 */
	public static int getTotalTestCount() {
		try(ResultSet results = connect().createStatement().executeQuery("select ID from REGISTRY where TYPENAME = 'Test';")){
			results.afterLast();
			return results.getRow();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Is a particular ID string the ID of a Question
	 * @param checkID An ID String of a Question, or other Registerable object
	 * @return true if the ID is a Question in this library
	 */
	public static boolean isAQuestion(String checkID){
		return getItemType(checkID).equals("Question");
	}

	/**
	 * Is a particular ID string the ID of a Test
	 * @param checkID An ID String of a Test, or other Registerable object
	 * @return true if the ID is a Test in this library
	 */
	public static boolean isATest(String checkID){
		return getItemType(checkID).equals("Test");
	}

	/**
	 * Is a particular ID string the ID of a Course
	 * @param checkID An ID String of a Course, or other Registerable object
	 * @return true if the ID is a Course in this library
	 */
	public static boolean isACourse(String checkID){
		return getItemType(checkID).equals("Course");
	}

	/**
	 * Gives a String of class of Registerable object an ID refers to in this library.
	 * @param checkID The unique ID String of a Registerable object.
	 * @return A String representing the class of the object ID given if it is in the Library. "Question", "Test", "Course" or "Unregistered"
	 */
	public static String classNameOf(String checkID){
		return getItemType(checkID);
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
		Registerable rval = getItem(queryID);
		if(rval instanceof Question)
			return (Question) rval;
		return null;
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
		Registerable rval = getItem(queryID);
		if(rval instanceof Test){
			((Test)rval).initQuestions();
			return (Test) rval;
		}
		return null;
	}
	/**
	 * Get a reference to a Test in the set of Tests, without necessarily valid Question references.
	 * @param queryID Unique ID string to look for
	 * @return Reference to the requested test or null for failure.
	 * @author Steven Clark
	 */
	public static Test previewTest(String queryID){
		Registerable rval = getItem(queryID);
		if(rval instanceof Test)
			return (Test) rval;
		return null;
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
		Registerable rval = getItem(queryID);
		if(rval instanceof Course)
			return (Course) rval;
		return null;
	}
	
	/**
	 * Retrieve a live reference to a CourseProgress from the Library given it's ID String.
	 * @param queryID A unique ID string for a CourseProgress
	 * @return A live CourseProgress found in the library with that ID.
	 */
	public static CourseProgress retrieveProgress(String queryID){
		Registerable rval = getItem(queryID);
		if(rval instanceof CourseProgress)
			return (CourseProgress) rval;
		return null;
	}

	/**
	 * Store or update the library representation of a given Question
	 * 
	 * @param updateThing
	 *            reference to a Question to store in the LibraryController
	 */
	public static void storeQuestion(Question updateThing) {
		putItem(updateThing);
	}

	/**
	 * Store or update the library representation of a given Test
	 * 
	 * @param updateThing
	 *            reference to a Test to store in the LibraryController
	 */
	public static void storeTest(Test updateThing) {
		putItem(updateThing);
	}

	/**
	 * Store or update the library representation of a given Course
	 * 
	 * @param updateThing
	 *            reference to a Course to store in the LibraryController
	 */
	public static void storeCourse(Course updateThing) {
		putItem(updateThing);
	}
	
	/**
	 * Store or update the library representation of a given CourseProgress
	 * 
	 * @param updateThing
	 *            reference to a CourseProgress to store in the LibraryController
	 */
	public static void storeProgress(CourseProgress updated){
		putItem(updated);
	}

	/**
	 * Permanently remove a Question from the Library
	 * @param delthing An ID string of a Question to remove.
	 */
	public static void deleteQuestion(String delthing){
		deleteItem(delthing);
	}

	/**
	 * Permanently remove a Test from the Library
	 * @param delthing An ID string of a Test to remove.
	 */
	public static void deleteTest(String delthing){
		deleteItem(delthing);
	}

	/**
	 * Permanently remove a Course from the Library
	 * @param delthing An ID string of a Course to remove.
	 */
	public static void deleteCourse(String delthing){
		deleteItem(delthing);
	}
	
	/**
	 * Permanently remove a CourseProgress from the Library
	 * @param delthing An ID string of a CourseProgress to remove.
	 */
	public static void deleteProgress(String delID){
		deleteItem(delID);
	}

	/**
	 * Get an array of all of the course names
	 * @return an array of name strings of Courses
	 */	
	public static String[] getAllCoursesAvailable(){
		ArrayList<String> tval = getCourseItemNames();
		return tval.toArray(new String[tval.size()]);
	}

	/**
	 * Get a list of all of the course idens
	 * @return an array of unique IDs of Couses
	 */
	public static String[] getAllCourseIDsAvailable(){
		ArrayList<String> tval = getCourseItemIDs();
		return tval.toArray(new String[tval.size()]);
	}

	/**
	 * Get a list of all questions in a particular course. If null, get all questions
	 * @param courseID
	 * @return
	 * @deprecated
	 */
	public static Question[] getAllQuestionsInCourse(String courseID){
		ArrayList<Question> tval = null;

		if(courseID == null){
			try(ResultSet results = connect().createStatement().executeQuery("select DATA from REGISTRY where TYPENAME = 'Question';")){
				tval = new ArrayList<Question>();
				while(results.next()){
					tval.add((Question)results.getObject(1));
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
		else if(isACourse(courseID)){
			tval = ((Course)getItem(courseID)).getQuestions();
		}
		
		if(tval != null){
			return tval.toArray(new Question[tval.size()]);
		}
		return null;
	}

	/**
	 *  Get a list of all question IDs in a particular course. If null, get all questions
	 * @param courseID
	 * @return
	 * @deprecated
	 */
	public static String[] getAllQuestionIDsInCourse(String courseID){
		ArrayList<String> tval = null;

		if(courseID == null){
			tval = getQuestionItemIDs();
		}
		else if(isACourse(courseID)){
			tval = ((Course)getItem(courseID)).getQuestionIDs();
		}
		
		if(tval != null){
			return tval.toArray(new String[tval.size()]);
		}
		return null;
	}
	
	/**
	 *  get a list of names of Questions in a particular course
	 * @param courseID
	 * @return
	 * @deprecated
	 */
	public static String[] getAllQuestionNamesInCourse(String courseID){
		ArrayList<String> tval = null;

		if(courseID == null){
			tval = getQuestionItemNames();
		}
		else if(isACourse(courseID)){
			tval = getNamesForIDs(((Course)getItem(courseID)).getQuestionIDs());
		}
		
		if(tval != null){
			return tval.toArray(new String[tval.size()]);
		}
		return null;
	}

	/**
	 *  Get a list of all Tests in a particular course. If null, get all questions
	 * @param courseID
	 * @return
	 * @deprecated
	 */
	public static Test[] getAllTestsInCourse(String courseID){
		ArrayList<Test> tval = null;

		if(courseID == null){
			try(ResultSet results = connect().createStatement().executeQuery("select DATA from REGISTRY where TYPENAME = 'Test';")){
				tval = new ArrayList<Test>();
				while(results.next()){
					tval.add((Test)results.getObject(1));
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
		else if(isACourse(courseID)){
			tval = retrieveCourse(courseID).getTests();
		}
		
		if(tval != null){
			return tval.toArray(new Test[tval.size()]);
		}
		return null;
	}

	/**
	 * Get a list of all Test IDs in a particular course. If null, get all questions
	 * @param courseID
	 * @return
	 * @deprecated
	 */
	public static String[] getAllTestIDsInCourse(String courseID){
		ArrayList<String> tval = null;

		if(courseID == null){
			tval = getTestItemIDs();
		}
		else if(isACourse(courseID)){
			tval = ((Course)getItem(courseID)).getTestIDs();
		}
		
		if(tval != null){
			return tval.toArray(new String[tval.size()]);
		}
		return null;
	}

	/**
	 *  get a list of names of Questions in a particular course
	 * @param courseID
	 * @return
	 * @deprecated
	 */
	public static String[] getAllTestNamesInCourse(String courseID){
		ArrayList<String> tval = null;

		if(courseID == null){
			tval = getTestItemNames();
		}
		else if(isACourse(courseID)){
			tval = ((Course)getItem(courseID)).getTestNames();
		}
		
		if(tval != null){
			return tval.toArray(new String[tval.size()]);
		}
		return null;
	}

	/**
	 * @deprecated new method will be set.
	 * @param string
	 */
	public static void backupLibrary(String string) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @deprecated new restore function will be set
	 */
	public static void restoreLibrary(String string) {
		// TODO Auto-generated method stub
		
	}

}
