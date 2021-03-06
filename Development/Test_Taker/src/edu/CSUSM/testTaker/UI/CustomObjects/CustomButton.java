package edu.CSUSM.testTaker.UI.CustomObjects;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class CustomButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int radius = 25;

	public CustomButton(String text) {
		super(text);
	}

	public CustomButton() {
		super();
	}

	public void paint(Graphics g) {
		// Set background same as parent.
		setBackground(getParent().getBackground());
        setBorder(new EmptyBorder(30,0,30,0));

		// I don't need this -- calls to above methods will 
		// invoke repaint as needed.
		//		        super.paint(g);

		// Take advantage of Graphics2D to position string
		Graphics2D g2d = (Graphics2D)g;

		// Make it grey #DDDDDD, and make it round with 
		// 1px black border.
		// Use an HTML color guide to find a desired color.
		// Last color is alpha, with max 0xFF to make 
		// completely opaque.
		g2d.setColor(Color.WHITE);

		// Draw rectangle with rounded corners on top of 
		// button
		g2d.fillRoundRect(0,0,getWidth(),getHeight(),radius,radius);

		// I'm just drawing a border
		g2d.setColor(Color.BLACK);
		//g2d.drawRoundRect(0,0,getWidth()-1, getHeight()-1,radius,radius);

		// Finding size of text so can position in center.
		FontRenderContext frc = 
				new FontRenderContext(null, false, false);
		Rectangle2D r = getFont().getStringBounds(getText(), frc);

		float xMargin = (float)(getWidth()-r.getWidth())/2;
		float yMargin = (float)(getHeight()-getFont().getSize())/2;

		// Draw the text in the center
		g2d.drawString(getText(), xMargin, 
				(float)getFont().getSize() + yMargin);
		
		int shade = 0;
        int topOpacity = 80;
        for (int i = 0; i < radius/2; i++) {
            g.setColor(new Color(shade, shade, shade, ((topOpacity / radius) * i)));
            g.drawRect(i, i, this.getWidth() - ((i * 2) + 1), this.getHeight() - ((i * 2) + 1));
        }
	}
}
