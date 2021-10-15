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
    public String displayBunny() {
        String bunnyMessage = "Bunny Name: " + bunnyName + "  Colour: " + colour + "  Price: " + price;
        return bunnyMessage;
    }

    //EFFECTS: returns the name of the bunny
    public String getBunnyName() {
        return bunnyName;
    }

    //EFFECTS: returns the colour of the bunny
    public String getBunnyColour() {
        return colour;
    }

    //EFFECTS: return the price of the bunny
    public double getBunnyPrice() {
        return price;
    }
}
