package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;

/**
 * @author Jeremy
 *
 * @purpose This is the create new quiz or set Page. It is called from the
 *          QuizAndFlashMain class by clicking Create New Quiz or Create New Set
 *          from the Quiz Main and Flashcard Main. It's purpose is to allow the
 *          user to either create a new quiz/set or edit an existing quiz/set by
 *          clicking the corresponding button.
 * 
 * @date 11/1. Currently, create new quiz and edit existing are not building the
 *       correct panels. They need to build a jtable paneltype.
 * 
 */

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

	// Constructor used to create page
	public CreateQuizorSet(PanelType currentPanelType, PageType currentPageType) {
		super(currentPanelType, currentPageType);
		// Set the layout

		// Build the contents, If saving a quiz, call updateActions,
		// Otherwise, we'll be saving a flashcard set with
		// updateActionsFlashcard.

		if (currentPageType == CustomPage.PageType.QUIZ)
			updateActions();
		else if (currentPageType == CustomPage.PageType.FLASHCARD)
			updateActionsFlashcard();
		else
			System.out.println("error");

	}

	// Button actions for the Create New Quiz Page and edit existing quiz
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

	// Button actions for the Create New Set Page and edit existing set
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
	// questionbuilder. It needs to be updated to a List view
	// for the questions to add .
	private class CreateNew implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			CreateNewQuizOrSet newQuiz = new CreateNewQuizOrSet(CreateNewQuizOrSet.PanelType.QUESTION_BUILDER_TYPE,
					CreateNewQuizOrSet.PageType.QUIZ);
			System.out.println(newQuiz.toString());
			newQuiz.setName("Create New Quiz");
			newQuiz.parentController = parentController;
			parentController.displayView(newQuiz);

		}

	}

	// Opens page to create a new quiz. It is currently set to a
	// questionbuilder. It needs to be updated to a List view
	// for the questions to add
	private class CreateNewSet implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			CreateNewQuizOrSet newSet = new CreateNewQuizOrSet(CreateNewQuizOrSet.PanelType.QUESTION_BUILDER_TYPE,
					CreateNewQuizOrSet.PageType.FLASHCARD);
			System.out.println(newSet.toString());
			newSet.setName("Create New Flashcard Set");
			newSet.parentController = parentController;
			parentController.displayView(newSet);

		}

	}

	// Currently set to question builder type. Needs to be updated to
	// have a list view of existing quizzes in the database to
	// select a quiz to edit
	private class EditQuiz implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			EditExistingQuizorSet editQ = new EditExistingQuizorSet(
					EditExistingQuizorSet.PanelType.QUESTION_BUILDER_TYPE, EditExistingQuizorSet.PageType.QUIZ);
			editQ.setName("Edit Quiz Page");
			editQ.parentController = parentController;
			parentController.displayView(editQ);

		}

	}

	// Currently set to question builder type. Needs to be updated to
	// have a list view of existing quizzes in the database to
	// select a quiz to edit
	private class EditFlashcard implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			EditExistingQuizorSet editSet = new EditExistingQuizorSet(
					EditExistingQuizorSet.PanelType.QUESTION_BUILDER_TYPE, EditExistingQuizorSet.PageType.FLASHCARD);
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
