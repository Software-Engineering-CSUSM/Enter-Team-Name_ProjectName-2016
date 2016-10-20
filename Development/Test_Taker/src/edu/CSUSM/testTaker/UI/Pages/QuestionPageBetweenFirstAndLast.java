package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;

public class QuestionPageBetweenFirstAndLast extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuestionPageBetweenFirstAndLast(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();

	}

	public QuestionPageBetweenFirstAndLast(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public QuestionPageBetweenFirstAndLast(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "Exit Quiz", "Previous Question", "Next Question" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new ExitReturnQuizMain());
				break;
			case 1:
				this.currentActions[i].addActionListener(new Previous());
				break;
			case 2:
				this.currentActions[i].addActionListener(new NextQuestion());
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
			if (QuizQuestionPage1.questionPageNumber != 10) {
				QuizMain quizPage = new QuizMain(QuizMain.PanelType.THREE_BUTTON_TYPE);
				quizPage.setName("Quiz Page");
				quizPage.parentController = parentController;
				parentController.displayView(quizPage);
			} else {
				QuizResultsPage results = new QuizResultsPage(QuizResultsPage.PanelType.TWO_BUTTON_TYPE);
				results.setName("Results Page " + QuizQuestionPage1.questionPageNumber);
				results.parentController = parentController;
				parentController.displayView(results);

			}
		}

	}

	private class Previous implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			// System.out.println("Opening " + this.getClass());
			// QuizMain quizPage = new
			// QuizMain(QuizMain.PanelType.THREE_BUTTON_TYPE);
			// quizPage.setName("Quiz Page");
			// parentController.dismissView();
			// quizPage.parentController = parentController;
			// parentController.displayView(quizPage);
			parentController.dismissView();

		}

	}

	private class NextQuestion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			// If we get to the question before the last question
			// call the final quesiton page.
			if (QuizQuestionPage1.questionPageNumber != 10) {
				QuestionPageBetweenFirstAndLast questionPage = new QuestionPageBetweenFirstAndLast(
						QuestionPageBetweenFirstAndLast.PanelType.THREE_BUTTON_TYPE);
				questionPage.setName("Quiz Question Page " + QuizQuestionPage1.questionPageNumber);
				questionPage.parentController = parentController;
				parentController.displayView(questionPage);
				QuizQuestionPage1.questionPageNumber++;
			} else {
				QuestionPageLast lastQuestion = new QuestionPageLast(QuestionPageLast.PanelType.THREE_BUTTON_TYPE);
				lastQuestion
						.setName("Quiz Question Page " + QuizQuestionPage1.questionPageNumber + " (Final Question)");
				lastQuestion.parentController = parentController;
				parentController.displayView(lastQuestion);

			}

		}
	}

}
