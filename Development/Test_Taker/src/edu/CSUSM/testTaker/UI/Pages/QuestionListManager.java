package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import edu.CSUSM.testTaker.LibraryController;
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
					this.currentActions[i].addActionListener(new AddQuestionHere());
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

	private class AddQuestionHere implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			//Show the "Create Question" View
			AddQuestion addQ = new AddQuestion("Add Question Page", AddQuestion.PanelType.Q_and_A_Type_MC);
			addQ.parentController = parentController;
			parentController.displayView(addQ);
		}

	}
	
	private class DeleteQuestion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Opening " + this.getClass());

			// Call Pop Up window to confirm deleting test.  To actually delete test upon clicking
			// "yes", implement the function in the action listener in the PopUp class
			try{
				//Check to see if a test is selected. If not, alert the user they must seect one
				if(ManageData.currentIDSelected == null || ManageData.currentIDSelected.length() == 0){
					throw new NullPointerException();
				}else{
					// Set number of buttons to two for next page
					CustomPage.setqBuilderNumButtons(2);
					
					//Delete the selected question
					LibraryController.deleteQuestion(ManageData.currentIDSelected);

					//Set the rows to all questions in teh test
					CustomPage.setQBRowHeaders(LibraryController.getAllQuestionNamesInCourse(ManageData.currentIDSelected));
					CustomPage.setQBRowIDs(LibraryController.getAllQuestionIDsInCourse(ManageData.currentIDSelected));

					QuestionListManager qlm = new QuestionListManager("Question List Manager", QuestionListManager.PanelType.QUESTION_BUILDER_TYPE);
					//qlm.setName("Question List Manager");
					ManageData.resetButtons();
					qlm.parentController = parentController;
					parentController.displayView(qlm);
				}
			}
			catch(NullPointerException ex){
				JOptionPane.showMessageDialog(null, "Please select a question before continuing");
			}

		}

		

	}

}

