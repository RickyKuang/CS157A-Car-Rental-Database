import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class User {

	 JFrame frmCarRentals;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User window = new User();
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
	public User() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCarRentals = new JFrame();
		frmCarRentals.setTitle("Car Rentals");
		frmCarRentals.setBounds(100, 100, 450, 300);
		frmCarRentals.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCarRentals.getContentPane().setLayout(null);
		
		JButton bookCarButton = new JButton("Book Car");
		bookCarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserBook userBook = new UserBook();
				userBook.frmCarRentals.setVisible(true);	
			}
		});
		bookCarButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		bookCarButton.setBounds(48, 127, 89, 23);
		frmCarRentals.getContentPane().add(bookCarButton);
		
		JLabel lblNewLabel = new JLabel("User");
		lblNewLabel.setFont(new Font("UD Digi Kyokasho NK-R", Font.PLAIN, 20));
		lblNewLabel.setBounds(193, 34, 56, 34);
		frmCarRentals.getContentPane().add(lblNewLabel);
		
		JButton reviewButton = new JButton("Review");
		reviewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		reviewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserReview userReview = new UserReview();
				userReview.frmCarRentals.setVisible(true);
			}
		});
		reviewButton.setBounds(307, 127, 89, 23);
		frmCarRentals.getContentPane().add(reviewButton);
		
		JButton payButton = new JButton("Pay");
		payButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		payButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserPay userPay = new UserPay();
				userPay.frmCarRentals.setVisible(true);
			}
		});
		payButton.setBounds(176, 127, 89, 23);
		frmCarRentals.getContentPane().add(payButton);
	}

}
