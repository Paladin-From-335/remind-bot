package com.example.remindbot.service.handler;

import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.config.ServiceConfig;
import com.example.remindbot.model.dto.CallbackWrapper;
import com.example.remindbot.service.CallbackService;
import com.example.remindbot.service.RemindService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CallbackHandler {

    private final ServiceConfig serviceConfig;
    private final RemindService remindService;

    public SendMessage handleCallback(Update query) {
        Long id = query.getCallbackQuery().getMessage().getChatId();
        CallbackWrapper callbackWrapper = new CallbackWrapper(id, remindService);
        String queryMessage = query.getCallbackQuery().getData();
        Map<String, CallbackService> map = serviceConfig.callbackServiceMap;
        try {
            if (map.containsKey(queryMessage)) {
                return map.get(queryMessage).handleCallbackQuery(callbackWrapper);
            }
            return buildResponse(id, "UNRECOGNIZED CALLBACK");
        } catch (Exception e) {
            return buildResponse(id, e.getMessage());
        }
    }
}
