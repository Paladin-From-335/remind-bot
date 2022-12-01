package com.example.remindbot.utils;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class ResponseBuilder {

    public static SendMessage buildResponse(Long id, String message) {
        SendMessage response = new SendMessage();
        response.setChatId(id);
        response.setText(message);
        return response;
    }

    public static SendMessage buildResponse(Long id, String message, ReplyKeyboard markup) {
        SendMessage response = new SendMessage();
        response.setReplyMarkup(markup);
        response.setChatId(id);
        response.setText(message);
        return response;
    }
}
