package com.example.remindbot.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReminderDto {

    private String remindText;
    private Long chatId;
    private LocalDateTime createdAt;
    private String timeTo;
    private String dateTo;

    public ReminderDto(Long chatId) {
        this.chatId = chatId;
    }
}
