package userInterface;

import businessLogic.TasksManagement;
import dataAccess.SerializationOperations;
import dataModel.ComplexTask;
import dataModel.Employee;
import dataModel.SimpleTask;
import dataModel.Task;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller implements ActionListener {
    private View view;
    private TasksManagement tasksManagement;
    private int idEmployee = -1;

    public Controller(View v) throws IOException, ClassNotFoundException {
        this.view = v;
        this.tasksManagement = SerializationOperations.deserialize();
        //this.tasksManagement = new TasksManagement();
    }


    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "ADD_EMPLOYEE":
                this.handleAddEmployee();
                break;
            case "ADD_SIMPLE_TASK":
                this.handleAddSimpleTask();
                break;
            case "ADD_COMPLEX_TASK":
                this.handleAddComplexTask();
                break;
            case "SHOW_TOTAL_WORK_HOURS":
                this.handleShowWorkHours();
                break;
            case "CHANGE_STATUS":
                this.handleChangeStatus();
                break;
            default:
                System.out.println("Invalid command");
        }
    }

    private void handleChangeStatus() {
        String taskID = view.getStatusIdTextField().getText().trim();

        //  Find task
        Task task = tasksManagement.getTask(Integer.parseInt(taskID));
        if(task == null){
            view.showError("Task not found");
            System.out.println("Task not found");
            return;
        }

        Employee employee = tasksManagement.getEmployeeByTaskId(Integer.parseInt(taskID));
        tasksManagement.modifyTaskStatus(employee.getIdEmployee(), Integer.parseInt(taskID));
        String curStatus = view.getStatusIdTextField().getText().trim();
        String newStatus = curStatus.equals("Completed") ? "Uncompleted" : "Completed";
        view.updateTaskStatus(taskID, newStatus);
        System.out.println("Task status updated, id: " + taskID);
    }

    private void handleShowWorkHours() {
        String employeeName = view.getEmployeeNameForWorkHoursTextField().getText().trim();
        if (employeeName.isEmpty()) {
            System.out.println("Employee name cannot be empty!");
            view.showError("Employee name cannot be empty!");
            return;
        }
        Employee employee = tasksManagement.getEmployee(employeeName);
        if(employee==null){
            System.out.println("Employee not found");
            view.showError("Employee not found");
            return;
        }
        int totalHours = 0;
        totalHours = tasksManagement.calculateEmployeeWork(tasksManagement.getEmployeeId(employeeName));
        view.setTotalWorkHoursLabel("Total Work Hours: " + totalHours);
    }

    private void handleAddEmployee() {
        String employeeName = view.getEmployeeTextField().getText().trim();

        if (employeeName.isEmpty()) {
            System.out.println("Employee name cannot be empty!");
            view.showError("Employee name cannot be empty!");
            return;
        }

        //  Find employee or create him
        Employee employee = tasksManagement.getEmployee(employeeName);
        if(employee != null){
            System.out.println("Employee already exists!");
            view.showError("Employee already exists");
            return;
        }

        employee = new Employee(++idEmployee, employeeName);
        tasksManagement.addEmployee(employee);

        // TODO: implement serialization
        System.out.println("Added Employee: " + employeeName);

        view.updateEmployeeTree(employee, null);
    }

    private void handleAddSimpleTask() {
        String employeeName = view.getSimpleTaskEmployeeTextField().getText().trim();
        String taskID = view.getSimpleTaskTextField().getText().trim();
        int startHour = Integer.parseInt(view.getSimpleTaskStartTextField().getText().trim());
        int endHour = Integer.parseInt(view.getSimpleTaskEndTextField().getText().trim());

        if (employeeName.isEmpty()) {
            System.out.println("Employee name cannot be empty!");
            view.showError("Employee name cannot be empty!");
            return;
        }


        String taskDetails = taskID;
        //  Find employee or create him
        Employee employee = tasksManagement.getEmployee(employeeName);
        if(employee==null){
            System.out.println("Employee not found");
            employee = new Employee(++idEmployee, employeeName);
            //  Add employee to table
            tasksManagement.addEmployee(employee);
        }

        //  Check task ID to be unique
        if(tasksManagement.getTask(Integer.parseInt(taskID)) != null){
            System.out.println("Task already exists!");
            view.showError("Task already exists");
            return;
        }
        Task newTask = new SimpleTask(Integer.parseInt(taskID), "Uncompleted", startHour, endHour);
        tasksManagement.assignTaskToEmployee(employee.getIdEmployee(), newTask);
        Task task = tasksManagement.getTask(Integer.parseInt(taskID));

        view.updateEmployeeTree(employee, task);
        System.out.println("Added Simple Task: " + employeeName + " with ID: " + taskID);
        tasksManagement.printTasks();

    }

    private void handleAddComplexTask() {
        String employeeName = view.getComplexTaskEmployeeTextField().getText().trim();
        String taskID = view.getComplexTaskTextField().getText().trim();

        //  Get individual id's for computing complex task
        String[] taskList = view.getComplexTaskTaskListTextField().getText().trim().split("[,\\s]+");

        //  Recreate task list for adding to table
        String formattedTaskIds = String.join(", ", taskList);
        if (employeeName.isEmpty()) {
            System.out.println("Employee name cannot be empty!");
            view.showError("Employee name cannot be empty!");
            return;
        }
        //  TODO: transform into function
        //  Find employee or create him
        Employee employee = tasksManagement.getEmployee(employeeName);
        if(employee==null){
            System.out.println("Employee not found");
            employee = new Employee(++idEmployee, employeeName);
            //  Add employee to table
            tasksManagement.addEmployee(employee);
        }

        //  Check task ID to be unique
        if(tasksManagement.getTask(Integer.parseInt(taskID)) != null){
            System.out.println("Task already exists!");
            view.showError("Task already exists");
            return;
        }
        List<Task> list = new ArrayList<Task>();
        while(tasksManagement.getTask(Integer.parseInt(taskID)) != null){
            list.add(tasksManagement.getTask(Integer.parseInt(taskID)));
        }
        //  TODO i think Create/find task
        ComplexTask task = (ComplexTask) tasksManagement.getTask(Integer.parseInt(taskID));
        if(task==null){
            task = new ComplexTask(Integer.parseInt(taskID), "Uncompleted");
        }
        task.setTasks(list);

        System.out.println("Added Complex Task: " + employeeName + " with ID: " + taskID);

        view.updateEmployeeTree(employee, task);
    }

    public TasksManagement getTasksManagement() {
        return tasksManagement;
    }
}
