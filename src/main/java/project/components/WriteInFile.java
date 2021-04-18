package project.components;

import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class WriteInFile {
    public void writeInLogFile(String log, String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));

        writer.write(String.format("%s%n", log));

        writer.close();
    }
}
