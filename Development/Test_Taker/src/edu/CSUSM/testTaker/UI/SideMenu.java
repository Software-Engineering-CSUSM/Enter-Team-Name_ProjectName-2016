package edu.CSUSM.testTaker.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * @author Justin
 * @purpose Creates and maintains a side menu for the main Frame
 *
 *	Note that this may be considered a Singleton
 */
public class SideMenu extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int _numberOfButtons;
	private static final int MAX_NUMBER_OF_BUTTONS = 10;
	public static JButton[] menuOptionButtons;
	public static Font basicFont = new Font(Font.SERIF, Font.BOLD, 16);
	public static final int WIDTH = 200, HEIGHT = GUIController.FRAME_HEIGHT;
	
	/**
	 * @param buttonNames
	 */
	public SideMenu (String[] buttonNames){
		super();
		
		//Set the length of the array as max amount of buttons
		_numberOfButtons = buttonNames.length;
		
		//Set the grid layout
		this.setLayout(new GridLayout(MAX_NUMBER_OF_BUTTONS, 1));
		
		//Initialize the button array
		menuOptionButtons = new JButton[_numberOfButtons];
		
		//Add the buttons
		for(int iterator = 0; iterator < _numberOfButtons; iterator++){
			menuOptionButtons[iterator] = newButton(buttonNames[iterator]);
			this.add(menuOptionButtons[iterator]);
		}
		
		//Show the panel
		this.setVisible(true);
		
		//Set the appearance
		this.setBackground(new Color(85, 85, 85));
		
		//Set the first button as disabled, becuse this is the one that is going to be shown
		setStatus(false, menuOptionButtons[0]);
		
		this.setBounds(0, 0, SideMenu.WIDTH, SideMenu.HEIGHT);
	}
	
	/**
	 * @param flag
	 * @description sets disabled and enabeled states
	 */
	public static void setStatus(boolean flag, JButton buttonClicked){
		
		//First, reset all buttons
		resetButtons();
		
		if(flag){							//If the button is becoming enabled
			
		}else{								//If the button is becoming disabled
			buttonClicked.setEnabled(false);
			buttonClicked.setFont(basicFont);
			buttonClicked.setBackground(Color.LIGHT_GRAY);
			buttonClicked.setOpaque(true);
		}
	}
	
	private static void resetButtons(){
		for(int i = 0; i < _numberOfButtons; i++){
			menuOptionButtons[i].setEnabled(true);;
			menuOptionButtons[i].setFont(basicFont);
			menuOptionButtons[i].setBackground(null);
			menuOptionButtons[i].setOpaque(false);
		}
	}
	
	private JButton newButton(String title){

		//Make custom button (Should be a seperate class)
		UIManager.put("Button.disabledText", Color.red);	//Sets the color of the disbaled text
		JButton temp = new JButton(title);
		
		temp.setBorder(new EmptyBorder(50, 50, 50, 50));
		//temp.setOpaque(true);
		temp.setForeground(Color.WHITE);
		temp.setFont(basicFont);
		return temp;
	}
	
}
