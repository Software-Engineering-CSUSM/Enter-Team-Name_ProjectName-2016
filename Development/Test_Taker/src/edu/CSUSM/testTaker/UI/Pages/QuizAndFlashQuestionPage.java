package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;
import edu.CSUSM.testTaker.UI.CustomPage.PanelType;

public class QuizAndFlashQuestionPage extends CustomPage {
	/**
	 * @author Jeremy
	 *
	 * @purpose Question Page able to navigate from next and previous questions.
	 *          Has submit button for final question which takes you to a
	 *          results page. Works for both quiz and flashcards question sets
	 */
	private static final long serialVersionUID = 1L;

	// Public int to keep track of the question page numbers.
	public static int questionPageNumber = 1;

	public QuizAndFlashQuestionPage(String panelName, PanelType currentPanelType) {
		super(panelName, currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public QuizAndFlashQuestionPage(String panelName, PanelType currentPanelType, BufferedImage newImage) {
		super(panelName, currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public QuizAndFlashQuestionPage(String panelName, PanelType currentPanelType, String imageAddress) {
		super(panelName, currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	// Constructor used to create page
	public QuizAndFlashQuestionPage(String panelName, PanelType currentPanelType, PageType currentPageType) {
		super(panelName, currentPanelType, currentPageType);
		// Set the layout

		// If saving a quiz, call updateActions, otherwise, we'll be saving
		// a flashcard set.
		if (currentPageType == CustomPage.PageType.QUIZ)
			updateActions();
		if (currentPageType == CustomPage.PageType.QUIZ_MC)
		{
			updateActionsQuizMC();
		}
		else if (currentPageType == CustomPage.PageType.FLASHCARD)
			updateActionsFlashcard();
		else
			System.out.println("error");

	}

	// Button actions for quiz. Uses conditional statements to determine
	// which buttons to display: exit and next for the first page, exit,
	// previous
	// and next for every page between the first and last, and exit previous and
	// submit for the last page.
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
	
	// Button actions for quiz. Uses conditional statements to determine
	// which buttons to display: exit and next for the first page, exit,
	// previous
	// and next for every page between the first and last, and exit previous and
	// submit for the last page.
	public void updateActionsQuizMC() {
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
					this.currentActions[i].addActionListener(new NextQuestionMC());
				else if (questionPageNumber > 1)
					this.currentActions[i].addActionListener(new Previous());
				break;
			case 2:
				if (questionPageNumber < TakeQuizTakeSet.totalNumQuestions)
					this.currentActions[i].addActionListener(new NextQuestionMC());
				else
					this.currentActions[i].addActionListener(new Submit());
				break;

			default:
				System.out.println("Not enough implemented classes");
				break;
			}
		}

	}

	// Button actions for Flashcard. Uses conditional statements to determine
	// which buttons to display: exit and next for the first page, exit,
	// previous
	// and next for every page between the first and last, and exit previous and
	// submit for the last page.
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
		// Action Listener for the showHide checkbox. FlashcardAnswer
		// is a public variable and should be set by a function that
		// get the answer for the current question
		showHide.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Create PopUp object
				PopUp answer;

				// If showHide is checked then show the answer. After
				// answer box is closed, the checkbox will become unselected
				if (showHide.isSelected()) {
					FlashcardAnswer = "This is the answer";
					answer = new PopUp("FlashCard Question/Answer", PopUp.PanelType.LOGO_ONLY_TYPE, PopUpType.FlashcardAnswerPopUp);
					showHide.setSelected(false);
				}
			}
		});

	}

	// Action listener for next question button. Clicking it takes the user
	// to the next question and increments the public questionPageNumber
	private class NextQuestion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			QuizAndFlashQuestionPage questionPage = new QuizAndFlashQuestionPage("Quiz Question Page: " + QuizAndFlashQuestionPage.questionPageNumber,
					QuizAndFlashQuestionPage.PanelType.QUESTIONPAGE, QuizAndFlashQuestionPage.PageType.QUIZ);
			questionPage.parentController = parentController;
			parentController.displayView(questionPage);
			questionPageNumber++; // increment the quiz question number

		}
	}
	private class NextQuestionMC implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			QuizAndFlashQuestionPage questionPage = new QuizAndFlashQuestionPage("Quiz Question Page: " + QuizAndFlashQuestionPage.questionPageNumber,
					QuizAndFlashQuestionPage.PanelType.QUESTIONPAGEMC, QuizAndFlashQuestionPage.PageType.QUIZ_MC);
			questionPage.parentController = parentController;
			parentController.displayView(questionPage);
			questionPageNumber++; // increment the quiz question number

		}
	}

	// Dismisses the current window and returns back to the last window.
	// It also decrements the questionPageNumber
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
			QuizAndFlashMain quizPage = new QuizAndFlashMain("Quiz Page", QuizAndFlashMain.PanelType.THREE_BUTTON_TYPE);
			quizPage.parentController = parentController;
			questionPageNumber = 1;
			parentController.displayView(quizPage);

		}

	}

	// Submit the quiz and go to the results page. Also reset the
	// questionPageNumber
	private class Submit implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			QuizResultsPage results = new QuizResultsPage("Results", QuizResultsPage.PanelType.RESULTS);
			results.parentController = parentController;
			parentController.displayView(results);
			questionPageNumber = 1; // Reset the questionPageNumber
		}
	}

	// Exits the flashcard take set and return to QuizAndFlashMain
	// Also resets the questionPageNumber
	private class SubmitFlash implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			QuizAndFlashMain fcMain = new QuizAndFlashMain("Flash Card Main Page", QuizAndFlashMain.PanelType.THREE_BUTTON_TYPE,
					QuizAndFlashMain.PageType.FLASHCARD);
			fcMain.parentController = parentController;
			parentController.displayView(fcMain);
			questionPageNumber = 1; // reset questionPageNumber
		}
	}

	// Action listener for next question button. Clicking it takes the user
	// to the next question and increments the public questionPageNumber
	private class FlashNextQuestion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			QuizAndFlashQuestionPage FquestionPage = new QuizAndFlashQuestionPage("Flash Card Question Page: " + QuizAndFlashQuestionPage.questionPageNumber,
					QuizAndFlashQuestionPage.PanelType.FLASHCARDPAGE, QuizAndFlashQuestionPage.PageType.FLASHCARD);
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
			QuizAndFlashMain FlashCPage = new QuizAndFlashMain("Flash Card Page", QuizAndFlashMain.PanelType.THREE_BUTTON_TYPE,
					QuizAndFlashMain.PageType.FLASHCARD);
			FlashCPage.parentController = parentController;
			questionPageNumber = 1;
			parentController.displayView(FlashCPage);

		}

	}

}
