package com.example.remindbot.utils.exception;

public class IncorrectDateTimeException extends RuntimeException {
    public IncorrectDateTimeException() {
    }

    public IncorrectDateTimeException(String message) {
        super(message);
    }
}
