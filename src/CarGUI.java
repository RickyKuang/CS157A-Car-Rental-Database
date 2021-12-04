import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CarGUI {

	 JFrame frmCarRentals;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarGUI window = new CarGUI();
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
	public CarGUI() {
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
		
		/*
		 * Label for the type of Service offered
		 */
		JLabel carRentalServiceLabel = new JLabel("Car Rental Service");
		carRentalServiceLabel.setFont(new Font("UD Digi Kyokasho NK-R", Font.PLAIN, 20));
		carRentalServiceLabel.setBounds(130, 24, 193, 34);
		frmCarRentals.getContentPane().add(carRentalServiceLabel);
		
		/*
		 * Button for Accessing Public User Functions
		 */
		JButton userButton = new JButton("User");
		userButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				user.frmCarRentals.setVisible(true);
			}
		});
		userButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		userButton.setBounds(48, 127, 89, 23);
		frmCarRentals.getContentPane().add(userButton);
		
		/*
		 * Button for Accessing Admin User Functions
		 */
		JButton adminButton = new JButton("Admin");
		adminButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		adminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admin admin = new Admin();
				admin.frmCarRentals.setVisible(true);
			}
		});
		adminButton.setBounds(300, 127, 89, 23);
		frmCarRentals.getContentPane().add(adminButton);
	}
}
