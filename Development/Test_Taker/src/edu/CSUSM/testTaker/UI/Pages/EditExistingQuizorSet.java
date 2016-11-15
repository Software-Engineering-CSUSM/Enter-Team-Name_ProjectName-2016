package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;
import edu.CSUSM.testTaker.UI.CustomPage.PanelType;

/**
 * @author Jeremy
 *
 * @purpose This is the edit existing quiz or set Page. It is called from the
 *          CreateQuizorSet class by clicking Edit Existing Quiz or Edit
 *          Existing Set from the Create Quiz and Create Flashcard. It's purpose
 *          is to allow the user to choose from a list of existing quizzes or
 *          flashcard sets and click the edit button to open a page to edit
 *          which questions to use for the chosen quiz or set
 * 
 * @date 11/1. Currently, edit quiz and edit flashcard are not building the
 *       correct panels. They need to build a jtable paneltype to allow the user
 *       to select the quiz or set to edit.
 * 
 */

public class EditExistingQuizorSet extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EditExistingQuizorSet(String panelName, PanelType currentPanelType) {
		super(panelName, currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public EditExistingQuizorSet(String panelName, PanelType currentPanelType, BufferedImage newImage) {
		super(panelName, currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public EditExistingQuizorSet(String panelName, PanelType currentPanelType, String imageAddress) {
		super(panelName, currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	// Constructor used to create the panel
	public EditExistingQuizorSet(String panelName, PanelType currentPanelType, PageType currentPageType) {
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
		setButtonNames(new String[] { "Edit Quiz", "Do Nothing" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new EditQuiz());
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
		setButtonNames(new String[] { "Edit Set", "Do Nothing" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new EditFlashcard());
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

	// Opens the edit page with question builder type. Need to update
	// to jtable type to select which quiz to edit
	private class EditQuiz implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			EditQuestionsFromExisting editQ = new EditQuestionsFromExisting("Edit Existing Quiz Page",
					EditQuestionsFromExisting.PanelType.TWO_BUTTON_TYPE, EditQuestionsFromExisting.PageType.QUIZ);
			editQ.setName("Edit Existing Quiz Page");
			editQ.parentController = parentController;
			parentController.displayView(editQ);

		}

	}

	// Opens the edit page with question builder type. Need to update
	// to jtable type to select which set to edit
	private class EditFlashcard implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			EditQuestionsFromExisting editFC = new EditQuestionsFromExisting("Edit Existing Quiz Page",
					EditQuestionsFromExisting.PanelType.TWO_BUTTON_TYPE, EditQuestionsFromExisting.PageType.FLASHCARD);
			editFC.setName("Edit Existing Quiz Page");
			editFC.parentController = parentController;
			parentController.displayView(editFC);

		}

	}

}
