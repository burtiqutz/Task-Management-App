package userInterface;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.awt.*;

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


    Controller controller = new Controller(this);

    public View(String name) {
        super(name);
        this.prepareGui();
    }

    public void prepareGui() {
        this.setSize(600, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //this.contentPane = new JPanel(new GridLayout(0, 1));
        this.contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        this.prepareEmployeePanel();
        this.prepareTaskPanel();
        this.prepareTreePanel();
        this.prepareWorkHoursPanel();
        this.prepareStatusPanel();
        this.setContentPane(this.contentPane);
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

        JButton switchToSimpleTaskButton = new JButton("Show Simple Task");
        switchToSimpleTaskButton.addActionListener(e -> cardLayout.show(taskPanel, "Simple Task"));

        JButton switchToComplexTaskButton = new JButton("Show Complex Task");
        switchToComplexTaskButton.addActionListener(e -> cardLayout.show(taskPanel, "Complex Task"));

        JPanel switchButtonPanel = new JPanel();
        switchButtonPanel.add(switchToSimpleTaskButton);
        switchButtonPanel.add(switchToComplexTaskButton);

        this.contentPane.add(switchButtonPanel, BorderLayout.NORTH);

        this.contentPane.add(this.taskPanel, BorderLayout.CENTER);

        this.taskPanel.setPreferredSize(new Dimension(600, 25));
        this.taskPanel.setMaximumSize(new Dimension(600, 25));
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
        DefaultMutableTreeNode taskNode = new DefaultMutableTreeNode(newTaskType + ": " + newTaskDetails);
        employeeNode.add(taskNode);
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
}
