package ui;

import model.AdoptableBunnies;
import model.AdoptionProfile;
import model.Bunny;
import model.OwnedBunnies;

import java.util.List;
import java.util.Scanner;

public class BunnyAdoptionApp {

    private AdoptionProfile profile;
    private AdoptableBunnies adoptableBunnies;
    private OwnedBunnies ownedBunnies;

    Scanner scan = new Scanner(System.in);

    //Bunny adoption app
    public  BunnyAdoptionApp() {
        runBunnyAdoptionApp();
    }

    public void runBunnyAdoptionApp() {
        System.out.println("Welcome to the Virtual Bunny Adoption Center!  How may I help you today?");
        System.out.println("1. Create a new profile  2. Exit");
        String option = scan.nextLine();
        if (option.equals("1")) {
            System.out.println("What is your name?");
            String userName = scan.nextLine();
            profile = new AdoptionProfile(userName);
            viewAndAdoptBunny();
        } else if (option.equals("2")) {
            System.out.println("See you next time!");
        }
    }

    public void viewAndAdoptBunny() {
        System.out.println("Would you like to view the adoptable bunnies? Yes/No");
        String adopt = scan.nextLine();
        if (adopt.equals("Yes")) {
            adoptableBunnies = new AdoptableBunnies();
            for (Bunny b : adoptableBunnies.getAdoptableBunnies()) {
                b.displayBunny();
            }
            System.out.println("Which bunny would you like to adopt? Enter bunny number: ");
            String bunnyNum = scan.nextLine();
            ownedBunnies = new OwnedBunnies();
            ownedBunnies.addBunny(adoptableBunnies.getAdoptableBunnies().get(Integer.valueOf(bunnyNum) - 1));
            adoptableBunnies.removeBunny(Integer.valueOf(bunnyNum) - 1);
            System.out.println("You have adopted bunny #" + bunnyNum);

        } else if (adopt.equals("No")) {
            System.out.println("Thanks for stopping by!");
        }
    }
}
