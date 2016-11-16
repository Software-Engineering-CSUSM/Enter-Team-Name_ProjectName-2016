package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

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
	public static Course CourseID;

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
		CourseID = id;
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
			String newTestName = JOptionPane.showInputDialog("Please name your test: ");
			if(newTestName != null && newTestName.length() != 0){
				
				//Add the Course
				Test newCourse = new Test();
				newCourse.setName(newTestName);
				
				CourseID.addTest(newCourse);

				//After saving course name in the pop up, call courses main and refresh
				CustomPage.setqBuilderNumButtons(3);

				//After deleting a course from the pop up, call courses main and refresh
				CustomPage.setqBuilderNumButtons(3);

				CustomPage.setQBRowHeaders(LibraryController.getAllTestNamesInCourse(CourseID.getID()));
				CustomPage.setQBRowIDs(LibraryController.getAllTestIDsInCourse(CourseID.getID()));
	        	TestListManager cm = new TestListManager("Tests", CustomPage.PanelType.QUESTION_BUILDER_TYPE);
	        	cm.parentController = parentController;
	        	parentController.replaceCurrentView(cm);
				//System.out.println("Value Found: " + newTestName.toString());
			}else{
				System.out.println("No Value Found");
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
			
			
			//After deleting a test from the pop up, call courses main and refresh
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete the test?:\n" + LibraryController.retrieveCourse(ManageData.currentIDSelected), "Delete Course", JOptionPane.YES_NO_OPTION);
	        if (reply == JOptionPane.YES_OPTION) {
	        	//Refresh the table here

				LibraryController.deleteTest(ManageData.currentIDSelected);

				//After deleting a course from the pop up, call courses main and refresh
				CustomPage.setqBuilderNumButtons(3);

				CustomPage.setQBRowHeaders(LibraryController.getAllTestNamesInCourse(CourseID.getID()));
				CustomPage.setQBRowIDs(LibraryController.getAllTestIDsInCourse(CourseID.getID()));
	        	TestListManager cm = new TestListManager("Tests", CustomPage.PanelType.QUESTION_BUILDER_TYPE);
	        	cm.parentController = parentController;
	        	parentController.replaceCurrentView(cm);
	        }
	        else {
	           //Do nothing
	        }

		}

	}
	
	private class ManageSelectedTest implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			 System.out.println("Opening " + this.getClass());

			// Set number of buttons to two for next page
			CustomPage.setqBuilderNumButtons(2);
			
			QuestionListManager qlm = new QuestionListManager("Question List Manager", QuestionListManager.PanelType.QUESTION_BUILDER_TYPE);
			//qlm.setName("Question List Manager");
			ManageData.resetButtons();
			qlm.parentController = parentController;
			parentController.displayView(qlm);
			

		}

	}



}

