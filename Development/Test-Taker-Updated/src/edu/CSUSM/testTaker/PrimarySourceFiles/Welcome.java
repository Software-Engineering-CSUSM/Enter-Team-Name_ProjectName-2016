package edu.CSUSM.testTaker.PrimarySourceFiles;
import javax.imageio.ImageIO;
import javax.swing.*;

import edu.CSUSM.testTaker.UI.CustomObjects.CustomButton;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.*;



public class Welcome extends JPanel
{
	public JPanel main = new JPanel();
	public ImageIcon mainImage = new ImageIcon("_Logo.png");

	//Initi vars
	public static int _WIDTH, _HEIGHT;

	public Welcome(int width, int height, String btnText)
	{
		_WIDTH = width;
		_HEIGHT = height;
		BufferedImage tempImg = null;

		setBackground(Color.WHITE);
		setLayout(null);
		setSize(width, height);

		//Set the background image to the new image found on github
		try{
			URL url = new URL(
					"https://github.com/Software-Engineering-CSUSM/Test-Taker/blob/master/Team%20Graphics/Test_Taker_LogoOption3.png?raw=true");
			tempImg = ImageIO.read(url);
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}

		Image newImg = tempImg.getScaledInstance(_WIDTH,(int)(_HEIGHT/1.5), java.awt.Image.SCALE_SMOOTH);
		mainImage = new ImageIcon(newImg);

		//Add Label
		JLabel logoLabel = new JLabel();
		logoLabel.setBounds(0, -50, _WIDTH, _HEIGHT);
		logoLabel.setIcon(mainImage);

		//Add Image to Label
		add(logoLabel);

		CustomButton btn = new CustomButton("Review Classes");
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				//Hide the objects in this panel
				btn.setVisible(false);
				logoLabel.setVisible(false);

				//Add the function screen
				ClassOverview clas = new ClassOverview(width, height);
				add(clas);

				//setBackground(new Color(85, 85, 85));
			}
		});

		float center = _WIDTH/2, btnWidth = _WIDTH/3, btnHeight = _HEIGHT/8;

		btn.setBounds((int)center - ((int)btnWidth/2), _HEIGHT - 100, (int)btnWidth, (int)btnHeight);
		add(btn);

		setVisible(true);
	}

	public boolean checkForVisible()
	{
		if (this.isVisible()) {
			return true;
		}
		else {
			return false;
		}
	}



}