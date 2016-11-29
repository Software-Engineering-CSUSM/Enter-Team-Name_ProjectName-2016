package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.LibraryController;
import edu.CSUSM.testTaker.Backend.Test;
import edu.CSUSM.testTaker.Backend.Question;
import edu.CSUSM.testTaker.UI.CustomPage;
import edu.CSUSM.testTaker.UI.CustomPage.PanelType;

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



	private static final long serialVersionUID = 1L;

	public TakeQuizTakeSet(String panelName, PanelType currentPanelType) {
		super(panelName, currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public TakeQuizTakeSet(String panelName, PanelType currentPanelType, BufferedImage newImage) {
		super(panelName, currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public TakeQuizTakeSet(String panelName, PanelType currentPanelType, String imageAddress) {
		super(panelName, currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	// Constructor used to create page
	public TakeQuizTakeSet(String panelName, PanelType currentPanelType, PageType currentPageType) {
		super(panelName, currentPanelType, currentPageType);
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
		setButtonNames(new String[] { "Take Selected Quiz"});

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				QuizAndFlashQuestionPage.questionPageNumber = 1;
				this.currentActions[i].addActionListener(new StartQuiz());
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
		setButtonNames(new String[] { "Take Selected Set"});

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				QuizAndFlashQuestionPage.questionPageNumber = 1;
				this.currentActions[i].addActionListener(new StartSet());
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

			// This is for short answer quiz			 
			
			/*QuizAndFlashQuestionPage QquestionPage = new QuizAndFlashQuestionPage("Quiz Question Page: " + QuizAndFlashQuestionPage.questionPageNumber,
					QuizAndFlashQuestionPage.PanelType.QUESTIONPAGE, QuizAndFlashQuestionPage.PageType.QUIZ);
			QquestionPage.parentController = parentController;
			parentController.displayView(QquestionPage);
			QuizAndFlashQuestionPage.questionPageNumber++; */
			
			
			intializeResultChecker();	
			
			//LibraryController.CURRENT_TEST = (Test)LibraryController.getItem(ManageData.currentIDSelected);
			LibraryController.CURRENT_TEST = LibraryController.retrieveTest(ManageData.currentIDSelected);
			System.out.println(LibraryController.CURRENT_TEST.getTestName());
			System.out.println("Number of Questions: " + LibraryController.CURRENT_TEST.numQuestions());
			
			//Set the total number of questions for the current test
			totalNumQuestions = LibraryController.CURRENT_TEST.numQuestions();
			
			// Intialize random answer num array to zero.  this is done because
			// the condition to shuffle the questions is based of the sum of the 
			// random answers indexes
			for(int i = 0; i < totalNumQuestions; i++)
				for(int j = 0; j < 4; j++)
					randAnswerNum[i][j] = 0;

			QuizAndFlashQuestionPage QquestionPage = new QuizAndFlashQuestionPage("Quiz Question Page: " + QuizAndFlashQuestionPage.questionPageNumber,
					QuizAndFlashQuestionPage.PanelType.QUESTIONPAGEMC, QuizAndFlashQuestionPage.PageType.QUIZ_MC);
			QquestionPage.parentController = parentController;
			parentController.displayView(QquestionPage);
			QuizAndFlashQuestionPage.questionPageNumber++; 
								

		}

	}

	// Button listener to start the Set. Also increments the
	// questionPageNumber which keeps track of the question
	// page.  The questionPageNumber was commented out and moved into
	// the update action to be able to use show hide without a pop up.
	private class StartSet implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			
			//LibraryController.CURRENT_TEST = (Test)LibraryController.getItem(ManageData.currentIDSelected);
			LibraryController.CURRENT_TEST = LibraryController.retrieveTest(ManageData.currentIDSelected);
			System.out.println(LibraryController.CURRENT_TEST.getTestName());
			System.out.println("Number of Questions: " + LibraryController.CURRENT_TEST.numQuestions());
			
			//Set the total number of questions for the current test
			totalNumQuestions = LibraryController.CURRENT_TEST.numQuestions();
			
				QuizAndFlashQuestionPage FCquestionPage = new QuizAndFlashQuestionPage("Flash Card Question Page: " + QuizAndFlashQuestionPage.questionPageNumber,
						QuizAndFlashQuestionPage.PanelType.FLASHCARDPAGE, QuizAndFlashQuestionPage.PageType.FLASHCARD);
				FCquestionPage.parentController = parentController;
				parentController.displayView(FCquestionPage);
				
				//Save questionPageNumber incrementer in case we need to switch back
				// to pop up
				
				//QuizAndFlashQuestionPage.questionPageNumber++;
	
			
		}

	}

}
