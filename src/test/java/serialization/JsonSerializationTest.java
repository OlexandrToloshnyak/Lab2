package serialization;

import lab2.camera.Camera_module;
import lab2.serialize.JsonSerializator;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class JsonSerializationTest {

    @Test
    public void testJsonForCameraModule() {
        Camera_module myCamera = new Camera_module.Builder()
                .withCamera_id(0)
                .withName("IMX363")
                .withType_of_lens("27mm,f/1.7,OIS")
                .withProducer("Sony")
                .build();
        JsonSerializator <Camera_module> jsonSerializator = new JsonSerializator<Camera_module>(Camera_module.class);
        jsonSerializator.serialize(myCamera,"src/main/java/lab2/output/camera.json");
        Camera_module Cameranew = jsonSerializator.deserialization("src/main/java/lab2/output/camera.json");
        Assertions.assertEquals(myCamera.toString(),Cameranew.toString());
    }
}


