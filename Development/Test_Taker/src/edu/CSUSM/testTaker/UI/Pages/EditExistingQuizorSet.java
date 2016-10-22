package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;
import edu.CSUSM.testTaker.UI.CustomPage.PageType;
import edu.CSUSM.testTaker.UI.CustomPage.PanelType;

public class EditExistingQuizorSet extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EditExistingQuizorSet(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public EditExistingQuizorSet(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public EditExistingQuizorSet(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}
	public EditExistingQuizorSet(PanelType currentPanelType, PageType currentPageType) {
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

	// Opens the edit page with List View of of questions. Currently
	// set to two button type. Needs to be updated to listview of
	// questions from selected quiz with a save button.
	private class EditQuiz implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			EditQuestionsFromExisting editQ = new EditQuestionsFromExisting(EditQuestionsFromExisting.PanelType.TWO_BUTTON_TYPE, EditQuestionsFromExisting.PageType.QUIZ);
			editQ.setName("Edit Existing Quiz Page");
			editQ.parentController = parentController;
			parentController.displayView(editQ);

		}

	}
	private class EditFlashcard implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			EditQuestionsFromExisting editFC = new EditQuestionsFromExisting(EditQuestionsFromExisting.PanelType.TWO_BUTTON_TYPE, EditQuestionsFromExisting.PageType.FLASHCARD);
			editFC.setName("Edit Existing Quiz Page");
			editFC.parentController = parentController;
			parentController.displayView(editFC);

		}

	}

}
