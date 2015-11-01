package com.epam.kiieu.bdd.core.generalhelpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * User: Viktoriia_Akhadova Date: 28.10.2015 Time: 19:44
 */
public class PropertyFactory {

    public static final String PROPERTY_FILE = "src/main/resources/config.properties";

    private static Properties properties;

    public static Properties getInstance() {
        if (null == properties) {
            properties = new Properties();
            InputStream is;
            try {
                is = new FileInputStream(PROPERTY_FILE);
                properties.load(is);
            } catch (FileNotFoundException e) {
                System.out.println("FileNotFoundException: \n");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("IOException: \n");
                e.printStackTrace();
            }

        }
        return properties;
    }

    public String getProperty(String property) {
    properties = getInstance();
        return properties.getProperty(property);
    }

}
