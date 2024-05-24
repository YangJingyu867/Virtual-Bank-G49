package view;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.BankSystem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class DepositPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JRadioButton rdbtnCurrentAccount;
    private JRadioButton rdbtnSavingsAccount6;
    private JRadioButton rdbtnSavingsAccount12;
    private JRadioButton rdbtnSavingsAccount24;
    private ButtonGroup depositTypeGroup;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepositPage frame = new DepositPage();
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
	public DepositPage() {
		setTitle("Deposit");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 458);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblDeposit = new JLabel("Deposit");
        lblDeposit.setHorizontalAlignment(SwingConstants.CENTER);
        lblDeposit.setFont(new Font("Arial", Font.PLAIN, 35));
        lblDeposit.setBounds(43, -18, 337, 101);
        contentPane.add(lblDeposit);

        JButton btnNewButton = new JButton("Confirm");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the amount to deposit
                String amountText = textField.getText();
                double amount = Double.parseDouble(amountText);
                int result=0;
             // Check which deposit type is selected
                if (rdbtnCurrentAccount.isSelected()) {
                	result=BankSystem.getInstance().deposit(BankSystem.getInstance().getLoginAccount().getAccountNumber(), amount,0);
                } else if (rdbtnSavingsAccount6.isSelected()) {
                	result=BankSystem.getInstance().deposit(BankSystem.getInstance().getLoginAccount().getAccountNumber(), amount,6);
                } else if (rdbtnSavingsAccount12.isSelected()) {
                	result=BankSystem.getInstance().deposit(BankSystem.getInstance().getLoginAccount().getAccountNumber(), amount,12);
                } else if (rdbtnSavingsAccount24.isSelected()) {
                	result=BankSystem.getInstance().deposit(BankSystem.getInstance().getLoginAccount().getAccountNumber(), amount,24);
                } 
                // Deposit the amount using BankSystem
                switch(result) {
                case 1:
		            JOptionPane.showMessageDialog(null, "Deposit Successfully!");
		            BankSystem.getInstance().setMoney(BankSystem.getInstance().getMoney()-amount);
		            dispose();
		            break;
                case 2:
		            JOptionPane.showMessageDialog(null, "Amount of money should be larger than 0.");
		            break;
                case 3:
		            JOptionPane.showMessageDialog(null, "You do not have enough money.");
		            break;
                }
            }
        });
        btnNewButton.setBounds(102, 379, 93, 23);
        contentPane.add(btnNewButton);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the deposit page
                dispose();
            }
        });
        btnCancel.setBounds(230, 379, 93, 23);
        contentPane.add(btnCancel);

        JLabel lblNewLabel = new JLabel("Enter the amount of money to deposit:");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setBounds(92, 196, 231, 15);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setBounds(92, 221, 231, 21);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblAmountOfMoney = new JLabel("Amount of money you currently have:");
        lblAmountOfMoney.setHorizontalAlignment(SwingConstants.RIGHT);
        lblAmountOfMoney.setBounds(92, 82, 231, 15);
        contentPane.add(lblAmountOfMoney);

        JTextArea textMoney = new JTextArea();
        textMoney.setEditable(false);
        textMoney.setBounds(91, 107, 232, 22);
        contentPane.add(textMoney);
        textMoney.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        // Get current balance from BankSystem and display it
        double currentMoney = BankSystem.getInstance().getMoney();
        textMoney.setText(String.valueOf(currentMoney));
        
        JLabel lblAmountOfMoney_2 = new JLabel("Amount of money in your account:");
        lblAmountOfMoney_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblAmountOfMoney_2.setBounds(92, 139, 231, 15);
        contentPane.add(lblAmountOfMoney_2);
        
        JTextArea textBalance = new JTextArea();
        textBalance.setEditable(false);
        textBalance.setText("0.0");
        textBalance.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        textBalance.setBounds(91, 164, 232, 22);
        contentPane.add(textBalance);
        double currentBalance = BankSystem.getInstance().getLoginAccount().getTotalBalance();
        textBalance.setText(String.valueOf(currentBalance));
        
        JLabel lblNewLabel_1 = new JLabel("Choose the type of deposit:");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setBounds(141, 255, 182, 15);
        contentPane.add(lblNewLabel_1);
        
        rdbtnCurrentAccount = new JRadioButton("Current Account");
        rdbtnCurrentAccount.setBounds(26, 287, 121, 23);
        contentPane.add(rdbtnCurrentAccount);

        rdbtnSavingsAccount6 = new JRadioButton("Savings Account (6 months)");
        rdbtnSavingsAccount6.setBounds(230, 287, 198, 23);
        contentPane.add(rdbtnSavingsAccount6);

        rdbtnSavingsAccount12 = new JRadioButton("Savings Account (12 months)");
        rdbtnSavingsAccount12.setBounds(26, 330, 198, 23);
        contentPane.add(rdbtnSavingsAccount12);

        rdbtnSavingsAccount24 = new JRadioButton("Savings Account (24 months)");
        rdbtnSavingsAccount24.setBounds(230, 330, 198, 23);
        contentPane.add(rdbtnSavingsAccount24);

        // Add radio buttons to a button group
        depositTypeGroup = new ButtonGroup();
        depositTypeGroup.add(rdbtnCurrentAccount);
        depositTypeGroup.add(rdbtnSavingsAccount6);
        depositTypeGroup.add(rdbtnSavingsAccount12);
        depositTypeGroup.add(rdbtnSavingsAccount24);
    }
}
