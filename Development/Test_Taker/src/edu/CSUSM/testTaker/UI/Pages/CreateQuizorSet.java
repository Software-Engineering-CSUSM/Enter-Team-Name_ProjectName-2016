package edu.CSUSM.testTaker.UI.Pages;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;
import edu.CSUSM.testTaker.UI.CustomPage.PageType;
import edu.CSUSM.testTaker.UI.CustomPage.PanelType;

public class CreateQuizorSet extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateQuizorSet(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public CreateQuizorSet(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public CreateQuizorSet(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}
	public CreateQuizorSet(PanelType currentPanelType, PageType currentPageType) {
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
		
		setButtonNames(new String[] { "Create New Quiz", "Edit Existing Quiz" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new CreateNew());
				break;
			case 1:
				this.currentActions[i].addActionListener(new EditQuiz());
				break;
			default:
				System.out.println("Not enough implemented classes");
				break;
			}
		}
	}
	public void updateActionsFlashcard() {

		// Set the button names
		
		setButtonNames(new String[] { "Create Set", "Edit Existing Set" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new CreateNewSet());
				break;
			case 1:
				this.currentActions[i].addActionListener(new EditFlashcard());
				break;
			default:
				System.out.println("Not enough implemented classes");
				break;
			}
		}
	}


	// Opens page to create a new quiz. It is currently set to a
	// two button type. It needs to be updated to a List view
	// for the questions to add as well as a save button.
	private class CreateNew implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			CreateNewQuizOrSet newQuiz = new CreateNewQuizOrSet(CreateNewQuizOrSet.PanelType.TWO_BUTTON_TYPE, CreateNewQuizOrSet.PageType.QUIZ);
			System.out.println(newQuiz.toString());
			newQuiz.setName("Create New Quiz");
			newQuiz.parentController = parentController;
			parentController.displayView(newQuiz);

		}

	}
	private class CreateNewSet implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			CreateNewQuizOrSet newSet = new CreateNewQuizOrSet(CreateNewQuizOrSet.PanelType.TWO_BUTTON_TYPE, CreateNewQuizOrSet.PageType.FLASHCARD);
			System.out.println(newSet.toString());
			newSet.setName("Create New Flashcard Set");
			newSet.parentController = parentController;
			parentController.displayView(newSet);

		}

	}

	// Currently set to two button type. Needs to be updated to
	// have a list view of existing quizzes in the database to
	// select a quiz to edit as well as an "edit" button
	private class EditQuiz implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			EditExistingQuizorSet editQ = new EditExistingQuizorSet(EditExistingQuizorSet.PanelType.TWO_BUTTON_TYPE, EditExistingQuizorSet.PageType.QUIZ);
			editQ.setName("Edit Quiz Page");
			editQ.parentController = parentController;
			parentController.displayView(editQ);

		}

	}
	private class EditFlashcard implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			EditExistingQuizorSet editSet = new EditExistingQuizorSet(EditExistingQuizorSet.PanelType.TWO_BUTTON_TYPE, EditExistingQuizorSet.PageType.FLASHCARD);
			editSet.setName("Edit Flashcard Set");
			editSet.parentController = parentController;
			parentController.displayView(editSet);

		}

	}

	private class OpenQuestionBuilder implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());
			CustomPage questionBuilder = new CustomPage(CustomPage.PanelType.QUESTION_BUILDER_TYPE);
			questionBuilder.setName("Question Page");
			questionBuilder.parentController = parentController;
			parentController.displayView(questionBuilder);
		}
	}

}
