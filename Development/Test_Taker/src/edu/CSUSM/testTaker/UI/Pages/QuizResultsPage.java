package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;

public class QuizResultsPage extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuizResultsPage(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();

	}

	public QuizResultsPage(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public QuizResultsPage(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "Return to Take Quiz Page", "Does Nothing" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new ReturnTakeQuiz());
				break;
			case 1:
				// this.currentActions[i].addActionListener(new Previous());
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
			TakeQuizTakeSet quizPage = new TakeQuizTakeSet(TakeQuizTakeSet.PanelType.THREE_BUTTON_TYPE, TakeQuizTakeSet.PageType.QUIZ);
			quizPage.setName("Quiz Page");
			quizPage.parentController = parentController;
			parentController.displayView(quizPage);

		}

	}

	private class Previous implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			parentController.dismissView();

		}

	}

	// Returns to the Take Quiz Page
	private class ReturnTakeQuiz implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			TakeQuizTakeSet takeQ = new TakeQuizTakeSet(TakeQuizTakeSet.PanelType.TWO_BUTTON_TYPE, TakeQuizTakeSet.PageType.QUIZ);
			takeQ.setName("Take Quiz Page");
			takeQ.parentController = parentController;
			parentController.displayView(takeQ);

		}
	}

}
