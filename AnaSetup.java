import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AnaSetup {
	private static DateFormat dateReg = new SimpleDateFormat("dd-MM-yyyy|HH:mm:ss");
		
	public static void sendEvent(String Action){
		String st = assembleSt("Event");
		
		System.out.println(st + Action + " occured.");
	}
	
	public static void sendPage(String pageName){
		String st = assembleSt("Page ");
		
		System.out.println(st + pageName + " was accessed.");
	}
	
	//Ex. 07-08-1995|12:12:30|Event:
	public static String assembleSt(String category){
		Date date = new Date();
		
		String msg = dateReg.format(date) + "|" + category + ": "; 
		return msg;
	}
}


