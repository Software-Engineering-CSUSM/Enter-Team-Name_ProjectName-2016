package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;

public class CreateQuiz extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateQuiz(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public CreateQuiz(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public CreateQuiz(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
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

	// Opens page to create a new quiz. It is currently set to a
	// two button type. It needs to be updated to a List view
	// for the questions to add as well as a save button.
	private class CreateNew implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			CreateNewQuizPage newQuiz = new CreateNewQuizPage(CreateNewQuizPage.PanelType.TWO_BUTTON_TYPE);
			System.out.println(newQuiz.toString());
			newQuiz.setName("Create New Page");
			newQuiz.parentController = parentController;
			parentController.displayView(newQuiz);

		}

	}

	// Currently set to two button type. Needs to be updated to
	// have a list view of existing quizzes in the database to
	// select a quiz to edit as well as an "edit" button
	private class EditQuiz implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			EditExistingQuiz editQ = new EditExistingQuiz(EditExistingQuiz.PanelType.TWO_BUTTON_TYPE);
			editQ.setName("Edit Quiz Page");
			editQ.parentController = parentController;
			parentController.displayView(editQ);

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
