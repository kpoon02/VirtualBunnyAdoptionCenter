package persistence;

import model.Bunny;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This class references code from the stleary/Json-java repo
// Link: https://github.com/stleary/JSON-java
public class JsonTest {
    protected void checkBunny(String name, String colour, double price, Bunny b) {
        assertEquals(name, b.getBunnyName());
        assertEquals(colour, b.getBunnyColour());
        assertEquals(price, b.getBunnyPrice());
    }
}
