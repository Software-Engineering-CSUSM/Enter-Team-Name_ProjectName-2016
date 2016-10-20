package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;

public class EditQuestionsInQuizzes extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EditQuestionsInQuizzes(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public EditQuestionsInQuizzes(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public EditQuestionsInQuizzes(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "Save", "Do Nothing" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				// this.currentActions[i].addActionListener(new
				// SaveEditedQuiz());
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

	private class SaveEditedQuiz implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			CreateNewQuizPage saveQ = new CreateNewQuizPage(CreateNewQuizPage.PanelType.TWO_BUTTON_TYPE);
			saveQ.setName("Pop up.Name New Quiz");
			saveQ.parentController = parentController;
			parentController.displayView(saveQ);

		}

	}

}
