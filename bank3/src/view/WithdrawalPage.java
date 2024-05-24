package view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.*;

public class WithdrawalPage extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private DefaultListModel<String> accountListModel;

    public WithdrawalPage() {
        setTitle("Withdrawal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 452, 411);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblDeposit = new JLabel("Withdrawal");
        lblDeposit.setHorizontalAlignment(SwingConstants.CENTER);
        lblDeposit.setFont(new Font("Arial", Font.PLAIN, 35));
        lblDeposit.setBounds(43, -18, 337, 101);
        contentPane.add(lblDeposit);

        // Initialize the account list model
        accountListModel = new DefaultListModel<>();

        // Add current account to the list
        BankAccount currentAccount = BankSystem.getInstance().getLoginAccount();
        accountListModel.addElement(currentAccount.getCurrentAccount().toString());

        // Add savings accounts to the list
        for (SavingsAccount savingsAccount : currentAccount.getSavingsList()) {
            accountListModel.addElement(savingsAccount.toString());
        }

        JList<String> listAccount = new JList<>(accountListModel);
        JScrollPane scrollPane = new JScrollPane(listAccount);
        scrollPane.setBounds(23, 67, 403, 180);
        contentPane.add(scrollPane);

        JButton btnNewButton = new JButton("Confirm");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the selected account
                int selectedIndex = listAccount.getSelectedIndex();
                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Please select an account.");
                    return;
                }

                String accountNumber = accountListModel.getElementAt(selectedIndex);

                // Get the amount to withdraw
                String amountText = textField.getText();
                double amount = Double.parseDouble(amountText);
                int result = 0;
                if (selectedIndex == 0) {
                    result = BankSystem.getInstance().withdraw(amount, null);
                } else {
                    result = BankSystem.getInstance().withdraw(amount, currentAccount.getSavingsList().get(selectedIndex - 1));
                }
                // Withdraw the amount using BankSystem
                switch (result) {
                    case 1:
                        JOptionPane.showMessageDialog(null, "Withdrawal Successful!");
    		            BankSystem.getInstance().setMoney(BankSystem.getInstance().getMoney()+amount);
                        dispose();
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Amount should be larger than 0.");
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, "Insufficient funds in the account.");
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null, "It’s not time to withdraw money from your savings account yet.");
                        break;
                }
            }
        });
        btnNewButton.setBounds(92, 333, 93, 23);
        contentPane.add(btnNewButton);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the withdrawal page
                dispose();
            }
        });
        btnCancel.setBounds(230, 333, 93, 23);
        contentPane.add(btnCancel);

        JLabel lblNewLabel = new JLabel("Enter the amount of money to withdraw:");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setBounds(75, 266, 248, 15);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setBounds(92, 291, 231, 21);
        contentPane.add(textField);
        textField.setColumns(10);

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WithdrawalPage frame = new WithdrawalPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

