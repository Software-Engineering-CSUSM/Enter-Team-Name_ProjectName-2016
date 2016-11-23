package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.LibraryController;
import edu.CSUSM.testTaker.Backend.Question;
import edu.CSUSM.testTaker.Backend.Test;
import edu.CSUSM.testTaker.UI.CustomPage;
import edu.CSUSM.testTaker.UI.CustomPage.PanelType;

/**
 * @author Jeremy
 *
 * @purpose This is the add question page. It is called from the
 *          QuizAndFlashMain by clicking Add Question It's purpose create a
 *          question for a quiz by writing the question into the question text
 *          area and its answer into the answer text area, and clicking save.
 * 
 * @date 11/5. Modified save to save the question and answer textarea into
 * 			  a pulbic string.  Class needs to be implemented to set the string
 * 			to the correct string array.
 * 
 */

public class AddQuestion extends CustomPage {

	private static final long serialVersionUID = 1L;
	
	// String to hold the question and answer from the question
	// and answer text area.
	public String questionStr, answerStr;
	
	//String array to hold answers for Multiple Choice Questions
	public String answerStrMC[] = new String[4];
	
	
	
	public AddQuestion(String panelName, PanelType currentPanelType) {
		super(panelName, currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		if(currentPanelType == CustomPage.PanelType.Q_and_A_Type_MC)
			updateActionsMC();
		else
			updateActions();
	}

	public AddQuestion(String panelName, PanelType currentPanelType, BufferedImage newImage) {
		super(panelName, currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public AddQuestion(String panelName, PanelType currentPanelType, String imageAddress) {
		super(panelName, currentPanelType, imageAddress);
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

public void updateActionsMC() {

		// Set the button names
		setButtonNames(new String[] { "Save Question", "View Question" });

		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new QuestionSavePopUpMC());
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
			
			// Saves the question and answer into a string and prints it
			// out for testing purposes
			questionStr = question.getText();
			answerStr = answer.getText();
			System.out.println("question: " + questionStr);
			System.out.println("\nanswer: " + answerStr);
			
			
			// Constructor uses a main window with just a logo type, and
			// an AddAnotherQuestion to create the correct popUp window in the
			// PopUp class
			PopUp saveQ = new PopUp("Add Question Page", PopUp.PanelType.LOGO_ONLY_TYPE, PopUpType.AddAnotherQuestion);
			saveQ.parentController = parentController;
			parentController.displayView(saveQ);
			

		}

	}
	private class QuestionSavePopUpMC implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//System.out.println("Opening " + this.getClass());
			
			
			// Saves the question and answer into a string and prints it
			// out for testing purposes//Create a new Question object
			Question newQ = new Question(question.getText());
			for(int i = 0; i < answerTextMC.length; i++)
			{
				newQ.addAnswer(answerTextMC[i].getText());
			}
			
			newQ.setCorrectIndex(5);
			
			try{
				LibraryController.retrieveTest(LibraryController.CURRENT_TEST.getID()).addQuestion(newQ, 15);
				
			}catch(NullPointerException ex){
				System.out.println("Current Error: " + ex.getMessage());
				System.out.println("Course Value: " + LibraryController.CURRENT_COURSE.toString());
				//System.out.println("Test Value: " + LibraryController.CURRENT_TEST.toString());
			}
			
			// Constructor uses a main window with just a logo type, and
			// an AddAnotherQuestion to create the correct popUp window in the
			// PopUp class
			PopUp saveQ = new PopUp("Add Question Page", PopUp.PanelType.LOGO_ONLY_TYPE, PopUpType.AddAnotherQuestionMC);
			saveQ.parentController = parentController;
			parentController.displayView(saveQ);

		}

	}
	// Do we need this class?
	private class ViewList implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			AddQuestion list = new AddQuestion("View Questions", AddQuestion.PanelType.QUESTION_BUILDER_TYPE);
			list.parentController = parentController;
			parentController.displayView(list);

		}

	}

}
