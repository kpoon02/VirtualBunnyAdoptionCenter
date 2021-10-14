package model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class OwnedBunnies {
    private List<Bunny> bunnyList;

    public OwnedBunnies() {
        bunnyList = new ArrayList<>();
    }

    public List<Bunny> addBunny(Bunny b) {
        bunnyList.add(b);
        return bunnyList;
    }
}
