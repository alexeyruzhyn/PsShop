package psshop.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetUrlFromPropertiesFile {

    public static Properties prop = new Properties();

    public static void getProperty() {

        try (InputStream input = new FileInputStream("src/test/resources.properties")) {

            // load a properties file
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
