package model;

import java.util.ArrayList;
import java.util.List;

public class AdoptionProfile {
    private String name;
    private OwnedBunnies ownedBunnies;

    public AdoptionProfile(String accountName) {
        name = accountName;
        ownedBunnies = new OwnedBunnies();
    }

    public String getName() {
        return name;
    }

    public OwnedBunnies getOwnedBunnies() {
        return ownedBunnies;
    }

}

