package businessLogic;

import dataModel.Employee;
import dataModel.Task;

import java.io.Serializable;
import java.util.*;

public class TasksManagement implements Serializable {
    private Map<Employee, List<Task>> tasks;

    public TasksManagement() {
        this.tasks = new HashMap<>();
    }

    public Map<Employee, List<Task>> getTasks() {
        return tasks;
    }

    public void setTasks(Map<Employee, List<Task>> tasks) {
        this.tasks = tasks;
    }

    public void addEmployee(Employee employee) {
        this.tasks.put(employee, new ArrayList<>());
    }

    public void assignTaskToEmployee(int idEmployee, Task task) {
        for(Employee employee : tasks.keySet()) {
            if(employee.getIdEmployee() == idEmployee) {
                //  I think this is redundant
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

    public Task getTask(int idTask) {
        for(Employee employee : tasks.keySet()) {
            for(Task task : tasks.get(employee)) {
                if(task.getIdTask() == idTask) {
                    return task;
                }
            }
        }
        return null;
    }

    public int getEmployeeId (String employeeName) {
        for(Employee employee : tasks.keySet()) {
            if(employee.getName().equals(employeeName)) {
                return employee.getIdEmployee();
            }
        }
        return -1;
    }

    public Employee getEmployee(String employeeName) {
        for(Employee employee : tasks.keySet()) {
            if(employee.getName().equals(employeeName)) {
                return employee;
            }
        }
        return null;
    }

    public Employee getEmployeeByTaskId(int taskID) {
        for(Employee employee : tasks.keySet()) {
            for(Task task : tasks.get(employee)) {
                if(task.getIdTask() == taskID) {
                    return employee;
                }
            }
        }
        return null;
    }

    //  Used for testing
    public void printTasks() {
        System.out.println("--------------dumping tasks------------");
        for(Employee employee : tasks.keySet()) {
            System.out.println("Employee Name: " + employee.getName() + ", Employee ID: " + employee.getIdEmployee());
            for(Task task : tasks.get(employee)) {
                System.out.println("Task ID: " + task.getIdTask());
                System.out.println("Task Status: " + task.getStatusTask());
            }
        }
    }
}
