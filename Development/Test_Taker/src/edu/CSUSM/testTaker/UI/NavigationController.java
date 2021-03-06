package edu.CSUSM.testTaker.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

/**
 * @author Justin
 *
 */
/**
 * @author Justin
 *
 */
public class NavigationController extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Set default values
	 */
	public static String DEFAULT_BACK_BUTTON_TITLE = "<< Back";
	public static int DEFAULT_NAVIGATION_BAR_HEIGHT = 50;

	public JButton backButton;
	public JLabel currentPageDescriptionLabel;

	private JPanel navigationPanel;
	public static BufferedImage applicationImage;

	// Now, for the page manager items
	private Stack<JPanel> panelsDisplayed; // Change to private
	private JPanel viewShown, initialView;

	/**
	 * @Description Default constructor for the Navigation Controller. Adds the Navigation Bar to the top.
	 */
	public NavigationController() {

		// For visibility, make the background black;
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		addNavigationBar();
	}

	/**
	 * @Description constructor to create a Navigation Controller when an image is wished to be used.
	 * @param applicationImage - Image to display in the navigation bar
	 */
	public NavigationController(BufferedImage applicationImage) {
		this.setLayout(new BorderLayout());
		NavigationController.applicationImage = applicationImage;
		addNavigationBar();
	}
	
	
	/**
	 * @return The current view shown in the stack
	 */
	public JPanel getViewShown(){
		return viewShown;
	}
	
	/**
	 * @description Shows the new page without showing the back button or addinga  new page to the stack.
	 * @param page
	 */
	public void replaceCurrentView(JPanel page){
		//Check to see if view shown is null
		if(this.viewShown != null){
			//Do the replacement
			//Remove the current page
			this.remove(this.viewShown);
			
			//Set the view shown to the new page
			this.viewShown = page;
			
			//Add the view
			this.add(this.viewShown, BorderLayout.CENTER);
			
			verifyBackButton();
			
			//reloads content
			revalidate();
			repaint();
			
		}else{
			//Set the page as thte first page
			displayView(page);
		}
	}

	/**
	 * @Description - Adds the navigation bar to the top of the page. This is to manage the page navigation.
	 */
	private void addNavigationBar() {
		/** For Testing */
		if (NavigationController.applicationImage == null) {
			URL url;
			try {
				url = new URL(
						"https://github.com/Software-Engineering-CSUSM/Test-Taker/blob/master/Team%20Graphics/Test_Taker_LogoOption3.png?raw=true");
				NavigationController.applicationImage = ImageIO.read(url);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/** End of Testing */
		panelsDisplayed = new Stack<JPanel>();

		navigationPanel = new JPanel();
		navigationPanel.setLayout(new BorderLayout());
		navigationPanel.setBackground(Color.WHITE);
		navigationPanel.setMinimumSize(new Dimension(this.getWidth(), DEFAULT_NAVIGATION_BAR_HEIGHT));
		navigationPanel.setBounds(0, 0, this.getWidth(), DEFAULT_NAVIGATION_BAR_HEIGHT);

		// Add the back buton
		backButton = new JButton(DEFAULT_BACK_BUTTON_TITLE);
		backButton.setForeground(Color.BLUE);
		backButton.addActionListener(new BackSelected());
		backButton.setBorder(new EmptyBorder(0, DEFAULT_NAVIGATION_BAR_HEIGHT / 2, 0, 0));
		backButton.setBackground(navigationPanel.getBackground());
		backButton.setOpaque(true);
		navigationPanel.add(backButton, BorderLayout.WEST);

		// Add the application Logo
		// Just display an image in the panel
		JLabel imageLabel = new JLabel();
		try {
			Image newImg = NavigationController.applicationImage.getScaledInstance(DEFAULT_NAVIGATION_BAR_HEIGHT * 2,
					DEFAULT_NAVIGATION_BAR_HEIGHT, java.awt.Image.SCALE_SMOOTH);
			ImageIcon newIcon = new ImageIcon(newImg);
			imageLabel.setIcon(newIcon);
		} catch (NullPointerException e) {
			System.out.println("No Logo could be found. Please check the project directory");
		} catch (Exception e) {
			System.out.println("Error Finding image: " + e.getMessage());
		}
		imageLabel.setBorder(new EmptyBorder(0, DEFAULT_NAVIGATION_BAR_HEIGHT, 0, DEFAULT_NAVIGATION_BAR_HEIGHT));
		imageLabel.setHorizontalAlignment(JLabel.CENTER);
		imageLabel.setVerticalAlignment(JLabel.CENTER);
		navigationPanel.add(imageLabel, BorderLayout.CENTER);

		// Add the current Page title
		currentPageDescriptionLabel = new JLabel("Page 1");
		currentPageDescriptionLabel.setBorder(new EmptyBorder(0, 0, 0, DEFAULT_NAVIGATION_BAR_HEIGHT / 2));
		navigationPanel.add(currentPageDescriptionLabel, BorderLayout.EAST);
		navigationPanel.setName("Navigation Panel");
		navigationPanel.setBorder(new MatteBorder(0,0, 1, 0, Color.LIGHT_GRAY));

		//Add the navigation bar to the top of the page/
		this.add(navigationPanel, BorderLayout.NORTH);

		// If the stack is empty, hide the back button
		if (panelsDisplayed.isEmpty()) {
			backButton.setVisible(false);
		}
		this.setVisible(true);
	}

	/**
	 * @param firstView - Sets the first view that will be shown in the navigation controller.
	 */
	public void setInitialView(JPanel firstView) {
		this.viewShown = firstView;
		this.add(this.viewShown, BorderLayout.CENTER);
		this.backButton.setVisible(false);
		this.currentPageDescriptionLabel.setText(firstView.getName());
		this.initialView = this.viewShown;
	}

	/**
	 * @Description - Displays the next view in the lineup and pushes the current view in the stack, to be referenced as the back button is pressed.
	 * @param panelToDisplay - The panel that will be displayed next to the view
	 */
	public void displayView(JPanel panelToDisplay) {
		if (this.viewShown != null) {
			// Add it to the stack
			this.panelsDisplayed.push(this.viewShown);

			// Set it to 'not visible'
			this.remove(this.viewShown);

			// Show the back button
			this.backButton.setVisible(true);
		} else {
			// If there is not a view currently shown, no need to add it to the
			// stack before hand
			System.out.println("Could not locate a previous view");
			this.viewShown = panelToDisplay;
			this.add(this.viewShown, BorderLayout.CENTER);
			this.backButton.setVisible(false);
			this.initialView = this.viewShown;
		}
		// System.out.println("Size of Stack: " + this.panelsDisplayed.size());

		// Now that the view is ready, display it
		this.add(panelToDisplay, BorderLayout.CENTER);
		panelToDisplay.setVisible(true);

		// Set this panel to the current panel
		this.viewShown = panelToDisplay;
		// this.currentPageDescriptionLabel.setText(this.viewShown.getName());
		currentPageDescriptionLabel.setText((this.viewShown.getName() != null) ? this.viewShown.getName() : "");

	}

	/**
	 * @description Indicates that the back button was selected. now, the most
	 *              recent view will be popped off the stack and displayed
	 */
	public void dismissView() {
		// System.out.println("Size of Stack: " + this.panelsDisplayed.size());

		// Hide the currentview
		this.viewShown.setVisible(false);

		// Pop the view from the stack and display it
		if (this.panelsDisplayed.size() > 0) {
			JPanel newPanel = this.panelsDisplayed.pop();
			this.add(newPanel);
			newPanel.setVisible(true);
			this.viewShown = newPanel;
		} else {
			System.out.println("Nothing found in the stack");
		}

		currentPageDescriptionLabel.setText((this.viewShown.getName() != null) ? this.viewShown.getName() : "");

		// If the stack is empty, hide the back button
		verifyBackButton();

	}

	/**
	 * @description Goes back to the prior view
	 */
	public class BackSelected implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("Back Selected");
			dismissView();
		}
	}

	/**
	 * @Description Resets all subpanels so only the arent will be displayed
	 */
	public void reset() {
		// Remove all items from the stack, except the first
		while (this.panelsDisplayed.size() > 0) {
			dismissView();
		}
		// System.out.println("Size of stack: " + this.panelsDisplayed.size());

		verifyBackButton();
		setInitialView(initialView);
		
		//repaint the initial view
		initialView.repaint();
		initialView.revalidate();

	}

	
	/**
	 * @Description - Determines if the back button should be viewed of not. - Based on the size of the stack.
	 */
	private void verifyBackButton() {
		if (this.panelsDisplayed.size() == 0) {
			backButton.setVisible(false);
		} else {
			backButton.setVisible(true);
		}
	}

	/**
	 * @Description - Sets the current panel name - to be displayed in the navigation bar;
	 * @param newName
	 */
	public void setCurrentPanelName(String newName) {
		// Set the panel name
		this.viewShown.setName(newName);

		// Display it in the top right
		this.currentPageDescriptionLabel.setText(newName);
	}

}
