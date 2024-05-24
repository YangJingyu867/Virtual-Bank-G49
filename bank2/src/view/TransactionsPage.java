package view;

import java.awt.EventQueue;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import model.BankSystem;
import model.Task;
import model.Transaction;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class TransactionsPage extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TransactionsPage frame = new TransactionsPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TransactionsPage() {
        setTitle("Tasks");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 342);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTasks = new JLabel("Transactions");
        lblTasks.setHorizontalAlignment(SwingConstants.CENTER);
        lblTasks.setFont(new Font("Arial", Font.PLAIN, 35));
        lblTasks.setBounds(35, -14, 337, 101);
        contentPane.add(lblTasks);

        // Create a JScrollPane and add the JList to it
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(35, 69, 357, 165);
        contentPane.add(scrollPane);

        JList<Transaction> list = new JList<Transaction>();
        scrollPane.setViewportView(list);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancel.setBounds(168, 257, 93, 23);
        contentPane.add(btnCancel);

        // Populate JList with tasks
        List<Transaction> transactions = BankSystem.getInstance().getTransactions();
        DefaultListModel<Transaction> model = new DefaultListModel<>();
        for (Transaction transaction : transactions) {
            model.addElement(transaction);
        }
        list.setModel(model);
    }
}

