import javax.swing.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.Collections;

public class Display {

    public static void runDisplay() {
        System.out.println("Ran display");
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Simple Display");
        JButton button = new JButton("Click Me");

        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("Button clicked!");
            }
        });

        frame.add(button);

        frame.setSize(200, 100);

        frame.setVisible(true);

    }

}