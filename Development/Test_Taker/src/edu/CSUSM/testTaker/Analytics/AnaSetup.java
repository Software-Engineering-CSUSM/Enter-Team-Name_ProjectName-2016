<<<<<<< HEAD
=======
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AnaSetup {
	private static DateFormat dateReg = new SimpleDateFormat("dd-MM-yyyy|HH:mm:ss");
	
	//Presently unused
	public static void bootUp() throws IOException{
		
	}
	
	//Send an event - unexpected jumps, errors, etc.
	public static void sendEvent(String Action) throws IOException{
		String st = assembleSt("Event");
		System.out.println(st + Action + " occured.");
		
		//write to local.txt
		FileWriter fw = new FileWriter("C:\\AnaManager\\Analytics.txt", true);
		PrintWriter out = new PrintWriter(fw);
		out.println(st + Action + " was accessed");
		out.close();
	}
	
	//Send a page access - main menu, test page, etc.
	public static void sendPage(String pageName) throws IOException{
		String st = assembleSt("Page ");
		System.out.println(st + pageName + " was accessed.");
		
		//write to local.txt
		FileWriter fw = new FileWriter("C:\\AnaManager\\Analytics.txt", true);
		PrintWriter out = new PrintWriter(fw);
		out.println(st + pageName + " was accessed");
		out.close();
			
	}
	
	//Ex. 07-08-1995|12:12:30|Event:
	public static String assembleSt(String category){
		Date date = new Date();
		
		String msg = dateReg.format(date) + "|" + category + ": "; 
		return msg;
	}
	
	//clear recorded data - may move to different class?
	public static void clearData() throws IOException{
		FileWriter fileWriter = new FileWriter("C:\\AnaManager\\Analytics.txt", false);
		fileWriter.write(" ");
		fileWriter.close();
		
		System.out.println("Data Cleared");
	}
}

>>>>>>> Tizzle
