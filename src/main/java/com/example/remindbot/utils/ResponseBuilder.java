package com.example.remindbot.utils;

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodSerializable;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import java.util.List;

public class ResponseBuilder {

    public static SendMessage buildResponse(Long id, String message) {
        SendMessage response = new SendMessage();
        response.setChatId(id);
        response.setText(message);
        return response;
    }

    public static SendMessage buildResponse(Long id, ReplyKeyboard markup) {
        SendMessage response = new SendMessage();
        response.setChatId(id);
        response.setReplyMarkup(markup);
        return response;
    }

    public static SendMessage buildResponse(Long id, String message, ReplyKeyboard markup) {
        SendMessage response = new SendMessage();
        response.setReplyMarkup(markup);
        response.setChatId(id);
        response.setText(message);
        return response;
    }

    public static SendMessage buildResponse(Long id) {
        SendMessage response = new SendMessage();
        response.setChatId(id);
        response.setText("You don't have unsaved reminders");
        return response;
    }

    public static EditMessageReplyMarkup buildResponse(Long id, Integer msgId, InlineKeyboardMarkup keyboard) {
        EditMessageReplyMarkup edit = new EditMessageReplyMarkup();
        edit.setChatId(id);
        edit.setMessageId(msgId);
        edit.setReplyMarkup(keyboard);
        return edit;
    }

    public static EditMessageText buildResponse(Long id, Integer msgId, String message) {
        EditMessageText edit = new EditMessageText();
        edit.setMessageId(msgId);
        edit.setChatId(id);
        edit.setText(message);
        return edit;
    }

    public static List<BotApiMethodSerializable> buildResponse(Long id, Integer msgId, String message,
                                                               InlineKeyboardMarkup markup) {
        return List.of(
                buildResponse(id, msgId, message),
                buildResponse(id, msgId, markup)
        );
    }
}
