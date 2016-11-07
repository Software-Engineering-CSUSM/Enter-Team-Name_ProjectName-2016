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

	// Temporary to store textfield into for saving quiz
	// and set names
	public String quizNameStr, setNameStr;
	
	/**
	 * 
	 */
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

		if (pType == PopUpType.SaveQuiz)
			createWindowQuizPopUp();
		else if (pType == PopUpType.AddAnotherQuestion)
			createWindowQuestionPopUp();
		else if (pType == PopUpType.SaveSet)
			createWindowSetPopUp();
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
	public void createWindowQuestionPopUp() {
		// Creates the window using the GUIContoller to build the
		// frame. Note* the parameter is a dummy parameter which was
		// created to create 2 GUIController constructors
		GUIController popUpWindow = new GUIController(5);
		popUpWindow.setTitle("Add Question");

		// Create box to hold JLabel asking if the user would
		// likek to create another quiz
		Box jLabelBox = Box.createHorizontalBox(); // buttons
		jLabelBox.add(Box.createHorizontalGlue());
		jLabelBox.add(Box.createVerticalGlue());
		jLabelBox.add(new JLabel("Would you like to add another question?"));
		jLabelBox.add(Box.createHorizontalGlue());

		// Create buttons
		JButton create = new JButton("Create Another Question");
		JButton dontCreate = new JButton("Don't Create Another");

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
		CreateorDont.add(Box.createHorizontalStrut(popUpWindow.getWidth() / 8));
		CreateorDont.add(create);
		CreateorDont.add(Box.createHorizontalStrut(popUpWindow.getWidth() / 10));
		CreateorDont.add(dontCreate);
		CreateorDont.add(Box.createHorizontalStrut(popUpWindow.getWidth() / 8));
		CreateorDont.add(Box.createVerticalStrut(popUpWindow.getHeight() / 4));

		// Add the buttons to the bottom of the panel
		buttonPanel.add(CreateorDont, BorderLayout.SOUTH);
		popUpWindow.add(buttonPanel);

		// Action listener for don't create. If selected, it closes the
		// window and return to QuizandFlashcardMain
		dontCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				popUpWindow.dispose();
				StudyToolsMain QandFMain = new StudyToolsMain("Quiz Main Page", StudyToolsMain.PanelType.THREE_BUTTON_TYPE);
				QandFMain.parentController = parentController;
				parentController.displayView(QandFMain);

			}
		});

		// Action listener for create. If selected, calls the add question
		// page to allow the user to add another question
		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				popUpWindow.dispose();
				AddQuestion addQ = new AddQuestion("Add Question Page", AddQuestion.PanelType.Q_and_A_Type);
				addQ.parentController = parentController;
				parentController.displayView(addQ);
				
				
			}
		});

	}

	// Creates a pop up window prompting the user to type the name
	// of the newly created quiz and click save to save it.
	public void createWindowQuizPopUp() {

		// Creates the window using the GUIContoller to build the
		// frame. Note* the parameter is a dummy parameter which was
		// created to create 2 GUIController constructors
		GUIController popUpWindow = new GUIController(5);
		popUpWindow.setTitle("Save Quiz");

		// Create box to hold JLabel asking if the user to name
		// quiz and click save
		Box jLabelBox = Box.createVerticalBox();

		// Set positioning of components for the box
		jLabelBox.add(Box.createHorizontalStrut(popUpWindow.getWidth() / 3));
		jLabelBox.add(Box.createVerticalGlue());
		jLabelBox.add(new JLabel("Name your quiz and click save."));
		jLabelBox.add(Box.createHorizontalGlue());
		jLabelBox.add(Box.createVerticalStrut(10));

		// Create text field to enter in name of quiz to save
		// add it to a panel, and put the panel inside the box
		JTextField quizName = new JTextField(20);
		JPanel quizNamePanel = new JPanel();
		quizNamePanel.setBackground(Color.WHITE);
		quizNamePanel.add(quizName);
		jLabelBox.add(quizNamePanel);

		// Create save button and add to a panel
		JButton save = new JButton("Save Quiz");
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
				QuizAndFlashMain QuizPage = new QuizAndFlashMain("Quiz Main Page", QuizAndFlashMain.PanelType.THREE_BUTTON_TYPE);
				QuizPage.parentController = parentController;
				parentController.displayView(QuizPage);
				
				// Saves the quiz named typed in the text field
				// into a string.
				quizNameStr = quizName.getText();
				System.out.println(quizNameStr);

			}
		});

	}


	// Creates a pop up window prompting the user to type the name
	// of the newly created set and click save to save it.
	public void createWindowSetPopUp() {

		// Creates the window using the GUIContoller to build the
		// frame. Note* the parameter is a dummy parameter which was
		// created to create 2 GUIController constructors
		GUIController popUpWindow = new GUIController(5);

		// Set the windows title
		popUpWindow.setTitle("Save Set");

		// Create box to hold JLabel asking if the user to name
		// set and click save
		Box jLabelBox = Box.createVerticalBox();

		// Set positioning of components for the box
		jLabelBox.add(Box.createHorizontalStrut(popUpWindow.getWidth() / 3));
		jLabelBox.add(Box.createVerticalGlue());
		jLabelBox.add(new JLabel("Name your Flashcard Set and click save."));
		jLabelBox.add(Box.createHorizontalGlue());
		jLabelBox.add(Box.createVerticalStrut(10));

		// Create text field to enter in name of quiz to save
		// add it to a panel, and put the panel inside the box
		JTextField setName = new JTextField(20);
		JPanel quizNamePanel = new JPanel();
		quizNamePanel.setBackground(Color.WHITE);
		quizNamePanel.add(setName);
		jLabelBox.add(quizNamePanel);

		// Create save button and add to a panel
		JButton save = new JButton("Save Set");

		// Create panel to hold the jLabelBox asking the user
		// to create or not create a new quiz
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setLayout(new BorderLayout());

		// Add the Jlabel box to the
		// button panel which is then added to the frame
		buttonPanel.add(jLabelBox, BorderLayout.CENTER);

		// Create a box to hold all the components and add them
		// into the pop up window
		Box saveSetBox = Box.createHorizontalBox();
		saveSetBox.add(Box.createHorizontalStrut(200));
		saveSetBox.add(save);
		saveSetBox.add(Box.createVerticalStrut(popUpWindow.getHeight() / 4));

		buttonPanel.add(saveSetBox, BorderLayout.SOUTH);
		popUpWindow.add(buttonPanel);

		// Action listener to save the set name. Need to implement a function
		// to save the name of the set. After clicking save, the pop up window
		// is closed and then the user is returned to the Quiz and Flash Main
		// page
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				popUpWindow.dispose();
				QuizAndFlashMain FlashPage = new QuizAndFlashMain("Flash Card Main Page", QuizAndFlashMain.PanelType.THREE_BUTTON_TYPE,
						QuizAndFlashMain.PageType.FLASHCARD);
				FlashPage.setName("Flashcard Main Page");
				FlashPage.parentController = parentController;
				parentController.displayView(FlashPage);
				
				// Saves the set named typed in the text field
				// into a string.
				setNameStr = setName.getText();
				System.out.println(setNameStr);

			}
		});

	}

	private class ReturnTakeMain implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			TakeQuizTakeSet takeSet = new TakeQuizTakeSet("Take Quiz Page", TakeQuizTakeSet.PanelType.TWO_BUTTON_TYPE,
					TakeQuizTakeSet.PageType.FLASHCARD);
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
