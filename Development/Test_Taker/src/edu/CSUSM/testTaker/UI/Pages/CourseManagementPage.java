package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.LibraryController;
import edu.CSUSM.testTaker.UI.CustomPage;
import edu.CSUSM.testTaker.UI.CustomPage.PanelType;

public class CourseManagementPage extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseManagementPage(String panelName, PanelType currentPanelType) {
		super(panelName, currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public CourseManagementPage(String panelName, PanelType currentPanelType, BufferedImage newImage) {
		super(panelName, currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public CourseManagementPage(String panelName, PanelType currentPanelType, String imageAddress) {
		super(panelName, currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "Course Management", "Other 1", "Other 2" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new OpenCourseManagement());
				break;
			case 1:
				this.currentActions[i].addActionListener(new OpenCourseManagement());
				break;
			case 2:
				this.currentActions[i].addActionListener(new OpenCourseManagement());
				break;
			default:
				System.out.println("Not enough implemented classes");
				break;
			}
		}
	}

	private class OpenCourseManagement implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());
			
			//Should show a list of all available courses
			//Set the row headers to be that of the type we are displaying (Questions, in this case)
			CustomPage.setQBRowHeaders(LibraryController.getAllCoursesAvailable());
			CustomPage.setQBRowIDs(LibraryController.getAllCourseIDsAvailable());
			CourseList courseList = new CourseList("Course List", CustomPage.PanelType.QUESTION_BUILDER_TYPE);
			courseList.parentController = parentController;
			parentController.displayView(courseList);

		}

	}
	/*
	 * 
	 * private static class OpenContentManagement implements ActionListener{
	 * 
	 * @Override public void actionPerformed(ActionEvent e) {
	 * System.out.println("Opening " + this.getClass()); }
	 * 
	 * }
	 */

}
