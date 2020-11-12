package lab2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.io.Serializable;

public class Type implements Serializable{
    /**
     * Клас який описує тип девайсу
     */
    private int Type_id;
    private String Description;

    public Type(int type_id, String description) {
        Type_id = type_id;
        Description = description;
    }

    @Override
    public String toString() {
        return "Type{" +
                "Type_id=" + Type_id +
                ", Description='" + Description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return Type_id == type.Type_id &&
                Description.equals(type.Description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Type_id, Description);
    }
    public void toJson(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(this);

        try (FileWriter file = new FileWriter("src/main/java/lab2/output/" + this.Description + this.Type_id + ".json")) {

            file.write(json);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Type fromJson(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {

            System.out.println("\nReading JSON from a file");
            System.out.println("----------------------------");

            BufferedReader br = new BufferedReader(
                    new FileReader("src/main/java/lab2/output/" + this.Description + this.Type_id + ".json"));

            //convert the json string back to object
            Type typeObj = gson.fromJson(br, Type.class);
//            System.out.println(cameraObj.toString());
            return typeObj;
        } catch (IOException e) {
            e.printStackTrace();
            return this;
        }
    }
}
