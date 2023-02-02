package com.example.remindbot.utils;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class ResponseBuilder {

    public static BotApiMethod<?> buildResponse(Long id, String message) {
        SendMessage response = new SendMessage();
        response.setChatId(id);
        response.setText(message);
        response.enableHtml(true);
        return response;
    }

    public static BotApiMethod<?> buildResponse(Long id, ReplyKeyboard markup) {
        SendMessage response = new SendMessage();
        response.setChatId(id);
        response.setReplyMarkup(markup);
        response.enableHtml(true);
        return response;
    }

    public static BotApiMethod<?> buildResponse(Long id, String message, ReplyKeyboard markup) {
        SendMessage response = new SendMessage();
        response.setReplyMarkup(markup);
        response.setChatId(id);
        response.setText(message);
        response.enableHtml(true);
        return response;
    }

    public static BotApiMethod<?> buildResponse(Long id) {
        SendMessage response = new SendMessage();
        response.setChatId(id);
        response.setText("You don't have unsaved reminders");
        response.enableHtml(true);
        return response;
    }

    public static BotApiMethod<?> buildResponse(Long id, Integer msgId, InlineKeyboardMarkup keyboard) {
        EditMessageReplyMarkup edit = new EditMessageReplyMarkup();
        edit.setChatId(id);
        edit.setMessageId(msgId);
        edit.setReplyMarkup(keyboard);
        return edit;
    }

    public static BotApiMethod<?> buildResponse(Long id, Integer msgId, String message) {
        EditMessageText edit = new EditMessageText();
        edit.setMessageId(msgId);
        edit.setChatId(id);
        edit.setText(message);
        edit.enableHtml(true);
        return edit;
    }

    public static BotApiMethod<?> buildResponse(Long id, Integer msgId, String message,
                                                InlineKeyboardMarkup markup) {
        EditMessageText edit = new EditMessageText();
        edit.setChatId(id);
        edit.setMessageId(msgId);
        edit.setText(message);
        edit.setReplyMarkup(markup);
        edit.enableHtml(true);
        return edit;
    }
}
