package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Represents an adoption profile
public class AdoptionProfile implements Writable {
    private final String name;
    private final OwnedBunnies ownedBunnies;

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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("ownedBunnies", ownedBunniesToJson());
        return json;
    }

    // EFFECTS: returns ownedBunnies in this adoption profile as a JSON array
    private JSONArray ownedBunniesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Bunny b : getOwnedBunnies().getListOfOwnedBunnies()) {
            jsonArray.put(b.toJson());
        }

        return jsonArray;
    }
}

