package com.example.remindbot.utils.cash;

import com.example.remindbot.model.constants.State;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class StateCash {

    private static final Map<Long, State> stateCashMap = new ConcurrentHashMap<>();

    public static State getLastState(Long chatId) {
        return stateCashMap.get(chatId) == null ?
                State.EMPTY
                :
                stateCashMap.get(chatId);
    }

    public static void saveStateCash(Long chatId, State state) {
        stateCashMap.put(chatId, state);
    }

    public static void deleteState(Long chatId) {
        stateCashMap.remove(chatId);
    }

}

