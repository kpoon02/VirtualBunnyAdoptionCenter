package model;

import java.util.ArrayList;
import java.util.List;

//Represents a list of bunnies that are available to be adopted
public class AdoptableBunnies {
    private Bunny bun1;
    private Bunny bun2;
    private Bunny bun3;
    private Bunny bun4;
    private List<Bunny> adoptableBunniesList;

    //MODIFIES: this
    //EFFECTS: creates an empty list of bunnies and adds specific bunnies to the list
    public AdoptableBunnies() {
        adoptableBunniesList = new ArrayList<>();
        bun1 = new Bunny("Fluffy", "White", 100);
        bun2 = new Bunny("Jackie", "White with brown spots", 80);
        bun3 = new Bunny("Pompom", "Grey", 140);
        bun4 = new Bunny("Gochi", "Light auburn", 250);
        adoptableBunniesList.add(bun1);
        adoptableBunniesList.add(bun2);
        adoptableBunniesList.add(bun3);
        adoptableBunniesList.add(bun4);
    }

    //EFFECTS: returns a list of adoptable bunnies
    public List<Bunny> getAdoptableBunniesList() {
        return adoptableBunniesList;
    }

    //REQUIRES: list must be non-empty
    //MODIFIES: this
    //EFFECTS: removes the bunny of integer index in the list and returns list of adoptable bunnies
    public List<Bunny> removeBunny(int index) {
        adoptableBunniesList.remove(index);
        return adoptableBunniesList;
    }
}
