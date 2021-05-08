package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class LeerProperty {


    private static Properties prop = null;

    public static Properties leerProperties() throws FileNotFoundException {

        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
            prop.load(ip);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch ( IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
