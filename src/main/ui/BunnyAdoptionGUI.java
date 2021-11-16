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
    private String lineOfBunnies;

    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    private JButton newProfile;
    private JButton loadProfile;
    private JButton nextButton;
    private JButton yesButton;
    private JButton noButton;

    private JLabel nameLabel;
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

                nameLabel = new JLabel("Hello " + userName + "!\nWould you like to view the adoptable bunnies?");
                nameLabel.setBounds(60, 100, 200, 25);
                panel.add(nameLabel);

                yesButton = new JButton("Yes");
                yesButton.setBounds(100, 150, 150, 25);
                panel.add(yesButton);
                setupYesListener();

                noButton = new JButton("No");
                noButton.setBounds(200, 150, 150, 25);
                panel.add(noButton);
                setupNoListener();
            }
        };
        nextButton.addActionListener(nextListener);
    }

    public void setupYesListener() {
        ActionListener yesListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seeBunnyShop();
            }
        };
        yesButton.addActionListener(yesListener);
    }

    public void seeBunnyShop() {
        panel.remove(yesButton);
        panel.remove(noButton);
        panel.remove(nameLabel);
        panel.revalidate();
        panel.repaint();
        adoptableBunnies = new AdoptableBunnies();
        if (adoptableBunnies.getAdoptableBunniesList().size() == 0) {
            JLabel noBunniesLabel = new JLabel("Sorry, there are no more bunnies left to adopt!");
            noBunniesLabel.setBounds(100, 100, 150, 25);
            panel.add(noBunniesLabel);
        }
        int i = 0;
        for (Bunny b : adoptableBunnies.getAdoptableBunniesList()) {
            lineOfBunnies = b.displayBunny();
            JLabel adoptableBunniesLabel = new JLabel(lineOfBunnies);
            adoptableBunniesLabel.setBounds(100, 100 + i, 1000, 25);
            panel.add(adoptableBunniesLabel);
            i += 20;
        }
    }

    public void setupNoListener() {
        ActionListener noListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quit();
            }
        };
        noButton.addActionListener(noListener);
    }

    public void quit() {
        panel.remove(yesButton);
        panel.remove(noButton);
        panel.remove(nameLabel);
        panel.revalidate();
        panel.repaint();
        JLabel quitLabel = new JLabel("Thanks for dropping by!");
        quitLabel.setBounds(100,100,200,25);
        panel.add(quitLabel);
    }

//    class yourClass extends JPanel { //yourClass is the JPanel
//        @Override //if you aren't overriding correctly this makes the compiler tell you
//        protected void paintComponent(Graphics gr){
//            super.paintComponent(gr);
//            gr.drawString("string literal or a string variable", 0,10);
//        }
//    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

