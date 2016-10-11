package edu.CSUSM.testTaker.UI.Pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import edu.CSUSM.testTaker.UI.CustomPage;

public class QuizMain extends CustomPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuizMain(PanelType currentPanelType) {
		super(currentPanelType);
		// TODO Auto-generated constructor stub
		System.out.println("Printing a new Form");
		updateActions();
	}

	public QuizMain(PanelType currentPanelType, BufferedImage newImage) {
		super(currentPanelType, newImage);
		// TODO Auto-generated constructor stub
		updateActions();
	}

	public QuizMain(PanelType currentPanelType, String imageAddress) {
		super(currentPanelType, imageAddress);
		// TODO Auto-generated constructor stub
		updateActions();
	}
	
	
	public void updateActions(){
		this.currentActions[0].setText("Changing the text");
		this.setName("Hello World");
		
		this.currentActions[0].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("New ACtion Lsitener");
			}
		});
	}
	
	
	

}
