package lab2.serialize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import java.beans.XMLDecoder;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class XmlSerializator < SomeObject >  {

    private Class< SomeObject > type;

    public XmlSerializator(Class< SomeObject > type) { this.type = type; }

    public void serialize(SomeObject model,String filePath) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

            String xmlString = xmlMapper.writeValueAsString(model);

            // write to the console
//            System.out.println(xmlString);

            // write XML string to file
            File xmlOutput = new File(filePath);
            FileWriter fileWriter = new FileWriter(xmlOutput);
            fileWriter.write(xmlString);
            fileWriter.close();
        } catch (JsonProcessingException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public SomeObject deserialization(String filePath) throws JsonProcessingException {
            try {
                XmlMapper xmlMapper = new XmlMapper();
                xmlMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
                String readContent = new String(Files.readAllBytes(Paths.get(filePath)));

                SomeObject deserializedData = xmlMapper.readValue(readContent, type);
                return deserializedData;
            } catch (IOException e) {
                System.out.println(e);
            }
            return null;
        }

}
