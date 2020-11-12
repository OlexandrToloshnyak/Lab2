package lab2;
import lab2.camera.Camera_module;
import lab2.display.Display;
import lab2.os.Operating_systems;
import lab2.processor.*;
import lab2.storage.Storage_types;
import lab2.os.OS_types;
import lab2.os.Accessibility;
import lab2.storage.Storage;
import lab2.Device;
import lab2.Type;
import lab2.serialize.JsonSerializator;
import lab2.serialize.XmlSerializator;
import lab2.serialize.TextSerializator;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	Display myDisplay = new Display.Builder()
            .withDisplay_id(0)
            .withMatrix_type("IPS")
            .withResolution("1920x1080")
            .withTouch_surface(true)
            .withColor_certification("Vesa Certified DisplayHDR")
            .withSRGB_coverage("99%")
            .build();

	Camera_module myCamera = new Camera_module.Builder()
            .withCamera_id(0)
            .withName("IMX363")
            .withType_of_lens("27mm,f/1.7,OIS")
            .withProducer("Sony")
            .build();

	Type myType = new Type(0,"Smartphone");
	Storage myStorage = new Storage(0,Storage_types.UFS,128,1200,850);
	GPU myGpu = new GPU(0,"Adreno 550",GPU_types.Integrated,"Quallcom",6,3000);
	Architectures myArch = new Architectures(0,"ARM",Architectures_types.CISC);
	Processors myCpu = new Processors(0,"Snapdragon 865", myArch,myGpu,"Quallcom",8,1800);
	Operating_systems myOS = new Operating_systems(0,"Android 10",OS_types.Hybrid,Accessibility.Free);
	Device myDevice = new Device(0,"Samsung Galaxy s20",myType,myCpu,myGpu,myDisplay,myStorage,myOS,myCamera,8,"IP68");

//	System.out.print("\n\n"+myCamera.toString());
//	myCamera.toJson();
////	myCamera=myCamera.fromJson();
//
//	myDisplay.toJson();
////	myDisplay=myDisplay.fromJson();
//
//	myArch.toJson();
//
//	myCpu.toJson();
//
//	myGpu.toJson();
//
//	myOS.toJson();
//
//	myStorage.toJson();
//
//	myType.toJson();
//
//	myDevice.toJson();
	JsonSerializator <Camera_module> jsonSerializator = new JsonSerializator<Camera_module>(Camera_module.class);
	jsonSerializator.serialize(myCamera,"src/main/java/lab2/output/camera.json");
	Camera_module Cameranew = jsonSerializator.deserialization("src/main/java/lab2/output/camera.json");
	System.out.println(Cameranew.toString());


	TextSerializator < Display > textSerializator = new TextSerializator< Display>();
	textSerializator.serialize(myDisplay,"src/main/java/lab2/output/display.txt");
	Display Displaynew = textSerializator.deserialization("src/main/java/lab2/output/display.txt");
	System.out.println(Displaynew.toString());


	XmlSerializator < Display > xmlSerializator = new XmlSerializator <Display> (Display.class);
	xmlSerializator.serialize(myDisplay,"src/main/java/lab2/output/display.xml");
	Display Displaynew1 = xmlSerializator.deserialization("src/main/java/lab2/output/display.xml");
	System.out.println(Displaynew1.toString());

	}
}
