package lab2.serialize;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonSerializator < Model > {

    private Class< Model > type;

    public JsonSerializator(Class< Model > type) { this.type = type; }

    public void serialize(Model model, String filePath) {
        Gson gson = new Gson();
        FileWriter writer = null;
        try {
            writer = new FileWriter(filePath);
            gson.toJson(model, writer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Model deserialization(String filePath) {
        Gson gson = new Gson();
        FileReader fileReader = null;
        JsonReader reader = null;
        try {
            fileReader = new FileReader(filePath);
            reader = new JsonReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return gson.fromJson(reader, this.type);
    }

}
