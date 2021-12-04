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
	private JTextField reviewIDTextField;
	private JTextField nameTextField;
	private JTextField agentTextField;
	private JTextField starTextField;
	private JLabel outputLabel;

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
		frmCarRentals.setBounds(100, 100, 450, 450);
		frmCarRentals.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCarRentals.getContentPane().setLayout(null);
		
		JLabel ratingLabel = new JLabel("Rating");
		ratingLabel.setFont(new Font("UD Digi Kyokasho N-R", Font.PLAIN, 20));
		ratingLabel.setBounds(170, 23, 70, 39);
		frmCarRentals.getContentPane().add(ratingLabel);
		
		JLabel reviewIDLabel = new JLabel("Enter Review ID:");
		reviewIDLabel.setBounds(119, 60, 150, 14);
		frmCarRentals.getContentPane().add(reviewIDLabel);
		
		reviewIDTextField = new JTextField();
		reviewIDTextField.setBounds(220, 60, 86, 20);
		frmCarRentals.getContentPane().add(reviewIDTextField);
		reviewIDTextField.setColumns(10);
		
		JLabel starLabel = new JLabel("Enter Stars:");
		starLabel.setBounds(119, 90, 150, 14);
		frmCarRentals.getContentPane().add(starLabel);
		
		starTextField = new JTextField();
		starTextField.setBounds(220, 90, 86, 20);
		frmCarRentals.getContentPane().add(starTextField);
		starTextField.setColumns(10);
		
		JLabel agentLabel = new JLabel("Enter Agent ID:");
		agentLabel.setBounds(119, 120, 150, 14);
		frmCarRentals.getContentPane().add(agentLabel);
		
		agentTextField = new JTextField();
		agentTextField.setBounds(220, 120, 86, 20);
		frmCarRentals.getContentPane().add(agentTextField);
		agentTextField.setColumns(10);
		
		JLabel nameLabel = new JLabel("Enter User ID:");
		nameLabel.setBounds(119, 150, 150, 14);
		frmCarRentals.getContentPane().add(nameLabel);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(220, 150, 86, 20);
		frmCarRentals.getContentPane().add(nameTextField);
		nameTextField.setColumns(10);
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "RK10mysqlroot!");
					Statement stmt = con.createStatement();
					
					String sql = "INSERT INTO REVIEWS (reviewID, stars, reviewedAgentID, reviewer) VALUES (" + reviewIDTextField.getText() + ", " + starTextField.getText() + ", " + agentTextField.getText() + ", " + nameTextField.getText() + ")";
					stmt.executeUpdate(sql);
					
					outputLabel.setText("Thank you for reviewing");
					con.close();
				} catch (SQLException e1) {
					outputLabel.setText(e1.getMessage());
				}
			}
		});
		submitButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		submitButton.setBounds(164, 180, 89, 23);
		frmCarRentals.getContentPane().add(submitButton);
		
		/*
		 * Update Review
		 */
		JLabel updateReviewLabel = new JLabel("Update Rating");
		updateReviewLabel.setFont(new Font("UD Digi Kyokasho N-R", Font.PLAIN, 20));
		updateReviewLabel.setBounds(140, 210, 150, 39);
		frmCarRentals.getContentPane().add(updateReviewLabel);
		
		JLabel revisedAgentLabel = new JLabel("Enter Agent ID:");
		revisedAgentLabel.setBounds(119, 240, 150, 14);
		frmCarRentals.getContentPane().add(revisedAgentLabel);
		
		JTextField revisedAgentTextField = new JTextField();
		revisedAgentTextField.setBounds(220, 240, 86, 20);
		frmCarRentals.getContentPane().add(revisedAgentTextField);
		revisedAgentTextField.setColumns(10);
		
		JLabel revisedNameLabel = new JLabel("Enter User ID:");
		revisedNameLabel.setBounds(119, 270, 150, 14);
		frmCarRentals.getContentPane().add(revisedNameLabel);
		
		JTextField revisedNameTextField = new JTextField();
		revisedNameTextField.setBounds(220, 270, 86, 20);
		frmCarRentals.getContentPane().add(revisedNameTextField);
		revisedNameTextField.setColumns(10);
		
		JLabel updatedStarLabel = new JLabel("Enter New Stars:");
		updatedStarLabel.setBounds(119, 300, 150, 14);
		frmCarRentals.getContentPane().add(updatedStarLabel);
		
		JTextField updatedStarTextField = new JTextField();
		updatedStarTextField.setBounds(220, 300, 86, 20);
		frmCarRentals.getContentPane().add(updatedStarTextField);
		updatedStarTextField.setColumns(10);
		
		JButton updateButton = new JButton("Update Review");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CAR_RENTAL", "root", "RK10mysqlroot!");
					Statement stmt = con.createStatement();
					
					String sql = "UPDATE REVIEWS SET stars = " + updatedStarTextField.getText() 
									+ " WHERE reviewer = " + revisedNameTextField.getText() 
									+ " AND reviewedAgentID = " + revisedAgentTextField.getText();
					stmt.executeUpdate(sql);
					
					outputLabel.setText("Your Review Has Been Revised");
					con.close();
				} catch (SQLException e1) {
					outputLabel.setText(e1.getMessage());
				}
			}
		});
		updateButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		updateButton.setBounds(150, 330, 120, 23);
		frmCarRentals.getContentPane().add(updateButton);
		
		outputLabel = new JLabel("Output: ");
		outputLabel.setBounds(100, 360, 300, 15);
		frmCarRentals.getContentPane().add(outputLabel);
	}
}