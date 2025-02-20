package ca.uqam.mgl7230.tp1.config;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterProvider {

    public FileWriter createFile(String fileName) {
        try {
            return new FileWriter(fileName);
        } catch (IOException e) {
            System.out.println("Issue when creating file: " + e);
            return null;
        }
    }
}
