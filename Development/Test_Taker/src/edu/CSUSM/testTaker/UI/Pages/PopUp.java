package edu.CSUSM.testTaker.UI.Pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.CSUSM.testTaker.LibraryController;
import edu.CSUSM.testTaker.UI.CustomPage;
import edu.CSUSM.testTaker.UI.GUIController;
import edu.CSUSM.testTaker.UI.CustomPage.PanelType;

/**
 * @author Jeremy
 *
 * @purpose This is the PopUp window class. It is currently called from three
 *          different classes: AddQuestion (save added question,
 *          CreateNewQuizOrSet (Save Quiz or Save Set), and
 *          QuizAndFlashQuestionPage (showHide action listener to show flashcard
 *          answer). CreateWindowQuiz and CreateWindowSet currently save the
 *          names into a public string.
 *          
 *          We need to implement the function to set the string name inside both
 *          function's save action listener.
 * 
 * 
 */

public class PopUp extends CustomPage {

	
	
	/**
	 * 
	 */
	// Temporary to store textfield into for saving quiz
	// and set names
	public String quizNameStr, setNameStr, courseNameStr, testNameStr, questionNameStr;
	
	private static final long serialVersionUID = 1L;

	public PopUp(String panelName, PanelType currentPanelType) {
		super(panelName, currentPanelType);
		// TODO Auto-generated constructor stub

		// createWindowQuestionPopUp();
	}

	// Constructor to create pop up windows. It uses enum type
	// to determine the appropriate window to display.
	public PopUp(String panelName, PanelType currentPanelType, PopUpType pType) {
		super(panelName, currentPanelType);
		// TODO Auto-generated constructor stub

		// Text field with save Pop-Up Save name of quiz, set, test, or course
				if (pType == PopUpType.SaveQuiz || pType == PopUpType.SaveSet || pType == PopUpType.AddCourse || pType == PopUpType.AddTest || pType == PopUpType.AddQuestionName)
					createSaveWindowPopUp(pType);
				// Two button Pop-Up.  For add another question ("Create" or "Don't)
				// and for deleting course or test confirmation ("Yes" or "No")
				else if (pType == PopUpType.AddAnotherQuestion || pType == PopUpType.AddAnotherQuestionMC ||  pType == PopUpType.DeleteCourse || pType == PopUpType.DeleteTest || pType == PopUpType.DeleteQuestion)
					AddQuestion_or_DeleteItemPopUp(pType);
				//else if (pType == PopUpType.DeleteCourse)
					//deleteCoursePopUp();
				else if (pType == PopUpType.FlashcardAnswerPopUp)
					createFlashAnswerPopUp();

	}

