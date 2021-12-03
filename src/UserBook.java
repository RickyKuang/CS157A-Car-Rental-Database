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
	private JTextField nameTextField;
	private JTextField agentTextField;
	private JTextField carTextField;

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
		frmCarRentals.setBounds(100, 100, 450, 300);
		frmCarRentals.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCarRentals.getContentPane().setLayout(null);
		
		JLabel nameLabel = new JLabel("Enter Name:");
		nameLabel.setBounds(106, 93, 77, 14);
		frmCarRentals.getContentPane().add(nameLabel);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(193, 90, 86, 20);
		frmCarRentals.getContentPane().add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel agentLabel = new JLabel("Enter Agent:");
		agentLabel.setBounds(106, 118, 77, 14);
		frmCarRentals.getContentPane().add(agentLabel);
		
		agentTextField = new JTextField();
		agentTextField.setBounds(193, 115, 86, 20);
		frmCarRentals.getContentPane().add(agentTextField);
		agentTextField.setColumns(10);
		
		JLabel carLabel = new JLabel("Enter Car:");
		carLabel.setBounds(106, 143, 65, 14);
		frmCarRentals.getContentPane().add(carLabel);
		
		carTextField = new JTextField();
		carTextField.setBounds(193, 140, 86, 20);
		frmCarRentals.getContentPane().add(carTextField);
		carTextField.setColumns(10);
		
		JButton bookButton = new JButton("Book");
		bookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "password");
					Statement stmt = con.createStatement();
					String sql = "INSERT INTO CUSTOMER(customerName, assignedAgent, assignedCar) VALUES ('" + nameTextField.getText() + "', '" + agentTextField.getText() + "', " + carTextField.getText() + ")";
					stmt.executeUpdate(sql);
					Date date = new Date(1000);
					java.sql.Date sqldate = new java.sql.Date(date.getTime());
					String sql2 = "INSERT INTO BOOKING(bookedCarID, rentDate, dueDate, overdue) VALUES ('" + carTextField.getText() + "', '" + sqldate + "', '" + sqldate + "', 1)";
					stmt.executeUpdate(sql2);
					System.out.println("done");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		bookButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		bookButton.setBounds(159, 181, 89, 23);
		frmCarRentals.getContentPane().add(bookButton);
		
		JLabel lblNewLabel_3 = new JLabel("Book Car");
		lblNewLabel_3.setFont(new Font("UD Digi Kyokasho NK-R", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(159, 24, 107, 34);
		frmCarRentals.getContentPane().add(lblNewLabel_3);
	}
}
