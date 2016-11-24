package edu.CSUSM.testTaker;
import edu.CSUSM.testTaker.Analytics.AnaViewer;

public class ShutdownManager extends Thread {
	static ShutdownManager instance = null;
	
	ShutdownManager(){
		
	}
	
	public static ShutdownManager getInstance(){
		if(instance == null){
			instance = new ShutdownManager();
		}
		
		return instance;
	}
	
	public void run(){
		LibraryController.backupLibrary("Library.bin");
		AnaViewer.processLogs();
	}

}
