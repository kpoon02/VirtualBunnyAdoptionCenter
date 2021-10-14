package model;

import java.util.ArrayList;
import java.util.List;

//Represents an adoption profile
public class AdoptionProfile {
    private String name;
    private OwnedBunnies ownedBunnies;

    //REQUIRES: a non-zero length string for accountName
    //EFFECTS: creates an adoption profile with a name and an empty list of owned bunnies
    public AdoptionProfile(String accountName) {
        name = accountName;
        ownedBunnies = new OwnedBunnies();
    }

    //EFFECTS: returns name of user's profile
    public String getName() {
        return name;
    }

    //EFFECTS: returns the list of owned bunnies
    public OwnedBunnies getOwnedBunnies() {
        return ownedBunnies;
    }

}

