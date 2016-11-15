package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;
import edu.CSUSM.testTaker.UI.CustomPage.PanelType;
import edu.CSUSM.testTaker.UI.CustomPage.PopUpType;
/*
 * Display a list of all current Test (Quizzes). User can add a new Test (Quiz)
 * name with add Test (Quiz), Delete Existing Test (Quiz) from list, and manage the
 * selected Test (Quiz)
 * 
 */

public class QuestionListManager extends CustomPage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuestionListManager(String panelName, PanelType currentPanelType) {
		super(panelName, currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public QuestionListManager(String panelName, PanelType currentPanelType, BufferedImage newImage) {
		super(panelName, currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public QuestionListManager(String panelName, PanelType currentPanelType, String imageAddress) {
		super(panelName, currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "Add Question", "Delete Question"});

		try {
			for (int i = 0; i < this.currentActions.length; i++) {
				switch (i) {
				case 0:
					this.currentActions[i].addActionListener(new AddQuestion());
					break;
				case 1:
					this.currentActions[i].addActionListener(new DeleteQuestion());
					break;
				default:
					System.out.println("Not enough implemented classes");
					break;
				}
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	private class AddQuestion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			// Constructor uses a main window with just a logo type, and
			// an SaveQuiz to create the correct popUp window in the
			// PopUp class
			PopUp popup = new PopUp("", PopUp.PanelType.LOGO_ONLY_TYPE, PopUpType.AddQuestionName);
			
			
			//After saving course name in the pop up, call test main and refresh
			CustomPage.setqBuilderNumButtons(2);
			QuestionListManager qlm = new QuestionListManager("Question List Manager", QuestionListManager.PanelType.QUESTION_BUILDER_TYPE);
			//qlm.setName("Question List Manager");
			qlm.parentController = parentController;
			parentController.displayView(qlm);
			
			qlm.revalidate();

		}

	}
	
	private class DeleteQuestion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			// Call Pop Up window to confirm deleting test.  To actually delete test upon clicking
			// "yes", implement the function in the action listener in the PopUp class
			PopUp popup = new PopUp("", PopUp.PanelType.LOGO_ONLY_TYPE, PopUpType.DeleteQuestion);
			
			
			//After deleting a test from the pop up, call courses main and refresh
			CustomPage.setqBuilderNumButtons(2);
			QuestionListManager qlm = new QuestionListManager("Question List Manager", QuestionListManager.PanelType.QUESTION_BUILDER_TYPE);
			//qlm.setName("Question List Manager");
			qlm.parentController = parentController;
			parentController.displayView(qlm);
			
			qlm.revalidate();

		}

	}

}

