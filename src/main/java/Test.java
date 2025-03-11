import businessLogic.TasksManagement;
import dataModel.Employee;
import dataModel.SimpleTask;

import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TasksManagement tasksManagement = new TasksManagement();
        SimpleTask simpleTask = new SimpleTask(1, "uncompleted", 12, 15);
        SimpleTask simpleTask2 = new SimpleTask(2, "completed", 12, 15);
        Employee employee = new Employee(1, "Ion");

        tasksManagement.addEmployee(employee);
        tasksManagement.assignTaskToEmployee(1, simpleTask);
        tasksManagement.assignTaskToEmployee(1, simpleTask2);

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
    }
}
