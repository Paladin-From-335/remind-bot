package com.example.remindbot.service.impl.callback;

import static com.example.remindbot.utils.Mapper.dtoToReminder;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;
import static com.example.remindbot.utils.cash.EventCash.isEventExist;

import com.example.remindbot.model.dto.CallbackWrapper;
import com.example.remindbot.model.entity.Reminder;
import com.example.remindbot.service.CallbackService;
import com.example.remindbot.utils.cash.EventCash;
import com.example.remindbot.utils.cash.StateCash;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.springframework.stereotype.Component;

@Component("save_reminder_query")
public class SaveCallbackImpl implements CallbackService {

    @Override
    public BotApiMethod<?> handleCallbackQuery(CallbackWrapper wrapper) {
        Long id = wrapper.getId();
        if (isEventExist(id)) {
            Reminder reminder = dtoToReminder(EventCash.getEvent(id));
            wrapper.getRemindService().saveRemind(reminder);
            EventCash.deleteEventCash(id);
            StateCash.deleteState(id);
            return buildResponse(id, "Your reminder is saved");
        }
        return buildResponse(id);
    }
}
