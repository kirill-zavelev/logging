package com.epam.webdriver.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final String DATE_PATTERN = "uuuu-MM-dd_HH-mm-ss";

    public static String getCurrentTimeAsString(){
        return ZonedDateTime.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }
}
