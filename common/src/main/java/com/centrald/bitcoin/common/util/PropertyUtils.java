package com.centrald.bitcoin.common.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    private static Properties properties;

    synchronized private static Properties getProperties() {
        if (properties == null) {
            try {
                properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("application.properties"));
            } catch (IOException e) {
                properties = new Properties();
            }
        }
        return properties;
    }

    public static String getString(String key) {
        return getProperties().getProperty(key);
    }

    public static Boolean getBoolean(String key) {
        return Boolean.parseBoolean(getString(key));
    }
}
