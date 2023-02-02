package com.example.remindbot.service.handler.cash;

import com.example.remindbot.model.constants.State;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class StateCash {

    private final Map<Long, State> stateCashMap = new ConcurrentHashMap<>();

    public State getLastState(Long chatId) {
        return stateCashMap.get(chatId) == null ?
                State.EMPTY
                :
                stateCashMap.get(chatId);
    }

    public void saveStateCash(Long chatId, State state) {
        stateCashMap.put(chatId, state);
    }

    public void deleteState(Long chatId) {
        stateCashMap.remove(chatId);
    }

}

