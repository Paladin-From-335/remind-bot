package com.example.remindbot.model.constants;

public enum BotButtons {

    CREATE_KEY("Create remind"), HELP_KEY("help info");

    private String value;

    BotButtons(String str) {
        this.value = str;
    }

    @Override
    public String toString() {
        return value;
    }
}