	public PopUp(String panelName, PanelType currentPanelType, BufferedImage newImage) {
		super(panelName, currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public PopUp(String panelName, PanelType currentPanelType, String imageAddress) {
		super(panelName, currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	// Doesn't appear to be needed
	public void updateActions() {
		/*
		 * System.out.println("In the update for PopUp"); // Set the button
		 * names setButtonNames(new String[] { "Return to Take Quiz Page",
		 * "Does Nothing" });
		 * 
		 * for (int i = 0; i < this.currentActions.length; i++) { switch (i) {
		 * case 0: this.currentActions[i].addActionListener(new
		 * ReturnTakeMain()); break; case 1: //
		 * this.currentActions[i].addActionListener(new Previous()); break;
		 * default: System.out.println("Not enough implemented classes"); break;
		 * } }
		 */
	}

	// This window is created to ask the user if they would like
		// to add another question from the add question page
	public void AddQuestion_or_DeleteItemPopUp(PopUpType pType) 
		{
			// Creates the window using the GUIContoller to build the
			// frame. Note* the parameter is a dummy parameter which was
			// created to create 2 GUIController constructors
			GUIController popUpWindow = new GUIController(5);
			
			JLabel courseOrQuestionLabel = new JLabel();
			
			// Create buttons
			JButton create_Yes = new JButton();
			JButton dontCreate_No = new JButton();
			
			// Creating window for adding another question
			if(pType == PopUpType.AddAnotherQuestion)
			{
				popUpWindow.setTitle("Add Question");
				courseOrQuestionLabel = new JLabel("Would you like to add another question?");
				create_Yes = new JButton("Create Another Question");
				dontCreate_No = new JButton("Don't Create Another");
			}
			else if(pType == PopUpType.AddAnotherQuestionMC)
			{
				popUpWindow.setTitle("Add Question");
				courseOrQuestionLabel = new JLabel("Would you like to add another question?");
				create_Yes = new JButton("Create Another Question");
				dontCreate_No = new JButton("Don't Create Another");
			
			}
			// Creating window for deleting a course
			else if(pType == PopUpType.DeleteCourse)
			{
				popUpWindow.setTitle("Delete Course");
				courseOrQuestionLabel = new JLabel("Are you sure you want to delete this course?");
				create_Yes = new JButton("Yes");
				dontCreate_No = new JButton("No");
							
			}
			// Creating window for deleting a test
			else if(pType == PopUpType.DeleteTest)
			{
				popUpWindow.setTitle("Delete Test");
				courseOrQuestionLabel = new JLabel("Are you sure you want to delete this test?");
				create_Yes = new JButton("Yes");
				dontCreate_No = new JButton("No");
				
			}
			// Creating window for deleting a question
			else if(pType == PopUpType.DeleteQuestion)
			{
				popUpWindow.setTitle("Delete Question");
				courseOrQuestionLabel = new JLabel("Are you sure you want to delete this question?");
				create_Yes = new JButton("Yes");
				dontCreate_No = new JButton("No");
			}
			
			

			// Create box to hold JLabel asking if the user would
			// likek to create another quiz
			Box jLabelBox = Box.createHorizontalBox(); // buttons
			jLabelBox.add(Box.createHorizontalGlue());
			jLabelBox.add(Box.createVerticalGlue());
			jLabelBox.add(courseOrQuestionLabel);
			jLabelBox.add(Box.createHorizontalGlue());


			// Create panel to hold the jLabelBox asking the user
			// to create or not create a new quiz
			JPanel buttonPanel = new JPanel();
			buttonPanel.setBackground(Color.WHITE);
			buttonPanel.setLayout(new BorderLayout());

			// Add the boxes containing the buttons to the
			// button panel which is then added to the frame

			buttonPanel.add(jLabelBox, BorderLayout.CENTER);

			// Create a box to hold the buttons to either create a
			// new quiz or return to quiz main
			Box CreateorDont = Box.createHorizontalBox();
			CreateorDont.add(Box.createHorizontalGlue());
			CreateorDont.add(create_Yes);
			CreateorDont.add(Box.createHorizontalGlue());
			CreateorDont.add(dontCreate_No);
			CreateorDont.add(Box.createVerticalStrut(popUpWindow.getHeight() / 4));

			// Add the buttons to the bottom of the panel
			buttonPanel.add(CreateorDont, BorderLayout.SOUTH);
			popUpWindow.add(buttonPanel);

			// Action listener for don't create. If selected, it closes the
			// window and return to QuizandFlashcardMain. Also action listener
			// for "No" for delete course
			dontCreate_No.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					popUpWindow.dispose();
					
					//Case for don't create another question for add question popup
					if(pType == PopUpType.AddAnotherQuestion || pType == PopUpType.AddAnotherQuestionMC)
					{
						/*
						StudyToolsMain QandFMain = new StudyToolsMain("Study Tools", StudyToolsMain.PanelType.TWO_BUTTON_TYPE);
						//QandFMain.setName("Quiz Main Page");
						QandFMain.parentController = parentController;
						parentController.displayView(QandFMain);*/
						
						if(LibraryController.CURRENT_TEST.numQuestions() > 0){
							

							//Set the rows to all questions in teh test
							try{
								CustomPage.setQBRowHeaders(LibraryController.CURRENT_TEST.getQuestionNames().toArray(new String[LibraryController.CURRENT_TEST.getQuestionNames().size()]));
								CustomPage.setQBRowIDs(LibraryController.CURRENT_TEST.getQuestionIDList().toArray(new String[LibraryController.CURRENT_TEST.getQuestionIDList().size()]));

							}catch(Exception emp){
								emp.printStackTrace();
							}
						}
						else{
							System.out.println("No Questions Yet");
							CustomPage.setQBRowHeaders(new String[]{"No Questions Yet"});
							CustomPage.setQBRowIDs(new String[]{"No Questions Yet"});
						}

						//Save the reference to the current test
						/** Currently returning a null object */
						try{
							//LibraryController.CURRENT_TEST = LibraryController.retrieveTest(ManageData.currentIDSelected);
							System.out.println("Current ID: " + ManageData.currentIDSelected + " Current Type: " + LibraryController.getItemType(ManageData.currentIDSelected));
							//System.out.print(LibraryController.CURRENT_TEST.toString());
						}catch(Exception ex){
							System.out.println("Error getting test: " + ex.getMessage());
						}

						QuestionListManager qlm = new QuestionListManager("Question List Manager", QuestionListManager.PanelType.QUESTION_BUILDER_TYPE);

						ManageData.resetButtons();
						qlm.parentController = parentController;
						parentController.displayView(qlm);
					}
		
					
					//Case for don't delete course for delete course pop up
					
					else if(pType == PopUpType.DeleteCourse)
					{
						System.out.println("Do not delete course");
						
					}
					//Case for don't delete test for delete test pop up
					else if(pType == PopUpType.DeleteTest)
					{
						System.out.println("Do not delete test");
						
					}
					//Case for don't delete test for delete question pop up
					else if(pType == PopUpType.DeleteQuestion)
					{
						System.out.println("Do not delete question");
						
					}
					

				}
			});

			// Action listener for create. If selected, calls the add question
			// page to allow the user to add another question.  Also listener for
			// delete course pop up.  
			create_Yes.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					popUpWindow.dispose();
					
					//Case for create another question to add question
					if(pType == PopUpType.AddAnotherQuestion)
					{
					
					AddQuestion addQ = new AddQuestion("Add Question Page", AddQuestion.PanelType.Q_and_A_Type_MC);
					addQ.parentController = parentController;
					parentController.displayView(addQ);
					addQ.revalidate();
					}
					else if(pType == PopUpType.AddAnotherQuestionMC)
					{
					
					AddQuestion addQ = new AddQuestion("Add Question Page", AddQuestion.PanelType.Q_and_A_Type_MC);
					addQ.parentController = parentController;
					parentController.displayView(addQ);
					addQ.revalidate();
					}

					
					//Case if user wants to delete course for delete course
					// Add function to delete the course
					else if(pType == PopUpType.DeleteCourse)
					{
						System.out.println("Deleting course");
						
					}
					
					//Case if user wants to delete test for delete course
					// add function to delete the test
					else if(pType == PopUpType.DeleteTest)
					{
						System.out.println("Deleting test");
						
					}
					//Case for delete question for delete course pop up
					else if(pType == PopUpType.DeleteQuestion)
					{
						System.out.println("Deleting question");
						
					}
					
				}
			});

		}

		// Creates a pop up window prompting the user to type the name
		// of the newly created quiz and click save to save it.
		public void createSaveWindowPopUp(PopUpType pType) {
			
			
			// Creates the window using the GUIContoller to build the
			// frame. Note* the parameter is a dummy parameter which was
			// created to create 2 GUIController constructors
			GUIController popUpWindow = new GUIController(5);
			
			
			JLabel quizSetOrCourseLabel = new JLabel();
			JButton save = new JButton();
			if(pType == PopUpType.SaveQuiz)
			{
				popUpWindow.setTitle("Save Quiz");
				quizSetOrCourseLabel = new JLabel("Name your quiz and click save.");
				save = new JButton("Save Quiz");
			}
			else if(pType == PopUpType.SaveSet)
			{
				popUpWindow.setTitle("Save Set");
				quizSetOrCourseLabel = new JLabel("Name your Flashcard Set and click save.");
				save = new JButton("Save Set");
			}
			else if(pType == PopUpType.AddCourse)
			{
				popUpWindow.setTitle("Add Course");
				quizSetOrCourseLabel = new JLabel("Name your course and click save.");
				save = new JButton("Save Course");
				
			}
			else if(pType == PopUpType.AddTest)
			{
				popUpWindow.setTitle("Add Test");
				quizSetOrCourseLabel = new JLabel("Name your test and click save.");
				save = new JButton("Save Test");
				
			}
			
			else if(pType == PopUpType.AddQuestionName)
			{
				popUpWindow.setTitle("Add Question");
				quizSetOrCourseLabel = new JLabel("Name your Question and click save.");
				save = new JButton("Save Question");
				
			}
		
			// Create box to hold JLabel asking if the user to name
			// quiz and click save
			Box jLabelBox = Box.createVerticalBox();

			// Set positioning of components for the box
			jLabelBox.add(Box.createHorizontalStrut(popUpWindow.getWidth() / 3));
			jLabelBox.add(Box.createVerticalGlue());
			jLabelBox.add(quizSetOrCourseLabel);
			jLabelBox.add(Box.createHorizontalGlue());
			jLabelBox.add(Box.createVerticalStrut(10));

			// Create text field to enter in name of quiz, set, test, or
			//course add it to a panel, and put the panel inside the box
			JTextField Quiz_Set_Test_Course_or_Question = new JTextField(20);
			JPanel quizNamePanel = new JPanel();
			quizNamePanel.setBackground(Color.WHITE);
			quizNamePanel.add(Quiz_Set_Test_Course_or_Question);
			jLabelBox.add(quizNamePanel);

			// Create save button and add to a panel
			//JButton save = new JButton("Save Quiz");
			JPanel buttonPanel = new JPanel();
			buttonPanel.setBackground(Color.WHITE);
			buttonPanel.setLayout(new BorderLayout());

			// Add the Jlabel box to the
			// button panel which is then added to the frame
			buttonPanel.add(jLabelBox, BorderLayout.CENTER);

			// Create a box to hold all the components and add them
			// into the pop up window
			Box saveQuizBox = Box.createHorizontalBox();
			saveQuizBox.add(Box.createHorizontalStrut(200));
			saveQuizBox.add(save);
			saveQuizBox.add(Box.createVerticalStrut(popUpWindow.getHeight() / 4));

			buttonPanel.add(saveQuizBox, BorderLayout.SOUTH);
			popUpWindow.add(buttonPanel);

			// Action listner to save the quiz name. Need to implement a function
			// to save the name of the quiz. After clicking save, the pop up window
			// is closed and then the user is returned to the Quiz and Flash Main
			// page
			save.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					popUpWindow.dispose();
				
					// Case for Quiz
					// Saves the quiz named typed in the text field
					// into a string, and return to quizandflashmain
					if(pType == PopUpType.SaveQuiz)
					{
						
						quizNameStr = Quiz_Set_Test_Course_or_Question.getText();
						System.out.println(quizNameStr);
						
						QuizAndFlashMain QuizPage = new QuizAndFlashMain("Quiz Main Page", QuizAndFlashMain.PanelType.THREE_BUTTON_TYPE);
						//QuizPage.setName("Quiz Main Page");
						QuizPage.parentController = parentController;
						parentController.displayView(QuizPage);
						
					}
					// Case for Flashcard Set
					// Saves the set named typed in the text field
					// into a string, and return to quizandflashmain
					else if(pType == PopUpType.SaveSet)
					{
						
						setNameStr = Quiz_Set_Test_Course_or_Question.getText();
						System.out.println(setNameStr);
						
						QuizAndFlashMain QuizPage = new QuizAndFlashMain("Quiz Main Page", QuizAndFlashMain.PanelType.THREE_BUTTON_TYPE);
						//QuizPage.setName("Quiz Main Page");
						QuizPage.parentController = parentController;
						parentController.displayView(QuizPage);
						
					}
					// Case for AddCourse
					// Saves the course name typed in the text field into a string
					// a prints for testing purposes
					else if(pType == PopUpType.AddCourse)
					{
						courseNameStr = Quiz_Set_Test_Course_or_Question.getText();
						System.out.println(courseNameStr);					
						
					}
					// Case for AddTest
					// Saves the test name typed in the text field into a string
					// a prints for testing purposes
					else if(pType == PopUpType.AddTest)
					{
						testNameStr = Quiz_Set_Test_Course_or_Question.getText();
						System.out.println(testNameStr);
						
					}
					
					// Case for Add Question Name
					// Saves the question name typed in the text field into a string
					// a prints for testing purposes
					else if(pType == PopUpType.AddQuestionName)
					{
						questionNameStr = Quiz_Set_Test_Course_or_Question.getText();
						System.out.println(questionNameStr);
						
					}

				}
			});

		}



		private class ReturnTakeMain implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Opening " + this.getClass());

				TakeQuizTakeSet takeSet = new TakeQuizTakeSet("Take Quiz Page", TakeQuizTakeSet.PanelType.TWO_BUTTON_TYPE,
						TakeQuizTakeSet.PageType.FLASHCARD);
				//takeSet.setName("Take Quiz Page");
				takeSet.parentController = parentController;
				parentController.displayView(takeSet);

			}
		}

		// Flashcard answer pop up window. Creates pop up window that
		// displays the answer to the flashcard question when the show/hide
		// checkbox is clicked
		public void createFlashAnswerPopUp() {

			// Creates the window using the GUIContoller to build the
			// frame. Note* the parameter is a dummy parameter which was
			// created to create 2 GUIController constructors
			GUIController popUpWindow = new GUIController(5);

			// Set the title of the window
			popUpWindow.setTitle("Flashcard Answer");

			// Create JLabel to hold the public string holding the
			// answer to the flashcard question
			JLabel FlashAnswer = new JLabel(FlashcardAnswer);
			Font font = new Font("Courier", Font.BOLD, 18);
			FlashAnswer.setFont(font);

			// Had to add the label to a box before putting it
			// into the panel due to it aligning to the left.
			Box answerBox = Box.createHorizontalBox();
			answerBox.add(Box.createHorizontalGlue());
			answerBox.add(FlashAnswer);
			answerBox.add(Box.createHorizontalGlue());

			// Create the panel to put the answer into and set its
			// color.
			JPanel answerPanel = new JPanel(new BorderLayout());
			answerPanel.setBackground(Color.WHITE);
			answerPanel.add(answerBox);

			// Add the answer panel to the popUpWindow
			popUpWindow.add(answerPanel, BorderLayout.CENTER);

		}

	}
