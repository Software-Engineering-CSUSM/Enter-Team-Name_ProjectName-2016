package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;

public class QuizMain extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuizMain(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public QuizMain(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public QuizMain(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

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

	// Button Listener for add question. Currently set for two button type.
	// Needs to be updated for 2 test fields with two buttons
	private class OpenAddQuestions implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			// System.out.println("Opening " + this.getClass());
			AddQuestion addQ = new AddQuestion(AddQuestion.PanelType.TWO_BUTTON_TYPE);
			addQ.setName("Add Question Page");
			addQ.parentController = parentController;
			parentController.displayView(addQ);

		}

	}

	// Button Listener for the Take Quiz Page. Currently set to two
	// button type. Needs to be updated to list view for list of
	// quizzes to take and a start button
	private class TakeQuizPage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			// System.out.println("Opening " + this.getClass());
			TakeQuiz takeQ = new TakeQuiz(TakeQuiz.PanelType.TWO_BUTTON_TYPE);
			takeQ.setName("Take Quiz Page");
			takeQ.parentController = parentController;
			parentController.displayView(takeQ);

		}

	}

	// Button Listener for the Take Quiz Page. Currently set to two
	// button type. Needs to be updated to list view for list of
	// quizzes to take and a start button
	private class CreateQuizPage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			// System.out.println("Opening " + this.getClass());
			CreateQuiz createQ = new CreateQuiz(CreateQuiz.PanelType.TWO_BUTTON_TYPE);
			createQ.setName("Create Quiz Page");
			createQ.parentController = parentController;
			parentController.displayView(createQ);

		}

	}
}
