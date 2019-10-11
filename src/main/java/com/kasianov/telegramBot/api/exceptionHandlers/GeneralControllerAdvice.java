package com.kasianov.telegramBot.api.exceptionHandlers;

import com.kasianov.telegramBot.dao.dtos.BaseErrorDTO;
import com.kasianov.telegramBot.dao.exceptions.NoSuchEntityException;
import com.kasianov.telegramBot.dao.exceptions.ThisEntityAlreadyExistsException;
import com.kasianov.telegramBot.dao.exceptions.VoidMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = "com.kasianov.telegramBot")
public class GeneralControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private BaseErrorDTO MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder errorString = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errorString.append(error.getField()).append(": ").append(error.getDefaultMessage());
            errorString.append("  ");
        }
        VoidMessageException exception = new VoidMessageException(errorString.toString());
        return mapException(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ThisEntityAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    private BaseErrorDTO thisEntityAlreadyExists(ThisEntityAlreadyExistsException e) {
        return mapException(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoSuchEntityException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    private BaseErrorDTO noSuchPositionException(NoSuchEntityException e) {
        return mapException(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VoidMessageException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    private BaseErrorDTO noSuchPositionException(VoidMessageException e) {
        return mapException(e, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    private BaseErrorDTO internalError(Exception e) {
        return mapException(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private BaseErrorDTO mapException(Exception e, HttpStatus status) {
        String msg = e.getMessage().isEmpty() ? e.getClass().toString() : e.getMessage();
        return new BaseErrorDTO(status, msg, e.getMessage());
    }
}
