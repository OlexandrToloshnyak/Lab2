package serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import lab2.display.Display;
import lab2.serialize.XmlSerializator;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class XMLSerializationTest {

    @Test
    public void testXMLForDisplay() throws JsonProcessingException {
        Display myDisplay = new Display.Builder()
                .withDisplay_id(0)
                .withMatrix_type("IPS")
                .withResolution("1920x1080")
                .withTouch_surface(true)
                .withColor_certification("Vesa Certified DisplayHDR")
                .withSRGB_coverage("99%")
                .build();
        XmlSerializator < Display > xmlSerializator = new XmlSerializator <Display> (Display.class);
        xmlSerializator.serialize(myDisplay,"src/main/java/lab2/output/display.xml");
        Display Displaynew = xmlSerializator.deserialization("src/main/java/lab2/output/display.xml");
        Assertions.assertEquals(myDisplay.toString(),Displaynew.toString());
    }
}


