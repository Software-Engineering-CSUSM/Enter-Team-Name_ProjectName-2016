package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;
import edu.CSUSM.testTaker.UI.CustomPage.PanelType;

/**
 * @author Jeremy
 *
 * @purpose This is the results page for quizzes. It is called from the
 *          QuizAndFlashQuestionPage class by clicking Submit on the final
 *          question. It's purpose is to show the user the results from taking
 *          the quiz by displaying the number right out of the total number of
 *          questions as well as which questions the user got correct and which
 *          ones the user got wrong
 * 
 *          Need to implement with one button type.
 * 
 */

public class QuizResultsPage extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuizResultsPage(String panelName, PanelType currentPanelType) {
		super(panelName, currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public QuizResultsPage(String panelName, PanelType currentPanelType, BufferedImage newImage) {
		super(panelName, currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public QuizResultsPage(String panelName, PanelType currentPanelType, String imageAddress) {
		super(panelName, currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	// Button action Listener. Returns the user back to the quiz main page
	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "Return to Take Quiz Page", "Does Nothing" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new ReturnTakeQuiz());
				break;
			case 1:
				// this.currentActions[i].addActionListener(new Previous());
				break;
			default:
				System.out.println("Not enough implemented classes");
				break;
			}
		}
	}

	// Returns to the Take Quiz Page
	private class ReturnTakeQuiz implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

		TakeQuizTakeSet takeQ = new TakeQuizTakeSet("Take Quiz Page", TakeQuizTakeSet.PanelType.TWO_BUTTON_TYPE,
					TakeQuizTakeSet.PageType.QUIZ);
			takeQ.parentController = parentController;
			parentController.displayView(takeQ);

		}
	}

}
