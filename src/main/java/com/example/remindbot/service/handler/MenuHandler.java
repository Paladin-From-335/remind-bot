package com.example.remindbot.service.handler;

import com.example.remindbot.model.constants.BotButtons;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuHandler {

    public SendMessage sendMenu(Long id, String message) {
        SendMessage response = new SendMessage();
        response.setReplyMarkup(getKeyboardMarkup());
        response.setChatId(id);
        response.setText(message);
        return response;
    }

    private ReplyKeyboardMarkup getKeyboardMarkup() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardList = new ArrayList<>();
        for (BotButtons button : BotButtons.values()) {
            KeyboardRow keyboardRow = new KeyboardRow(Collections
                    .singleton(new KeyboardButton(button.toString())));
            keyboardList.add(keyboardRow);
        }
        replyKeyboardMarkup.setKeyboard(keyboardList);
        return replyKeyboardMarkup;
    }
}
