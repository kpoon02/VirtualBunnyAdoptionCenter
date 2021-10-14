package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdoptionProfileTest {
    private AdoptionProfile profile;
    private OwnedBunnies myBunnies;
    private Bunny testBun;
    private AdoptableBunnies adoptableBunnies;


    @BeforeEach
    void runBefore(){
        myBunnies = new OwnedBunnies();
        profile = new AdoptionProfile("Katherine");
        testBun = new Bunny("Squishy", "Black", 10);
        adoptableBunnies = new AdoptableBunnies();
    }

    @Test
    void AdoptionProfileTest() {
        assertEquals("Katherine", profile.getName());
    }

    @Test
    void getNameTest() {
        assertEquals("Katherine", profile.getName());
    }

    //OwnedBunnies tests

    //@Test
    //void OwnedBunniesTest() {
    //    assertEquals();
    //}

    @Test
    void addBunnyTest() {
        myBunnies.getOwnedBunnies().add(testBun);
        assertEquals(myBunnies.getOwnedBunnies(), myBunnies.addBunny(testBun));
    }

    //AdoptableBunnies tests

    //@Test
    //void AdoptableBunniesTest() {
    //}

    //@Test
    //void getAdoptableBunniesTest() {
    //    assertEquals(      , adoptableBunnies.getAdoptableBunniesList());
    //}

    //@Test
    //void removeBunnyTest() {
    //    AdoptableBunnies addedBunnyList = adoptableBunnies.getAdoptableBunniesList().add(testBun);
    //    assertEquals(adoptableBunnies.getAdoptableBunniesList(), addedBunnyList.removeBunny(0));
    //}

    //Bunny Test

    //constructor test

    //@Test
    //void displayBunnyTest() {
        //how to test void methods.....
    //}
}