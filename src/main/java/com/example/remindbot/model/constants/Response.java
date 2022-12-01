package com.example.remindbot.model.constants;

public enum Response {

    GREETINGS("Hello, I'm a remind bot.\n" +
            "To start enter your current time in format 23:30 (hour:min)"),
    CREATING_TEXT("Write remind text"),
    CREATING_TIME("Set time in format hh:mm.\n" +
            "Example: 00:00"),
    CREATING_DATE("Set date in format dd.MM.yyyy (day.mon.year)\n" +
            "Example: 01.01.1970"),
    FINISHED("Reminder is saved"),
    CREATED_REMIND("<b>Your remind: </b><i>%s</i>\n" +
            "<b>Time: </b><i>%s</i>\n" +
            "<b>Date: </b><i>%s</i>"),
    INCORRECT_DATE_FORMAT("Incorrect date format, try again.\n" +
            "Example: 01.01.1970 (day.mon.year)"),
    INCORRECT_TIME_FORMAT("To start enter your current time" +
            "Incorrect time format, try again.\n" +
            "Example: 23:30 (hour:min)\n" +
            "<i>24-hour format</i>"),
    INCORRECT_DATE_TIME_FORMAT("Incorrect date and/or time format, try again.\n" +
            "Date example: 01.01.1970 (day.mon.year)\n" +
            "Time example: 23:30 (hour:min)"),
    ELAPSED_DATE("You tried to set elapsed date.\n" +
            "Check current date and try again."),
    ELAPSED_TIME("You tried to set elapsed time.\n" +
            "Check current date and try again."),
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
            " you can use this command after using /list or /list_today\n" +
            " - <b>/delete_all</b> - delete all your reminders\n" +
            "Active commands/buttons:\n" +
            "/start, Create, Help");

    private String value;

    Response(String str) {
        this.value = str;
    }

    @Override
    public String toString() {
        return value;
    }
}
