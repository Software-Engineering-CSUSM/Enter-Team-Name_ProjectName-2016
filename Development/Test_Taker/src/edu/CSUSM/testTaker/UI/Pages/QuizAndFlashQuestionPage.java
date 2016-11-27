package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import javax.swing.JOptionPane;

import edu.CSUSM.testTaker.LibraryController;
import edu.CSUSM.testTaker.UI.CustomPage;
import edu.CSUSM.testTaker.UI.CustomPage.PanelType;

public class QuizAndFlashQuestionPage extends CustomPage {
	/**
	 * @author Jeremy
	 *
	 * @purpose Question Page able to navigate from next and previous questions.
	 *          Has submit button for final question which takes you to a
	 *          results page. Works for both quiz and flashcards question sets
	 */
	private static final long serialVersionUID = 1L;

	// Public int to keep track of the question page numbers.
	public static int questionPageNumber = 0;
	
	public static boolean popUp = true;

	public QuizAndFlashQuestionPage(String panelName, PanelType currentPanelType) {
		super(panelName, currentPanelType);
		// TODO Auto-generated constructor stub
		// System.out.println("Printing a new Form");
		updateActions();
	}

	public QuizAndFlashQuestionPage(String panelName, PanelType currentPanelType, BufferedImage newImage) {
		super(panelName, currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public QuizAndFlashQuestionPage(String panelName, PanelType currentPanelType, String imageAddress) {
		super(panelName, currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	// Constructor used to create page
	public QuizAndFlashQuestionPage(String panelName, PanelType currentPanelType, PageType currentPageType) {
		super(panelName, currentPanelType, currentPageType);
		// Set the layout

		// If saving a quiz, call updateActions, otherwise, we'll be saving
		// a flashcard set.
		if (currentPageType == CustomPage.PageType.QUIZ)
			updateActions();
		if (currentPageType == CustomPage.PageType.QUIZ_MC)
		{
			
			updateActionsQuizMC();
			
		}
		else if (currentPageType == CustomPage.PageType.FLASHCARD)
		{

			updateActionsFlashcard();
		
		}
		else
			System.out.println("error");

	}

	// Button actions for quiz. Uses conditional statements to determine
	// which buttons to display: exit and next for the first page, exit,
	// previous
	// and next for every page between the first and last, and exit previous and
	// submit for the last page.
	public void updateActions() {
		// Set the button names
		if (questionPageNumber == 1)
			setButtonNames(new String[] { "Exit Quiz", "Next Question" });
		else if (questionPageNumber > 1 && questionPageNumber < TakeQuizTakeSet.totalNumQuestions)
			setButtonNames(new String[] { "Exit Quiz", "Previous Question", "Next Question", });
		else if (questionPageNumber == TakeQuizTakeSet.totalNumQuestions) {
			setButtonNames(new String[] { "Exit Quiz", "Previous Question", "Submit and Finish" });
		}
		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new ExitQuiz());
				break;
			case 1:
				if (questionPageNumber == 1)
					this.currentActions[i].addActionListener(new NextQuestion());
				else if (questionPageNumber > 1)
					this.currentActions[i].addActionListener(new Previous());
				break;
			case 2:
				if (questionPageNumber < TakeQuizTakeSet.totalNumQuestions)
					this.currentActions[i].addActionListener(new NextQuestion());
				else
					this.currentActions[i].addActionListener(new Submit());
				break;

			default:
				System.out.println("Not enough implemented classes");
				break;
			}
		}

	}
	
	// Button actions for quiz. Uses conditional statements to determine
	// which buttons to display: exit and next for the first page, exit,
	// previous
	// and next for every page between the first and last, and exit previous and
	// submit for the last page.
	public void updateActionsQuizMC() {
		// Set the button names
		if (questionPageNumber == 1 && totalNumQuestions != 1)
			setButtonNames(new String[] { "Exit Quiz", "Next Question" });
		else if (questionPageNumber == 1 && totalNumQuestions == 1)
			setButtonNames(new String[] { "Exit Quiz", "Submit and Finish" });
		else if (questionPageNumber > 1 && questionPageNumber < TakeQuizTakeSet.totalNumQuestions)
			setButtonNames(new String[] { "Exit Quiz", "Previous Question", "Next Question", });
		else if (questionPageNumber == TakeQuizTakeSet.totalNumQuestions) {
			setButtonNames(new String[] { "Exit Quiz", "Previous Question", "Submit and Finish" });
		}
		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new ExitQuiz());
				break;
			case 1:
				if (questionPageNumber == 1 && totalNumQuestions == 1)
					this.currentActions[i].addActionListener(new Submit());
				else if (questionPageNumber == 1 && totalNumQuestions != 1)
					this.currentActions[i].addActionListener(new NextQuestionMC());
				else if (questionPageNumber > 1)
					this.currentActions[i].addActionListener(new Previous());
				break;
			case 2:
				if (questionPageNumber < TakeQuizTakeSet.totalNumQuestions)
					this.currentActions[i].addActionListener(new NextQuestionMC());
				else
					this.currentActions[i].addActionListener(new Submit());
				break;

			default:
				System.out.println("Not enough implemented classes");
				break;
			}
		} 
		MC_Answers[0].addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	
	        	// This is the correct answer. Set resultsChecker to 1 to calculate
	        	// the score at the end
	        	if(randAnswerNum[0] == 0){
	        		System.out.println("This is the correct answer\n");
	        		resultsChecker[questionPageNumber - 2] = 1;
	        		System.out.println("Results  " + resultsChecker[questionPageNumber - 2]);
	        	}
	        	else
	        		resultsChecker[questionPageNumber - 2] = 0;
	        	
	        	// Used to set the button to clicked after backtracking and 
	        	// clicking next question
	        	radioButtonTracker[questionPageNumber - 2] = 0;

	        }
	    });
		MC_Answers[1].addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	           
	        	// This is the correct answer. Set resultsChecker to 1 to calculate
	        	// the score at the end
	        	if(randAnswerNum[1] == 0){
	        		System.out.println("This is the correct answer\n");
	        		resultsChecker[questionPageNumber - 2] = 1;
	        		
	        	}
	        	else
	        		resultsChecker[questionPageNumber - 2] = 0;
	        	
	        	// Used to set the button to clicked after backtracking and 
	        	// clicking next question
	        	radioButtonTracker[questionPageNumber - 2] = 1;

	        }
	    });
		MC_Answers[2].addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	          
	        	// This is the correct answer. Set resultsChecker to 1 to calculate
	        	// the score at the end
	        	if(randAnswerNum[2] == 0){
	        		System.out.println("This is the correct answer\n");
	        		resultsChecker[questionPageNumber - 2] = 1;
	        		
	        	}
	        	else
	        		resultsChecker[questionPageNumber - 2] = 0;
	        	
	        	// Used to set the button to clicked after backtracking and 
	        	// clicking next question
	        	radioButtonTracker[questionPageNumber - 2] = 2;

	        }
	    });
		MC_Answers[3].addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	           
	        	// This is the correct answer. Set resultsChecker to 1 to calculate
	        	// the score at the end
	        	if(randAnswerNum[3] == 0){
	        		System.out.println("This is the correct answer\n");
	        		resultsChecker[questionPageNumber - 2] = 1;
	        		
	        	}
	        	else
	        		resultsChecker[questionPageNumber - 2] = 0;
	        	
	        	// Used to set the button to clicked after backtracking and 
	        	// clicking next question
	        	radioButtonTracker[questionPageNumber - 2] = 3;

	        }
	    });
	}

	// Button actions for Flashcard. Uses conditional statements to determine
	// which buttons to display: exit and next for the first page, exit,
	// previous
	// and next for every page between the first and last, and exit previous and
	// submit for the last page.
	public void updateActionsFlashcard() {
		// Set the button names
		if (questionPageNumber == 1)
			setButtonNames(new String[] { "Exit Flashcard", "Next Question", "SHOW" });
		else if (questionPageNumber > 1 && questionPageNumber < TakeQuizTakeSet.totalNumQuestions)
			setButtonNames(new String[] { "Exit Flashcard", "Previous Question", "Next Question", });
		else if (questionPageNumber == TakeQuizTakeSet.totalNumQuestions) {
			setButtonNames(new String[] { "Exit Flashcard", "Previous Question", "Submit and Finish" });
		}
		for (int i = 0; i < this.currentActions.length; i++) {
			switch (i) {
			case 0:
				this.currentActions[i].addActionListener(new ExitFlashCard());
				break;
				// The questionPageNumber increment was moved here to be able
				// to use show/Hide without a pop up
			case 1:
				if (questionPageNumber == 1){
					questionPageNumber++;
					this.currentActions[i].addActionListener(new FlashNextQuestion());}
				else if (questionPageNumber > 1)
					this.currentActions[i].addActionListener(new PreviousFlash());
				break;
				// The questionPageNumber increment was moved here to be able
				// to use show/Hide without a pop up
			case 2:
				if (questionPageNumber < TakeQuizTakeSet.totalNumQuestions){
					questionPageNumber++;
					this.currentActions[i].addActionListener(new FlashNextQuestion());}
				else
					this.currentActions[i].addActionListener(new SubmitFlash());
				break;

			default:
				System.out.println("Not enough implemented classes");
				break;
			}
		}
		// Action Listener for the showHide checkbox. FlashcardAnswer
		// is a public variable and should be set by a function that
		// get the answer for the current question
		showHide.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Save this code in case there is a problem with the show/Hide, and 
				// we need to revert back to the popup
				
				// Create PopUp object
				//PopUp answer;
				
				// If showHide is checked then show the answer. After
				// answer box is closed, the checkbox will become unselected
				/*if (showHide.isSelected()) 
				{
					
					FlashcardAnswer = "This is the answer";
					//answer = new PopUp("FlashCard Question/Answer", PopUp.PanelType.LOGO_ONLY_TYPE, PopUpType.FlashcardAnswerPopUp);
					showHide.setSelected(false);
					
				}*/
			
		
				
				// Set the answer string to NULL if show is checked.  Also need to set
				// the flashcard answer to the appropriate string here using the whatever
				// backend function was created
				if(isChecked  == true)
				{
					FlashcardAnswer = "This is the answer";
					
				}
				else
				{
					FlashcardAnswer = "";
					
				}
				
				// Decrement the page number to prevent the show/Hide box from advancing
				// to the next page
				questionPageNumber--;
				
			// Rebuild the page
			QuizAndFlashQuestionPage FquestionPage = new QuizAndFlashQuestionPage("Flash Card Question Page: " + QuizAndFlashQuestionPage.questionPageNumber,
						QuizAndFlashQuestionPage.PanelType.FLASHCARDPAGE, QuizAndFlashQuestionPage.PageType.FLASHCARD);
		    FquestionPage.parentController = parentController;
			parentController.replaceCurrentView(FquestionPage);
		    
			// Change boolean value of checked or unchecked to determine which string to
			// show: NULL or the answer string
			isChecked = !isChecked;
				
			}  	// end actionPerformed
			
		});		// end showHide.addActionListener
		
	}

	// Action listener for next question button. Clicking it takes the user
	// to the next question and increments the public questionPageNumber
	private class NextQuestion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			QuizAndFlashQuestionPage questionPage = new QuizAndFlashQuestionPage("Quiz Question Page: " + QuizAndFlashQuestionPage.questionPageNumber,
					QuizAndFlashQuestionPage.PanelType.QUESTIONPAGE, QuizAndFlashQuestionPage.PageType.QUIZ);
			questionPage.parentController = parentController;
			parentController.displayView(questionPage);
			questionPageNumber++; // increment the quiz question number

		}
	}
	private class NextQuestionMC implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());
			

			QuizAndFlashQuestionPage questionPage = new QuizAndFlashQuestionPage("Quiz Question Page: " + QuizAndFlashQuestionPage.questionPageNumber,
					QuizAndFlashQuestionPage.PanelType.QUESTIONPAGEMC, QuizAndFlashQuestionPage.PageType.QUIZ_MC);
			questionPage.parentController = parentController;
			parentController.displayView(questionPage);
			questionPageNumber++; // increment the quiz question number

		}
	}

	// Dismisses the current window and returns back to the last window.
	// It also decrements the questionPageNumber
	private class Previous implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());
			
			
			parentController.dismissView();
						
			questionPageNumber--; // decrement the quiz question number
			
			
			// An issue occurs for the page before last not displaying the radiobutton
			// checked.  However, it still keeps track of what was clicked.
			
			// Case for when the Unanswered question pop-up is called.  This is a work around
			// to an issue that caused issues with all the buttons on the frame not abled
			// to be pressed.  It also changes the value of pop up which needs to be kept
			// if this block is deleted.
			if(questionPageNumber == (totalNumQuestions - 1) && popUp == false ){
				
			
			popUp = true;
			parentController.dismissView();
			
			QuizAndFlashQuestionPage questionPage = new QuizAndFlashQuestionPage("Quiz Question Page: " + QuizAndFlashQuestionPage.questionPageNumber,
					QuizAndFlashQuestionPage.PanelType.QUESTIONPAGEMC, QuizAndFlashQuestionPage.PageType.QUIZ_MC);
			questionPage.parentController = parentController;
			parentController.displayView(questionPage);
			questionPageNumber++; // increment the quiz question number
			}

		}

	}
	private class PreviousFlash implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());
			
			
			parentController.dismissView();
						
			questionPageNumber--; // decrement the quiz question number
		

	}
}


	// Take you back to the quiz main page
	private class ExitQuiz implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			// System.out.println("Opening " + this.getClass());
			QuizAndFlashMain quizPage = new QuizAndFlashMain("Quiz Page", QuizAndFlashMain.PanelType.THREE_BUTTON_TYPE);
			quizPage.parentController = parentController;
			questionPageNumber = 1;
			parentController.displayView(quizPage);

		}

	}

	// Submit the quiz and go to the results page. Also reset the
	// questionPageNumber
	private class Submit implements ActionListener {


		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());
			
			boolean unansweredQuestion = false;
			
			for(int i = 0; i < totalNumQuestions; i++)
			{
				if(resultsChecker[i] == -1){
					unansweredQuestion = true;
					i = totalNumQuestions;
				}
					
			}
			
			if(unansweredQuestion){
				
			
			int reply1 = JOptionPane.showConfirmDialog(null, "One or more Questons have not been answered. Please"
					+ "complete before submitting\n", "Warning!", JOptionPane.DEFAULT_OPTION);
			if (reply1 == JOptionPane.OK_OPTION) {
					//Refresh the table here
					
					
					if(popUp){
						
					questionPageNumber--;
					popUp = false;
					}
					
				
					QuizAndFlashQuestionPage questionPage = new QuizAndFlashQuestionPage("Quiz Question Page: " + QuizAndFlashQuestionPage.questionPageNumber,
							QuizAndFlashQuestionPage.PanelType.QUESTIONPAGEMC, QuizAndFlashQuestionPage.PageType.QUIZ_MC);
					questionPage.parentController = parentController;
					parentController.replaceCurrentView(questionPage);
					
					
				}
				
			}
			else{
			QuizResultsPage results = new QuizResultsPage("Results", QuizResultsPage.PanelType.RESULTS);
			results.parentController = parentController;
			parentController.displayView(results);
			questionPageNumber = 1; // Reset the questionPageNumber
			}
		}
	}

	// Exits the flashcard take set and return to QuizAndFlashMain
	// Also resets the questionPageNumber
	private class SubmitFlash implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			StudyToolsMain fcMain = new StudyToolsMain("Study Tools Main Page", StudyToolsMain.PanelType.THREE_BUTTON_TYPE);
			fcMain.parentController = parentController;
			parentController.displayView(fcMain);
			questionPageNumber = 1; // reset questionPageNumber
		}
	}

	// Action listener for next question button. Clicking it takes the user
	// to the next question and increments the public questionPageNumber
	private class FlashNextQuestion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			QuizAndFlashQuestionPage FquestionPage = new QuizAndFlashQuestionPage("Flash Card Question Page: " + QuizAndFlashQuestionPage.questionPageNumber,
					QuizAndFlashQuestionPage.PanelType.FLASHCARDPAGE, QuizAndFlashQuestionPage.PageType.FLASHCARD);
			FquestionPage.parentController = parentController;
			parentController.displayView(FquestionPage);
			//questionPageNumber++; // increment the quiz question number
			FlashcardAnswer = "";
			
		}

	}

	// Take you back to the quiz main page
	private class ExitFlashCard implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			// System.out.println("Opening " + this.getClass());
			QuizAndFlashMain FlashCPage = new QuizAndFlashMain("Flash Card Page", QuizAndFlashMain.PanelType.THREE_BUTTON_TYPE,
					QuizAndFlashMain.PageType.FLASHCARD);
			FlashCPage.parentController = parentController;
			questionPageNumber = 1;
			parentController.displayView(FlashCPage);

		}

	}
	

}
