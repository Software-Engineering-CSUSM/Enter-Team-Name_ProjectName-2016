package edu.CSUSM.testTaker.Analytics;

import java.io.IOException;

//import AnaViewer.java;

public class AnaTestMain{
	public static void Main(String [] args){
		try{
		AnaSetup.logEvent("Main Menu");
		AnaSetup.logEvent("Test");
		AnaSetup.logEvent("Question Create");
		AnaViewer.processLogs();
		}catch(IOException EX){
			EX.printStackTrace();
		}
	}
}