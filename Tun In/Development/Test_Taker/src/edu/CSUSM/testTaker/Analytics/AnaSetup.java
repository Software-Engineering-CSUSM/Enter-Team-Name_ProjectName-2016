package edu.CSUSM.testTaker.Analytics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;

public class AnaSetup {
	private static DateFormat dateReg = new SimpleDateFormat("MM_dd");
	protected static Vector<String> typesVector = new Vector<String>(0);   
	protected static Vector<Integer> visitsVector = new Vector<Integer>(0);
	private final static Charset ENCODING = StandardCharsets.UTF_8;
	private static Scanner scanner; 
	
	/**
	 * Log an event - outputs to <user.dir> + File.separator + logFileName
	 * eg. on my computer, Final filepath : C:\Users\Christian\workspace\AnaManager2\report11_25.txt
	 * Action is the name you want recorded in the log. This will be reflected in the graph if we finish it.
	 * @param Action
	 * @throws IOexception
	 */
	//done
	public static void logEvent(String Action) throws IOException{
		if(!typesVector.contains(new String(Action))){
			typesVector.addElement(new String(Action));
			visitsVector.addElement(new Integer(1));
		}else{
			int i = (Integer)typesVector.indexOf(Action);
			int val = ((Integer)visitsVector.elementAt(i)).intValue();
			visitsVector.setElementAt(val + 1, i);
		}
	}
	
	/**
	 * Process the logged actions stored in vectors - call when closing the program
	 * potentially call when generating a report- I'll think about it.
	 * @throws IOException
	 */
	public static final void processLogs() throws IOException{
		Date date = new Date();
		String dateFile = "report" + dateReg.format(date) + ".txt";
		String dateFilePath = System.getProperty("user.dir") + File.separator + dateFile;
		File f = new File(dateFilePath);
		Path dFilePath = Paths.get(dateFilePath);
		
		System.out.println("Path: " + dateFilePath);
		
		if(f.exists()&& !f.isDirectory()){
			processLineByLine(dFilePath);
		}
		  
		  
		 Enumeration<String> tEnum = typesVector.elements();
	     Enumeration<Integer>vEnum = visitsVector.elements();
	      
	     FileWriter fw = new FileWriter(dateFilePath, false);
		 PrintWriter out = new PrintWriter(fw);
		 while(tEnum.hasMoreElements())
			  out.println(tEnum.nextElement() + "-" + vEnum.nextElement());
		 out.close();
	     fw.close();
		  
	     typesVector.clear();
	     visitsVector.clear();
		      
	  }
	//done
	protected static final void processLineByLine(Path pFilePath) throws IOException {
	    try (Scanner scanner =  new Scanner(pFilePath, ENCODING.name())){
	      while (scanner.hasNextLine()){
	        processLine(scanner.nextLine());       
	      } 
	    }
	  }
	
	//done
	protected static void processLine(String aLine){
	    scanner = new Scanner(aLine);
	    scanner.useDelimiter("-");
	    if (scanner.hasNext()){
	    	String type = scanner.next();
	    	Integer visits = Integer.parseInt(String.valueOf(scanner.next()));
	    	
	    	if(!typesVector.contains(new String(type))){
				typesVector.addElement(new String(type));
				visitsVector.addElement(new Integer(visits));
			}else{
				int i = (Integer)typesVector.indexOf(type);
				int val = ((Integer)visitsVector.elementAt(i)).intValue();
				visitsVector.setElementAt(val + visits, i);
			}
	    }
	}
	
	/**
	 * @deprecated
	 */
	public static void clearData() throws IOException{
		/**
		 * Will be used to clear data. Marked as deprecated until filled
		 */
	}
	
}
	
	
	
	
	


