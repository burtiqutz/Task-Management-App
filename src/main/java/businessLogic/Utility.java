package businessLogic;

import dataModel.Employee;
import dataModel.Task;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utility {
    public static String filterAndSortEmployees(TasksManagement tasksManagement) {
        StringBuilder result = new StringBuilder();

        tasksManagement.getTasks().entrySet().stream()  // Get stream for filtering and sorting
                .filter(entry -> tasksManagement.calculateEmployeeWork(entry.getKey().getIdEmployee()) > 40)  // Filter employees with > 40 hours
                .sorted(Comparator.comparingInt(entry -> tasksManagement.calculateEmployeeWork(entry.getKey().getIdEmployee())))  // Sort by work hours
                .forEach(entry -> result.append(entry.getKey().getName())
                        .append(" - hours worked: ")
                        .append(tasksManagement.calculateEmployeeWork(entry.getKey().getIdEmployee()))
                        .append("\n"));  // Append each employee's name to the result

        return result.toString();  // Return the final string
    }

    public static Map<String, Map<String, Integer>> countTasksByStatus(TasksManagement tasksManagement) {
        Map<String, Map<String, Integer>> result = new HashMap<>();

        //  Iterate through tasksManagement's map of employees & tasks
        for (Map.Entry<Employee, List<Task>> entry : tasksManagement.getTasks().entrySet()) {
            Employee employee = entry.getKey();
            List<Task> taskList = entry.getValue();

            //  Create smaller map for each employee
            Map<String, Integer> statusCount = new HashMap<>();
            statusCount.put("Completed", 0);
            statusCount.put("Uncompleted", 0);

            for (Task task : taskList) {
                statusCount.put(task.getStatusTask(), statusCount.get(task.getStatusTask()) + 1);
            }

            result.put(employee.getName(), statusCount);
        }
        return result;
    }
}
