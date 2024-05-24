package view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.BankSystem;
import model.Task;

public class ParentPage extends JFrame {

    private JPanel contentPane;
    private JTextField textReward;
    private JTextField textDescription;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ParentPage frame = new ParentPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ParentPage() {
        setTitle("Parent");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 503);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTasks_1 = new JLabel("Inspection Tasks");
        lblTasks_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblTasks_1.setFont(new Font("Arial", Font.PLAIN, 35));
        lblTasks_1.setBounds(39, 0, 337, 101);
        contentPane.add(lblTasks_1);

        // Create a JScrollPane and add the JList to it
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(37, 93, 357, 165);
        contentPane.add(scrollPane);

        JList<Task> list = new JList<Task>();
        scrollPane.setViewportView(list);

        // Populate JList with tasks from BankSystem
        DefaultListModel<Task> taskListModel = new DefaultListModel<>();
        for (Task task : BankSystem.getInstance().getTasks()) {
            taskListModel.addElement(task);
        }
        list.setModel(taskListModel);

        JLabel lblReward = new JLabel("Reward:");
        lblReward.setHorizontalAlignment(SwingConstants.RIGHT);
        lblReward.setBounds(36, 295, 132, 15);
        contentPane.add(lblReward);

        textReward = new JTextField();
        textReward.setColumns(10);
        textReward.setBounds(178, 292, 153, 21);
        contentPane.add(textReward);

        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescription.setBounds(60, 332, 108, 15);
        contentPane.add(lblDescription);

        textDescription = new JTextField();
        textDescription.setColumns(10);
        textDescription.setBounds(178, 329, 153, 21);
        contentPane.add(textDescription);

        JButton btnDeleteTask = new JButton("Delete Task");
        btnDeleteTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Task selectedTask = list.getSelectedValue();
                if (selectedTask != null) {
                    BankSystem.getInstance().deleteTask(selectedTask);
                    taskListModel.removeElement(selectedTask);
                }
            }
        });
        btnDeleteTask.setBounds(91, 378, 121, 23);
        contentPane.add(btnDeleteTask);

        JButton btnAddTask = new JButton("Add Task");
        btnAddTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double reward = Double.parseDouble(textReward.getText());
                String description = textDescription.getText();
                Task newTask = new Task(description, reward);
                BankSystem.getInstance().addTask(newTask);
                taskListModel.addElement(newTask);
                textReward.setText("");
                textDescription.setText("");
            }
        });
        btnAddTask.setBounds(251, 378, 93, 23);
        contentPane.add(btnAddTask);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancel.setBounds(251, 420, 93, 23);
        contentPane.add(btnCancel);
        
        JButton btnNewButton = new JButton("Complete Task");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                Task selectedTask = list.getSelectedValue();
                if (selectedTask != null) {
                    double reward = selectedTask.getReward();
                    BankSystem.getInstance().setMoney(BankSystem.getInstance().getMoney() + reward);
                    JOptionPane.showMessageDialog(null, "Task Completed! Child has earned $" + reward + ".");
                }
            }
        });
        btnNewButton.setBounds(91, 420, 121, 23);
        contentPane.add(btnNewButton);
    }
}
