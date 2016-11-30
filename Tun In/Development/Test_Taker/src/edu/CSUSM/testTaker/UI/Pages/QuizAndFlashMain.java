package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;
import edu.CSUSM.testTaker.UI.CustomPage.PanelType;

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

	public QuizAndFlashMain(String panelName, PanelType currentPanelType) {
		super(panelName, currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public QuizAndFlashMain(String panelName, PanelType currentPanelType, BufferedImage newImage) {
		super(panelName, currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public QuizAndFlashMain(String panelName, PanelType currentPanelType, String imageAddress) {
		super(panelName, currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	// Constructor used to create page
	public QuizAndFlashMain(String nameofPanel, PanelType currentPanelType, PageType currentPageType) {
		super(nameofPanel, currentPanelType, currentPageType);
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

			/*AddQuestion addQ = new AddQuestion("Add Question Page", AddQuestion.PanelType.Q_and_A_Type);
			addQ.parentController = parentController;
			parentController.displayView(addQ);*/
			
			AddQuestion addQ = new AddQuestion("Add Question Page", AddQuestion.PanelType.Q_and_A_Type_MC);
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
			CustomPage.setqBuilderNumButtons(1);
			TakeQuizTakeSet takeQ = new TakeQuizTakeSet("Take Quiz Page", TakeQuizTakeSet.PanelType.QUESTION_BUILDER_TYPE,
					TakeQuizTakeSet.PageType.QUIZ);
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
			CreateQuizorSet createQ = new CreateQuizorSet("Create Quiz Page", CreateQuizorSet.PanelType.TWO_BUTTON_TYPE,
					CreateQuizorSet.PageType.QUIZ);
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
			
			CustomPage.setqBuilderNumButtons(1);
			TakeQuizTakeSet takeSet = new TakeQuizTakeSet("Take Flashcard Set Page", TakeQuizTakeSet.PanelType.QUESTION_BUILDER_TYPE,
					TakeQuizTakeSet.PageType.FLASHCARD);
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
			CreateQuizorSet createS = new CreateQuizorSet("Create Flash Card Set Page", CreateQuizorSet.PanelType.TWO_BUTTON_TYPE,
					CreateQuizorSet.PageType.FLASHCARD);
			createS.parentController = parentController;
			parentController.displayView(createS);

		}

	}
}
