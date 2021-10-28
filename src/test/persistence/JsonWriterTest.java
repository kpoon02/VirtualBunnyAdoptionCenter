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
public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            AdoptionProfile ap = new AdoptionProfile("");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyAdoptionProfile() {
        try {
            AdoptionProfile ap = new AdoptionProfile("");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAdoptionProfile.json");
            writer.open();
            writer.write(ap);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAdoptionProfile.json");
            ap = reader.read();
            assertEquals("", ap.getName());
            assertEquals(0, ap.getOwnedBunnies().getListOfOwnedBunnies().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralAdoptionProfile() {
        try {
            AdoptionProfile ap = new AdoptionProfile("Katherine");
            ap.getOwnedBunnies().getListOfOwnedBunnies().add(new Bunny("Gochi", "Light auburn", 250.00));
            ap.getOwnedBunnies().getListOfOwnedBunnies().add(new Bunny("Fluffy", "White", 100.00));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAdoptionProfile.json");
            writer.open();
            writer.write(ap);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralAdoptionProfile.json");
            ap = reader.read();
            assertEquals("Katherine", ap.getName());
            List<Bunny> ownedBunnies = ap.getOwnedBunnies().getListOfOwnedBunnies();
            assertEquals(2, ap.getOwnedBunnies().getListOfOwnedBunnies().size());
            checkBunny("Gochi", "Light auburn", 250.00, ownedBunnies.get(0));
            checkBunny("Fluffy", "White", 100.00, ownedBunnies.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
