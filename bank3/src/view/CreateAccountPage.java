package view;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.BankSystem;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateAccountPage extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldNumber;
    private JTextField textFieldChildPassword;
    private JTextField textFieldParentPassword;

    /**
     * Create the frame.
     */
    public CreateAccountPage() {
        setTitle("Account Creation");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Changed to dispose on close
        setBounds(100, 100, 450, 308);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Account Number:");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setBounds(33, 94, 108, 15);
        contentPane.add(lblNewLabel);

        JLabel lblChildPassword = new JLabel("Child Password:");
        lblChildPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        lblChildPassword.setBounds(33, 131, 108, 15);
        contentPane.add(lblChildPassword);

        textFieldNumber = new JTextField();
        textFieldNumber.setBounds(151, 91, 153, 21);
        contentPane.add(textFieldNumber);
        textFieldNumber.setColumns(10);

        textFieldChildPassword = new JTextField();
        textFieldChildPassword.setColumns(10);
        textFieldChildPassword.setBounds(151, 128, 153, 21);
        contentPane.add(textFieldChildPassword);

        JButton btnNewButton = new JButton("Confirm");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create new account
            	String accountNumber = textFieldNumber.getText(); // Get account number from text field
                String childPassword = textFieldChildPassword.getText(); // Get password from text field
                String parentPassword = textFieldParentPassword.getText(); // Get password from text field

                switch(BankSystem.getInstance().createAccount(accountNumber, childPassword, parentPassword)) {
                case 1:
                	JOptionPane.showMessageDialog(null,"Account created successfully!");
                    dispose();
                    break;
                case 2:
                	JOptionPane.showMessageDialog(null,"Length of account number should larger than 7.");
                	break;
                case 3:
                	JOptionPane.showMessageDialog(null,"Length of password should be 6.");
                	break;
                }

            }
        });
        btnNewButton.setBounds(96, 226, 93, 23);
        contentPane.add(btnNewButton);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the window
                dispose();
            }
        });
        btnCancel.setBounds(225, 226, 93, 23);
        contentPane.add(btnCancel);

        JLabel lblNewLabel_1 = new JLabel("Parent Password:");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setBounds(33, 170, 108, 15);
        contentPane.add(lblNewLabel_1);

        JLabel lblAccountCreation = new JLabel("Account Creation");
        lblAccountCreation.setHorizontalAlignment(SwingConstants.CENTER);
        lblAccountCreation.setFont(new Font("Arial", Font.PLAIN, 35));
        lblAccountCreation.setBounds(33, -17, 337, 117);
        contentPane.add(lblAccountCreation);
        
        textFieldParentPassword = new JTextField();
        textFieldParentPassword.setColumns(10);
        textFieldParentPassword.setBounds(151, 167, 153, 21);
        contentPane.add(textFieldParentPassword);
    }
}
