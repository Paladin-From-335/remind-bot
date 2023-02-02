package com.example.remindbot.service.impl.message;

import static com.example.remindbot.model.constants.Response.ELAPSED_DATE;
import static com.example.remindbot.utils.DateTimeUtil.validateDateTime;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.config.DataWrapperConfig;
import com.example.remindbot.model.dto.ReminderDto;
import com.example.remindbot.service.MessageService;
import com.example.remindbot.utils.exception.IncorrectDateTimeException;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component("EDIT_DATE")
@RequiredArgsConstructor
public class EditDateMessageImpl implements MessageService {
    
    @Override
    public BotApiMethod<?> handleMessage(Long id, String reminderDate, DataWrapperConfig data) throws IncorrectDateTimeException {
        String userTimezone = data.getUserRepo().getTimezone(id);
        ReminderDto reminder = data.getEvents().getEvent(id);
        if (validateDateTime(reminderDate, reminder.getTimeTo(), userTimezone)) {
            data.getEvents().getEvent(id).setDateTo(reminderDate);
            return buildResponse(
                    id,
                    data.getMapper().dtoToResponse(data.getEvents().getEvent(id)),
                    data.getKeyboard().getReminderMarkup()
            );
        }
        return buildResponse(
                id,
                ELAPSED_DATE.toString(),
                data.getKeyboard().getReminderMarkup()
        );
    }
}
