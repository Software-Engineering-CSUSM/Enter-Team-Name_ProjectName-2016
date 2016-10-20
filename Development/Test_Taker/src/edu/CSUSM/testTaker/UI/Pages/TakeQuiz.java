package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;

public class TakeQuiz extends CustomPage {
	/**
	 * 
	 */
	public static int totalNumQuestions = 10; // Total Number of quesitons
												// to determinew when to load
												// final page
	private static final long serialVersionUID = 1L;

	public TakeQuiz(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public TakeQuiz(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public TakeQuiz(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "Start", "Do Nothing" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new StartQuiz());
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

	// Button listener to start the Quiz. This is currently set to a two
	// button type. It needs to be updated to have a question, textfield for
	// answer as well as two buttons for exit and next question.
	private class StartQuiz implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			QuizQuestionPage questionPage = new QuizQuestionPage(QuizQuestionPage.PanelType.TWO_BUTTON_TYPE);
			questionPage.setName("Quiz Question Page " + QuizQuestionPage.questionPageNumber);
			questionPage.parentController = parentController;
			parentController.displayView(questionPage);
			QuizQuestionPage.questionPageNumber++; // global variable to keep
													// track of the number of
													// quiz pages

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
