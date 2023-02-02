package com.example.remindbot.service.handler.cash;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class MessageIdCash {

    private final Map<Long, Integer> userMessageIds = new ConcurrentHashMap<>();

    public Integer getMsgId(Long chatId) {
        return userMessageIds.get(chatId) == null ?
                null
                :
                userMessageIds.get(chatId);
    }

    public void saveMsgId(Long chatId, Integer msgId) {
        userMessageIds.put(chatId, msgId);
    }

    public void deleteMsgId(Long chatId) {
        userMessageIds.remove(chatId);
    }

}
