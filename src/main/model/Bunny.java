package model;

public class Bunny {
    private String bunnyName;
    private String colour;
    private double price;

    public Bunny(String name, String colour, double price) {
        bunnyName = name;
        this.colour = colour;
        this.price = price;
    }

    public void displayBunny() {
        System.out.println("Bunny Name: " + bunnyName + "    Colour: " + colour + "    Price: " + price);
    }
}
