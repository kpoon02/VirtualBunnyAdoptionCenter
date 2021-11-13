package ui;

import model.AdoptableBunnies;
import model.AdoptionProfile;
import model.Bunny;
import model.OwnedBunnies;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

//runs BunnyAdoptionGUI
public class MenuGUI implements ActionListener {
    public static final int FRAMEWIDTH = 400;
    public static final int FRAMEHEIGHT = 400;

    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    private JButton newProfile;
    private JButton loadProfile;

    public MenuGUI() {
        frame = new JFrame();
        frame.setSize(FRAMEWIDTH, FRAMEHEIGHT);

        panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Bunny Adoption Application");
        frame.setVisible(true);

        label = new JLabel();
        panel.add(label);

        newProfile = new JButton("Create New Profile");
        newProfile.setBounds(100, 100, 150, 25);
        panel.add(newProfile);

        loadProfile = new JButton("Load Profile");
        loadProfile.setBounds(100, 150, 150, 25);
        loadProfile.addActionListener(this);
        panel.add(loadProfile);

        setUpButtonListeners();
    }

    public static void main(String[] args) {
        new MenuGUI();
    }

    public void setUpButtonListeners() {
        ActionListener newProfileListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateProfileGUI();
            }
        };
        newProfile.addActionListener(newProfileListener);
        ActionListener loadProfileListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("load");
            }
        };
        loadProfile.addActionListener(loadProfileListener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
