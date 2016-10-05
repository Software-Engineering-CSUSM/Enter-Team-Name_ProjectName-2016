package com.jtizz.NavigationController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
	private static Image applicationImage;
	
	//Now, for the page manager items
	private Stack<JPanel> panelsDisplayed;
	private JPanel viewShown;
	
	
	public NavigationController(){

		//For visibility, make the background black;
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		addNavigationBar();
	}

	public NavigationController(Image applicationImage){
		this.setLayout(new BorderLayout());
		NavigationController.applicationImage = applicationImage;
		addNavigationBar();
	}

	private void addNavigationBar(){
		/** For Testing */
		URL url;
		try {
			url = new URL("https://github.com/Software-Engineering-CSUSM/Test-Taker/blob/master/Team%20Graphics/Test_Taker_LogoOption3.png?raw=true");
			NavigationController.applicationImage = ImageIO.read(url);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/** End of Testing */ 
		panelsDisplayed = new Stack<JPanel>();

		navigationPanel  = new JPanel();
		navigationPanel.setLayout(new BorderLayout());
		navigationPanel.setBackground(Color.WHITE);
		navigationPanel.setMinimumSize(new Dimension(this.getWidth(), DEFAULT_NAVIGATION_BAR_HEIGHT));
		navigationPanel.setBounds(0, 0, this.getWidth(), DEFAULT_NAVIGATION_BAR_HEIGHT);

		//Add the back buton
		backButton = new JButton(DEFAULT_BACK_BUTTON_TITLE);
		backButton.setForeground(Color.BLUE);
		backButton.addActionListener(new BackSelected());
		backButton.setBorder(new EmptyBorder(0,DEFAULT_NAVIGATION_BAR_HEIGHT/2,0,0));
		navigationPanel.add(backButton, BorderLayout.WEST);	

		//Add the application Logo
		//Just display an image in the panel
		JLabel imageLabel = new JLabel();
		try{
			Image newImg = NavigationController.applicationImage.getScaledInstance(DEFAULT_NAVIGATION_BAR_HEIGHT*2, DEFAULT_NAVIGATION_BAR_HEIGHT,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon newIcon = new ImageIcon(newImg);
			imageLabel.setIcon(newIcon);
		}
		catch(NullPointerException e){
			System.out.println("No Logo could be found. Please check the project directory");
		}
		catch(Exception e){
			System.out.println("Error Finding image: " + e.getMessage());
		}
		imageLabel.setBorder(new EmptyBorder(0, DEFAULT_NAVIGATION_BAR_HEIGHT, 0, DEFAULT_NAVIGATION_BAR_HEIGHT));
		imageLabel.setHorizontalAlignment(JLabel.CENTER);
		imageLabel.setVerticalAlignment(JLabel.CENTER);
		navigationPanel.add(imageLabel, BorderLayout.CENTER);
		
		//Add the current Page title
		currentPageDescriptionLabel = new JLabel("Page 1");
		currentPageDescriptionLabel.setBorder(new EmptyBorder(0,0,0,DEFAULT_NAVIGATION_BAR_HEIGHT/2));
		navigationPanel.add(currentPageDescriptionLabel, BorderLayout.EAST);
		navigationPanel.setName("Navigation Panel");

		this.add(navigationPanel, BorderLayout.NORTH);
		//System.out.println("Size of Navigation Bar:\nW: " + navigationPanel.getWidth() + "\nH: " + navigationPanel.getHeight());
		
		//If the stack is empty, hide the back button
		if(panelsDisplayed.isEmpty()){
			backButton.setVisible(false);
		}
		this.setVisible(true);
	}
	
	public void setInitialView(JPanel firstView){
		this.viewShown = firstView;
		this.add(this.viewShown, BorderLayout.CENTER);
		this.backButton.setVisible(false);
	}
	
	//Now, for the control
	public void displayView(JPanel panelToDisplay){
		if(this.viewShown != null){
			//Add it to the stack
			this.panelsDisplayed.push(this.viewShown);
			
			//Set it to 'not visible'
			this.remove(this.viewShown);
			
			//Show the back button
			this.backButton.setVisible(true);
		}else{
			//If there is not a view currently shown, no need to add it to the stack before hand
			System.out.println("Could not locate a previous view");
		}
		System.out.println("Size of Stack: " + this.panelsDisplayed.size());
		
		//Now that the view is ready, display it
		this.add(panelToDisplay, BorderLayout.EAST);
		panelToDisplay.setVisible(true);
		
		//Set this panel to the current panel
		this.viewShown = panelToDisplay;
		//this.currentPageDescriptionLabel.setText(this.viewShown.getName());
		currentPageDescriptionLabel.setText((this.viewShown.getName() != null) ? this.viewShown.getName() : "No Title");
		
	}
	
	/**
	 * @description Indicates that the back button was selected. now, the most recent view will be popped off the stack and displayed
	 */
	public void dismissView(){
		//System.out.println("Size of Stack: " + this.panelsDisplayed.size());
		
		//Hide the currentview
		this.viewShown.setVisible(false);
		
		//Pop the view from the stack and display it
		JPanel newPanel = this.panelsDisplayed.pop();
		this.add(newPanel);
		newPanel.setVisible(true);
		this.viewShown = newPanel;
		
		currentPageDescriptionLabel.setText((this.viewShown.getName() != null) ? this.viewShown.getName() : "No Title");
		
		//If the stack is empty, hide the back button
		if(this.panelsDisplayed.size() == 0)
			backButton.setVisible(false);
		
	}
	
	/**
	 * @description Goes back to the prior view
	 */
	public class BackSelected implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//System.out.println("Back Selected");
			dismissView();
		}
	}
	
	public void setCurrentPanelName(String newName){
		//Set the panel name
		this.viewShown.setName(newName);
		
		//Display it in the top right
		this.currentPageDescriptionLabel.setText(newName);
	}

}
