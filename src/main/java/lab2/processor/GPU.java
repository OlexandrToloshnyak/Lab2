package lab2.processor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Objects;
import java.io.Serializable;

public class GPU  implements Serializable{
    /**
     * Клас в якому міститься інформація про графічний процесор дивайсу
     */
    private int GPU_id;
    private String Name;
    private GPU_types Type;
    private String Producer;
    private int Memory;
    private float Frequency;

    public GPU(int GPU_id, String name, GPU_types type, String producer, int memory, float frequency) {
        this.GPU_id = GPU_id;
        Name = name;
        Type = type;
        Producer = producer;
        Memory = memory;
        Frequency = frequency;
    }

    @Override
    public String toString() {
        return "GPU{" +
                "GPU_id=" + GPU_id +
                ", Name='" + Name + '\'' +
                ", Type=" + Type +
                ", Producer='" + Producer + '\'' +
                ", Memory=" + Memory +
                ", Frequency=" + Frequency +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GPU gpu = (GPU) o;
        return GPU_id == gpu.GPU_id &&
                Memory == gpu.Memory &&
                Float.compare(gpu.Frequency, Frequency) == 0 &&
                Name.equals(gpu.Name) &&
                Type == gpu.Type &&
                Producer.equals(gpu.Producer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(GPU_id, Name, Type, Producer, Memory, Frequency);
    }
    public void toJson() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(this);

        try (FileWriter file = new FileWriter("src/main/java/lab2/output/" + this.Producer +this.Name + ".json")) {

            file.write(json);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GPU fromJson() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {

            System.out.println("\nReading JSON from a file");
            System.out.println("----------------------------");

            BufferedReader br = new BufferedReader(
                    new FileReader("src/main/java/lab2/output/" + this.Producer + this.Name + ".json"));

            //convert the json string back to object
            GPU gpuObj = gson.fromJson(br, GPU.class);
//            System.out.println(cameraObj.toString());
            return gpuObj;
        } catch (IOException e) {
            e.printStackTrace();
            return this;
        }
    }
}
