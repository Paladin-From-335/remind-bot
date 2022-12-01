package com.example.remindbot.service.impl.message;

import static com.example.remindbot.utils.DateTimeUtil.countUserGmt;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.model.dto.ServiceWrapper;
import com.example.remindbot.service.MessageService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class TimezoneMessageImpl implements MessageService {

    @Override
    public SendMessage handleMessage(ServiceWrapper wrapper) {
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
