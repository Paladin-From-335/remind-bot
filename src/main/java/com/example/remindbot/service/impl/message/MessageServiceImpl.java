package com.example.remindbot.service.impl.message;

import com.example.remindbot.model.constants.State;
import com.example.remindbot.service.MessageService;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public abstract class MessageServiceImpl implements MessageService {
    private State key;
}
