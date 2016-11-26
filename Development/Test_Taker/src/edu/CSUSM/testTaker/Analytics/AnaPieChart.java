package edu.CSUSM.testTaker.Analytics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.plaf.ColorChooserUI;

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
	Slice[] slices = getColor(AnaSetup.visitsVector);
   MyComponent() {}
   public void paint(Graphics g) {
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
   public Slice[] getColor(Vector<Integer> color){
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
}
public class AnaPieChart {
   public static void main(String[] argv) {
	   
      JFrame frame = new JFrame();
      frame.getContentPane().add(new MyComponent());
      frame.setSize(300, 200);
      frame.setVisible(true);
   }
}