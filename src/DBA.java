import java.sql.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class DBA {

    private App app;
	JFrame frmCarRentals;
	private JTextField textField;
	private JTable table;
	private JTextField selectTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBA window = new DBA();
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
	public DBA() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
		app = new App();
		} catch (Exception exc) {
			
		}
		 
		frmCarRentals = new JFrame();
		frmCarRentals.setBounds(100, 100, 1000, 600);
		frmCarRentals.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		frmCarRentals.getContentPane().add(panel, BorderLayout.NORTH);
		
		/*
		 * FOR EXECUTING QUERIES
		 * Will create a new label, text field, and submit button for executing queries.
		 */
		JLabel lblNewLabel = new JLabel("Enter Query:");
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(20);
		
		JButton submitButton = new JButton("Submit");
		panel.add(submitButton);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			           Class.forName("com.mysql.cj.jdbc.Driver");
			           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "password");
			           Statement stmt = con.createStatement();
			           
			           System.out.println("Connected");
			           
			           String sql = textField.getText();
			           stmt.executeUpdate(sql);
			           
			           con.close();
			       }
			       catch (Exception exeption) {
			            System.out.println(exeption);
			       }
			}
		});
		
		
		/*
		 * FOR RETURNING RESULT SETS
		 * Will create a new label, text field, and submit button for result sets.
		 */
		JLabel resultLabel = new JLabel("Enter SELECT:");
		panel.add(resultLabel);
		
		selectTextField = new JTextField();
		panel.add(selectTextField);
		selectTextField.setColumns(20);
		
		JButton submitSelectButton = new JButton("Submit");
		panel.add(submitSelectButton);
		submitSelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			           Class.forName("com.mysql.cj.jdbc.Driver");
			           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "password");
			           Statement stmt = con.createStatement();
			           
			           System.out.println("Connected");
			           String sql = selectTextField.getText();
			           
			           ResultSet rs = stmt.executeQuery(sql);
			           while (rs.next()) {
			        	   System.out.println(rs.getRow());
			           }
			           
			           con.close();
			       }
			       catch (Exception exeption) {
			            System.out.println(exeption);
			       }
			}
		});
		
		table = new JTable();
		frmCarRentals.getContentPane().add(table, BorderLayout.CENTER);
	}

}
