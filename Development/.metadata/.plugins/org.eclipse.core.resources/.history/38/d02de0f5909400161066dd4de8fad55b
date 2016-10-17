package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;

public class ViewQuestionList extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewQuestionList(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public ViewQuestionList(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public ViewQuestionList(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "1", "2" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				// this.currentActions[i].addActionListener(new ViewList());
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
	/*
	 * // Button Listener For Save Question. It should be a pop up window
	 * private class ViewList implements ActionListener {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { //
	 * System.out.println("Opening " + this.getClass());
	 * 
	 * CustomPage cm = new CustomPage(CustomPage.PanelType.LOGO_ONLY_TYPE);
	 * cm.setName("Question List"); parentController.displayView(cm);
	 * 
	 * }
	 * 
	 * }
	 */

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
