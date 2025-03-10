package userInterface;


import businessLogic.TasksManagement;
import dataAccess.SerializationOperations;
import dataModel.Employee;
import dataModel.Task;
import org.jetbrains.annotations.NotNull;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.IOException;

public class View extends JFrame {
    private JPanel contentPane;

    //  Employee panel variables
    private JPanel employeePanel;
    private JLabel employeeLabel;
    private JTextField employeeTextField;
    private JButton addEmployeeButton;

    //  Tasks panel variables
    private JPanel taskPanel;
    private CardLayout cardLayout;

    //  Simple task
    private JPanel simpleTaskPanel;
    private JButton simpleTaskButton;
    private JLabel simpleTaskLabel;
    private JTextField simpleTaskTextField;             //  Task id
    private JTextField simpleTaskEmployeeTextField;     //  Employee name
    private JTextField simpleTaskStartTextField;
    private JTextField simpleTaskEndTextField;

    //  Complex task
    private JPanel complexTaskPanel;
    private JButton complexTaskButton;
    private JLabel complexTaskLabel;
    private JTextField complexTaskTextField;            //  Task id
    private JTextField complexTaskTaskListTextField;    //  Task list components
    private JTextField complexTaskEmployeeTextField;    //  Employee name

    //  Tree panel
    private JPanel treePanel;
    private JTree taskTree;
    private DefaultMutableTreeNode rootNode;
    private DefaultTreeModel treeModel;

    //  Work Hours panel
    private JPanel workHoursPanel;
    private JTextField employeeNameForWorkHoursTextField;
    private JButton showWorkHoursButton;
    private JLabel totalWorkHoursLabel;

    //  Modify task hours panel
    private JPanel statusPanel;
    private JLabel statusLabel;
    private JTextField statusIdTextField;
    private JButton changeStatusButton;

    //  Utility variables
    private JFrame statisticsFrame;
    private JFrame sortingFrame;
    private JPanel utilityPanel;
    private JButton sortingButton;
    private JButton statisticsButton;

    Controller controller = new Controller(this);
    private SerializationOperations serializationOperations;

