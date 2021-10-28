package persistence;

import model.AdoptionProfile;
import model.Bunny;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// This class references code from the stleary/Json-java repo
// Link: https://github.com/stleary/JSON-java
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            AdoptionProfile ap = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testReaderEmptyAdoptionProfile() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAdoptionProfile.json");
        try {
            AdoptionProfile ap = reader.read();
            assertEquals("", ap.getName());
            assertEquals(0, ap.getOwnedBunnies().getListOfOwnedBunnies().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralAdoptionProfile() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralAdoptionProfile.json");
        try {
            AdoptionProfile ap = reader.read();
            assertEquals("Katherine", ap.getName());
            List<Bunny> ownedBunnies = ap.getOwnedBunnies().getListOfOwnedBunnies();
            assertEquals(2, ownedBunnies.size());
            checkBunny("Fluffy", "White", 100.00, ownedBunnies.get(0));
            checkBunny("Gochi", "Light auburn", 250.00, ownedBunnies.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
