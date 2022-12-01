package com.example.remindbot.model.dto;

import com.example.remindbot.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceWrapper {
    private Long id;
    private String userMsg;
    private UserRepo userRepo;
}
