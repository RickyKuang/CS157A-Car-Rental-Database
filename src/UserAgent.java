import java.awt.EventQueue;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.SwingConstants;
import java.awt.Font;

public class UserAgent {

	JFrame frmCarRentals;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserAgent window = new UserAgent();
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
	public UserAgent() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCarRentals = new JFrame();
		frmCarRentals.setBounds(100, 100, 450, 375);
		frmCarRentals.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCarRentals.getContentPane().setLayout(null);

		JPanel displayPanel = new JPanel();
		frmCarRentals.getContentPane().add(displayPanel);
		
		JLabel agentLabel = new JLabel("Agents:");
		agentLabel.setFont(new Font("UD Digi Kyokasho NK-R", Font.PLAIN, 20));
		agentLabel.setBounds(175, 11, 89, 35);
		frmCarRentals.getContentPane().add(agentLabel);
		
		JLabel outputLabel = new JLabel("");
		outputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		outputLabel.setBounds(94, 59, 245, 146);
		frmCarRentals.getContentPane().add(outputLabel);
		
		JButton submitButton = new JButton("Check Agent List");
		submitButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			           Class.forName("com.mysql.cj.jdbc.Driver");
			           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "password");
			           Statement stmt = con.createStatement();
			           
			           String sql = "SELECT agentName, agentID FROM AGENT";
			           
			           ResultSet rs = stmt.executeQuery(sql);
			           String agentOutput = "<html>Agent Name, Agent ID<br>"
			           		+ "------------------<br>";
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
		submitButton.setBounds(145, 277, 150, 23);
		frmCarRentals.getContentPane().add(submitButton);
		
		JButton checkAvgAgentBtn = new JButton("Check Agent Avg Ratings");
		checkAvgAgentBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		checkAvgAgentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			           Class.forName("com.mysql.cj.jdbc.Driver");
			           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "password");
			           Statement stmt = con.createStatement();
			           
			           String sql = "SELECT agentName, average_rating.avgStars "
			           		+ "FROM (SELECT avg(stars) as avgStars, reviewedAgentID from REVIEWS group by reviewedAgentID) as average_rating, AGENT "
			           		+ "WHERE AGENT.agentID = average_rating.reviewedAgentID;";
			           
			           ResultSet rs = stmt.executeQuery(sql);
			           String agentOutput = "<html>Agent Name, Average Rating<br>"
			           		+ "------------------<br>";
			           while (rs.next()) {
			        	   agentOutput += (rs.getString(1) + " | " + rs.getDouble(2) + "<br>");
			           }
			           outputLabel.setText(agentOutput + "</html>");
			           
			           con.close();
			       }
			       catch (Exception exeption) {
			            outputLabel.setText(exeption.getMessage());
			       }
		}
		});
		
		checkAvgAgentBtn.setBounds(145, 302, 150, 23);
		frmCarRentals.getContentPane().add(checkAvgAgentBtn);
	}

}