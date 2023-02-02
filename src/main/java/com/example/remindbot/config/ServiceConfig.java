package com.example.remindbot.config;

import com.example.remindbot.service.CallbackService;
import com.example.remindbot.service.MessageService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ServiceConfig {
    public final Map<String, CallbackService> callbackServiceMap;
    public final Map<String, MessageService> messageServiceMap;
}
