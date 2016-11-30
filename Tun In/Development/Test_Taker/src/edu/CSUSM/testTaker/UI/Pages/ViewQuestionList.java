package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.LibraryController;
import edu.CSUSM.testTaker.UI.CustomPage;

/**
 * This class is in the wire frame, but I'm not sure if we need it. I believe
 * it's purpose is to display a list of existing questions in the database.
 * However, the create quiz and set have the same functionality
 * 
 * 
 */

public class ViewQuestionList extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewQuestionList(String panelName, PanelType currentPanelType) {
		super(panelName, currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public ViewQuestionList(String panelName, PanelType currentPanelType, BufferedImage newImage) {
		super(panelName, currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public ViewQuestionList(String panelName, PanelType currentPanelType, String imageAddress) {
		super(panelName, currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "1", "2" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				// this.currentActions[i].addActionListener(new ViewList());
				break;
			case 1:
				// this.currentActions[i].addActionListener(new OpenQuizMain());
				break;
			default:
				System.out.println("Not enough implemented classes");
				break;
			}
		}
	}

	private class OpenQuestionBuilder implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());
			
			
			CustomPage questionBuilder = new CustomPage("Question Page", CustomPage.PanelType.QUESTION_BUILDER_TYPE);
			questionBuilder.parentController = parentController;
			parentController.displayView(questionBuilder);
		}
	}

}
