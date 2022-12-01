package com.example.remindbot.utils;

import static com.example.remindbot.model.constants.Response.INCORRECT_DATE_FORMAT;
import static com.example.remindbot.model.constants.Response.INCORRECT_DATE_TIME_FORMAT;
import static com.example.remindbot.model.constants.Response.INCORRECT_TIME_FORMAT;
import static java.time.format.DateTimeFormatter.ISO_TIME;

import com.example.remindbot.utils.exception.IncorrectDateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtil {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    protected static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static Boolean validateDateTime(String reminderDate,
                                           String reminderTime,
                                           String userGmt) throws IncorrectDateTimeException {
        try {
            LocalDate parsedDate = LocalDate.parse(reminderDate, DATE_FORMATTER);
            if (reminderTime != null) {
                LocalTime parsedTime = LocalTime.parse(reminderTime, ISO_TIME);
                return !parsedDate.atTime(parsedTime).isBefore(LocalDateTime.now(ZoneId.of(userGmt)));
            }
            return !parsedDate.isBefore(LocalDate.now(ZoneId.of(userGmt)));
        } catch (DateTimeParseException e) {
            throw new IncorrectDateTimeException(INCORRECT_DATE_TIME_FORMAT.toString());
        }
    }

    public static Boolean validateDate(String remindDate, String userGmt) throws IncorrectDateTimeException {
        try {
            LocalDate parsedDate = LocalDate.parse(remindDate, DATE_FORMATTER);
            return !parsedDate.isBefore(LocalDate.now(ZoneId.of(userGmt)));
        } catch (DateTimeParseException e) {
            throw new IncorrectDateTimeException(INCORRECT_DATE_FORMAT.toString());
        }
    }

    public static Integer countUserGmt(String userTimezone) {
        LocalTime serverGmt = LocalTime.now(ZoneId.of("GMT"));
        int ifMore = 24;
        try {
            int userHour = LocalTime.parse(userTimezone, ISO_TIME).getHour();
            if (userHour - serverGmt.getHour() >= 12) {
                return userHour - (serverGmt.getHour() + ifMore);
            }
            return userHour - serverGmt.getHour();
        } catch (DateTimeParseException e) {
            throw new IncorrectDateTimeException(INCORRECT_TIME_FORMAT.toString());
        }
    }
}
