package com.example.remindbot.model.constants.menu;

public enum EditFieldMenu {

    return_to_edit_query("Return \uD83D\uDD19");

    private String value;

    EditFieldMenu(String str) {
        this.value = str;
    }

    @Override
    public String toString() {
        return value;
    }
}
