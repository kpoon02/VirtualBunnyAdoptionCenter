package model;

//Represents a bunny with a name, colour and price
public class Bunny {
    private String bunnyName;
    private String colour;
    private double price;

    //REQUIRES: name has a non-zero length
    //MODIFIES: this (?)
    //EFFECTS: creates a bunny with name, colour and price
    public Bunny(String name, String colour, double price) {
        bunnyName = name;
        this.colour = colour;
        this.price = price;
    }

    //EFFECTS: prints out the bunny name, colour and price
    public void displayBunny() {
        System.out.println("Bunny Name: " + bunnyName + "    Colour: " + colour + "    Price: " + price);
    }
}
