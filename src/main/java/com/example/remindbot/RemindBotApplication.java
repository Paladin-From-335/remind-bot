package com.example.remindbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RemindBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemindBotApplication.class, args);

    }

}
