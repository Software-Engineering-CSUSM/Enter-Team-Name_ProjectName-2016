package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.CSUSM.testTaker.LibraryController;
import edu.CSUSM.testTaker.Backend.Course;
import edu.CSUSM.testTaker.Backend.Test;
import edu.CSUSM.testTaker.UI.CustomPage;

/*
 * Display a list of all current Test (Quizzes). User can add a new Test (Quiz)
 * name with add Test (Quiz), Delete Existing Test (Quiz) from list, and manage the
 * selected Test (Quiz)
 * 
 */

public class TestListManager extends CustomPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Course CourseObj;

	public TestListManager(String panelName, PanelType currentPanelType) {
		super(panelName, currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public TestListManager(String panelName, PanelType currentPanelType, BufferedImage newImage) {
		super(panelName, currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public TestListManager(String panelName, PanelType currentPanelType, String imageAddress) {
		super(panelName, currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public static void setCourse(Course id){
		CourseObj = id;
	}

	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "Add Test (Quiz)", "Delete Test (Quiz)", "Manage Selected Test (Quiz)" });

		try {
			for (int i = 0; i < this.currentActions.length; i++) {
				switch (i) {
				case 0:
					this.currentActions[i].addActionListener(new AddTest());
					break;
				case 1:
					this.currentActions[i].addActionListener(new DeleteTest());
					break;
				case 2:
					this.currentActions[i].addActionListener(new ManageSelectedTest());
					break;
				default:
					System.out.println("Not enough implemented classes");
					break;
				}
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	private class AddTest implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			// Constructor uses a main window with just a logo type, and
			// an SaveQuiz to create the correct popUp window in the
			// PopUp class
			try{
				//Check to see if a course is selected. If not, alert the user they must seect one
				if(LibraryController.CURRENT_COURSE == null || LibraryController.CURRENT_COURSE.toString().length() == 0){
					throw new NullPointerException();
				}else{
					String newTestName = JOptionPane.showInputDialog("Please name your test: ");
					if(newTestName != null && newTestName.length() != 0){

						//Add the Course
						Test newCourse = new Test();
						newCourse.setName(newTestName);

						CourseObj.addTest(newCourse);

						//After saving course name in the pop up, call courses main and refresh
						CustomPage.setqBuilderNumButtons(3);
						/*
						ArrayList<String> testNames = new ArrayList<String>();
						for(Test tempTest : LibraryController.getAllTestsInCourse(CourseObj.getID())){
							testNames.add(tempTest.getTestName());
						}


						CustomPage.setQBRowHeaders(testNames.toArray(new String[testNames.size()]));
						CustomPage.setQBRowIDs(LibraryController.getAllTestIDsInCourse(CourseObj.getID()));*/
						
						CustomPage.setQBRowHeaders(LibraryController.CURRENT_COURSE.getTestNames().toArray(new String[LibraryController.CURRENT_COURSE.getTestNames().size()]));
						CustomPage.setQBRowIDs(LibraryController.CURRENT_COURSE.getTestIDs().toArray(new String[LibraryController.CURRENT_COURSE.getTestIDs().size()]));
						
						TestListManager cm = new TestListManager("Tests", CustomPage.PanelType.QUESTION_BUILDER_TYPE);
						cm.parentController = parentController;
						parentController.replaceCurrentView(cm);
						//System.out.println("Value Found: " + newTestName.toString());
					}else{
						System.out.println("No Value Found");
					}
				}
			}catch(NullPointerException ex){
				JOptionPane.showMessageDialog(null, "Please select a test before continuing");
			}

		}

	}

	private class DeleteTest implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			// Call Pop Up window to confirm deleting test.  To actually delete test upon clicking
			// "yes", implement the function in the action listener in the PopUp class
			//PopUp popup = new PopUp("", PopUp.PanelType.LOGO_ONLY_TYPE, PopUpType.DeleteTest);

			try{
				//Check to see if a test is selected. If not, alert the user they must seect one
				if(ManageData.currentIDSelected == null || ManageData.currentIDSelected.length() == 0){
					throw new NullPointerException();
				}else{
					
					//Get the current ID Type
					System.out.println("ID Type: " + LibraryController.getItemType(ManageData.currentIDSelected));
					
					//Set the current Test
					LibraryController.CURRENT_TEST = LibraryController.retrieveTest(ManageData.currentIDSelected);
					
					//After deleting a test from the pop up, call courses main and refresh
					int reply = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete the test?:\n" + LibraryController.CURRENT_TEST.getTestName(), "Delete Test", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						//Refresh the table here

						LibraryController.deleteTest(ManageData.currentIDSelected);
						LibraryController.CURRENT_COURSE.remove(LibraryController.CURRENT_TEST);

						//After deleting a course from the pop up, call courses main and refresh
						CustomPage.setqBuilderNumButtons(3);

						CustomPage.setQBRowHeaders(LibraryController.CURRENT_COURSE.getTestNames().toArray(new String[LibraryController.CURRENT_COURSE.getTestNames().size()]));
						CustomPage.setQBRowIDs(LibraryController.CURRENT_COURSE.getTestIDs().toArray(new String[LibraryController.CURRENT_COURSE.getTestIDs().size()]));
						TestListManager cm = new TestListManager("Tests", CustomPage.PanelType.QUESTION_BUILDER_TYPE);
						cm.parentController = parentController;
						parentController.replaceCurrentView(cm);
					}
					else {
						//Do nothing
					}
				}
			}catch(NullPointerException ex){
				JOptionPane.showMessageDialog(null, "Please select a test before continuing");
			}

		}

	}

	private class ManageSelectedTest implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//System.out.println("Opening " + this.getClass());

			try{
				//Check to see if a test is selected. If not, alert the user they must seect one
				if(ManageData.currentIDSelected == null || ManageData.currentIDSelected.length() == 0){
					throw new NullPointerException();
				}else{
					// Set number of buttons to two for next page
					CustomPage.setqBuilderNumButtons(2);

					LibraryController.CURRENT_TEST = LibraryController.retrieveTest(ManageData.currentIDSelected);
					System.out.println(LibraryController.CURRENT_TEST.getTestName());
					System.out.println("Has Questions: " + LibraryController.CURRENT_TEST.numQuestions());

					if(LibraryController.CURRENT_TEST.numQuestions() > 0){
						

						//Set the rows to all questions in teh test
						try{
							CustomPage.setQBRowHeaders(LibraryController.CURRENT_TEST.getQuestionNames().toArray(new String[LibraryController.CURRENT_TEST.getQuestionNames().size()]));
							CustomPage.setQBRowIDs(LibraryController.CURRENT_TEST.getQuestionIDList().toArray(new String[LibraryController.CURRENT_TEST.getQuestionIDList().size()]));

						}catch(Exception emp){
							emp.printStackTrace();
						}
					}
					else{
						System.out.println("No Questions Yet");
						CustomPage.setQBRowHeaders(new String[]{"No Questions Yet"});
						CustomPage.setQBRowIDs(new String[]{"No Questions Yet"});
					}

					//Save the reference to the current test
					/** Currently returning a null object */
					try{
						//LibraryController.CURRENT_TEST = LibraryController.retrieveTest(ManageData.currentIDSelected);
						System.out.println("Current ID: " + ManageData.currentIDSelected + " Current Type: " + LibraryController.getItemType(ManageData.currentIDSelected));
						//System.out.print(LibraryController.CURRENT_TEST.toString());
					}catch(Exception ex){
						System.out.println("Error getting test: " + ex.getMessage());
					}

					QuestionListManager qlm = new QuestionListManager("Question List Manager", QuestionListManager.PanelType.QUESTION_BUILDER_TYPE);

					ManageData.resetButtons();
					qlm.parentController = parentController;
					parentController.displayView(qlm);
				}
			}
			catch(NullPointerException ex){
				JOptionPane.showMessageDialog(null, "Please select a test before continuing");
			}

		}

	}



}
