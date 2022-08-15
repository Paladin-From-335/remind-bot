package com.example.remindbot.utils;

public enum Phrases {

    GREETINGS("Hello, I'm a remind bot.\n" +
            "You can create new remind using /create.\n" +
            "Or use /help to get list of all commands"),
    CREATING_TEXT("Write remind text"),
    CREATING_TIME("Set time in format dd.MM.yyyy hh:mm.\n" +
            "Example: 12:30 19.07.2025");

    private String value;

    Phrases(String str) {
        this.value = str;
    }

    @Override
    public String toString() {
        return value;
    }
}
