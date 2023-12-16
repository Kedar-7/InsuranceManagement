package Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropUtil {
    public static String getConnectionString(String propertyFileName) {
        Properties properties = new Properties();
        String connectionString = null;

        try (FileInputStream fileInputStream = new FileInputStream(propertyFileName)) {
            properties.load(fileInputStream);
            String hostname = properties.getProperty("hostname");  
            String port = properties.getProperty("port");  
            String dbname = properties.getProperty("dbname");

            connectionString = "jdbc:mysql://" + hostname + ":" + port + "/" + dbname;
        } catch (IOException e) {
           
            e.printStackTrace();
        }

        return connectionString;
    }
}
