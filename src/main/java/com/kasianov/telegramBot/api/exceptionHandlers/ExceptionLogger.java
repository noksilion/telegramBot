package com.kasianov.telegramBot.api.exceptionHandlers;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ExceptionLogger {
    public RuntimeException logAndReturnException(Logger logger, RuntimeException throwable) throws RuntimeException{
        logger.error(String.format("Message - %s",throwable.getMessage()));
        return throwable;
    }
}
