package com.alexslo.responseAnalyzer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class DateUtil {

    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    private DateUtil() {
    }

    public static Date parseDate(String value) {
        Objects.requireNonNull(value);
        if (value.equals(""))
            return null;

        Date date = null;

        try {
            date = DATE_FORMATTER.parse(value);
        } catch (ParseException e) {
            System.out.printf("Unable to parse date from given value %s%n", value);
        }
        return date;
    }
}
