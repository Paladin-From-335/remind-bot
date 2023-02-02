package com.example.remindbot.service.impl.message;

import static com.example.remindbot.utils.DateTimeUtil.countUserGmt;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.config.DataWrapperConfig;
import com.example.remindbot.service.MessageService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component("SET_TIMEZONE")
public class TimezoneMessageImpl implements MessageService {

    @Override
    public BotApiMethod<?> handleMessage(Long id, String userTime, DataWrapperConfig data) {
        Integer userGmt = countUserGmt(userTime);
        StringBuilder sb = new StringBuilder("GMT");
        if (userGmt > 0) sb.append("+").append(userGmt);
        if (userGmt < 0) sb.append(userGmt);
        data.getUserRepo().setTimezone(sb.toString(), id);
        return buildResponse(id, "Your timezone: " + sb);
    }
}
