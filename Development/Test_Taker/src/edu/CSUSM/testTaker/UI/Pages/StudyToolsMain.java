package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import edu.CSUSM.testTaker.LibraryController;
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
		setButtonNames(new String[] {"Quiz Page", "Practice With Flash Cards" });

		try {
			for (int i = 0; i < this.currentActions.length; i++) {
				switch (i) {
				case 0:
					this.currentActions[i].addActionListener(new OpenQuizPage());
					break;
				case 1:
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

	private class OpenFlashCards implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			/*CustomPage.setqBuilderNumButtons(1);
			TakeQuizTakeSet takeSet = new TakeQuizTakeSet("Select Flashcard Set" , TakeQuizTakeSet.PanelType.QUESTION_BUILDER_TYPE,
					TakeQuizTakeSet.PageType.FLASHCARD);
			takeSet.parentController = parentController;
			parentController.displayView(takeSet);*/
			
			try{
				//Check to see if a test is selected. If not, alert the user they must seect one
				if(ManageData.currentIDSelected == null || ManageData.currentIDSelected.length() == 0){
					throw new NullPointerException();
				}else{
					CustomPage.setqBuilderNumButtons(3);

					//Set the question list based on teh test ID just gathered
					CustomPage.setQBRowHeaders(LibraryController.getAllTestNamesInCourse(ManageData.currentIDSelected));
					CustomPage.setQBRowIDs(LibraryController.getAllTestIDsInCourse(ManageData.currentIDSelected));

					CustomPage.setqBuilderNumButtons(1);
					TestListManager.setCourse(LibraryController.retrieveCourse(ManageData.currentIDSelected));
					TakeQuizTakeSet takeSet = new TakeQuizTakeSet("Select Flashcard Set" , TakeQuizTakeSet.PanelType.QUESTION_BUILDER_TYPE,
							TakeQuizTakeSet.PageType.FLASHCARD);
					LibraryController.CURRENT_COURSE = TestListManager.CourseObj;
					//System.out.println("Current ID Selected: " + ManageData.currentIDSelected);
					//tm.setName("Test List Manager");
					ManageData.resetButtons();
					takeSet.parentController = parentController;
					parentController.displayView(takeSet);
				}
			}
			catch(NullPointerException ex){
				JOptionPane.showMessageDialog(null, "Please select a course before continuing");
			}
		}

	}

	private class OpenQuizPage implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			// System.out.println("Opening " + this.getClass());
			/*CustomPage.setqBuilderNumButtons(1);
			TakeQuizTakeSet takeQ = new TakeQuizTakeSet("Take Quiz Page", TakeQuizTakeSet.PanelType.QUESTION_BUILDER_TYPE,
					TakeQuizTakeSet.PageType.QUIZ);
			takeQ.parentController = parentController;
			parentController.displayView(takeQ);*/
			
			try{
				//Check to see if a test is selected. If not, alert the user they must seect one
				if(ManageData.currentIDSelected == null || ManageData.currentIDSelected.length() == 0){
					throw new NullPointerException();
				}else{
					CustomPage.setqBuilderNumButtons(3);

					//Set the question list based on teh test ID just gathered
					CustomPage.setQBRowHeaders(LibraryController.getAllTestNamesInCourse(ManageData.currentIDSelected));
					CustomPage.setQBRowIDs(LibraryController.getAllTestIDsInCourse(ManageData.currentIDSelected));

					CustomPage.setqBuilderNumButtons(1);
					TestListManager.setCourse(LibraryController.retrieveCourse(ManageData.currentIDSelected));
					TakeQuizTakeSet takeQ = new TakeQuizTakeSet("Take Quiz Page", CustomPage.PanelType.QUESTION_BUILDER_TYPE);
					LibraryController.CURRENT_COURSE = TestListManager.CourseObj;
					//System.out.println("Current ID Selected: " + ManageData.currentIDSelected);
					//tm.setName("Test List Manager");
					ManageData.resetButtons();
					takeQ.parentController = parentController;
					parentController.displayView(takeQ);
				}
			}
			catch(NullPointerException ex){
				JOptionPane.showMessageDialog(null, "Please select a course before continuing");
			}

		}

	}

}
