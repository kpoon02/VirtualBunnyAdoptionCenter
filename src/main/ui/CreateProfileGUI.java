package ui;

import javax.swing.*;
import java.awt.*;

public class CreateProfileGUI {
    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    private JTextField textField;

    public CreateProfileGUI() {
        frame = new JFrame();
        frame.setSize(MenuGUI.FRAMEWIDTH, MenuGUI.FRAMEHEIGHT);

        panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Bunny Adoption Application");
        frame.setVisible(true);

        textField = new JTextField(20);
        textField.setBounds(100,100,80,25);
        textField.setVisible(true);
        panel.add(textField);

        label = new JLabel("Name:");
        label.setBounds(60,100,80,25);
        label.setVisible(true);
        panel.add(label);
    }

    public static void main(String[] args) {
        new CreateProfileGUI();
    }
}
