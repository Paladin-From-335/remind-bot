package com.example.remindbot.service.impl.message;

import static com.example.remindbot.utils.DateTimeUtil.countUserGmt;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.model.constants.State;
import com.example.remindbot.model.dto.ServiceWrapper;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TimezoneMessageImpl extends MessageServiceImpl {

    private final State key = State.SET_TIMEZONE;

    @Override
    public BotApiMethod<?> handleMessage(ServiceWrapper wrapper) {
        Long id = wrapper.getId();
        String userTime = wrapper.getUserMsg();
        Integer userGmt = countUserGmt(userTime);
        StringBuilder sb = new StringBuilder("GMT");
        if (userGmt > 0) sb.append("+").append(userGmt);
        if (userGmt < 0) sb.append(userGmt);
        wrapper.getUserRepo().setTimezone(sb.toString(), id);
        return buildResponse(id, "Your timezone: " + sb);
    }
}
