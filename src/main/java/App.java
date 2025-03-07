import userInterface.View;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        JFrame frame = new View("Hello World");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
