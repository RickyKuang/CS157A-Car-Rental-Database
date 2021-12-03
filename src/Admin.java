import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

public class Admin {
	JFrame frmCarRentals;
	private JLabel outputLabel;
	private JTextField archCustomerTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin();
					window.frmCarRentals.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Admin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {	
		frmCarRentals = new JFrame("Admin");
		frmCarRentals.setBounds(100, 100, 975, 750);
		frmCarRentals.getContentPane().setLayout(new GridLayout(1,2));
		frmCarRentals.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel displayPanel = new JPanel(new GridLayout(4,4));
		frmCarRentals.getContentPane().add(displayPanel);
		
		outputLabel = new JLabel("Output: ");
		outputLabel.setBounds(150, 100, 200, 100);
		
		/*
		 * Check Availability of Cars
		 */
		JButton checkCarsAvailBtn = new JButton("Available Cars");
		checkCarsAvailBtn.setBounds(121, 24, 193, 34);
		displayPanel.add(checkCarsAvailBtn);
		checkCarsAvailBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			           Class.forName("com.mysql.cj.jdbc.Driver");
			           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "RK10mysqlroot!");
			           Statement stmt = con.createStatement();
			           
			           String sql = "SELECT carID, brand, type FROM CARS WHERE rented = 0;";
			           
			           ResultSet rs = stmt.executeQuery(sql);
			           String availCars = "<html>Available Cars<br>"
			           		+ "(carID, brand, type)<br>"
			           		+ "----------------<br>";
			           while (rs.next()) {
			        	   availCars += rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + "<br>";
			           }
			           outputLabel.setText(availCars + "</html>");
			           
