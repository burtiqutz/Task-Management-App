package businessLogic;

import dataModel.Employee;
import dataModel.Task;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utility {
    public static void filterAndSortEmployees(TasksManagement tasksManagement) {
        tasksManagement.getTasks().entrySet().stream()  //  Get stream to make filtering&sorting easier
                .filter(entry -> tasksManagement.calculateEmployeeWork(entry.getKey().getIdEmployee()) > 40)
                .sorted(Comparator.comparingInt(entry -> tasksManagement.calculateEmployeeWork(entry.getKey().getIdEmployee())))
                .forEach(entry -> System.out.println(entry.getKey().getName()));
    }

    public static Map<String, Map<String, Integer>> countTasksByStatus(TasksManagement tasksManagement) {
        Map<String, Map<String, Integer>> result = new HashMap<>();

        for (Map.Entry<Employee, List<Task>> entry : tasksManagement.getTasks().entrySet()) {
            Employee employee = entry.getKey();
            List<Task> taskList = entry.getValue();

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
