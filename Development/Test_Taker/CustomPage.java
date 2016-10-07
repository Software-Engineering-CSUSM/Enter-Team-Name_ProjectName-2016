
package edu.CSUSM.testTaker.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.ImageView;

/**
 * 
 * @author Justin
 * @purpose The purpose of this class is to make it easier to create JPanels. Are panel types are quite simple to create.
 * 			For simplicity, there are a few type of panels:
 * 				• TWO_BUTTON_TYPE 		- Has a logo and two buttons (sBs)
 * 				• THREE_BUTTON_TYPE 	- Has a logo and three buttons (2 on top, one centered below)
 * 				• LOGO_ONLY_TYPE		- Has only a logo
 * 				• QUESTION_BUILDER_TYPE	- has a question input, posible answer input and radio buttons to selct correct answer
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

	

	public static enum PanelType{
		TWO_BUTTON_TYPE,
		THREE_BUTTON_TYPE,
		LOGO_ONLY_TYPE,
		QUESTION_BUILDER_TYPE
	};
	
	public CustomPage(PanelType currentPanelType, String imageAddress){
		
		super();
		
		//Create the image from the image address
		try {                
			URL url = new URL(imageAddress);
	          CustomPage.mainLogoToDisplay = ImageIO.read(url);
	       } catch (IOException ex) {
	            // handle exception...
	    	   System.out.println(ex.getMessage());
	       }
		
		buildPanel(currentPanelType);
	}
	

	/**
	 * @param currentPanelType
	 * @description Call if the image was already found
	 */
	public CustomPage(PanelType currentPanelType){
		super();
		buildPanel(currentPanelType);
		
	}
	
	private void buildPanel(PanelType currentPanelType){
		
		//Set the size of the panel
		this.setBounds(SideMenu.WIDTH, 0, GUIController.FRAME_WIDTH - SideMenu.WIDTH, GUIController.FRAME_HEIGHT);
		
		switch(currentPanelType){
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
			break;
			
		default:
			System.out.println("Panel Type Not Found");
			break;
	}
	}
	
	private void createLogoType(){
		
		//Set the layout
		this.setLayout(new BorderLayout());
		
		//Set the background color
		this.setBackground(Color.WHITE);				//Option 1
		//this.setBackground(new Color(39, 72, 155));	//Option 2
		
		//Just display an image in the panel
		imageLabel = new JLabel();
		try{
			
			Image img = CustomPage.mainLogoToDisplay;
			newImg = img.getScaledInstance((this.getWidth() == 0) ? 600 : this.getWidth(), (this.getHeight()/2 == 0) ? 250 : this.getHeight()/2,  java.awt.Image.SCALE_SMOOTH);
			newIcon = new ImageIcon(newImg);
			imageLabel.setIcon(newIcon);
		}
		catch(NullPointerException e){
			System.out.println("No Logo could be found. Please check the project directory");
		}
		catch(Exception e){
			System.out.println("Error Finding image: " + e.getMessage());
		}
		this.add(imageLabel);
	}
	
	
	private void createTwoButtonType(){
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		/** Later
		//Add the image to the top of the screen. We are going to have 3 objects at the top: Back btn, logo, and currentpage title
		 * */
		JLabel iconLabel = new JLabel();
		iconLabel.setBounds(0, 0, this.getWidth(), this.getHeight()/3 * 2);
		iconLabel.setIcon(newIcon);
		//this.add(iconLabel);
		this.add(iconLabel);
		
		//Add the two button
		JButton btn[] = new RoundButton[2];
		int centerOfNewFrame = (this.getHeight() + (this.getHeight() - (iconLabel.getY() + iconLabel.getHeight())))/2;
		
		for(int i = 0; i < btn.length; i++){
			btn[i] = new RoundButton("Button " + (i+1));
			
			int originOfButton = 0;
			if(i==0){
				originOfButton = this.getWidth()/3 - (this.getWidth()/3)/2 - 25;
			}else{
				originOfButton = (this.getWidth()/3)*2 - (this.getWidth()/3)/2 + 25;
			}
			
			btn[i].setBounds(originOfButton, centerOfNewFrame, this.getWidth()/3, 100);
			btn[i].setBorder(new EmptyBorder(50, 50, 50, 50));
			this.add(btn[i]);
		}
		
	}
	
	private void createThreeButtonType(){
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		JLabel iconLabel = new JLabel();
		iconLabel.setBounds(0, 0, 600, 200);
		iconLabel.setIcon(newIcon);
		//this.add(iconLabel);
		this.add(iconLabel);
		
		// offset from the logo panel (it seemed to
		// be to close without it)
		double offset = this.getHeight() * .05; 
		
		//Add the three button
				JButton btn[] = new RoundButton[3];
				
				//Half the centerOfNewFrame from two button center
				int centerOfNewFrame = (this.getHeight() + (this.getHeight() - (iconLabel.getY() + iconLabel.getHeight())))/4;
		
		// Add the offset from the logo panel
				
				centerOfNewFrame += offset;
				for(int i = 0; i < btn.length; i++){
					btn[i] = new RoundButton("Button " + (i+1));
					
					int originOfButton = 0;
					if(i==0){
						originOfButton = this.getWidth()/3 - (this.getWidth()/3)/2 - 25;
					}else if(i == 1){
						originOfButton = (this.getWidth()/3)*2 - (this.getWidth()/3)/2 + 25;
					}
					// added case for 3rd button
					else{
						originOfButton = (this.getWidth()/3);  // Centers the button
						centerOfNewFrame += centerOfNewFrame/2;  // Adds 1/2 the distance between the y coordinate and the rest of the frame
					
					}

					btn[i].setBounds(originOfButton, centerOfNewFrame, this.getWidth()/3, 100);
					btn[i].setBorder(new EmptyBorder(50, 50, 50, 50));
					this.add(btn[i]);
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
