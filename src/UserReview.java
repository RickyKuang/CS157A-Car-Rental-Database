import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserReview {

	JFrame frmCarRentals;
	private JTextField nameTextField;
	private JTextField agentTextField;
	private JTextField starTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserReview window = new UserReview();
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
	public UserReview() {
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
		
		JLabel ratingLabel = new JLabel("Rating");
		ratingLabel.setFont(new Font("UD Digi Kyokasho N-R", Font.PLAIN, 20));
		ratingLabel.setBounds(170, 23, 70, 39);
		frmCarRentals.getContentPane().add(ratingLabel);
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "password");
					Statement stmt = con.createStatement();
					String sql = "INSERT INTO REVIEWS (stars, reviewedAgentID, reviewer) VALUES ('" + starTextField.getText() + "', '" + agentTextField.getText() + "', " + nameTextField.getText() + ")";
					stmt.executeUpdate(sql);
					System.out.println("done");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		submitButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		submitButton.setBounds(164, 167, 89, 23);
		frmCarRentals.getContentPane().add(submitButton);
		
		JLabel nameLabel = new JLabel("Enter Name:");
		nameLabel.setBounds(119, 122, 84, 14);
		frmCarRentals.getContentPane().add(nameLabel);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(209, 119, 86, 20);
		frmCarRentals.getContentPane().add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel agentLabel = new JLabel("Enter Agent:");
		agentLabel.setBounds(119, 95, 70, 14);
		frmCarRentals.getContentPane().add(agentLabel);
		
		JLabel starLabel = new JLabel("Enter Stars:");
		starLabel.setBounds(119, 70, 70, 14);
		frmCarRentals.getContentPane().add(starLabel);
		
		agentTextField = new JTextField();
		agentTextField.setBounds(209, 92, 86, 20);
		frmCarRentals.getContentPane().add(agentTextField);
		agentTextField.setColumns(10);
		
		starTextField = new JTextField();
		starTextField.setBounds(209, 67, 86, 20);
		frmCarRentals.getContentPane().add(starTextField);
		starTextField.setColumns(10);
	}
}
