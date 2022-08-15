package com.example.remindbot.utils;

import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static final String DATE_PATTERN = "HH:mm dd.MM.yyyy";
    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN);
}
