package dataAccess;

import businessLogic.TasksManagement;

import java.io.*;

public class SerializationOperations {
    private TasksManagement tasksManagement;

    public SerializationOperations(TasksManagement tasksManagement) {
        this.tasksManagement = tasksManagement;
    }

    public void serialize() throws IOException {
        try (FileOutputStream fos = new FileOutputStream("data.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(tasksManagement);
            System.out.println("Serialized tasksManagement");
        }
    }

    public static TasksManagement deserialize() throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream("data.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            TasksManagement restoredTasksManagement = (TasksManagement) ois.readObject();
            System.out.println("Deserialized tasksManagement");
            return restoredTasksManagement;
        }
    }
}
