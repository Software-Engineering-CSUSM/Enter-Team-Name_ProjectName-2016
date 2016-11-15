package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;
import edu.CSUSM.testTaker.UI.CustomPage.PanelType;

/**
 * @author Jeremy
 *
 * @purpose This is the edit questions from existing quiz or set Page. It is
 *          called from the EditExistingQuizorSet class by clicking Edit Quiz or
 *          Edit Set from the Edit Quiz and Edit Flashcard. It's purpose is to
 *          allow the user to edit the questions from an existing quiz/set by
 *          selecting the questions for the quiz or set from a list and clicking
 *          save.
 * 
 * @date 11/1. Currently, edit quiz and edit flashcard are not building the
 *       correct panels. They need to build a jtable paneltype to allow the user
 *       to select the quiz or set to edit. Also need to implement function to
 *       save the quiz or set inside the SaveEdited classes
 * 
 */

public class EditQuestionsFromExisting extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EditQuestionsFromExisting(String panelName, PanelType currentPanelType) {
		super(panelName, currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public EditQuestionsFromExisting(String panelName, PanelType currentPanelType, BufferedImage newImage) {
		super(panelName, currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public EditQuestionsFromExisting(String panelName, PanelType currentPanelType, String imageAddress) {
		super(panelName, currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	// Constructor used to create the panel
	public EditQuestionsFromExisting(String panelName, PanelType currentPanelType, PageType currentPageType) {
		super(panelName, currentPanelType, currentPageType);
		// Set the layout

		// Build the contents. Uses condition statements to determine
		// whether to update actions for quiz or flashcard
		if (currentPageType == CustomPage.PageType.QUIZ)
			updateActions();
		else if (currentPageType == CustomPage.PageType.FLASHCARD)
			updateActionsFlashcard();
		else
			System.out.println("error");

	}

	// Update actions for Quiz type
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

	// Update actions for Flashcard type
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

	// Save edited quiz and return to Quiz Main. Need to implement function
	// to save the quiz for this actionLIstener
	private class SaveEditedQuiz implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			QuizAndFlashMain quizPage = new QuizAndFlashMain("Quiz Main Page", QuizAndFlashMain.PanelType.THREE_BUTTON_TYPE);
			quizPage.parentController = parentController;
			parentController.displayView(quizPage);

		}

	}

	// Save edited set and return to Quiz Main. Need to implement function
	// to save the set for this actionLIstener
	private class SaveEditedFlashcard implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			QuizAndFlashMain FlashcardPage = new QuizAndFlashMain("Quiz Main Page", QuizAndFlashMain.PanelType.THREE_BUTTON_TYPE);
			FlashcardPage.parentController = parentController;
			parentController.displayView(FlashcardPage);

		}

	}

}
