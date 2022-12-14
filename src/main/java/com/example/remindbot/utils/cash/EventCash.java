package com.example.remindbot.utils.cash;

import com.example.remindbot.model.dto.ReminderDto;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventCash {

    private static final Map<Long, ReminderDto> eventCashMap = new ConcurrentHashMap<>();

    public static ReminderDto getEvent(Long chatId) {
        return eventCashMap.get(chatId) == null ?
                null
                :
                eventCashMap.get(chatId);
    }

    public static void saveEventCash(Long chatId, ReminderDto event) {
        eventCashMap.put(chatId, event);
    }

    public static void deleteEventCash(Long chatId) {
        eventCashMap.remove(chatId);
    }

    public static Boolean isEventExist(Long chatId) {
        return Objects.nonNull(getEvent(chatId));
    }
}

