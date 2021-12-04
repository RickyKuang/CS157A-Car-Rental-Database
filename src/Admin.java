import java.sql.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;

public class Admin {
	JFrame frmCarRentals;
	private JLabel outputLabel;

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
		 * Right Outer Join for Customers and Agent Info
		 */
		JButton customersAgentsBtn = new JButton("Customers and Assigned Agents");
		customersAgentsBtn.setBounds(120, 25, 190, 35);
		displayPanel.add(customersAgentsBtn);
		customersAgentsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			           Class.forName("com.mysql.cj.jdbc.Driver");
			           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "RK10mysqlroot!");
			           Statement stmt = con.createStatement();
			           
			           String sql = "SELECT customerID, customerName, agentID, agentName "
			           		+ "FROM CUSTOMER, AGENT WHERE assignedAgentID = agentID "
			           		+ "UNION ALL "
			           		+ "SELECT NULL, NULL, agentID, agentName "
			           		+ "FROM AGENT WHERE agentID NOT IN (SELECT assignedAgentID FROM CUSTOMER);";
			           
			           ResultSet rs = stmt.executeQuery(sql);
			           String totalAgents = "<html>Customers and Assigned Agents<br>"
			           		+ "(customerID, customerName, agentID, agentName)<br>"
			           		+ "--------------------<br>";
			           while (rs.next()) {
			        	   totalAgents += rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getInt(3) + " | " + rs.getString(4) + "<br>";
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
			           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "RK10mysqlroot!");
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
			           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "RK10mysqlroot!");
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
			           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "RK10mysqlroot!");
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
			           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "RK10mysqlroot!");
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
					JFrame archiveCustomer = new JFrame("Archive Customer");
					archiveCustomer.setBounds(100, 100, 450, 100);
					archiveCustomer.getContentPane().setLayout(new GridLayout(4,1));
					archiveCustomer.setVisible(true);
					
					// Year Entry
					JLabel yearLabel = new JLabel("Enter Year Cutoff (XXXX):");
					yearLabel.setBounds(100, 90, 50, 14);
					archiveCustomer.getContentPane().add(yearLabel);
					
					JTextField yearTextField = new JTextField();
					yearTextField.setBounds(250, 90, 70, 20);
					archiveCustomer.getContentPane().add(yearTextField);
					
					// Month Entry
					JLabel monthLabel = new JLabel("Enter Month Cutoff (XX):");
					monthLabel.setBounds(100, 90, 50, 14);
					archiveCustomer.getContentPane().add(monthLabel);
					
					JTextField monthTextField = new JTextField();
					monthTextField.setBounds(250, 90, 70, 20);
					archiveCustomer.getContentPane().add(monthTextField);
					
					// Day Entry
					JLabel dayLabel = new JLabel("Enter Day Cutoff (XX):");
					dayLabel.setBounds(100, 90, 50, 14);
					archiveCustomer.getContentPane().add(dayLabel);
					
					JTextField dayTextField = new JTextField();
					dayTextField.setBounds(250, 90, 70, 20);
					archiveCustomer.getContentPane().add(dayTextField);
					
					// Submit Info
					JButton submitBtn = new JButton("Submit");
					submitBtn.setBounds(100, 300, 150, 14);
					archiveCustomer.getContentPane().add(submitBtn);
					submitBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								Class.forName("com.mysql.cj.jdbc.Driver");
						        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "RK10mysqlroot!");
						           
						        String sql = "{call archiveCustomer(?)}";
						        CallableStatement cstmt = con.prepareCall(sql);
						        
						        Timestamp cutoffDate = new Timestamp(System.currentTimeMillis());
						        cstmt.setTimestamp(1, cutoffDate);
						        cstmt.execute();
						           
						        outputLabel.setText("Cutoff Date: " + cutoffDate);
						        con.close();
							} catch (Exception exception) {
								outputLabel.setText(exception.getMessage());
							}
						}
					});
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
			           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "RK10mysqlroot!");
			           
			           String sql = "{call archiveBooking(?)}";
			           CallableStatement cstmt = con.prepareCall(sql);
			           Timestamp cutoffDate = new Timestamp(System.currentTimeMillis());
			           cstmt.setTimestamp(1, cutoffDate);
			           cstmt.execute();
			           
			           outputLabel.setText("Cutoff Date: " + cutoffDate);
			           
			           con.close();
			       }
			       catch (Exception exeption) {
			            outputLabel.setText(exeption.getMessage());
			       }
		}
		});
		
		/*
		 * Check Users that Reviewed
		 */
		JButton checkMultCarsBtn = new JButton("Cars w/ >1 Colors");
		checkMultCarsBtn.setBounds(120, 25, 190, 35);
		displayPanel.add(checkMultCarsBtn);
		checkMultCarsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
			        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "RK10mysqlroot!");
			        Statement stmt = con.createStatement();
			           
			        String sql = "SELECT brand, year, type, color, rented FROM CARS c1 "
			        		+ "WHERE carID != ANY ( "
			        		+ "SELECT carID FROM CARS "
			        		+ "WHERE brand = c1.brand AND year = c1.year "
			        		+ "AND type = c1.type);";
			        
			        ResultSet rs = stmt.executeQuery(sql);
			        String multCars = "<html>Cars with >1 Colors<br>"
			           		+ "(Brand, Year, Type, Color, Rented)<br>"
			           		+ "--------------------------<br>";
			        while (rs.next()) {
			        	   multCars += rs.getString(1) + " | " + rs.getInt(2) + " | " + rs.getString(3) 
			        	   			+ " | " + rs.getString(4) + " | " + rs.getInt(5) + "<br>";
			        }
			        outputLabel.setText(multCars + "</html>");
			        
			        con.close();
				} catch (Exception exception) {
					outputLabel.setText(exception.getMessage());
				}
			}
		});
		
		/*
		 * Intersect, Customers that Left Review for Assigned Agent
		 */
		JButton customerReviewAgentBtn = new JButton("Customers Review of Agent");
		customerReviewAgentBtn.setBounds(120, 25, 190, 35);
		displayPanel.add(customerReviewAgentBtn);
		customerReviewAgentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
			        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "RK10mysqlroot!");
			        Statement stmt = con.createStatement();
					
					String sql = "SELECT customerName, assignedAgentID FROM CUSTOMER "
							+ "WHERE (customerID, assignedAgentID) IN (SELECT reviewer, reviewedAgentID FROM REVIEWS);";
					
					ResultSet rs = stmt.executeQuery(sql);
			        String custAgent = "<html>Customers Review of Agent<br>"
			           		+ "(Customer Name, Agent Reviewed)<br>"
			           		+ "--------------------------<br>";
			        while (rs.next()) {
			        	   custAgent += rs.getString(1) + " | " + rs.getInt(2) + "<br>";
			        }
			        outputLabel.setText(custAgent + "</html>");
					
					con.close();
				} catch (Exception exception) {
					outputLabel.setText(exception.getMessage());
				}
			}
		});
		
		/*
		 * Check Customers with > 1 Review
		 */
		JButton checkMoreThanOneBtn = new JButton("Check Users w/ Mult Ratings");
		checkMoreThanOneBtn.setBounds(120, 25, 190, 35);
		displayPanel.add(checkMoreThanOneBtn);
		checkMoreThanOneBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
			        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "RK10mysqlroot!");
			        Statement stmt = con.createStatement();
					
					String sql = "SELECT customerID, customerName FROM CUSTOMER "
							+ "WHERE customerID IN ("
							+ "SELECT reviewer FROM REVIEWS "
							+ "GROUP BY reviewer "
							+ "HAVING count(*) > 1);";
					
					ResultSet rs = stmt.executeQuery(sql);
			        String moreThanOne = "<html>Customers with >1 Ratings<br>"
			           		+ "(Customer ID, Customer Name)<br>"
			           		+ "--------------------------<br>";
			        while (rs.next()) {
			        	   moreThanOne += rs.getInt(1) + " | " + rs.getString(2) + "<br>";
			        }
			        outputLabel.setText(moreThanOne + "</html>");
					
					con.close();
				} catch (Exception exception) {
					outputLabel.setText(exception.getMessage());
				}
			}
		});
		
		JPanel displayOutput = new JPanel();
		displayOutput.add(outputLabel);
		frmCarRentals.getContentPane().add(displayOutput);
	}
}
