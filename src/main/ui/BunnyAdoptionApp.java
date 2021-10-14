package ui;

import model.AdoptableBunnies;
import model.AdoptionProfile;
import model.Bunny;
import model.OwnedBunnies;

import java.util.Scanner;

//Bunny Adoption Application
public class BunnyAdoptionApp {

    private AdoptionProfile profile;
    private AdoptableBunnies adoptableBunnies;
    private OwnedBunnies ownedBunnies;

    Scanner scan = new Scanner(System.in);

    //Runs the Bunny Adoption App
    public  BunnyAdoptionApp() {
        runBunnyAdoptionApp();
    }

    public void runBunnyAdoptionApp() {
        adoptableBunnies = new AdoptableBunnies();
        System.out.println("Welcome to the Virtual Bunny Adoption Center!  How may I help you today?");
        System.out.println("1. Create a new profile  2. Exit");
        String option = scan.nextLine();
        if (option.equals("1")) {
            ownedBunnies = new OwnedBunnies();
            System.out.println("What is your name?");
            String userName = scan.nextLine();
            profile = new AdoptionProfile(userName);
            viewBunny();
        } else if (option.equals("2")) {
            System.out.println("See you next time!");
        }
    }

    public void viewBunny() {
        System.out.println("Would you like to view the adoptable bunnies? Yes/No");
        String view = scan.nextLine();
        if (view.equals("Yes")) {
            for (Bunny b : adoptableBunnies.getAdoptableBunniesList()) {
                b.displayBunny();
            }
            adoptBunny();
        } else if (view.equals("No")) {
            quit();
        }
    }

    public void adoptBunny() {
        System.out.println("Would you like to adopt a bunny? Yes/No");
        String adopt = scan.nextLine();
        if (adopt.equals("Yes")) {
            System.out.println("Which bunny would you like to adopt? Enter bunny number: ");
            String bunnyNum = scan.nextLine();
            ownedBunnies.addBunny(adoptableBunnies.getAdoptableBunniesList().get(Integer.valueOf(bunnyNum) - 1));
            adoptableBunnies.removeBunny(Integer.valueOf(bunnyNum) - 1);
            System.out.println("You have adopted bunny #" + bunnyNum);
            viewOwnedBunnies();
        } else if (adopt.equals("No")) {
            quit();
        }
    }

    public void viewOwnedBunnies() {
        System.out.println("Would you like to view your owned bunnies? Yes/No");
        String seeMyBunnies = scan.nextLine();
        if (seeMyBunnies.equals("Yes")) {
            for (Bunny b : ownedBunnies.getOwnedBunnies()) {
                b.displayBunny();
            }
            viewBunny();
        } else if (seeMyBunnies.equals("No")) {
            quit();
        }
    }

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
