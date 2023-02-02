package com.example.remindbot.utils;

import static com.example.remindbot.model.constants.Response.CREATED_REMIND;
import static com.example.remindbot.utils.DateTimeUtil.DATE_TIME_FORMATTER;
import static java.time.format.DateTimeFormatter.ISO_TIME;

import com.example.remindbot.model.dto.ReminderDto;
import com.example.remindbot.model.entity.Reminder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Reminder dtoToReminder(ReminderDto dto) {
        LocalDate ldTo = LocalDate.parse(dto.getDateTo(), DATE_TIME_FORMATTER);
        LocalTime ltTo = LocalTime.parse(dto.getTimeTo(), ISO_TIME);
        return new Reminder(
                dto.getRemindText(),
                dto.getChatId(),
                LocalDateTime.now(ZoneId.of("GMT")),
                ldTo.atTime(ltTo)

        );
    }

    public String dtoToResponse(ReminderDto dto) {
        return String.format(
                CREATED_REMIND.toString(),
                dto.getRemindText(),
                dto.getTimeTo(),
                dto.getDateTo());
    }
}
