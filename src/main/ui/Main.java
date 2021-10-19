package ui;

import java.io.FileNotFoundException;

//main method that runs the bunny adoption app
public class Main {
    public static void main(String[] args) {

        try {
            new BunnyAdoptionApp();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot run the application - file is not found");
        }
    }
}
