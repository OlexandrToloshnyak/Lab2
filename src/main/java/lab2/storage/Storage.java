package lab2.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lab2.os.Operating_systems;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class Storage {
    private int Storage_Id;
    private Storage_types Type;
    private int Size_Gb;
    private int Reading_speed;
    private int Writting_speed;

    public Storage(int storage_Id, Storage_types type, int size_Gb, int reading_speed, int writting_speed) {
        Storage_Id = storage_Id;
        Type = type;
        Size_Gb = size_Gb;
        Reading_speed = reading_speed;
        Writting_speed = writting_speed;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "Storage_Id=" + Storage_Id +
                ", Type=" + Type +
                ", Size_Gb=" + Size_Gb +
                ", Reading_speed=" + Reading_speed +
                ", Writting_speed=" + Writting_speed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return Storage_Id == storage.Storage_Id &&
                Size_Gb == storage.Size_Gb &&
                Reading_speed == storage.Reading_speed &&
                Writting_speed == storage.Writting_speed &&
                Type == storage.Type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Storage_Id, Type, Size_Gb, Reading_speed, Writting_speed);
    }
    public void toJson(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(this);

        try (FileWriter file = new FileWriter("src/main/java/lab2/output/" + this.Type + this.Storage_Id + ".json")) {

            file.write(json);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Storage fromJson(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {

            System.out.println("\nReading JSON from a file");
            System.out.println("----------------------------");

            BufferedReader br = new BufferedReader(
                    new FileReader("src/main/java/lab2/output/" + this.Type + this.Storage_Id + ".json"));

            //convert the json string back to object
            Storage storageObj = gson.fromJson(br, Storage.class);
//            System.out.println(cameraObj.toString());
            return storageObj;
        } catch (IOException e) {
            e.printStackTrace();
            return this;
        }
    }
}
