/*
 * c_d_NHLController
 * 
 * An NHL Teams stat-tracker completed for Fanshawe's INFO-5051 project1.
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class c_d_NHLController extends JFrame {

private static final long serialVersionUID = 1L;
	
	//factors for sizing and placing GUI according to screen resolution
	private static final double FR_WIDTH_FACTOR = 0.5;
	private static final double FR_HEIGHT_FACTOR = 0.45;
	
	//class scope declarations
	private JButton btnAdd, btnClear, btnDelete, btnRetrieve, btnStandings, btnClose;
	private JComboBox cmbMonth, cmbDay, cmbYear;
	private JComboBox cmbHome, cmbAway;
	private JTextField fldHome, fldAway;
	private JCheckBox chbOvertime, chbShootout;
	private ButtonListener listener;
	
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet results = null;
	
	static final String DATABASE_URL = "jdbc:odbc:NHL2013db";  
	private String DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	
	//cponstructor
	public c_d_NHLController() throws HeadlessException
	{
		// Set up the basic JFrame
		this.setTitle("NHL Game Tracker");
		this.setSize((int)(getToolkit().getScreenSize().width * FR_WIDTH_FACTOR),
								(int)(getToolkit().getScreenSize().height * FR_HEIGHT_FACTOR));
		this.setLocationRelativeTo(null);
		// set default as do nothing so that user can deny exit confirmation dialog
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//check when user clicks the frame's "Close" button. 
		this.addWindowListener(new WindowAdapter() 
		{
		  public void windowClosing(WindowEvent e)
		  {
			  int n = JOptionPane.showConfirmDialog(
						c_d_NHLController.this, "Are you sure you want to exit?",
						"User Confirmation", JOptionPane.YES_NO_OPTION);
				
				if ( n == JOptionPane.YES_OPTION)
				{
					//destory the frame object
					c_d_NHLController.this.dispose();					
				}			
		  }//end method
		});//end method addWindowListener() arg list
		
		//create one button listener object for all buttons
		listener = new ButtonListener();
		// GameInfoPanel
		JPanel gameInfoPanel = new JPanel();
		
		gameInfoPanel.setBorder( BorderFactory.createTitledBorder("Game Information"));
		gameInfoPanel.setLayout(new GridLayout(5, 1, 20, 20));		
		
		// Date Panel
		JPanel datePanel = new JPanel();		
		datePanel.setLayout( new GridLayout(1, 4, 20, 20) );		
		datePanel.add(new JLabel("<< Date >> (Month/Day/Year)",  JLabel.RIGHT));
		
		cmbMonth = new JComboBox();
		//load months
		cmbMonth.addItem("January");
		cmbMonth.addItem("February");
		cmbMonth.addItem("March");
		cmbMonth.addItem("April");
		cmbMonth.addItem("May");
		cmbMonth.addItem("June");
		cmbMonth.addItem("July");
		cmbMonth.addItem("August");
		cmbMonth.addItem("September");
		cmbMonth.addItem("October");
		cmbMonth.addItem("November");
		cmbMonth.addItem("December");
		
		cmbMonth.addActionListener(listener);
		
		datePanel.add(cmbMonth);
		
		//load with years
		cmbYear = new JComboBox();
		 for ( int year = 2012; year <= 2021; ++year) 
		 {
			cmbYear.addItem(year);
		 }//end for
		cmbYear.addActionListener(listener);
		
		//load days combo
		cmbDay = new JComboBox();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(cmbYear.getSelectedItem().toString()));
		cal.set(Calendar.MONTH, cmbMonth.getSelectedIndex());
		int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cmbDay.removeAll();
		for(int i = 1; i <= daysInMonth; i++){
			cmbDay.addItem(i);
		}
		cmbDay.setSelectedIndex(0);
		datePanel.add(cmbDay);
		
		datePanel.add(cmbYear);
		
		gameInfoPanel.add(datePanel);	
		
		JPanel title = new JPanel();		
		title.setLayout( new GridLayout(1, 3, 20, 20) );		
		title.add(new JLabel(""));
		title.add(new JLabel("HOME", JLabel.CENTER));
		title.add(new JLabel("AWAY",  JLabel.CENTER));
		
		gameInfoPanel.add(title);
	
		JPanel team = new JPanel();		
		team.setLayout( new GridLayout(1, 3, 20, 20));		
		team.add(new JLabel("<< Team >> ",  JLabel.RIGHT));
		
		cmbHome = new JComboBox();
		cmbAway = new JComboBox();
		//Get Teams from DB
		//establish connection to db
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(DATABASE_URL, "root", "y5g3mrw8t");
			//create statement
			stmt = conn.createStatement();
			//write the query statement in SQL and assign results to ResultSet object
			String query = "SELECT * FROM NHL2013db.Teams";
			results = stmt.executeQuery(query);
			
			while(results.next()){
				String asdf = results.getString(2);
				cmbHome.addItem(asdf);
				cmbAway.addItem(asdf);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception ex){}

		team.add(cmbHome);
		team.add(cmbAway);		
		gameInfoPanel.add(team);
		
		JPanel goal = new JPanel();		
		goal.setLayout( new GridLayout(1, 3, 20, 20));		
		goal.add(new JLabel("Goals", JLabel.RIGHT));
		
		fldHome = new JTextField("");
		fldHome.setHorizontalAlignment(JTextField.CENTER) ;
		goal.add(fldHome);
		
		fldAway = new JTextField("");
		fldAway.setHorizontalAlignment(JTextField.CENTER) ;
		goal.add(fldAway);		
		gameInfoPanel.add(goal);
		
		JPanel overtime = new JPanel();		
		overtime.setLayout( new GridLayout(1, 3, 20, 20));		
		overtime.add(new JLabel("Overtime?", JLabel.RIGHT));
		
		chbOvertime= new JCheckBox();
		chbOvertime.addActionListener(listener);
		chbOvertime.setName("Overtime");
		overtime.add(chbOvertime);
		
		overtime.add(new JLabel("Shootout?", JLabel.RIGHT));
		chbShootout = new JCheckBox();
		chbShootout.setName("Shootout");
		chbShootout.addActionListener(listener);
		overtime.add(chbShootout);		
		gameInfoPanel.add(overtime);		
		this.add(gameInfoPanel, BorderLayout.CENTER);
		
		// Button Panel
		JPanel buttonPanel = new JPanel();		
		buttonPanel.setLayout( new GridLayout(2, 3, 20, 20) );
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(listener);
		buttonPanel.add(btnAdd);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(listener);
		buttonPanel.add(btnClear);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(listener);
		buttonPanel.add(btnDelete);
		
		btnRetrieve = new JButton("<< Retrieve >> ");
		btnRetrieve.addActionListener(listener);
		buttonPanel.add(btnRetrieve);
		
		btnStandings = new JButton("Standings");
		btnStandings.addActionListener(listener);
		buttonPanel.add(btnStandings);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(listener);
		buttonPanel.add(btnClose);
		
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		//last line
		this.setVisible(true);
		
		
	}//end constructor
	
	public static void main(String[] args)
	{
		// anonymous object...
		new c_d_NHLController();
	}//end main
	
	//Implement a listener for ActionEvents 
	// as an inner class
	private class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			//code to respond to the buttons goes here
			switch(e.getActionCommand()){

			//ComboBox changed
			case "comboBoxChanged":
				//Update ComboBoxDay
				if(e.getSource().equals(cmbYear) || e.getSource().equals(cmbMonth)){
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.YEAR, Integer.parseInt(cmbYear.getSelectedItem().toString()));
					cal.set(Calendar.MONTH, cmbMonth.getSelectedIndex());
					int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
					int currentDay = cmbDay.getSelectedIndex();
					cmbDay.removeAll();
					for(int i = 1; i <= daysInMonth; i++){
						cmbDay.addItem(i);
					}
					cmbDay.setSelectedIndex(currentDay);
				}
				break;
				
			//Overtime/shootout checked
			case "":
				
				if(chbShootout.isSelected()){
					chbOvertime.setSelected(true);
				}
				
				break;
				
			//Add Clicked
			case "Add":

				addEntry();
				
				break;
				
			//Clear clicked
			case "Clear":
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to save your Current Game first?","Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
					//attempt to save, then clear
					int result = addEntry();
					if(result==0)
					{
						clearForm();
					}
				}
				else{
					//clear without save
					clearForm();
				}
				
				break;
				
			case "Delete":
				
				dialogButton = JOptionPane.YES_NO_OPTION;
				dialogResult = JOptionPane.showConfirmDialog (null, "Delete the selected game?","Delete?",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
					
					retreiveEntry();
					int result = deleteEntry();
					if(result == 0){
						JOptionPane.showMessageDialog(null, "Game Deleted Successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Error:\nDelete Failed!", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				break;
				
			case "<< Retrieve >> ":
				
				dialogButton = JOptionPane.YES_NO_OPTION;
				dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to save your Current Game first?","Warning",dialogButton);
				int result;
				if(dialogResult == JOptionPane.YES_OPTION){
					//attempt to save, then retrieve
					addEntry();
					result = retreiveEntry();
				}
				else{
					//retrieve without save
					result = retreiveEntry();
				}
				
				if(result!=0){
					JOptionPane.showMessageDialog(null, "Error:\nCould not find the selected game.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				break;
				
			case "Standings":
				
				new StandingsForm();
				
				break;
				
			case "Close":
				
				dialogButton = JOptionPane.YES_NO_OPTION;
				dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to exit?","User Confirmation",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
					dispose();
				}
				
				break;
			}
		}//end actionPerformed()
	}//end inner class
	
	//methods here for the various database operations
	
	/* Validates that all required fields have been filled out by the user. */
	private String validateForm(){
		
		//Input
		boolean valid = true;
		String error = "";
		//validate date
		if(cmbYear.getSelectedItem() == null || cmbMonth.getSelectedItem() == null || cmbDay.getSelectedItem() == null){
			valid = false;
			error += "Must set a date.\n";
		}
		//validate teams
		if(cmbHome.getSelectedItem()==cmbAway.getSelectedItem()){
			valid = false;
			error += "Must select opposing teams.\n";
		}
		//validate score
		if(fldHome.getText().compareTo(fldAway.getText())==0){
			valid = false;
			error += "Games cannot end in a tie.\n";
		}
		return error;
		
	}
	
	/* Adds the current entry to the Games DB and updates the Teams DB. 
	 * @return int Returns 0 if successful.
	 * */
	private int addEntry(){
		
		String error = validateForm();
		
		//Output
		if(error==""){
			//Save to DataBase
			//establish connection to db
			try {
				conn = DriverManager.getConnection(DATABASE_URL, "root", "y5g3mrw8t");
				//create statement
				stmt = conn.createStatement();
				//write the insert query
				String query = "INSERT into NHL2013db.Games (" +
						"HomeTeamID, AwayTeamID, HomeTeamGoals, AwayTeamGoals, GameMonth, GameDay, GameYear, Overtime, Shootout" +
						") VALUES (" +
						cmbHome.getSelectedIndex() + ", " +
						cmbAway.getSelectedIndex() + ", " +
						Integer.parseInt(fldHome.getText()) + ", " + 
						Integer.parseInt(fldAway.getText()) + ", " + 
						cmbMonth.getSelectedIndex() + ", " + 
						cmbDay.getSelectedIndex() + ", " + 
						cmbYear.getSelectedItem() + ", " + 
						chbOvertime.isSelected() + ", " + 
						chbShootout.isSelected()  + ");";
				stmt.executeUpdate(query);
				//write the update-winner query
				//get winner/loser
				int winner;
				int loser;
				if(Integer.parseInt(fldHome.getText()) > Integer.parseInt(fldAway.getText())){
					winner = cmbHome.getSelectedIndex() + 1;
					loser = cmbAway.getSelectedIndex() + 1;
				}
				else{
					loser = cmbHome.getSelectedIndex() + 1;
					winner = cmbAway.getSelectedIndex() + 1;
				}
				query = "UPDATE NHL2013db.Teams SET GamesPlayed = GamesPlayed + 1, Wins = Wins + 1, Points = Points + 2 " +
						"WHERE TeamID = " + winner;
				stmt.executeUpdate(query);
				//write the update-loser query
				//get the loss type
				String lossType = "RegulationLosses";
				int points = 0;
				if(chbOvertime.isSelected()){ lossType = "OvertimeLosses"; points = 1;}
				if(chbShootout.isSelected()){ lossType = "ShootoutLosses"; points = 1;}
				query = "UPDATE NHL2013db.Teams SET GamesPlayed = GamesPlayed + 1, Points = Points + " + points + ", " + lossType + " = " + lossType + " + 1 " +
						"WHERE TeamID = " + loser;
				stmt.executeUpdate(query);
				
				JOptionPane.showMessageDialog(null, "Game Saved Successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 1;
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Error:\n" + error, "Error", JOptionPane.ERROR_MESSAGE);
			return 1;
		}
		return 0;
	}

	/* Clears the form back to a generic start date - Jan. 01, 2012. */
	private void clearForm(){
		
		cmbAway.setSelectedIndex(0);
		cmbDay.setSelectedIndex(0);
		cmbHome.setSelectedIndex(0);
		cmbMonth.setSelectedIndex(0);
		cmbYear.setSelectedIndex(0);
		fldAway.setText("");
		fldHome.setText("");
		chbOvertime.setSelected(false);
		chbShootout.setSelected(false);
		
	}
	
	/* Deletes the currently displayed entry the Games DB and updates the Teams DB, if that entry is a valid entry. 
	 * @return int Returns 0 if successful.
	 * */
	private int deleteEntry(){
		//attempt to delete from DB
		//establish connection to db
		try {
			conn = DriverManager.getConnection(DATABASE_URL, "root", "y5g3mrw8t");
			//create statement
			stmt = conn.createStatement();
			//write the insert query
			String query = "DELETE FROM NHL2013db.Games " +
					"WHERE " +
					"HomeTeamID = " + cmbHome.getSelectedIndex() + " AND " +
					"AwayTeamID = " + cmbAway.getSelectedIndex() + " AND " +
					"GameMonth = " + cmbMonth.getSelectedIndex() + " AND " + 
					"GameDay = " + cmbDay.getSelectedIndex() + " AND " + 
					"GameYear = " + cmbYear.getSelectedItem();
			stmt.executeUpdate(query);
			//write the update-winner query
			//get winner/loser
			int winner;
			int loser;
			if(Integer.parseInt(fldHome.getText()) > Integer.parseInt(fldAway.getText())){
				winner = cmbHome.getSelectedIndex() + 1;
				loser = cmbAway.getSelectedIndex() + 1;
			}
			else{
				loser = cmbHome.getSelectedIndex() + 1;
				winner = cmbAway.getSelectedIndex() + 1;
			}
			query = "UPDATE NHL2013db.Teams SET GamesPlayed = GamesPlayed - 1, Wins = Wins - 1, Points = Points - 2 " +
					"WHERE TeamID = " + winner;
			stmt.executeUpdate(query);
			//write the update-loser query
			//get the loss type
			String lossType = "RegulationLosses";
			int points = 0;
			if(chbOvertime.isSelected()){ lossType = "OvertimeLosses"; points = 1;}
			if(chbShootout.isSelected()){ lossType = "ShootoutLosses"; points = 1;}
			query = "UPDATE NHL2013db.Teams SET GamesPlayed = GamesPlayed - 1, Points = Points-+ " + points + ", " + lossType + " = " + lossType + " - 1 " +
					"WHERE TeamID = " + loser;
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}
		return 0;
	}

	/* Retrieves any game information available for the currently indicated date, Home Team and/or Away Team.  
	 * @return int Returns 0 if successful.
	 * */
	private int retreiveEntry(){
		//query
		//establish connection to db
		try {
			conn = DriverManager.getConnection(DATABASE_URL, "root", "y5g3mrw8t");
			//create statement
			stmt = conn.createStatement();
			//write the query statement in SQL and assign results to ResultSet object
			String query = "SELECT * FROM NHL2013db.Games WHERE " +
					"GameDay = " + cmbDay.getSelectedIndex() + " AND " +
					"GameMonth = " + cmbMonth.getSelectedIndex() + " AND " +
					"GameYear = " + cmbYear.getSelectedItem() + " AND " +
					"HomeTeamID = " + cmbHome.getSelectedIndex() + " AND " +
					"AwayTeamID = " + cmbAway.getSelectedIndex();
			results = stmt.executeQuery(query);
			if(!results.next()){ return 1;}
			while(results.next()){
				fldHome.setText(String.valueOf(results.getInt(4)));
				fldAway.setText(String.valueOf(results.getInt(5)));
				chbOvertime.setSelected(results.getBoolean(9));
				chbShootout.setSelected(results.getBoolean(10));
			}
		} catch (SQLException e) {
			//error
			JOptionPane.showMessageDialog(null, "Error:\nCould not find the selected game.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return 1;
		}
		return 0;
	}
	
}
