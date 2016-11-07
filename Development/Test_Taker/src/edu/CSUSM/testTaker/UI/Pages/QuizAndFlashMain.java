package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;

/**
 * @author Jeremy
 *
 * @purpose This is the main page for quizzes and flashcard. It is called from
 *          the StudyToolsMain class by clicking on the side menu option "Study
 *          Tools". It's purpose is to allow the user to select a category:
 *          "Study Game", "Quizzes", or , "Practice With Flashcards", and then
 *          open up the corresponding page.
 * 
 *          **Need to update the take quiz and take set with a jtable to be able
 *          to select which quiz or flashcard set to take.
 * 
 */

public class QuizAndFlashMain extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuizAndFlashMain(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public QuizAndFlashMain(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public QuizAndFlashMain(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	// Constructor used to create page
	public QuizAndFlashMain(PanelType currentPanelType, PageType currentPageType) {
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

	// Button Actions for quizzes
	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "Add Question", "Create Quiz", "Take Quiz" });
		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new OpenAddQuestions());
				break;
			case 1:
				this.currentActions[i].addActionListener(new CreateQuizPage());
				break;
			case 2:
				this.currentActions[i].addActionListener(new TakeQuizPage());
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
		setButtonNames(new String[] { "Add Question", "Take Set", "Create Set" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new OpenAddQuestions());
				break;
			case 1:
				this.currentActions[i].addActionListener(new TakeSetPage());
				break;
			case 2:
				this.currentActions[i].addActionListener(new CreateSetPage());
				break;
			default:
				System.out.println("Not enough implemented classes");
				break;
			}

		}
	}

	// Open up page to add questions and answer to the database
	private class OpenAddQuestions implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			AddQuestion addQ = new AddQuestion(AddQuestion.PanelType.Q_and_A_Type);
			addQ.setName("Add Question Page");
			addQ.parentController = parentController;
			parentController.displayView(addQ);

		}

	}

	// Button Listener for the Take Quiz Page. Currently set to question
	// builder type. Needs to be updated to jtable for the user to be able
	// to select which quiz to take
	private class TakeQuizPage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			// System.out.println("Opening " + this.getClass());
			TakeQuizTakeSet takeQ = new TakeQuizTakeSet(TakeQuizTakeSet.PanelType.QUESTION_BUILDER_TYPE,
					TakeQuizTakeSet.PageType.QUIZ);
			takeQ.setName("Take Quiz Page");
			takeQ.parentController = parentController;
			parentController.displayView(takeQ);

		}

	}

	// Button Listener for the Create Quiz. Opens up page to create
	// new quiz or edit an existing quiz.
	private class CreateQuizPage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			// System.out.println("Opening " + this.getClass());
			CreateQuizorSet createQ = new CreateQuizorSet(CreateQuizorSet.PanelType.TWO_BUTTON_TYPE,
					CreateQuizorSet.PageType.QUIZ);
			createQ.setName("Create Quiz Page");
			createQ.parentController = parentController;
			parentController.displayView(createQ);

		}

	}

	// Button Listener for the Take Quiz Page. Currently set to question
	// builder type. Needs to be updated to jtable for the user to be able
	// to select which quiz to take
	private class TakeSetPage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			// System.out.println("Opening " + this.getClass());
			// FlashCardQuestionPage takeSet = new
			// FlashCardQuestionPage(FlashCardQuestionPage.PanelType.TWO_BUTTON_TYPE);
			TakeQuizTakeSet takeSet = new TakeQuizTakeSet(TakeQuizTakeSet.PanelType.QUESTION_BUILDER_TYPE,
					TakeQuizTakeSet.PageType.FLASHCARD);
			takeSet.setName("Take Flashcard Set Page");
			takeSet.parentController = parentController;
			parentController.displayView(takeSet);

		}

	}

	// Button Listener for the Create Set. Opens up page to create
	// new quiz or edit an existing set.
	private class CreateSetPage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			// System.out.println("Opening " + this.getClass());
			CreateQuizorSet createS = new CreateQuizorSet(CreateQuizorSet.PanelType.TWO_BUTTON_TYPE,
					CreateQuizorSet.PageType.FLASHCARD);
			createS.setName("Create Flashcard Set Page");
			createS.parentController = parentController;
			parentController.displayView(createS);

		}

	}
}
