package edu.CSUSM.testTaker.Analytics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


/**
 * 
 * @author John Orcino
 *@description the AnaPieChart will display the pie chart and legend in two separate frames 
 *for a particular date and only display the first five Visit types
 *@toCall call these function with loadData as the initial function:
 *	AnaPieChart.loadData(date); PieChart.displayPie(); Legend.displayText();
 *
*/
public class AnaPieChart {
	 public static Vector<Integer> gotVisits = new Vector<Integer>(0);
	 public static Vector<String>  gotTypes = new Vector<String>(0);
	 private final static Charset ENCODING = StandardCharsets.UTF_8;
	 private static DateFormat dateReg = new SimpleDateFormat("MM_dd");
	 private static Scanner scanner; 
	 
	 public static void loadData() throws IOException{
		 Date date = new Date();
		  String dateFile = "report" + dateReg.format(date) + ".txt";
		  String dateFilePath = System.getProperty("user.dir") + File.separator + dateFile;
		  File f = new File(dateFilePath);
		  Path dFilePath = Paths.get(dateFilePath);
			
			System.out.println("Path: " + dateFilePath);
			
			if(f.exists()&& !f.isDirectory()){
				try (Scanner lscanner =  new Scanner(dFilePath, ENCODING.name())){
				      while (lscanner.hasNextLine()){
				    	scanner = new Scanner(lscanner.nextLine());
				  	    scanner.useDelimiter("-");
				  	    if (scanner.hasNext()){
				  			gotTypes.addElement(scanner.next());
				  			gotVisits.addElement(Integer.parseInt(String.valueOf(scanner.next())));
				  	    }
				      }
				} 
			}  
	  }
	 
	 public AnaPieChart() {

	        JFrame frame = new JFrame("Testing");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLayout(new BorderLayout());
	        frame.getContentPane().add(new PieChart());
	        frame.pack();
	        frame.setSize(500, 300);
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	        
	    }
	   public static void main(String[] argv) {
		   try{
		  AnaPieChart.loadData();
		   }catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 PieChart.displayPie(); 
		 Legend.displayText();
	   }
}

class Slice {
   double value;
   Color color;
   public Slice(int value, Color color) {  
      this.value = (double)value;
      this.color = color;
   }
}


/**
 * 
 * @author John Orcino
 *@description uses the data from the load data function and displays a pie chart
 *in a single frame
 */
class PieChart extends JComponent {

	private static final long serialVersionUID = 1L;
private static Vector<Slice> slices = makeSlices(AnaPieChart.gotVisits);
  
   public  void paint(Graphics g) {
      drawPie((Graphics2D) g, getBounds(), slices);
   }
   
   void drawPie(Graphics2D g, Rectangle area, Vector<Slice> slices) {
      double total = 0.0D;
      for (int i = 0; i < slices.size(); i++) {
         total += slices.get(i).value;
      }
      double curValue = 0.0D;
      int startAngle = 0;
      for (int i = 0; i < slices.size(); i++) {
         startAngle = (int) (curValue * 360 / total);
         int arcAngle = (int) (slices.get(i).value * 360 / total);
         g.setColor(slices.get(i).color);
         g.fillArc(area.x, area.y, area.width, area.height, 
         startAngle, arcAngle);
         curValue += slices.get(i).value;
      }
   }
   //reworked to use a vector of slices
   public static Vector<Slice> makeSlices(Vector<Integer> visits){
	 Color[] Colors = {Color.BLACK,Color.GREEN,Color.YELLOW,Color.RED,Color.BLUE};
	 Vector<Slice> pieVector = new Vector<Slice>(0);
	 
	 for(int i = 0; i < visits.size() && i < Colors.length; i++){
		 pieVector.add(new Slice(visits.get(i), Colors[i]));
	 }
	 
	 return pieVector;
	
   }
   public static String getColor(int index){
	   String color = null;
	   switch(index){
	   case 0: color = "Black";
	   		break;
	   case 1: color = "Green";
	   break;
	   case 2: color = "Yellow";
	   break;
	   case 3: color = "Red";
	   break;
	   case 4: color = "Blue";
	   break;
	   }
	return color;
   }
   
   public static void displayPie(){
	   JFrame frame = new JFrame();
	   frame.getContentPane().add(new PieChart());
	   frame.setSize(600, 300);
	   frame.setVisible(true);
	      
   }
}

/**
 * 
 * @author John Orcino
 *@description uses the information from the load data and creates a legends with 
 *the visit type and counter with their corresponding color.
 */
class Legend extends JPanel {

	private static final long serialVersionUID = 1L;

private void drawString(Graphics g, String text, int x, int y) {
    for (String line : text.split("-"))
        g.drawString(line, x, y += g.getFontMetrics().getHeight());
}

public  void paintComponent(Graphics g) {
    super.paintComponent(g);
    int x = 20;
    int y = 20;
    for(int i = 0; i < AnaPieChart.gotTypes.size(); i++ ){
    drawString(g,PieChart.getColor(i)+":"+ //Calling for the Color
    		AnaPieChart.gotTypes.get(i)+":"+//Calling for the Type
    		Integer.toString(AnaPieChart.gotVisits.get(i))+"-", x, y);//calling for the counter
    y =  y+ 2*g.getFontMetrics().getHeight(); //increment the spacing for the next line
    }
}
public static void displayText(){
	 JFrame f = new JFrame();
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.add(new Legend());
	    f.setSize(220, 220);
	    f.setVisible(true);

	}
}