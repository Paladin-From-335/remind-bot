package com.example.remindbot.service.impl.callback;

import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.model.constants.State;
import com.example.remindbot.model.dto.CallbackWrapper;
import com.example.remindbot.service.CallbackService;
import com.example.remindbot.utils.cash.StateCash;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
public class EditTextCallbackImpl implements CallbackService {

    @Override
    public SendMessage handleCallbackQuery(CallbackWrapper wrapper) {
        StateCash.saveStateCash(wrapper.getId(), State.REMIND_TIME);
        return buildResponse(wrapper.getId(), "Write reminder time");
    }
}
