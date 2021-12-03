import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;

public class Admin {

	 JFrame frmCarRentals;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin();
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
	public Admin() {
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
		
		JLabel adminLabel = new JLabel("Admin");
		adminLabel.setFont(new Font("UD Digi Kyokasho NK-R", Font.PLAIN, 20));
		adminLabel.setBounds(182, 27, 72, 39);
		frmCarRentals.getContentPane().add(adminLabel);
		
		JButton carsButton = new JButton("Cars");
		carsButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		carsButton.setBounds(30, 106, 89, 23);
		frmCarRentals.getContentPane().add(carsButton);
		
		JButton agentsButton = new JButton("Agents");
		agentsButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		agentsButton.setBounds(172, 106, 89, 23);
		frmCarRentals.getContentPane().add(agentsButton);
		
		JButton reviewsButton = new JButton("Reviews");
		reviewsButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		reviewsButton.setBounds(313, 106, 89, 23);
		frmCarRentals.getContentPane().add(reviewsButton);
		
		JLabel carsLabel = new JLabel("Cars Available:");
		carsLabel.setBounds(30, 180, 89, 14);
		frmCarRentals.getContentPane().add(carsLabel);
		
		JLabel agentsLabel = new JLabel("Number of Agents:");
		agentsLabel.setBounds(172, 180, 119, 14);
		frmCarRentals.getContentPane().add(agentsLabel);
		
		JLabel reviewButton = new JLabel("Average Review:");
		reviewButton.setBounds(313, 180, 111, 14);
		frmCarRentals.getContentPane().add(reviewButton);
	}
}