package com.jtizz.NavigationController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.CSUSM.testTaker.UI.*;

/**
 * @author Justin
 * @description Runs an example Application with the NavigationController Implementation.
 *
 */
public class ExampleDriver extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new ExampleDriver();

	}
	
	public ExampleDriver(){
		this.setSize(750, 500);
		this.setTitle("Testing Application");
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Add the navigation Controller
		NavigationController nc = new NavigationController();
		this.add(nc, BorderLayout.CENTER);
		nc.setBackground(Color.WHITE);
		
		this.setVisible(true);
		
		SideMenu sm = new SideMenu(new String[]{"Home", "Courses", "Study Tools", "Statistics"});
		this.add(sm, BorderLayout.WEST);
		
		CustomPage newPage = new CustomPage(CustomPage.PanelType.LOGO_ONLY_TYPE, "https://github.com/Software-Engineering-CSUSM/Test-Taker/blob/master/Team%20Graphics/Test_Taker_LogoOption3.png?raw=true");
		newPage.setName(SideMenu.menuOptionButtons[0].getText());
		nc.displayView(newPage);
		
		CustomPage testingTwo = new CustomPage(CustomPage.PanelType.TWO_BUTTON_TYPE);
		testingTwo.setName(SideMenu.menuOptionButtons[1].getText());
		nc.setInitialView(testingTwo);
		testingTwo.currentActions[1].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				CustomPage thirdPage = new CustomPage(CustomPage.PanelType.THREE_BUTTON_TYPE);
				thirdPage.setName("Testing a 3 Btn");
				nc.displayView(thirdPage);
				
				//Add an action listener to the button
				thirdPage.currentActions[1].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						Random newRand = new Random();
						int newRandNum = newRand.nextInt(2);
						CustomPage endlessPage = new CustomPage((newRandNum == 1) ? CustomPage.PanelType.THREE_BUTTON_TYPE : CustomPage.PanelType.TWO_BUTTON_TYPE);
						endlessPage.setName(endlessPage.panelTypeString);
						nc.displayView(endlessPage);
					}
				});
			}
		});
		
		CustomPage testingThree = new CustomPage(CustomPage.PanelType.THREE_BUTTON_TYPE);
		testingThree.setName(SideMenu.menuOptionButtons[2].getText());
		nc.displayView(testingThree);
		nc.reset();
		

		PageManager<CustomPage> pm = new PageManager<CustomPage>((JPanel)nc, SideMenu.menuOptionButtons, new CustomPage[]{newPage, testingTwo, testingThree}, 0);
		pm.hideAllPanelsButAtIndex(0);
		
		//Now, when a user selects a side menu option, we need to remove all items from the stack;
		for(JButton sideBtn : SideMenu.menuOptionButtons){
			sideBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//Reset the views in teh current view
					nc.reset();
					nc.currentPageDescriptionLabel.setText(sideBtn.getText());
					System.out.println("Reseting Stack: " + nc.panelsDisplayed.size());
				}
			});
		}
		
		/**
		//Count all the views currently shown
		System.out.println("View Shown in container: " + nc.getComponentCount());
		for(Component a : nc.getComponents()){
			System.out.println("Component Name: " + a.getName());
		}*/
	}

}