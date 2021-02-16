package Helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFile {
    private static Properties properties = new java.util.Properties();


    public static void propertiesFileInputStream(){
        try {
            var filePath = new FileInputStream("C:/Users/User/IdeaProjects/LegalUsers/SQL_connection_data.properties");
            properties.load(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static String getDBProperty(String key) {

        return properties.getProperty(key);
    }

    public static void setDBProperty(String key, String value) {

        properties.setProperty(key, value);
    }

}
