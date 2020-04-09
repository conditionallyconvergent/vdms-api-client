package com.conditionallyconvergent.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class VDMSDateTimeFormatter {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static String format(Instant instant) {
        return formatter.format(Date.from(instant));
    }

    public static Instant parse(String string) throws ParseException {
        return formatter.parse(string).toInstant();
    }
}
