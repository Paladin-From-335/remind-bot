package com.example.remindbot.model.dto;

import com.example.remindbot.service.RemindService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CallbackWrapper {

    private Long id;
    private Integer lastMsgId;
    private RemindService remindService;

    public CallbackWrapper(Long id, RemindService remindService) {
        this.id = id;
        this.remindService = remindService;
    }
}
