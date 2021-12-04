import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserBook {

	JFrame frmCarRentals;
	private JTextField custIDTextField;
	private JTextField nameTextField;
	private JTextField agentTextField;
	private JTextField carTextField;
	private JTextField bookingTextField;
	private JLabel outputLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserBook window = new UserBook();
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
	public UserBook() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCarRentals = new JFrame();
		frmCarRentals.setBounds(100, 100, 450, 450);
		frmCarRentals.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCarRentals.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Book Car");
		lblNewLabel_3.setFont(new Font("UD Digi Kyokasho NK-R", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(159, 24, 107, 34);
		frmCarRentals.getContentPane().add(lblNewLabel_3);
		
		JLabel custIDLabel = new JLabel("Enter Customer ID:");
		custIDLabel.setBounds(75, 90, 150, 14);
		frmCarRentals.getContentPane().add(custIDLabel);
		
		custIDTextField = new JTextField();
		custIDTextField.setBounds(250, 90, 86, 20);
		frmCarRentals.getContentPane().add(custIDTextField);
		custIDTextField.setColumns(10);
		
		JLabel nameLabel = new JLabel("Enter Customer Name:");
		nameLabel.setBounds(75, 120, 150, 14);
		frmCarRentals.getContentPane().add(nameLabel);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(250, 120, 86, 20);
		frmCarRentals.getContentPane().add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel agentLabel = new JLabel("Enter Agent ID:");
		agentLabel.setBounds(75, 150, 150, 14);
		frmCarRentals.getContentPane().add(agentLabel);
		
		agentTextField = new JTextField();
		agentTextField.setBounds(250, 150, 86, 20);
		frmCarRentals.getContentPane().add(agentTextField);
		agentTextField.setColumns(10);
		
		JLabel carLabel = new JLabel("Enter Car ID:");
		carLabel.setBounds(75, 180, 150, 14);
		frmCarRentals.getContentPane().add(carLabel);
		
		carTextField = new JTextField();
		carTextField.setBounds(250, 180, 86, 20);
		frmCarRentals.getContentPane().add(carTextField);
		carTextField.setColumns(10);
		
		JLabel bookingIDLabel = new JLabel("Enter Booking ID:");
		bookingIDLabel.setBounds(75, 210, 150, 14);
		frmCarRentals.getContentPane().add(bookingIDLabel);
		
		bookingTextField = new JTextField();
		bookingTextField.setBounds(250, 210, 86, 20);
		frmCarRentals.getContentPane().add(bookingTextField);
		bookingTextField.setColumns(10);
		
		JButton bookButton = new JButton("Book");
		bookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "password");
					Statement stmt = con.createStatement();
					String sql = "INSERT INTO CUSTOMER(customerID, customerName, assignedAgentID, assignedCar) VALUES (" + custIDTextField.getText() + ", '" +  nameTextField.getText() + "', '" + agentTextField.getText() + "', " + carTextField.getText() + ")";
					stmt.executeUpdate(sql);
					java.util.Date date = new java.util.Date();
					java.sql.Date sqlDate = new Date(date.getTime());
					java.sql.Date sqlDate2 = new Date(date.getTime());
					String sql2 = "INSERT INTO BOOKING(bookingID, bookedCarID, rentDate, dueDate, overdue) VALUES (" + bookingTextField.getText() + ", "+ carTextField.getText() + ", '" + sqlDate + "', '" + sqlDate2 + "', 0)";
					stmt.executeUpdate(sql2);
					String sql3 = "UPDATE CARS SET rented = 1 WHERE carID = " + carTextField;
					stmt.executeUpdate(sql3);
					
					outputLabel.setText("Thank you for Booking");
				} catch (SQLException e1) {
					outputLabel.setText(e1.getLocalizedMessage());
				}
			}
		});
		bookButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		bookButton.setBounds(159, 240, 89, 23);
		frmCarRentals.getContentPane().add(bookButton);
		
		outputLabel = new JLabel("Output:");
		outputLabel.setBounds(100, 270, 400, 25);
		frmCarRentals.getContentPane().add(outputLabel);
	}
}