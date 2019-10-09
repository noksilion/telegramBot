package com.kasianov.telegramBot.dao.exceptions;

public class NoSuchEntityException extends RuntimeException {
    private String message;

    public NoSuchEntityException(String entityName, String withWhat) {
        this.message = String.format("There are no such %s with %s", entityName, withWhat);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
