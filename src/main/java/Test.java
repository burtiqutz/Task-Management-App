import businessLogic.TasksManagement;
import dataModel.Employee;
import dataModel.SimpleTask;

public class Test {
    public static void main(String[] args) {
        TasksManagement tasksManagement = new TasksManagement();
        SimpleTask simpleTask = new SimpleTask(1, "uncompleted", 12, 15);
        SimpleTask simpleTask2 = new SimpleTask(1, "completed", 12, 15);
        Employee employee = new Employee(1, "Ion");
        tasksManagement.addEmployee(employee);
        tasksManagement.assignTaskToEmployee(1, simpleTask);
        tasksManagement.assignTaskToEmployee(2, simpleTask2);
        tasksManagement.printTasks();
    }
}
