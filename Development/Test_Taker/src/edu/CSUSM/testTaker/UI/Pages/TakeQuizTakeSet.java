package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;

/**
 * @author Jeremy
 *
 * @purpose This class opens the QuizAndFlashQuestionPage for the user to take
 *          the quiz or flash card set. It is called from QuizAndFlashMain and
 *          is currently set to two button type. It needs to be built with only
 *          one button.
 */

public class TakeQuizTakeSet extends CustomPage {
	/**
	 * 
	 */

	// Total Number of questions for a quiz or flashcard set.
	// should be set by a function that gets the total number of
	// questions for each particular quiz or flashcard set
	public static int totalNumQuestions = 10;

	private static final long serialVersionUID = 1L;

	public TakeQuizTakeSet(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public TakeQuizTakeSet(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public TakeQuizTakeSet(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	// Constructor used to create page
	public TakeQuizTakeSet(PanelType currentPanelType, PageType currentPageType) {
		super(currentPanelType, currentPageType);
		// Set the layout

		// If saving a quiz, call updateActions, otherwise, we'll be saving
		// a flashcard set.
		if (currentPageType == CustomPage.PageType.QUIZ)
			updateActions();
		else if (currentPageType == CustomPage.PageType.FLASHCARD)
			updateActionsFlashcard();
		else
			System.out.println("error");

	}

	// Button Actions for quizzes
	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "Start", "Do Nothing" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				QuizAndFlashQuestionPage.questionPageNumber = 1;
				this.currentActions[i].addActionListener(new StartQuiz());
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

	// Button Actions for Flashcards
	public void updateActionsFlashcard() {

		// Set the button names
		setButtonNames(new String[] { "Start", "Do Nothing" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				QuizAndFlashQuestionPage.questionPageNumber = 1;
				this.currentActions[i].addActionListener(new StartSet());
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

	// Button listener to start the Quiz. Also increments the
	// questionPageNumber which keeps track of the question
	// page.
	private class StartQuiz implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			QuizAndFlashQuestionPage QquestionPage = new QuizAndFlashQuestionPage(
					QuizAndFlashQuestionPage.PanelType.QUESTIONPAGE, QuizAndFlashQuestionPage.PageType.QUIZ);
			QquestionPage.setName("Quiz Question Page " + QuizAndFlashQuestionPage.questionPageNumber);
			QquestionPage.parentController = parentController;
			parentController.displayView(QquestionPage);
			QuizAndFlashQuestionPage.questionPageNumber++; // Increment the
															// questionPageNumber

		}

	}

	// Button listener to start the Set. Also increments the
	// questionPageNumber which keeps track of the question
	// page.
	private class StartSet implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			QuizAndFlashQuestionPage FCquestionPage = new QuizAndFlashQuestionPage(
					QuizAndFlashQuestionPage.PanelType.FLASHCARDPAGE, QuizAndFlashQuestionPage.PageType.FLASHCARD);
			FCquestionPage.setName("Flashcard Question Page " + QuizAndFlashQuestionPage.questionPageNumber);
			FCquestionPage.parentController = parentController;
			parentController.displayView(FCquestionPage);
			QuizAndFlashQuestionPage.questionPageNumber++; // Increment the
															// questionPageNumber

		}

	}

}
