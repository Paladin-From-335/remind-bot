package com.example.remindbot.service.handler;

import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.config.DataWrapperConfig;
import com.example.remindbot.config.ServiceConfig;
import com.example.remindbot.service.CallbackService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CallbackHandler {

    private final ServiceConfig serviceConfig;
    private final DataWrapperConfig dataConfig;

    public BotApiMethod<?> handleCallback(Update query, Integer msgId) {
        Long id = query.getCallbackQuery().getMessage().getChatId();
        String queryMessage = query.getCallbackQuery().getData();
        Map<String, CallbackService> map = serviceConfig.callbackServiceMap;
        try {
            if (map.containsKey(queryMessage)) {
                return map.get(queryMessage).handleCallbackQuery(id, msgId, dataConfig);
            }
            return buildResponse(id, "UNRECOGNIZED CALLBACK");
        } catch (Exception e) {
            return buildResponse(id, e.getMessage());
        }
    }
}
