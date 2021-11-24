package ui;

import javafx.scene.text.Text;
import model.AdoptableBunnies;
import model.AdoptionProfile;
import model.Bunny;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


//Bunny Adoption GUI
public class BunnyAdoptionGUI implements ActionListener {
    public static final int FRAMEWIDTH = 500;
    public static final int FRAMEHEIGHT = 500;

    private static final String JSON_STORE = "./data/adoptionProfile.json";
    private AdoptionProfile adoptionProfile;
    private AdoptableBunnies adoptableBunnies;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private String lineOfBunnies;
    private String userName;

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
    private ImageIcon bunnyImage;
    private JLabel bunnyImageLabel;

    private JLabel questionLabel;
    private JTextField nameTextField;
    private JButton clearBunniesButton;
    private JTextField numTextField;
    private JTextArea logArea;
    private JButton quitButton;


    //runs BunnyAdoptionGUI
    public BunnyAdoptionGUI() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        menuGUI();
    }

    //MODIFIES: this
    //EFFECTS: Allows users to choose to create profile or load profile
    public void menuGUI() {
        adoptableBunnies = new AdoptableBunnies();

        logArea = new JTextArea();
        logArea.setEditable(false);

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
        menuOptions();
    }

    //MODIFIES: this
    //EFFECTS: sets up create profile button and load profile button
    public void menuOptions() {
        newProfile = new JButton("Create New Profile");
        newProfile.setBounds(150, 100, 150, 25);
        panel.add(newProfile);

        loadProfile = new JButton("Load Profile");
        loadProfile.setBounds(150, 150, 150, 25);
        loadProfile.addActionListener(this);
        panel.add(loadProfile);

        bunnyImage = new ImageIcon(getClass().getResource("bunnyPicture.jpg"));
        Image image = bunnyImage.getImage();
        Image newImage = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        bunnyImage = new ImageIcon(newImage);

        bunnyImageLabel = new JLabel(bunnyImage);
        bunnyImageLabel.setBounds(30, 100, 400, 400);
        panel.repaint();
        panel.add(bunnyImageLabel);

        quitButton();

        setUpButtonListeners();
    }

    //MODIFIES: this
    //EFFECTS: sets up action listeners for the create profile button and the load profile button
    public void setUpButtonListeners() {
        ActionListener newProfileListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createProfile();
            }
        };
        newProfile.addActionListener(newProfileListener);
        ActionListener loadProfileListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load();
            }
        };
        loadProfile.addActionListener(loadProfileListener);
    }

    //MODIFIES: this
    //EFFECTS: loads profile and displays owned bunnies or catches IOException if file can't be read
    public void load() {
        try {
            adoptionProfile = jsonReader.read();
            userName = adoptionProfile.getName();
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            JLabel name = new JLabel(adoptionProfile.getName());
            name.setBounds(50, 100, 200, 25);
            panel.add(name);
            createLabelsForOwnedBunnies();
            removeAdoptedBunnies();
        } catch (IOException e) {
            JLabel cantReadFile = new JLabel("Unable to read from file: " + JSON_STORE);
            cantReadFile.setBounds(100, 100, 200, 25);
            panel.add((cantReadFile));
        }
        quitButton();
        adoptAnother();
    }

    //MODIFIES: this
    //EFFECTS: displays a numbered list of owned bunnies as JLabels
    public void createLabelsForOwnedBunnies() {
        List<Bunny> ownedBunnies = adoptionProfile.getOwnedBunnies().getListOfOwnedBunnies();
        int i = 0;
        int k = 1;
        for (Bunny b : ownedBunnies) {
            lineOfBunnies = k + "   " + b.displayBunny();
            JLabel adoptedBunniesLabel = new JLabel(lineOfBunnies);
            adoptedBunniesLabel.setBounds(50, 150 + i, 1000, 25);
            panel.add(adoptedBunniesLabel);
            i += 40;
            k++;
        }
    }

    //MODIFIES: this
    //EFFECTS: remove the adopted bunnies from the list of adoptable bunnies
    public void removeAdoptedBunnies() {
        List<Bunny> ownedBunnies = adoptionProfile.getOwnedBunnies().getListOfOwnedBunnies();
        for (Bunny ob : ownedBunnies) {
            for (int j = 0; adoptableBunnies.getAdoptableBunniesList().size() > j; j++) {
                if (ob.getBunnyName().equals(adoptableBunnies.getAdoptableBunniesList().get(j).getBunnyName())) {
                    adoptableBunnies.getAdoptableBunniesList().remove(j);
                }
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: prompts user to enter their name for the new profile and adds a next button
    public void createProfile() {
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

        quitButton();
    }

    //MODIFIES: this
    //EFFECTS: calls viewAdoptableBunnies() when the next button is clicked
    public void nextListener() {
        ActionListener nextListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAdoptableBunnies();
                userName = nameTextField.getText();
            }
        };
        nextButton.addActionListener(nextListener);
    }

    //MODIFIES: this
    //EFFECTS: asks the user to choose yes or no to view the adoptable bunnies
    public void viewAdoptableBunnies() {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        userName = nameTextField.getText();
        adoptionProfile = new AdoptionProfile(userName);
        adoptableBunnies = new AdoptableBunnies();

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

        quitButton();

        setupNoListener();
    }

    //MODIFIES: this
    //EFFECTS: calls seeBunnyShop() when yes button is clicked
    public void setupYesListener() {
        ActionListener yesListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seeBunnyShop();
            }
        };
        yesButton.addActionListener(yesListener);
    }

    //MODIFIES: this
    //EFFECTS: shows the list of adoptable bunnies if there are bunnies left, then calls bunnySelect()
    public void seeBunnyShop() {
        panel.removeAll();
        panel.repaint();
        if (adoptableBunnies.getAdoptableBunniesList().size() == 0) {
            JLabel noBunniesLabel = new JLabel("Sorry, there are no more bunnies left to adopt!");
            noBunniesLabel.setBounds(100, 100, 400, 25);
            panel.add(noBunniesLabel);
            nextButton = new JButton("See Adopted Bunnies");
            nextButton.setBounds(100, 300, 200, 25);
            panel.add(nextButton);
            seeAdoptedBunniesListener();
        } else {
            displayAdoptableBunnies();
        }
        quitButton();
    }

    //MODIFIES: this
    //EFFECTS: displays all the adoptable bunnies in a list of JLabels
    public void displayAdoptableBunnies() {
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
    //MODIFIES: this
    //EFFECTS: calls showBunnies(0 when nextButton is pressed
    public void seeAdoptedBunniesListener() {
        ActionListener seeAdoptedBunniesListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBunnies();
            }
        };
        nextButton.addActionListener(seeAdoptedBunniesListener);
    }

    //MODIFIES: this
    //EFFECTS: prompts users to enter the bunny number that they want to adopt in a text field with next button
    public void bunnySelect() {
        questionLabel = new JLabel("Enter the number of the bunny you would like to adopt:");
        numTextField = new JTextField();
        questionLabel.setBounds(50, 300, 400, 25);
        numTextField.setBounds(50, 350, 200, 25);
        panel.add(questionLabel);
        panel.add(numTextField);

        nextButton = new JButton("Next");
        nextButton.setBounds(50, 400, 150, 25);
        panel.add(nextButton);
        adoptBunnyListener();
        quitButton();
    }

    //MODIFIES: this
    //EFFECTS: adds adopted bunny to profile and removes it from the adoptable bunnies list, then calls showBunnies()
    public void adoptBunnyListener() {
        ActionListener adoptBunnyListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int bunnyNum = Integer.valueOf(numTextField.getText());
                Bunny adoptionBunny = adoptableBunnies.getAdoptableBunniesList().get(bunnyNum - 1);
                adoptionProfile.getOwnedBunnies().addBunny(adoptionBunny);
                adoptableBunnies.removeBunny(bunnyNum - 1);
                showBunnies();
            }
        };
        nextButton.addActionListener(adoptBunnyListener);
    }

    //MODIFIES: this
    //EFFECTS: shows the adopted bunnies in a list of JLabels and calls save(), adoptAnother() and clearAllBunnies()
    public void showBunnies() {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        questionLabel = new JLabel("Here are your adopted bunnies:");
        questionLabel.setBounds(50, 100, 200, 25);
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
        clearAllBunnies();
        quitButton();
    }

    //MODIFIES: this
    //EFFECTS: calls quit() when no button is pressed
    public void setupNoListener() {
        ActionListener noListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quit();
            }
        };
        noButton.addActionListener(noListener);
    }

    //MODIFIES: this
    //EFFECTS: shows a save button on the panel
    public void save() {
        saveButton = new JButton("Save");
        saveButton.setBounds(100, 350, 80, 25);
        panel.add(saveButton);
        saveListener();
    }

    //MODIFIES: this
    //EFFECTS: clears all adopted bunnies from profile and resets the adoptable bunnies back to the original list
    public void clearAllBunnies() {
        clearBunniesButton = new JButton("Clear Adopted Bunnies");
        clearBunniesButton.setBounds(200, 50, 200, 25);
        panel.add(clearBunniesButton);
        clearAllBunniesListener();
    }

    //MODIFIES: this
    //EFFECTS: creates a new AdoptionProfile with same name and new AdoptableBunnies() then calls showBunnies()
    public void clearAllBunniesListener() {
        ActionListener clearAllBunniesListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adoptionProfile = new AdoptionProfile(userName);
                adoptableBunnies = new AdoptableBunnies();
                adoptionProfile.clearOwnedBunnies();
                showBunnies();
            }
        };
        clearBunniesButton.addActionListener(clearAllBunniesListener);
    }

    //MODIFIES: this
    //EFFECTS: saves the profile or catches FileNotFoundException when save button is pressed
    public void saveListener() {
        ActionListener saveListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(adoptionProfile);
                    jsonWriter.close();
                    JLabel savedLabel = new JLabel("Saved profile successfully");
                    savedLabel.setBounds(80, 375, 200, 25);
                    savedLabel.setForeground(Color.green);
                    panel.add(savedLabel);
                    panel.repaint();
                } catch (FileNotFoundException fe) {
                    JLabel savedLabel = new JLabel("Unable to write to file: " + JSON_STORE);
                    savedLabel.setBounds(85, 375, 200, 25);
                    panel.add(savedLabel);
                    panel.repaint();
                }
            }
        };
        saveButton.addActionListener(saveListener);
    }

    //MODIFIES: this
    //EFFECTS: shows a button to adopt another bunny from the adoptable bunnies
    public void adoptAnother() {
        adoptButton = new JButton("Adopt Another Bunny");
        adoptButton.setBounds(200, 350, 200, 25);
        panel.add(adoptButton);
        adoptListener();
    }

    //MODIFIES: this
    //EFFECTS: calls seeBunnyShop() when adoptButton is pressed
    public void adoptListener() {
        ActionListener adoptListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seeBunnyShop();
            }
        };
        adoptButton.addActionListener(adoptListener);
    }

    //MODIFIES: this
    //EFFECTS: shows a button to adopt another bunny from the adoptable bunnies
    public void quitButton() {
        quitButton = new JButton("Quit");
        quitButton.setBounds(380, 20, 100, 25);
        panel.add(quitButton);
        panel.revalidate();
        panel.repaint();
        quitListener();
    }

    //MODIFIES: this
    //EFFECTS: calls seeBunnyShop() when adoptButton is pressed
    public void quitListener() {
        ActionListener quitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quit();
            }
        };
        quitButton.addActionListener(quitListener);
    }

    //MODIFIES: this
    //EFFECTS: shows a goodbye JLabel
    public void quit() {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        JLabel quitLabel = new JLabel("Thanks for dropping by!");
        quitLabel.setBounds(100, 100, 200, 25);
        panel.add(quitLabel);
        printLog();
    }
    
    public void printLog() {
        for (Event next : EventLog.getInstance()) {
            System.out.println(next.toString() + "\n");
        }
    }

    //EFFECTS: the default actionPerformed overriden method if no other overriden method is specified
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}

