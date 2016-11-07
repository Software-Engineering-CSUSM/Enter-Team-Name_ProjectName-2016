package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

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
		setButtonNames(new String[] { "Manage Courses", "Manage Content" });

		try {
			for (int i = 0; i < this.currentActions.length; i++) {
				switch (i) {
				case 0:
					this.currentActions[i].addActionListener(new OpenCourseManagement());
					System.out.println("Mange course button");
					break;
				case 1:
					this.currentActions[i].addActionListener(new OpenContentManagement());
					System.out.println("Mange course button");
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

	private class OpenCourseManagement implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			CourseManagementPage cm = new CourseManagementPage("Manage Courses", CustomPage.PanelType.THREE_BUTTON_TYPE);
			cm.setName("Manage Courses");
			cm.parentController = parentController;
			parentController.displayView(cm);

		}

	}

	private class OpenContentManagement implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			ContentManagementPage cm = new ContentManagementPage("Manage Content", CustomPage.PanelType.TWO_BUTTON_TYPE);
			cm.parentController = parentController;
			parentController.displayView(cm);
		}

	}

}
