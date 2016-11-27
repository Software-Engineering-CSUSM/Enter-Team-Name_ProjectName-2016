import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AnaViewer {
	
	  //constructor- only requires the file name of the logs file from AnaSetup. Could hard code?
	  public AnaViewer(String aFileName){
	    fFilePath = Paths.get(aFileName);
	  }
	  
	  //Processes the logs file. This should be called on program exit and when generating a new graph.
	  public static final void processLogs() throws IOException{
		  String fileName = null;
		  try (Scanner scanner = new Scanner(fFilePath, ENCODING.name())){
			  String Date = processLine(scanner.nextLine(), 0);
			  fileName = ("C:\\AnaManager\\report" + Date + ".txt");
		  }
		  
		  System.out.println(fileName);
		  File f = new File(fileName);
		  typesVector.clear();
		  visitsVector.clear();
		  if(f.exists()&& !f.isDirectory()){
			  System.out.println(fileName + " already exists, setting vectors..");
			  dFilePath = Paths.get(fileName);
			  try(Scanner scanner = new Scanner(dFilePath, ENCODING.name())){
				  processLineByLine(dFilePath, 1);
			  }
		  }
		  processLineByLine(fFilePath, 0);
		  
		  Enumeration<String> tEnum = typesVector.elements();
	      Enumeration<Integer>vEnum = visitsVector.elements();
	      
	      FileWriter fw = new FileWriter(fileName, false);
		  PrintWriter out = new PrintWriter(fw);
		  while(tEnum.hasMoreElements())
			  out.println(tEnum.nextElement() + "-" + vEnum.nextElement());
		  out.close();
	      fw.close();
		  
	      AnaSetup.clearData();
		      
	  }
	  
	  
	  //processes the file denoted by pFilePath line-by-line, passing the required mode to the processLine function
	  //This should be set as private but I have it as protected just in case I want to expand on this in a child function.
	  protected static final void processLineByLine(Path pFilePath, int mode) throws IOException {
	    try (Scanner scanner =  new Scanner(pFilePath, ENCODING.name())){
	      while (scanner.hasNextLine()){
	        processLine(scanner.nextLine(), mode);       
	      } 
	    }
	  }
	      
	  //this is what linebyline calls! same privacy deal as linebyline, too.
	  protected static String processLine(String aLine, int mode){
	    //use a second Scanner to parse the content of each line
		System.out.println("Scanning...");  
	    scanner = new Scanner(aLine);
	    scanner.useDelimiter("-");
	    if (scanner.hasNext()){
	    	if(mode == 0){
	    		//assumes the line has a certain structure
	    		String date = scanner.next();
	    		String type = scanner.next();
	      
	    		System.out.println("Date is: " + quote(date.trim()) + 
	    					", Type is: " + quote(type.trim())	);
	    		//If a new type of page is detected, adds the element to the vector and initializes its visits value to 1
	    		//If the page already exists, increments the visits value.
	    		if(!typesVector.contains(new String(type))){
	    			typesVector.addElement(new String(type));
	    			visitsVector.addElement(new Integer(1));
	    		}else{
	    			int i = (Integer)typesVector.indexOf(type);
	    			int val = ((Integer)visitsVector.elementAt(i)).intValue();
	    			visitsVector.setElementAt(val + 1, i);
	    		}
	    		return date;
	    	}else if(mode == 1){
	    		String type = scanner.next();
	    		Integer visits = Integer.parseInt(String.valueOf(scanner.next()));
	    		System.out.println("Discovered type " + quote(type.trim()) + " with " + visits + " visits.");
	    		
	    		typesVector.addElement(type);
	    		visitsVector.addElement(visits);
	    		
	    		return null;
	    	}
	    }
	    else {
	      System.out.println("Empty or invalid line. Unable to process.\n");
	      return "0";
	    }
		return null;
	  }
	  
	  // PRIVATE STUFF
	  private static Path fFilePath;   //path to the logs file
	  private static Path dFilePath;   //path to a dated file
	  private final static Charset ENCODING = StandardCharsets.UTF_8; //needed for scanner
	  private static Scanner scanner;  //initialization of scanner
	  private static Vector<String> typesVector = new Vector<String>(0);   
	  private static Vector<Integer> visitsVector = new Vector<Integer>(0);
	  
	
	  private static String quote(String aText){
	    String QUOTE = "'";
	    return QUOTE + aText + QUOTE;
	  }
	} 