			           con.close();
			       }
			       catch (Exception exeption) {
			            outputLabel.setText(exeption.getMessage());
			       }
			}
		});
		
		/*
		 * Check the total Number of Agents
		 */
		JButton checkTotalAgentsBtn = new JButton("Total Agents");
		checkTotalAgentsBtn.setBounds(120, 25, 190, 35);
		displayPanel.add(checkTotalAgentsBtn);
		checkTotalAgentsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			           Class.forName("com.mysql.cj.jdbc.Driver");
			           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "password");
			           Statement stmt = con.createStatement();
			           
			           String sql = "SELECT count(*) FROM AGENT";
			           
			           ResultSet rs = stmt.executeQuery(sql);
			           String totalAgents = "<html>Agent Total<br>"
			           		+ "-----------<br>";
			           while (rs.next()) {
			        	   totalAgents += rs.getInt(1) + "<br>";
			           }
			           outputLabel.setText(totalAgents);
			           
			           con.close();
			       }
			       catch (Exception exeption) {
			            outputLabel.setText(exeption.getMessage());
			       }
			}
		});
		
		/*
		 * Check the ratings of each Agent.
		 */
		JButton checkAgentReviewsBtn = new JButton("Agent Reviews");
		checkAgentReviewsBtn.setBounds(120, 25, 190, 35);
		displayPanel.add(checkAgentReviewsBtn);
		checkAgentReviewsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			           Class.forName("com.mysql.cj.jdbc.Driver");
			           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "password");
			           Statement stmt = con.createStatement();
			           
			           String sql = "SELECT agentName, stars FROM AGENT, REVIEWS "
			           		+ "WHERE agentID = reviewedAgentID "
			           		+ "order by stars desc";
			           
			           ResultSet rs = stmt.executeQuery(sql);
			           String agentOutput = "<html>Agent Reviews"
			           		+ "(Agent, Stars)<br>"
			           		+ "-------------<br>";
			           while (rs.next()) {
			        	   agentOutput += (rs.getString(1) + " | " + rs.getInt(2) + "<br>");
			           }
			           outputLabel.setText(agentOutput + "</html>");
			           
			           con.close();
			       }
			       catch (Exception exeption) {
			            outputLabel.setText(exeption.getMessage());
			       }
		}
		});
		
		
		/*
		 * Check the Customers that have an overdue car.
		 */
		JButton checkOverdueBtn = new JButton("Customers Overdue");
		checkOverdueBtn.setBounds(120, 25, 190, 35);
		displayPanel.add(checkOverdueBtn);
		checkOverdueBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			           Class.forName("com.mysql.cj.jdbc.Driver");
			           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "password");
			           Statement stmt = con.createStatement();
			           
			           String sql = "SELECT customerID, customerName, assignedCar FROM CUSTOMER\n"
			           		+ "WHERE assignedCar IN ( "
			           		+ "SELECT carID FROM CARS, BOOKING "
			           		+ "WHERE carID = bookedCarID "
			           		+ "AND overdue = 1 "
			           		+ ");";
			           
			           ResultSet rs = stmt.executeQuery(sql);
			           String overdueCustomers = "<html>Customers Overdue<br>"
			           		+ "(customerID, customerName, assignedCar)<br>"
			           		+ "------------------------------------<br>";
			           while (rs.next()) {
			        	   overdueCustomers += (rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getInt(3) + "<br>");
			           }
			           outputLabel.setText(overdueCustomers + "</html>");
			           
			           con.close();
			       }
			       catch (Exception exeption) {
			            outputLabel.setText(exeption.getMessage());
			       }
		}
		});
		
		/*
		 * Check the archived Customers.
		 */
		JButton checkCustomerArchiveBtn = new JButton("Archived Customers");
		checkCustomerArchiveBtn.setBounds(120, 25, 190, 35);
		displayPanel.add(checkCustomerArchiveBtn);
		checkCustomerArchiveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			           Class.forName("com.mysql.cj.jdbc.Driver");
			           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "password");
			           Statement stmt = con.createStatement();
			           
			           String sql = "SELECT * FROM ARCHIVE_CUSTOMER";
			           
			           ResultSet rs = stmt.executeQuery(sql);
			           String overdueCustomers = "<html>Archived Customers<br>"
			           		+ "(customerID, customerName, assignedAgentID, assignedCar, lastUpdated)<br>"
			           		+ "-----------------------------------------------------------<br>";
			           while (rs.next()) {
			        	   overdueCustomers += (rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getInt(3) + " | "
			        			   				+ rs.getInt(4) + " | " + rs.getTimestamp(5) + "<br>");
			           }
			           outputLabel.setText(overdueCustomers + "</html>");
			           
			           con.close();
			       }
			       catch (Exception exeption) {
			            outputLabel.setText(exeption.getMessage());
			       }
		}
		});
		
		/*
		 * Check the archived Bookings.
		 */
		JButton checkBookingArchiveBtn = new JButton("Archived Bookings");
		checkBookingArchiveBtn.setBounds(120, 25, 190, 35);
		displayPanel.add(checkBookingArchiveBtn);
		checkBookingArchiveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			           Class.forName("com.mysql.cj.jdbc.Driver");
			           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "password");
			           Statement stmt = con.createStatement();
			           
			           String sql = "SELECT * FROM ARCHIVE_BOOKING";
			           
			           ResultSet rs = stmt.executeQuery(sql);
			           String overdueCustomers = "<html>Archived Bookings<br>"
			           		+ "(bookingID, bookedCarID, rentDate, dueDate, overdue, lastUpdated)<br>"
			           		+ "---------------------------------------------------------<br>";
			           while (rs.next()) {
			        	   overdueCustomers += (rs.getInt(1) + " | " + rs.getInt(2) + " | " + rs.getDate(3) + " | "
			        			   				+ rs.getDate(4) + " | " + rs.getInt(5) + " | " + rs.getTimestamp(6) + "<br>");
			           }
			           outputLabel.setText(overdueCustomers + "</html>");
			           
			           con.close();
			       }
			       catch (Exception exeption) {
			            outputLabel.setText(exeption.getMessage());
			       }
		}
		});
		
		/*
		 * Archive Customer Stored Procedure
		 */
		JButton archiveCustomerBtn = new JButton("Archive Customer by Date");
		archiveCustomerBtn.setBounds(120, 25, 190, 35);
		displayPanel.add(archiveCustomerBtn);
		archiveCustomerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			           Class.forName("com.mysql.cj.jdbc.Driver");
			           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "password");
			           
			           String sql = "{call archiveCustomer(?)}";
			           CallableStatement cstmt = con.prepareCall(sql);
			           Timestamp cutoffDate = new Timestamp(System.currentTimeMillis());
			           cstmt.setTimestamp(1, cutoffDate);
			           boolean hasResult = cstmt.execute();
			           
			           outputLabel.setText("Cutoff Date: " + cutoffDate);
			           
			           con.close();
			       }
			       catch (Exception exeption) {
			            outputLabel.setText(exeption.getMessage());
			       }
		}
		});
		
		/*
		 * Archive Booking Stored Procedure
		 */
		JButton archiveBookingBtn = new JButton("Archive Booking by Date");
		archiveBookingBtn.setBounds(120, 25, 190, 35);
		displayPanel.add(archiveBookingBtn);
		archiveBookingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			           Class.forName("com.mysql.cj.jdbc.Driver");
			           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "password");
			           
			           String sql = "{call archiveBooking(?)}";
			           CallableStatement cstmt = con.prepareCall(sql);
			           Timestamp cutoffDate = new Timestamp(System.currentTimeMillis());
			           cstmt.setTimestamp(1, cutoffDate);
			           boolean hasResult = cstmt.execute();
			           
			           outputLabel.setText("Cutoff Date: " + cutoffDate);
			           
			           con.close();
			       }
			       catch (Exception exeption) {
			            outputLabel.setText(exeption.getMessage());
			       }
		}
		});
		
		JPanel displayOutput = new JPanel();
		displayOutput.add(outputLabel);
		frmCarRentals.getContentPane().add(displayOutput);
	}
}
