package com.kasianov.telegramBot.buisnesLogic.validator;

import com.kasianov.telegramBot.api.exceptionHandlers.ExceptionLogger;
import com.kasianov.telegramBot.dao.exceptions.VoidMessageException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CheckIfNullInListThrowVoidMessageExceptionValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckIfNullInListThrowVoidMessageExceptionValidator.class);
    private final ExceptionLogger exceptionLogger;

    public void checkIfNullInList(List list,String message){
        if(list.contains(null)){
            throw exceptionLogger.logAndReturnException(LOGGER, new VoidMessageException(message));
        }
    }
}
