package model;

import java.util.ArrayList;
import java.util.List;

public class AdoptableBunnies {
    private Bunny bun1;
    private Bunny bun2;
    private Bunny bun3;
    private Bunny bun4;

    private List<Bunny> adoptableBunnies;

    public AdoptableBunnies() {
        adoptableBunnies = new ArrayList<Bunny>();
        bun1 = new Bunny("Fluffy", "White", 100);
        bun2 = new Bunny("Jackie", "White with brown spots", 80);
        bun3 = new Bunny("Pompom", "Grey", 140);
        bun4 = new Bunny("Gochi", "Light auburn", 250);
        adoptableBunnies.add(bun1);
        adoptableBunnies.add(bun2);
        adoptableBunnies.add(bun3);
        adoptableBunnies.add(bun4);
    }

    public List<Bunny> getAdoptableBunnies() {
        return adoptableBunnies;
    }

    public List<Bunny> removeBunny(int index) {
        adoptableBunnies.remove(index);
        return adoptableBunnies;
    }
}
