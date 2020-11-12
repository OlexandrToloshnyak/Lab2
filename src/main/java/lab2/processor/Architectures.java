package lab2.processor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Objects;
import java.io.Serializable;

public class Architectures implements Serializable{
    /**
     * Клас в якому міститься інформація про архітектуру центрального процесора
     */
    private int Architectures_id;
    private String Name;
    private Architectures_types Type;

    public Architectures(int architectures_id, String name, Architectures_types type) {
        Architectures_id = architectures_id;
        Name = name;
        Type = type;
    }

    @Override
    public String toString() {
        return "Architectures{" +
                "Architectures_id=" + Architectures_id +
                ", Name='" + Name + '\'' +
                ", Type=" + Type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Architectures that = (Architectures) o;
        return Architectures_id == that.Architectures_id &&
                Name.equals(that.Name) &&
                Type == that.Type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Architectures_id, Name, Type);
    }
    public void toJson() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(this);

        try (FileWriter file = new FileWriter("src/main/java/lab2/output/" + this.Name + this.Architectures_id + ".json")) {

            file.write(json);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Architectures fromJson() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {

            System.out.println("\nReading JSON from a file");
            System.out.println("----------------------------");

            BufferedReader br = new BufferedReader(
                    new FileReader("src/main/java/lab2/output/" + this.Name + this.Architectures_id + ".json"));

            //convert the json string back to object
            Architectures processorObj = gson.fromJson(br, Architectures.class);
//            System.out.println(cameraObj.toString());
            return processorObj;
        } catch (IOException e) {
            e.printStackTrace();
            return this;
        }
    }
}
