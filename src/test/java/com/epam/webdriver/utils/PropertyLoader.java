package com.epam.webdriver.utils;

import java.util.ResourceBundle;

public class PropertyLoader {

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(System.getProperty("environment"));

    public static String loadProperty(String key){
        return RESOURCE_BUNDLE.getString(key);
    }
}
