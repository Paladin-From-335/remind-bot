package com.example.remindbot.config;

import com.example.remindbot.model.constants.State;
import com.example.remindbot.service.CallbackService;
import com.example.remindbot.service.impl.message.MessageServiceImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ServiceConfig {

    private final List<MessageServiceImpl> messageServiceList;
    public final Map<String, CallbackService> callbackServiceMap;
    public Map<State, MessageServiceImpl> messageServiceMap = new HashMap<>();

    @PostConstruct
    private void initInterfaces() {
        messageServiceMap = messageServiceList.stream()
                .collect(Collectors.toMap(MessageServiceImpl::getKey, messageService -> messageService));
    }
}
