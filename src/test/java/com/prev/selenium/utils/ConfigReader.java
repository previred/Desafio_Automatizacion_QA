package com.prev.selenium.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader(String filePath) {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("No se pudo cargar el archivo properties.");
        }
    }

    public String getEmail() {
        return properties.getProperty("email");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }
}
