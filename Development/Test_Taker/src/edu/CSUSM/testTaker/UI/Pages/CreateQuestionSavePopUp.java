// Can get rid of this page, if pop up is working correctly
package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;

public class CreateQuestionSavePopUp extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateQuestionSavePopUp(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public CreateQuestionSavePopUp(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public CreateQuestionSavePopUp(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "Create Another Question", "Return to Quiz Main" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new SaveQuestion());
				break;
			case 1:
				this.currentActions[i].addActionListener(new ReturnToQuizMain());
				break;
			default:
				System.out.println("Not enough implemented classes");
				break;
			}
		}
	}

	// Save Current Question and return to AddQuestion page. Currently
	// causing an error.
	private class SaveQuestion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			AddQuestion addQ = new AddQuestion(AddQuestion.PanelType.TWO_BUTTON_TYPE);
			addQ.setName("Pop Up");
			addQ.parentController = parentController;
			parentController.displayView(addQ);

		}

	}

	// Return to Quiz Main. Currently causing error
	private class ReturnToQuizMain implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			QuizMain returnQuiz = new QuizMain(QuizMain.PanelType.TWO_BUTTON_TYPE);
			returnQuiz.setName("Pop Up");
			returnQuiz.parentController = parentController;
			parentController.displayView(returnQuiz);

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
