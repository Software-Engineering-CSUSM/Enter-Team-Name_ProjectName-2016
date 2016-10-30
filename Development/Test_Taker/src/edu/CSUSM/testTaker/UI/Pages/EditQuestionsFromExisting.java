package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;
import edu.CSUSM.testTaker.UI.CustomPage.PageType;
import edu.CSUSM.testTaker.UI.CustomPage.PanelType;

public class EditQuestionsFromExisting extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EditQuestionsFromExisting(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public EditQuestionsFromExisting(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public EditQuestionsFromExisting(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}
	public EditQuestionsFromExisting(PanelType currentPanelType, PageType currentPageType) {
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
		setButtonNames(new String[] { "Save and Return to Main", "Do Nothing" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new SaveEditedQuiz());
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
	public void updateActionsFlashcard() {

		// Set the button names
		setButtonNames(new String[] { "Save and Return to Main", "Do Nothing" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new SaveEditedFlashcard());
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

	// Save edited quiz and return to Quiz Main
	private class SaveEditedQuiz implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			QuizAndFlashMain quizPage = new QuizAndFlashMain(QuizAndFlashMain.PanelType.THREE_BUTTON_TYPE);
			quizPage.setName("Quiz Main Page");
			quizPage.parentController = parentController;
			parentController.displayView(quizPage);

		}

	}
	private class SaveEditedFlashcard implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			QuizAndFlashMain FlashcardPage = new QuizAndFlashMain(QuizAndFlashMain.PanelType.THREE_BUTTON_TYPE);
			FlashcardPage.setName("Quiz Main Page");
			FlashcardPage.parentController = parentController;
			parentController.displayView(FlashcardPage);

		}

	}

}
