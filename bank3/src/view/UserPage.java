package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.BankAccount;
import model.BankSystem;
import model.SavingsAccount;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class UserPage extends JFrame {

	private JPanel contentPane;
    JTextArea textBalance = new JTextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserPage frame = new UserPage();
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
	public UserPage() {
		setTitle("User");
		addWindowFocusListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                updateBalance();
            }

            private void updateBalance() {
                BankAccount currentAccount = BankSystem.getInstance().getLoginAccount();
                if (currentAccount != null) {
                    textBalance.setText( String.format("%.2f$",currentAccount.getTotalBalance()));
                } else {
                    System.exit(1);
                }
            }
        });
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 371, 409);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Account Number:");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setBounds(50, 62, 108, 15);
        contentPane.add(lblNewLabel);

        JLabel lblBalance = new JLabel("Balance:");
        lblBalance.setHorizontalAlignment(SwingConstants.RIGHT);
        lblBalance.setBounds(50, 99, 108, 15);
        contentPane.add(lblBalance);

        JTextArea textAccountNumber = new JTextArea();
        textAccountNumber.setEditable(false);
        textAccountNumber.setBounds(168, 57, 108, 22);
        textAccountNumber.setText(BankSystem.getInstance().getLoginAccount().getAccountNumber());
        contentPane.add(textAccountNumber);
        textBalance.setEditable(false);

        textBalance.setBounds(168, 94, 108, 22);
        contentPane.add(textBalance);

        JButton btnDeposit = new JButton("Deposit");
        btnDeposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	DepositPage frame = new DepositPage();
				frame.setVisible(true);
	            BankSystem.getInstance().getLoginAccount().updateSavingsAccount();
            }
        });
        btnDeposit.setBounds(26, 148, 141, 57);
        contentPane.add(btnDeposit);

        JButton btnWithdrawal = new JButton("Withdrawal");
        btnWithdrawal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	WithdrawalPage frame = new WithdrawalPage();
				frame.setVisible(true);
	            BankSystem.getInstance().getLoginAccount().updateSavingsAccount();
            }
        });
        btnWithdrawal.setBounds(189, 148, 141, 57);
        contentPane.add(btnWithdrawal);

        JButton btnTasks = new JButton("View Tasks");
        btnTasks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(!BankSystem.getInstance().getLoginAccount().isParent()) {
	            	TasksPage frame = new TasksPage();
	                frame.setVisible(true);
            	}
            	else {
            		ParentPage frame = new ParentPage();
	                frame.setVisible(true);
            	}
            }
        });
        if(BankSystem.getInstance().getLoginAccount().isParent()) {
        	btnTasks.setText("Inspection Task");
        }
        btnTasks.setBounds(26, 215, 141, 53);
        contentPane.add(btnTasks);

        JButton btnSavingsgoal = new JButton("SavingsGoal");
        btnSavingsgoal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	SavingsGoalPage frame = new SavingsGoalPage();
                frame.setVisible(true);
            }
        });
        btnSavingsgoal.setBounds(189, 215, 141, 53);
        contentPane.add(btnSavingsgoal);

        JButton btnQuit = new JButton("Quit");
        btnQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(1);
            }
        });
        btnQuit.setBounds(189, 288, 141, 53);
        contentPane.add(btnQuit);
        
        JButton btnViewTransactions = new JButton("View Transactions");
        btnViewTransactions.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
	        	TransactionsPage frame = new TransactionsPage();
	            frame.setVisible(true);
        	}
        });
        btnViewTransactions.setBounds(26, 288, 141, 53);
        contentPane.add(btnViewTransactions);
    }
}
