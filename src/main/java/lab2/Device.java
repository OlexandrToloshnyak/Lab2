package lab2;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lab2.processor.Processors;
import lab2.processor.GPU;
import lab2.display.Display;
import lab2.storage.Storage;
import lab2.os.Operating_systems;
import lab2.camera.Camera_module;
import java.io.Serializable;

import java.io.*;
import java.util.Objects;

public class Device implements Serializable{
    /**
     * Основний клас який звязує всі комплектуючі девайсу
     */
    private int Device_id;
    private String Name;
    private Type type;
    private Processors Processor;
    private GPU gpu;
    private Display display;
    private Storage storage;
    private Operating_systems OS;
    private Camera_module Camera;
    private int RAM;
    private String Dust_and_wet_protection;

    public Device(int device_id, String name, Type type, Processors processor, GPU gpu, Display display, Storage storage, Operating_systems OS, Camera_module camera, int RAM, String dust_and_wet_protection) {
        Device_id = device_id;
        Name = name;
        this.type = type;
        Processor = processor;
        this.gpu = gpu;
        this.display = display;
        this.storage = storage;
        this.OS = OS;
        Camera = camera;
        this.RAM = RAM;
        Dust_and_wet_protection = dust_and_wet_protection;
    }

    @Override
    public String toString() {
        return "Device{" +
                "Device_id=" + Device_id +
                ", Name='" + Name + '\'' +
                ", type=" + type +
                ", Processor=" + Processor +
                ", gpu=" + gpu +
                ", lab2.display=" + display +
                ", lab2.storage=" + storage +
                ", OS=" + OS +
                ", Camera=" + Camera +
                ", RAM=" + RAM +
                ", Dust_and_wet_protection='" + Dust_and_wet_protection + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Device_id == device.Device_id &&
                RAM == device.RAM &&
                Name.equals(device.Name) &&
                type.equals(device.type) &&
                Processor.equals(device.Processor) &&
                gpu.equals(device.gpu) &&
                display.equals(device.display) &&
                storage.equals(device.storage) &&
                Objects.equals(OS, device.OS) &&
                Objects.equals(Camera, device.Camera) &&
                Objects.equals(Dust_and_wet_protection, device.Dust_and_wet_protection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Device_id, Name, type, Processor, gpu, display, storage, OS, Camera, RAM, Dust_and_wet_protection);
    }
    public void toJson(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(this);

        try (FileWriter file = new FileWriter("src/main/java/lab2/output/" + this.Name + this.Device_id + ".json")) {

            file.write(json);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Device fromJson(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {

            System.out.println("\nReading JSON from a file");
            System.out.println("----------------------------");

            BufferedReader br = new BufferedReader(
                    new FileReader("src/main/java/lab2/output/" + this.Name + this.Device_id + ".json"));

            //convert the json string back to object
            Device deviceObj = gson.fromJson(br, Device.class);
//            System.out.println(cameraObj.toString());
            return deviceObj;
        } catch (IOException e) {
            e.printStackTrace();
            return this;
        }
    }
}
