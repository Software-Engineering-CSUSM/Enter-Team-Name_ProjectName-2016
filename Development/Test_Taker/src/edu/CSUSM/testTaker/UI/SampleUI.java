package edu.CSUSM.testTaker.UI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SampleUI extends JFrame {
	
	public static void main(String[] args){
		JFrame sampleFrame = new JFrame("Hello World");
		sampleFrame.setSize(500, 200);
		sampleFrame.setLocationRelativeTo(null);
		//sampleFrame.setLayout(new GridLayout(1,2));
		sampleFrame.setLayout(null);
		sampleFrame.setVisible(true);
		sampleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create a JPanel
		JPanel samplePanel = new JPanel();
		samplePanel.setBounds(5, 5, sampleFrame.size().width - 10, sampleFrame.size().height - 10);
		samplePanel.setBackground(Color.CYAN);
		sampleFrame.add(samplePanel);
		
		//Sample Button
		JButton sampleButton = new JButton("Sample");
		sampleButton.setBounds(15, 25, 30, 30);
		sampleButton.setBackground(Color.RED);
		samplePanel.add(sampleButton);
		
		JButton sampleButton2 = new JButton();
		sampleButton2.setBackground(Color.RED);
		sampleButton2.setText("Hello world2");
		sampleButton2.setBounds(25, 15, 30, 30);
		samplePanel.add(sampleButton2);
		
		JButton sample3 = new JButton("Sample 3");
		sample3.setBounds(125, 125, 40, 30);
		samplePanel.add(sample3);
		
		samplePanel.repaint();
		
	}
	

}
