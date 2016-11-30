package edu.CSUSM.testTaker.UI.Pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import edu.CSUSM.testTaker.LibraryController;
import edu.CSUSM.testTaker.LibraryController.CourseInfo;

/**
 * 
 * @author Justin
 * @description The purpose of this class is to create a data class that has a
 *              few items:
 * 
 *              • Row 1 Data - This is mostly going to contain the questions,
 *              tests, and courses. This field is likely going to display the
 *              names of those fields
 * 
 *              • Edit Button - This will allow the user to edit the content
 *              that is currently displayed.
 * 
 *              • Remove Button - This will allow the user to remove the element
 *              displayed. After the remove, the panel will repaint itself and
 *              adjust its size accordingly.
 * 
 *              Also note that all of this content is housed by a scrollview,
 *              allowing the user to scroll through the content - good for long
 *              lists.
 * 
 *              The structure is going to contain several cells and act like a
 *              giant table. The cells are each an instance of a class -
 *              extending JPanel
 * 
 *              The Generics is used for the tostring method, and the id method.
 *              each of which may be used.
 * 
 *
 */

public class ManageData<ObjectDisplayed> extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ObjectDisplayed primaryObject;
	private JLabel titleLabel;
	public String titleOfCurrentQuestionPanel;
	private String[] rowHeaders, rowIdentifiers;
	public JPanel tableView;
	private JPanel innerView;
	public static String currentIDSelected;

	/**
	 * @Description - Default constructor for a simple table view. All fields are required.
	 * @param panelTitle - Sets the panel title (Big letters)
	 * @param rowLabels - Sets the row's text
	 * @param objectIdentifiers	- The ID that is refrered to when a radio button is selected. This is the action command,
	 */
	public ManageData(String panelTitle, String[] rowLabels, String[] objectIdentifiers) {
		// First, let's just see if the class works the way we want it to from
		// within the parent.
		this.setBackground(Color.BLUE); // It works!

		this.setLayout(new BorderLayout());
		titleLabel = new JLabel(panelTitle);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setBackground(Color.WHITE);
		titleLabel.setOpaque(true);
		titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		titleLabel.setFont(new Font(Font.SERIF, titleLabel.getFont().getStyle(), 24));
		this.add(titleLabel, BorderLayout.NORTH);

		// Set local values
		this.setRowHeaders(rowLabels);
		this.setRowIdentifiers(objectIdentifiers);
		
		//Rest the row count
		Row.ROW_COUNT = 0;

		// Build the panel to hold the smaller panels
		try{
			buildTable();
		}catch(NullPointerException e){
			System.out.println(e.getMessage());
		}
		catch(Exception e){
			//If not a null pointer:
			System.out.println(e.getLocalizedMessage());
		}

		//currentIDSelected = "";		//Resets the ID each time
	}
	
	/**
	 * @Description - REbuilds the table if content is reloaded from library controller - DNF
	 * @deprecated
	 */
	public void rebuildTable(){
		//Reset the current table
		tableView = null;
		
		//Rebuild table
		buildTable();
		
		//revalidate frame
		revalidate();
	}

	/**
	 * @Desctiption - unsets the selected one from the button group
	 */
	public static void resetButtons(){
		Row.resetRowButtons(); //From Row();
		
		//reset the current ID
		ManageData.currentIDSelected = null;
	}
	
	/**
	 * @Description - Builds the table based on the current Rows and IDs set.
	 */
	private void buildTable() {
		// Create the main Table view
		tableView = new JPanel();
		tableView.setBackground(Color.WHITE);
		tableView.setLayout(new BorderLayout());
		this.add(tableView, BorderLayout.CENTER);

		innerView = new JPanel();
		innerView.setLayout(new GridBagLayout());
		innerView.setBackground(Color.WHITE);
		GridBagConstraints gb = new GridBagConstraints();
		/*gb.gridx = 0;
		gb.ipady = 0;
		gb.gridwidth = 5;
		gb.fill = GridBagConstraints.HORIZONTAL;
		gb.anchor = GridBagConstraints.FIRST_LINE_START;*/

		// Add the ScrollPane to it
		JScrollPane scrollView = new JScrollPane(innerView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollView.getVerticalScrollBar().setUnitIncrement(15);
		scrollView.setBorder(new MatteBorder(1,0,1,0, Color.lightGray));
		// scrollView.setBackground(Color.GREEN);
		tableView.add(scrollView);

		/** Add Some Sample Questions to be displayed */
		//Question questionList[] = new Question[10];
		// LibraryController lb = new LibraryController();
		
		//Get all of the questions from the libcontroller
		//System.out.println(LibraryController.giveCourseList());
		
		if(this.getRowIdentifiers() == null || this.getRowHeaders() == null || this.getRowHeaders().length == 0 || this.getRowIdentifiers().length == 0){
			
			//Alert the user that no data was found
			//JOptionPane.showMessageDialog(null, "No Content Was Found.\nPlease add content by selecting \"Add\" below");
			
		}else{
			//Add the data requested
			for(int i = 0; i < this.getRowHeaders().length; i++){
				Row newRow = new Row(rowHeaders[i], rowIdentifiers[i]);
				gb.gridy = i;
				gb.weightx = 1;
				gb.fill = GridBagConstraints.BOTH;
				gb.insets = new Insets( 0,0,0,0 );
				gb.weighty = 0;
				// Add the row to the table
				innerView.add(newRow, gb);
			}
			
		}

		// scrollView.setPreferredSize(new Dimension(this.getWidth(),
		// this.getHeight() * 2));
	}

	/**
	 * @author Justin
	 * @Description This class will be a small panel that will display the
	 *              contents. A new Row will be created for each row label
	 */
	private static class Row extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String rowName;
		private String accessID;
		private static int ROW_COUNT = 0;
		public static ButtonGroup group = new ButtonGroup();

		private Row(String name, String ID) {
			setAccessID(ID);
			setRowName(name);

			build();
		}
		
		/**
		 * @Desctiption - unsets the selected one from the button group
		 */
		public static void resetRowButtons(){
			group.clearSelection();
		}

		private Row() {
			setAccessID("NoID");
			setRowName("No Name Provided");

			build();
		}

		/**
		 * @description Builds the Row
		 */
		private void build() {
			this.setBackground(Color.WHITE);
			ROW_COUNT++;
			this.setBorder(new MatteBorder(0,5, 1, 5, Color.LIGHT_GRAY));
			this.setLayout(new BorderLayout());

			// Add a label indicating the Row number
			JLabel rowNumberLabel = new JLabel("" + ROW_COUNT + ") ");
			rowNumberLabel.setBorder(new EmptyBorder(30, 20, 30, 0));
			//rowNumberLabel.setBackground(Color.RED);
			//rowNumberLabel.setOpaque(true);
			this.add(rowNumberLabel, BorderLayout.WEST);

			// Add the label with the row name
			JLabel rowNameLabel = new JLabel(this.rowName);
			//rowNameLabel.setBackground(Color.BLUE);
			rowNameLabel.setBorder(new EmptyBorder(0, 10, 0, 10));
			rowNameLabel.setHorizontalAlignment(JLabel.LEFT);
			//rowNameLabel.setOpaque(true);
			this.add(rowNameLabel, BorderLayout.CENTER);

			// Add JRadioButton
			JRadioButton rb = new JRadioButton();
			rb.setBorder(new MatteBorder(0, 25, 1, 25, Color.BLACK));
			rb.setActionCommand(this.accessID);
			group.add(rb);
			this.add(rb, BorderLayout.EAST);
			
			rb.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					//Print out the action command
					System.out.println(e.getActionCommand()); //Displays the id of the row when selected.
					
					//This is where we are going to open whatever content we need from the id (now that we have it)
					currentIDSelected = e.getActionCommand();
				}
			});

		}

		/**
		 * @param accessID
		 *            the accessID to set
		 */
		public void setAccessID(String accessID) {
			this.accessID = accessID;
		}

		/**
		 * @param rowName
		 *            the rowName to set
		 */
		public void setRowName(String rowName) {
			this.rowName = rowName;
		}
	}

	/**
	 * @Description - Sets the title label of the table (Caption)
	 */
	public void setQuestionLayout() {
		titleLabel.setText(this.titleOfCurrentQuestionPanel);
	}

	/**
	 * @return the primaryObject
	 */
	public ObjectDisplayed getPrimaryObject() {
		return primaryObject;
	}

	/**
	 * @param primaryObject
	 *            the primaryObject to set
	 */
	public void setPrimaryObject(ObjectDisplayed primaryObject) {
		this.primaryObject = primaryObject;
	}

	/**
	 * @return the rowHeaders
	 */
	public String[] getRowHeaders() {
		return rowHeaders;
	}

	/**
	 * @param rowHeaders
	 *            the rowHeaders to set
	 */
	public void setRowHeaders(String[] rowHeaders) {
		this.rowHeaders = rowHeaders;
	}

	/**
	 * @return the rowIdentifiers
	 */
	public String[] getRowIdentifiers() {
		return rowIdentifiers;
	}

	/**
	 * @param rowIdentifiers
	 *            the rowIdentifiers to set
	 */
	public void setRowIdentifiers(String[] rowIdentifiers) {
		this.rowIdentifiers = rowIdentifiers;
	}

}
