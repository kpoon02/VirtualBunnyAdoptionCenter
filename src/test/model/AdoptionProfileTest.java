package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdoptionProfileTest {
    private AdoptionProfile profile;
    private OwnedBunnies listOfBunnies;
    private Bunny testBun;

    @BeforeEach
    void runBefore(){
        listOfBunnies = new OwnedBunnies();
        profile = new AdoptionProfile("Katherine");
        testBun = new Bunny("Squishy", "Black", 10);
    }

    //@Test
    //void AdoptionProfileTest() {

    //}

    @Test
    void getName() {
        assertEquals("Katherine", profile.getName());
    }

    @Test
    void getOwnedBunnies() {
        listOfBunnies.addBunny(testBun);
    }


}