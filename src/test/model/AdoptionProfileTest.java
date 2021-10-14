package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdoptionProfileTest {
    private AdoptionProfile profile;
    private OwnedBunnies listOfBunnies;

    @BeforeEach
    void runBefore(){
        listOfBunnies = new OwnedBunnies();
        profile = new AdoptionProfile("Katherine");
    }

    @Test
    void AdoptionProfileTest() {

    }

}