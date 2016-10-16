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
				this.currentActions[i].addActionListener(new NewQuiz());
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

	// Not opening another the CreateNewQuizPage for some reason
	private class NewQuiz implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			CreateNewQuizPage cm = new CreateNewQuizPage(CreateNewQuizPage.PanelType.TWO_BUTTON_TYPE);
			cm.setName("Create Quiz Page");
			parentController.displayView(cm);

		}

	}

	private class EditQuiz implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			EditExistingQuiz cm = new EditExistingQuiz(EditExistingQuiz.PanelType.TWO_BUTTON_TYPE);
			cm.setName("Edit Quiz Page");
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
