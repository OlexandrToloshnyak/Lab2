package serialization;

import lab2.display.Display;
import lab2.serialize.TextSerializator;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TextSerializationTest {

    @Test
    public void testTextForDisplay() {
        Display myDisplay = new Display.Builder()
                .withDisplay_id(0)
                .withMatrix_type("IPS")
                .withResolution("1920x1080")
                .withTouch_surface(true)
                .withColor_certification("Vesa Certified DisplayHDR")
                .withSRGB_coverage("99%")
                .build();
        TextSerializator< Display > textSerializator = new TextSerializator< Display>();
        textSerializator.serialize(myDisplay,"src/main/java/lab2/output/display.txt");
        Display Displaynew = textSerializator.deserialization("src/main/java/lab2/output/display.txt");
        Assertions.assertEquals(myDisplay.toString(),Displaynew.toString());
    }
}


