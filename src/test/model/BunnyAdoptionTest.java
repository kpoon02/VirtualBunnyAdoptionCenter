package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Test classes
class BunnyAdoptionTest {
    private AdoptionProfile profile;
    private OwnedBunnies myBunnies;
    private Bunny testBun;
    private AdoptableBunnies adoptableBunnies;
    private Bunny bun1;
    private Bunny bun2;
    private Bunny bun3;
    private Bunny bun4;


    @BeforeEach
    void runBefore(){
        myBunnies = new OwnedBunnies();
        profile = new AdoptionProfile("Katherine");
        testBun = new Bunny("Squishy", "Black", 150);
        adoptableBunnies = new AdoptableBunnies();
        bun1 = adoptableBunnies.getAdoptableBunniesList().get(0);
        bun2 = adoptableBunnies.getAdoptableBunniesList().get(1);
        bun3 = adoptableBunnies.getAdoptableBunniesList().get(2);
        bun4 = adoptableBunnies.getAdoptableBunniesList().get(3);
    }

    @Test
    void AdoptionProfileTest() {
        assertEquals("Katherine", profile.getName());
        List<Bunny> expectedBunnies = new ArrayList<>();
        assertEquals(expectedBunnies, profile.getOwnedBunnies().getListOfOwnedBunnies());
    }

    @Test
    void getNameTest() {
        assertEquals("Katherine", profile.getName());
    }

    //OwnedBunnies tests

    @Test
    void OwnedBunniesTest() {
        List<Bunny> expectedBunnies = new ArrayList<>();
        expectedBunnies.add(bun1);
        profile.getOwnedBunnies().getListOfOwnedBunnies().add(bun1);
        assertEquals(expectedBunnies, profile.getOwnedBunnies().getListOfOwnedBunnies());
    }

    @Test
    void addBunnyTest() {
        myBunnies.getListOfOwnedBunnies().add(testBun);
        assertEquals(myBunnies.getListOfOwnedBunnies(), myBunnies.addBunny(testBun));
    }

    //AdoptableBunnies tests

    @Test
    void AdoptableBunniesTest() {
        List<Bunny> expectedAdoptableBunnies = new ArrayList<>();
        expectedAdoptableBunnies.add(bun1);
        expectedAdoptableBunnies.add(bun2);
        expectedAdoptableBunnies.add(bun3);
        expectedAdoptableBunnies.add(bun4);
        assertEquals(expectedAdoptableBunnies, adoptableBunnies.getAdoptableBunniesList());
    }

    @Test
    void removeBunnyTest() {
        List<Bunny> expectedRemoveBunnyTest = new ArrayList<>();
        expectedRemoveBunnyTest.add(bun1);
        expectedRemoveBunnyTest.add(bun2);
        expectedRemoveBunnyTest.add(bun3);
        assertEquals(expectedRemoveBunnyTest, adoptableBunnies.removeBunny(3));
    }

    //Bunny Test

    @Test
    void BunnyTest() {
        assertEquals("Squishy", testBun.getBunnyName());
        assertEquals("Black", testBun.getBunnyColour());
        assertEquals(150, testBun.getBunnyPrice());
    }

    @Test
    void displayBunnyTest() {
        assertEquals("Bunny Name: Squishy  Colour: Black  Price: 150.0", testBun.displayBunny());
    }
}