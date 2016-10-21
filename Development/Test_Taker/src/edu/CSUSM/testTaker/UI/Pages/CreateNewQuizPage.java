package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;

public class CreateNewQuizPage extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateNewQuizPage(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public CreateNewQuizPage(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public CreateNewQuizPage(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "Save Quiz", "Do Nothing" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new SavePopUp());
				break;
			case 1:
				// do nothing
				break;
			default:
				System.out.println("Not enough implemented classes");
				break;
			}
		}
	}

	// Pop up to name the new quiz. Needs to implement a pop up size window
	// Currently set to two button type.
	// needs to be updated to have a text field to save the name of the
	// quiz as well as a save button
	private class SavePopUp implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			PopUp quizPage = new PopUp(PopUp.PanelType.TWO_BUTTON_TYPE, 0); // added
																			// a
																			// new
																			// constructor
																			// with
																			// an
																			// int
																			// to
																			// determine
																			// which
																			// pop
																			// up
																			// to
																			// display
			quizPage.setName("Quiz Page");
			quizPage.parentController = parentController;
			parentController.displayView(quizPage);

		}

	}

}
