package com.example.remindbot.utils;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum Command {

    START("/start"),
    EN("/en"),
    UA("/ua"),
    CREATE("/create"),
    REMINDS("/remind_list"),
    HELP("/help");

    private String value;

    Command(String str) {
        this.value = str;
    }

    @Override
    public String toString() {
        return value;
    }
}
