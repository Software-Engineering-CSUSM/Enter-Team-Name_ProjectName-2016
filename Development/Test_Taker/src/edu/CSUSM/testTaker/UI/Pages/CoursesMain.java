package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import edu.CSUSM.testTaker.LibraryController;
import edu.CSUSM.testTaker.Backend.Course;
import edu.CSUSM.testTaker.UI.CustomPage;
import edu.CSUSM.testTaker.UI.CustomPage.PanelType;

public class CoursesMain extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CoursesMain(String panelName, PanelType currentPanelType) {
		super(panelName, currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public CoursesMain(String panelName, PanelType currentPanelType, BufferedImage newImage) {
		super(panelName, currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public CoursesMain(String panelName, PanelType currentPanelType, String imageAddress) {
		super(panelName, currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "Add Course", "Delete Course", "Manage Selected Course" });

		try {
			for (int i = 0; i < this.currentActions.length; i++) {
				switch (i) {
				case 0:
					this.currentActions[i].addActionListener(new AddCourse());
					break;
				case 1:
					this.currentActions[i].addActionListener(new DeleteCourse());
					break;
				case 2:
					this.currentActions[i].addActionListener(new ManageSelectedCourse());
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

	// Creates a Pop up window to add a course.  To actually add a course, implement
	// method from the PopUp class in the action listener for add course
	private class AddCourse implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			// Constructor uses a main window with just a logo type, and
			// an SaveQuiz to create the correct popUp window in the
			// PopUp class
			//PopUp popup = new PopUp( "",PopUp.PanelType.LOGO_ONLY_TYPE, PopUpType.AddCourse);

			//Display a JDialogBox
			String newTestName = JOptionPane.showInputDialog("Please name your Course: ");
			if(newTestName != null && newTestName.length() != 0){

				//Add the Course
				Course newCourse = new Course();
				newCourse.setName(newTestName);

				//After saving course name in the pop up, call courses main and refresh
				CustomPage.setqBuilderNumButtons(3);

				//After deleting a course from the pop up, call courses main and refresh
				CustomPage.setqBuilderNumButtons(3);

				CustomPage.setQBRowHeaders(LibraryController.getAllCoursesAvailable());
				CustomPage.setQBRowIDs(LibraryController.getAllCourseIDsAvailable());
				CoursesMain cm = new CoursesMain("Courses Main", CustomPage.PanelType.QUESTION_BUILDER_TYPE);
				cm.parentController = parentController;
				parentController.replaceCurrentView(cm);
				System.out.println("Value Found: " + newTestName.toString());
			}else{
				System.out.println("No Value Found");
			}


		}

	}
	// Creates a Pop up window to delete course.  To actually delete the course, implement
	// method from the PopUp class in the action listener for delete
	private class DeleteCourse implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			// Call Pop Up window to confirm deleting course.  To actually delete course upon clicking
			// "yes", implement the function in the action listener in the PopUp class
			//PopUp popup = new PopUp("",PopUp.PanelType.LOGO_ONLY_TYPE, PopUpType.DeleteCourse);
			try{
				if(ManageData.currentIDSelected.length() == 0 || ManageData.currentIDSelected == null){
					throw new NullPointerException();
				}
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete the course:\n" + LibraryController.retrieveCourse(ManageData.currentIDSelected), "Delete Course", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					//Refresh the table here

					LibraryController.deleteCourse(ManageData.currentIDSelected);

					//After deleting a course from the pop up, call courses main and refresh
					CustomPage.setqBuilderNumButtons(3);

					CustomPage.setQBRowHeaders(LibraryController.getAllCoursesAvailable());
					CustomPage.setQBRowIDs(LibraryController.getAllCourseIDsAvailable());
					CoursesMain cm = new CoursesMain("Courses Main", CustomPage.PanelType.QUESTION_BUILDER_TYPE);
					cm.parentController = parentController;
					parentController.replaceCurrentView(cm);
				}
				else {
					//Do nothing
				}
			}

			catch(NullPointerException ex){
				JOptionPane.showMessageDialog(null, "Please select a course before continuing");
			}finally{
				LibraryController.initDB();
			}

		}

	}

	private class ManageSelectedCourse implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//System.out.println("Opening " + this.getClass() + "\nID: " + ManageData.currentIDSelected);

			try{
				//Check to see if a test is selected. If not, alert the user they must seect one
				if(ManageData.currentIDSelected == null || ManageData.currentIDSelected.length() == 0){
					throw new NullPointerException();
				}else{
					CustomPage.setqBuilderNumButtons(3);

					//Set the question list based on teh test ID just gathered
					CustomPage.setQBRowHeaders(LibraryController.getAllTestNamesInCourse(ManageData.currentIDSelected));
					CustomPage.setQBRowIDs(LibraryController.getAllTestIDsInCourse(ManageData.currentIDSelected));


					TestListManager.setCourse(LibraryController.retrieveCourse(ManageData.currentIDSelected));
					TestListManager tm = new TestListManager("Test List Manager", CustomPage.PanelType.QUESTION_BUILDER_TYPE);
					System.out.println("Current ID Selected: " + ManageData.currentIDSelected);
					//tm.setName("Test List Manager");
					ManageData.resetButtons();
					tm.parentController = parentController;
					parentController.displayView(tm);
				}
			}
			catch(NullPointerException ex){
				JOptionPane.showMessageDialog(null, "Please select a course before continuing");
			}


		}

	}



}
