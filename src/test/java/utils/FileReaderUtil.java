package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class FileReaderUtil {
    public static HashMap<String, String> leerCredenciales() {

        HashMap<String, String> credentials = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("/src/test/java/resources/credenciales.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("=")) {
                    String[] parts = line.split("=", 2);
                    credentials.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return credentials;
    }
}
