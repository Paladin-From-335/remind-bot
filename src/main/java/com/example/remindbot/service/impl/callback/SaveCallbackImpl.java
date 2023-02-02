package com.example.remindbot.service.impl.callback;

import static com.example.remindbot.model.constants.Response.FINISHED;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.config.DataWrapperConfig;
import com.example.remindbot.model.entity.Reminder;
import com.example.remindbot.service.CallbackService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.springframework.stereotype.Component;

@Component("save_reminder_query")
public class SaveCallbackImpl implements CallbackService {

    @Override
    public BotApiMethod<?> handleCallbackQuery(Long id, Integer msgId, DataWrapperConfig data) {
        Reminder reminder = data.getMapper().dtoToReminder(data.getEvents().getEvent(id));
        reminder = data.getRemService().saveRemind(reminder);
        if (reminder != null) {
            data.getReminders().saveTodaySReminder(reminder);
        }
        data.getEvents().deleteEventCash(id);
        data.getStates().deleteState(id);
        data.getMsgIds().deleteMsgId(id);
        return buildResponse(id, msgId, FINISHED.toString());
    }
}
