package com.example.remindbot.service.impl.callback;

import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.config.DataWrapperConfig;
import com.example.remindbot.service.CallbackService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.springframework.stereotype.Component;

@Component("delete_reminder_query")
public class DeleteCallbackImpl implements CallbackService {

    @Override
    public BotApiMethod<?> handleCallbackQuery(Long id, Integer msgId, DataWrapperConfig data) {
        data.getEvents().deleteEventCash(id);
        data.getStates().deleteState(id);
        data.getMsgIds().deleteMsgId(id);
        return buildResponse(id, msgId, "Removed");
    }
}
