package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;

public class QuizQuestionPage1 extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int counter2 = 1;
	public static int questionPageNumber = 1;

	public QuizQuestionPage1(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public QuizQuestionPage1(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public QuizQuestionPage1(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "Exit Quiz", "Next Question" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new ExitQuiz());
				break;
			case 1:
				this.currentActions[i].addActionListener(new NextQuestion()); // Getting
				break; // an
						// error

			default:
				System.out.println("Not enough implemented classes");
				break;
			}
		}
	}

	// Go to the next question. This class calls QuestionPageBetweenFirstAndLast
	// which is made to accomodate a 3 button style quiz page. This is
	// only due to the fact that the first and last page have only
	// 2 buttons. This class provides 3. Exit, Previous, and Next buttons
	private class NextQuestion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			QuestionPageBetweenFirstAndLast questionPage = new QuestionPageBetweenFirstAndLast(
					QuestionPageBetweenFirstAndLast.PanelType.THREE_BUTTON_TYPE);
			questionPage.setName("Quiz Question Page " + QuizQuestionPage1.questionPageNumber);
			questionPage.parentController = parentController;
			parentController.displayView(questionPage);
			QuizQuestionPage1.questionPageNumber++;

		}
	}

	// Take you back to the quiz main page
	private class ExitQuiz implements ActionListener {

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

}
