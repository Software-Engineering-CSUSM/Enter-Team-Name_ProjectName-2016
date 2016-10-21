package edu.CSUSM.testTaker.UI.Pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.CSUSM.testTaker.UI.CustomPage;
import edu.CSUSM.testTaker.UI.GUIController;

public class PopUp extends CustomPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PopUp(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub

		createWindowQuestionPopUp();
	}

	public PopUp(PanelType currentPanelType, int determineQuizorQuestion) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		if (determineQuizorQuestion == 0)
			createWindowQuizPopUp();
		else
			createWindowQuestionPopUp();
	}

	public PopUp(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public PopUp(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}

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

	public void createWindowQuestionPopUp() {
		GUIController popUpWindow = new GUIController(5);

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

		buttonPanel.add(CreateorDont, BorderLayout.SOUTH);
		popUpWindow.add(buttonPanel);

		dontCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				popUpWindow.dispose();
				QuizMain QuizPage = new QuizMain(QuizMain.PanelType.THREE_BUTTON_TYPE);
				QuizPage.setName("Quiz Main Page");
				QuizPage.parentController = parentController;
				parentController.displayView(QuizPage);

			}
		});

		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				popUpWindow.dispose();
				AddQuestion addQ = new AddQuestion(AddQuestion.PanelType.TWO_BUTTON_TYPE);
				addQ.setName("Add Question Page");
				addQ.parentController = parentController;
				parentController.displayView(addQ);

			}
		});

	}

	public void createWindowQuizPopUp() {
		GUIController popUpWindow = new GUIController(5);

		// Create box to hold JLabel asking if the user would
		// likek to create another quiz
		Box jLabelBox = Box.createVerticalBox(); // buttons
		// Create text field to enter quiz name
		jLabelBox.add(Box.createHorizontalStrut(popUpWindow.getWidth() / 3));
		jLabelBox.add(Box.createVerticalGlue());
		jLabelBox.add(new JLabel("Name your quiz and click save."));
		jLabelBox.add(Box.createHorizontalGlue());
		jLabelBox.add(Box.createVerticalStrut(10));
		JTextField quizName = new JTextField(20);
		JPanel quizNamePanel = new JPanel();
		quizNamePanel.setBackground(Color.WHITE);
		quizNamePanel.add(quizName);
		jLabelBox.add(quizNamePanel);

		// Create buttons
		JButton save = new JButton("Save Quiz");

		// Create panel to hold the jLabelBox asking the user
		// to create or not create a new quiz
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setLayout(new BorderLayout());

		// Add the Jlabel box to the
		// button panel which is then added to the frame

		buttonPanel.add(jLabelBox, BorderLayout.CENTER);

		// Create a box to hold the buttons to either create a
		// new quiz or return to quiz main
		Box CreateorDont = Box.createHorizontalBox();
		CreateorDont.add(Box.createHorizontalStrut(200));
		CreateorDont.add(save);
		CreateorDont.add(Box.createVerticalStrut(popUpWindow.getHeight() / 4));

		buttonPanel.add(CreateorDont, BorderLayout.SOUTH);
		popUpWindow.add(buttonPanel);

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				popUpWindow.dispose();
				QuizMain QuizPage = new QuizMain(QuizMain.PanelType.THREE_BUTTON_TYPE);
				QuizPage.setName("Quiz Main Page");
				QuizPage.parentController = parentController;
				parentController.displayView(QuizPage);

			}
		});

	}

	private class ReturnTakeQuiz implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Opening " + this.getClass());

			TakeQuiz takeQ = new TakeQuiz(TakeQuiz.PanelType.TWO_BUTTON_TYPE);
			takeQ.setName("Take Quiz Page");
			takeQ.parentController = parentController;
			parentController.displayView(takeQ);

		}
	}

}
