package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;

public class QuizQuestionPage1 extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuizQuestionPage1(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public QuizQuestionPage1(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public QuizQuestionPage1(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "Exit Quiz", "Next Question" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				// this.currentActions[i].addActionListener(new NextQuestion());
				break;
			case 1:
				this.currentActions[i].addActionListener(new NextQuestion()); // Getting
																				// an
																				// error
				break;
			default:
				System.out.println("Not enough implemented classes");
				break;
			}
		}
	}

	// Getting an error when calling this class
	private class NextQuestion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			QuestionPageBetweenFirstAndLast cm = new QuestionPageBetweenFirstAndLast(
					QuestionPageBetweenFirstAndLast.PanelType.TWO_BUTTON_TYPE);
			cm.setName("Quiz Question Page");
			parentController.displayView(cm);

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
