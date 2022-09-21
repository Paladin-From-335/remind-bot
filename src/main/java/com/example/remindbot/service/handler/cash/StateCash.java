package com.example.remindbot.service.handler.cash;

import com.example.remindbot.model.constants.State;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class StateCash {

    public Map<Long, State> stateCashMap = new ConcurrentHashMap<>();

    public State getLastState(Long chatId) {
        return stateCashMap.get(chatId) == null ?
                State.EMPTY
                :
                stateCashMap.get(chatId);
    }

    public void saveStateCash(Long chatId, State state) {
        stateCashMap.put(chatId, state);
    }

}

