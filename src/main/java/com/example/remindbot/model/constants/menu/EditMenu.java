package com.example.remindbot.model.constants.menu;

public enum EditMenu {

    edit_text_query("Edit text \uD83E\uDDFE"),
    edit_date_query("Edit date \uD83D\uDCC5"),
    edit_time_query("Edit time \uD83D\uDD54"),
    return_to_menu_query("Return \uD83D\uDD19");

    private String value;

    EditMenu(String str) {
        this.value = str;
    }

    @Override
    public String toString() {
        return value;
    }
}
