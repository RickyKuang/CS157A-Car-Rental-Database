import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class UserPay {

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
		
		JButton payButton = new JButton("Pay");
		payButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		payButton.setBounds(159, 181, 89, 23);
		frmCarRentals.getContentPane().add(payButton);
		
		JLabel payLabel = new JLabel("Pay");
		payLabel.setFont(new Font("UD Digi Kyokasho NK-R", Font.PLAIN, 20));
		payLabel.setBounds(182, 25, 44, 34);
		frmCarRentals.getContentPane().add(payLabel);
	}

}
