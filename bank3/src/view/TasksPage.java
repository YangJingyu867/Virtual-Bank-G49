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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class TasksPage extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TasksPage frame = new TasksPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TasksPage() {
        setTitle("Tasks");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 342);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTasks = new JLabel("Tasks");
        lblTasks.setHorizontalAlignment(SwingConstants.CENTER);
        lblTasks.setFont(new Font("Arial", Font.PLAIN, 35));
        lblTasks.setBounds(35, -14, 337, 101);
        contentPane.add(lblTasks);

        // Create a JScrollPane and add the JList to it
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(35, 69, 357, 165);
        contentPane.add(scrollPane);

        JList<Task> list = new JList<Task>();
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
        List<Task> tasks = BankSystem.getInstance().getTasks();
        DefaultListModel<Task> model = new DefaultListModel<>();
        for (Task task : tasks) {
            model.addElement(task);
        }
        list.setModel(model);
    }
}

