package model;

import java.util.ArrayList;
import java.util.List;

//Represents a list of bunnies that is adopted/owned by the user
public class OwnedBunnies {
    private List<Bunny> listOfBunnies;

    //EFFECTS: creates an empty list of bunnies
    public OwnedBunnies() {
        listOfBunnies = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds bunny to the list of owned bunnies
    public List<Bunny> addBunny(Bunny b) {
        listOfBunnies.add(b);
        return listOfBunnies;
    }

    //EFFECTS: returns list of owned bunnies
    public List<Bunny> getListOfOwnedBunnies() {
        return listOfBunnies;
    }
}
