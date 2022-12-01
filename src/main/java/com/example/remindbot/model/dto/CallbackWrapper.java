package com.example.remindbot.model.dto;

import com.example.remindbot.service.RemindService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CallbackWrapper {

    private Long id;
    private RemindService remindService;
}
