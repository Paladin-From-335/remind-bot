package com.example.remindbot.model.constants.menu;

public enum ReplyMenu {

    edit_reminder_query("Edit reminder \uD83D\uDCDD"),
    save_reminder_query("Save reminder \uD83D\uDCBE"),
    delete_reminder_query("Delete reminder \uD83D\uDDD1");

    private String value;

    ReplyMenu(String str) {
        this.value = str;
    }

    @Override
    public String toString() {
        return value;
    }
}
