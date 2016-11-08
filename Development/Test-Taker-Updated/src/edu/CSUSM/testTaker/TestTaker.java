package edu.CSUSM.testTaker;
import javax.swing.*;

import edu.CSUSM.testTaker.Backend.Course;
import edu.CSUSM.testTaker.Backend.Question;
import edu.CSUSM.testTaker.Backend.Test;
import edu.CSUSM.testTaker.PrimarySourceFiles.Welcome;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


public class TestTaker extends JFrame 
{
	//Init Vars
	//public static final int _WIDTH = 600, _HEIGHT = 450;
	public static final int _WIDTH = 700, _HEIGHT = 500;
	
	//Init Specal classes for JPanels
	public Welcome wl;

	public static void main(String[] args) 
	{
		new TestTaker();
	}

	public TestTaker()
	{
		super("Test Taker");
		setSize(_WIDTH, _HEIGHT);
		setLayout(null);
		setResizable(false);
		buildComp();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//Restore Library if it exists
		File testfile = new File("Library.bin");
		if(testfile.exists()){
			LibraryController.restoreLibrary("Library.bin");
		}else{
			//Load content into library only once
			Question sample = 		Question.makeExample();
			Test sampleTest = 		Test.makeExample();
			Course sampleCourse = 	Course.makeExample();

			System.out.println(sample);
			System.out.println(sampleTest);
			System.out.println(sampleCourse);

		}

		//Add shutdown hook to store the library at exit
		java.lang.Runtime.getRuntime().addShutdownHook(ShutdownManager.getInstance());
	}

	public void buildComp()
	{
		//Add Main Screen using class (This will just be on top of everything)
		wl = new Welcome(_WIDTH, _HEIGHT, "Review Classes");
		add(wl);
	}








}