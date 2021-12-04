import java.awt.EventQueue;
import java.awt.Font;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserPay {

	JFrame frmCarRentals;
	private JTextField idTextField;
	private JTextField agentTextField;
	private JTextField carTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserPay window = new UserPay();
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
	public UserPay() {
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
		
		JLabel idLabel = new JLabel("Enter Customer ID:");
		idLabel.setBounds(94, 93, 113, 14);
		frmCarRentals.getContentPane().add(idLabel);
		
		idTextField = new JTextField();
		idTextField.setBounds(229, 90, 86, 20);
		frmCarRentals.getContentPane().add(idTextField);
		idTextField.setColumns(10);
		
		JLabel agentLabel = new JLabel("Enter Agent ID:");
		agentLabel.setBounds(94, 118, 100, 14);
		frmCarRentals.getContentPane().add(agentLabel);
		
		agentTextField = new JTextField();
		agentTextField.setBounds(229, 115, 86, 20);
		frmCarRentals.getContentPane().add(agentTextField);
		agentTextField.setColumns(10);
		
		JLabel carLabel = new JLabel("Enter Car ID:");
		carLabel.setBounds(94, 143, 86, 14);
		frmCarRentals.getContentPane().add(carLabel);
		
		carTextField = new JTextField();
		carTextField.setBounds(229, 140, 86, 20);
		frmCarRentals.getContentPane().add(carTextField);
		carTextField.setColumns(10);
		
		JButton payButton = new JButton("Pay");
		payButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "password");
					Statement stmt = con.createStatement();
					String sql = "UPDATE CUSTOMER SET assignedCar = NULL WHERE customerID = '" + idTextField.getText() + "' AND assignedAgentID =  " + agentTextField.getText() + " AND assignedCar = " + carTextField.getText();
					stmt.executeUpdate(sql);
					String sql2 = "DELETE FROM BOOKING WHERE bookedCarID =  " + carTextField.getText();
					stmt.executeUpdate(sql2);
					String sql3 = "UPDATE CARS SET rented = 0 WHERE carID = " + carTextField.getText();
					stmt.executeUpdate(sql3);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		payButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		payButton.setBounds(159, 181, 89, 23);
		frmCarRentals.getContentPane().add(payButton);
		
		JLabel payLabel = new JLabel("Pay");
		payLabel.setFont(new Font("UD Digi Kyokasho NK-R", Font.PLAIN, 20));
		payLabel.setBounds(182, 25, 44, 34);
		frmCarRentals.getContentPane().add(payLabel);
	}

}
