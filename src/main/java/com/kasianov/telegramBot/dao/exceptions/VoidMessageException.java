package com.kasianov.telegramBot.dao.exceptions;

public class VoidMessageException extends RuntimeException {
    private String message;
    public VoidMessageException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
