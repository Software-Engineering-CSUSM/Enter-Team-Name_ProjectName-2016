package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;

public class FlashCardQuestionPage extends CustomPage {
	/**
	 * @author Jeremy
	 *
	 * @purpose Question Page able to navigate from next and previous questions.
	 *          Has submit button for final question which takes you to a
	 *          results page
	 */
	private static final long serialVersionUID = 1L;
	public static int FCardquestionPageNumber = 1;

	public FlashCardQuestionPage(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public FlashCardQuestionPage(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public FlashCardQuestionPage(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public void updateActions() {
		// Set the button names
		if (FCardquestionPageNumber == 1)
			setButtonNames(new String[] { "Exit Flashcard", "Next Question" });
		else if (FCardquestionPageNumber > 1 && FCardquestionPageNumber < TakeQuiz.totalNumQuestions)
			setButtonNames(new String[] { "Exit Flashcard", "Previous Question", "Next Question", });
		else if (FCardquestionPageNumber == TakeQuiz.totalNumQuestions) {
			setButtonNames(new String[] { "Exit Flashcard", "Previous Question", "Submit and Finish" });
		}
		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new ExitFlashCard());
				break;
			case 1:
				if (FCardquestionPageNumber == 1)
					this.currentActions[i].addActionListener(new FlashNextQuestion());
				else if (FCardquestionPageNumber > 1)
					this.currentActions[i].addActionListener(new Previous());
				break;
			case 2:
				if (FCardquestionPageNumber < TakeQuiz.totalNumQuestions)
					this.currentActions[i].addActionListener(new FlashNextQuestion());
				else
					this.currentActions[i].addActionListener(new Submit());
				break;

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
	private class FlashNextQuestion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			FlashCardQuestionPage FquestionPage = new FlashCardQuestionPage(
					FlashCardQuestionPage.PanelType.THREE_BUTTON_TYPE);
			System.out.println("hello");
			FquestionPage.setName("Flashcard Question Page " + FCardquestionPageNumber);
			FquestionPage.parentController = parentController;
			parentController.displayView(FquestionPage);
			FCardquestionPageNumber++; // increment the quiz question number

		}
	}

	private class Previous implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());
			FCardquestionPageNumber--; // decrement the quiz question number
			parentController.dismissView();

		}

	}

	// Take you back to the quiz main page
	private class ExitFlashCard implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			// System.out.println("Opening " + this.getClass());
			FlashCardMain FlashCPage = new FlashCardMain(FlashCardMain.PanelType.THREE_BUTTON_TYPE);
			FlashCPage.setName("Flashcard Page");
			FlashCPage.parentController = parentController;
			parentController.displayView(FlashCPage);

		}

	}

	private class Submit implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			QuizResultsPage results = new QuizResultsPage(QuizResultsPage.PanelType.TWO_BUTTON_TYPE);
			results.setName("Results Page");
			results.parentController = parentController;
			parentController.displayView(results);
			FCardquestionPageNumber = 1; // quiz questionreset page
			// number
		}
	}

}
