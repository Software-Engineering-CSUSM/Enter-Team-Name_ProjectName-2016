package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;

public class FlashCardMain extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FlashCardMain(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public FlashCardMain(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public FlashCardMain(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "Add Question", "Take Set", "Create Set" });
		try {
			for (int i = 0; i < this.currentActions.length; i++) {
				switch (i) {
				case 0:
					System.out.println("Hello");
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
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			System.out.println("Inside catch");
		}
	}

	// Button Listener for add question. Currently set for two button type.
	// Needs to be updated for 2 test fields with two buttons
	private class OpenAddQuestions implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			System.out.println("Opening " + this.getClass());

			AddQuestion questionPage = new AddQuestion(CustomPage.PanelType.TWO_BUTTON_TYPE);
			System.out.println(questionPage.toString());
			questionPage.setName("Flashcard Page");
			questionPage.parentController = parentController;
			parentController.displayView(questionPage);

		}

	}

	// Button Listener for the Take Quiz Page. Currently set to two
	// button type. Needs to be updated to list view for list of
	// quizzes to take and a start button
	private class TakeSetPage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			// System.out.println("Opening " + this.getClass());
			FlashCardQuestionPage takeSet = new FlashCardQuestionPage(FlashCardQuestionPage.PanelType.TWO_BUTTON_TYPE);
			takeSet.setName("Take Flashcard Set Page");
			takeSet.parentController = parentController;
			parentController.displayView(takeSet);

		}

	}

	// Button Listener for the Take Quiz Page. Currently set to two
	// button type. Needs to be updated to list view for list of
	// quizzes to take and a start button
	private class CreateSetPage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			// System.out.println("Opening " + this.getClass());
			CreateQuiz createQ = new CreateQuiz(CreateQuiz.PanelType.TWO_BUTTON_TYPE);
			createQ.setName("Create Flashcard Set Page");
			createQ.parentController = parentController;
			parentController.displayView(createQ);

		}

	}

}