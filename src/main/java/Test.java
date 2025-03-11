import businessLogic.TasksManagement;
import businessLogic.Utility;
import dataModel.ComplexTask;
import dataModel.Employee;
import dataModel.SimpleTask;
import dataModel.Task;
import kotlin.collections.ArrayDeque;

import java.io.*;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TasksManagement tasksManagement = new TasksManagement();
        SimpleTask simpleTask = new SimpleTask(1, "Completed", 0, 20);
        SimpleTask simpleTask2 = new SimpleTask(2, "Completed", 0, 25);
        ComplexTask complexTask = new ComplexTask(3, "Uncompleted");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(simpleTask2);
        tasks.add(simpleTask);
        complexTask.setTasks(tasks);
        Employee employee = new Employee(1, "Ion");

        tasksManagement.addEmployee(employee);
        tasksManagement.assignTaskToEmployee(1, simpleTask);
        tasksManagement.assignTaskToEmployee(1, simpleTask2);
        tasksManagement.assignTaskToEmployee(1, complexTask);

        FileOutputStream fos = new FileOutputStream("test_file.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(tasksManagement);
        System.out.println("serialzed obketasdfasdf");
        oos.close();
        fos.close();

        FileInputStream fis = new FileInputStream("test_file.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        TasksManagement tasksManagement2 = (TasksManagement) ois.readObject();
        System.out.println("output after deserialization");
        ois.close();
        fis.close();
        tasksManagement2.printTasks();
        System.out.println("Sorting employees:");
        System.out.println(Utility.filterAndSortEmployees(tasksManagement2));
    }
}
