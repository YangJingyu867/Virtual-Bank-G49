package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;

import model.BankSystem;
import model.SavingsGoal;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SavingsGoalPage extends JFrame {

    private JPanel contentPane;
    private JTextField textAmount;
    private JTextField textDescription;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SavingsGoalPage frame = new SavingsGoalPage();
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
    public SavingsGoalPage() {
    	setTitle("Savings Goal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 416);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTasks = new JLabel("Savings Goal");
        lblTasks.setHorizontalAlignment(SwingConstants.CENTER);
        lblTasks.setFont(new Font("Arial", Font.PLAIN, 35));
        lblTasks.setBounds(36, -24, 337, 101);
        contentPane.add(lblTasks);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(36, 59, 357, 165);
        contentPane.add(scrollPane);

        JList<SavingsGoal> list = new JList<SavingsGoal>();
        scrollPane.setViewportView(list);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancel.setBounds(300, 344, 93, 23);
        contentPane.add(btnCancel);

        JButton btnAddGoal = new JButton("Add Goal");
        btnAddGoal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get amount and description from text fields
                double amount = Double.parseDouble(textAmount.getText());
                String description = textDescription.getText();
                // Create new SavingsGoal object
                SavingsGoal goal = new SavingsGoal(description, amount);
                // Add goal to system
                BankSystem.getInstance().getLoginAccount().addSavingsGoal(goal);
                // Update JList
                DefaultListModel<SavingsGoal> model = (DefaultListModel<SavingsGoal>) list.getModel();
                model.addElement(goal);
                // Clear text fields
                textAmount.setText("");
                textDescription.setText("");
            }
        });
        btnAddGoal.setBounds(185, 344, 93, 23);
        contentPane.add(btnAddGoal);

        JLabel lblAmountOfMoney = new JLabel("Amount of Money:");
        lblAmountOfMoney.setHorizontalAlignment(SwingConstants.RIGHT);
        lblAmountOfMoney.setBounds(35, 261, 132, 15);
        contentPane.add(lblAmountOfMoney);

        textAmount = new JTextField();
        textAmount.setColumns(10);
        textAmount.setBounds(177, 258, 153, 21);
        contentPane.add(textAmount);

        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescription.setBounds(59, 298, 108, 15);
        contentPane.add(lblDescription);

        textDescription = new JTextField();
        textDescription.setColumns(10);
        textDescription.setBounds(177, 295, 153, 21);
        contentPane.add(textDescription);

        JButton btnDeleteGoal = new JButton("Delete Goal");
        btnDeleteGoal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get selected goal
                SavingsGoal selectedGoal = list.getSelectedValue();
                if (selectedGoal != null) {
                    // Remove goal from system
                    BankSystem.getInstance().getLoginAccount().removeGoal(selectedGoal);
                    // Remove goal from JList
                    DefaultListModel<SavingsGoal> model = (DefaultListModel<SavingsGoal>) list.getModel();
                    model.removeElement(selectedGoal); // Remove selected goal from model
                }
            }
        });
        btnDeleteGoal.setBounds(61, 344, 108, 23);
        contentPane.add(btnDeleteGoal);

        // Get goals from BankSystem and populate JList
        List<SavingsGoal> goals = BankSystem.getInstance().getLoginAccount().getSavingsGoals();
        DefaultListModel<SavingsGoal> model = new DefaultListModel<>();
        for (SavingsGoal goal : goals) {
            model.addElement(goal);
        }
        list.setModel(model);
    }
}