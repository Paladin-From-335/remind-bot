package com.example.remindbot.model.constants.menu;

public enum BotMenu {

    CREATE_KEY("Create new reminder \uD83D\uDD8A"),
    HELP_KEY("Help information \uD83C\uDD98"),
    ANOTHER_COMMANDS("Another commands ➡️");

    private String value;

    BotMenu(String str) {
        this.value = str;
    }

    @Override
    public String toString() {
        return value;
    }
}
