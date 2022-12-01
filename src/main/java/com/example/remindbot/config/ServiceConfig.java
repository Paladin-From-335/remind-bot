package com.example.remindbot.config;

import static java.util.Map.entry;

import com.example.remindbot.model.constants.State;
import com.example.remindbot.service.CallbackService;
import com.example.remindbot.service.MessageService;
import com.example.remindbot.service.impl.callback.DeleteCallbackImpl;
import com.example.remindbot.service.impl.callback.EditCallbackImpl;
import com.example.remindbot.service.impl.callback.EditDateCallbackImpl;
import com.example.remindbot.service.impl.callback.EditTextCallbackImpl;
import com.example.remindbot.service.impl.callback.EditTimeCallbackImpl;
import com.example.remindbot.service.impl.callback.SaveCallbackImpl;
import com.example.remindbot.service.impl.message.CreateMessageImpl;
import com.example.remindbot.service.impl.message.DateMessageImpl;
import com.example.remindbot.service.impl.message.EditDateMessageImpl;
import com.example.remindbot.service.impl.message.EditTextMessageImpl;
import com.example.remindbot.service.impl.message.EditTimeMessageImpl;
import com.example.remindbot.service.impl.message.HelpMessageImpl;
import com.example.remindbot.service.impl.message.StartMessageImpl;
import com.example.remindbot.service.impl.message.TextMessageImpl;
import com.example.remindbot.service.impl.message.TimeMessageImpl;
import com.example.remindbot.service.impl.message.TimezoneMessageImpl;
import java.util.Map;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    public Map<State, MessageService> messageServiceMap = Map.ofEntries(
            entry(State.START, new StartMessageImpl()),
            entry(State.HELP, new HelpMessageImpl()),
            entry(State.CREATE, new CreateMessageImpl()),
            entry(State.REMIND_DESCRIPTION, new TextMessageImpl()),
            entry(State.REMIND_DATE, new DateMessageImpl()),
            entry(State.REMIND_TIME, new TimeMessageImpl()),
            entry(State.EDIT_TEXT, new EditTextMessageImpl()),
            entry(State.EDIT_DATE, new EditDateMessageImpl()),
            entry(State.EDIT_TIME, new EditTimeMessageImpl()),
            entry(State.SET_TIMEZONE, new TimezoneMessageImpl())
    );

    public Map<String, CallbackService> callbackServiceMap = Map.ofEntries(
            entry("save_reminder_query", new SaveCallbackImpl()),
            entry("edit_reminder_query", new EditCallbackImpl()),
            entry("edit_text_query", new EditTextCallbackImpl()),
            entry("edit_date_query", new EditDateCallbackImpl()),
            entry("edit_time_query", new EditTimeCallbackImpl()),
            entry("delete_reminder_query", new DeleteCallbackImpl())
    );
}
