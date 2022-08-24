package com.example.remindbot.utils;

public enum Phrases {

    GREETINGS("Hello, I'm a remind bot.\n" +
            "You can create new remind using /create.\n" +
            "Or use /help to get list of all commands"),
    CREATING_TEXT("Write remind text"),
    CREATING_TIME("Set time in format dd.MM.yyyy hh:mm.\n" +
            "Example: 12:30 19.07.2025"),
    WRONG_TIME_EXCEPTION("Your date and time can't be earlier than current\n" +
            "Current date and time: <i>%s</i>"),
    CREATED_REMIND("<b>Your remind: </b><i>%s</i>\n" +
            "<b>Time: </b><i>%s</i>"),
    INCORRECT_DATE_FORMAT("Incorrect date format, try again.\n" +
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
