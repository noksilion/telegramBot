package com.kasianov.telegramBot.dao.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseErrorDTO {
    private HttpStatus status;
    private String message;
    private String debugMessage;
}
