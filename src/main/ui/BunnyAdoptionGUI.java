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
import java.util.Scanner;


//runs BunnyAdoptionGUI
public class BunnyAdoptionGUI implements ActionListener {
    public static final int FRAMEWIDTH = 500;
    public static final int FRAMEHEIGHT = 500;

    private static final String JSON_STORE = "./data/adoptionProfile.json";
    private AdoptionProfile adoptionProfile;
    private AdoptableBunnies adoptableBunnies;
    private OwnedBunnies ownedBunnies;
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
    private JButton saveButton;
    private JButton adoptButton;

    private JLabel questionLabel;
    private JTextField nameTextField;

    public BunnyAdoptionGUI() throws FileNotFoundException {
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
        newProfile.setBounds(150, 100, 150, 25);
        panel.add(newProfile);

        loadProfile = new JButton("Load Profile");
        loadProfile.setBounds(150, 150, 150, 25);
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
        nameTextField.setBounds(150, 100, 150, 25);
        nameTextField.setVisible(true);
        panel.add(nameTextField);

        questionLabel = new JLabel("Name:");
        questionLabel.setBounds(100, 100, 80, 25);
        questionLabel.setVisible(true);
        panel.add(questionLabel);

        nextButton = new JButton("Next");
        nextButton.setBounds(150, 150, 150, 25);
        panel.add(nextButton);
        nextListener();
    }

    public void nextListener() {
        ActionListener nextListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(questionLabel);
                panel.remove(nameTextField);
                panel.remove(nextButton);
                panel.revalidate();
                panel.repaint();

                String userName = nameTextField.getText();
                adoptionProfile = new AdoptionProfile(userName);

                questionLabel = new JLabel("Hello " + userName + "! Would you like to view the adoptable bunnies?");
                questionLabel.setBounds(80, 100, 500, 25);
                panel.add(questionLabel);

                yesButton = new JButton("Yes");
                yesButton.setBounds(75, 150, 150, 25);
                panel.add(yesButton);
                setupYesListener();

                noButton = new JButton("No");
                noButton.setBounds(225, 150, 150, 25);
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
        panel.removeAll();
        panel.repaint();
        adoptableBunnies = new AdoptableBunnies();
        if (adoptableBunnies.getAdoptableBunniesList().size() == 0) {
            JLabel noBunniesLabel = new JLabel("Sorry, there are no more bunnies left to adopt!");
            noBunniesLabel.setBounds(100, 100, 150, 25);
            panel.add(noBunniesLabel);
        }
        int i = 0;
        int k = 1;
        for (Bunny b : adoptableBunnies.getAdoptableBunniesList()) {
            lineOfBunnies = k + "   " + b.displayBunny();
            JLabel adoptableBunniesLabel = new JLabel(lineOfBunnies);
            adoptableBunniesLabel.setBounds(50, 100 + i, 1000, 25);
            panel.add(adoptableBunniesLabel);
            i += 40;
            k++;
        }
        bunnySelect();
    }

    public void bunnySelect() {
        questionLabel = new JLabel("Enter the number of the bunny you would like to adopt:");
        nameTextField = new JTextField();
        questionLabel.setBounds(50,300,400,25);
        nameTextField.setBounds(50,350,200,25);
        panel.add(questionLabel);
        panel.add(nameTextField);

        nextButton = new JButton("Next");
        nextButton.setBounds(50, 400, 150, 25);
        panel.add(nextButton);
        adoptBunnyListener();

    }

    public void adoptBunnyListener() {
        ActionListener adoptBunnyListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                panel.repaint();
                int bunnyNum = Integer.valueOf(nameTextField.getText());
                Bunny adoptionBunny = adoptableBunnies.getAdoptableBunniesList().get(bunnyNum - 1);
                adoptionProfile.getOwnedBunnies().addBunny(adoptionBunny);
                adoptableBunnies.removeBunny(bunnyNum - 1);
                questionLabel = new JLabel("Here are your adopted bunnies:");
                questionLabel.setBounds(50,100,200,25);
                panel.add(questionLabel);
                int i = 0;
                int k = 1;
                for (Bunny b : adoptionProfile.getOwnedBunnies().getListOfOwnedBunnies()) {
                    lineOfBunnies = k + "   " + b.displayBunny();
                    JLabel adoptedBunniesLabel = new JLabel(lineOfBunnies);
                    adoptedBunniesLabel.setBounds(50, 150 + i, 1000, 25);
                    panel.add(adoptedBunniesLabel);
                    i += 40;
                    k++;
                }
                save();
                adoptAnother();
            }
        };
        nextButton.addActionListener(adoptBunnyListener);
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

    public void save() {
        saveButton = new JButton("Save");
        saveButton.setBounds(100,350,80,25);
        panel.add(saveButton);
        saveListener();
    }

    public void saveListener() {
        ActionListener saveListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(adoptionProfile);
                    jsonWriter.close();
                    System.out.println("Saved " + adoptionProfile.getName() + " to " + JSON_STORE);
                    JLabel savedLabel = new JLabel("Saved profile successfully");
                    savedLabel.setBounds(80,375,200,25);
                    savedLabel.setForeground(Color.green);
                    panel.add(savedLabel);
                    panel.repaint();
                } catch (FileNotFoundException fe) {
                    JLabel savedLabel = new JLabel("Unable to write to file: " + JSON_STORE);
                    savedLabel.setBounds(85,375,200,25);
                    panel.add(savedLabel);
                    panel.repaint();
                }
            }
        };
        saveButton.addActionListener(saveListener);
    }

    public void adoptAnother() {
        adoptButton = new JButton("Adopt Another Bunny");
        adoptButton.setBounds(200,350,200,25);
        panel.add(adoptButton);
        adoptListener();
    }

    public void adoptListener() {
        ActionListener adoptListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seeBunnyShop();
            }
        };
        adoptButton.addActionListener(adoptListener);
    }

    public void quit() {
        panel.remove(yesButton);
        panel.remove(noButton);
        panel.remove(questionLabel);
        panel.revalidate();
        panel.repaint();
        JLabel quitLabel = new JLabel("Thanks for dropping by!");
        quitLabel.setBounds(100,100,200,25);
        panel.add(quitLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

