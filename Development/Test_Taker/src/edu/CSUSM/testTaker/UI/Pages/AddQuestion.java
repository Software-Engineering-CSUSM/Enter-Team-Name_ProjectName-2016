package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;

/**
 * @author Jeremy
 *
 * @purpose This is the add question page. It is called from the
 *          QuizAndFlashMain by clicking Add Question It's purpose create a
 *          question for a quiz by writing the question into the question text
 *          area and its answer into the answer text area, and clicking save.
 * 
 * @date 11/1. Currently, the save button prompts a pop up to create another
 *       question or not. Text can be written into the text area, but action
 *       listeners need to be implemented with a function to save the question
 *       and answer
 * 
 *       View List must also still be implemented
 * 
 */

public class AddQuestion extends CustomPage {

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

	// Set button names and action listeners
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

	// Button Listener For Save Question. Clicking Save creates pop up
	// window by calling the PopUp class.
	private class QuestionSavePopUp implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			// Constructor uses a main window with just a logo type, and
			// an AddAnotherQuestion to create the correct popUp window in the
			// PopUp class
			PopUp saveQ = new PopUp(PopUp.PanelType.LOGO_ONLY_TYPE, PopUpType.AddAnotherQuestion);
			saveQ.setName("Save Question Pop Up Window");
			saveQ.parentController = parentController;
			parentController.displayView(saveQ);

		}

	}

	// Do we need this class?
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

}
