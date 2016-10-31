package edu.CSUSM.testTaker.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.CSUSM.testTaker.UI.CustomObjects.CustomButton;
import edu.CSUSM.testTaker.UI.Pages.ManageData;

/**
 * 
 * @author Justin
 * @purpose The purpose of this class is to make it easier to create JPanels.
 *          Are panel types are quite simple to create. For simplicity, there
 *          are a few type of panels: • TWO_BUTTON_TYPE - Has a logo and two
 *          buttons (sBs) • THREE_BUTTON_TYPE - Has a logo and three buttons
 *          (2 on top, one centered below) • LOGO_ONLY_TYPE - Has only a logo
 *          • QUESTION_BUILDER_TYPE - has a question input, posible answer
 *          input and radio buttons to selct correct answer
 *
 */
public class CustomPage extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static BufferedImage mainLogoToDisplay;
	public static Image newImg;
	public static ImageIcon newIcon;
	public JLabel imageLabel = new JLabel();
	protected static int centerOfNewFrame;
	public JButton[] currentActions;
	public String panelTypeString, titleOfCurrentQuestionPanel;
	public NavigationController parentController, parentController2;

	// Created an array of string for the button names
	private static final int MAX_NUMBER_OF_BUTTONS = 10;
	protected static String[] buttonName = new String[MAX_NUMBER_OF_BUTTONS];
	/** For the Question Panel */
	JLabel titleLabel;
	JPanel questionsMainPanel;

	/** End of question panel specific vars */

	public static enum PanelType {
		TWO_BUTTON_TYPE, THREE_BUTTON_TYPE, LOGO_ONLY_TYPE, QUESTION_BUILDER_TYPE
	};

	public CustomPage(PanelType currentPanelType, String imageAddress) {

		super();

		// Create the image from the image address
		try {
			URL url = new URL(imageAddress);
			CustomPage.mainLogoToDisplay = ImageIO.read(url);
		} catch (IOException ex) {
			// handle exception...
			System.out.println(ex.getMessage());
		}

		buildPanel(currentPanelType);
	}

	public CustomPage(PanelType currentPanelType, BufferedImage newImage) {
		super();
		CustomPage.mainLogoToDisplay = newImage;
		buildPanel(currentPanelType);
	}

	/**
	 * @param currentPanelType
	 * @description Call if the image was already found
	 */
	public CustomPage(PanelType currentPanelType) {
		super();
		// Set the layout
		this.setLayout(new BorderLayout());

		// Build the contents
		buildPanel(currentPanelType);

	}

	/**
	 * @param nc
	 *            Navigation controller used to show the next panel
	 */
	public void setParentController(NavigationController nc) {
		this.parentController = nc;
	}

	/*
	 *  added String[] to arguments of createButton Types which is passed
	 *  through buildPanel, so I added a String[] argument here as well
	 * 
	 */
		
		//Set the string value of the panel type
	private void buildPanel(PanelType currentPanelType) {

		this.panelTypeString = currentPanelType.toString();

		// Set the size of the panel
		this.setBounds(SideMenu.WIDTH, 0, GUIController.FRAME_WIDTH - SideMenu.WIDTH, GUIController.FRAME_HEIGHT);

		// Set the background color
		this.setBackground(Color.WHITE);

		switch (currentPanelType) {
		case TWO_BUTTON_TYPE:
		
			createTwoButtonType();
			break;

		case THREE_BUTTON_TYPE:
			createThreeButtonType();
			break;

		case LOGO_ONLY_TYPE:
			createLogoType();
			break;

		case QUESTION_BUILDER_TYPE:
			createQuestionBuilderType();
			break;

		default:
			System.out.println("Panel Type Not Found");
			break;
		}
	}

	protected void createLogoType(){

		// Set the background color // Option 1
		// this.setBackground(new Color(39, 72, 155)); //Option 2

		// Just display an image in the panel
		imageLabel = new JLabel();
		try {

			Image img = CustomPage.mainLogoToDisplay;
			newImg = img.getScaledInstance((this.getWidth() == 0) ? 600 : this.getWidth(),
					(this.getHeight() / 2 == 0) ? 250 : this.getHeight() / 2, java.awt.Image.SCALE_SMOOTH);
			newIcon = new ImageIcon(newImg);
			imageLabel.setIcon(newIcon);
		} catch (NullPointerException e) {
			System.out.println("No Logo could be found. Please check the project directory");
		} catch (Exception e) {
			System.out.println("Error Finding image: " + e.getMessage());
		}
		imageLabel.setBounds(0, 0, this.getWidth(), (int) (this.getHeight() / 2.25));

		// Align to center
		imageLabel.setHorizontalAlignment(JLabel.CENTER);
		imageLabel.setVerticalAlignment(JLabel.CENTER);
		this.add(imageLabel, BorderLayout.CENTER);

	}
	
