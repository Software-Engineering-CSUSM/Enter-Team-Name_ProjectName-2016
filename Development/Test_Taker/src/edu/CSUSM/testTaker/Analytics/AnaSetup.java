package edu.CSUSM.testTaker.Analytics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AnaSetup {
	private static DateFormat dateReg = new SimpleDateFormat("MM_dd");
	
	
	//Log an event - outputs to "C:\\AnaManager\\Analytics.txt"
	//Action is the name you want recorded in the log. This will be reflected in the graph if john finishes it.
	public static void logEvent(String Action) throws IOException{
		Date date = new Date();
		
		String msg = dateReg.format(date) + "-" + Action;
		System.out.println(msg);
		
		FileWriter fw = new FileWriter("C:\\AnaManager\\Analytics.txt", true);
		PrintWriter out = new PrintWriter(fw);
		out.println(msg);
		out.close();
		fw.close();
	}
	
	
	//clear the log file- useful for anaViewer or emergency data clearing.
	public static void clearData() throws IOException{
		FileWriter fileWriter = new FileWriter("C:\\AnaManager\\Analytics.txt", false);
		fileWriter.write("");
		fileWriter.close();
		
		System.out.println("Data Cleared");
	}
}


