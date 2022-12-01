package com.example.remindbot.utils;

import com.example.remindbot.model.constants.menu.BotMenu;
import com.example.remindbot.model.constants.menu.EditMenu;
import com.example.remindbot.model.constants.menu.ReplyMenu;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class KeyboardUtil {

    public static ReplyKeyboardMarkup getKeyboardMarkup() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardList = new ArrayList<>();
        for (BotMenu button : BotMenu.values()) {
            KeyboardRow keyboardRow = new KeyboardRow(Collections
                    .singleton(new KeyboardButton(button.toString())));
            keyboardList.add(keyboardRow);
        }
        replyKeyboardMarkup.setKeyboard(keyboardList);
        return replyKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getMessageMarkup() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        for (ReplyMenu button : ReplyMenu.values()) {
            List<InlineKeyboardButton> inlineButtons = new ArrayList<>();
            InlineKeyboardButton btn = new InlineKeyboardButton(button.toString());
            btn.setCallbackData(button.name());
            inlineButtons.add(btn);
            rowList.add(inlineButtons);
        }
        markup.setKeyboard(rowList);
        return markup;
    }

    public static InlineKeyboardMarkup getEditMarkup() {
        InlineKeyboardMarkup editMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        for (EditMenu editButton : EditMenu.values()) {
            List<InlineKeyboardButton> inlineButtons = new ArrayList<>();
            InlineKeyboardButton btn = new InlineKeyboardButton(editButton.toString());
            btn.setCallbackData(editButton.name());
            inlineButtons.add(btn);
            rowList.add(inlineButtons);
        }
        editMarkup.setKeyboard(rowList);
        return editMarkup;
    }

}
