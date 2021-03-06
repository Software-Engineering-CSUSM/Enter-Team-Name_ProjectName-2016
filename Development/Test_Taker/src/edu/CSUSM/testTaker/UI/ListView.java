package edu.CSUSM.testTaker.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.CSUSM.testTaker.Backend.Course;
import edu.CSUSM.testTaker.Backend.Question;
import edu.CSUSM.testTaker.Backend.Test;


public class ListView <K> extends CustomPage {
	private static final long serialVersionUID = 1L;
	
	ArrayList<K> contentList;
	ArrayList<String> identifiers;
	
	enum classType {
		Question, Course, Test
	};

	classType thisType;
	
	public ListView(ArrayList<K> content, String[] btnNames){
		super();
		this.contentList = content;
		build();
		updateActions(btnNames);
	}

	public ListView(String[] btnNames) {
		super();
		
		build();
		// TODO Auto-generated constructor stub
		updateActions(btnNames);
		
	}
	
	private void updateActions(String[] btnNames){
		
		//Note that this will have just 2 buttons
		if(btnNames.length == 1){
			setButtonNames(btnNames);
		}else{
			//This will only show if the incorrect bttn count is provided
			setButtonNames(new String[] {"Button 1", "Button 2"});
		}
		
	}
	
	protected void build(){
		
		//Add main panel to the north

		addButtons(1);	//Only 1 btn this time

		//Build the table
		this.add(basicTable(), BorderLayout.NORTH);
	}
	
	@SuppressWarnings("unchecked")
	private JPanel basicTable(){
		
		JPanel tableView = new JPanel();
		JTable table;
		
		//Create a list based on the object type
		if(this.contentList.getClass().equals(Course.class)){
			System.out.println("Course Found");
			thisType = classType.Course;
		}else if(this.contentList.getClass().equals(Test.class)){
			System.out.println("Test Found");
			thisType = classType.Test;
		}else if(this.contentList.getClass().equals(Question.class)){
			System.out.println("Question Found");
			thisType = classType.Question;
		}else{
			thisType = classType.Course;
		}
		
		if(this.contentList.size() == 0){
			JLabel newLabel = new JLabel("No Content Found");
			newLabel.setHorizontalAlignment(JLabel.CENTER);
			newLabel.setVerticalAlignment(JLabel.CENTER);
			tableView.add(newLabel);
			
		}else{
			System.out.println("Content Found: " + this.contentList.toString());
			
			String[] columnNames = {"Name", "Selected"};
			
			//Create teh shown object RowName, False
			ArrayList<DataToDisplay> finalList = new ArrayList<DataToDisplay>();
			
			switch(thisType){
			case Test:
				
				break;
			case Course:
				for(K aTest : this.contentList){
					Course thisTest = (Course)aTest;
					K rowName = (K) thisTest.getName();
					finalList.add(new DataToDisplay(rowName, false));
				}
				break;
			case Question:
				break;
			default:
				break;
			}
			
			
			//Add the main table
			DefaultTableModel model = new DefaultTableModel(); 
			table = new JTable(model);
			
			model.addColumn("Name");
			model.addColumn("Selected");
			
			//Add all of the rows from the list
			for(K type : this.contentList){
				model.addRow(new Object[]{type.toString(), new Boolean(false)});
			}
			
			System.out.println("Table Rows Count: " + table.getRowCount());
			
			//table.setPreferredScrollableViewportSize(new Dimension(500, 70));
			table.setFillsViewportHeight(true);
			
			//Create the scroll pane and add the table to it.
	        JScrollPane scrollPane = new JScrollPane(table);

	        //Add the scroll pane to this panel.
	        tableView.add(scrollPane);
		}
		
		
		//tableView.setBackground(Color.RED);
		
		return tableView;
	}
	
	private class DataToDisplay{
		private K row; 
		private boolean value;
		
		@SuppressWarnings("unused")
		public DataToDisplay(K rowName, Boolean value){
			this.setRow(rowName);
			this.setValue(value);
		}

		/**
		 * @return the row
		 */
		@SuppressWarnings("unused")
		public K getRow() {
			return row;
		}

		/**
		 * @param row the row to set
		 */
		public void setRow(K row) {
			this.row = row;
		}

		/**
		 * @return the value
		 */
		@SuppressWarnings("unused")
		public boolean isValue() {
			return value;
		}

		/**
		 * @param value the value to set
		 */
		public void setValue(boolean value) {
			this.value = value;
		}
	}

}

