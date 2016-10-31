package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;

public class AddQuestion extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddQuestion(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public AddQuestion(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public AddQuestion(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "Save Question", "View Question" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new QuestionSavePopUp());
				break;
			case 1:
				this.currentActions[i].addActionListener(new ViewList());
				break;
			default:
				System.out.println("Not enough implemented classes");
				break;
			}
		}
	}

	// Button Listener For Save Question. It should be a pop up window
	private class QuestionSavePopUp implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			PopUp saveQ = new PopUp(PopUp.PanelType.LOGO_ONLY_TYPE, 1); // added a new constructor with an int to determine which pop up to display
			saveQ.setName("Save Question Pop Up Window");
			saveQ.parentController = parentController;
			parentController.displayView(saveQ);

		}

	}

	private class ViewList implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			ViewQuestionList list = new ViewQuestionList(ViewQuestionList.PanelType.QUESTION_BUILDER_TYPE);
			list.setName("View Questions");
			list.parentController = parentController;
			parentController.displayView(list);

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
