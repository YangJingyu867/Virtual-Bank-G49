package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.BankAccount;
import model.BankSystem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginPage() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Account Number:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(66, 109, 108, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(184, 106, 153, 21);
		contentPane.add(textField);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(66, 146, 108, 15);
		contentPane.add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(184, 143, 153, 21);
		contentPane.add(textField_1);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String accountNumber = textField.getText();
			        String password = textField_1.getText();

			        // Authenticate the user
			        boolean isAuthenticated = BankSystem.getInstance().authenticate(accountNumber, password);

			        if (isAuthenticated) {
			            // Login successful
			            BankAccount currentAccount = BankSystem.getInstance().getAccount(accountNumber);
			            BankSystem.getInstance().setLoginAccount(currentAccount);
			            UserPage frame = new UserPage();
						frame.setVisible(true);
			            dispose(); // Close the login page
			        } else {
			            // Login failed, show error message
			            JOptionPane.showMessageDialog(null, "Invalid account number or password. Please try again.");
			        }
			}
		});
		btnLogin.setBounds(95, 214, 93, 23);
		contentPane.add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(1);
			}
		});
		btnCancel.setBounds(223, 214, 93, 23);
		contentPane.add(btnCancel);
		
		JLabel lblLogin = new JLabel("Login ");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Arial", Font.PLAIN, 35));
		lblLogin.setBounds(57, 0, 301, 97);
		contentPane.add(lblLogin);
	}

}