/*
 *  	added String[] to arguments of createButton Types
 *	    to name the buttons with the string passed from
 *	    the GUIController
 * 
 */
	


		/**
		 * Later //Add the image to the top of the screen. We are going to have
		 * 3 objects at the top: Back btn, logo, and currentpage title
		 */
	protected void createTwoButtonType(){
		JLabel iconLabel = new JLabel();
		iconLabel.setBounds(0, 0, this.getWidth(), (int) (this.getHeight() / 2.25));
		iconLabel.setIcon(newIcon);
		// this.add(iconLabel);
		this.add(iconLabel, BorderLayout.CENTER);

		// Align to center
		iconLabel.setHorizontalAlignment(JLabel.CENTER);
		iconLabel.setVerticalAlignment(JLabel.CENTER);

		CustomPage.centerOfNewFrame = iconLabel.getHeight() - iconLabel.getY();

		addButtons(2);
		
	
		/* 
		 * Modified same as createTwoButtonType 
		 */
		

	}

	private void createThreeButtonType() {

		JLabel iconLabel = new JLabel();
		iconLabel.setBounds(0, 0, this.getWidth(), (int) (this.getHeight() / 2.25));
		iconLabel.setIcon(newIcon);
		this.add(iconLabel);

		// Align to center
		iconLabel.setHorizontalAlignment(JLabel.CENTER);
		iconLabel.setVerticalAlignment(JLabel.CENTER);

		// centerOfNewFrame = (this.getHeight() - (this.getHeight() -
		// iconLabel.getHeight()));
		addButtons(3);

	}

	private void createQuestionBuilderType() {
	/* 
	 * Modified addButtons to rename to string
	 */

		/** Testing - Move to actual class before release */
		this.titleOfCurrentQuestionPanel = "Question Builder";
		/** end of testing */

		// Create a title label to demonstrate what is shown

		// Now, add a panel for the questions
		/*
		 * this.questionsMainPanel = new JPanel();
		 * this.questionsMainPanel.setBackground(Color.RED);
		 * this.add(questionsMainPanel, BorderLayout.CENTER);
		 */

		// We need to add a few components to this view:
		// JComboBox - Allows the user to
		ManageData<String> newDataManager = new ManageData<String>(this.titleOfCurrentQuestionPanel,
				new String[] { "Cell 1" }, new String[] { "No ID" });
		this.add(newDataManager, BorderLayout.CENTER);

		addButtons(2);
		// setQuestionLayout();
	}

	private void addButtons(int count) {
		// Add a panel to the south for the buttons

		JPanel buttonHolder = new JPanel();
		buttonHolder.setLayout(new GridBagLayout()); // May need to be
														// gridbaglayout
		buttonHolder.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();
		this.add(buttonHolder, BorderLayout.SOUTH);

		this.currentActions = new CustomButton[count];

		for (int i = 0; i < count; i++) {

			c.gridwidth = 1;
			c.gridheight = 1;
			c.weightx = 0.5;
			c.gridx = i % 2;
			c.gridy = (i % 2 == 0 && i > 0) ? c.gridy++ : c.gridy;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(10, 10, 10, 10);

			// If the last button is an odd one, make it the same size as both
			// buttons above it, combined
			if (count % 2 == 1 && i == count - 1) {
				// System.out.println(this.panelTypeString + " for button: " +
				// (i));
				c.gridwidth = 2;
			}

			this.currentActions[i] = new CustomButton("Button " + (i + 1));
			buttonHolder.add(this.currentActions[i], c);
		}
	}

	public void setButtonNames(String[] btnNames) {
		// System.out.println("Modifying Buton names");

		try {
			for (int i = 0; i < this.currentActions.length; i++) {
				try {
					this.currentActions[i].setText(btnNames[i]);
				} catch (ArrayIndexOutOfBoundsException e) {

				}
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	/** Accessors and mutators */

	public static BufferedImage getMainLogoToDisplay() {
		return mainLogoToDisplay;
	}

	public static void setMainLogoToDisplay(BufferedImage mainLogoToDisplay) {
		CustomPage.mainLogoToDisplay = mainLogoToDisplay;
	}

}
