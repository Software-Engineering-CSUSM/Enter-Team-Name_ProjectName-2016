package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;

public class QuestionPageLast extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuestionPageLast(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();

	}

	public QuestionPageLast(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public QuestionPageLast(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "Exit Quiz", "Previous Question", "Submit and Finish" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new ExitReturnQuizMain());
				break;
			case 1:
				this.currentActions[i].addActionListener(new Previous());
				break;
			case 2:
				this.currentActions[i].addActionListener(new Submit());
				break;
			default:
				System.out.println("Not enough implemented classes");
				break;
			}
		}
	}

	// After saving quiz, return to quiz main
	private class ExitReturnQuizMain implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			// System.out.println("Opening " + this.getClass());
			QuizMain quizPage = new QuizMain(QuizMain.PanelType.THREE_BUTTON_TYPE);
			quizPage.setName("Quiz Page");
			quizPage.parentController = parentController;
			parentController.displayView(quizPage);

		}

	}

	// Return to previous question
	private class Previous implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			parentController.dismissView();

		}

	}

	// Submits quiz and calls the result page. It is currently using a
	// two button type. It needs to be updated to display the results
	// and have one button to return to the Take Quiz Page
	private class Submit implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			QuizResultsPage results = new QuizResultsPage(QuizResultsPage.PanelType.TWO_BUTTON_TYPE);
			results.setName("Results Page");
			results.parentController = parentController;
			parentController.displayView(results);

		}
	}

}
