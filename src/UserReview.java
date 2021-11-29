import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;

public class UserReview {

	JFrame frmCarRentals;
	private JTextField nameTextField;

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
		
		JRadioButton oneButton = new JRadioButton("1");
		oneButton.setHorizontalAlignment(SwingConstants.CENTER);
		oneButton.setBounds(26, 69, 70, 23);
		frmCarRentals.getContentPane().add(oneButton);
		
		JRadioButton twoButton = new JRadioButton("2");
		twoButton.setHorizontalAlignment(SwingConstants.CENTER);
		twoButton.setBounds(98, 69, 70, 23);
		frmCarRentals.getContentPane().add(twoButton);
		
		JRadioButton threeButton = new JRadioButton("3");
		threeButton.setHorizontalAlignment(SwingConstants.CENTER);
		threeButton.setBounds(170, 69, 70, 23);
		frmCarRentals.getContentPane().add(threeButton);
		
		JRadioButton fourButton = new JRadioButton("4");
		fourButton.setHorizontalAlignment(SwingConstants.CENTER);
		fourButton.setBounds(245, 69, 70, 23);
		frmCarRentals.getContentPane().add(fourButton);
		
		JRadioButton fiveButton = new JRadioButton("5");
		fiveButton.setHorizontalAlignment(SwingConstants.CENTER);
		fiveButton.setBounds(317, 69, 70, 23);
		frmCarRentals.getContentPane().add(fiveButton);
		
		JButton submitButton = new JButton("Submit");
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
	}
}
