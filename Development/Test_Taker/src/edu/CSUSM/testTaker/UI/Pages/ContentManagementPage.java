package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.LibraryController;
import edu.CSUSM.testTaker.UI.CustomPage;
import edu.CSUSM.testTaker.UI.CustomPage.PanelType;

public class ContentManagementPage extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContentManagementPage(String panelName, PanelType currentPanelType) {
		super(panelName, currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public ContentManagementPage(String panelName, PanelType currentPanelType, BufferedImage newImage) {
		super(panelName, currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public ContentManagementPage(String panelName, PanelType currentPanelType, String imageAddress) {
		super(panelName, currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "Manage Quizzes", "Take Quizzes" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new OpenCourseManagement());
				break;
			case 1:
				this.currentActions[i].addActionListener(new OpenQuestionBuilder());
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
			// System.out.println("Opening " + this.getClass());

			CourseManagementPage cm = new CourseManagementPage("Manage Content", CustomPage.PanelType.TWO_BUTTON_TYPE);
			cm.parentController = parentController;
			parentController.displayView(cm);
		}

	}

	private class OpenQuestionBuilder implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());
			
			//Set row Headers and idens
			CustomPage.setQBRowHeaders(LibraryController.getAllQuestionsInCourse(""));
			CustomPage.setQBRowIDs(LibraryController.getAllQuestionsInCourseID(""));
			
			CustomPage questionBuilder = new CustomPage("Question Manager", CustomPage.PanelType.QUESTION_BUILDER_TYPE);
			questionBuilder.parentController = parentController;
			parentController.displayView(questionBuilder);
		}

	}

}
