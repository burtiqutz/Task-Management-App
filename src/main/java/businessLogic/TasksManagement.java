package businessLogic;

import dataModel.Employee;
import dataModel.Task;

import java.util.*;

public class TasksManagement {
    private Map<Employee, List<Task>> tasks;

    public TasksManagement(Map<Employee, List<Task>> tasks) {
        this.tasks = tasks;
    }

    public void assignTaskToEmployee(int idEmployee, Task task) {
        for(Employee employee : tasks.keySet()) {
            if(employee.getIdEmployee() == idEmployee) {
                tasks.computeIfAbsent(employee, k -> new ArrayList<Task>());    //  Creates entry for employee if absent
                tasks.get(employee).add(task);
            }
        }
    }
    public int calculateEmployeeWork (int idEmployee) {
        int totalDuration = 0;
        //  Find the employee according to the id
        for(Employee employee : tasks.keySet()) {
            if(employee.getIdEmployee() == idEmployee) {
               //  If found, compute total work time
                for(Task task : tasks.get(employee)) {
                    if(task.getStatusTask().equals("Completed")) {
                        totalDuration += task.estimateDuration();
                    }
                }
            }
        }
        return totalDuration;
    }
    public void modifyTaskStatus(int idEmployee, int idTask) {
        for(Employee employee : tasks.keySet()) {
            if(employee.getIdEmployee() == idEmployee) {
                for(Task task : tasks.get(employee)) {
                    if(task.getIdTask() == idTask) {
                        //  Or modify status to boolean and change in GUI
                        if(task.getStatusTask().equals("Completed")) {
                            task.setStatusTask("Uncompleted");
                        } else {
                            task.setStatusTask("Completed");
                        }
                    }
                }
            }
        }
    }
}
