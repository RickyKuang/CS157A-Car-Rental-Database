import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import java.awt.Font;

public class UserCar {

	JFrame frmCarRentals;
	private JLabel outputLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserCar window = new UserCar();
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
	public UserCar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCarRentals = new JFrame();
		frmCarRentals.setBounds(100, 100, 450, 300);
		frmCarRentals.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCarRentals.getContentPane().setLayout(null);

		JPanel displayPanel = new JPanel();
		frmCarRentals.getContentPane().add(displayPanel);
		
		JLabel outputLabel = new JLabel("");
		outputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		outputLabel.setBounds(94, 59, 245, 146);
		frmCarRentals.getContentPane().add(outputLabel);
		
		JButton submitButton = new JButton("See Car Selection");
		submitButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			           Class.forName("com.mysql.cj.jdbc.Driver");
			           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "RK10mysqlroot!");
			           Statement stmt = con.createStatement();
			           
			           String sql = "SELECT carId, brand, year, color, type FROM CARS";
			           
			           ResultSet rs = stmt.executeQuery(sql);
			           String agentOutput = "<html>";
			           while (rs.next()) {
			        	   agentOutput += (rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getInt(3) + " | " + rs.getString(4) + " | " + rs.getString(5) +"<br>");
			           }
			           outputLabel.setText(agentOutput + "</html>");
			           
			           con.close();
			       }
			       catch (Exception exeption) {
			            outputLabel.setText(exeption.getMessage());
			       }
		}
		});
		submitButton.setBounds(150, 216, 150, 23);
		frmCarRentals.getContentPane().add(submitButton);
		
		JLabel carLabel = new JLabel("Cars:");
		carLabel.setFont(new Font("UD Digi Kyokasho NK-R", Font.PLAIN, 20));
		carLabel.setBounds(189, 13, 69, 35);
		frmCarRentals.getContentPane().add(carLabel);
	}
}