package ui;

import model.AdoptableBunnies;
import model.AdoptionProfile;
import model.OwnedBunnies;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


//runs BunnyAdoptionGUI
public class BunnyAdoptionGUI implements ActionListener {
    public static final int FRAMEWIDTH = 400;
    public static final int FRAMEHEIGHT = 400;

    private static final String JSON_STORE = "./data/adoptionProfile.json";
    private AdoptionProfile adoptionProfile;
    private AdoptableBunnies adoptableBunnies;
    private OwnedBunnies ownedBunnies;
    private Scanner scan;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    private JButton newProfile;
    private JButton loadProfile;
    private JButton nextButton;

    private JLabel nameLabel;
    private JFrame createProfileFrame;
    private JPanel createProfilePanel;
    private JTextField nameTextField;

    public BunnyAdoptionGUI() {
        scan = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        menuGUI();
    }

    public void menuGUI() {
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


    public void setUpButtonListeners() {
        ActionListener newProfileListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createProfileGUI();
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

    public void createProfileGUI() {
        panel.remove(newProfile);
        panel.remove(loadProfile);
        panel.revalidate();
        panel.repaint();

        nameTextField = new JTextField(20);
        nameTextField.setBounds(100, 100, 150, 25);
        nameTextField.setVisible(true);
        panel.add(nameTextField);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(60, 100, 80, 25);
        nameLabel.setVisible(true);
        panel.add(nameLabel);

        nextButton = new JButton("Next");
        nextButton.setBounds(100, 150, 150, 25);
        panel.add(nextButton);
        nextListener();
    }

    public void nextListener() {
        ActionListener nextListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(nameLabel);
                panel.remove(nameTextField);
                panel.remove(nextButton);
                panel.revalidate();
                panel.repaint();

                String userName = nameTextField.getText();
                adoptionProfile = new AdoptionProfile(userName);

                nameLabel = new JLabel("Hello " + userName + "!");
                nameLabel.setBounds(60, 100, 80, 25);
                nameLabel.setVisible(true);
                panel.add(nameLabel);
            }
        };
        nextButton.addActionListener(nextListener);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

