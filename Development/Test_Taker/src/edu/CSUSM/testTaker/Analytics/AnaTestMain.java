package edu.CSUSM.testTaker.Analytics;

//import AnaViewer.java;

public class AnaTestMain{
	public static void Main(String [] args){
		AnaSetup.logEvent("Main Menu");
		AnaSetup.logEvent("Test");
		AnaSetup.logEvent("Question Create");
		AnaViewer.processLogs();
		return;
	}
}