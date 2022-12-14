package com.example.remindbot.service.handler;

import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.config.ServiceConfig;
import com.example.remindbot.model.constants.State;
import com.example.remindbot.model.dto.ServiceWrapper;
import com.example.remindbot.repo.UserRepo;
import com.example.remindbot.service.MessageService;
import com.example.remindbot.service.impl.message.MessageServiceImpl;
import com.example.remindbot.utils.KeyboardUtil;
import com.example.remindbot.utils.exception.IncorrectDateTimeException;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageHandler {

    private final KeyboardUtil keyboardUtil;
    private final ServiceConfig serviceConfig;
    private final UserRepo userRepo;

    public BotApiMethod<?> handleMessage(Update update, State state) {
        Long id = update.getMessage().getChatId();
        String userMessage = update.getMessage().getText();
        ServiceWrapper serviceWrapper = new ServiceWrapper(
                id, userMessage, userRepo);
        if (state != State.START && userRepo.getUserTimezone(id) == null) {
            state = State.SET_TIMEZONE;
        }
        Map<State, MessageServiceImpl> messageServices = serviceConfig.messageServiceMap;
        try {
            if (messageServices.containsKey(state)) {
                return messageServices.get(state).handleMessage(serviceWrapper);
            }
        } catch (IncorrectDateTimeException e) {
            return buildResponse(id, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buildResponse(id, "Unrecognized command\nUse main menu", keyboardUtil.getKeyboardMarkup());
    }
}
