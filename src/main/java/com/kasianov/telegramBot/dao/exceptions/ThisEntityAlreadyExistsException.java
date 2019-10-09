package com.kasianov.telegramBot.dao.exceptions;

public class ThisEntityAlreadyExistsException extends RuntimeException {
    private String message;

    public ThisEntityAlreadyExistsException(String entityName) {
        this.message = "This " + entityName + " already exists DB";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
