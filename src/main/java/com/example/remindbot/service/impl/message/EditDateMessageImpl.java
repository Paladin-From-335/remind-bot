package com.example.remindbot.service.impl.message;

import static com.example.remindbot.model.constants.Response.ELAPSED_DATE;
import static com.example.remindbot.utils.DateTimeUtil.validateDateTime;
import static com.example.remindbot.utils.KeyboardUtil.getMessageMarkup;
import static com.example.remindbot.utils.Mapper.dtoToResponse;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.model.dto.ReminderDto;
import com.example.remindbot.model.dto.ServiceWrapper;
import com.example.remindbot.service.MessageService;
import com.example.remindbot.utils.cash.EventCash;
import com.example.remindbot.utils.exception.IncorrectDateTimeException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EditDateMessageImpl implements MessageService {

    @Override
    public SendMessage handleMessage(ServiceWrapper wrapper) throws IncorrectDateTimeException {
        Long id = wrapper.getId();
        String reminderDate = wrapper.getUserMsg();
        String userTimezone = wrapper.getUserRepo().getUserTimezone(id);
        ReminderDto reminder = EventCash.getEvent(id);
        if (validateDateTime(reminderDate, reminder.getTimeTo(), userTimezone)) {
            EventCash.getEvent(id).setDateTo(reminderDate);
            return buildResponse(
                    id,
                    dtoToResponse(EventCash.getEvent(id))
            );
        }
        return buildResponse(
                id,
                ELAPSED_DATE.toString(),
                getMessageMarkup()
        );
    }
}
