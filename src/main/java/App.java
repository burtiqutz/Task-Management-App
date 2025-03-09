import userInterface.View;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        System.out.println("App started");
        JFrame frame = new View("Management Application (please work)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
