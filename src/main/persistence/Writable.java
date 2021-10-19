package persistence;

import org.json.JSONObject;

// This interface references code from the stleary/Json-java repo
// Link: https://github.com/stleary/JSON-java
public interface Writable {
    //EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
