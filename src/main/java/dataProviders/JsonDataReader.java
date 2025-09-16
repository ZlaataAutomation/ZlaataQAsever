package dataProviders;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public final class JsonDataReader {


    private final String credentialsFilePath;

    public JsonDataReader() {
        credentialsFilePath = System.getProperty("user.dir") + File.separator + "src/main/resources/ZlaataUI/Secrets/UISecrets.json";
    }

    public String getValueFromJson(String key) {
        String value = null;
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(credentialsFilePath)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            value = (String) jsonObject.get(key);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return value;
    }
}
