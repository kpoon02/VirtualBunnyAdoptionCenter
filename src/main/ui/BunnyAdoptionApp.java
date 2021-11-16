package ui;

import model.AdoptableBunnies;
import model.AdoptionProfile;
import model.Bunny;
import model.OwnedBunnies;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

//Bunny Adoption Application
public class BunnyAdoptionApp {

    private static final String JSON_STORE = "./data/adoptionProfile.json";
    private AdoptionProfile adoptionProfile;
    private AdoptableBunnies adoptableBunnies;
    private OwnedBunnies ownedBunnies;
    private Scanner scan;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //Runs the Bunny Adoption App
    public  BunnyAdoptionApp() throws FileNotFoundException {
        scan = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runBunnyAdoptionApp();
    }

    //MODIFIES: this
    //EFFECTS: Allows users to create a profile or exit program
    public void runBunnyAdoptionApp() {
        adoptableBunnies = new AdoptableBunnies();
        System.out.println("Welcome to the Virtual Bunny Adoption Center!  How may I help you today?");
        System.out.println("1. Create a new profile  2. Load your profile  3. Exit");
        String option = scan.nextLine();
        if (option.equals("1")) {
            createProfile();
        } else if (option.equals("2")) {
            loadProfile();
        } else if (option.equals("3")) {
            quit();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new adoption profile
    public void createProfile() {
        ownedBunnies = new OwnedBunnies();
        System.out.println("What is your name?");
        String userName = scan.nextLine();
        adoptionProfile = new AdoptionProfile(userName);
        viewBunny();
    }

    // This method references code from the stleary/Json-java repo
    // Link: https://github.com/stleary/JSON-java
    // MODIFIES: this
    // EFFECTS: loads adoption profile from file
    public void loadProfile() {
        try {
            adoptionProfile = jsonReader.read();
            System.out.println("Loaded " + adoptionProfile.getName() + "'s profile from " + JSON_STORE);
            List<Bunny> ownedBunnies = adoptionProfile.getOwnedBunnies().getListOfOwnedBunnies();
            for (Bunny b: ownedBunnies) {
                System.out.println(b.displayBunny());
            }
            for (Bunny ob : ownedBunnies) {
                for (int i = 0; adoptableBunnies.getAdoptableBunniesList().size() > i; i++) {
                    if (ob.getBunnyName().equals(adoptableBunnies.getAdoptableBunniesList().get(i).getBunnyName())) {
                        adoptableBunnies.getAdoptableBunniesList().remove(i);
                    }
                }
            }
            viewBunny();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    //EFFECTS: show user adoptable bunnies if they would like to view, run quit otherwise
    public void viewBunny() {
        System.out.println("Would you like to view the adoptable bunnies? Yes/No");
        String view = scan.nextLine();
        if (view.equals("Yes")) {
            if (adoptableBunnies.getAdoptableBunniesList().size() == 0) {
                System.out.println("Sorry, there are no bunnies left to adopt!");
            }
            for (Bunny b : adoptableBunnies.getAdoptableBunniesList()) {
                System.out.println(b.displayBunny());
            }
            adoptBunny();
        } else if (view.equals("No")) {
            quit();
        }
    }

    // This method references code from the stleary/Json-java repo
    // Link: https://github.com/stleary/JSON-java
    // EFFECTS: saves the adoption profile to file
    public void saveProfile() {
        System.out.println("Would you like to save your profile? Yes/No");
        String save = scan.nextLine();
        if (save.equals("Yes")) {
            try {
                jsonWriter.open();
                jsonWriter.write(adoptionProfile);
                jsonWriter.close();
                System.out.println("Saved " + adoptionProfile.getName() + " to " + JSON_STORE);
                viewBunny();
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        } else if (save.equals("No")) {
            viewBunny();
        }
    }

    //MODIFIES: this
    //EFFECTS: if user adopts, add numbered bunny to owned bunnies, remove from adoptable bunnies, otherwise quit
    public void adoptBunny() {
        System.out.println("Would you like to adopt a bunny? Yes/No");
        String adopt = scan.nextLine();
        if (adopt.equals("Yes")) {
            System.out.println("Which bunny would you like to adopt? Enter bunny number: ");
            String bunnyNum = scan.nextLine();
            Bunny adoptionBunny = adoptableBunnies.getAdoptableBunniesList().get(Integer.valueOf(bunnyNum) - 1);
            adoptionProfile.getOwnedBunnies().addBunny(adoptionBunny);
            adoptableBunnies.removeBunny(Integer.valueOf(bunnyNum) - 1);
            System.out.println("You have adopted bunny #" + bunnyNum);
            viewOwnedBunnies();
        } else if (adopt.equals("No")) {
            quit();
        }
    }

    //EFFECTS: show users their owned bunnies if they say Yes, otherwise runs quit method
    public void viewOwnedBunnies() {
        System.out.println("Would you like to view your owned bunnies? Yes/No");
        String seeMyBunnies = scan.nextLine();
        if (seeMyBunnies.equals("Yes")) {
            for (Bunny b : adoptionProfile.getOwnedBunnies().getListOfOwnedBunnies()) {
                System.out.println(b.displayBunny());
            }
            saveProfile();
        } else if (seeMyBunnies.equals("No")) {
            saveProfile();
        }
    }

    //EFFECTS: quits the application if user says Yes, if not runs viewBunny method
    private void quit() {
        System.out.println("Would you like to quit?");
        String quit = scan.nextLine();
        if (quit.equals("Yes")) {
            System.out.println("Thanks for dropping by!");
        } else if (quit.equals("No")) {
            viewBunny();
        }
    }
}

