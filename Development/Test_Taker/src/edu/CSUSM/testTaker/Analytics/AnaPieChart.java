package edu.CSUSM.testTaker.Analytics;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorChooserUI;

//import com.my.piechart.MyComponent;
//import com.my.piechart.Slice;

/**
 * 
 * @author John Orcino
 *Just call 
 */
public class AnaPieChart {
	   public static void main(String[] argv) {
		 MyComponent.displayPie();  
	      /*JFrame frame = new JFrame();
	      frame.getContentPane().add(new MyComponent());
	      frame.setSize(300, 300);
	      frame.setVisible(true);*/
	   }
}
class Slice {
   double value;
   Color color;
   public Slice(double value, Color color) {  
      this.value = value;
      this.color = color;
   }
}
class MyComponent extends JComponent {
  
  
	/*
	Slice[] slices = { 
	new Slice(5, Color.black), 
   new Slice(1, Color.green),
   new Slice(3, Color.yellow), 
   new Slice(2, Color.red) };*/
	static Slice[] slices = getColor(AnaSetup.visitsVector);
	MyComponent() {}
   public  void paint(Graphics g) {
      drawPie((Graphics2D) g, getBounds(), slices);
   }
   void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
      double total = 0.0D;
      for (int i = 0; i < slices.length; i++) {
         total += slices[i].value;
      }
      double curValue = 0.0D;
      int startAngle = 0;
      for (int i = 0; i < slices.length; i++) {
         startAngle = (int) (curValue * 360 / total);
         int arcAngle = (int) (slices[i].value * 360 / total);
         g.setColor(slices[i].color);
         g.fillArc(area.x, area.y, area.width, area.height, 
         startAngle, arcAngle);
         curValue += slices[i].value;
      }
   }
   public static Slice[] getColor(Vector<Integer> color){
	  if(color.size()==1){
		  Slice[] slices = { 
				  new Slice(color.get(0), Color.black), 
				  new Slice(0, Color.green),
				  new Slice(0, Color.yellow), 
				  new Slice(0, Color.red),
				  new Slice(0, Color.blue) };
		  return slices;
		 	}
	  else if(color.size()==2){
		  Slice[] slices = { 
				  new Slice(color.get(0), Color.black), 
				  new Slice(color.get(1), Color.green),
				  new Slice(0, Color.yellow), 
				  new Slice(0, Color.red),
				  new Slice(0, Color.blue) };
		  	return slices;
	  }
	  else if(color.size()==3){
		  Slice[] slices = { 
				  new Slice(color.get(0), Color.black), 
				  new Slice(color.get(1), Color.green),
				  new Slice(color.get(2), Color.yellow), 
				  new Slice(0, Color.red),
				  new Slice(0, Color.blue) };
		  	return slices;
	  }
	  else if(color.size()==4){
		  Slice[] slices = { 
				  new Slice(color.get(0), Color.black), 
				  new Slice(color.get(1), Color.green),
				  new Slice(color.get(2), Color.yellow), 
				  new Slice(color.get(3), Color.red),
				  new Slice(0, Color.blue) };
		  	return slices;
	  }
	  else{
		   Slice[] slices = { 
					new Slice(color.get(0), Color.black), 
				   new Slice(color.get(1), Color.green),
				   new Slice(color.get(2), Color.yellow), 
				   new Slice(color.get(3), Color.red),
				   new Slice(color.get(4), Color.blue) };
		   return slices;
		 	}
   }
   public static void createLegend(JFrame frame){
	   Vector<String> types = AnaSetup.typesVector;
	   Vector<Integer> visits= AnaSetup.visitsVector;
	   for(int i = 0; i <5; i++ ){
		   JLabel text = new JLabel(types.get(i)+ "-" + Integer.toString(visits.get(i)),JLabel.LEFT);
		   text.setForeground(slices[i].color);
		   frame.add(text);
	   }
   }
   public static void displayPie(){
	   JFrame frame = new JFrame();
	   Container content = frame.getContentPane();
	      content.setLayout(new GridLayout(1, 0));
	      Border border = LineBorder.createGrayLineBorder();
	      frame.getContentPane().add(new MyComponent());
	      frame.setSize(300, 300);
	      frame.setVisible(true);
	      MyComponent.createLegend(frame);
	      
   }
}