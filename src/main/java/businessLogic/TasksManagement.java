package businessLogic;

import dataModel.Employee;
import dataModel.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TasksManagement {
    private Map<Employee, List<Task>> tasks;

    public TasksManagement(Map<Employee, List<Task>> tasks) {
        this.tasks = tasks;
    }

    public void assignTaskToEmployee(int idEmployee, Task task) {
        List<Task> taskList = tasks.get(idEmployee);
    }
}
