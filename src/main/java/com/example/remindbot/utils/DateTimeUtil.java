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
import java.time.format.ResolverStyle;

public class DateTimeUtil {

    protected static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final DateTimeFormatter DATE_FORMATTER_STRICT =
            DateTimeFormatter.ofPattern("dd.MM.uuuu");

    private static final DateTimeFormatter DATE_TIME_FORMATTER_STRICT =
            DateTimeFormatter.ofPattern("dd.MM.uuuu HH:mm");

    public static final DateTimeFormatter DATE_TIME_SEND =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    private static final Integer maxGmt = 14;
    private static final Integer minGmt = -12;

    public static Boolean validateDateTime(String reminderDate,
                                           String reminderTime,
                                           String userGmt) throws IncorrectDateTimeException {
        try {
            LocalDateTime ldt = LocalDateTime.parse(
                    reminderDate + " " + reminderTime, DATE_TIME_FORMATTER_STRICT
                            .withResolverStyle(ResolverStyle.STRICT)
                            .withZone(ZoneId.of(userGmt)));
            return !ldt.isBefore(LocalDateTime.now(ZoneId.of(userGmt)));
        } catch (DateTimeParseException e) {
            throw new IncorrectDateTimeException(INCORRECT_DATE_TIME_FORMAT.toString());
        }
    }

    public static Boolean validateDate(String remindDate, String userGmt) throws IncorrectDateTimeException {
        try {
            LocalDate parsedDate = LocalDate.parse(
                    remindDate, DATE_FORMATTER_STRICT
                            .withResolverStyle(ResolverStyle.STRICT)
                            .withZone(ZoneId.of(userGmt)));
            return !parsedDate.isBefore(LocalDate.now(ZoneId.of(userGmt)));
        } catch (DateTimeParseException e) {
            throw new IncorrectDateTimeException(INCORRECT_DATE_FORMAT.toString());
        }
    }

    public static Integer countUserGmt(String userTimezone) {
        int serverHour = LocalTime.now(ZoneId.of("GMT")).getHour();
        int ifMore = 24;
        try {
            int userHour = LocalTime.parse(userTimezone, ISO_TIME).getHour();
            if (userHour - serverHour > maxGmt) {
                return userHour - (serverHour + ifMore);
            } else if (userHour - serverHour < minGmt) {
                return userHour - (serverHour - ifMore);
            }
            return userHour - serverHour;
        } catch (DateTimeParseException e) {
            throw new IncorrectDateTimeException(INCORRECT_TIME_FORMAT.toString());
        }
    }

    public static Boolean isEqualsOrElapsed(LocalDateTime userTime) {
        LocalDateTime serverTime = LocalDateTime.now(ZoneId.of("GMT"));
        return serverTime.format(DATE_TIME_SEND).equals(userTime.format(DATE_TIME_SEND))
                || serverTime.isAfter(userTime);
    }

}
