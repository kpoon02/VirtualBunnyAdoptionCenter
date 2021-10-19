package persistence;

import model.AdoptionProfile;
import model.Bunny;
import model.OwnedBunnies;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// This class references code from the stleary/Json-java repo
// Link: https://github.com/stleary/JSON-java
// Represents a reader that reads adoption profile from JSON data stored in file
public class JsonReader {
    private String source;

    //EFFECTS: constructs a reader to read from the source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads adoption profile from file and returns it and throws IO exception if error occurs when reading file
    public AdoptionProfile read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAdoptionProfile(jsonObject);
    }

    // EFFECTS: reads the source file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses AdoptionProfile from JSON object and returns it
    private AdoptionProfile parseAdoptionProfile(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        AdoptionProfile ap = new AdoptionProfile(name);
        addBunnies(ap, jsonObject);
        return ap;
    }

    // MODIFIES: ap
    // EFFECTS: parses bunnies from JSON object and adds them to AdoptionProfile
    private void addBunnies(AdoptionProfile ap, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("ownedBunnies");
        for (Object json : jsonArray) {
            JSONObject nextBunny = (JSONObject) json;
            addBunny(ap, nextBunny);
        }
    }

    // MODIFIES: ap
    // EFFECTS: parses bunny from JSON object and adds it to AdoptionProfile
    private void addBunny(AdoptionProfile ap, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String colour = jsonObject.getString("colour");
        double price = jsonObject.getDouble("price");
        Bunny bunny = new Bunny(name,colour, price);
        ap.getOwnedBunnies().addBunny(bunny);
    }

}
