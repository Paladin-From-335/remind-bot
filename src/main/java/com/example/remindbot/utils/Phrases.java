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
            "Example: 12:30 19.07.2025"),
    HELP_INFO("This bot is created to remind you of something.\n" +
            "Commands:\n" +
            " - <b>/start</b> - start bot;\n" +
            " - <b>/create</b> - create new remind,\n" +
            " also you can create remind writing remind text\n" +
            " - <b>/list</b> - get your reminder list\n" +
            " - <b>/list_today</b> - get your reminders for today\n" +
            " - <b>/update</b> - update your reminder (text or date)\n" +
            " you can use this command after using /list or /list_today\n" +
            " - <b>/delete</b> - delete reminder\n" +
            " - <b>/delete_all</b> - delete all your reminders\n" +
            "<b><i>YOU CAN'T CREATE REMINDER STARTING WITH / (slash)</i></b>\n" +
            "Active commands:\n" +
            "/start, /create, /help");

    private String value;

    Phrases(String str) {
        this.value = str;
    }

    @Override
    public String toString() {
        return value;
    }
}
