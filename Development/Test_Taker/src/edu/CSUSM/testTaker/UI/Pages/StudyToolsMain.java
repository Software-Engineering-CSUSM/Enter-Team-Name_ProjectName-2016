package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;
import edu.CSUSM.testTaker.UI.CustomPage.PanelType;

public class StudyToolsMain extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StudyToolsMain(String panelName, PanelType currentPanelType) {
		super(panelName, currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public StudyToolsMain(String panelName, PanelType currentPanelType, BufferedImage newImage) {
		super(panelName, currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public StudyToolsMain(String panelName, PanelType currentPanelType, String imageAddress) {
		super(panelName, currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public void updateActions() {

		// Set the button names
		setButtonNames(new String[] { "Play Game", "Quiz Page", "Practice With Flash Cards" });

		try {
			for (int i = 0; i < this.currentActions.length; i++) {
				switch (i) {
				case 0:
					this.currentActions[i].addActionListener(new OpenStudyGame());
					break;
				case 1:
					this.currentActions[i].addActionListener(new OpenQuizPage());
					break;
				case 2:
					this.currentActions[i].addActionListener(new OpenFlashCards());
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

	private class OpenStudyGame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			/**
			StudyGame cm = new StudyGame(parentController.getWidth(),
					parentController.getHeight(), null); cm.setName("Study Game");
					parentController.displayView(cm);
					*/
			GamePrep prep = new GamePrep("Game Setup", CustomPage.PanelType.TWO_BUTTON_TYPE);
			prep.parentController = parentController;
			parentController.displayView(prep);
			


		}

	}

	private class OpenFlashCards implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			CustomPage.setqBuilderNumButtons(1);
			TakeQuizTakeSet takeSet = new TakeQuizTakeSet("Select Flashcard Set" , TakeQuizTakeSet.PanelType.QUESTION_BUILDER_TYPE,
					TakeQuizTakeSet.PageType.FLASHCARD);
			takeSet.parentController = parentController;
			parentController.displayView(takeSet);
		}

	}

	private class OpenQuizPage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			// System.out.println("Opening " + this.getClass());
			CustomPage.setqBuilderNumButtons(1);
			TakeQuizTakeSet takeQ = new TakeQuizTakeSet("Take Quiz Page", TakeQuizTakeSet.PanelType.QUESTION_BUILDER_TYPE,
					TakeQuizTakeSet.PageType.QUIZ);
			takeQ.parentController = parentController;
			parentController.displayView(takeQ);

		}

	}

}
