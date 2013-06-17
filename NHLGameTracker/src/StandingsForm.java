/*
 * c_d_NHLController - StandingsForm
 * 
 * An interactive JTable Form used by c_d_NHLController to display an overview 
 * of all current team standings - Team, Wins, Losses, Points, etc.
 * Supported by a MySQL database backend.
 * 
 * By: Cameron Dykeman
 * Last Edited: 16/06/13
 * 
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


/*
 * Functionality for the Standings Form
 */
public class StandingsForm extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	//factors for sizing and placing GUI according to screen resolution
	private static final double FR_WIDTH_FACTOR = 0.45;
	private static final double FR_HEIGHT_FACTOR = 0.4;
	
	//class scope declarations
	private JTable tableStats;
	private JComboBox cmbDiv;
	private JButton btnRefresh, btnClose;
	private ButtonListener listener;
	
	//Database URL
	static final String DATABASE_URL = "jdbc:odbc:NHL2013db";  
	private String DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	
	/* StandingsForm constructor
	 */
	public StandingsForm() throws HeadlessException{
		
		// Set up the basic JFrame
		this.setTitle("Division Standings");
		this.setSize((int)(getToolkit().getScreenSize().width * FR_WIDTH_FACTOR),
								(int)(getToolkit().getScreenSize().height * FR_HEIGHT_FACTOR));
		this.setLocationRelativeTo(null);
		// set default as do nothing so that user can deny exit confirmation dialog
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//create one button listener object for all buttons
		listener = new ButtonListener();
		
		//build form
		addBottomControls();
		
		updateJTable("SELECT * FROM NHL2013db.Teams");
		
		this.setVisible(true);
	}

	/*
	 * Adds the Bottom control bar to the Form.
	 */
	private void addBottomControls()
	{
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1, 0, 10, 0));
		String[] buttonLabels = {"Refresh", "Close"};
		JButton[] buttons = new JButton[buttonLabels.length];
		
		JLabel label = new JLabel("Select Division");
		buttonsPanel.add(label);
		cmbDiv = new JComboBox<String>();
		cmbDiv.addItem("All");
		cmbDiv.addItem("Northeast");
		cmbDiv.addItem("Atlantic");
		cmbDiv.addItem("Southeast");
		cmbDiv.addItem("Pacific");
		cmbDiv.addItem("Central");
		cmbDiv.addItem("Northwest");
		cmbDiv.addActionListener(listener);
		buttonsPanel.add(cmbDiv);
		
		for(int i = 0; i < buttons.length; i++){
			buttons[i] = new JButton(buttonLabels[i]);
			buttons[i].addActionListener(listener);
			buttonsPanel.add(buttons[i]);
		}
		
		this.add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	/*
	 * Updates the JTable presenting NHL Team info.
	 * 
	 * @Param query A String containing a mySQL UPDATE command.
	 */
	private void updateJTable(String query)
	{
		//Build JTable
		Connection conn = null;
		Statement stmt = null;
		ResultSet results = null;
		Vector colNames = new Vector();
		Vector dataVector = new Vector();

		try
		{
			//establish connection to db
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(DATABASE_URL, "root", "y5g3mrw8t");
			//create statement
			stmt = conn.createStatement();
			//write the query statement in SQL and assign results to ResultSet object
			results = stmt.executeQuery(query);
			ResultSetMetaData metaData = results.getMetaData();		//gets metadata from the recordset returned
			int numberOfColumns = metaData.getColumnCount();
			//loop to output column names as headers
			for(int i = 1; i <= numberOfColumns; i++)
			{
				colNames.add( metaData.getColumnName(i) );
			}//end for
			while(results.next() )
			{
				Vector row = new Vector();
				for(int i = 1; i <= numberOfColumns; i++)
				{
					row.add(results.getString(i) );
				}//end for
				dataVector.add(row);
			}//end while			
		}
		catch(SQLException sqle)
		{
			String error = sqle.toString();
		}
		catch(Exception ex){}
		
		tableStats = new JTable(dataVector,colNames);
		JScrollPane scrollPane = new JScrollPane(tableStats);
		this.add(scrollPane);
		this.setVisible(true);
	}
	
	/*
	 * Generic listener object for use by the StandingsForm controls.
	 */
	private class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			//code to respond to the buttons goes here
			switch(e.getActionCommand()){
			
			case "Refresh":
				
				//get selection from combobox
				int teamDiv = cmbDiv.getSelectedIndex();
				//make query string
				String query = "SELECT * FROM NHL2013db.Teams ";
				if(cmbDiv.getSelectedIndex()==1){			//Northeast
					query += "WHERE Division = 'Northeast'";
				}
				else if(cmbDiv.getSelectedIndex()==2){		//Atlantic
					query += "WHERE Division = 'Atlantic'";
				}
				else if(cmbDiv.getSelectedIndex()==3){		//Southeast
					query += "WHERE Division = 'Southeast'";
				}
				else if(cmbDiv.getSelectedIndex()==4){		//Pacific
					query += "WHERE Division = 'Pacific'";
				}
				else if(cmbDiv.getSelectedIndex()==5){		//Central
					query += "WHERE Division = 'Central'";		
				}
				else if(cmbDiv.getSelectedIndex()==6){		//Northwest
					query += "WHERE Division = 'Northwest'";
				}
				
				//update table
				updateJTable(query);
				
				break;
			
			case "Close":

				dispose();
				
				break;

			}
		}
	}
}