    public View(String name) throws IOException, ClassNotFoundException {
        super(name);
        this.prepareGui();
        this.serializationOperations = new SerializationOperations(controller.getTasksManagement());
        this.updateTreeFromTasksManagement(controller.getTasksManagement());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Call serialize to save data when the window is closing
                try {
                    serializationOperations.serialize();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    // Handle the exception (show an error message, etc.)
                }
            }
        });
    }

    private void prepareGui() {
        this.setSize(600, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        try{UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch (Exception e){
            e.printStackTrace();
        }


        //this.contentPane = new JPanel(new GridLayout(0, 1));
        this.contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        this.prepareEmployeePanel();
        this.prepareTaskPanel();
        this.prepareTreePanel();
        this.prepareWorkHoursPanel();
        this.prepareStatusPanel();
        this.prepareUtilityPanel();

        this.setContentPane(this.contentPane);
    }

    private void prepareUtilityPanel() {
        this.utilityPanel = new JPanel();
        this.utilityPanel.setLayout(new BoxLayout(utilityPanel, BoxLayout.X_AXIS));

        this.sortingButton = new JButton("Sort Employees");
        this.sortingButton.addActionListener(controller);
        this.sortingButton.setActionCommand("SORT_EMPLOYEE");

        this.statisticsButton = new JButton("Show Statistics");
        this.statisticsButton.addActionListener(controller);
        this.statisticsButton.setActionCommand("SHOW_STATISTICS");

        this.utilityPanel.add(this.sortingButton);
        this.utilityPanel.add(this.statisticsButton);
        this.contentPane.add(this.utilityPanel);
    }


    private void prepareEmployeePanel() {
        this.employeePanel = new JPanel();
        //this.employeePanel.setLayout(new GridLayout(1, 3));
        this.employeePanel.setLayout(new BoxLayout(employeePanel, BoxLayout.X_AXIS));
        this.employeeLabel = new JLabel("Employee Name");
        this.employeeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.employeeTextField = new JTextField();
        this.employeeTextField.setMaximumSize(new Dimension(150, 25));

        this.addEmployeeButton = new JButton("Add Employee");
        this.addEmployeeButton.addActionListener(this.controller);
        this.addEmployeeButton.setActionCommand("ADD_EMPLOYEE");
        this.addEmployeeButton.setHorizontalAlignment(SwingConstants.CENTER);

        this.employeePanel.add(Box.createHorizontalStrut(15));
        this.employeePanel.add(this.employeeLabel);
        this.employeePanel.add(Box.createHorizontalStrut(15));
        this.employeePanel.add(this.employeeTextField);
        this.employeePanel.add(Box.createHorizontalStrut(15));
        this.employeePanel.add(this.addEmployeeButton);
        this.contentPane.add(this.employeePanel);
    }

    private void prepareTaskPanel() {
        // Create the panel with CardLayout
        this.taskPanel = new JPanel();
        this.cardLayout = new CardLayout();  // Initialize the CardLayout
        this.taskPanel.setLayout(cardLayout);  // Set the layout of the taskPanel to CardLayout

        // Simple Task Panel
        this.simpleTaskPanel = new JPanel();
        //this.simpleTaskPanel.setLayout(new GridLayout(1, 0));
        this.simpleTaskPanel.setLayout(new BoxLayout(this.simpleTaskPanel, BoxLayout.X_AXIS));
        this.simpleTaskButton = new JButton("Add Task");
        this.simpleTaskLabel = new JLabel("Simple Task");
        this.simpleTaskTextField = new JTextField("1");
        this.simpleTaskEmployeeTextField = new JTextField("Ion");
        this.simpleTaskStartTextField = new JTextField("12");
        this.simpleTaskEndTextField = new JTextField("15");

        this.simpleTaskButton.setPreferredSize(new Dimension(150, 25));
        this.simpleTaskLabel.setPreferredSize(new Dimension(150, 25));
        this.simpleTaskTextField.setPreferredSize(new Dimension(150, 25));
        this.simpleTaskEmployeeTextField.setPreferredSize(new Dimension(150, 25));
        this.simpleTaskStartTextField.setPreferredSize(new Dimension(150, 25));
        this.simpleTaskEndTextField.setPreferredSize(new Dimension(150, 25));

        this.simpleTaskButton.addActionListener(this.controller);
        this.simpleTaskButton.setActionCommand("ADD_SIMPLE_TASK");
        this.simpleTaskPanel.add(this.simpleTaskLabel);
        this.simpleTaskPanel.add(this.simpleTaskEmployeeTextField);
        this.simpleTaskPanel.add(this.simpleTaskTextField);
        this.simpleTaskPanel.add(this.simpleTaskStartTextField);
        this.simpleTaskPanel.add(this.simpleTaskEndTextField);
        this.simpleTaskPanel.add(this.simpleTaskButton);

        // Complex Task Panel
        this.complexTaskPanel = new JPanel();
        //this.complexTaskPanel.setLayout(new GridLayout(1, 0));
        this.complexTaskPanel.setLayout(new BoxLayout(this.complexTaskPanel, BoxLayout.X_AXIS));
        this.complexTaskLabel = new JLabel("Complex Task");
        this.complexTaskTextField = new JTextField("1");
        this.complexTaskButton = new JButton("Add Task");
        this.complexTaskEmployeeTextField = new JTextField("Ion");
        this.complexTaskTaskListTextField = new JTextField("1 2 3");

        this.complexTaskButton.setPreferredSize(new Dimension(150, 25));
        this.complexTaskLabel.setPreferredSize(new Dimension(100, 25));
        this.complexTaskEmployeeTextField.setPreferredSize(new Dimension(150, 25));
        this.complexTaskTextField.setPreferredSize(new Dimension(150, 25));
        this.complexTaskTextField.setMaximumSize(new Dimension(150, 25));
        this.complexTaskTaskListTextField.setPreferredSize(new Dimension(150, 25));
        this.complexTaskTaskListTextField.setMaximumSize(new Dimension(150, 25));

        this.complexTaskButton.addActionListener(this.controller);
        this.complexTaskButton.setActionCommand("ADD_COMPLEX_TASK");
        this.complexTaskPanel.add(this.complexTaskLabel);  // Label
        this.complexTaskPanel.add(this.complexTaskEmployeeTextField);  // Employee name
        this.complexTaskPanel.add(this.complexTaskTextField);  // Task id
        this.complexTaskPanel.add(this.complexTaskTaskListTextField);
        this.complexTaskPanel.add(this.complexTaskButton);

        this.taskPanel.add(this.simpleTaskPanel, "Simple Task");
        this.taskPanel.add(this.complexTaskPanel, "Complex Task");

        JPanel switchButtonPanel = getSwitchButtonPanel();

        this.contentPane.add(switchButtonPanel, BorderLayout.NORTH);

        this.contentPane.add(this.taskPanel, BorderLayout.CENTER);

        this.taskPanel.setPreferredSize(new Dimension(600, 25));
        this.taskPanel.setMaximumSize(new Dimension(600, 25));
    }

    @NotNull
    private JPanel getSwitchButtonPanel() {
        JButton switchToSimpleTaskButton = new JButton("Show Simple Task");
        switchToSimpleTaskButton.addActionListener(e -> cardLayout.show(taskPanel, "Simple Task"));

        JButton switchToComplexTaskButton = new JButton("Show Complex Task");
        switchToComplexTaskButton.addActionListener(e -> cardLayout.show(taskPanel, "Complex Task"));

        JPanel switchButtonPanel = new JPanel();
        switchButtonPanel.add(switchToSimpleTaskButton);
        switchButtonPanel.add(switchToComplexTaskButton);
        return switchButtonPanel;
    }

    private void prepareTreePanel() {
        this.treePanel = new JPanel(new BorderLayout());

        // Create the root node
        rootNode = new DefaultMutableTreeNode("Tasks");

        // Set up the tree model and JTree
        treeModel = new DefaultTreeModel(rootNode);
        taskTree = new JTree(treeModel);

        // Add the tree to a scroll pane
        JScrollPane scrollPane = new JScrollPane(taskTree);
        this.treePanel.add(scrollPane, BorderLayout.CENTER);

        this.contentPane.add(this.treePanel);
    }

    private void prepareWorkHoursPanel() {
        workHoursPanel = new JPanel();
        workHoursPanel.setLayout(new BoxLayout(workHoursPanel, BoxLayout.X_AXIS));

        //  Employee name
        JLabel employeeNameLabel = new JLabel("Employee Name:");
        this.employeeNameForWorkHoursTextField = new JTextField();
        this.employeeNameForWorkHoursTextField.setMaximumSize(new Dimension(150, 25));

        //  Button
        this.showWorkHoursButton = new JButton("Show Total Work Hours");
        this.showWorkHoursButton.addActionListener(this.controller);
        this.showWorkHoursButton.setActionCommand("SHOW_TOTAL_WORK_HOURS");

        this.totalWorkHoursLabel = new JLabel("Total Work Hours: ");

        // Add components to the panel
        workHoursPanel.add(employeeNameLabel);
        workHoursPanel.add(this.employeeNameForWorkHoursTextField);
        workHoursPanel.add(this.showWorkHoursButton);
        workHoursPanel.add(this.totalWorkHoursLabel);

        this.contentPane.add(workHoursPanel);  // Add panel to the content pane
    }

    private void prepareStatusPanel() {
        statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));

        statusLabel = new JLabel("TaskID:   ");
        statusIdTextField = new JTextField("1");
        changeStatusButton = new JButton("Change Status");
        changeStatusButton.addActionListener(this.controller);
        changeStatusButton.setActionCommand("CHANGE_STATUS");

        statusPanel.add(statusLabel);
        statusPanel.add(statusIdTextField);
        statusPanel.add(changeStatusButton);
        this.contentPane.add(statusPanel);
    }

    public void updateEmployeeTree(String employeeName, String taskID, String newTaskType, String newTaskDetails) {
        DefaultMutableTreeNode employeeNode = findOrCreateEmployeeNode(employeeName);
        //  Check if employee has task assigned
        if(taskID != null && !taskID.isEmpty()) {
            DefaultMutableTreeNode taskNode = new DefaultMutableTreeNode(newTaskType + ": " + newTaskDetails);
            employeeNode.add(taskNode);
        }

        treeModel.reload();
    }

    private DefaultMutableTreeNode findOrCreateEmployeeNode(String employeeName) {
        for (int i = 0; i < rootNode.getChildCount(); i++) {
            DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) rootNode.getChildAt(i);
            if (childNode.toString().equals(employeeName)) {
                return childNode;
            }
        }
        // If the employee is not found, create a new one
        DefaultMutableTreeNode employeeNode = new DefaultMutableTreeNode(employeeName);
        rootNode.add(employeeNode);

        return employeeNode;
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public JTextField getSimpleTaskTextField() {
        return simpleTaskTextField;
    }

    public JTextField getSimpleTaskEmployeeTextField() {
        return simpleTaskEmployeeTextField;
    }

    public JTextField getEmployeeTextField() {
        return this.employeeTextField;
    }

    public JTextField getComplexTaskTextField() {
        return complexTaskTextField;
    }

    public JTextField getComplexTaskTaskListTextField() {
        return complexTaskTaskListTextField;
    }

    public JTextField getComplexTaskEmployeeTextField() {
        return complexTaskEmployeeTextField;
    }

    public JTextField getSimpleTaskStartTextField() {
        return simpleTaskStartTextField;
    }

    public JTextField getSimpleTaskEndTextField() {
        return simpleTaskEndTextField;
    }

    public JTextField getEmployeeNameForWorkHoursTextField() {
        return employeeNameForWorkHoursTextField;
    }

    public void setTotalWorkHoursLabel(String totalWorkHoursString) {
        this.totalWorkHoursLabel.setText(totalWorkHoursString);
    }

    public JTextField getStatusIdTextField() {
        return statusIdTextField;
    }

    public void updateTreeFromTasksManagement(TasksManagement tasksManagement) {
        // Clear the existing tree nodes
        rootNode.removeAllChildren();

        // Get the tasks map from the TasksManagement object
        Map<Employee, List<Task>> tasks = controller.getTasksManagement().getTasks();

        // Iterate through employees and their assigned tasks
        for (Employee employee : tasks.keySet()) {
            DefaultMutableTreeNode employeeNode = new DefaultMutableTreeNode(employee.getName());

            // For each task assigned to the employee, create a task node
            for (Task task : tasks.get(employee)) {
                DefaultMutableTreeNode taskNode = new DefaultMutableTreeNode(
                        "Task ID: " + task.getIdTask() + " | Status: " + task.getStatusTask());
                employeeNode.add(taskNode);
            }

            // Add the employee node to the root node
            rootNode.add(employeeNode);
        }

        // Notify the tree model that the tree has changed
        treeModel.reload();
    }

}
