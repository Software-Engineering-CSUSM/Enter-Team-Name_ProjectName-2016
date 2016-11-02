package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;

/**
 * @author Jeremy
 *
 * @purpose This is the create new quiz/set page. It is called from the
 *          CreateQuizorSet class by clicking Create Quiz from quiz page or
 *          Create Set from set page. The purpose is to create a quiz by
 *          selecting the question and answers for the quiz the user wants to
 *          use, and click save which prompts a pop up window to name and save
 *          quiz.
 * 
 * @date 11/1. Currently, the save button brings up the pop up window to save.
 *       However, a jtable needs to be implemented to be able to select the
 *       questions and answers from the database. Also, the there needs to be
 *       function implemented to save the name of the quiz written into the text
 *       field.
 * 
 * 
 */

public class CreateNewQuizOrSet extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateNewQuizOrSet(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public CreateNewQuizOrSet(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public CreateNewQuizOrSet(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	// Constructor used to create page
	public CreateNewQuizOrSet(PanelType currentPanelType, PageType currentPageType) {
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

	// Button actions for the Create New Quiz Page. We only need one button,
	// so we need to either change to a one button type, or change "Do nothing
	// to back button.
	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "Save Quiz", "Do Nothing" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				// Click save and call for the name quiz popup window
				this.currentActions[i].addActionListener(new SavePopUp());
				break;
			case 1:
				// do nothing
				break;
			default:
				System.out.println("Not enough implemented classes");
				break;
			}
		}
	}

	// Button actions for the Create Flashcard Set Page. We only need one
	// button,
	// so we need to either change to a one button type, or change "Do nothing
	// to back button.
	public void updateActionsFlashcard() {

		// Set the button names
		setButtonNames(new String[] { "Save Set", "Do Nothing" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				// Click save and call for the name set popup window
				this.currentActions[i].addActionListener(new SavePopUpFlashcard());
				break;
			case 1:
				// do nothing
				break;
			default:
				System.out.println("Not enough implemented classes");
				break;
			}
		}
	}

	// Pop up window with a textfield to save the name of the quiz and
	// a save button. Need to implement a method to save the name of
	// the set when the user clicks save
	private class SavePopUp implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			// Constructor uses a main window with just a logo type, and
			// an SaveQuiz to create the correct popUp window in the
			// PopUp class
			PopUp quizPage = new PopUp(PopUp.PanelType.LOGO_ONLY_TYPE, PopUpType.SaveQuiz);

			quizPage.setName("Save Quiz Page");
			quizPage.parentController = parentController;
			parentController.displayView(quizPage);

		}

	}

	// Pop up window with a textfield to save the name of the set and
	// a save button. Need to implement a method to save the name of
	// the set when the user clicks save
	private class SavePopUpFlashcard implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			// Constructor uses a main window with just a logo type, and
			// an SaveQuiz to create the correct popUp window in the
			// PopUp class
			PopUp FlashcardPage = new PopUp(PopUp.PanelType.LOGO_ONLY_TYPE, PopUpType.SaveSet);

			FlashcardPage.setName("Save Flashcard Page");
			FlashcardPage.parentController = parentController;
			parentController.displayView(FlashcardPage);

		}

	}

}
