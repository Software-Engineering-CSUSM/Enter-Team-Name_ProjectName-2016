package edu.CSUSM.testTaker.Analytics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AnaSetup {
	private static DateFormat dateReg = new SimpleDateFormat("MM_dd");
	private static String logFileName = "Analytics.txt";
	
	/**
	 * Log an event - outputs to <user.home> + File.separator + logFileName
	 * eg. on my computer, Final filepath : C:\Users\Christian\Analytics.txt
	 * Action is the name you want recorded in the log. This will be reflected in the graph if we finish it.
	 * @param Action
	 * @throws IOexception
	 */
	//Log an event - outputs to "C:\\AnaManager\\Analytics.txt"
	//Action is the name you want recorded in the log. This will be reflected in the graph if john finishes it.
	public static void logEvent(String Action) throws IOException{
		Date date = new Date();
		File dirFile = null;
		String msg = dateReg.format(date) + "-" + Action;
		System.out.println(msg);
		try{
			String workingDirectory = System.getProperty("user.home");
			String absoluteFilePath = workingDirectory + File.separator + logFileName;
			
			System.out.println("Final filepath : " + absoluteFilePath);
			
			dirFile = new File(absoluteFilePath);
			
			if (dirFile.createNewFile()) {
				System.out.println("File is created!");
			} else {
				System.out.println("File is already existed!");
			}

		  } catch (IOException e) {
			e.printStackTrace();
		  }
		if(dirFile != null){	
			FileWriter fw = new FileWriter(dirFile, true);
			PrintWriter out = new PrintWriter(fw);
			out.println(msg);
			out.close();
			fw.close();
		}else{
			System.out.println("Error: directory file is null. No File opened.");
		}
	}
	
	
	/**
	 * Clear the log file - useful for anaVidewer or emergency data clearing
	 * @throws IOException
	 */
	public static void clearData() throws IOException{
		File dirFile = null;
		try{
			//options: user.home or user.dir - depending on how I want to use it.
			String workingDirectory = System.getProperty("user.home");
			String absoluteFilePath = workingDirectory + File.separator + logFileName;
			//System.out.println("Final filepath : " + absoluteFilePath);
			
			dirFile = new File(absoluteFilePath);
			
			if (dirFile.createNewFile()) {
				System.out.println("File is created!");
			} else {
				System.out.println("File is already existed!");
			}

		  } catch (IOException e) {
			e.printStackTrace();
		  }
		
		
		if(dirFile != null){
		FileWriter fileWriter = new FileWriter(dirFile, false);
		fileWriter.write("");
		fileWriter.close();

		System.out.println("Data Cleared");
		}else{
			System.out.println("Unable to open log file");
		}
	}
}


