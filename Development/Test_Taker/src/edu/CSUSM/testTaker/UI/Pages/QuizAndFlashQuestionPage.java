package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;
import edu.CSUSM.testTaker.UI.CustomPage.PageType;
import edu.CSUSM.testTaker.UI.CustomPage.PanelType;

public class QuizAndFlashQuestionPage extends CustomPage {
	/**
	 * @author Jeremy
	 *
	 * @purpose Question Page able to navigate from next and previous questions.
	 *          Has submit button for final question which takes you to a
	 *          results page
	 */
	private static final long serialVersionUID = 1L;
	public static int questionPageNumber = 1;

	public QuizAndFlashQuestionPage(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public QuizAndFlashQuestionPage(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public QuizAndFlashQuestionPage(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}
	public QuizAndFlashQuestionPage(PanelType currentPanelType, PageType currentPageType) {
		super(currentPanelType, currentPageType);
		// Set the layout
		

		// Build the contents
		if(currentPageType == CustomPage.PageType.QUIZ)
		updateActions();
		else if(currentPageType == CustomPage.PageType.FLASHCARD)
			updateActionsFlashcard();
		else
			System.out.println("error");
		

	}

	public void updateActions() {
		// Set the button names
		if (questionPageNumber == 1)
			setButtonNames(new String[] { "Exit Quiz", "Next Question" });
		else if (questionPageNumber > 1 && questionPageNumber < TakeQuizTakeSet.totalNumQuestions)
			setButtonNames(new String[] { "Exit Quiz", "Previous Question", "Next Question", });
		else if (questionPageNumber == TakeQuizTakeSet.totalNumQuestions) {
			setButtonNames(new String[] { "Exit Quiz", "Previous Question", "Submit and Finish" });
		}
		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new ExitQuiz());
				break;
			case 1:
				if (questionPageNumber == 1)
					this.currentActions[i].addActionListener(new NextQuestion());
				else if (questionPageNumber > 1)
					this.currentActions[i].addActionListener(new Previous());
				break;
			case 2:
				if (questionPageNumber < TakeQuizTakeSet.totalNumQuestions)
					this.currentActions[i].addActionListener(new NextQuestion());
				else
					this.currentActions[i].addActionListener(new Submit());
				break;

			default:
				System.out.println("Not enough implemented classes");
				break;
			}
		}
	}
	public void updateActionsFlashcard() {
		// Set the button names
		if (questionPageNumber == 1)
			setButtonNames(new String[] { "Exit Flashcard", "Next Question" });
		else if (questionPageNumber > 1 && questionPageNumber < TakeQuizTakeSet.totalNumQuestions)
			setButtonNames(new String[] { "Exit Flashcard", "Previous Question", "Next Question", });
		else if (questionPageNumber == TakeQuizTakeSet.totalNumQuestions) {
			setButtonNames(new String[] { "Exit Flashcard", "Previous Question", "Submit and Finish" });
		}
		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new ExitFlashCard());
				break;
			case 1:
				if (questionPageNumber == 1)
					this.currentActions[i].addActionListener(new FlashNextQuestion());
				else if (questionPageNumber > 1)
					this.currentActions[i].addActionListener(new Previous());
				break;
			case 2:
				if (questionPageNumber < TakeQuizTakeSet.totalNumQuestions)
					this.currentActions[i].addActionListener(new FlashNextQuestion());
				else
					this.currentActions[i].addActionListener(new SubmitFlash());
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
	private class NextQuestion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			QuizAndFlashQuestionPage questionPage = new QuizAndFlashQuestionPage(QuizAndFlashQuestionPage.PanelType.THREE_BUTTON_TYPE, QuizAndFlashQuestionPage.PageType.QUIZ);
			questionPage.setName("Quiz Question Page " + QuizAndFlashQuestionPage.questionPageNumber);
			questionPage.parentController = parentController;
			parentController.displayView(questionPage);
			questionPageNumber++; // increment the quiz question number

		}
	}

	private class Previous implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());
			questionPageNumber--; // decrement the quiz question number
			parentController.dismissView();

		}

	}

	// Take you back to the quiz main page
	private class ExitQuiz implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			// System.out.println("Opening " + this.getClass());
			QuizAndFlashMain quizPage = new QuizAndFlashMain(QuizAndFlashMain.PanelType.THREE_BUTTON_TYPE);
			quizPage.setName("Quiz Page");
			quizPage.parentController = parentController;
			questionPageNumber = 1;
			parentController.displayView(quizPage);

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
			questionPageNumber = 1; // quiz questionreset page
									// number
		}
	}
	private class SubmitFlash implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			QuizAndFlashMain fcMain = new QuizAndFlashMain(QuizAndFlashMain.PanelType.THREE_BUTTON_TYPE,QuizAndFlashMain.PageType.FLASHCARD);
			fcMain.setName("Flashcard Main Page");
			fcMain.parentController = parentController;
			parentController.displayView(fcMain);
			questionPageNumber = 1; // quiz questionreset page
									// number
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

			QuizAndFlashQuestionPage FquestionPage = new QuizAndFlashQuestionPage(
					QuizAndFlashQuestionPage.PanelType.THREE_BUTTON_TYPE, QuizAndFlashQuestionPage.PageType.FLASHCARD);
			
			FquestionPage.setName("Flashcard Question Page " + questionPageNumber);
			FquestionPage.parentController = parentController;
			parentController.displayView(FquestionPage);
			questionPageNumber++; // increment the quiz question number

		}
	}
	// Take you back to the quiz main page
	private class ExitFlashCard implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			// System.out.println("Opening " + this.getClass());
			QuizAndFlashMain FlashCPage = new QuizAndFlashMain(QuizAndFlashMain.PanelType.THREE_BUTTON_TYPE, QuizAndFlashMain.PageType.FLASHCARD);
			FlashCPage.setName("Flashcard Page");
			FlashCPage.parentController = parentController;
			questionPageNumber = 1;
			parentController.displayView(FlashCPage);

		}

	}

}
