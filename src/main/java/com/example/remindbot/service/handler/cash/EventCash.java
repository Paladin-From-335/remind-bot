package com.example.remindbot.service.handler.cash;

import com.example.remindbot.model.dto.ReminderDto;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class EventCash {

    private final Map<Long, ReminderDto> eventCashMap = new ConcurrentHashMap<>();

    public ReminderDto getEvent(Long chatId) {
        return eventCashMap.get(chatId) == null ?
                null
                :
                eventCashMap.get(chatId);
    }

    public void saveEventCash(Long chatId, ReminderDto event) {
        eventCashMap.put(chatId, event);
    }

    public void deleteEventCash(Long chatId) {
        eventCashMap.remove(chatId);
    }

    public Boolean isEventExist(Long chatId) {
        return Objects.nonNull(getEvent(chatId));
    }
}

